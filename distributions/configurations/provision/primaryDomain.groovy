import org.openiam.idm.srvc.role.dto.Role;
import java.util.List;


List<Role> userRoles = user.memberOfRoles;
if (userRoles == null || userRoles.isEmpty()) {
	output="USR_SEC_DOMAIN";
}else {
 Role rl = userRoles.get(0);
 output = rl.id.serviceId;
}

