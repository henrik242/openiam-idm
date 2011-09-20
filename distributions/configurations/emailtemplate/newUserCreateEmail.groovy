
def String identity = req.getNotificationParam("IDENTITY").valueObj
def String password = req.getNotificationParam("PSWD").valueObj



emailStr = "Dear " + user.firstName + " " + user.lastName + ": \n\n" +
 	"A new user account has been created for you.. \n\n" + 
 	"\n\n" +
 	"Your login Id is: " + identity + " \n" +
 	"Your password is: " + password
 	
output=emailStr