
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
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;



public class TransformCSVUserSrcRecord extends AbstractTransformScript {
	
	static String BASE_URL= "http://localhost:8080/openiam-idm-esb/idmsrvc";
	
	public int execute(LineObject rowObj, ProvisionUser pUser) {
		
		
		System.out.println("** 2-Transform object called. **");
		
		def loginManager = loginService();
		Attribute attrVal = null;
	
		println("Is New User:" + isNewUser)
		println("User Object:" + user)
		println("PrincipalList: " + principalList)
		println("User Roles:" + userRoleList)
		
		
		Map<String,Attribute> columnMap =  rowObj.getColumnMap();
		
		
		populateObject(rowObj, pUser);
		
		if (isNewUser) {
			pUser.setUserId(null);
		}
		
		attrVal = columnMap.get("status");
		if (attrVal != null) {
			if (attrVal.getValue().equals("Active")) {
				pUser.status = UserStatusEnum.ACTIVE;
			}
		}	
		
		if (pUser.status == null) {
			pUser.status = UserStatusEnum.INACTIVE;
		}
		pUser.securityDomain = "0"
		
		println("User status = " + pUser.status);
	
		
		// Set role
		if (isNewUser) {
		attrVal = columnMap.get("role");
		if (attrVal != null) {
			List<Role> roleList = new ArrayList<Role>();
			RoleId id = new RoleId("USR_SEC_DOMAIN", attrVal.getValue());
			Role r = new Role();
			r.setId(id);
			roleList.add(r);
			
			println("Role=" + attrVal.getValue());
		
			pUser.setMemberOfRoles(roleList);
		}
		}
		
		if (isNewUser){
		
		//  set fax and phone
		List<Phone> phoneList = new ArrayList<Phone>();
		List<EmailAddress> emailList = new ArrayList<EmailAddress>();
		
		
		attrVal = columnMap.get("fax");
		if (attrVal != null) {
			String fax  = attrVal.getValue();
			println("fax=" + fax);
			Phone faxPhone = new Phone();
			faxPhone.setAreaCd(fax.substring(0,3));
			faxPhone.setPhoneNbr(fax.substring(4));
			faxPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			faxPhone.setName("FAX");
			//phoneList.add(faxPhone);
			pUser.getPhone().add(faxPhone);
			
		}	
		
		attrVal = columnMap.get("phone");
		if (attrVal != null) {
			String phone = attrVal.getValue();
			println("Phone=" + phone);
			Phone ph = new Phone();
			ph.setAreaCd(phone.substring(0,3));
			ph.setPhoneNbr(phone.substring(4));
			ph.setParentType(ContactConstants.PARENT_TYPE_USER);
			ph.setName("DESK PHONE");
			//phoneList.add(ph);
			pUser.getPhone().add(ph);
			
		}

		attrVal = columnMap.get("email");
		if (attrVal != null) {
			String email = attrVal.getValue();
			EmailAddress email1 = new EmailAddress();
			email1.setEmailAddress(email);
			email1.setName("EMAIL1");
			email1.setParentType(ContactConstants.PARENT_TYPE_USER);
        	emailList.add(email1);
        	pUser.getEmailAddress().add(email1);
        
		}
		

		}
		
		

		
			
		return TransformScript.NO_DELETE;
	}
	
	private void populateObject(LineObject rowObj, ProvisionUser pUser) {
		Attribute attrVal = null;
		DateFormat df =  new SimpleDateFormat("MM-dd-yyyy"); 
		
		Map<String,Attribute> columnMap =  rowObj.getColumnMap();
		
		attrVal = columnMap.get("userid");
		if (attrVal != null) {
			addAttribute(pUser, attrVal);
		}

		attrVal = columnMap.get("first name");
		if (attrVal != null) {
			pUser.setFirstName(attrVal.getValue());
		}
		
		attrVal = columnMap.get("second name");
		if (attrVal != null) {
			pUser.setLastName(attrVal.getValue());
		}
		
		attrVal = columnMap.get("email");
		if (attrVal != null) {
			pUser.email = attrVal.getValue();
		}	

		attrVal = columnMap.get("title");
		if (attrVal != null) {
			pUser.title = attrVal.getValue();
		}	
		
		attrVal = columnMap.get("preferred language");
		if (attrVal != null) {
			addAttribute(pUser, attrVal);
		}	
		
		attrVal = columnMap.get("inactive timemout");
		if (attrVal != null) {
			addAttribute(pUser, attrVal);
		}
		
		// set org
		attrVal = columnMap.get("org");
		if (attrVal != null) {
			addAttribute(pUser, attrVal);
		}
		// set org
		attrVal = columnMap.get("manager");
		if (attrVal != null) {
			addAttribute(pUser, attrVal);
		}

		
	}
	
	
	private void addAttribute(ProvisionUser user, Attribute attr) {
		
		if (attr != null && attr.getName() != null && attr.getName().length() > 0) {
			UserAttribute userAttr = new UserAttribute(attr.getName(), attr.getValue());
			user.getUserAttributes().put(attr.getName(), userAttr);
			println("Attribute added to the user object.");
		}
	}
	
	
	static LoginDataWebService loginService() {
		String serviceUrl = BASE_URL + "/LoginDataWebService"
		String port ="LoginDataWebServicePort"
		String nameSpace = "urn:idm.openiam.org/srvc/auth/service"
		
		Service service = Service.create(QName.valueOf(serviceUrl))
			
		service.addPort(new QName(nameSpace,port),
				SOAPBinding.SOAP11HTTP_BINDING,	serviceUrl)
		
		return service.getPort(new QName(nameSpace,	port),
				LoginDataWebService.class);
	}
	
}
