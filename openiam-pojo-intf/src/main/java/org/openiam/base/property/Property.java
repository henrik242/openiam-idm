package org.openiam.base.property;


/**
 * <code>Property</code> class represents an attribute row of a
 * property container.  A property can be added to any object
 * implementing the property container interface with the class having any knowledge
 * of the property or requiring any interface or code change to accomodate the addition
 * of that property. <code>Property</code> may be extended to include other methods fields
 * and methods and still maintain compatibility with related property containers.
 * A typical Attribute table could consist of an attributeId, fields mapping to
 * the parent key and fields mapping to an object. Metadata Id may or may not
 * exist. If it does exist, it provides a join to show more fields of a property,
 * as well as constraints, and contain names of fields for display.
 */

public interface Property extends java.io.Serializable {
/**
 * Gets the unique id of this object.
 * @return java.lang.String
 */
public String getId();
/**
 * Gets the name of this object.
 * @return java.lang.String
 */
public String getName();
/**
 *  Gets the key object of the parent or owning object, such as User, Company,
 *  Product.
 */
public Object getParentKey();
/**
 * Gets the value of this component.
 */
public Object getValue();
/**
 * Returns the value of the property as a string. This method is useful when
 * the value is an <code>Object</code>.
 */
public String getValueAsString();
/**
* Sets the unique id for this component.
* @return java.lang.String
*/
public void setId(String id);
/**
 * Gets the name of this object.
 * @return java.lang.String
 */
public void setName(String name);
/**
 *  Sets the Id of the parent or owning object, such as User, Company,
 *  Product.
 */
 public void setParentKey(Object id);
/**
 * Sets the value of this component.
 */
 public void setValue(Object value);
 /**
  * Indicates if the values in this property were changed since the last time they were
  * saved in the database.
  */
 public boolean isModified();
 /**
  * Returns the MetaData Associated with this object.
  */
 public String getMetaDataId();
 public void setMetaDataId(String val);

 /**
  * <p>
  * <code>isMultivalued</code><font face="arial"> indicates is this is a
  * <code>MultiValuedProperty</code>.
  * </font><p>
  */

 public boolean isMultivalued();
}