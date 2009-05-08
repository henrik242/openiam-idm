package org.openiam.webadmin.metadata;
/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */


import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Command object for the ManagedSystemList 
 * @author suneet
 *
 */
public class MetadataAttributeCommand implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 5730162333111454215L;
	
	private MetadataElement[] elementAry;
	
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
	

	public MetadataAttributeCommand() {
    	
    }
	
	public MetadataAttributeCommand(MetadataElement[] elementAry) {
		super();
		this.elementAry = elementAry;
	}


	
	
	public void setElements(String attributeName, Integer auditable,
			String dataType, String defaultValue, String description,
			String label, Integer maxLen,
			Long maxValue, String metadataElementId, String metadataTypeId,
			Integer minLen, Long minValue, String multiValue, Integer required,
			Integer selfEditable, Integer selfViewable, String textCase,
			String uiSize, String uiType, String valueList, String valueSrc) {

		this.attributeName = attributeName;
		this.auditable = auditable;
		this.dataType = dataType;
		this.defaultValue = defaultValue;
		this.description = description;
		this.label = label;
		this.maxLen = maxLen;
		this.maxValue = maxValue;
		this.metadataElementId = metadataElementId;
		this.metadataTypeId = metadataTypeId;
		this.minLen = minLen;
		this.minValue = minValue;
		this.multiValue = multiValue;
		this.required = required;
		this.selfEditable = selfEditable;
		this.selfViewable = selfViewable;
		this.textCase = textCase;
		this.uiSize = uiSize;
		this.uiType = uiType;
		this.valueList = valueList;
		this.valueSrc = valueSrc;
	}

	public MetadataElement[] getElementAry() {
		return elementAry;
	}


	public void setElementAry(MetadataElement[] elementAry) {
		this.elementAry = elementAry;
	}

	public String getMetadataElementId() {
		return metadataElementId;
	}

	public void setMetadataElementId(String metadataElementId) {
		this.metadataElementId = metadataElementId;
	}

	public String getMetadataTypeId() {
		return metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMinLen() {
		return minLen;
	}

	public void setMinLen(Integer minLen) {
		this.minLen = minLen;
	}

	public Integer getMaxLen() {
		return maxLen;
	}

	public void setMaxLen(Integer maxLen) {
		this.maxLen = maxLen;
	}

	public String getTextCase() {
		return textCase;
	}

	public void setTextCase(String textCase) {
		this.textCase = textCase;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getMinValue() {
		return minValue;
	}

	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}

	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValueList() {
		return valueList;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMultiValue() {
		return multiValue;
	}

	public void setMultiValue(String multiValue) {
		this.multiValue = multiValue;
	}

	public Integer getAuditable() {
		return auditable;
	}

	public void setAuditable(Integer auditable) {
		this.auditable = auditable;
	}

	public Integer getRequired() {
		return required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	public Integer getSelfEditable() {
		return selfEditable;
	}

	public void setSelfEditable(Integer selfEditable) {
		this.selfEditable = selfEditable;
	}

	public Integer getSelfViewable() {
		return selfViewable;
	}

	public void setSelfViewable(Integer selfViewable) {
		this.selfViewable = selfViewable;
	}

	public String getUiType() {
		return uiType;
	}

	public void setUiType(String uiType) {
		this.uiType = uiType;
	}

	public String getUiSize() {
		return uiSize;
	}

	public void setUiSize(String uiSize) {
		this.uiSize = uiSize;
	}

	public String getValueSrc() {
		return valueSrc;
	}

	public void setValueSrc(String valueSrc) {
		this.valueSrc = valueSrc;
	}




	

}
