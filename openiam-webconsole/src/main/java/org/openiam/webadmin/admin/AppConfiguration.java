package org.openiam.webadmin.admin;

/**
 * Obtains configuration information for password change and reset from spring configuration files.
 * @author suneet
 * @version 2
 *
 */
public class AppConfiguration {
	protected boolean displaySyncServices = false;
	protected boolean displayDomainList = true;
	protected String defaultSecurityDomain = null;
	protected String defaultManagedSysId = null;
	protected String defaultChallengeResponseGroup = null;
	protected String parentOrgId = null;
	protected String userCategoryType = null;
	protected String managedSystemType;
	protected String homePage;
	protected String errorUrl;
	

	
	public String getUserCategoryType() {
		return userCategoryType;
	}

	public void setUserCategoryType(String userCategoryType) {
		this.userCategoryType = userCategoryType;
	}

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

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
}
