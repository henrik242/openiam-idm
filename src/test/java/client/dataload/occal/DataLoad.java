package client.dataload.occal;

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
public class DataLoad {

		static String driver_classname= "com.mysql.jdbc.Driver";
		static String driver_url= "jdbc:mysql://10.100.0.25:3306/occal";
		static String username= "ocuser";
		static String password= "ocuser";

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
		
		  String sql = "SELECT ADSSOPassword, CAPSYN, CAPSUID, CAPSPSWD, DisplayName, FirstName, MiddleInitials," +
		  		" LastName, EmployeeID, EmployeeType,PrimaryEmail, Description, " +
		  		" WebPage,Manager, Department, TelephoneNumber " +
		  		"	from CAPS_USERS4 ";

		  
		  PreparedStatement stmt = con.prepareStatement(sql);
		  
		  ResultSet rs = stmt.executeQuery();
		  while (rs.next()) {

			  String ADSSOPassword = rs.getString(1);
			  String CAPSYN = rs.getString(2);
			  String CAPSUID = rs.getString(3);
			  String CASPPSWD = rs.getString(4);
			  String DisplayName = rs.getString(5);
			  String FirstName = rs.getString(6);
			  String MiddleInitials = rs.getString(7);
			  String LastName = rs.getString(8);
			  String EmployeeID = rs.getString(9);
			  String EmployeeType = rs.getString(10);
			  String PrimaryEmail = rs.getString(11);
			  String Description = rs.getString(12);
			  String WebPage = rs.getString(13);
			  String Manager = rs.getString(14);
			  String Department = rs.getString(15);
			  String TelephoneNumber = rs.getString(16);
			  
			  System.out.println("SUID=" + CAPSUID);
			  // create the user object
			  
			  User usr = new User();
			  usr.setFirstName(FirstName);
			  if (MiddleInitials != null) {
				  usr.setMiddleInit(String.valueOf (MiddleInitials.charAt(0)));
			  }
			  usr.setLastName(LastName);
			  usr.setEmployeeId(EmployeeID);
			  usr.setDeptCd(Department);
			  userMgr.addUserWithDependent(usr,false);
			  
			  // add a few attributes
			  UserAttribute userAttribute = new UserAttribute();
			  UserAttribute userAttribute2 = new UserAttribute();
			  UserAttribute userAttribute3 = new UserAttribute();
			  UserAttribute userAttribute4 = new UserAttribute();
			  UserAttribute userAttribute5 = new UserAttribute();

			  userAttribute.setMetadataElementId("101");
			  userAttribute.setName("APPS_NAME");
			  userAttribute.setValue("CAPS");
			  userAttribute.setUserId(usr.getUserId());

			  userAttribute2.setMetadataElementId("104");
			  userAttribute2.setName("APP_URL");
			  userAttribute2.setValue("");
			  userAttribute2.setUserId(usr.getUserId());
			  
			  userAttribute3.setMetadataElementId("105");
			  userAttribute3.setName("APPS_UID");
			  userAttribute3.setValue(CAPSUID);
			  userAttribute3.setUserId(usr.getUserId());
			  
			  userAttribute4.setMetadataElementId("106");
			  userAttribute4.setName("APPS_PASSWORD");
			  userAttribute4.setValue(CASPPSWD);
			  userAttribute4.setUserId(usr.getUserId());
			  
			  if (CAPSYN != null && (CAPSYN.equalsIgnoreCase("1") || CAPSYN.equalsIgnoreCase("Yes"))) {
				  userAttribute5.setMetadataElementId("107");
				  userAttribute5.setName("CAPS_ENABLED");
				  userAttribute5.setValue("1");
			  }else {
				  userAttribute5.setMetadataElementId("107");
				  userAttribute5.setName("CAPS_ENABLED");
				  userAttribute5.setValue("0");
				  
			  }
			  userAttribute5.setUserId(usr.getUserId());
			  
			  Map<String, UserAttribute> attrMap =  usr.getUserAttributes();
			  attrMap.put(userAttribute.getName(),userAttribute);
			  attrMap.put(userAttribute2.getName(),userAttribute2);
			  attrMap.put(userAttribute3.getName(),userAttribute3);
			  attrMap.put(userAttribute4.getName(),userAttribute4);
			  attrMap.put(userAttribute5.getName(),userAttribute5);
			  
			  userMgr.updateUserWithDependent(usr,true);
			  //userMgr.addUserWithDependent(usr,true);
			  
			  // add email
			  EmailAddress eml = new EmailAddress();
			//  eml.setEmailId(String.valueOf(System.currentTimeMillis()));
			  eml.setEmailAddress(PrimaryEmail);
			  eml.setDescription("PRIMARY");
			  eml.setIsDefault(new Integer(1));
			  eml.setParentId(usr.getUserId());
			  eml.setParentType("USER");
			  Set<EmailAddress> emailMap = usr.getEmailAddress();				
			  emailMap.add( eml);

			  
			  // add Phone
			  Phone phone = new Phone();
			  //phone.setPhoneId(String.valueOf(System.currentTimeMillis()));
			  phone.setPhoneNbr(TelephoneNumber);
			  phone.setIsDefault(new Integer(1));
			  phone.setDescription("PRIMARY");
			  phone.setParentType("USER");
			  phone.setParentId(usr.getUserId());
			  Set<Phone> phoneMap = usr.getPhone();
			  phoneMap.add(phone);
			  
			  		  		  
			  userMgr.updateUserWithDependent(usr,true);
			  
			  if (PrimaryEmail != null) {
				  // add a login object
				  Login login = new Login();
				  login.setUser(usr);
				  login.setId(new LoginId("USR_SEC_DOMAIN", PrimaryEmail, "0"));
				  login.setPassword(ADSSOPassword);
				  login.setIsLocked(0);
				  login.setAuthFailCount(0);
				  login.setIsDefault(new Integer(0));
				  login.setResetPwd(0);
			  
			  loginService.addLogin(login);
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
		System.out.println("Starting dataload..for OC Cal");
		init();
		load();

	}

}
