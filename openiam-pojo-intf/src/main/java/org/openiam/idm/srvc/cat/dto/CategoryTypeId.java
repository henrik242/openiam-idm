package org.openiam.idm.srvc.cat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.idm.srvc.cat.dto.CategoryLanguage;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryTypeId", propOrder = {
    "categoryId",
    "typeId"
})
public class CategoryTypeId implements Serializable {
	private String categoryId;
	private String typeId;

	
	public CategoryTypeId() {
		super();
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


}
