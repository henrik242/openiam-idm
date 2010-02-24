/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.pswd.rule;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordHistory;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.service.service.ServiceDAOImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Validates a password to ensure that is conforms to the history rules.
 * 
 * @author suneet
 *
 */
public class PasswordHistoryRule extends AbstractPasswordRule {

	private static final Log log = LogFactory.getLog(PasswordHistoryRule.class);

	public PasswordValidationCode isValid() {
		
		log.info("PasswordHistoryRule called.");
		
		PasswordValidationCode retval = PasswordValidationCode.SUCCESS;
		boolean enabled = false;
						
		PolicyAttribute attribute = policy.getAttribute("PWD_HIST_VER");
		if (attribute.getValue1() != null) {
			enabled = true;

		}
		
		if (enabled) {			
			log.info("password history rule is enabled.");
			Password pswd = new Password();
			pswd.setDomainId(lg.getId().getDomainId());
			pswd.setManagedSysId(lg.getId().getManagedSysId());
			pswd.setPrincipal(lg.getId().getLogin());
			pswd.setPassword(password);
			
			int version =  Integer.parseInt( attribute.getValue1() );
			List<PasswordHistory> historyList = passwordHistoryDao.findPasswordHistoryByPrincipal(
					 pswd.getDomainId(), pswd.getPrincipal(), pswd.getManagedSysId(), version);
			if (historyList == null || historyList.isEmpty()) {
				// no history
				return retval;
			}
			// check the list.
			log.info("Found " + historyList.size() + " passwords in the history");
			for ( PasswordHistory hist  : historyList) {
				String pwd = hist.getPassword();
				String decrypt =  cryptor.decrypt(pwd);
				if (pswd.getPassword().equals(decrypt)) {
					log.info("matching password found.");
					return PasswordValidationCode.FAIL_HISTORY_RULE;
				}
			}

			
		}	
		
		return retval;		
		
	}
	

	
	
}
