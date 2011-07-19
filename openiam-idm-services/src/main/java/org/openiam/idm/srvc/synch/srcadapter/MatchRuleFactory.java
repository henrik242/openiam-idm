package org.openiam.idm.srvc.synch.srcadapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.service.MatchObjectRule;
import org.openiam.idm.srvc.synch.service.SourceAdapter;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Instantiates the appropriate matching rule object for use in the synchronization request
 * @author suneet
 *
 */
public class MatchRuleFactory implements  ApplicationContextAware {
	public static ApplicationContext ac;
	private String scriptEngine;
	private static final Log log = LogFactory.getLog(MatchRuleFactory.class);
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}
	
	public MatchObjectRule create(SynchConfig config) throws ClassNotFoundException {
		ScriptIntegration se = null;
		
		if (config.getCustomMatchRule() == null || config.getCustomMatchRule().length() == 0 ) {
			return (MatchObjectRule)ac.getBean("defaultMatchRule");		
		}
		// instantiate a rule via script
		String matchRule = config.getCustomMatchRule();
		if (matchRule == null || matchRule.length() ==0) {
			return null;
		}
		try {
			se = ScriptFactory.createModule(scriptEngine); 
			return (MatchObjectRule)se.instantiateClass(null, matchRule);
		}catch(Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	public String getScriptEngine() {
		return scriptEngine;
	}

	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}
}
