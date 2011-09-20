
import groovy.sql.*
import org.openiam.idm.srvc.user.dto.UserStatusEnum


print("InactiveUserPUV.groovy");

def db='jdbc:mysql://localhost:3306/gilt'
def u='demouser'
def password='demouser'
def driver='com.mysql.jdbc.Driver'

def sql = Sql.newInstance(db,u, password, driver)

def userManager = context.getBean("userWS")

sql.eachRow("select USER_ID from USERS " + 
	" where STATUS in ('PENDING_INITIAL_LOGIN', 'PENDING_USER_VALIDATION') AND " +
	" 		CREATE_DATE BETWEEN DATE_SUB(CURDATE(), INTERVAL 180 DAY) AND DATE_SUB(CURDATE(), INTERVAL 90 DAY)"	) { id ->
	
	String userId = id.USER_ID	
	println("userId = >" + userId)
	
	usr = userManager.getUserWithDependent(userId, true).user
	if (usr.status != UserStatusEnum.INACTIVE) {
		usr.status = UserStatusEnum.INACTIVE
		userManager.updateUserWithDependent(usr,true)
	    println("- Users set to inactive");
}
	
	
}
	
sql.eachRow("select USER_ID from USERS " +
			" where STATUS in ('PENDING_INITIAL_LOGIN', 'PENDING_USER_VALIDATION') AND " +
			" 		CREATE_DATE <= DATE_SUB(CURDATE(), INTERVAL 180 DAY)"	) { id ->
	
	String userId = id.USER_ID	
	println("userId for 188 users = >" + userId)
	
	usr = userManager.getUserWithDependent(userId, true).user
	if (usr.status != UserStatusEnum.TERMINATE) {
		usr.status = UserStatusEnum.TERMINATE
		userManager.updateUserWithDependent(usr,true)
	    println("- Users set to TERMINATE");
	}
	
}
			
output=1
		


  
  

		

