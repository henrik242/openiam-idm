package org.openiam.idm.srvc.meta.dto;

// Generated Nov 4, 2008 12:11:29 AM by Hibernate Tools 3.2.2.GA

/**
 * <code>MetadataElement</code> represents an attribute of MetadataType.
 * MetadataElement also contains parameters that define validation constraints.
 */
public class MetadataElement implements java.io.Serializable {

	private String metadataElementId;
	private String metadataTypeId;
	private String attributeName;
	private String description;
	private Integer minLen;
	private Integer maxLen;
	private String textCase;
	private String dataType;
	private Long minValue;
	private Long maxValue;
	private String defaultValue;
	private String valueList;
	private String label;
	private String multiValue;
	private Integer auditable;
	private Integer required;
	
	private Integer selfEditable;
	private Integer selfViewable;

	private String uiType;
	private String uiSize;
	private String valueSrc;

	public MetadataElement() {
	}

	public MetadataElement(String metadataId) {
		this.metadataElementId = metadataId;
	}

	public MetadataElement(String metadataId, String typeId,
			String attributeName, String description, Integer minLen,
			Integer maxLen, String textCase, String dataType, Long minValue,
			Long maxValue, String defaultValue, String valueList, String label,
			String multiValue, Integer auditable, Integer required) {
		this.metadataElementId = metadataId;
		this.metadataTypeId = typeId;
		this.attributeName = attributeName;
		this.description = description;
		this.minLen = minLen;
		this.maxLen = maxLen;
		this.textCase = textCase;
		this.dataType = dataType;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.defaultValue = defaultValue;
		this.valueList = valueList;
		this.label = label;
		this.multiValue = multiValue;
		this.auditable = auditable;
		this.required = required;
	}

	public String getMetadataElementId() {
		return this.metadataElementId;
	}

	public void setMetadataElementId(String metadataId) {
		this.metadataElementId = metadataId;
	}

	public String getMetadataTypeId() {
		return this.metadataTypeId;
	}

	public void setMetadataTypeId(String typeId) {
		this.metadataTypeId = typeId;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMinLen() {
		return this.minLen;
	}

	public void setMinLen(Integer minLen) {
		this.minLen = minLen;
	}

	public Integer getMaxLen() {
		return this.maxLen;
	}

	public void setMaxLen(Integer maxLen) {
		this.maxLen = maxLen;
	}

	public String getTextCase() {
		return this.textCase;
	}

	public void setTextCase(String cs) {
		this.textCase = cs;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getMinValue() {
		return this.minValue;
	}

	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}

	public Long getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValueList() {
		return this.valueList;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMultiValue() {
		return this.multiValue;
	}

	public void setMultiValue(String multiValue) {
		this.multiValue = multiValue;
	}

	public Integer getAuditable() {
		return this.auditable;
	}

	public void setAuditable(Integer auditable) {
		this.auditable = auditable;
	}

	public Integer getRequired() {
		return this.required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}
	
	/**
	 * Flag indicating whether the user owning this record can edit this field
	 * @return
	 */
	public Integer isSelfEditable() {
		return selfEditable;
	}

	public void setSelfEditable(Integer selfEditable) {
		this.selfEditable = selfEditable;
	}
	/**
	 * Flag indicating whether the user owning this record can view this field.
	 * @return
	 */
	public Integer isSelfViewable() {
		return selfViewable;
	}

	public void setSelfViewable(Integer selfViewable) {
		this.selfViewable = selfViewable;
	}

	public String getUiType() {
		return uiType;
	}

	/**
	 * Sets the type of UI object to use when rendering this element.
	 * @param uiType
	 */
	public void setUiType(String uiType) {
		this.uiType = uiType;
	}

	public String getValueSrc() {
		return valueSrc;
	}
	
	/**
	 * Sets a rule or script that will handle retrieving the list data for this element. Typically used
	 * when working with a list that needs to be dynamically populated.
	 * @param valueSrc
	 */
	public void setValueSrc(String valueSrc) {
		this.valueSrc = valueSrc;
	}

	/**
	 * Defines the size related information that is to be used for the UI
	 * @return
	 */
	public String getUiSize() {
		return uiSize;
	}

	public void setUiSize(String uiSize) {
		this.uiSize = uiSize;
	}

}
