package org.openiam.idm.srvc.mngsys.dto;

// Generated Nov 3, 2008 12:14:43 AM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



/**
 * Domain object representing a managed resource. Managed systems include items such as AD, LDAP, etc which are
 * managed by the IDM system. Managed Resource can also be forms
 */
public class ManagedSys implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -648884785253890053L;
	private String managedSysId;
	private String name;
	private String description;
	private String status;
	private String connectorId;
	private String domainId;
	private String hostUrl;
	private Integer port;
	private String commProtocol;
	private String userId;
	private String pswd;
	private String decryptPassword;
	private Date startDate;
	private Date endDate;
	private String resourceId;
	//private Set<ApproverAssociation> resourceApprovers = new HashSet<ApproverAssociation>(0);
	/*
	private Set<AttributeMap> systemAttributeMap = new HashSet<AttributeMap>(0);
	*/
	private Set<ManagedSystemObjectMatch> mngSysObjectMatchs = new HashSet<ManagedSystemObjectMatch>(0);

	public ManagedSys() {
	}

	public ManagedSys(String managedSysId, String connectorId, String domainId) {
		this.managedSysId = managedSysId;
		this.connectorId = connectorId;
		this.domainId = domainId;
	}

	public ManagedSys(String managedSysId, String name, String description,
			String status, String connectorId, String domainId, String hostUrl,
			Integer port, String commProtocol, String userId, String pswd,
			Date startDate, Date endDate) {
		this.managedSysId = managedSysId;
		this.name = name;
		this.description = description;
		this.status = status;
		this.connectorId = connectorId;
		this.domainId = domainId;
		this.hostUrl = hostUrl;
		this.port = port;
		this.commProtocol = commProtocol;
		this.userId = userId;
		this.pswd = pswd;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}

	public String getManagedSysId() {
		return this.managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConnectorId() {
		return this.connectorId;
	}

	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public String getDomainId() {
		return this.domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getHostUrl() {
		return this.hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getCommProtocol() {
		return this.commProtocol;
	}

	public void setCommProtocol(String commProtocol) {
		this.commProtocol = commProtocol;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPswd() {
		return this.pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}


	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Return a ManagedSystemObjectMatch for an object type.
	 * Return null is an object for the specified objectType is not found.
	 * @param objectType
	 * @return
	 */
	public ManagedSystemObjectMatch getObjectMatchDetailsByType(String objectType) {
		Set<ManagedSystemObjectMatch> matchSet = getMngSysObjectMatchs();
		if (matchSet == null || matchSet.isEmpty())
			return null;
		Iterator<ManagedSystemObjectMatch> it = matchSet.iterator();
		while (it.hasNext()) {
			ManagedSystemObjectMatch match = it.next();
			if (match.getObjectType().equalsIgnoreCase(objectType)) {
				return match;
			}
		}
		return null;
	}



	public Set<ManagedSystemObjectMatch> getMngSysObjectMatchs() {
		return mngSysObjectMatchs;
	}

	public void setMngSysObjectMatchs(
			Set<ManagedSystemObjectMatch> mngSysObjectMatchs) {
		this.mngSysObjectMatchs = mngSysObjectMatchs;
	}

	public String getDecryptPassword() {
		return decryptPassword;
	}

	public void setDecryptPassword(String decryptPassword) {
		this.decryptPassword = decryptPassword;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

/*	public Set<ApproverAssociation> getResourceApprovers() {
		return resourceApprovers;
	}

	public void setResourceApprovers(Set<ApproverAssociation> resourceApprovers) {
		this.resourceApprovers = resourceApprovers;
	}
*/
/*
	public Set<AttributeMap> getSystemAttributeMap() {
		return systemAttributeMap;
	}

	public void setSystemAttributeMap(Set<AttributeMap> systemAttributeMap) {
		this.systemAttributeMap = systemAttributeMap;
	}
*/
}
