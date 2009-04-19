package client.dataload.ocfl;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.util.db.*;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.util.db.Search;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * UPDATE LOGIN l, OCGOV g
	SET l.CANONICAL_NAME = g.CanonicalName
	where l.USER_ID = g.employeeId and l.SERVICE_ID = 'USR_SEC_DOMAIN';
	
	
	UPDATE LOGIN l, OCCC C
	SET l.CANONICAL_NAME = C.CanonicaName
	where l.USER_ID = C.employeeId and l.SERVICE_ID = 'OCCC';


	UPDATE LOGIN l, OCUD D
	SET l.CANONICAL_NAME = D.CanonicalName
	where l.USER_ID = D.employeeId and l.SERVICE_ID = 'OCUD';
	
 * @author suneet
 *
 */

public class ProvisionOCFLTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user;
	IdmAuditLogDataService auditDataService;
	IdmAuditLog log;

	static String userId;

	static protected ResourceBundle res = ResourceBundle.getBundle("datasource");

	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBean");
		auditDataService = (IdmAuditLogDataService)ctx.getBean("auditDataService");

		
	} 

	
	public void testAutoProvision() {
		  String className = res.getString("orcl.driver_classname");  
		  String driver = res.getString("orcl.driver_url");
		  String uname = res.getString("orcl.username");
		  String pword= res.getString("orcl.password");

 		Connection con = null;

 		try {
		  
		  Class driverObject = Class.forName(className);
		  con = DriverManager.getConnection(driver, uname, pword);
		
		  String sql = "select EMPLID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, LOCATION, DEPTID, EMPL_STATUS, LAST_4DIGITS_SSN, LOCATION_DESCR " +  
					   "	from PS_OC_SECURITY_VW ";

		  
		  PreparedStatement stmt = con.prepareStatement(sql);
		  
		  ResultSet rs = stmt.executeQuery();
		  String roleId = null;
		  while (rs.next()) {
			//  System.out.println("getting USER for: " + rs.getString(1) + " " + rs.getString(2) + "  " + rs.getString(3));
			  //return true;
			  //authenticated = true;
			  String employeeId = rs.getString(1);
			  String lastName = rs.getString(2);
			  String firstName = rs.getString(3);
			  String middleName = rs.getString(4);
			  String location = rs.getString(5);
			  String deptid = rs.getString(6);
			  String status = rs.getString(7);
			  String ssn = rs.getString(8);
			  String locDesc = rs.getString(9);
		
			  
			  System.out.println("EmployeeId=" + employeeId);
			  
			  String affectedDomain = null;
			  String locationBasedDomain = null;
			  
			  if (location !=null ) {
				  
				  if (location.startsWith("CC")) {
					  locationBasedDomain = " DOM=CC";
				  }else if (location.startsWith("UT")) {
					  locationBasedDomain = " DOM=UD";
				  }else {
					  locationBasedDomain = " DOM=OCGOV";
				  }
				  
				  
			  }
			  
			  
			  User iamUser = getOpenIAMRecord(rs.getString(1));
			  Login UDlogin = this.getLoginRec("OCUD", employeeId);
			  Login UClogin = this.getLoginRec("OCCC", employeeId);
			  Login OCGOVlogin = this.getLoginRec("USR_SEC_DOMAIN", employeeId);
			  
			  String ocgovCanName=null;
			  String ucCanName = null;
			  String udLogin = null;
			  
			  if  (OCGOVlogin != null &&  OCGOVlogin.getCanonicalName() != null) {
				  ocgovCanName =  OCGOVlogin.getCanonicalName();
			  }
				
//			  if (UClogin != null &&  UClogin.getCanonicalName() != null) {
//				 ucCanName =  UClogin.getCanonicalName(); 
//			  }
//			  if (UDlogin != null &&  UDlogin.getCanonicalName() != null) {
//				  udLogin =  UDlogin.getCanonicalName();
//			  }
			  
			  //System.out.println("ocgovCanName: " + OCGOVlogin.getUser().getUserId() + " - " + OCGOVlogin.getCanonicalName());

					  
					  
			// if ( ( ocgovCanName != null && ocgovCanName.contains("Disabled")) || 
			//		    (ucCanName != null &&  ucCanName.contains("Disabled")) ||
			//		    (udLogin != null &&  udLogin.contains("Disabled")) ) {
			  if ( ocgovCanName != null && ocgovCanName.contains("Disabled") ) {
			  
				  System.out.println("Mail box holder: " + iamUser.getUserId() );
				  
			  } else {
			  
				  if (iamUser == null) {
					  // new User 
					  System.out.println("New user: emplid=" + employeeId + " status=" + status  );
					  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") )) {
						  // Only add active account
						  //effective domain
						  
						  if (UDlogin != null) {
							  affectedDomain = " DOM=" + "Utilities";
						  }
						  if (UClogin != null) {
							  affectedDomain = " DOM=" + "CC";
						  }
						  if (OCGOVlogin != null) {
							  affectedDomain = affectedDomain + " DOM=" + "OCGOV";
						  }
						  
						  System.out.println("User is to be ADDED - " + rs.getString(1) + " " + rs.getString(2) + "  " + rs.getString(3) );
						  IdmAuditLog log = new IdmAuditLog();
						  log.setActionDatetime(new Date(System.currentTimeMillis()));
						  log.setActionId("ADD USER");
						  log.setActionStatus(status);
						  log.setUserId(employeeId);
						  log.setReason(firstName + " " + lastName + " PS EID=" + employeeId + "DEPT ID=" + deptid + locationBasedDomain + " ADD Domain Group: HRMS PS" );
						  log.setObjectTypeId("USER");
						  log.setLogHash("Test");
						  log.setLoginId("3001");
						  this.auditDataService.addLog(log);
						  /*
						   * c.	Initial password is birth year + last four digits of the social security number
								d.	EmployeeType field = E
								e.	Business Category = BC
								f.	Company Field : Orange County BCC
								g.	Set the location code, department and division. Department and Division Fields will be based on the Location Code from PeopleSoft.
								h.	Based on the location code, determine which AD to provision the shell account –ie. OCGOV, Convention Center, Utilities
								i.	Description Field: PeopleSoft Shell Account
								j.	Email:
								i.	If Department is Convention Center – email OCCC-NewPeopleSoftAccount@ocfl.net
								ii.	If Department is Utilities – email: OCUD-NewPeopleSoftAccount@ocfl.net.
								iii.	Always email : CA-ISS-Security@ocfl.net in addition to any other email accounts
						   * 
						   */
					  }
				  }else {
				
					  System.out.println("Existing user: emplid=" + employeeId + " status=" + status + " location=" + location );
					  // record exists
					  if (UDlogin != null) {
						  affectedDomain = " DOM=" + "Utilities";
					  }
					  if (UClogin != null) {
						  affectedDomain = " DOM=" + "CC";
					  }
					  if (OCGOVlogin != null) {
						  affectedDomain = affectedDomain + " DOM=" + "OCGOV";
					  }				  
					  // - check for changes to the record
					  
	
					  // logic to enable and disable account
					  if (location.startsWith("UT")) {
						  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
								  (UDlogin != null && UDlogin.getStatus() != null && UDlogin.getStatus().equalsIgnoreCase("DISABLED") )	)   {
								  System.out.println("User STATUS CHANGED - ENABLE ACCOUNT " + employeeId + " " + lastName + " " + firstName + " peoplesoft status=" + status + " iamstatus=" + iamUser.getStatus());
								  IdmAuditLog log = new IdmAuditLog();
								  log.setActionDatetime(new Date(System.currentTimeMillis()));
								  log.setActionId("STATUS CHNG-ENABLE");
								  log.setUserId(employeeId);
								  log.setReason(firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL" );
								  log.setObjectTypeId("USER");
								  log.setActionStatus(status);
								  log.setLogHash("Test");
								  log.setLoginId("3001");
								  this.auditDataService.addLog(log);						  
						  }				  
						  
						  if (status != null && (status.equals("R") || status.equals("T") || status.equals("D")) &&   
							  (UDlogin != null && UDlogin.getStatus() == null  ) )	   {
							  System.out.println("User STATUS CHANGED - DISABLE ACCOUNT employeeId=" + employeeId + " " + lastName + " " + firstName + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             peoplesoft status=" + status + " iamstatus=" + iamUser.getStatus() );
							  IdmAuditLog log = new IdmAuditLog();
							  log.setActionDatetime(new Date(System.currentTimeMillis()));
							  log.setActionId("STATUS CHNG-DISABLE");
							  log.setUserId(employeeId);
							  log.setObjectTypeId("USER");
							  log.setReason(firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL" );
							  log.setLogHash("Test");
							  log.setActionStatus(status);
							  log.setLoginId("3001");
							  this.auditDataService.addLog(log);
						  }
					  }else if (location.startsWith("CC")) {
						  //}else  if (UClogin != null) {
						  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
								  (UClogin != null && UClogin.getStatus() != null && UClogin.getStatus().equalsIgnoreCase("DISABLED") )	)   {
								  System.out.println("User STATUS CHANGED - ENABLE ACCOUNT " + employeeId + " " + lastName + " " + firstName + " peoplesoft status=" + status + " iamstatus=" + iamUser.getStatus());
								  IdmAuditLog log = new IdmAuditLog();
								  log.setActionDatetime(new Date(System.currentTimeMillis()));
								  log.setActionId("STATUS CHNG-ENABLE");
								  log.setUserId(employeeId);
								  log.setReason(firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC CC" );
								  log.setObjectTypeId("USER");
								  log.setActionStatus(status);
								  log.setLogHash("Test");
								  log.setLoginId("3001");
								  this.auditDataService.addLog(log);						  
						  }				  
						  
						  if (status != null && (status.equals("R") || status.equals("T") || status.equals("D")) &&   
							  (UClogin != null && UClogin.getStatus() == null  ) )	   {
							  System.out.println("User STATUS CHANGED - DISABLE ACCOUNT employeeId=" + employeeId + " " + lastName + " " + firstName + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             peoplesoft status=" + status + " iamstatus=" + iamUser.getStatus() );
							  IdmAuditLog log = new IdmAuditLog();
							  log.setActionDatetime(new Date(System.currentTimeMillis()));
							  log.setActionId("STATUS CHNG-DISABLE");
							  log.setUserId(employeeId);
							  log.setObjectTypeId("USER");
							  log.setReason(firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC CC" );
							  log.setLogHash("Test");
							  log.setActionStatus(status);
							  log.setLoginId("3001");
							  this.auditDataService.addLog(log);
						  }
						  
					  }else {
	
						  // only in oc-gov
						  System.out.println("Existing user - OCGov Domain status = " + OCGOVlogin.getStatus());
						  
						  if (OCGOVlogin != null) {
				
							  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
									  (OCGOVlogin != null && OCGOVlogin.getStatus() != null && OCGOVlogin.getStatus().startsWith("DISABLE") )	)   {
									  System.out.println("User STATUS CHANGED - ENABLE ACCOUNT " + employeeId + " " + lastName + " " + firstName + " peoplesoft status=" + status + " iamstatus=" + iamUser.getStatus());
									  IdmAuditLog log = new IdmAuditLog();
									  log.setActionDatetime(new Date(System.currentTimeMillis()));
									  log.setActionId("STATUS CHNG-ENABLE");
									  log.setUserId(employeeId);
									  log.setReason(firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OCGOV" );
									  log.setObjectTypeId("USER");
									  log.setActionStatus(status);
									  log.setLogHash("Test");
									  log.setLoginId("3001");
									  this.auditDataService.addLog(log);						  
							  }				  
							  
							  if (status != null && (status.equals("R") || status.equals("T") || status.equals("D")) &&   
								  (OCGOVlogin != null && 
										  (OCGOVlogin.getStatus() == null || OCGOVlogin.getStatus().startsWith("NO_PWDEXP"))  ))	   {
								  System.out.println("User STATUS CHANGED - DISABLE ACCOUNT employeeId=" + employeeId + " " + lastName + " " + firstName + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             peoplesoft status=" + status + " iamstatus=" + iamUser.getStatus() );
								  IdmAuditLog log = new IdmAuditLog();
								  log.setActionDatetime(new Date(System.currentTimeMillis()));
								  log.setActionId("STATUS CHNG-DISABLE");
								  log.setUserId(employeeId);
								  log.setObjectTypeId("USER");
								  log.setReason(firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OCGOV" );
								  log.setLogHash("Test");
								  log.setActionStatus(status);
								  log.setLoginId("3001");
								  this.auditDataService.addLog(log);
							  }
						}
					 }
				  }
			  }
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

		
		
	
	}	
	
	public User getOpenIAMRecord(String employeeId) {
		User usr = null;
		
		  String className = res.getString("openiam.driver_classname");  
		  String driver = res.getString("openiam.driver_url");
		  String uname = res.getString("openiam.username");
		  String pword= res.getString("openiam.password");

 		Connection con = null;

 
 		try {
		  
		  Class driverObject = Class.forName(className);
		  con = DriverManager.getConnection(driver, uname, pword);
		
		  String sql = "select user_id, last_name, first_name, dept, divsion, employee_id, STATUS " + 
		  			" from users where employee_id = ? "; 

		  PreparedStatement stmt = con.prepareStatement(sql);
		  stmt.setString(1, employeeId);
		  
		  ResultSet rs = stmt.executeQuery();
	  
		  
		  if (rs.next()) {
			  
			  usr = new User();
			  usr.setUserId(rs.getString(1));
			  usr.setLastName(rs.getString(2));
			  usr.setFirstName(rs.getString(3));
			  usr.setDeptCd(rs.getString(4));
			  usr.setDivision(rs.getString(5));
			  usr.setEmployeeId(rs.getString(6));
			  usr.setStatus(rs.getString(7));
			  
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
		return usr;
		
	}
	
	public Login getLoginRec(String serviceId, String userId) {
		Login login = null;
		
		  String className = res.getString("openiam.driver_classname");  
		  String driver = res.getString("openiam.driver_url");
		  String uname = res.getString("openiam.username");
		  String pword= res.getString("openiam.password");

 		Connection con = null;

 
 		try {
		  
		  Class driverObject = Class.forName(className);
		  con = DriverManager.getConnection(driver, uname, pword);
		
		  String sql = "select service_id, login, user_id, status, CANONICAL_NAME " + 
		  			" from LOGIN where service_Id = ? and  user_Id = ? "; 

		  PreparedStatement stmt = con.prepareStatement(sql);
		  stmt.setString(1, serviceId);
		  stmt.setString(2, userId);
		  
		  
		  ResultSet rs = stmt.executeQuery();
	  
		  
		  if (rs.next()) {
			  
			  login = new Login();
			  login.setId(new LoginId(rs.getString(1), rs.getString(2),"AD"));
			  User usr = new User();
			  usr.setUserId(rs.getString(4));
			  login.setUser(usr);
			  login.setStatus(rs.getString(4));
			  login.setCanonicalName(rs.getString(5));

			  
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
		return login;
		
	}


	
		
}








