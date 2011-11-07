package org.openiam.idm.srvc.synch.service;

import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.springframework.context.ApplicationContextAware;

import java.sql.Timestamp;
import java.util.*;

/**
 * Interface that needs to be implemented to call a webservice that will be used as an authoritative source.
 * User: suneetshah
 */
public interface WSOperationCommand extends ApplicationContextAware  {

    List<LineObject> execute(SynchConfig config);
}
