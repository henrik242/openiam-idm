package org.openiam.idm.srvc.res.service;

import org.openiam.idm.srvc.res.dto.PrivilegeDef;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: arun
 * Date: 8/8/11
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PrivilegeDefDAO {

    PrivilegeDef findById(String id);

    List<PrivilegeDef> findByExample(PrivilegeDef instance);

    PrivilegeDef add(PrivilegeDef instance);

    void remove(PrivilegeDef instance);

    PrivilegeDef update(PrivilegeDef instance);

    List<PrivilegeDef> findAllPrivileges();

    int removeAllPrivileges();
}
