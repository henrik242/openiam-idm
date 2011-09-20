
/*
Objects that are passed to the script:

sysId - DefaultManagedSysId
user - user object
org - Organization object		
context - Spring application context. Allows you to look up any spring bean
*/


//loginId = user.firstName + '.' + user.lastName

loginId = user.userAttributes.get("userid").value;

if (matchParam != null) {
	
	loginId = matchParam.keyField + "=" + loginId + "," + matchParam.baseDn
}



println("----uid.groovy=" + loginId)

output = loginId