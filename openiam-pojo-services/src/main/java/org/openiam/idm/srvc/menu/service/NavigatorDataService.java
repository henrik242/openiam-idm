package org.openiam.idm.srvc.menu.service;

import org.openiam.idm.srvc.menu.dto.*;
import java.util.*;

import javax.jws.WebService;

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
@WebService
public interface NavigatorDataService {



	/**
	  * Returns a top level menu for the user specified by userId. UserId is available
	  * through the UserWebDataService.  This also implies that the user is known to the systems.
	  * @param  userId      -The user cd for which you are trying to find valid menu options
	  * @param languageCd   -The language in which the menu information is to be retrieved.
	  * @returns  List containing valid menu options.
	 */
	List<Menu> getInitialMenuOptions(java.lang.String userId, String languageCd);

	/**
	  * Returns a list of valid menu options for the requested menu group without taking the user into 
	  * account.  This mode is typically used in public or anonymous mode where the user id is not known.
	  * Language code defaults to the default language code in the deployment descriptor.
	  * @return java.util.List
	 * @param menuGroupId The menu group for which you are trying to locate the options
	 * @param languageCd -The language in which the menu information is to be retrieved.  Defaults to 'en' if null
	 */
	Menu[] menuGroup(String menuGroupId, String langCd);
	

	/**
	  * Returns a list of valid menu options for the requested menu group for the user. Takes into account a users role
	  * @param  menuGroupId The menu group for which you are trying to locate the options
	  * @param  userId    The user cd for which you are trying to find valid menu options
	  * @param languageCd   - The language for which the menus are to be selected. Defaults to "en" if null
	  * @returns  array A list containing valid menu options.
	  */
	Menu[] menuGroupByUser(String menuGroupId, java.lang.String userId, String languageCd) ;


	/**
	 * Returns an array of all menus in a menuGroup. For menu items that a user is allowed to access,
	 * based on their role, will have the "selected" flag set to true. 
	 * @param menuGroupId
	 * @param userId
	 * @param languageCd
	 * @return
	 */
	Menu[] menuGroupSelectedByUser(String menuGroupId, java.lang.String userId, String languageCd);
	




	/**
	 * Returns a <code>String</code> of MenuIds for all the menus in the
	 * parentMenuGroupId hierarchy. This includes menu options found several level deep in the
	 * hierarchy. The String of Ids is comma separated.
	 * @param parentMenuGroupId
	 * @param languageCd
	 * @return String
	 */
	String getAllMenuOptionIDs(String parentMenuGroupId, String languageCd);


	/**
	 * Returns a Collection of Menu objects for all the menus in the
	 * parentMenuGroupId hierachy. This includes menu options found several levels deep in the
	 * hiearchy.
	 * @param parentMenuGroupId
	 * @param languageCd
	 * @return Collection of Menu objects
	 *
	 */
	Collection<Menu> getAllSubMenu(String parentMenuGroupId, String languageCd);

	/**
	 * Adds a new menu option
	 * @param data
	 */
	public void addMenu(Menu data);

	/**
	 * Gets a Menu object for the menuId and languageCd passed in
	 * @param id - menuId
	 * @param languageCd
	 * @return Menu
	 */
	Menu getMenu(String menuId, String languageCd);

	/**
	 * Update an existing menu option
	 * @param data
	 */
	public void updateMenu(Menu data);
	/**
	 * Removes a menu option.  The deleteChildren parameter is set to true, then the service will delete all 
	 * child menu options.
	 * @param menuId
	 * @param deleteChildren
	 */
	public void removeMenu(String menuId, boolean deleteChildren);
	
	
}
