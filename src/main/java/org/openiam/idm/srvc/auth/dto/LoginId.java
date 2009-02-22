package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11



/**
 * PrimaryKey for a Login object.
 */
public class LoginId  implements java.io.Serializable {


     private String serviceId;
     private String login;
     private String managedSysId;



	public LoginId() {
    }

    public LoginId(String serviceId, String login, String managedSysId) {
       this.serviceId = serviceId;
       this.login = login;
       this.managedSysId = managedSysId;
    }
   
    public String getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public String getManagedSysId() {
		return managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}
	
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LoginId) ) return false;
		 LoginId castOther = ( LoginId ) other; 
         
		 return ( (this.getServiceId()==castOther.getServiceId()) || ( this.getServiceId()!=null && castOther.getServiceId()!=null && this.getServiceId().equals(castOther.getServiceId()) ) )
 && ( (this.getLogin()==castOther.getLogin()) || ( this.getLogin()!=null && castOther.getLogin()!=null && this.getLogin().equals(castOther.getLogin()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceId() == null ? 0 : this.getServiceId().hashCode() );
         result = 37 * result + ( getLogin() == null ? 0 : this.getLogin().hashCode() );
         return result;
   }   


}


