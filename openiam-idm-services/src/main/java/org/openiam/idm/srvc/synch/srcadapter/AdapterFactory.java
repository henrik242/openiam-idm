/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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
package org.openiam.idm.srvc.synch.srcadapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.service.SourceAdapter;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Factory to create the scripts that are used in the synchronization process.
 * @author suneet
 *
 */
public class AdapterFactory  implements  ApplicationContextAware {

	private static final Log log = LogFactory.getLog(AdapterFactory.class);
	private String scriptEngine;
	public static ApplicationContext ac;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}
	
	
	public SourceAdapter create(SynchConfig config) throws ClassNotFoundException {
		ScriptIntegration se = null;
		SourceAdapter adpt = null;
		
		String adapterType = config.getSynchAdapter();
		String customScript = config.getCustomAdatperScript(); 
		if (adapterType != null) {
			try {
				if (adapterType.equalsIgnoreCase("CUSTOM") && 
					( adapterType  != null &&  adapterType.length() > 0)) {
					// custom adapter- written groovy
					try {
						se = ScriptFactory.createModule(scriptEngine); 
					}catch(Exception e) {
						log.error(e);
						e.printStackTrace();
						return null;
					}
					adpt =  (SourceAdapter)se.instantiateClass(null, customScript);
	
				}else {
					// using standard adapter
					Class cls = null;
					if (adapterType.equalsIgnoreCase("RDBMS")) {
						return (SourceAdapter)ac.getBean("rdbmsAdapter");
					}
					if (adapterType.equalsIgnoreCase("CSV")) {
						return (SourceAdapter)ac.getBean("csvAdapter");
					}				
					if (adapterType.equalsIgnoreCase("LDAP")) {
						return (SourceAdapter)ac.getBean("ldapAdapter");
					}
					if (adapterType.equalsIgnoreCase("AD")) {
						return (SourceAdapter)ac.getBean("activeDirAdapter");
					}	
					adpt = (SourceAdapter)cls.newInstance();
				}
				adpt.setApplicationContext(ac);
				return adpt;
				
			}catch(IllegalAccessException ia) {
				log.error(ia.getMessage(),ia);
				
			}catch(InstantiationException ie) {
				log.error(ie.getMessage(),ie);
			}
		}
		
		return null;
		
	}

	public String getScriptEngine() {
		return scriptEngine;
	}

	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}


}
