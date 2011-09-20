
import org.openiam.base.ExtendController;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.selfsrvc.login.LoginCommand;
import org.openiam.provision.service.ProvisionService;
import org.openiam.idm.srvc.user.dto.User;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;


public class LoginController extends ExtendController   {
	
	static String BASE_URL= "http://localhost:8080/openiam-idm-esb/idmsrvc";
	

	public int pre(String command, Map<String,Object> objList,Object cmd) {
		
		System.out.println("LoginController script called with command." + command);
		
		def LoginCommand logincommand = (LoginCommand)cmd;
		def ProvisionService provisionService = povisionService();
		
		def String userId = objList.get("userId"); 
		def String userObj = (User)objList.get("userObj"); 
		
		System.out.println("UserId = " + userId);
		System.out.println("UserObj = " + userObj);
		
	
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
  
  ProvisionService povisionService() {
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