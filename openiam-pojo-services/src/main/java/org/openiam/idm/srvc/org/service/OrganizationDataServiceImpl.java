package org.openiam.idm.srvc.org.service;

import javax.jws.*;

//import diamelle.common.continfo.*;
//import diamelle.base.prop.*;

import java.util.*;
import java.rmi.*;

import org.hibernate.Hibernate;
import org.openiam.idm.srvc.org.dto.*;

/**
 * <code>OrganizationManager</code> provides a service level interface to the
 * Organization components and its dependant objects as well as search
 * capability.<br>
 * 
 * Note: The spring configuration file defines MetadataTypes are used to identify Departments and Divisions in the org list.
 * 
 * @author OpenIAm
 * @version 2
 */

@WebService(endpointInterface="org.openiam.idm.srvc.org.service.OrganizationDataService",
		targetNamespace="urn:idm.openiam.org/srvc/org/service",
		portName = "OrganizationDataWebServicePort",
		serviceName="OrganizationDataWebService")
public class OrganizationDataServiceImpl implements OrganizationDataService {

	protected OrganizationDAO orgDao;

	protected OrganizationAttributeDAO orgAttrDao;
	
	
	


	/**
	 * Returns a list of companies that match the search criteria.
	 * 
	 * @param search
	 * @return
	 * @throws RemoteException
	 */
	// List searchOrganization(OrganizationSearch search) throws
	// RemoteException;
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#subOrganizations(java.lang.String)
	 */
	public List<Organization> subOrganizations(String orgId) {
		if (orgId == null) {
			throw new NullPointerException("orgId is null");
		}
		return orgDao.findChildOrganization(orgId);
		//return testOrgList(orgId);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#getTopLevelOrganizations()
	 */
	public List<Organization> getTopLevelOrganizations() {
		List<Organization> orgList = orgDao.findRootOrganizations();
		
		// initialize the collections
		for ( Organization org :orgList) {
			Hibernate.initialize( org.getAttributes() ); 
		}
		
		return orgList;
		//return testOrgList("myTopLevelOrg");
	}
	
	/**
	 * Returns a list of all organizations based on a metadataType. The parentId parameter can be used to get 
	 * values that are nested further in the hierarchy. If parentId is null, the method will search only on the typeId and parentId 
	 * will be ignored.
	 * @param typeId
	 * @param parentId
	 * @return
	 */
   public List<Organization> getOrganizationByType(String typeId, String parentId) {
		if (typeId == null)
			throw new NullPointerException("typeId is null");
		
	   List<Organization> orgList = orgDao.findOrganizationByType(typeId, parentId);
	   if (orgList == null) {
		   return null;
	   }
	   return orgList;
   }

   public List<Organization> getOrganizationByClassification(String parentId, String classification) {
		if (classification == null)
			throw new NullPointerException("classification is null");
		
	   List<Organization> orgList = orgDao.findOrganizationByClassification(parentId, classification);
	   if (orgList == null) {
		   return null;
	   }
	   return orgList;
  }
   
   public List<Organization> allDepartments(String parentId) {
	   return getOrganizationByClassification(parentId, OrgClassificationEnum.DEPARTMENT.toString());
   }
   public List<Organization> allDivisions(String parentId) {
	   return getOrganizationByClassification(parentId, OrgClassificationEnum.DIVISION.toString());  
   }
   

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#addOrganization(org.openiam.idm.srvc.org.dto.Organization)
	 */
	public Organization addOrganization(Organization org) {
		if (org == null)
			throw new NullPointerException("org object is null");

		return orgDao.add(org); //null pointer here, orgDao seems to be null
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#updateOrganization(org.openiam.idm.srvc.org.dto.Organization)
	 */
	public void updateOrganization(Organization org) {
		if (org == null)
			throw new NullPointerException("org object is null");
		if (org.getOrgId() == null)
			throw new NullPointerException("org id is null");
		orgDao.update(org);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#removeOrganization(java.lang.String)
	 */
	public void removeOrganization(String orgId) {
		if (orgId == null)
			throw new NullPointerException("orgId is null");

		Organization instance = new Organization();
		instance.setOrgId(orgId);  // dont need if new Organization(orgId) constructor available
		orgDao.remove(instance);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#isRootOrganization(java.lang.String)
	 */
	public boolean isRootOrganization(String orgId) {
		if (orgId == null)
			throw new NullPointerException("orgId object is null");

		Organization org = orgDao.findById(orgId);
		if (org == null) {
			return false;
		}
		if (org.getParentId() == null) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#containsChildren(java.lang.String)
	 */
	public boolean containsChildren(String orgId) {
		if (orgId == null)
			throw new NullPointerException("orgId object is null");

		List<Organization> orgList = orgDao.findChildOrganization(orgId);
		if (orgList != null && !orgList.isEmpty()) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#getOrganization(java.lang.String)
	 */
	public Organization getOrganization(String orgId) {
		if (orgId == null)
			throw new NullPointerException("orgId object is null");
		
		System.out.println("In Organization: orgDao=" + orgDao );
		
		Organization org = orgDao.findById(orgId);
		if (org != null) {
			Hibernate.initialize( org.getAttributes() ); 
		}else {
			return null;
		}
		return org;
		
	}

	/* -------- Methods for Attributes ---------- */

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#addAttribute(org.openiam.idm.srvc.org.dto.OrganizationAttribute)
	 */
	public void addAttribute(OrganizationAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getAttrId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getOrganizationId() == null) {
			throw new NullPointerException(
					"OrganizationId has not been associated with this attribute.");
		}

		orgAttrDao.add(attribute);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#updateAttribute(org.openiam.idm.srvc.org.dto.OrganizationAttribute)
	 */
	public void updateAttribute(OrganizationAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getAttrId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getOrganizationId() == null) {
			throw new NullPointerException(
					"Org has not been associated with this attribute.");
		}

		orgAttrDao.update(attribute);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#getAllAttributes(java.lang.String)
	 */
	public Map<String, OrganizationAttribute> getAllAttributes(String orgId) {

		Map<String, OrganizationAttribute> attrMap = null;

		if (orgId == null) {
			throw new NullPointerException("orgId is null");
		}
		
		Organization org = this.orgDao.findById(orgId);
		
		attrMap = org.getAttributes();
		if (attrMap != null && attrMap.isEmpty())
			return null;
		return attrMap;
		
		//return this.attribMap(orgId);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#getAttribute(java.lang.String)
	 */
	public OrganizationAttribute getAttribute(String attrId) {
		if (attrId == null) {
			throw new NullPointerException("attrId is null");
		}
		return orgAttrDao.findById(attrId);
		//return this.orgAttrib(attrId);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#removeAttribute(org.openiam.idm.srvc.org.dto.OrganizationAttribute)
	 */
	public void removeAttribute(OrganizationAttribute attr) {
		if (attr == null) {
			throw new NullPointerException("attr is null");
		}
		if (attr.getAttrId() == null) {
			throw new NullPointerException("attrId is null");
		}

		orgAttrDao.remove(attr);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#removeAllAttributes(java.lang.String)
	 */
	public void removeAllAttributes(String orgId) {
		if (orgId == null) {
			throw new NullPointerException("orgId is null");
		}
		orgAttrDao.removeAttributesByParent(orgId);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#getOrgAttrDao()
	 */
	public OrganizationAttributeDAO getOrgAttrDao() {
		return orgAttrDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#setOrgAttrDao(org.openiam.idm.srvc.org.service.OrganizationAttributeDAO)
	 */
	public void setOrgAttrDao(OrganizationAttributeDAO orgAttrDao) {
		this.orgAttrDao = orgAttrDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#getOrgDao()
	 */
	public OrganizationDAO getOrgDao() {
		return orgDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#setOrgDao(org.openiam.idm.srvc.org.service.OrganizationDAO)
	 */
	public void setOrgDao(OrganizationDAO orgDao) {
		this.orgDao = orgDao;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#search(java.lang.String, java.lang.String)
	 */
	public List<Organization> search(String name, String type, String classification, String internalOrgId) {
		return orgDao.search(name, type, classification, internalOrgId);
	}
	
	public List<Organization> getAllOrganizations() {
		return orgDao.findAllOrganization();
	}



}
