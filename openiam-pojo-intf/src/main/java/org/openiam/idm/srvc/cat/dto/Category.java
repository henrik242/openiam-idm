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
@XmlType(name = "category", propOrder = {
    "categoryId",
    "createdBy",
    "categoryName",
    "categoryDesc",
    "createDate",
    "parentId",
    "showList",
    "displayOrder",
    "childCategories",
    "categoryLanguages"
})
public class Category implements Serializable {
	private String categoryId;
	private String createdBy;
	private String categoryName;
	private String categoryDesc;
	@XmlSchemaType(name = "dateTime")
	private Date createDate;
	private String parentId;
	private int showList;
	private int displayOrder;
	private Category[] childCategories;
	private Set<CategoryLanguage> categoryLanguages = new HashSet<CategoryLanguage>(0);
	static final long serialVersionUID = 7480627520054050204L;
	
	public Category() {
		super();
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getShowList() {
		return this.showList;
	}

	public void setShowList(int showList) {
		this.showList = showList;
	}

	public int getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}



	public Set<CategoryLanguage> getCategoryLanguages() {
		return categoryLanguages;
	}

	public void setCategoryLanguages(Set<CategoryLanguage> categoryLanguages) {
		this.categoryLanguages = categoryLanguages;
	}

	public Category[] getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Category[] childCategories) {
		this.childCategories = childCategories;
	}

}
