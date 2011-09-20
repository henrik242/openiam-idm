package org.openiam.idm.srvc.menu.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Permission", propOrder = {
    "id"
})
public class Permission implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2591311225486164227L;
	private PermissionId id;
	
	public Permission() {
		
	}
	
	public Permission(PermissionId id) {
		this.id = id;
	}

		
	public PermissionId getId() {
		return id;
	}


	public void setId(PermissionId id) {
		this.id = id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Permission)) {
			return false;
		}
		Permission other = (Permission) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + "]";
	}

	

}
