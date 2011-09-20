

import java.util.Map;

import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.service.ValidationScript;


public class ValidateSrcRecord implements ValidationScript {
	
	public int isValid(LineObject rowObj) {
		println("1-Validation script called.");
		
		Attribute attrVal = null;
		Map<String,Attribute> columnMap =  rowObj.getColumnMap();
		
		
		//attrVal = columnMap.get("LAST_NAME");
		//if ( isNull(attrVal)) {
		// 	println("last_name check failed.")
		//	return ValidationScript.NOT_VALID;
		//}
		
		
		return ValidationScript.VALID;
		
	}
	private boolean isNull(Attribute attrVal) {
		if (attrVal == null || attrVal.getValue() == null  || attrVal.getValue().length() == 0  ) {
			return true;
		}
		return false;
	}
}
