

import java.util.Map;

import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.service.ValidationScript;


public class ValidateLDAPRecord implements ValidationScript {
	
	public int isValid(LineObject rowObj) {
		println("1-Validation script called.");
		
		
		return ValidationScript.VALID;
		
	}
	
}
