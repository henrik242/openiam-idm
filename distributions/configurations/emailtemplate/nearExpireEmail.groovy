
param = req.getNotificationParam("daysToExpiration")

emailStr = "Dear " + user.firstName + " " + user.lastName + ": \n\n" +
 	"Your password will expire in " + param.value  +  " days. \n\n\n" + 
 	"Please log into the selfservice application and change your password.\n" +
 	"http://localhost:8080/selfservice"
 	
output=emailStr