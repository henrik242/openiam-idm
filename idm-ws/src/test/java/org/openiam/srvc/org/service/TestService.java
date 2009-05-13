package org.openiam.srvc.org.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;

public class TestService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
      ApplicationContext context
           = new ClassPathXmlApplicationContext(new String[] {"client-beans.xml"});

    //  OrganizationDataService client = (OrganizationDataService)context.getBean("orgService");
	}

}
