
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.groovy.helper.ServiceHelper;


expParam = req.getNotificationParam("daysToExpiration").valueObj
def String expUserId = req.getNotificationParam("expiringUser").valueObj


def UserDataWebService userService = ServiceHelper.userService()
def User expiringUser = userService.getUserWithDependent (expUserId, true).user


emailStr = "Dear " + user.firstName + " " + user.lastName + ": \n\n" +
 	"Your sponsored account for: " + expiringUser.firstName + " " + expiringUser.lastName +  
 	" account will expire in " + expParam.value  +  " days. \n\n\n" + 
 	"To maintain this account as an active user, please log  \n" + 
 	"into the selfservice application and update this user.\n" 
 	
output=emailStr