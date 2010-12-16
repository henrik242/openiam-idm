package org.openiam.base.property;


/**
 * <code>PropertyImpl</code> is a default implementation of the <code>Property</code>
 * interface.
 */

public class PropertyImpl implements Property {

	static final long serialVersionUID = 1;
	
protected boolean isModified = false;

protected String id = null;         // OID for this property
protected String name = null;
protected Object value  = null;     // enables you to store typed objects like Integer, Float,etc
protected Object parentKey = null;  // enables you to handle composite keys if they are used
                                    // by the parent object.
protected String metadataId = null;

protected boolean multivalued = false;

  public PropertyImpl() {
  }

  public boolean isModified() {
    return isModified;
  }
  /**
 * Gets the unique id of this object.
 * @return java.lang.String
 */
public String getId() {
  return id;
}
/**
 * Gets the name of this object.
 * @return java.lang.String
 */
public String getName() {
  return name;
}
/**
 *  Gets the key object of the parent or owning object, such as User, Company,
 *  Product.
 */
public Object getParentKey() {
  return parentKey;
}
/**
 * Gets the value of this component.
 */
public Object getValue() {
  return value;
}

public String getMetaDataId() {
  return this.metadataId;
}
/**
* Sets the unique id for this component.
* @return java.lang.String
*/
public void setId(String id) {
  this.id = id;
  this.isModified = true;
}
/**
 * Gets the name of this object.
 * @return java.lang.String
 */
public void setName(String name) {
  this.name = name;
  this.isModified = true;
}
/**
 *  Sets the Id of the parent or owning object, such as User, Company,
 *  Product.
 */
 public void setParentKey(Object id) {
  this.parentKey = id;
  this.isModified = true;
 }
/**
 * Sets the value of this component.
 */
 public void setValue(Object value) {
  this.value = value;
  this.isModified = true;
 }

 public void setMetaDataId(String id) {
  this.metadataId = id;
  this.isModified = true;
 }

 public String toString() {
  String str =  "id = " + id;
  str = str.concat(" name = " + name);
  if (parentKey == null)
    str = str.concat(" parenkey = null ");
  else
    str = str.concat("parentKey = " + parentKey.toString());
  if (value == null)
    str = str.concat(" value = null ");
  else
    str = str.concat("value = " + value.toString());
  return str;

 }
 /**
 * Returns the value of the property as a string. This method is useful when
 * the value is an <code>Object</code>.
 */
public String getValueAsString() {
  if (value == null)
	return null;

  return value.toString();
}

 public boolean isMultivalued() { return multivalued;  }
  }