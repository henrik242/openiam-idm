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
	private String defaultSecurityDoamin = null;

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

	public String getDefaultSecurityDoamin() {
		return defaultSecurityDoamin;
	}

	public void setDefaultSecurityDoamin(String defaultSecurityDoamin) {
		this.defaultSecurityDoamin = defaultSecurityDoamin;
	}
	
}
