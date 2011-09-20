package org.openiam.selfsrvc.helper;

import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;


/**
 * Gets an instance of the ScriptEngine
 * User: suneetshah
 * Date: 7/4/11
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptEngineUtil {

    public static ScriptIntegration getScriptEngine() throws ClassNotFoundException {
        String scriptEngine = "org.openiam.script.GroovyScriptEngineIntegration";
        ScriptIntegration se = null;

		return  ScriptFactory.createModule(scriptEngine);

    }
}
