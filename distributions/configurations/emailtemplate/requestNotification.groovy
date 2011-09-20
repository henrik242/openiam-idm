
def String requestId = req.getNotificationParam("REQUEST_ID").valueObj
def String requestReason = req.getNotificationParam("REQUEST_REASON").valueObj
def String requestor = req.getNotificationParam("REQUESTOR").valueObj
def String targetUser = req.getNotificationParam("TARGET_USER").valueObj





emailStr = "Dear " + user.firstName + " " + user.lastName + ": \n\n" +
 	"A new request has been created and requires your review. Please login to the OpenIAM Selfservice application to review the request. \n\n" + 
 	"\n\n" +
 	"Request Type: " + requestReason + " \n" +
 	"Requestor: " + requestor + " \n" +
 	"For: " + targetUser
 	 	
 	
output=emailStr