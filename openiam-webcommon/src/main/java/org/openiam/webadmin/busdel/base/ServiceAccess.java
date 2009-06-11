package org.openiam.webadmin.busdel.base;

import org.springframework.web.context.*;
import java.util.*;

import org.apache.struts.util.LabelValueBean;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.service.service.ServiceMgr;
import org.openiam.idm.srvc.service.dto.Service;



/**
 * Access class to the ServiceMgr.
 * @author Suneet Shah
 *
 */
public class ServiceAccess {

	private ServiceMgr servMgr = null;
	
	public ServiceAccess() {
		// TODO Auto-generated constructor stub
	}


	public ServiceAccess(WebApplicationContext  webContext) {
		servMgr = (ServiceMgr)webContext.getBean("serviceManager");
	}
	
	public List<Service> getAllServices() {
		return servMgr.getAllServices();
	}
	
	public List getServiceListAsLabels(List<Service> serviceList) {
		List<LabelValueBean> newCodeList = new LinkedList();
		
		if (serviceList != null && serviceList.size() > 0) {
			newCodeList.add(new LabelValueBean("",""));
	    	for (int i = 0; i < serviceList.size(); i++) {       		
	    		Service val = serviceList.get(i);
	    	 	LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
	    	 	newCodeList.add(label);
	    	}
		}
		return newCodeList;
    }
	
}
