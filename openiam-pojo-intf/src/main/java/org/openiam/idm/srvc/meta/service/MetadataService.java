package org.openiam.idm.srvc.meta.service;


import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.dto.MetadataElement;

/**
 * Data service interface for Metadata. Metadata is used in OpenIAM to create
 * extend the capabilities of commonly used objects such as Users, Group,Role, 
 * Organizations, and Resources.
 * @author suneet
 * @version 1
 */

public interface MetadataService {

	  /**
	   * Creates a new Metadata Type.
	   *
	   * @param type MetadataType object.
	   */
	  MetadataType addMetadataType(MetadataType type);

	  /**
	  * Update Metadata type. Note that the typeId cannot be changed since it is the 
	  * primary key
	  *
	  * @param type MetadataType object.
	  */
	 MetadataType updateMetdataType(MetadataType type) ;
	  /**
	   * Deletes the MetadataType using Type Id.
	   * It also removes the association of MetadataType with
	   * Category and Metadata Element.
	   *
	   * @param typeId the Id that has to be removed.
	   */
	  void removeMetadataType(String typeId);

	  // Added by aps 11 Aug 01 due to database change. Type-Category methods now useless
	  /**
	   * Gets a map of all Metadata Types. Choosing from a Metadata Type is the first
	   * step in presenting a set of required attributes, and its constraints, for
	   * a data entry template for any attributes like Sku Attributes and Content
	   * Property.
	   *
	   * @return Map of MetaData Type as Key and Description as Values.
	   */
	  MetadataType[] getMetadataTypes();
	  /**
	   * Returns the metadataType and the metadataElements associated with it.
	   * @param typeId
	   * @return
	   */
	  MetadataType getMetadataType(String typeId);

	
	
	
	 /**
	   * Adds a MetadataElement to a MetadataType. MetadataId should be left unassigned
	   * as it will be automatically generated.
	   *
	   * @param metadataElement
	   */
	  MetadataElement addMetadataElement(MetadataElement metadataElement);
	  
	  
	/**
	   * Gets the MetadataElementValue values.
	   *
	   * @param elementId which uniquely identifies a metadata element. 
	   * @return MetadataElement.
	   * getMetadataElementById(String metadataId)
	   */

	  MetadataElement getMetadataElementById(String elementId);


	  /**
	   * Deletes the MetadataElement from a MetadataType
	   *
	   * @param metadataElementKey MetadataElementKey.
	   */
	  void removeMetadataElement(String elementId);

	  /**
	   * Gets the Metadata Element based on the Type Id.It also returns the MetadataOptions
	   * with the MetadataElements.
	   *
	   * @param typeId the MetadataType for which the MetadataElements are required.
	   * @return the Map which contains MetadataId as Key and MetadataElementValue
	   * objects as Values.
	   */
	  MetadataElement[] getMetadataElementByType(String typeId);

	   /**
	   * Updates the MetadataElement based on the MetadatElementKey and the values in
	   * the MetadataElementValue object.
	   *
	   * @param mv the Bulk Accessor with new set of values.
	   */
	  MetadataElement updateMetadataElement(MetadataElement mv);
	  
	  List<MetadataElement> getAllElementsForCategoryType(String categoryType) ;



	  /**************Methods added to handle Category-Type table.*************


	  /**
	   * Associates a MetadataType with a Category.
	   *
	   * @param typeId is the MetadataType that has to be associated with the Category.
	   * @param categoryId is the Category that has to be associated with the MetadataType.
	   */
	  void addTypeToCategory(String typeId,String categoryId) ;


	  /**
	   * Removes the association between a MetadataType and Category.
	   *
	   * @param typeId is the MetadataType that has to be disassociated with the Category.
	   * @param categoryId is the Category that has to be disassociated with the MetadataType.
	   */
	  void removeTypeFromCategory(String typeId,String categoryId);


	  /**
	   * Returns a list of MetadataTypes that are associated with a Category
	   *
	   * @param categoryId 
	   */
	  MetadataType[] getTypesInCategory(String categoryId);

}
