// Unlocks the user account automatically based on the password policy


import org.openiam.idm.srvc.user.dto.UserStatusEnum
import org.openiam.idm.groovy.helper.ServiceHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;


println("autoUnlock.groovy is being executed.");

def loginManager = ServiceHelper.loginService()
loginManager.bulkUnLock(UserStatusEnum.LOCKED)


output=1