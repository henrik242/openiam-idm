
import org.openiam.base.ExtendController;
import org.openiam.provision.dto.ProvisionUser;
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


public class NewUserController extends ExtendController   {

	static String BASE_URL= "http://localhost:8080/openiam-idm-esb/idmsrvc";
	

	public int pre(String command, Map<String,Object> objList,Object cmd) {
		
	  ProvisionUser u = (ProvisionUser) objList.get("user");
    List<Role> roleList =  u.getMemberOfRoles();
    if (roleList != null && roleList.size() > 0)  {
        Role r = roleList.get(0);
        if (r.getId().getRoleId().equals("ORG_ADMIN")) {
            // set the delegated admin filter
            u.setDelAdmin(0);
            Map<String, UserAttribute> aMap =  u.getUserAttributes();
            if (aMap == null) {
                aMap = new HashMap<String, UserAttribute>();
            }
            UserAttribute at = new UserAttribute("DLG_FLT_ORG", u.getCompanyId());
            at.setOperation(AttributeOperationEnum.ADD);
            at.setId(null);
            aMap.put("DLG_FLT_ORG", at);
            
        }
    }
        

		return ExtendController.SUCCESS_CONTINUE;
		

		
	}
	
	
	public int post(String command, Map<String,Object> objList,Object cmd) {
		return ExtendController.SUCCESS_CONTINUE;
	}
	
	
  public int validate(String command,
                                 Map<String,Object> objList,
                                 Object cmd,
                                 Object err) {
    
    NewHireCommand newHireCmd =  (NewHireCommand) cmd;		
		User user = newHireCmd.getUser();
		
                                 	
    if (user.getLastName() == null || user.getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}                               	
                               
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