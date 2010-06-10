package org.openiam.base;

/**
 * Obtains configuration information for password change and reset from spring configuration files.
 * @author suneet
 * @version 2
 *
 */
public class SysConfiguration {
	protected String defaultManagedSysId = null;
	protected String defaultSecurityDomain = null;

	public String getDefaultManagedSysId() {
		return defaultManagedSysId;
	}

	public void setDefaultManagedSysId(String defaultManagedSysId) {
		this.defaultManagedSysId = defaultManagedSysId;
	}

	public String getDefaultSecurityDomain() {
		return defaultSecurityDomain;
	}

	public void setDefaultSecurityDomain(String defaultSecurityDomain) {
		this.defaultSecurityDomain = defaultSecurityDomain;
	}

	

	
	
}
