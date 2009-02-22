package org.openiam.idm.srvc.cd.service;

import org.openiam.idm.srvc.cd.dto.*;

import java.util.*;

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
public class ReferenceDataServiceImpl {

	ReferenceDAO refDao;
	
	public ReferenceDAO getRefDao() {
		return refDao;
	}

	public void setRefDao(ReferenceDAO refDao) {
		this.refDao = refDao;
	}

	/**
	 * Finds a list of reference data for a particular codeGroup.. 
	 * @param codeGroup - A service may have several groups of code. Code Group breaks that down
	 * @param languageCd - LanguageCode
	 * @return List of ReferenceData objects.
	 * @throws FinderException
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
  
	  public void addRefData(ReferenceData val) {
		  if (val == null) {
			  return;
		  }
		  refDao.add(val);
		  
	  }
	  public void saveRefData(ReferenceData val) {
		  if (val == null) {
			  return;
		  }
		  refDao.update(val);		  
	  }
	  public void removeRefData(ReferenceData val) {
		  if (val == null) {
			  return;
		  }
		  refDao.remove(val);
	  }

	  public void getRefDataById(ReferenceDataId val) {
		  if (val == null) {
			  return;
		  }
		  refDao.findById(val);
	  }

	  
}
