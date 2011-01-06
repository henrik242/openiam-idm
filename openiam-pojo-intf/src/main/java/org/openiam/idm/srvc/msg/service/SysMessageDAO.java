package org.openiam.idm.srvc.msg.service;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.msg.dto.SysMessage;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SysMessageDelivery.
 * @see org.openiam.idm.srvc.msg.dto.SysMessageDelivery
 * @author Hibernate Tools
 */
public interface SysMessageDAO {



	public SysMessage add (SysMessage transientInstance) ;
	
	public void remove(SysMessage persistentInstance) ;

	public SysMessage update(SysMessage detachedInstance) ;

	public SysMessage findById(java.lang.String id) ;
	
	public List<SysMessage> findAll();

}
