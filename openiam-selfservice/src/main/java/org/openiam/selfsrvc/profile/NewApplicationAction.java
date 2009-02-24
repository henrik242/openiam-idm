//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.selfsrvc.profile;


import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
//import org.openiam.selfsrvc.util.*;

import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.User;

import diamelle.common.org.*;
import diamelle.common.service.Service;
import diamelle.common.service.ServiceMgr;
import diamelle.common.status.StatusCodeValue;
import diamelle.ebc.user.*;
//import diamelle.common.company.*;
import diamelle.security.auth.*;


import diamelle.util.Log;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;

/** 
 * MyEclipse Struts
 * Creation date: 02-25-2005
 * 
 * XDoclet definition:
 * @struts:action path="/customerProfile" name="customerProfileForm" input="/customerProfile.jsp" scope="request" validate="true"
 */
public class NewApplicationAction extends NavigationDispatchAction {

	UserAccess userAccess = null;
	AuthenticatorAccess authAccess = null; 
	AuditLogAccess logAccess = null;
	TokenAccess tknAccess = null;
	OrganizationAccess compAccess = null;
	
	
	public NewApplicationAction() {
		try {
		userAccess = new UserAccess();
		authAccess = new AuthenticatorAccess();
		logAccess = new AuditLogAccess();
		tknAccess = new TokenAccess();
		compAccess = new OrganizationAccess();
		//serviceAccess = new ServiceAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ActionForward userForm ( ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse res ) throws IOException, ServletException {

        try {
           
           HttpSession session = request.getSession();
   			
           ServletContext servletContext =  getServlet().getServletConfig().getServletContext();

		
           loadStaticData(session, servletContext);
           DynaValidatorForm userForm = (DynaValidatorForm)form;
           userForm.set("personId", "" );
        } catch(Exception e) {
            e.printStackTrace();
        }
        return (mapping.findForward("success"));
    }
	
	


	
   /** 
     * Creates a new User or Saves a Users details if its an existing user.
     */
     public ActionForward saveUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
        UserData userData = new UserData();
        Address adr = AddressFactory.create();
        Email email = EmailFactory.create();
        Phone cellPhone = PhoneFactory.create();
        Phone workPhone = PhoneFactory.create();
        Phone faxPhone = PhoneFactory.create();
        String personId = null;
      //  String submit = null;
        String logMsg = null;

        try {
           ActionErrors errors = null;
           
           HttpSession session = request.getSession();          
           DynaValidatorForm userForm = (DynaValidatorForm)form;

           // is actually the personId
           personId = (String) userForm.get("personId");
           if (personId != null)
            request.setAttribute("personId", personId);

   	  	  errors = validateLogin(errors,(String)userForm.get("firstName"));    	  	  
  	  	  if (errors != null) {
  	  	  	saveErrors(request, errors);
  	  	  	return (mapping.findForward("success"));
   	  	  }
  	  	                    
  	  	 

 		  userData = getUserDetails(userData, userForm);
          formToAddress(adr,userForm);
          formToEmail(email,userForm);
          formToPhone(workPhone,userForm,"WORK");
          formToPhone(cellPhone,userForm,"CELL");
          formToPhone(faxPhone,userForm,"FAX");

           if (personId != null && personId.length() > 0){
     		   userData.setId(personId);
   		      		   
     		  // update an existing user record
               userAccess.saveUser(userData);
               // LOG the event
               if (logMsg == null) {
               	logMsg = "User id=" + personId + " profile was updated through the self service application";
               }
               AuditLogAccess.logEvent(logMsg, request.getRemoteHost(), 
               		(String)session.getAttribute("userId"),
               		(String)session.getAttribute("login"),"IDM");
               
               adr.setParentId(personId);
               userAccess.saveAddress(personId,adr);
               
               email.setParentId(personId);
               userAccess.saveEmail(personId,email);
               
               workPhone.setParentId(personId);
               cellPhone.setParentId(personId);
               faxPhone.setParentId(personId);
               userAccess.savePhone(personId,workPhone);
               userAccess.savePhone(personId,cellPhone);
               userAccess.savePhone(personId,faxPhone);
               

           } 
	              
        } catch(Exception e) {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
            //errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
            saveErrors(request, errors);
            e.printStackTrace();
        }
        request.setAttribute("saved","1");
        return (view(mapping,form, request, res));
        
    }
     
 
     private  ActionErrors validateLogin(ActionErrors errors, String name) {
   	
     	 if (name == null || name.length() ==0) {
  	  		if (errors == null) {
  	  			errors = new ActionErrors();
  	  		}
  	  		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.name"));
   	  		//errors.add("error.name", new ActionError("error.name"));
  	 	}
      	return errors;

     }
 

 
    /** Private methods **/

    /**
     *  Retrieves the information of the user that is logged in.
     */
    public ActionForward view (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resb){
        HttpSession session = request.getSession();
        String personId = null;
        DynaValidatorForm userForm = (DynaValidatorForm) form;
        
        try {
           UserAccess userAccess = new UserAccess();
           personId = request.getParameter("personId");
           //personId = (String)session.getAttribute("userId");
           
           String saved = (String)request.getAttribute("saved");
           if (saved != null) {
           	request.setAttribute("saved",saved);
           }
           


           UserData userData = userAccess.getUser(personId);
           if (userData != null) {
              this.populateFormBean(userData, userForm);
              request.setAttribute("personData", userData);
              // get the phone, address and email
              Address adr = userAccess.getAddressByName(personId,"DEFAULT");
              Email email = userAccess.getEmailByName(personId,"DEFAULT");
              Phone workPhone = userAccess.getPhoneByName(personId,"WORK");
              Phone cellPhone = userAccess.getPhoneByName(personId,"CELL");
              Phone faxPhone = userAccess.getPhoneByName(personId,"FAX");
              addressToForm(userForm,adr);
              phoneToForm(userForm,workPhone, "WORK");
              phoneToForm(userForm,cellPhone, "CELL");
              phoneToForm(userForm,faxPhone, "FAX");
              emailToForm(userForm,email);
                           
           }
           
           request.setAttribute("personId",personId);
 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return (mapping.findForward("success"));

    }


    public ActionForward directory (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resb){
        HttpSession session = request.getSession();
        String personId = null;
        DynaValidatorForm userForm = (DynaValidatorForm) form;
        
        try {
        	
       	 WebApplicationContext webContext =  getWebApplicationContext();
        	
           UserAccess userAccess = new UserAccess();
           personId = request.getParameter("personId");
 
           UserData userData = userAccess.getUser(personId);
                    
           if (userData != null) {
              this.populateFormBean(userData, userForm);
              request.setAttribute("personData", userData);
              // get the phone, address and email
              Address adr = userAccess.getAddressByName(personId,"DEFAULT");
              Email email = userAccess.getEmailByName(personId,"DEFAULT");
              Phone workPhone = userAccess.getPhoneByName(personId,"WORK");
              Phone cellPhone = userAccess.getPhoneByName(personId,"CELL");
              Phone faxPhone = userAccess.getPhoneByName(personId,"FAX");
              addressToForm(userForm,adr);
              phoneToForm(userForm,workPhone, "WORK");
              phoneToForm(userForm,cellPhone, "CELL");
              phoneToForm(userForm,faxPhone, "FAX");
              emailToForm(userForm,email);                         
           
              // GET DIRECT REPORTS
              UserDataService userDataSrv = (UserDataService)webContext.getBean("userManager");
              
              request.setAttribute("directReports",  userDataSrv.getEmployees(personId) );
                   
              // GET SUPERVISOR
             
              request.setAttribute("supervisor",userDataSrv.getPrimarySupervisor(personId) );
           
           }
           
           request.setAttribute("personId",personId);
 
        } catch(Exception e) {
            e.printStackTrace();
        }
     	return (mapping.findForward("directory"));
    }
    
    /**
     * Retrieves the data from Form and sets in the UserData object
     */
    private UserData getUserDetails(UserData userData, DynaValidatorForm form) {
         CalendarUtil calUtil = new CalendarUtil();
         userData.setFirstName((String) form.get("firstName"));
         userData.setMiddleInit((String) form.get("middleName"));
         userData.setLastName((String) form.get("lastName"));

         //userData.setEMail((String) form.get("email"));
         userData.setUserInd((String) form.get("userInd"));
         userData.setDept((String) form.get("dept"));
         userData.setSex((String) form.get("sex"));
         userData.setTitle((String) form.get("title"));
         userData.setTaxCode((String) form.get("tax"));
         //userData.setCreateDate(new Timestamp(System.currentTimeMillis()));
         userData.setLstUpdated(new Timestamp(System.currentTimeMillis()));
         //userData.setCreatedBy(");
         
         String typeId = (String) form.get("typeId");         
         if (typeId != null && typeId.length() > 0)
            userData.setTypeId(typeId);
         
         String birthdate = (String)form.get("birthday");
         if (birthdate != null && birthdate.length() > 0)
            userData.setBirthdate(calUtil.getSqlDate(birthdate, "MM-dd-yyyy"));   
         
         String companyId = (String)form.get("companyId");
         if (companyId != null && companyId.length() > 0)
            userData.setCompanyId((String) form.get("companyId"));
                 
         
         String active = (String) form.get("active");        
         if (active.equalsIgnoreCase("on"))
            userData.setActive(true);
         else
            userData.setActive(false);
         
         userData.setStatusId((String) form.get("status"));  
         return userData;
    }

    private Address formToAddress(Address adr, DynaValidatorForm form) {
    	adr.setAddress1((String) form.get("address1"));
    	adr.setAddress2((String)form.get("address2"));
    	adr.setCity((String)form.get("city"));
    	adr.setDefault(true);
    	adr.setDescription("DEFAULT");
    	adr.setPostalCode((String)form.get("zip"));
    	adr.setState((String)form.get("state"));
    	adr.setId((String)form.get("addressId"));
    	adr.setCountry((String)form.get("country"));
    	return adr;
    	
    }
    private Email formToEmail(Email email, DynaValidatorForm form) {
    	email.setId((String)form.get("emailId"));
    	email.setEmailAddress((String)form.get("email"));
    	email.setDescription("DEFAULT");
    	email.setDefault(true);
    	return email;
    	
    }
    private Phone formToPhone(Phone ph, DynaValidatorForm form, String phoneType) {
    	if (phoneType.equals("WORK")) {
    		ph.setAreaCode((String)form.get("phone_areacd"));
    		ph.setDescription("WORK");
    		ph.setPhoneNumber((String)form.get("phone_nbr"));
    		ph.setId((String)form.get("workPhoneId"));
    	}
    	if (phoneType.equals("CELL")) {
    		ph.setAreaCode((String)form.get("cell_areacd"));
    		ph.setDescription("CELL");
    		ph.setPhoneNumber((String)form.get("cell_nbr"));
    		ph.setId((String)form.get("cellPhoneId"));
    		
    	}
    	if (phoneType.equals("FAX")) {
    		ph.setAreaCode((String)form.get("fax_areacd"));
    		ph.setDescription("FAX");
    		ph.setPhoneNumber((String)form.get("fax_nbr"));
    		ph.setId((String)form.get("faxPhoneId"));
   		
    	}
    	return ph;
    	
    }
   
    private void addressToForm(DynaValidatorForm form, Address adr) {
    	if (adr != null) {
       	form.set("address1",adr.getAddress1());
    	form.set("address2",adr.getAddress2());
    	form.set("city", adr.getCity());
    	form.set("zip",adr.getPostalCode());
    	form.set("state",adr.getState());
    	form.set("addressId",adr.getId());
    	form.set("country", adr.getCountry());
    	}
   	
    }
    private void phoneToForm(DynaValidatorForm form, Phone ph, String phoneType) {
       	if (ph == null)
       		return;
    	if (phoneType.equals("WORK")) {
    		form.set("phone_areacd",ph.getAreaCode());
    		form.set("phone_nbr",ph.getPhoneNumber());
    		form.set("workPhoneId",ph.getId());
    	}
    	if (phoneType.equals("CELL")) {
    		form.set("cell_areacd",ph.getAreaCode());
    		form.set("cell_nbr",ph.getPhoneNumber());
    		form.set("cellPhoneId",ph.getId());
    		
    	}
    	if (phoneType.equals("FAX")) {
    		form.set("fax_areacd",ph.getAreaCode());
    		form.set("fax_nbr",ph.getPhoneNumber());
    		form.set("faxPhoneId",ph.getId());
   		
    	}
    	
    }
    private void emailToForm(DynaValidatorForm form,Email email) {
    if (email != null) {
       	form.set("emailId",email.getId());
    	form.set("email",email.getEmailAddress());
    } 	
    }

    /**
     * Retrieves information from UserData and sets it in Form
     */
    private void populateFormBean(UserData ud, DynaValidatorForm uform) {
        CalendarUtil calUtil = new CalendarUtil();

      uform.set("personId", ud.getId());

      uform.set("firstName", ud.getFirstName());
      uform.set("middleName", ud.getMiddleInit());
      uform.set("lastName", ud.getLastName());

      uform.set("email", ud.getEmail());
      uform.set("userInd", ud.getUserInd());

      uform.set("typeId", ud.getTypeId());
      uform.set("dept", ud.getDept());
      uform.set("companyId", ud.getCompanyId());
      if ( ud.getCompanyId() != null) { 
      	try {
	      OrganizationValue compData =  compAccess.getOrganization( ud.getCompanyId());
	      uform.set("companyName", compData.getOrganizationName());
      	}catch(RemoteException re) {
      		Log.error(re.getMessage(),re);
      	}
      }
      
      uform.set("sex", ud.getSex());
      Timestamp createTime = ud.getCreateDate();
      if (createTime != null)
      	uform.set("createDate", createTime.toString());
      uform.set("createdBy", ud.getCreatedBy());

      if (ud.getBirthdate() != null) {
      	try {
      	String dt = CalendarUtil.getDateString(ud.getBirthdate(),"MM-dd-yyyy");
      	uform.set("birthday", dt);
      	}catch(ParseException pe) {
      		Log.error("Parse Exception", pe);
      	}
      }

      if (ud.isActive())
         uform.set("active", "on");
      
      //if (ud.getStatusId() != null )
      //   uform.set("status", ud.);
      uform.set("status",ud.getStatusId());   
      uform.set("tax", ud.getTaxCode());
      uform.set("title", ud.getTitle());
      
    }


  

    /* Load Static data */
    
    private Vector codeListToVector(List codes) {
	    Vector vct = new Vector();
		LabelValueBean emptybean = new LabelValueBean("<Select a value>", "-1");
		vct.add(emptybean);

		if (codes != null) {
		  String codeKey;
		  String codeDesc;
		  Iterator codeIter = codes.iterator();
		
	      while(codeIter.hasNext()) {
			StatusCodeValue codeValue = (StatusCodeValue) codeIter.next();
			codeKey = codeValue.getStatusCd();
			codeDesc = codeValue.getDescription(); 
			LabelValueBean bean = new LabelValueBean(codeDesc,codeKey);
			vct.add(bean);
		  }
		} 
		
		return vct;
	  }	
	  
  
	private List getUserStatusList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
        CodeAccess cdAccess = new CodeAccess();
        List codeList = cdAccess.getCodesByService("100","IDM","USER","en");
    
        if (codeList != null && codeList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i<codeList.size(); i++) {       		
        		StatusCodeValue val = (StatusCodeValue)codeList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getStatusCd());
        	 	newCodeList.add(label);
        	}
        }
        return newCodeList;
    }
	
	private List getCompanyList() {
	   	ArrayList newCodeList = new ArrayList();
		try {
			OrganizationSearch search = new OrganizationSearch();
			List companyList = compAccess.searchOrganization(search);
	        if (companyList != null && companyList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i< companyList.size(); i++) {       		
        		OrganizationValue val = (OrganizationValue)companyList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getOrganizationName(),val.getOrganizationId());
        	 	newCodeList.add(label);
        	}
        }

		}catch(RemoteException re) {
			re.printStackTrace();
		}
	    return newCodeList;	
	}

	
	private List getAllGroups() throws RemoteException {
    	ArrayList newGroupList = new ArrayList();
        SecurityAccess secAccess = new SecurityAccess();
        List groupList = secAccess.getAllGroups();
        
        if (groupList != null && groupList.size() > 0) {
        	newGroupList.add(new LabelValueBean("",""));
        	for (int i=0; i< groupList.size(); i++) {       		
        		GroupValue val = (GroupValue)groupList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
        	 	newGroupList.add(label);
        	}
        }
        return newGroupList;
    }

	private List getAllServices(ServletContext servletCtx)  {
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletCtx);
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");

		
    	ArrayList newServiceList = new ArrayList();
    	List serviceList = servMgr.getAllServices();
    	//List serviceList = serviceAccess.getServiceList();
      
        if (serviceList != null && serviceList.size() > 0) {
        	newServiceList.add(new LabelValueBean("",""));
        	for (int i=0; i < serviceList.size(); i++ ) {
        		Service val = (Service)serviceList.get(i);
          	 	LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
           	 	newServiceList.add(label);
        	}        		
        }
        return newServiceList;
    }
	
/*	private List getAllServices() throws RemoteException {
    	ArrayList newServiceList = new ArrayList();
    	List serviceList = serviceAccess.getServiceList();
      
        if (serviceList != null && serviceList.size() > 0) {
        	newServiceList.add(new LabelValueBean("",""));
        	for (int i=0; i < serviceList.size(); i++ ) {
        		ServiceValue val = (ServiceValue)serviceList.get(i);
          	 	LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
           	 	newServiceList.add(label);
        	}        		
        }
        return newServiceList;
    }
 */	
	
	private void loadStaticData(HttpSession session, ServletContext servletContext) throws RemoteException {
        if (session.getAttribute("services") == null) {
           	// we dont have the data for the drop downs.
           	session.setAttribute("services", getAllServices(servletContext));
           	
           	// get the list of groups - so that we can have a default group
           	// for a newly created user
           	List groupList = this.getAllGroups();
           	session.setAttribute("groupList", groupList);
 
           	// show the status codes if they are not already populated    	
            List statusList = getUserStatusList();
	        session.setAttribute("statusList", statusList);
        
        } 		
	}

}