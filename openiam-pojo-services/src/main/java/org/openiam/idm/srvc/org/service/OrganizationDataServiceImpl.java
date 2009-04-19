package org.openiam.idm.srvc.org.service;

import javax.jws.*;

//import diamelle.common.continfo.*;
//import diamelle.base.prop.*;

import java.util.*;
import java.rmi.*;
import org.openiam.idm.srvc.org.dto.*;

/**
 * <code>OrganizationManager</code> provides a service level interface to the
 * Organization components and its dependant objects as well as search
 * capability.<br>
 * 
 * 
 * @author diamelle
 * @version 2
 */

@WebService(endpointInterface="org.openiam.idm.srvc.org.service.OrganizationDataService",
		targetNamespace="urn:idm.openiam.org/srvc/org/service",
		serviceName="OrganizationDataWebService")
public class OrganizationDataServiceImpl implements OrganizationDataService {

	protected OrganizationDAO orgDao;

	protected OrganizationAttributeDAO orgAttrDao;
	
/* test objects --
	private List<Organization> testOrgList(String orgId) {
		List<Organization> orgList = new ArrayList<Organization>();

		for (int i = 0; i < 3; i++) {
			Organization org = new Organization();
			org.setOrgId(orgId + i);
			org.setOrganizationName("OPENIAM LLC " + i);
			org.setCreateDate(new Date());
			orgList.add(org);
		}
		return orgList;
	}
	
	private Organization org(String orgId) {
		Organization org = new Organization();
		org.setOrgId(orgId);
		org.setOrganizationName("OPENIAM LLC ");
		org.setCreatedBy("arun");
		org.setCreateDate(new Date());
		return org;
	}
	
	private OrganizationAttribute orgAttrib(String attrId) {
		OrganizationAttribute attrib = new OrganizationAttribute();
		attrib.setAttrId(attrId);
		attrib.setName("Attribute name " + attrId);
		attrib.setValue("attrib value " + attrId);
		return attrib;
	}
	
	
	private Map<String, OrganizationAttribute> attribMap(String orgId) {

		Map<String, OrganizationAttribute> attrMap = new HashMap<String, OrganizationAttribute>();
		for (int i = 0; i < 3; i++) {
			attrMap.put(orgId+i, orgAttrib(orgId+i));
		}
		return attrMap;
	}	
*/	

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
		return orgDao.findRootOrganizations();
		//return testOrgList("myTopLevelOrg");
	}
	
   public List<org.openiam.idm.srvc.org.dto.Organization> getOrganizationByType(String typeId) {
		if (typeId == null)
			throw new NullPointerException("typeId is null");
		
	   return orgDao.findOrganizationByType(typeId);
   }


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.org.service.OrganizationDataService#addOrganization(org.openiam.idm.srvc.org.dto.Organization)
	 */
	public void addOrganization(Organization org) {
		if (org == null)
			throw new NullPointerException("org object is null");
		if (org.getOrgId() == null)
			throw new NullPointerException("org id is null");

		orgDao.add(org); //null pointer here, orgDao seems to be null
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
		
		return orgDao.findById(orgId);
		//return this.org(orgId);
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
	public List<Organization> search(String name, String type) {
		return orgDao.search(name, type);
	}
	
	public List<Organization> getAllOrganizations() {
		return orgDao.findAllOrganization();
	}

}
