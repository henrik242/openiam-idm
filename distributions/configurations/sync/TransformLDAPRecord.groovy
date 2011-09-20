
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.service.AbstractTransformScript;
import org.openiam.idm.srvc.synch.service.TransformScript;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.idm.srvc.user.dto.UserStatusEnum
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;


public class TransformLDAPRecord extends AbstractTransformScript {
	
	public int execute(LineObject rowObj, ProvisionUser pUser) {
		
		
		System.out.println("** 2-Transform object called. **");
		
	
		println("Is New User:" + isNewUser)
		println("User Object:" + user)
		println("PrincipalList: " + principalList)
		println("User Roles:" + userRoleList)
		
		populateObject(rowObj, pUser);
		
		if (isNewUser) {
			pUser.setUserId(null);
		}
		pUser.setStatus(UserStatusEnum.ACTIVE);
		pUser.securityDomain = "0"
		
	
		
		// Set default role
		List<Role> roleList = new ArrayList<Role>();
		RoleId id = new RoleId("USR_SEC_DOMAIN", "END_USER");
		Role r = new Role();
		r.setId(id);
		roleList.add(r);
		
		pUser.setMemberOfRoles(roleList);
			
		return TransformScript.NO_DELETE;
	}
	
	private void populateObject(LineObject rowObj, ProvisionUser pUser) {
		Attribute attrVal = null;
		DateFormat df =  new SimpleDateFormat("MM-dd-yyyy"); 
		
		Map<String,Attribute> columnMap =  rowObj.getColumnMap();

		attrVal = columnMap.get("givenName");
		if (attrVal != null) {
			pUser.setFirstName(attrVal.getValue());
		}
		
		attrVal = columnMap.get("sn");
		if (attrVal != null) {
			pUser.setLastName(attrVal.getValue());
		}
		
		attrVal = columnMap.get("employeeNumber");
		if (attrVal != null) {
			pUser.employeeId = attrVal.getValue();
		}	
		
		attrVal = columnMap.get("cn");
		if (attrVal != null) {
			println("cn Value" + attrVal.getValue());
			addAttribute(pUser, attrVal);
		}	else {
			println("cn attribute was not found");
		}
		
		attrVal = columnMap.get("uid");
		if (attrVal != null) {
			println("uid Value" + attrVal.getValue());
			addAttribute(pUser, attrVal);
		}	else {
			println("cn attribute was not found");
		}
		
		
		attrVal = columnMap.get("mail");
		if (attrVal != null) {
			pUser.email = attrVal.getValue();
		}	else {
			println("cn attribute was not found");
		}
		
		attrVal = columnMap.get("postalCode");
		if (attrVal != null) {
			pUser.postalCd = attrVal.getValue();
		}	
	
		
		attrVal = columnMap.get("street");
		if (attrVal != null) {
			pUser.address1 = attrVal.getValue();
		}			
		attrVal = columnMap.get("st");
		if (attrVal != null) {
			pUser.state = attrVal.getValue();
		}				

		
		
	}
	
	
	private void addAttribute(ProvisionUser user, Attribute attr) {
		
		if (attr != null && attr.getName() != null && attr.getName().length() > 0) {
			UserAttribute userAttr = new UserAttribute(attr.getName(), attr.getValue());
			user.getUserAttributes().put(attr.getName(), userAttr);
			println("Attribute added to the user object.");
		}
	}
	
}
