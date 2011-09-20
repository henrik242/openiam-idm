package org.openiam.selfsrvc.pswd;

/**
 * Obtains configuration information for password change and reset from spring configuration files.
 * @author suneet
 * @version 2
 *
 */
public class PasswordConfiguration {
	private boolean displaySyncServices = false;
	private boolean displayDomainList = true;
	private String defaultSecurityDomain = null;
	private String defaultManagedSysId = null;
	private String defaultChallengeResponseGroup = null;
	protected String parentOrgId = null;
 	protected String managedSystemType = null;

	
	public boolean isDisplayDomainList() {
		return displayDomainList;
	}

	public void setDisplayDomainList(boolean displayDomainList) {
		this.displayDomainList = displayDomainList;
	}

	public boolean isDisplaySyncServices() {
		return displaySyncServices;
	}

	public void setDisplaySyncServices(boolean displaySyncServices) {
		this.displaySyncServices = displaySyncServices;
	}



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

	public String getDefaultChallengeResponseGroup() {
		return defaultChallengeResponseGroup;
	}

	public void setDefaultChallengeResponseGroup(
			String defaultChallengeResponseGroup) {
		this.defaultChallengeResponseGroup = defaultChallengeResponseGroup;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getManagedSystemType() {
		return managedSystemType;
	}

	public void setManagedSystemType(String managedSystemType) {
		this.managedSystemType = managedSystemType;
	}
	
}
