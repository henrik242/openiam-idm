package org.openiam.idm.srvc.menu.service;

import java.util.List;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.dto.Permission;
import org.openiam.idm.srvc.menu.dto.PermissionId;
import org.openiam.idm.srvc.role.dto.Role;

public interface PermissionDAO {

	Permission findById(PermissionId id);

	List<Permission> findByExample(Permission instance);

	Permission add(Permission instance);

	void remove(Permission instance);

	Permission update(Permission instance);

	List<Permission> findAllPermissions();

	int removeAllPermissions();

	List<Role> findRolesByMenu(String menuId);

	List<Menu> findMenusByRole(String roleId, String serviceId);

	List<Menu> findMenusByUser(String menuGroup, String roleId, String userId);

}