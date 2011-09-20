
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode
import org.openiam.idm.srvc.pswd.rule.AbstractPasswordRule

public class MyPasswordRule extends AbstractPasswordRule {


	public PasswordValidationCode isValid() {
		print("********MyPasswordRule called.")
		
		print("Abstract object contains several variables");
		print("- user= " + user)
		print("- login=" + lg);
		print("- principal=" + principal)
		
		return PasswordValidationCode.SUCCESS
	}
			
}

