package org.openiam.idm.srvc.cat.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.openiam.idm.srvc.cat.dto.CategoryLanguage;


public class Category implements Serializable {
	private String categoryId;

	private String createdBy;
	private String categoryName;
	private String categoryDesc;
	private Timestamp createDate;
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

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
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
