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
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author suneet
 *
 */
public class GroovyScriptEngineIntegration implements ScriptIntegration {

	 static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	 static String[] roots = null; 
	 static GroovyScriptEngine gse = null;
	 
	 Binding binding = new Binding();
	 
	 public GroovyScriptEngineIntegration() {
	 }
		
	 static public void init() {
		 if (gse == null) {
			 String scriptPath = res.getString("scriptRoot");
			 roots = new String[] { scriptPath };			
			 try {
			 gse = new GroovyScriptEngine(roots);
			 }catch(IOException io) {
					io.printStackTrace();
			 }
		 }
	 }
	 
	 public Object execute(Map<String, Object> bindingMap, String scriptName) {
		 init();
		 
		 try{
			 if (bindingMap != null && !bindingMap.isEmpty()) {
				 Iterator<String> keyIt =  bindingMap.keySet().iterator();
				 while (keyIt.hasNext()) {
					String key = keyIt.next();
					Object val = bindingMap.get(key);
					binding.setVariable(key, val);
				 }
			 }
			 gse.run(scriptName, binding);
			 return binding.getVariable("output");
		}catch(ScriptException se) {
			se.printStackTrace();
		}catch(ResourceException re) {
			re.printStackTrace();
		}
		return null;

		 
	 }


	
}
