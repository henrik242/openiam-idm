package org.openiam.idm.srvc.mngsys.dto;



import java.util.Date;

import org.openiam.idm.srvc.policy.dto.Policy;

/**
 * AttributeMap represents the mapping between an attribute in the target system and
 * an attribute policy.
 */
public class AttributeMap implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4584242607384442243L;
	private String attributeMapId;
	private String managedSysId;
	private String resourceId;
	private String mapForObjectType;
	private String attributeName;
	private String targetAttributeName;
	private Integer authoritativeSrc;
	private Policy attributePolicy;
	private String rule;
	private String status;
	private Date startDate;
	private Date endDate;
	private Integer storeInIamdb;
	private Boolean selected = new Boolean(false);

	public AttributeMap() {
	}

	public AttributeMap(String attributeMapId, String managedSysId) {
		this.attributeMapId = attributeMapId;
		this.managedSysId = managedSysId;
	}

	public AttributeMap(String attributeMapId, String managedSysId,
			String resourceId, String mapForObjectType, String attributeName,
			String targetAttributeName, Integer authoritativeSrc,
			String rule, String status,
			Date startDate, Date endDate, Integer storeInIamdb) {
		this.attributeMapId = attributeMapId;
		this.managedSysId = managedSysId;
		this.resourceId = resourceId;
		this.mapForObjectType = mapForObjectType;
		this.attributeName = attributeName;
		this.targetAttributeName = targetAttributeName;
		this.authoritativeSrc = authoritativeSrc;
		this.rule = rule;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.storeInIamdb = storeInIamdb;
	}

	public String getAttributeMapId() {
		return this.attributeMapId;
	}

	public void setAttributeMapId(String attributeMapId) {
		this.attributeMapId = attributeMapId;
	}

	public String getManagedSysId() {
		return this.managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getMapForObjectType() {
		return this.mapForObjectType;
	}

	public void setMapForObjectType(String mapForObjectType) {
		this.mapForObjectType = mapForObjectType;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getTargetAttributeName() {
		return this.targetAttributeName;
	}

	public void setTargetAttributeName(String targetAttributeName) {
		this.targetAttributeName = targetAttributeName;
	}

	public Integer getAuthoritativeSrc() {
		return this.authoritativeSrc;
	}

	public void setAuthoritativeSrc(Integer authoritativeSrc) {
		this.authoritativeSrc = authoritativeSrc;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getStoreInIamdb() {
		return this.storeInIamdb;
	}

	public void setStoreInIamdb(Integer storeInIamdb) {
		this.storeInIamdb = storeInIamdb;
	}

	public Policy getAttributePolicy() {
		return attributePolicy;
	}

	public void setAttributePolicy(Policy attributePolicy) {
		this.attributePolicy = attributePolicy;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
