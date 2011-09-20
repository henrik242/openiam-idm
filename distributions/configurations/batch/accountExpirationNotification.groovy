// accountExpirationNotification - sends a notification to an
// account owner or sponsor that he account is expiring


println("accountExpirationNotificaiton");

import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.srvc.msg.dto.NotificationParam

import java.util.Date
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.groovy.helper.ServiceHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.base.id.UUIDGen;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.dto.User;
import groovy.sql.*
import java.util.ResourceBundle;

	def MailService mailSrv = ServiceHelper.emailService();
	def UserDataWebService userService = ServiceHelper.userService()

	String DAYS_TO_EXPIRATION = 7

	ResourceBundle res = ResourceBundle.getBundle("datasource");



def db=res.getString("openiam.driver_url")  				//'jdbc:mysql://localhost:3306/gilt'
def u=res.getString("openiam.username")   					//'demouser'
def password=res.getString("openiam.password")			//'demouser'
def driver= res.getString("openiam.driver_classname")  //'com.mysql.jdbc.Driver'

String str = "select USER_ID, USER_OWNER_ID " + 
			" FROM USERS  " + 
			" WHERE USER_OWNER_ID IS NOT NULL AND LAST_DATE = DATE_ADD(CURDATE(), INTERVAL  " + DAYS_TO_EXPIRATION + " DAY)"

def sql = Sql.newInstance(db,u, password, driver)
sql.eachRow(str) { user ->

		String userId = user.USER_ID
		String ownerId = user.USER_OWNER_ID
		
		println("owner id = >" + userId)
		
		// get the user that is expiring
	 	User expiringUser = userService.getUserWithDependent (userId, true).user
		 
	
		
		NotificationRequest req = new NotificationRequest()
		req.notificationType = "ACCOUNT_NEAR_EXPIRATION"
		req.userId = ownerId
		req.linkedRequestId = parentRequestId
		req.requestId =  UUIDGen.getUUID()
		
		// set parameters
		NotificationParam param = new NotificationParam("expiringUser", userId);
		req.paramList.add( param )
		NotificationParam param2 = new NotificationParam("daysToExpiration", DAYS_TO_EXPIRATION);
		req.paramList.add( param2 )
			
		mailSrv.sendNotification(req)
	
}





output=1

