
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

	public int pre(String command, Map<String,Object> objList,Object cmd) {
		
		System.out.println("NewUserController extension script called with command." + command);
		
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
		return ExtendCommand.SUCCESS_CONTINUE;
	}
	
	public int validate(String command,
                                 Map<String,Object> objList,
                                 Object cmd,
                                 Object Errors) {
                               
  }
  

}