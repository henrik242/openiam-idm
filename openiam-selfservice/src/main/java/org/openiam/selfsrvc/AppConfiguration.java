package org.openiam.selfsrvc;

/**
 * Obtains configuration information for the self service application.
 * @author suneet
 * @version 2
 *
 */
public class AppConfiguration {

	private String logoUrl;
	private String title;
	private Integer maxResultSetSize;	
	private String welcomePageUrl;
	private String defaultLang;
	private String parentOrgId;

	private String publicLeftMenuGroup;
	private String publicRightMenuGroup1;
	private String publicRightMenuGroup2;
	private String publicRightMenuGroup3;
	
	
	protected String defaultSecurityDomain = null;
	protected String defaultManagedSysId = null;

	
	public Integer getMaxResultSetSize() {
		return maxResultSetSize;
	}
	public void setMaxResultSetSize(Integer maxResultSetSize) {
		this.maxResultSetSize = maxResultSetSize;
	}
	
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String url) {
		logoUrl = url;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}
	public String getWelcomePageUrl() {
		return welcomePageUrl;
	}
	public void setWelcomePageUrl(String welcomePageUrl) {
		this.welcomePageUrl = welcomePageUrl;
	}
	public String getDefaultLang() {
		return defaultLang;
	}
	public void setDefaultLang(String defaultLang) {
		this.defaultLang = defaultLang;
	}
	public String getPublicLeftMenuGroup() {
		return publicLeftMenuGroup;
	}
	public void setPublicLeftMenuGroup(String publicLeftMenuGroup) {
		this.publicLeftMenuGroup = publicLeftMenuGroup;
	}
	public String getPublicRightMenuGroup1() {
		return publicRightMenuGroup1;
	}
	public void setPublicRightMenuGroup1(String publicRightMenuGroup1) {
		this.publicRightMenuGroup1 = publicRightMenuGroup1;
	}
	public String getPublicRightMenuGroup2() {
		return publicRightMenuGroup2;
	}
	public void setPublicRightMenuGroup2(String publicRightMenuGroup2) {
		this.publicRightMenuGroup2 = publicRightMenuGroup2;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getDefaultSecurityDomain() {
		return defaultSecurityDomain;
	}
	public void setDefaultSecurityDomain(String defaultSecurityDomain) {
		this.defaultSecurityDomain = defaultSecurityDomain;
	}
	public String getDefaultManagedSysId() {
		return defaultManagedSysId;
	}
	public void setDefaultManagedSysId(String defaultManagedSysId) {
		this.defaultManagedSysId = defaultManagedSysId;
	}
	public String getPublicRightMenuGroup3() {
		return publicRightMenuGroup3;
	}
	public void setPublicRightMenuGroup3(String publicRightMenuGroup3) {
		this.publicRightMenuGroup3 = publicRightMenuGroup3;
	}



	
}


