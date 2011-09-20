
def String identity = req.getNotificationParam("IDENTITY").valueObj
def String password = req.getNotificationParam("PSWD").valueObj

def String name = req.getNotificationParam("NAME").valueObj


emailStr = "Dear " + user.firstName + " " + user.lastName + ": \n\n" +
 	"A new user account has been created for " + name +  
 	"\n\n" +
 	"The login Id is: " + identity + " \n" +
 	"The password is: " + password
 	
output=emailStr