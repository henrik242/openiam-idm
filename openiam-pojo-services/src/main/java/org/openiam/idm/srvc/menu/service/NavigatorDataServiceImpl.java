package org.openiam.idm.srvc.menu.service;

//import diamelle.common.continfo.*;
//import diamelle.base.prop.*;

import java.util.*;
import java.rmi.*;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.dto.MenuId;

/**
 * <code>NavigatorDataServiceImpl</code> provides a service implementation to deliver the application
 * menu options that a user may be entititled to. NavigatorDataServiceImpl also provides basic 
 * data level functionality <br>
 * 
 * 
 * <B>Examples</B>
 * 
 * @author Suneet Shah
 * @version 2
 */
public class NavigatorDataServiceImpl implements NavigatorDataService{

	NavigatorDAO navigatorDao;
	
	public void addMenu(Menu data) {
		  if (data == null)
			   throw new NullPointerException("Menu object is null");
		  if (data.getId() == null)
			  throw new NullPointerException("Menu id is null");
		  
		  navigatorDao.add(data);	 
		
	}

	/**
	 * Returns a <code>String</code> of MenuIds for all the menus in the
	 * parentMenuGroupId hierarchy. This includes menu options found several levels
	 * deep in the hierarchy. The String of Ids is comma separated.
	 * @param parentMenuGroupId
	 * @return String
	 */
	public String getAllMenuOptionIDs(String parentMenuGroupId, String languageCd) {
		StringBuffer allMenuIds = new StringBuffer();
		  allMenuIds.append( String.valueOf(parentMenuGroupId) );
		  String currentMenuId = allMenuIds.toString();
		  while (currentMenuId != null ) {
			 //   currentMenuId = getMenuIdString(currentMenuId, languageCd);
			 //   if (currentMenuId != null) {
			//      allMenuIds.append("," + currentMenuId);
			//    }
		  }
		  return allMenuIds.toString();

	}

	
	
	public String getAllMenuOptionIDs(String parentMenuGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Menu> getAllSubMenu(String parentMenuGroupId,
			String languageCd) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Menu> getAllSubMenu(String parentMenuGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Menu> getInitialMenuOptions(String userId, String languageCd) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Menu> getInitialMenuOptions(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Menu getMenu(String menuId, String languageCd) {
		  if (menuId == null)
			   throw new NullPointerException("MenuId  is null");
		
		  Menu m = new Menu();
		  MenuId id = new MenuId();
		  id.setMenuId(menuId);
		  id.setLanguageCd(languageCd);
		  m.setId(id);
		  return navigatorDao.findById(id);

	}

	public Menu getMenu(String menuId) {
		return getMenu(menuId, "en");

	}

	public List<Menu> getMenuGroup(String menuGroup, String langCd) {
		if (menuGroup == null)
			   throw new NullPointerException("menuGroup  is null");
		if (langCd == null)
			   throw new NullPointerException("languageCd  is null");
		
		return navigatorDao.findByMenuGroup(menuGroup, langCd);
	}

	public List<Menu> getMenuGroup(String menuGroup) {
		return getMenuGroup(menuGroup, "en");
	}

	public List<Menu> getMenuGroupByUser(String menuGroupId, String userId,
			String languageCd) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Menu> getMenuGroupByUser(String menuGroupId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeMenu(String menuId, boolean deleteChildren) {
		  if (menuId == null)
			   throw new NullPointerException("MenuId  is null");
		
		  Menu m = new Menu();
		  MenuId id = new MenuId();
		  id.setMenuId(menuId);
		  m.setId(id);
		  navigatorDao.remove(m);
		  
	}

	public void updateMenu(Menu data) {
		  if (data == null)
			   throw new NullPointerException("Menu object is null");
		  if (data.getId() == null)
			  throw new NullPointerException("Menu id is null");
		  
		  navigatorDao.update(data);	 
		
	}

	public NavigatorDAO getNavigatorDao() {
		return navigatorDao;
	}

	public void setNavigatorDao(NavigatorDAO navigatorDao) {
		this.navigatorDao = navigatorDao;
	}


}
