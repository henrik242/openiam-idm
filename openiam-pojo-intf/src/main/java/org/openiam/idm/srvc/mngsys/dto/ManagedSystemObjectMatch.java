package org.openiam.idm.srvc.mngsys.dto;

// Generated Dec 20, 2008 7:54:58 PM by Hibernate Tools 3.2.2.GA

/**
 * Domain object which defines how specific objects with in a managed system are to be located.
 * For example, in a directory we can use either a BaseDN or a search filter. Note that the
 * search filters can contain parameters to increase their flexibility.
 */
public class ManagedSystemObjectMatch implements java.io.Serializable {

	private String objectSearchId;
	private String managedSys;
	private String objectType;
	private String matchMethod;
	private String searchFilter;
	private String baseDn;
	private String searchBaseDn;;
	private String keyField;

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public ManagedSystemObjectMatch() {
	}

	public ManagedSystemObjectMatch(String objectSearchId, String managedSys) {
		this.objectSearchId = objectSearchId;
		this.managedSys = managedSys;
	}





	public ManagedSystemObjectMatch(String baseDn, String keyField,
			String managedSys, String matchMethod, String objectSearchId,
			String objectType, String searchFilter) {
		super();
		this.baseDn = baseDn;
		this.keyField = keyField;
		this.managedSys = managedSys;
		this.matchMethod = matchMethod;
		this.objectSearchId = objectSearchId;
		this.objectType = objectType;
		this.searchFilter = searchFilter;
	}

	public String getObjectSearchId() {
		return this.objectSearchId;
	}

	public void setObjectSearchId(String objectSearchId) {
		this.objectSearchId = objectSearchId;
	}

	public String getManagedSys() {
		return this.managedSys;
	}

	public void setManagedSys(String managedSys) {
		this.managedSys = managedSys;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getMatchMethod() {
		return this.matchMethod;
	}

	public void setMatchMethod(String matchMethod) {
		this.matchMethod = matchMethod;
	}

	public String getSearchFilter() {
		return this.searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	public String getSearchBaseDn() {
		return searchBaseDn;
	}

	public void setSearchBaseDn(String searchBaseDn) {
		this.searchBaseDn = searchBaseDn;
	}

}
