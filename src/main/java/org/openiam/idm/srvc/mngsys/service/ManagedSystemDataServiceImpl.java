package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

public class ManagedSystemDataServiceImpl implements ManagedSystemDataService {

	ManagedSysDAO managedSysDao;
	
	private static final Log log = LogFactory.getLog(ManagedSystemDataServiceImpl.class);
	


	public void addManagedSystem(ManagedSys sys) {

		if (sys == null) {
			throw new NullPointerException("sys is null");
		}
		managedSysDao.add(sys);

	}

	public ManagedSys getManagedSys(String sysId) {
		if (sysId == null) {
			throw new NullPointerException("sysId is null");
		}
		return managedSysDao.findById(sysId);

	}

	public ManagedSys[] getManagedSysByProvider(String providerId) {
		if (providerId == null) {
			throw new NullPointerException("providerId is null");
		}
		List<ManagedSys> sysList= managedSysDao.findbyConnectorId(providerId);
		if (sysList == null)
			return null;
		int size = sysList.size();
		ManagedSys[] sysAry = new ManagedSys[size];
		sysList.toArray(sysAry);
		return sysAry;
	}
	
	/**
	 * Returns an array of ManagedSys object for a security domain.  
	 * @param domainId
	 * @return
	 */
	public ManagedSys[] getManagedSysByDomain(String domainId) {
		if (domainId == null) {
			throw new NullPointerException("domainId is null");
		}
		List<ManagedSys> sysList= managedSysDao.findbyDomain(domainId);
		if (sysList == null)
			return null;
		int size = sysList.size();
		ManagedSys[] sysAry = new ManagedSys[size];
		sysList.toArray(sysAry);
		return sysAry;
		
	}
	

	public void removeManagedSystem(String sysId) {
		if (sysId == null) {
			throw new NullPointerException("sysId is null");
		}
		ManagedSys sys = getManagedSys(sysId);
		managedSysDao.remove(sys);
	}

	public void updateManagedSystem(ManagedSys sys) {
		if (sys == null) {
			throw new NullPointerException("sys is null");
		}
		managedSysDao.update(sys);
	}

	public ManagedSysDAO getManagedSysDao() {
		return managedSysDao;
	}

	public void setManagedSysDao(ManagedSysDAO managedSysDao) {
		this.managedSysDao = managedSysDao;
	}
	
}
