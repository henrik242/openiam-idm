import java.util.ArrayList;
import java.util.List;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;

def List<Role> roleList = user.getMemberOfRoles();
println("user roles =" + roleList);

if (roleList != null) {
	if (roleList.size() > 0)  {
		output = roleList.get(0).id.roleId;
	}else {
		output = null;
	}
}else {
	output = null;
}
