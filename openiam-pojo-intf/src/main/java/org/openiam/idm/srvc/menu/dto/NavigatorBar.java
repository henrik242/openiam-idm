package org.openiam.idm.srvc.menu.dto;


import java.util.*;
import java.io.*;

/**
 * <p>
 * <code>NavigatorBar</code> <font face=arial>is responsible for keeping track of the path,
 * that the user has traversed.
 * </font>
 * </p>
 */

public class NavigatorBar implements Serializable {

  // navigator list.
  protected List navList = new ArrayList();

  public NavigatorBar() {
  }

  public NavigatorBar(List navList) {
    this.navList = navList;
  }

 /**
  * Gets the current path, traversed by the user, as a list of items.
  * @return the current path, traversed by the user in sequence, as a List of NavigatorBarItem objects.
  */
  public List getNavigatorList() {
    return navList;
  }


  public void add(String menuName, String url ) {
    NavigatorBarItem item = new NavigatorBarItem(menuName, url);
    if (contains(item))
      deleteSucceedingItems(item);
    else
      addItem(item);
  }

 /**
  * Sets the previous path, traversed by the user.
  * @param navList the previous path, traversed by the user.
  */
  public void setNavigatorList(List navList) {
    this.navList = navList;
  }

 /**
  * Adds an item to the list
  * @param navList the previous path, traversed by the user.
  */
  protected void addItem(NavigatorBarItem item) {
    navList.add(item);
  }

  //Remove items from the navList until the current item is the last item in the navList.
  protected void deleteSucceedingItems(NavigatorBarItem item) {
    while((navList.indexOf(item)) < ((navList.size())-1)) {
      navList.remove((navList.size())-1);
    }
  }

  //Checks whether the current item is present in the navList or not.
  protected boolean contains(NavigatorBarItem item) {
    return navList.contains(item);
  }

}