package org.openiam.idm.srvc.mngsys.service;

import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.dto.ResourceApprover;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.util.encrypt.Cryptor;

@WebService(endpointInterface = "org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/mngsys/service", 
		serviceName = "ManagedSystemWebService")
public class ManagedSystemDataServiceImpl implements ManagedSystemDataService {

	ManagedSysDAO managedSysDao;
	ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	
	ResourceApproverDAO resourceApproverDao;
	UserDataService userManager;
	

	private static final Log log = LogFactory.getLog(ManagedSystemDataServiceImpl.class);
	
	protected Cryptor cryptor;
	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	boolean encrypt = true;	// default encryption setting


	public void addManagedSystem(ManagedSys sys) {

		if (sys == null) {
			throw new NullPointerException("sys is null");
		}
		
        if (encrypt && sys.getPswd() != null) {
        	sys.setPswd(cryptor.encrypt(sys.getPswd()));
        };
		
		managedSysDao.add(sys);

	}

	public ManagedSys getManagedSys(String sysId) {
		if (sysId == null) {
			throw new NullPointerException("sysId is null");
		}

		ManagedSys sys = managedSysDao.findById(sysId);
		
		if (sys != null && sys.getPswd() != null) {
			sys.setDecryptPassword(cryptor.decrypt(sys.getPswd()));
		}
		return sys;

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
        if (encrypt && sys.getPswd() != null) {
        	sys.setPswd(cryptor.encrypt(sys.getPswd()));
        };
        
		managedSysDao.update(sys);
	}
	
	/**
	 * Finds objects for an object type (like User, Group) for a ManagedSystem definition
	 * @param managedSystemId
	 * @param objectType
	 * @return
	 */
	public ManagedSystemObjectMatch[] managedSysObjectParam(String managedSystemId, String objectType) {
		if (managedSystemId == null) {
			throw new NullPointerException("managedSystemId is null");
		}
		if (objectType == null) {
			throw new NullPointerException("objectType is null");
		}
		List<ManagedSystemObjectMatch> objList = managedSysObjectMatchDao.findBySystemId(managedSystemId, objectType);
		if (objList == null) {
			return null;
		}
		int size = objList.size();
		ManagedSystemObjectMatch[] objAry = new ManagedSystemObjectMatch[size];
		objList.toArray(objAry);
		return objAry;
		
		
	}

	public ManagedSys getManagedSysByResource(String resourceId) {
		if (resourceId == null) {
			throw new NullPointerException("resourceId is null");
		}		
		return managedSysDao.findByResource(resourceId, "ACTIVE");
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService#getApproversByAction(java.lang.String, java.lang.String, int)
	 */
	public List<ResourceApprover> getApproversByAction(String managedSysId,
			String action, int level) {
		if ( managedSysId == null) {
			throw new NullPointerException("managedSysId is null");
		}
		return  resourceApproverDao.findApproversByAction(managedSysId, action, level);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService#getApproversByResource(java.lang.String)
	 */
	public List<ResourceApprover> getApproversByResource(String managedSysId) {
		if ( managedSysId == null) {
			throw new NullPointerException("managedSysId is null");
		}
		return  resourceApproverDao.findApproversByResource(managedSysId);
	}


	
	public ManagedSysDAO getManagedSysDao() {
		return managedSysDao;
	}

	public void setManagedSysDao(ManagedSysDAO managedSysDao) {
		this.managedSysDao = managedSysDao;
	}

	public ManagedSystemObjectMatchDAO getManagedSysObjectMatchDao() {
		return managedSysObjectMatchDao;
	}

	public void setManagedSysObjectMatchDao(
			ManagedSystemObjectMatchDAO managedSysObjectMatchDao) {
		this.managedSysObjectMatchDao = managedSysObjectMatchDao;
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}

	public ResourceApproverDAO getResourceApproverDao() {
		return resourceApproverDao;
	}

	public void setResourceApproverDao(ResourceApproverDAO resourceApproverDao) {
		this.resourceApproverDao = resourceApproverDao;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService#getManagedSysByName(java.lang.String)
	 */
	public ManagedSys getManagedSysByName(String name) {
		if (name == null) {
			throw new NullPointerException("Parameter Managed system name is null");
		}
		return managedSysDao.findByName(name);
	}



	
}
