package org.openiam.webadmin.busdel.base;

import org.springframework.web.context.*;
import java.util.*;

import org.apache.struts.util.LabelValueBean;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;;



/**
 * Access class to the ServiceMgr.
 * @author Suneet Shah
 *
 */
public class SecurityDomainAccess {

	private SecurityDomainDataService secDomainMgr = null;
	
	public SecurityDomainAccess() {
	}


	public SecurityDomainAccess(WebApplicationContext  webContext) {
		secDomainMgr = (SecurityDomainDataService)webContext.getBean("secDomainService");
	}
	
	public SecurityDomain[] getAllSecurityDomains() {
		return secDomainMgr.getAllSecurityDomains();
	}
	
	public List getDomainListAsLabels(SecurityDomain[] domainList) {
		List<LabelValueBean> newCodeList = new LinkedList();
		
		if (domainList != null && domainList.length > 0) {
			newCodeList.add(new LabelValueBean("",""));
	    	for (int i = 0; i < domainList.length; i++) {       		
	    		SecurityDomain val = domainList[i];
	    	 	LabelValueBean label = new LabelValueBean(val.getName(),val.getDomainId());
	    	 	newCodeList.add(label);
	    	}
		}
		return newCodeList;
    }
	
	public List getAllDomainsWithExclude(String exclude) {

		List<LabelValueBean> newCodeList = new LinkedList();
		
		SecurityDomain[] secDomainAry =  getAllSecurityDomains();
		
		if (secDomainAry != null && secDomainAry.length > 0) {
			// only show a blank line in the security domain
			// if we have more then 1 domain.
			if (secDomainAry.length > 2) {
				newCodeList.add(new LabelValueBean("",""));
			}
	    	for (int i = 0; i < secDomainAry.length; i++) {       		
	    		SecurityDomain val = secDomainAry[i];
	    		if (!val.getDomainId().equalsIgnoreCase(exclude)) {
	    			LabelValueBean label = new LabelValueBean(val.getName(),val.getDomainId());
	    			newCodeList.add(label);
	    		}
	    	}
		}
		return newCodeList;
	}
	
}
