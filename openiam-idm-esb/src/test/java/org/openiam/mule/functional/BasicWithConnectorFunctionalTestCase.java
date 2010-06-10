package org.openiam.mule.functional;

import org.mule.tck.FunctionalTestCase;

public class BasicWithConnectorFunctionalTestCase extends FunctionalTestCase {

	public void testStartup() throws Exception {
		 //Not an elaborate test, just test that Mule starts up.
	}

	@Override
	protected String getConfigResources() {
		return "openiam-mule-config.xml openiam-connector-mule-config.xml openiam-standalone-mule-config.xml openiam-connector-standalone-mule-config.xml";
	}

}
