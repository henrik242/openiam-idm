
import org.openiam.base.ExtendController;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.selfsrvc.pswd.PasswordChangeCommand;
import org.openiam.provision.type.ModificationAttribute;
import org.openiam.provision.type.ExtensibleAttribute;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;


public class PasswordChangeController extends ExtendController   {

static String BASE_URL= "http://localhost:8080/openiam-idm-esb/idmsrvc";

	
	public int pre(String command, Map<String,Object> objList,Object cmd) {
		
		println("PasswordChangeController script called with command." + command);
		
		def PasswordChangeCommand passwordChangeCmd = (PasswordChangeCommand)cmd;
		def ProvisionService provisionService = provisionService();
		
		def String userId = objList.get("userId"); 
		def PasswordSync passwordSync = (PasswordSync)objList.get("passwordSync"); 
	
	// Example below of how to update additional attributes in ldap while doing a password sync
	//	passwordSync.passThruAttributes = true;
	//	def List<ExtensibleAttribute> extAttr = passwordSync.attributeList;
	//	def ExtensibleAttribute ext = new ExtensibleAttribute("displayName","updatedDisplayName-x")
	//	ext.operation =  ModificationAttribute.replace;
	//	extAttr.add(ext);
	
		println("UserId = " + userId);
		println("passwordSyncObject = " + passwordSync );
		
	
	  	return ExtendController.SUCCESS_CONTINUE;
	
		//objList.put("REDIRECT_URL","/passwordChange.selfserve?hideRMenu=1&cd=pswdexp");
		//return ExtendController.SUCCESS_STOP;
		

		
	}
	
	
	public int post(String command, Map<String,Object> objList,Object cmd) {
		return ExtendController.SUCCESS_CONTINUE;
	}
	
	
  public int validate(String command,
                                 Map<String,Object> objList,
                                 Object cmd,
                                 Object Errors) {
                               
  }
  
  ProvisionService provisionService() {
		String serviceUrl = BASE_URL + "/ProvisioningService"
		String port ="ProvisionControllerServicePort"
		String nameSpace = "http://www.openiam.org/service/provision"
		
		Service service = Service.create(QName.valueOf(serviceUrl))
			
		service.addPort(new QName(nameSpace,port),
				SOAPBinding.SOAP11HTTP_BINDING,	serviceUrl)
		
		return service.getPort(new QName(nameSpace,	port),
				ProvisionService.class);
  }
	

}