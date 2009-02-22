package org.openiam.idm.srvc.menu.dto;
// Generated Dec 2, 2007 5:41:37 PM by Hibernate Tools 3.2.0.b11



/**
 * Menu domain object.  This object is used to transfer data between the service layer
 * and the client layer.
 */
public class Menu  implements java.io.Serializable {


     private MenuId id;
     private String menuGroup;
     private String menuName;
     private String menuDesc;
     private String url;
     private Boolean active;
     private Byte displayOrder;
     private Menu[] subMenus;

     /**
      * Returns an array of Menu objects
      * @return
      */
    public Menu[] getSubMenus() {
		return subMenus;
	}

    /**
     * Assigns an array of Menu objects as children of the current menu.
     * @param subMenus
     */

	public void setSubMenus(Menu[] subMenus) {
		this.subMenus = subMenus;
	}


	public Menu() {
    }

	
    public Menu(MenuId id) {
        this.id = id;
    }
    public Menu(MenuId id, String menuGroup, String menuName, String menuDesc, String url, Boolean active, Byte displayOrder) {
       this.id = id;
       this.menuGroup = menuGroup;
       this.menuName = menuName;
       this.menuDesc = menuDesc;
       this.url = url;
       this.active = active;
       this.displayOrder = displayOrder;
    }
   
    public MenuId getId() {
        return this.id;
    }
    
    public void setId(MenuId id) {
        this.id = id;
    }
    public String getMenuGroup() {
        return this.menuGroup;
    }
    
    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }
    public String getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuDesc() {
        return this.menuDesc;
    }
    
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Byte getDisplayOrder() {
        return this.displayOrder;
    }
    
    public void setDisplayOrder(Byte displayOrder) {
        this.displayOrder = displayOrder;
    }




}


