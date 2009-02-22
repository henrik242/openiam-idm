package org.openiam.idm.srvc.cat.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openiam.idm.srvc.cat.dto.CategoryLanguageId;


public class CategoryLanguage implements Serializable {

	
	private CategoryLanguageId id;
	private String categoryName;
	//private String categoryId;

	static final long serialVersionUID = -6948749895519617508L;

	public CategoryLanguage() {
		super();
	}
	
	public CategoryLanguage(CategoryLanguageId id, String categoryName) {
		super();
		this.categoryName = categoryName;
		this.id = id;
	}
	
	



	public CategoryLanguageId getId() {
		return id;
	}



	public void setId(CategoryLanguageId id) {
		this.id = id;
	}



	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




	

}
