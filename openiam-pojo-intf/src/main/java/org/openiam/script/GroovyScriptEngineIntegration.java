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
package org.openiam.script;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.control.CompilationFailedException;

/**
 * @author suneet
 * 
 */
public class GroovyScriptEngineIntegration implements ScriptIntegration {

	protected static final Log log = LogFactory
			.getLog(GroovyScriptEngineIntegration.class);

	static protected ResourceBundle res = ResourceBundle
			.getBundle("securityconf");
	static String[] roots = null;
	static GroovyScriptEngine gse = null;

	private Binding binding = new Binding();

	public GroovyScriptEngineIntegration() {
	}

	static public void init() {
		if (gse == null) {
			String scriptPath = res.getString("scriptRoot");
			roots = new String[] { scriptPath };
			try {
				gse = new GroovyScriptEngine(roots);
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}

	public Object execute(Map<String, Object> bindingMap, String scriptName) {
		init();

		try {
			setBinding(bindingMap);
			gse.run(scriptName, binding);
			return binding.getVariable("output");
		} catch (ScriptException se) {
			se.printStackTrace();
		} catch (ResourceException re) {
			re.printStackTrace();
		}
		return null;
	}

	/**
	 * Executes the specified groovy script under the given binding, returning
	 * the value of the variable "output"
	 */
	public Object executeAsScript(Map<String, Object> bindingMap, String script) {
		init();
		try {
			setBinding(bindingMap);
			GroovyShell gs = new GroovyShell(binding,gse.getConfig());
			gs.evaluate(script);
			return binding.getVariable("output");
		} catch (CompilationFailedException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.script.ScriptIntegration#instantiateClass(java.util.Map,
	 * java.lang.String)
	 */
	public Object instantiateClass(Map<String, Object> bindingMap,
			String scriptName) throws IOException {

		log.info("instantiateClass called.");

		GroovyClassLoader gcl = new GroovyClassLoader();
		try {
			String scriptPath = res.getString("scriptRoot");
			String fullPath = scriptPath + scriptName;
			Class cl = gcl.parseClass(new File(fullPath));
			return cl.newInstance();

		} catch (IllegalAccessException ia) {
			log.error(ia);
			ia.printStackTrace();

		} catch (InstantiationException ia) {
			log.error(ia);
			ia.printStackTrace();

		}

		return null;
	}

	private void setBinding(Map<String, Object> bindingMap) {
		if (bindingMap != null && !bindingMap.isEmpty()) {
			Iterator<String> keyIt = bindingMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				Object val = bindingMap.get(key);
				binding.setVariable(key, val);
			}
		}
	}

}
