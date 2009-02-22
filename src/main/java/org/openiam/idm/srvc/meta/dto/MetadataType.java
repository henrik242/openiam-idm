package org.openiam.idm.srvc.meta.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import org.openiam.idm.srvc.cat.dto.Category;

import org.openiam.idm.srvc.user.dto.UserAttribute;

// Generated Nov 4, 2008 12:11:29 AM by Hibernate Tools 3.2.2.GA

/**
 * <code>MetadataType</code> represents a metdata type instance.
 */
public class MetadataType implements java.io.Serializable {

	private String metadataTypeId;
	private String description;
	


	private int active = 0;
	private int syncManagedSys = 0;
	
	
	protected Map<String, MetadataElement> elementAttributes = new HashMap<String, MetadataElement>(0);
	protected Set<Category> categories = new HashSet<Category>(0); 

	
	//List<MetadataElement> elementAttributes = new ArrayList<MetadataElement>(0);
	
	
	public MetadataType() {
	}

	public MetadataType(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public MetadataType(String metadataTypeId, String description) {
		this.metadataTypeId = metadataTypeId;
		this.description = description;
	}

	public String getMetadataTypeId() {
		return this.metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Map<String,MetadataElement> getElementAttributes() {
		return this.elementAttributes;
	}

	public void setElementAttributes(Map<String,MetadataElement> elementAttributes) {
		this.elementAttributes = elementAttributes;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/*
	public boolean isActive() {
		if (active == 0)
			return false;
		return true;
	}

*/

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	/**
	 * Flag indicating where the object should be synch'd with a managed system
	 * @return
	 */
/*
	public boolean isSyncManagedSys() {
		if ( syncManagedSys == 0)
			return false;
		return true;
	}
*/
	public int getSyncManagedSys() {
		return syncManagedSys;
	}

	/**
	 * Sets a flag indicating where the object should be synch'd with a managed system
	 * @param syncManagedSys
	 */
	public void setSyncManagedSys(int syncManagedSys) {
		this.syncManagedSys = syncManagedSys;
	}



}
