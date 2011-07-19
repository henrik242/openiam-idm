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
package org.openiam.idm.srvc.pswd.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author suneet
 *
 */
public class ValidatorFactory implements ApplicationContextAware {
	// used to inject the application context into the groovy scripts
	public static ApplicationContext ac;
	
	protected static final Log log = LogFactory.getLog(ValidatorFactory.class);
	
	protected String scriptEngine;
	
	public static final String OBJECT_TYPE_JAVA = "java";
	public static final String OBJECT_TYPE_GROOVY = "groovy";
	
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		ac = ctx;
		
	}
	/**
	 * Creates an in instance of the Validator object
	 * @param className - Name of the Validator class or groovy script
	 * @param objType - Valid values are "java" or "groovy"
	 * @return
	 */
	public ChallengeResponseValidator createValidator(String className, String objType) {
		ScriptIntegration se = null;
		
		if (objType.equalsIgnoreCase(ValidatorFactory.OBJECT_TYPE_JAVA)) {
			return (ChallengeResponseValidator)ac.getBean(className);
		}else {
			// the object is defined as a groovy script.
			try {
				se = ScriptFactory.createModule(this.scriptEngine);
                return (ChallengeResponseValidator)se.instantiateClass(null, className);
			}catch(Exception e) {
				log.error(e);
				e.printStackTrace();
				return null;
			}
		}
		
	}
	public String getScriptEngine() {
		return scriptEngine;
	}
	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}

}
