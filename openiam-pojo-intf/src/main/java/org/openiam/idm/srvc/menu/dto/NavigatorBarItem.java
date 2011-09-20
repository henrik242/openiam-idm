package org.openiam.idm.srvc.menu.dto;


import java.io.Serializable;


/**
 * <p>
 * <code>NavigatorBarItem</code> <font face=arial>represents the item, say Menu, Category etc.
 * This object is added to the navigator list, to trace the path of the user.
 * </font>
 * </p>
 */

public class NavigatorBarItem implements Serializable {

  //Name of the item.(like Menu, Category etc)
  private String menuName;

  //the url of the item.
  private String url;

 /**
  * This is the default constructor of NavigatorBarItem.
  */
  public NavigatorBarItem() {
  }

 /**
  * This is the parameterised constructor of NavigatorBarItem.
  *
  * @param menuName the name of the item.
  * @param url the url of the item.
  */
  public NavigatorBarItem(String menuName, String url) {
    this.menuName = menuName;
    this.url = url;
  }

 /**
  * Sets the name of the item.
  *
  * @param menuName the name of the item.
  */
  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

 /**
  * Gets the name of the item.
  *
  * @return the name of the item.
  */
  public String getMenuName() {
    return menuName;
  }

 /**
  * Sets the url of the item.
  *
  * @param url the url of the item.
  */
  public void setUrl(String url) {
    this.url = url;
  }

 /**
  * Gets the url of the item.
  *
  * @return the url of the item.
  */
  public String getUrl() {
    return url;
  }


 /**
  * Checks this Object with any other NavigatorBarItem Object for equality.
  * @param object The object to be compared with this object.
  * @return boolean The boolean flag indicating whether the comparison was successful or not.
  */
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (object instanceof NavigatorBarItem) {
      NavigatorBarItem item = (NavigatorBarItem)object;
      return (item.menuName).equals(this.menuName);
    }
    else {
      return false;
    }
  }

 /**
  * Returns a hashcode value for the primary key(menuName) of NavigatorBarItem.
  * @return int The hashcode of menuName(primary key of NavigatorBarItem).
  */
  public int hashCode() {
    return menuName.hashCode();
  }


 /**
  * Returns the String representation of all the fields of the NavigatorBarItem object.
  * @return String The String representation of all the fields in the NavigatorBarItem object.
  */
  public String toString(){
    return " menuName = "+ menuName +
    " url = "+ url +
    "\n";
  }




}