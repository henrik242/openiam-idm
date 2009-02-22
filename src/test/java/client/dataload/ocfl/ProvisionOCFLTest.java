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
			  
			  if (iamUser == null) {
				  // new User 
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
					  log.setReason(firstName + " " + lastName + " PS EID=" + employeeId + "DEPT ID=" + deptid + locationBasedDomain);
					  log.setObjectTypeId("USER");
					  log.setLogHash("Test");
					  log.setLoginId("3001");
					  this.auditDataService.addLog(log);
				  }
			  }else {
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
				  if  ( lastName != null && !lastName.equals(iamUser.getLastName()) ) {
					// Update record - In AD you need to update first name, last name, middle init, directory name
					  System.out.println("User Name Data CHANGED - " + employeeId + " " + lastName + " " + firstName);
					  IdmAuditLog log = new IdmAuditLog();
					  log.setActionDatetime(new Date(System.currentTimeMillis()));
					  log.setActionId("NAME CHANGE");
					  log.setUserId(employeeId);
					  log.setReason(firstName + " " + lastName + " PS EID=" + employeeId + "DEPT ID=" + deptid + affectedDomain);
					  log.setActionStatus(status);
					  log.setObjectTypeId("USER");
					  log.setLogHash("Test");
					  log.setLoginId("3001");
					  this.auditDataService.addLog(log);
					  
				  }else {
					  if  ( firstName != null && !firstName.equals(iamUser.getFirstName()) ) {
							// Update record
							  System.out.println("User Data CHANGED - " + employeeId + " " + lastName + " " + firstName);
							  IdmAuditLog log = new IdmAuditLog();
							  log.setActionDatetime(new Date(System.currentTimeMillis()));
							  log.setActionId("NAME CHANGE");
							  log.setUserId(employeeId);
							  log.setReason(firstName + " " + lastName + " PS EID=" + employeeId + "DEPT ID=" + deptid + affectedDomain);
							  log.setActionStatus(status);
							  log.setObjectTypeId("USER");
							  log.setLogHash("Test");
							  log.setLoginId("3001");
							  this.auditDataService.addLog(log);
					  }else {
						  if  ( middleName != null && !middleName.equals(iamUser.getMiddleInit()) ) {
								// Update record
								  System.out.println("User Data CHANGED - " + employeeId + " " + lastName + " " + firstName );
								  IdmAuditLog log = new IdmAuditLog();
								  log.setActionDatetime(new Date(System.currentTimeMillis()));
								  log.setActionId("NAME CHANGE");
								  log.setUserId(employeeId);
								  log.setReason(firstName + " " + lastName + " PS EID=" + employeeId + "DEPT ID=" + deptid + affectedDomain );
								  log.setActionStatus(status);
								  log.setObjectTypeId("USER");
								  log.setLogHash("Test");
								  log.setLoginId("3001");
								  this.auditDataService.addLog(log);
						  }
					  }
					  
					  /*  else {
						  if  ( deptid != null && !deptid.equals(iamUser.getDept()) ) {
							// Update record
							  System.out.println("User Location CHANGED - " + employeeId + " " + lastName + " " + firstName + " dept=" + deptid);
						  } 
					  }
					 */
				  }

				  // logic to enable and disable account
				  if (location.startsWith("UT")) {
				  //if (UDlogin != null) {
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
					  
					  if (OCGOVlogin != null) {
			
						  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
								  (OCGOVlogin != null && OCGOVlogin.getStatus() != null && OCGOVlogin.getStatus().equalsIgnoreCase("DISABLED") )	)   {
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
							  (OCGOVlogin != null && OCGOVlogin.getStatus() == null  ) )	   {
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
		
		  String sql = "select service_id, login, user_id, status " + 
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








