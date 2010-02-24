package org.openiam.base;

/**
 * Obtains configuration information for password change and reset from spring configuration files.
 * @author suneet
 * @version 2
 *
 */
public class SysConfiguration {
	protected String defaultManagedSysId = null;

	public String getDefaultManagedSysId() {
		return defaultManagedSysId;
	}

	public void setDefaultManagedSysId(String defaultManagedSysId) {
		this.defaultManagedSysId = defaultManagedSysId;
	}

	

	
	
}
