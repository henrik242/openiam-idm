package org.openiam.idm.srvc.res.service;

import org.openiam.idm.srvc.res.dto.UserPrivilege;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: arun
 * Date: 8/8/11
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserPrivilegeDAO {

    UserPrivilege findById(String id);

    List<UserPrivilege> findByExample(UserPrivilege instance);

    UserPrivilege add(UserPrivilege instance);

    void remove(UserPrivilege instance);

    UserPrivilege update(UserPrivilege instance);

    List<UserPrivilege> findAllUserPrivileges();

    int removeAllUserPrivileges();
}
