package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11



/**
 * PrimaryKey for a Login object.
 */
public class LoginId  implements java.io.Serializable {


     private String domainId;
     private String login;
     private String managedSysId;


     public String toString() {
    	 String str= "serviceId=" + domainId + 
    	 		"  login=" + login + 
    	 		"  managedSysId=" + managedSysId;
    	 return str;
     }

	public LoginId() {
    }

    public LoginId(String domainId, String login, String managedSysId) {
       this.domainId = domainId;
       this.login = login;
       this.managedSysId = managedSysId;
    }
   
    public String getDomainId() {
        return this.domainId;
    }
    
    public void setDomainId(String domainId) {
        this.domainId = domainId;
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
         
		 return ( (this.getDomainId()==castOther.getDomainId()) || ( this.getDomainId()!=null && castOther.getDomainId()!=null && this.getDomainId().equals(castOther.getDomainId()) ) )
 && ( (this.getLogin()==castOther.getLogin()) || ( this.getLogin()!=null && castOther.getLogin()!=null && this.getLogin().equals(castOther.getLogin()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDomainId() == null ? 0 : this.getDomainId().hashCode() );
         result = 37 * result + ( getLogin() == null ? 0 : this.getLogin().hashCode() );
         return result;
   }   


}


