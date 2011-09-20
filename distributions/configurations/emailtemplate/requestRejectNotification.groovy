
def String requestId = req.getNotificationParam("REQUEST_ID").valueObj
def String requestReason = req.getNotificationParam("REQUEST_REASON").valueObj
def String requestor = req.getNotificationParam("REQUESTOR").valueObj
def String targetUser = req.getNotificationParam("TARGET_USER").valueObj





emailStr = "Dear " + user.firstName + " " + user.lastName + ": \n\n" +
 	"This is to notify you that the request summarized below was rejected. \n\n" + 
 	"\n\n" +
 	"Request ID: " + requestId + " \n" +
 	"Request Type: " + requestReason + " \n" +
 	"Approver: " + requestor + " \n" +
 	"For: " + targetUser
 	 	
 	
output=emailStr