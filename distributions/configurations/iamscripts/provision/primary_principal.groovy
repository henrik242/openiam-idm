import org.springframework.context.support.ClassPathXmlApplicationContext

def loginManager = context.getBean("loginManager")

loginID=user.firstName + "." + user.lastName


ctr = 1;
origLoginID = loginID


while ( loginManager.loginExists( "USR_SEC_DOMAIN", loginID, sysId )) {
  strCtrSize = String.valueOf(ctr)
	loginID=origLoginID + ctr
	ctr++
}

output=loginID


