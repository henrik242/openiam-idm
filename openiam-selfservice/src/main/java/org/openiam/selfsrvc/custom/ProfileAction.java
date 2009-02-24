//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.selfsrvc.custom;


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

import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.service.MetadataService;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;

import diamelle.common.org.*;
//import diamelle.common.service.Service;
//import diamelle.common.service.ServiceMgr;
//mport diamelle.common.status.StatusCodeValue;


import diamelle.util.Log;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;


public class ProfileAction extends NavigationDispatchAction {

	AuthenticatorAccess authAccess = null; 
	AuditLogAccess logAccess = null;

	OrganizationAccess compAccess = null;
	
	MetadataService metadataSrvc = null;
	UserDataService userDataSrvc = null;
	
	
	public ProfileAction() {
		try {
		//userAccess = new UserAccess();
		authAccess = new AuthenticatorAccess();
		logAccess = new AuditLogAccess();
		compAccess = new OrganizationAccess();
		//serviceAccess = new ServiceAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

   /** 
     * Creates a new User or Saves a Users details if its an existing user.
     */
     public ActionForward saveUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
 	 
    	 User userData = new User();
    	 Address adr = new Address(); 
    	 EmailAddress email = new EmailAddress();
    	 Phone cellPhone = new Phone();
    	 Phone workPhone = new Phone();
    	 Phone faxPhone = new Phone();
    	 
    	 Phone personalPhone = new Phone();
    	 Phone altPhone = new Phone();
    	 Phone altcellPhone = new Phone();
    	 Phone homePhone = new Phone();
    	 
    	 
         String personId = null;
      //  String submit = null;
         String logMsg = null;

        try {
           ActionErrors errors = null;
           
           HttpSession session = request.getSession();          
           DynaValidatorForm userForm = (DynaValidatorForm)form;

	   	   WebApplicationContext webCtx = getWebApplicationContext();
		   metadataSrvc = (MetadataService)webCtx.getBean("metadataService");
		   userDataSrvc = (UserDataService)webCtx.getBean("userManager");
           
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
 		  userData.setStatus("APPROVED");
          formToAddress(adr,userForm);
          formToEmail(email,userForm);
          formToPhone(workPhone,userForm,"WORK");
          formToPhone(cellPhone,userForm,"CELL");
          formToPhone(faxPhone,userForm,"FAX");
          formToPhone(personalPhone,userForm,"Personal");

          formToPhone(altPhone,userForm,"AltPhone");
          formToPhone(altcellPhone,userForm,"Alt. Cell Phone");
          formToPhone(homePhone,userForm,"Home");
          
          

          
          
           if (personId != null && personId.length() > 0){
     		   userData.setUserId(personId);
   		      		   
     		  // update an existing user record
     		  userDataSrvc.updateUserWithDependent(userData, false); 
     		  // LOG the event
               if (logMsg == null) {
               	logMsg = "User id=" + personId + " profile was updated through the self service application";
               }
               AuditLogAccess.logEvent(logMsg, request.getRemoteHost(), 
               		(String)session.getAttribute("userId"),
               		(String)session.getAttribute("login"),"IDM");
               
               adr.setParentId(personId);
               userDataSrvc.updateAddress(adr);
               
               email.setParentId(personId);
               userDataSrvc.updateEmailAddress(email);
               
               workPhone.setParentId(personId);
               cellPhone.setParentId(personId);
               faxPhone.setParentId(personId);
               
	          	personalPhone.setParentId(personId);
	        	altPhone.setParentId(personId);
	        	altcellPhone.setParentId(personId);
	        	homePhone.setParentId(personId);
               
               
               userDataSrvc.updatePhone(workPhone);
               userDataSrvc.updatePhone(cellPhone);
               userDataSrvc.updatePhone(faxPhone);
               
               userDataSrvc.updatePhone(personalPhone);
               userDataSrvc.updatePhone(altPhone);
               userDataSrvc.updatePhone(altcellPhone);
               userDataSrvc.updatePhone(homePhone);
               
             //  userDataSrvc.updatePhone(personalPhone);
               

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
   	
    	 System.out.println("validate login called.");
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
        
		WebApplicationContext webCtx = getWebApplicationContext();
		metadataSrvc = (MetadataService)webCtx.getBean("metadataService");
		userDataSrvc = (UserDataService)webCtx.getBean("userManager");
		
		session.setAttribute("roleList", this.getRoleList());
		
        try {
           //UserAccess userAccess = new UserAccess();
           personId = (String)session.getAttribute("userId");
           String login = (String)session.getAttribute("login");
           
           String saved = (String)request.getAttribute("saved");
           if (saved != null) {
           	request.setAttribute("saved",saved);
           }
           
           User userData = userDataSrvc.getUserWithDependent(personId,false);
           
           System.out.println("UserData in view = " + userData);

           //UserData userData = userAccess.getUser(personId);
           if (userData != null) {
              this.populateFormBean(userData, userForm,login);
              request.setAttribute("personData", userData);
              // get the phone, address and email
              Address adr = userDataSrvc.getAddressByName(personId, "PRIMARY");
              
              Phone workPhone = userDataSrvc.getPhoneByName(personId, "Desk Phone");
              Phone cellPhone = userDataSrvc.getPhoneByName(personId, "Cell Phone");
              Phone faxPhone = userDataSrvc.getPhoneByName(personId, "Fax");
              
              Phone personalPhone = userDataSrvc.getPhoneByName(personId, "Personal");
              Phone altPhone = userDataSrvc.getPhoneByName(personId, "AltPhone");
              Phone altcellPhone = userDataSrvc.getPhoneByName(personId, "Alt. Cell Phone");
              Phone homePhone = userDataSrvc.getPhoneByName(personId, "Home");
                       
              
              
              EmailAddress email = userDataSrvc.getEmailAddressByName(personId, "PRIMARY");
              
              // temp
              
              Map<String, UserAttribute> attrMap = userDataSrvc.getAllAttributes(personId);
              request.setAttribute("attrMap", attrMap);
              
              
              addressToForm(userForm,adr);
              phoneToForm(userForm,workPhone, "WORK");
              phoneToForm(userForm,cellPhone, "CELL");
              phoneToForm(userForm,faxPhone, "FAX");

              phoneToForm(userForm,personalPhone, "Personal");
              phoneToForm(userForm,altPhone, "AltPhone");
              phoneToForm(userForm,altcellPhone, "Alt. Cell Phone");
              phoneToForm(userForm,homePhone, "Home");
              
              
              emailToForm(userForm,email);
                           
           }
           String userTypeId = userData.getMetadataTypeId();

           
           if (userTypeId != null && userTypeId.length()>0) {
        	   MetadataElement[] elementAry = metadataSrvc.getMetadataElementByType(userTypeId);
        	   request.setAttribute("metadata", elementAry);

           }
           
           request.setAttribute("personId",personId);
 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return (mapping.findForward("success"));

    }


     
    /**
     * Retrieves the data from Form and sets in the UserData object
     */
    private User getUserDetails(User userData, DynaValidatorForm form) {
         CalendarUtil calUtil = new CalendarUtil();
         userData.setFirstName((String) form.get("firstName"));
         userData.setMiddleInit((String) form.get("middleName"));
         userData.setLastName((String) form.get("lastName"));

         userData.setDeptCd((String) form.get("dept"));
         userData.setSex((String) form.get("sex"));
         userData.setTitle((String) form.get("title"));
         //userData.setCreateDate(new Timestamp(System.currentTimeMillis()));
         userData.setLastUpdate(new Timestamp(System.currentTimeMillis()));
         //userData.setCreatedBy(");
   
         userData.setLocationCd((String) form.get("location"));
         userData.setManagerId((String) form.get("manager"));
         
        
         String birthdate = (String)form.get("birthday");
         if (birthdate != null && birthdate.length() > 0)
            userData.setBirthdate(calUtil.getSqlDate(birthdate, "MM-dd-yyyy"));   
         
         String companyId = (String)form.get("companyId");
         if (companyId != null && companyId.length() > 0)
            userData.setCompanyId((String) form.get("companyId"));
             
         userData.setLocationName((String) form.get("role"));
         userData.setSuffix((String) form.get("suffix"));
         userData.setMailCode((String) form.get("nickname"));
         
              
         userData.setStatus((String) form.get("status"));  
         return userData;
    }

    private Address formToAddress(Address adr, DynaValidatorForm form) {
    	adr.setAddress1((String) form.get("address1"));
    	adr.setAddress2((String)form.get("address2"));
    	adr.setCity((String)form.get("city"));
    	adr.setIsDefault(1);
    	adr.setDescription("PRIMARY");
    	adr.setPostalCd((String)form.get("zip"));
    	adr.setState((String)form.get("state"));
    	adr.setAddressId((String)form.get("addressId"));
    	adr.setCountry((String)form.get("country"));
    	adr.setParentType("USER");
    	return adr;
    	
    }
    private EmailAddress formToEmail(EmailAddress email, DynaValidatorForm form) {
    	email.setEmailId((String)form.get("emailId"));
    	email.setEmailAddress((String)form.get("email"));
    	email.setDescription("PRIMARY");
    	email.setIsDefault(1);
    	email.setParentType("USER");
    	return email;
    	
    }
    private Phone formToPhone(Phone ph, DynaValidatorForm form, String phoneType) {
    	if (phoneType.equals("WORK")) {
    		ph.setAreaCd((String)form.get("phone_areacd"));
    		ph.setDescription("WORK");
    		ph.setPhoneNbr((String)form.get("phone_nbr"));
    		ph.setPhoneId((String)form.get("workPhoneId"));
    		ph.setParentType("USER");
    		ph.setName("Desk Phone");
    	}
    	if (phoneType.equals("CELL")) {
    		ph.setAreaCd((String)form.get("cell_areacd"));
    		ph.setDescription("CELL");
    		ph.setPhoneNbr((String)form.get("cell_nbr"));
    		ph.setPhoneId((String)form.get("cellPhoneId"));
    		ph.setParentType("USER");
    		ph.setName("Cell Phone");
    	}
    	if (phoneType.equals("FAX")) {
    		ph.setAreaCd((String)form.get("fax_areacd"));
    		ph.setDescription("FAX");
    		ph.setPhoneNbr((String)form.get("fax_nbr"));
    		ph.setPhoneId((String)form.get("faxPhoneId"));
    		ph.setParentType("USER");
    		ph.setName("Fax");
    	}

    	
    	if (phoneType.equals("Personal")) {
    		ph.setAreaCd((String)form.get("personal_areacd"));
    		ph.setDescription("Personal");
    		ph.setPhoneNbr((String)form.get("personal_nbr"));
    		ph.setPhoneId((String)form.get("personalPhoneId"));
    		ph.setParentType("USER");
    		ph.setName("Personal");   		
    	}
    	if (phoneType.equals("AltPhone")) {
    		ph.setAreaCd((String)form.get("altphone_areacd"));
    		ph.setDescription("AltPhone");
    		ph.setPhoneNbr((String)form.get("altphone_nbr"));
    		ph.setPhoneId((String)form.get("altPhoneId"));
    		ph.setParentType("USER");
    		ph.setName("AltPhone");
    	}
    	if (phoneType.equals("Alt. Cell Phone")) {
    		ph.setAreaCd((String)form.get("altcellphone_areacd"));
    		ph.setDescription("Alt. Cell Phone");
    		ph.setPhoneNbr((String)form.get("altcellphone_nbr"));
    		ph.setPhoneId((String)form.get("altcellPhoneId"));
    		ph.setParentType("USER");
    		ph.setName("Alt. Cell Phone");  		
    	}    	
    	if (phoneType.equals("Home")) {
    		ph.setAreaCd((String)form.get("homephone_areacd"));
    		ph.setDescription("Home");
    		ph.setPhoneNbr((String)form.get("homephone_nbr"));
    		ph.setPhoneId((String)form.get("homePhoneId"));
    		ph.setParentType("USER");
    		ph.setName("Home");   		
    	}  
    	
    	return ph;
    	
    }
   
    private void addressToForm(DynaValidatorForm form, Address adr) {
    	if (adr != null) {
       	form.set("address1",adr.getAddress1());
    	form.set("address2",adr.getAddress2());
    	form.set("city", adr.getCity());
    	form.set("zip",adr.getPostalCd());
    	form.set("state",adr.getState());
    	form.set("addressId",adr.getAddressId());
    	form.set("country", adr.getCountry());
    	}
   	
    }
    private void phoneToForm(DynaValidatorForm form, Phone ph, String phoneType) {
       	if (ph == null)
       		return;
    	if (phoneType.equals("WORK")) {
    		form.set("phone_areacd",ph.getAreaCd());
    		form.set("phone_nbr",ph.getPhoneNbr());
    		form.set("workPhoneId",ph.getPhoneId());
    	}
    	if (phoneType.equals("CELL")) {
    		form.set("cell_areacd",ph.getAreaCd());
    		form.set("cell_nbr",ph.getPhoneNbr());
    		form.set("cellPhoneId",ph.getPhoneId());
    		
    	}
    	if (phoneType.equals("FAX")) {
    		form.set("fax_areacd",ph.getAreaCd());
    		form.set("fax_nbr",ph.getPhoneNbr());
    		form.set("faxPhoneId",ph.getPhoneId());
   		
    	}
    	if (phoneType.equals("Personal")) {
    		form.set("personal_areacd",ph.getAreaCd());
    		form.set("personal_nbr",ph.getPhoneNbr());
    		form.set("personalPhoneId",ph.getPhoneId());
   		
    	}
    	if (phoneType.equals("AltPhone")) {
    		form.set("altphone_areacd",ph.getAreaCd());
    		form.set("altphone_nbr",ph.getPhoneNbr());
    		form.set("altPhoneId",ph.getPhoneId());
   		
    	}
    	if (phoneType.equals("Alt. Cell Phone")) {
    		form.set("altcellphone_areacd",ph.getAreaCd());
    		form.set("altcellphone_nbr",ph.getPhoneNbr());
    		form.set("altcellPhoneId",ph.getPhoneId());
   		
    	}    	
    	if (phoneType.equals("Home")) {
    		form.set("homephone_areacd",ph.getAreaCd());
    		form.set("homephone_nbr",ph.getPhoneNbr());
    		form.set("homePhoneId",ph.getPhoneId());
   		
    	}    	
    	
    	
    	
    }
    private void emailToForm(DynaValidatorForm form,EmailAddress email) {
    if (email != null) {
       	form.set("emailId",email.getEmailId());
    	form.set("email",email.getEmailAddress());
    } 	
    }

    /**
     * Retrieves information from UserData and sets it in Form
     */
    private void populateFormBean(User ud, DynaValidatorForm uform, String login) {
        CalendarUtil calUtil = new CalendarUtil();

      uform.set("personId", ud.getUserId());

      uform.set("firstName", ud.getFirstName());
      uform.set("middleName", ud.getMiddleInit());
      uform.set("lastName", ud.getLastName());

      if (ud.getMetadataTypeId() != null) {
    	  uform.set("typeId", ud.getMetadataTypeId());
      }
      uform.set("dept", ud.getDeptCd());
      uform.set("companyId", ud.getCompanyId());
      if ( ud.getCompanyId() != null) { 
      	try {
	      OrganizationValue compData =  compAccess.getOrganization( ud.getCompanyId());
	      uform.set("companyName", compData.getOrganizationName());
      	}catch(RemoteException re) {
      		Log.error(re.getMessage(),re);
      	}
      }
      uform.set("location", ud.getLocationCd());
      // hack
      uform.set("role", ud.getLocationName());
      uform.set("suffix", ud.getSuffix());
      uform.set("nickname", ud.getMailCode());
      
      uform.set("manager", ud.getManagerId());
      uform.set("email",login);
      
      uform.set("sex", ud.getSex());
      Date createTime = ud.getCreateDate();
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

      
      //if (ud.getStatusId() != null )
      //   uform.set("status", ud.);
      uform.set("status",ud.getStatus());   
      uform.set("title", ud.getTitle());
      
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

 	private List getRoleList() {
	   	ArrayList roleList = new ArrayList();
	   	roleList.add(new LabelValueBean("",""));
	   	roleList.add(new LabelValueBean("Manager",""));
	   	roleList.add(new LabelValueBean("LSA","LSA"));
	   	roleList.add(new LabelValueBean("User","User"));
	   	roleList.add(new LabelValueBean("None","None"));
	   	
		
	    return roleList;	
	}
 	
	
}