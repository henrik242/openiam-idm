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
import org.openiam.idm.srvc.synch.service.TransformScript;
import org.openiam.idm.srvc.synch.service.ValidationScript;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;

/**
 * Factory to create the scripts that are used in the synchronziation process.
 * @author suneet
 *
 */
public class SynchScriptFactory {

	private static final Log log = LogFactory.getLog(SynchScriptFactory.class);
	private static String scriptEngine = "org.openiam.script.GroovyScriptEngineIntegration";
	
	private static Object createScript(String scriptName) {
		ScriptIntegration se = null;
		try {
			se = ScriptFactory.createModule(scriptEngine); 
		}catch(Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
		return (Object)se.instantiateClass(null, scriptName);

	}
	
	public static ValidationScript createValidationScript(String scriptName) throws ClassNotFoundException {
		return (ValidationScript)createScript(scriptName);
		
	}

	
	public static TransformScript createTransformationScript(String scriptName) throws ClassNotFoundException {
		
		return (TransformScript)createScript(scriptName);
		
	}
	public String getScriptEngine() {
		return scriptEngine;
	}
	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}

}
