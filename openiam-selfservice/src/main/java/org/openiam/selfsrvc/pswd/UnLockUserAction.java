package org.openiam.selfsrvc.pswd;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.*;


import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.service.dto.Service;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.selfsrvc.AppConfiguration;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;

import org.openiam.selfsrvc.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import org.apache.struts.util.LabelValueBean;

import diamelle.common.service.*;
import diamelle.common.org.*;
import diamelle.ebc.user.UserData;
import diamelle.security.auth.*;
import diamelle.security.idquest.*;
import diamelle.security.policy.*;
import diamelle.util.Log;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.*;
import javax.servlet.ServletContext;


/** 
 * MyEclipse Struts
 * Creation date: 05-24-2005
 * 
 * XDoclet definition:
 * @struts:action
 */
public class UnLockUserAction extends DispatchActionSupport {

	 static protected ResourceBundle resb = ResourceBundle.getBundle("securityconf");

	// --------------------------------------------------------- Instance Variables
	UserAccess userAccess =  null;
	OrganizationAccess compAccess = null;
	ServiceAccess serviceAccess = null;
	SecurityDomainAccess secDomainAccess = null;

	//ServiceAccess serviceAccess = null;
	AuditLogAccess logAccess = null;
	AuthenticatorAccess authAccess = new AuthenticatorAccess(); 
	IdQuestionAccess questAccess = new IdQuestionAccess();
	PolicyAccess policyAccess = new PolicyAccess();
	SecurityAccess securityAccess = new SecurityAccess();
	AppConfiguration appConfiguration;


	  // SAS - 8/31/08 - Integration with spring objects
	  protected PasswordConfiguration passwordConfig= null;
	  
 


	// --------------------------------------------------------- Methods
	public UnLockUserAction() {
		try {
			userAccess = new UserAccess();
			logAccess = new AuditLogAccess();
			compAccess = new OrganizationAccess();
		//	serviceAccess = new ServiceAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	  	public PasswordConfiguration getPasswordConfig() {
			return passwordConfig;
		}

		public void setPasswordConfig(PasswordConfiguration passwordConfig) {
			this.passwordConfig = passwordConfig;
		}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward view(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		HttpSession session = request.getSession();

		ServletContext servletContext =  getServlet().getServletConfig().getServletContext();
		
		serviceAccess = new ServiceAccess(getWebApplicationContext());
		appConfiguration = (AppConfiguration)getWebApplicationContext().getBean("appConfiguration");
	
		// put the configuration object in session
		session.setAttribute("logoUrl", appConfiguration.getLogoUrl());
		session.setAttribute("title", appConfiguration.getTitle());
		
		//session.setAttribute("serviceList", getAllServices());
		secDomainAccess = new SecurityDomainAccess(getWebApplicationContext());	
		session.setAttribute("serviceList", secDomainAccess.getAllDomainsWithExclude("IDM"));

		request.setAttribute("displaySyncServices", String.valueOf( passwordConfig.isDisplaySyncServices() ) ) ;
		request.setAttribute("displayDomainList", String.valueOf( passwordConfig.isDisplayDomainList() ) ) ;
		request.setAttribute("secDomain", String.valueOf( passwordConfig.getDefaultSecurityDoamin() ) ) ;
	
		
        
		// first step is to collect the loginId from the user
		// so that we can get the company they are associated with.
		return mapping.findForward("step1");

	}

	public ActionForward showQuestions(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		DynaActionForm dynForm = (DynaActionForm)form;
		ActionErrors err = new ActionErrors();
		String strQuestionCount = null;
		String questionSrc = null;
		String strQuestionList = null;
		List questionDropdownList = null;

		
		HttpSession session = request.getSession();
		
		// get the login
		String login = (String)dynForm.get("login");
		String domainId = (String)dynForm.get("domainId");
		
		String userId = validateLogin(login, domainId, err);

		if (!err.isEmpty()) {
 	  		saveErrors(request, err);
  	  		return (mapping.findForward("step1"));
		}		
		
/*		System.out.println("domainId = " + domainId);
		System.out.println("login = " + login);
		
		String userId = login;
		HttpSession session = request.getSession();
		session.setAttribute("login", login);
		session.setAttribute("domain", domainId);
		
		// temp code or ocfl
		if (domainId.startsWith("ICJIS")) {
			return (mapping.findForward("icjis"));
		}else {

			return (mapping.findForward("bcc"));
		}
*/		
		
		// -- temp code for ocfl

		
		//HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("login", login);
		session.setAttribute("domainId",domainId);
		
		try {
			List policyAttr = policyAccess.getPolicyForLogin(PolicyConstants.POLICY_TYPE_PASSWORD,	domainId, login);
			// get the parameters
			
			int size = policyAttr.size();
			for (int i=0; i < size; i++) {
				PolicyAttrValue attrValue = (PolicyAttrValue)policyAttr.get(i);
				if (attrValue.getName().equals("QUEST_COUNT")) {
					strQuestionCount = attrValue.getValue1();	
					request.setAttribute("questCount",  strQuestionCount);
				}
				if (attrValue.getName().equals("QUEST_SRC")) {
					questionSrc = attrValue.getValue1();			
					request.setAttribute("questionSrc",  questionSrc);
				}			
				if (attrValue.getName().equals("QUEST_LIST")) {
					strQuestionList = attrValue.getValue1();		
				}
			} 
			
			// check if answers to questions already exist for this user

			List answerList = questAccess.getUserAnswers(userId);
			if (answerList == null || answerList.isEmpty()) {	
				return mapping.findForward("showQuestion");
			}
			// get the questions so that we can display it on from
			List questionList = getQuestions(answerList);
			request.setAttribute("questionList", questionList);
		
		} catch (RemoteException re) {
			re.printStackTrace();
			
		}
		return mapping.findForward("showQuestion");
    
    
	}

	private List getQuestions(List answerList) throws RemoteException {
		List questionList = new ArrayList();
		int size = answerList.size();
		for (int i=0; i<size;i++) {
			QuestionValue ans = (QuestionValue)answerList.get(i);
			questionList.add(ans.getQuestionId());
		}		
		return questAccess.getSelectedQuestions(questionList);

		
	}
	private List getQuestions(String questionSrc, String questionList) throws RemoteException {
		// get the answers for this user
		// if none exist, then get the list of questions from the policy
		// if the questions are defined in the policy then get the list of questions
		// if they are user selected then get the list of questions in the question bank.
		if (questionSrc.equalsIgnoreCase("USER")) {
			// user needs to pick the questions.
			return this.questAccess.getAllQuestions();
		}else {
			// policy based definition of which questions to use.
			return this.questAccess.getSelectedQuestions(questionList);
		}		
	}



	/**
	 * Get the user answers from the form and see if  they match 
	 * what is in the database.  If they do, then ask them to if they want to 
	 * sync their password with other systems.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward processAnswers(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		String mode = request.getParameter("mode");
		QuestionValue questVal = null;
		List answerList = new ArrayList();
		List origAnswerList = null;
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		DynaActionForm dynForm = (DynaActionForm)form;
		
		serviceAccess = new ServiceAccess(getWebApplicationContext());
		
		//  get the users original answers
		try {
			origAnswerList = questAccess.getUserAnswers(userId);
			/* sas - temp hack*/
			secDomainAccess = new SecurityDomainAccess(getWebApplicationContext());	
			session.setAttribute("userServices", secDomainAccess.getAllDomainsWithExclude("IDM"));
			
			//session.setAttribute("userServices", serviceAccess.getAllServices());
			
			//session.setAttribute("userServices", securityAccess.getUserServices(userId));
			
		}catch(RemoteException re) {
			re.printStackTrace();
		}
		// check the the answer
		int size = origAnswerList.size();
		for (int i=0; i<size;i++) {
			QuestionValue val = (QuestionValue)origAnswerList.get(i);
			String questionId = val.getQuestionId();
			String userAns = request.getParameter("answer_" + questionId);
			if (!val.getAnsText().equalsIgnoreCase(userAns)) {
				//ActionErrors err = new ActionErrors();
				//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.wrongans", "error.wrongans"));
	 	  	  	//saveErrors(request, err);
	 	  	  	request.setAttribute("message","Identity verification failed. Answers were incorrect.");
	  	  	  	return this.showQuestions(mapping, form, request, response);
	 	  	  	//return (mapping.findForward("showQuestion"));
			}		
		}
		
		return mapping.findForward("showservicelist");
	}
	
		
	public ActionForward processICJISAnswers(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {


		return mapping.findForward("showservicelist");
		
	/*	String driver_classname= "com.mysql.jdbc.Driver";
		String driver_url= "jdbc:mysql://olpsdb01:3306/esi";
		String username= "openiam";
		String password= "ss0126";		
	
		
		Connection con = null;

 		try {
		  
		  Class driverObject = Class.forName(driver_classname);
		  con = DriverManager.getConnection(driver_url, username, password);
		
		  String sql = "SELECT FirstName, Initials, LastName, PreW2KName, Department, Division, employeeid, email, role " +
		  		"	from OCCC_EMPID where employeeId IS NOT NULL";

		  
 		}catch(Exception e) {
 			e.printStackTrace();
 		}finally {
 			try {
 			con.close();
 			}catch(Exception e) {
 				e.printStackTrace();
 			}
 		}
 		
 	  	request.setAttribute("message","Identity verification failed. Answers were incorrect.");
 	  	return (mapping.findForward("nonbcc"));
 	 */ 	
 		
	}


	public ActionForward processBCCAnswers(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		  String className = "oracle.jdbc.OracleDriver";  
		  String driver = "jdbc:oracle:thin:@AXPRD03:1522:HRBCPRD";
		  String uname = "ESEC_VW0";
		  String pword= "Esec4vw";

		  
 		Connection con = null;
 		
 		String ssn = request.getParameter("ssn");
 		String birthDate = request.getParameter("dateOfBirth");
 		String emplid = request.getParameter("employeeId");

 		// check the required fields
 		if (ssn == null || ssn.length() ==0) {
	 	  	request.setAttribute("message","SSN is required.");
	 	  	return (mapping.findForward("bcc")); 					
 		}
 		if (birthDate == null || birthDate.length() ==0) {
	 	  	request.setAttribute("message","Date of birth is required.");
	 	  	return (mapping.findForward("bcc")); 					
 		}
 		if (emplid == null || emplid.length() ==0) {
	 	  	request.setAttribute("message","emplid is required.");
	 	  	return (mapping.findForward("bcc")); 					
 		}
 		
 		// check the date format
 		
 		Date bDate = null;

 		//String DATE_FORMAT = "MM/dd/yyyy";
 		String DATE_FORMAT = "MMddyyyy";
 		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
 		
 		try {
 			bDate = sdf.parse(birthDate);
 		}catch(ParseException pe) {
	 	  	request.setAttribute("message","Invalid date format");
	 	  	return (mapping.findForward("bcc")); 			
 		}

 		
 		try {
		  
 			sdf.parse(birthDate);
 			
		  Class driverObject = Class.forName(className);
		  con = DriverManager.getConnection(driver, uname, pword);
		
		  String sql = "select EMPLID " +
		  		//"LAST_NAME, FIRST_NAME, MIDDLE_NAME, LOCATION, DEPTID, EMPL_STATUS, LAST_4DIGITS_SSN, LOCATION_DESCR, BIRTHDATE " +  
					   "	from PS_OC_SECURITY_VW " +
					   "	WHERE LAST_4DIGITS_SSN = ? AND EMPLID = ? AND BIRTHDATE= ? ";

		  
		  PreparedStatement stmt = con.prepareStatement(sql);
		  stmt.setString(1, ssn);
		  stmt.setString(2, emplid);
		  stmt.setDate(3, new java.sql.Date(bDate.getTime()));
		  
		  ResultSet rs = stmt.executeQuery();

		  if (rs.next()) {
			//  System.out.println("getting USER for: " + rs.getString(1) + " " + rs.getString(2) + "  " + rs.getString(3));
			  //return true;
			  //authenticated = true;
			  String employeeId = rs.getString(1);
	/*		  String lastName = rs.getString(2);
			  String firstName = rs.getString(3);
			  String middleName = rs.getString(4);
			  String location = rs.getString(5);
			  String deptid = rs.getString(6);
			  String status = rs.getString(7);
			  String social = rs.getString(8);
			  String locDesc = rs.getString(9);
			  Date dt = rs.getDate("10");
			  System.out.println("birthdate = " + birthDate);
	*/
			  System.out.println("employeeId = " + employeeId);
		  }else {
		 	  	request.setAttribute("message","Identity verification failed. Answers were incorrect.");
		 	  	return (mapping.findForward("bcc"));
		  }
		
		  stmt.close();
		 
		  }catch(Exception se) {
			  se.printStackTrace();
		  }finally {
			  try {
			  con.close();
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
		  }
		
		return mapping.findForward("showservicelist");
		
		
	}	
	


	public ActionForward syncPassword(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		DynaActionForm dynForm = (DynaActionForm)form;
		String password = (String)dynForm.get("password");
		String confpassword = (String)dynForm.get("confpassword");
		
		if (password == null || password.length() == 0) {
	  	  	request.setAttribute("message","Please enter a password");
			return mapping.findForward("showservicelist");			
		}
		if (confpassword == null || confpassword.length() == 0) {
	  	  	request.setAttribute("message","Please enter a confirmation password");
			return mapping.findForward("showservicelist");			
		}		
		if (!password.equals(confpassword)) {
	  	  	request.setAttribute("message","Confirmation password does not match the password.");
			return mapping.findForward("showservicelist");			
		}
		
		String[] serviceAry = request.getParameterValues("service");
		if (passwordConfig.isDisplaySyncServices()) {
			if (serviceAry == null || serviceAry.length == 0) {
	 	  	  	request.setAttribute("message","Please select a service");
				//ActionErrors errs = new ActionErrors();
				//errs.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.service", "error.service"));
				//saveErrors(request, errs);
				return mapping.findForward("showservicelist");
			}
		}
		
		try {
		/*	List loginList = authAccess.getAllLogins(userId);
			// iterate the login list and if a service matches on of the selected serices, then update the password.
			int size = loginList.size();
			LoginValue val = null;
			for (int i=0; i < size; i++) {
				val = (LoginValue)loginList.get(i);
				// if we don't show the services to sync with, then sync with all systems
				if (passwordConfig.isDisplaySyncServices()) {
					if (serviceSelected(val.getService(), serviceAry)) {
						val.setPassword(password);
						val.setLocked(false);
						authAccess.updateLogin(val);
					}
				}else {
						val.setPassword(password);
						val.setLocked(false);
						authAccess.updateLogin(val);	
				}
			}
	*/		
			WebApplicationContext webCtx = getWebApplicationContext();
			
			Password ldapPassword = (Password)webCtx.getBean("ldapPassword");
			Password adPassword = (Password)webCtx.getBean("adPassword");
			ConnectionParam conParam = (ConnectionParam)webCtx.getBean("ldapConParam");
			ConnectionParam conOcGovParam = (ConnectionParam)webCtx.getBean("adConOCGOVParam");
			ConnectionParam conUTParam = (ConnectionParam)webCtx.getBean("adConUTParam");
			ConnectionParam conCCParam = (ConnectionParam)webCtx.getBean("adConCCParam");
			ConnectionParam conICJISParam = (ConnectionParam)webCtx.getBean("adConICJISParam");
						
			String domainId = (String)session.getAttribute("domain");
			String login = (String)session.getAttribute("login");
			if (domainId.startsWith("RE")) {
				// LDAP
				ldapPassword.setPassword(conParam, login, password);
			}else {
				// ad 
				if (domainId.startsWith("USR")) {
					System.out.println("Preparing to change AD password...for OCGOV");
					adPassword.setPassword(conOcGovParam, login, password);
				}else {
					System.out.println("Preparing to change AD password...for ICJIS");
					adPassword.setPassword(conICJISParam, login, password);					
				}
			}
			
			
		    IdmAuditLogDataService auditService = 
	 	    		 (IdmAuditLogDataService)webCtx.getBean("auditDataService");

		       
			//UserAccess userAccess = new UserAccess();
			//UserData ud = userAccess.getUser(userId);
			// sync with connected directories
			
			//ldapPassword.setPassword(ud.getFirstName() + " " + ud.getLastName(), password);
			//ldapPassword.setPassword(val.getLogin(), password);

   		/*	String logMsg = "User id=" + val.getUserId() + " has unlocked their password";
            
  			IdmAuditLog log = new IdmAuditLog();
  			log.setActionId("PASSWORD RESET");
  			log.setActionStatus("SUCCESS");
  			log.setActionDatetime(new Date(System.currentTimeMillis()));
  			log.setHost(request.getRemoteHost());
  			log.setLogHash("00");
  			log.setObjectName(Class.forName("org.openiam.idm.srvc.user.dto.User").getName());
  			log.setObjectTypeId(val.getUserId());
  			log.setLoginId(val.getLogin());
  			log.setReason("PASSWORD_RESET");
  			log.setReasonDetail(logMsg);
  			log.setReqUrl(request.getRequestURI());
  			log.setServiceId(val.getService());
  			log.setUserId((String)session.getAttribute("userId"));
  			auditService.addLog(log);
	  */
			
			
			
		}catch(Exception re) {
			re.printStackTrace();
		}
		
		
		return mapping.findForward("success");
	}
	
	boolean serviceSelected(String serviceName, String[] serviceAry) {
		int arySize = serviceAry.length;
		
		for (int i=0; i<=arySize; i++) {
			if (serviceAry[i].equals( serviceName))
				return true;
		}
		return false;
	}
	
	
	/* Private methods */
	
	private String validateLogin(String login, String service, ActionErrors err) {
		String userId = null;
		
		if (login == null || login.length() == 0) {
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.idrequired"));
			//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.idrequired", "error.login.idrequired"));
		}
		if (service == null || service.length() == 0) {
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.service"));
			//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.service", "error.service"));			
		}
		if (login != null && service != null) {
			// see if the login exists
			try {
				LoginValue loginValue = authAccess.getLogin(service,login);
				if (loginValue == null) {
					err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.notfound"));
					//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.notfound", "error.login.notfound"));
					return null;
				}
				userId = loginValue.getUserId();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return userId;
	}
	 

	private String getUserCompany(String userId) {
		UserData ud = userAccess.getUser(userId);
		return ud.getCompanyOwnerId();
	}
	
	private List getAllServices() {

		SecurityDomainAccess secDomainAccess = null;
		
		
    	ArrayList<LabelValueBean> newServiceList = new ArrayList<LabelValueBean>();
    	//List serviceList = servMgr.getAllServices();
    	List<Service> serviceList = serviceAccess.getAllServices();
      
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
	

	
}