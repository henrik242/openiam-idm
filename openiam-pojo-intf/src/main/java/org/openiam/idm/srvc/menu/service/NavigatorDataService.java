package org.openiam.idm.srvc.menu.service;

import org.openiam.idm.srvc.menu.dto.*;
import org.openiam.idm.srvc.role.dto.Role;

import java.util.*;


/**
 * Interface of the <code>NavigatorDataService</code>.
 * <p>
 *  The <code>NavigatorDataService</code> is used to provide content for a dynamic menuing system.
 *  The menu options are typically generated based on the groups and roles that a user
 *  belongs to.  The NavigatorDataService also supports the
 *  notion of a guest mode as would typically be found in a public site to show menus that
 *  are not linked to a group or role.</p>
 *  <p>
 *  <b>Multilanguage Support:</b><br>
 *  Menu options also contain a language indicator, allowing you display the menu text in
 *  an unlimited number of languages as well provide different responses to each option.
 *  The interface to the Navigator contains 2 sets of methods - Those that accept a
 *  language code and those which do not. In most cases, where a language code
 *  is not a parameter, the default language code from the deployment descriptor is used.
 *  </p>
	<b>Examples:</b>Generate the top level menu for user that has logged in<br>
	<pre>

	</pre>

	 <b>Examples:</b>Generate a Sub Menu:<br>
	 To get a sub-menu call either of the methods below with a menu id that was
	 obtained from the list returned by getInitialMenuOptions or an earlier call
	 to getMenuGroup.
	<pre>
	 nav.getMenuGroup(menuid);
		or
	 nav.getMenuGroupByUser(menuid, user_id);
	</pre>
	
	<b>Examples:</b>Iterate through a list of menu options:<br>
	<pre>
	  list = nav.getMenuGroup(menuid);
	    it = list.listIterator();
	  while (it.hasNext()) {
		menu = (Menu)it.next();
	  }
   </pre>

*
*/
public interface NavigatorDataService {



	/**
	 * Returns a list of valid menu options for the requested menu group without
	 * taking the user into account. This mode is typically used in public or
	 * anonymous mode where the user id is not known. Language code defaults to
	 * the default language code in the deployment descriptor.
	 * 
	 * @param menuGroupId
	 *            The menu group for which you are trying to locate the options
	 * @param langCd
	 *            the lang cd
	 * @return java.util.List
	 */
	List<Menu> menuGroup(String menuGroupId, String langCd);
	

	/**
	 * Returns a list of valid menu options for the requested menu group for the
	 * user. Takes into account a users role
	 * 
	 * @param menuGroupId
	 *            the menu group id
	 * @param userId
	 *            the user id
	 * @param languageCd
	 *            - The language for which the menus are to be selected.
	 *            Defaults to "en" if null
	 * @return the list
	 * @returns array A list containing valid menu options.
	 */
	List<Menu> menuGroupByUser(String menuGroupId, java.lang.String userId, String languageCd) ;


	/**
	 * Returns list of all menus in a menuGroup. For menu items that a user is
	 * allowed to access, based on their role, will have the "selected" flag set
	 * to true.
	 * 
	 * @param menuGroupId
	 *            the menu group id
	 * @param userId
	 *            the user id
	 * @param languageCd
	 *            the language cd
	 * @return the list
	 */
	List<Menu> menuGroupSelectedByUser(String menuGroupId, java.lang.String userId, String languageCd);
	




	/**
	 * Returns a <code>String</code> of MenuIds for all the menus in the
	 * parentMenuGroupId hierarchy. This includes menu options found several
	 * level deep in the hierarchy. The String of Ids is comma separated.
	 * 
	 * @param parentMenuGroupId
	 *            the parent menu group id
	 * @param languageCd
	 *            the language cd
	 * @return String
	 */
	String getAllMenuOptionIDs(String parentMenuGroupId, String languageCd);



	/**
	 * Adds a new menu option.
	 * 
	 * @param data
	 *            the data
	 * @return the menu
	 */
	Menu addMenu(Menu data);

	/**
	 * Gets a Menu object for the menuId and languageCd passed in.
	 * 
	 * @param menuId
	 *            the menu id
	 * @param languageCd
	 *            the language cd
	 * @return Menu
	 */
	Menu getMenu(String menuId, String languageCd);

	/**
	 * Update an existing menu option.
	 * 
	 * @param data
	 *            the data
	 */
	void updateMenu(Menu data);
	
	/**
	 * Removes a menu option. The deleteChildren parameter is set to true, then
	 * the service will delete all child menu options.
	 * 
	 * @param menuId
	 *            the menu id
	 * @param deleteChildren
	 *            the delete children
	 * @return the int
	 */
	public int removeMenu(String menuId, boolean deleteChildren);

	
	/**
	 * Adds the permission.
	 * 
	 * @param menuId
	 *            the menu id
	 * @param roleId
	 *            the role id
	 * @param serviceId
	 *            the service id
	 * @return the permission
	 */
	Permission addPermission(String menuId, String roleId, String serviceId);

	/**
	 * Updates permission.
	 * 
	 * @param permission
	 *            the permission
	 * @return the permission
	 */
	Permission updatePermission(Permission permission);

	/**
	 * Gets the permission.
	 * 
	 * @param menuId
	 *            the menu id
	 * @param roleId
	 *            the role id
	 * @param serviceId
	 *            the service id
	 * @return the permission
	 */
	Permission getPermission(String menuId, String roleId, String serviceId);

	/**
	 * Gets the all permissions.
	 * 
	 * @return the all permissions
	 */
	List<Permission> getAllPermissions();

	/**
	 * Removes the permission.
	 * 
	 * @param menuId
	 *            the menu id
	 * @param roleId
	 *            the role id
	 * @param serviceId
	 *            the service id
	 */
	void removePermission(String menuId, String roleId, String serviceId);

	/**
	 * Removes the all permissions.
	 * 
	 * @return the int
	 */
	int removeAllPermissions();

	/**
	 * Gets the roles by menu.
	 * 
	 * @param menuId
	 *            the menu id
	 * @return the roles by menu
	 */
	List<Role> getRolesByMenu(String menuId);

	/**
	 * Gets the menus by role.
	 * 
	 * @param roleId
	 *            the role id
	 * @param serviceId
	 *            the service id
	 * @return the menus by role
	 */
	List<Menu> getMenusByRole(String roleId, String serviceId);

	/**
	 * Finds menus by user.
	 * 
	 * @param menuGroup
	 *            the menu group
	 * @param roleId
	 *            the role id
	 * @param userId
	 *            the user id
	 * @return the list
	 */
	List<Menu> getMenusByUser(String menuGroup, String roleId, String userId);
	/**
	 * Find a menu and its descendants and return as a flat List.
	 * 
	 * @param menuId
	 * @param languageCd
	 * 
	 * @return a flat list of menu objects
	 */
	List<Menu> getMenuFamily(String menuId, String languageCd);

	/**
	 * Find a menu and its descendants and return as nested List.
	 * 
	 * @param menuId
	 * @param languageCd
	 * 
	 * @return list of nested lists of menu objects
	 */
	List<Menu> getMenuTree(String menuId, String languageCd);
	
	
}
