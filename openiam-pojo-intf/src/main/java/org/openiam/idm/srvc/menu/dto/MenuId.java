package org.openiam.idm.srvc.menu.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
// Generated Dec 2, 2007 5:41:37 PM by Hibernate Tools 3.2.0.b11



/**
 * PrimaryKey object for Menu
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MenuId", propOrder = {
    "menuId",
    "languageCd"
})
public class MenuId  implements java.io.Serializable {


     private String menuId;
     private String languageCd;

    public MenuId() {
    }

    public MenuId(String menuId, String languageCd) {
       this.menuId = menuId;
       this.languageCd = languageCd;
    }
   
    public String getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public String getLanguageCd() {
        return this.languageCd;
    }
    
    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MenuId) ) return false;
		 MenuId castOther = ( MenuId ) other; 
         
		 return ( (this.getMenuId()==castOther.getMenuId()) || ( this.getMenuId()!=null && castOther.getMenuId()!=null && this.getMenuId().equals(castOther.getMenuId()) ) )
 && ( (this.getLanguageCd()==castOther.getLanguageCd()) || ( this.getLanguageCd()!=null && castOther.getLanguageCd()!=null && this.getLanguageCd().equals(castOther.getLanguageCd()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMenuId() == null ? 0 : this.getMenuId().hashCode() );
         result = 37 * result + ( getLanguageCd() == null ? 0 : this.getLanguageCd().hashCode() );
         return result;
   }   

	public String toString() {
		String str="menuId=" + menuId
			+ " langCd=" + languageCd;
		return str;
	}

}


