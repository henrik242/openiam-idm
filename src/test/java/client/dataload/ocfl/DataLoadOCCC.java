package client.dataload.ocfl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Class used to load data. Interim solution while the ETL solution is properly integrated.
 * @author suneet
 *
 */
public class DataLoadOCCC {

		static String driver_classname= "com.mysql.jdbc.Driver";
		static String driver_url= "jdbc:mysql://olpsdb01:3306/openiam";
		static String username= "openiam";
		static String password= "ss0126";

		static ApplicationContext ctx = null;

		static UserDataService userMgr;
		User user;
		Login login;
		static LoginDataService loginService;
	
		
	public static void init() {
		ctx = new ClassPathXmlApplicationContext(
				new String[] {"/applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		loginService = (LoginDataService)ctx.getBean("loginManager");		
	}
	
	public static void load() {
		Connection con = null;

 		try {
		  
		  Class driverObject = Class.forName(driver_classname);
		  con = DriverManager.getConnection(driver_url, username, password);
		
		  String sql = "SELECT FirstName, Initials, LastName, PreW2KName, Department, Division, employeeid, email, role " +
		  		"	from OCCC where employeeId IS NOT NULL";

		  
		  PreparedStatement stmt = con.prepareStatement(sql);
		  
		  ResultSet rs = stmt.executeQuery();
		  while (rs.next()) {

			  String firstName = rs.getString(1);
			  String initials = rs.getString(2);
			  String lastName = rs.getString(3);
			  String login = rs.getString(4);
			  String department = rs.getString(5);
			  String division = rs.getString(6);
			  String employeeId = rs.getString(7);
			  String email = rs.getString(8);
			  String status = rs.getString(9);
			  
			  System.out.println("employeeId = " + employeeId);
			  
			  User existUser = userMgr.getUserWithDependent(employeeId,false);
			  
			  // if the user exists
			  if (existUser != null) {
				  if (login != null) {
					  // add a login object
					  User usr = new User();
					  usr.setUserId(employeeId);
					  
					  Login lg = new Login();
					  lg.setUser(usr);
					  lg.setId(new LoginId("OCCC", login, "AD"));
					  lg.setPassword("12345");
					  lg.setIsLocked(0);
					  lg.setAuthFailCount(0);
					  lg.setIsDefault(new Integer(0));
					  lg.setResetPwd(0);
					  lg.setStatus(status);
				  
					  loginService.addLogin(lg);
				  }				  
			  }else {
			  
			  // create the user object
			  
			  //if (existUser == null) {
			  
			  User usr = new User();
			  usr.setFirstName(firstName);
			  if (initials != null) {
				  usr.setMiddleInit(String.valueOf (initials.charAt(0)));
			  }
			  usr.setLastName(lastName);
			  usr.setEmployeeId(employeeId);
			  usr.setDeptCd(department);
			  if (employeeId != null) {
				  usr.setUserId(employeeId);
			  }else {
				  usr.setUserId(String.valueOf(System.currentTimeMillis()));
			  }
			  
			  
			  userMgr.addUserWithDependent(usr,false);
			  
			  // add email
			  EmailAddress eml = new EmailAddress();
			  //eml.setEmailId(String.valueOf(System.currentTimeMillis()));
			  eml.setEmailAddress(email);
			  eml.setDescription("PRIMARY");
			  eml.setIsDefault(new Integer(1));
			  eml.setParentId(usr.getUserId());
			  eml.setParentType("USER");
			  Set<EmailAddress> emailMap = usr.getEmailAddress();				
			  emailMap.add( eml);

		  
			  		  		  
			  userMgr.updateUserWithDependent(usr,true);
			  
			  if (login != null) {
				  // add a login object
				  Login lg = new Login();
				  lg.setUser(usr);
				  lg.setId(new LoginId("OCCC", login, "AD"));
				  lg.setPassword("12345");
				  lg.setIsLocked(0);
				  lg.setAuthFailCount(0);
				  lg.setIsDefault(new Integer(0));
				  lg.setResetPwd(0);
				  lg.setStatus(status);
				  loginService.addLogin(lg);
			  }
			  }
			  
	  
		  }
 		}catch(Exception e) {
 			e.printStackTrace();
 		}finally {
 			try {
 			con.close();
 			}catch(Exception e) {
 				e.printStackTrace();
 			}
 		}
		
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting dataload..for OC FL");
		init();
		load();

	}

}
