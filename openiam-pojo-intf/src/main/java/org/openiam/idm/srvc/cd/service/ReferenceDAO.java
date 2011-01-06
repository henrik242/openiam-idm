package org.openiam.idm.srvc.cd.service;

import java.util.List;

import org.openiam.exception.data.DataException;
import org.openiam.idm.srvc.cd.dto.*;

public interface ReferenceDAO {

	/**
	 * Return an ReferenceDAO object for the id.
	 * @param id
	 */
	ReferenceData findById(ReferenceDataId id)  throws DataException;
	/**
	 * Creates a new ReferenceDAO object and adds it to the datastore. 
	 * @param instance
	 */
	ReferenceData add(ReferenceData instance) throws DataException;
	/**
	 * Updates an existing Reference data object. 
	 * @param instance
	 */
	void update(ReferenceData instance) throws DataException;
	/**
	 * Remove an ReferenceDAO object from the datastore log.
	 * @param instance
	 */
	void remove(ReferenceData instance) throws DataException;
	
	/**
	 * Returns a list of ReferenceData objects that belong to this group of the ReferenceData
	 * @param codeGroup
	 * @param languageCd
	 * @return
	 * @throws DataException
	 */
	public List<ReferenceData> findByGroup(String codeGroup, String languageCd) throws DataException ;
}
