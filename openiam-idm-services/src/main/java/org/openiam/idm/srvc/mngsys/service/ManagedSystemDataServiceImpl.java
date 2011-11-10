package org.openiam.idm.srvc.mngsys.service;

import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.EncryptionException;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.util.encrypt.Cryptor;

@WebService(endpointInterface = "org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/mngsys/service", 
		portName = "ManagedSystemWebServicePort",
		serviceName = "ManagedSystemWebService")
public class ManagedSystemDataServiceImpl implements ManagedSystemDataService {

	protected ManagedSysDAO managedSysDao;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;	
	protected ApproverAssociationDAO approverAssociationDao;
	protected UserDataService userManager;
	protected AttributeMapDAO attributeMapDao;
	

	private static final Log log = LogFactory.getLog(ManagedSystemDataServiceImpl.class);
	
	protected Cryptor cryptor;
	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	boolean encrypt = true;	// default encryption setting


	public ManagedSys addManagedSystem(ManagedSys sys) {

		if (sys == null) {
			throw new NullPointerException("sys is null");
		}
		
        if (encrypt && sys.getPswd() != null) {
        	try {
        		sys.setPswd(cryptor.encrypt(sys.getPswd()));
        	}catch(EncryptionException e) {
        		log.error(e);
        	}
        };
		
		return managedSysDao.add(sys);

	}

	public ManagedSys getManagedSys(String sysId) {
		if (sysId == null) {
			throw new NullPointerException("sysId is null");
		}

		ManagedSys sys = managedSysDao.findById(sysId);
		
		if (sys != null && sys.getPswd() != null) {
			try {
				sys.setDecryptPassword(cryptor.decrypt(sys.getPswd()));
        	}catch(EncryptionException e) {
        		log.error(e);
        	}
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
	
	public ManagedSys[] getAllManagedSys() {
		List<ManagedSys> sysList= managedSysDao.findAllManagedSys();
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
        	try {
        		sys.setPswd(cryptor.encrypt(sys.getPswd()));
        	}catch(EncryptionException e) {
        		log.error(e);
        	}
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
/*	public List<ApproverAssociation> getApproversByAction(String managedSysId,
			String action, int level) {
		if ( managedSysId == null) {
			throw new NullPointerException("managedSysId is null");
		}
		return  resourceApproverDao.findApproversByAction(managedSysId, action, level);
	}
*/
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService#getApproversByResource(java.lang.String)
	 */
/*	public List<ApproverAssociation> getApproversByResource(String managedSysId) {
		if ( managedSysId == null) {
			throw new NullPointerException("managedSysId is null");
		}
		return  resourceApproverDao.findApproversByResource(managedSysId);
	}
*/

	
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

	//Approver Association  ================================================================

	
	public ApproverAssociationDAO getApproverAssociationDao() {
		return approverAssociationDao;
	}

	public void setApproverAssociationDao(ApproverAssociationDAO approverAssociationDao) {
		this.approverAssociationDao = approverAssociationDao;
	}


	public ApproverAssociation addApproverAssociation(ApproverAssociation approverAssociation) {
		if (approverAssociation == null)
			throw new IllegalArgumentException("approverAssociation object is null");

		return approverAssociationDao.add(approverAssociation);
	}

	public ApproverAssociation updateApproverAssociation(ApproverAssociation approverAssociation) {
		if (approverAssociation == null)
			throw new IllegalArgumentException("approverAssociation object is null");

		return approverAssociationDao.update(approverAssociation);
	}

	public ApproverAssociation getApproverAssociation(String approverAssociationId) {
		if (approverAssociationId == null)
			throw new IllegalArgumentException("approverAssociationId is null");

		return approverAssociationDao.findById(approverAssociationId);
	}

	public void removeApproverAssociation(String approverAssociationId) {
		if (approverAssociationId == null)
			throw new IllegalArgumentException("approverAssociationId is null");
		ApproverAssociation obj = this.approverAssociationDao.findById(approverAssociationId);
		this.approverAssociationDao.remove(obj);
	}

	public int removeAllApproverAssociations() {
		return this.approverAssociationDao.removeAllApprovers();
	}

	
	public List<ApproverAssociation> getApproverByRequestType(String requestType, int level) {
		if (requestType == null)
			throw new IllegalArgumentException("requestType is null");
		return this.approverAssociationDao.findApproversByRequestType(requestType, level);
	}
	
	public List<ApproverAssociation> getAllApproversByRequestType(String requestType) {
		if (requestType == null)
			throw new IllegalArgumentException("requestType is null");
		return approverAssociationDao.findAllApproversByRequestType(requestType);		
	}
	
	public List<ApproverAssociation> getApproversByObjectId (String associationObjId) {
		if (associationObjId == null)
			throw new IllegalArgumentException("associationObjId is null");
		return this.approverAssociationDao.findApproversByObjectId(associationObjId);
	}

	public int removeApproversByObjectId(String associationObjId) {
		if (associationObjId == null)
			throw new IllegalArgumentException("associationObjId is null");
		return this.approverAssociationDao.removeApproversByObjectId(associationObjId);
	}

	// find by RESOURCE, GROUP, ROLE, SUPERVISOR,INDIVIDUAL
	List<ApproverAssociation> getApproversByObjectType(String associationType) {
		if (associationType == null)
			throw new IllegalArgumentException("associationType is null");
		return this.approverAssociationDao.findApproversByObjectType(associationType);
	}
	
	public int removeApproversByObjectType(String associationType) {
		if (associationType == null)
			throw new IllegalArgumentException("associationType is null");
		return this.approverAssociationDao.removeApproversByObjectType(associationType);
	}
	
	
	List<ApproverAssociation> getApproversByAction(String associationObjId,
			String action, int level) {
		if (associationObjId == null)
			throw new IllegalArgumentException("associationObjId is null");
		return this.approverAssociationDao.findApproversByAction(associationObjId, action, level);
	}

	
	List<ApproverAssociation> getApproversByUser(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId is null");
		return this.approverAssociationDao.findApproversByUser(userId);
	}
	
	public int removeApproversByUser(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId is null");
		return this.approverAssociationDao.removeApproversByUser(userId);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService#addManagedSystemObjectMatch(org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch)
	 */
	public void addManagedSystemObjectMatch(ManagedSystemObjectMatch obj) {
		managedSysObjectMatchDao.add(obj);
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService#updateManagedSystemObjectMatch(org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch)
	 */
	public void updateManagedSystemObjectMatch(ManagedSystemObjectMatch obj) {
		this.managedSysObjectMatchDao.update(obj);
		
	}
	
	public void removeManagedSystemObjectMatch(ManagedSystemObjectMatch obj) {
		this.managedSysObjectMatchDao.remove(obj);
	}

	public AttributeMap getAttributeMap(String attributeMapId) {
		if (attributeMapId == null)
			throw new IllegalArgumentException("attributeMapId is null");

		AttributeMap obj = attributeMapDao.findById(attributeMapId);

		return obj;
	}

	public AttributeMap addAttributeMap(AttributeMap attributeMap) {
		if (attributeMap == null)
			throw new IllegalArgumentException("AttributeMap object is null");

		return attributeMapDao.add(attributeMap);
	}

	public AttributeMap updateAttributeMap(AttributeMap attributeMap) {
		if (attributeMap == null)
			throw new IllegalArgumentException("attributeMap object is null");

		return attributeMapDao.update(attributeMap);
	}

	public void removeAttributeMap(String attributeMapId) {
		if (attributeMapId == null) {
			throw new IllegalArgumentException("attributeMapId is null");
		}
		AttributeMap obj = this.attributeMapDao.findById(attributeMapId);
		this.attributeMapDao.remove(obj);
		
	}

	public int removeResourceAttributeMaps(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");

		return this.attributeMapDao.removeResourceAttributeMaps(resourceId);
	}

	public List<AttributeMap> getResourceAttributeMaps(String resourceId) {
		if (resourceId == null) {
			throw new IllegalArgumentException("resourceId is null");
		}
		return attributeMapDao.findByResourceId(resourceId);
	}

	public List<AttributeMap> getAllAttributeMaps() {
		List<AttributeMap> attributeMapList = attributeMapDao.findAllAttributeMaps();

		return attributeMapList;
	}




    public AttributeMapDAO getAttributeMapDao() {
		return attributeMapDao;
	}

	public void setAttributeMapDao(AttributeMapDAO attributeMapDao) {
		this.attributeMapDao = attributeMapDao;
	}

	
}
