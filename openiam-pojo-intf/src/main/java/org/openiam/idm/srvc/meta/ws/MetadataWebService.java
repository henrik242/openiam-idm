package org.openiam.idm.srvc.meta.ws;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.dto.MetadataElement;

/**
 * Web service interface for Metadata. Metadata is used in OpenIAM to create
 * extend the capabilities of commonly used objects such as Users, Group,Role, 
 * Organizations, and Resources.
 * @author suneet
 * @version 2.1
 */
@WebService
public interface MetadataWebService {

	  /**
	   * Creates a new Metadata Type.
	   *
	   * @param type MetadataType object.
	   */
	@WebMethod
	  MetadataTypeResponse addMetadataType(
			  @WebParam(name = "type", targetNamespace = "")
			  MetadataType type);

	  /**
	  * Update Metadata type. Note that the typeId cannot be changed since it is the 
	  * primary key
	  *
	  * @param type MetadataType object.
	  */
	@WebMethod
	MetadataTypeResponse updateMetdataType(
			 @WebParam(name = "type", targetNamespace = "")
			 MetadataType type) ;
	  /**
	   * Deletes the MetadataType using Type Id.
	   * It also removes the association of MetadataType with
	   * Category and Metadata Element.
	   *
	   * @param typeId the Id that has to be removed.
	   */
	@WebMethod
	  Response removeMetadataType(
			  @WebParam(name = "typeId", targetNamespace = "")
			  String typeId);

	  // Added by aps 11 Aug 01 due to database change. Type-Category methods now useless
	  /**
	   * Gets a map of all Metadata Types. Choosing from a Metadata Type is the first
	   * step in presenting a set of required attributes, and its constraints, for
	   * a data entry template for any attributes like Sku Attributes and Content
	   * Property.
	   *
	   * @return Map of MetaData Type as Key and Description as Values.
	   */
	@WebMethod
	  MetadataTypeArrayResponse getMetadataTypes();
	  /**
	   * Returns the metadataType and the metadataElements associated with it.
	   * @param typeId
	   * @return
	   */
	@WebMethod
	MetadataTypeResponse getMetadataType(
			  @WebParam(name = "typeId", targetNamespace = "")
			  String typeId);

	
	
	
	 /**
	   * Adds a MetadataElement to a MetadataType. MetadataId should be left unassigned
	   * as it will be automatically generated.
	   *
	   * @param metadataElement
	   */
	@WebMethod
	  MetadataElementResponse addMetadataElement(
			  @WebParam(name = "metadataElement", targetNamespace = "")
			  MetadataElement metadataElement);
	  
	  
	/**
	   * Gets the MetadataElementValue values.
	   *
	   * @param elementId which uniquely identifies a metadata element. 
	   * @return MetadataElement.
	   * getMetadataElementById(String metadataId)
	   */
	@WebMethod
	MetadataElementResponse getMetadataElementById(
			  @WebParam(name = "elementId", targetNamespace = "")
			  String elementId);


	  /**
	   * Deletes the MetadataElement from a MetadataType
	   *
	   * @param metadataElementKey MetadataElementKey.
	   */
	@WebMethod
	  Response removeMetadataElement(
			  @WebParam(name = "elementId", targetNamespace = "")
			  String elementId);

	  /**
	   * Gets the Metadata Element based on the Type Id.It also returns the MetadataOptions
	   * with the MetadataElements.
	   *
	   * @param typeId the MetadataType for which the MetadataElements are required.
	   * @return the Map which contains MetadataId as Key and MetadataElementValue
	   * objects as Values.
	   */
	@WebMethod
	  MetadataElementArrayResponse getMetadataElementByType(
			  @WebParam(name = "typeId", targetNamespace = "")
			  String typeId);

	   /**
	   * Updates the MetadataElement based on the MetadatElementKey and the values in
	   * the MetadataElementValue object.
	   *
	   * @param mv the Bulk Accessor with new set of values.
	   */
	@WebMethod
	MetadataElementResponse updateMetadataElement(
			  @WebParam(name = "element", targetNamespace = "")
			  MetadataElement element);



	  /**************Methods added to handle Category-Type table.*************


	  /**
	   * Associates a MetadataType with a Category.
	   *
	   * @param typeId is the MetadataType that has to be associated with the Category.
	   * @param categoryId is the Category that has to be associated with the MetadataType.
	   */
	@WebMethod
	  Response addTypeToCategory(
			  @WebParam(name = "typeId", targetNamespace = "")
			  String typeId,
			  @WebParam(name = "categoryId", targetNamespace = "")
			  String categoryId) ;


	  /**
	   * Removes the association between a MetadataType and Category.
	   *
	   * @param typeId is the MetadataType that has to be disassociated with the Category.
	   * @param categoryId is the Category that has to be disassociated with the MetadataType.
	   */
	@WebMethod
	  Response removeTypeFromCategory(
			  @WebParam(name = "typeId", targetNamespace = "")
			  String typeId,
			  @WebParam(name = "categoryId", targetNamespace = "")
			  String categoryId);


	  /**
	   * Returns a list of MetadataTypes that are associated with a Category
	   *
	   * @param categoryId 
	   */
	@WebMethod
	MetadataTypeArrayResponse getTypesInCategory(
			  @WebParam(name = "categoryId", targetNamespace = "")
			  String categoryId);
	
	@WebMethod
	MetadataElementArrayResponse getAllElementsForCategoryType(
			  @WebParam(name = "categoryType", targetNamespace = "")
			  String categoryType) ;

}
