package org.openiam.idm.srvc.synch.srcadapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.service.MatchObjectRule;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class DefaultMatchObjectRule implements MatchObjectRule {

	private UserDataWebService userManager = null;
	public static ApplicationContext ac;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
}
	
	public User lookup(SynchConfig config, Map<String, Attribute> rowAttr) {
		UserSearch search = new UserSearch();
		//Map<String, UserAttribute> atMap = user.getUserAttributes();
		String srcFieldValue = rowAttr.get(config.getCustomMatchAttr()).getValue();
	
		
		if (config.getMatchFieldName().equalsIgnoreCase("USERID")) {
			search.setUserId(srcFieldValue);
		}
		if (config.getMatchFieldName().equalsIgnoreCase("PRINCIPAL")) {
			search.setPrincipal(srcFieldValue);
		}
		if (config.getMatchFieldName().equalsIgnoreCase("EMAIL")) {
			search.setEmailAddress(srcFieldValue);
		}	
		if (config.getMatchFieldName().equalsIgnoreCase("EMPLOYEE_ID")) {
			search.setEmployeeId(srcFieldValue);
		}		
		if (config.getMatchFieldName().equalsIgnoreCase("ATTRIBUTE")) {
			System.out.println("- cofiguring search by attribute..");
			System.out.println("- match attr=.." + config.getCustomMatchAttr());
		
				
			// get the attribute value from the data_set
			String valueToMatch = rowAttr.get(config.getCustomMatchAttr()).getValue();
			System.out.println("- src field value=.." + valueToMatch);
			
			search.setAttributeName(config.getCustomMatchAttr());
			search.setAttributeValue(valueToMatch);
		}
		
		userManager = (UserDataWebService) ac.getBean("userWS");
		List<User> userList = userManager.search(search).getUserList();
		
		if (userList != null) {
			System.out.println("User matched with existing user...");
			User u = userList.get(0);
			return u;
		}		
		return null;
	}

}
