package org.openiam.idm.srvc.menu.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PermissionId", propOrder = {
    "menuId",
    "roleId",
    "serviceId"
})
public class PermissionId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2388899255222778128L;
	private String menuId;
	private String roleId;
	private String serviceId;
	
	public PermissionId() {
		
	}
	
	public PermissionId(String menuId, String roleId, String serviceId) {
		this.menuId = menuId;
		this.roleId = roleId;
		this.serviceId = serviceId;
	}
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((serviceId == null) ? 0 : serviceId.hashCode());
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
		if (!(obj instanceof PermissionId)) {
			return false;
		}
		PermissionId other = (PermissionId) obj;
		if (menuId == null) {
			if (other.menuId != null) {
				return false;
			}
		} else if (!menuId.equals(other.menuId)) {
			return false;
		}
		if (roleId == null) {
			if (other.roleId != null) {
				return false;
			}
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		if (serviceId == null) {
			if (other.serviceId != null) {
				return false;
			}
		} else if (!serviceId.equals(other.serviceId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PermissionId [menuId=" + menuId + ", roleId=" + roleId
				+ ", serviceId=" + serviceId + "]";
	}
	

}
