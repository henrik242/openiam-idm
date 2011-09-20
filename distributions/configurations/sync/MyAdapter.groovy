
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.service.MatchObjectRule;
import org.openiam.idm.srvc.synch.service.SourceAdapter;
import org.openiam.idm.srvc.synch.service.TransformScript;
import org.openiam.idm.srvc.synch.service.ValidationScript;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.openiam.idm.srvc.synch.srcadapter.MatchRuleFactory;
import org.mule.api.MuleContext;

public class MyAdapter implements SourceAdapter {

	protected LineObject rowHeader = new LineObject();
	protected ProvisionUser pUser = new ProvisionUser();
	public static ApplicationContext ac;	
	MatchRuleFactory matchRuleFactory;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}
	
	public SyncResponse startSynch(SynchConfig config) {

         println("CMY CSV");

        println("CUSTOM SYNCHRONIZATION COMPLETE^^^^^^^^");
		
		SyncResponse resp = new SyncResponse(ResponseStatus.SUCCESS);
		return resp;
		
	}
	
	
	




	public static ApplicationContext getAc() {
		return ac;
	}

	public static void setAc(ApplicationContext ac) {
		MyAdapter.ac = ac;
	}
	
	  public void setMuleContext(MuleContext ctx) {
        
    }
    

}
