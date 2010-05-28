loginID=lg.id.login

if (matchParam != null) {
	
	loginID = matchParam.keyField + "=" + loginID + "," + matchParam.baseDn
}

System.out.println("uid=" + loginID)

output = loginID