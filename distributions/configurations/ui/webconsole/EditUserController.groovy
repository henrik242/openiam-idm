
import org.openiam.base.ExtendController;
import org.openiam.provision.dto.ProvisionUser;


public class EditUserController extends ExtendController   {

	public int pre(String command, Map<String,Object> objList,Object cmd) {
		
		System.out.println("EditUserController extension script called with command." + command);

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