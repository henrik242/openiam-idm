package org.openiam.idm.srvc.cd.service;

import org.openiam.idm.srvc.cd.dto.*;

import java.util.*;

import javax.jws.WebService;

/**
The <code>ReferenceDAOImpl</code> is used to manage and provide access to codes or lookup values 
 * that are commonly used.  The ReferenceDAOImpl has been designed to support internationalization  
 * <br>
 * <b>Terminology:</b>
 * <ul>
 *	<li>codeGroup: Provides a way to group codes that are associated together.
  *	<li>statusCd: Code that is used by the system. 
 *	<li>languageCd: 2 letter code indicating the language the code is associated with.
 * </ul>
 * <b>Example:</b><br>
 * Getting a list of codes:<br>
 * <code>
 *		codeMgr = codeMgrHome.create();
 *		List codeList = codeMgr.getCodesByService(serviceId, companyOwnerId,	codeGroup,languageCd);
 *		<br>
 *		// iterate through the list
 *		if (codeList != null) {
 * 			for (int i=0; i<codeList.size(); i++) {
 *  				StatusCodeValue val = (StatusCodeValue)codeList.get(i);
 *  				...
 *  			}
 * 		}
 * </code>
 *
 * <br>
 * @see org.openidm.srvc.dto.Status
 * @author Suneet Shah
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.cd.service.ReferenceDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/ref/service", 
		portName = "ReferenceDataWebServicePort", 
		serviceName = "ReferenceDataWebService")
public class ReferenceDataServiceImpl implements ReferenceDataService {

	ReferenceDAO refDao;
	
	public ReferenceDAO getRefDao() {
		return refDao;
	}

	public void setRefDao(ReferenceDAO refDao) {
		this.refDao = refDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cd.service.ReferenceDataService#getRefByGroup(java.lang.String, java.lang.String)
	 */
	  public List<ReferenceData> getRefByGroup(String codeGroup, String languageCd) {
		  if (codeGroup == null) {
			  return null;
		  }
		  if (languageCd == null) {
			  return null;
		  }
		  return refDao.findByGroup(codeGroup, languageCd);		  
	  }
  
	  /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cd.service.ReferenceDataService#addRefData(org.openiam.idm.srvc.cd.dto.ReferenceData)
	 */
	public void addRefData(ReferenceData val) {
		  if (val == null) {
			  return;
		  }
		  refDao.add(val);
		  
	  }
	  /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cd.service.ReferenceDataService#saveRefData(org.openiam.idm.srvc.cd.dto.ReferenceData)
	 */
	public void saveRefData(ReferenceData val) {
		  if (val == null) {
			  return;
		  }
		  refDao.update(val);		  
	  }
	  /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cd.service.ReferenceDataService#removeRefData(org.openiam.idm.srvc.cd.dto.ReferenceData)
	 */
	public void removeRefData(ReferenceData val) {
		  if (val == null) {
			  return;
		  }
		  refDao.remove(val);
	  }

	  /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cd.service.ReferenceDataService#getRefDataById(org.openiam.idm.srvc.cd.dto.ReferenceDataId)
	 */
	public void getRefDataById(ReferenceDataId val) {
		  if (val == null) {
			  return;
		  }
		  refDao.findById(val);
	  }

	  
}
