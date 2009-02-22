package org.openiam.base.id;

import org.openiam.exception.data.DataException;

public interface SequenceGenDAO {

	/**
	 * Returns the next sequential id as a String.
	 * @param key
	 * @return
	 * @throws DataException
	 */
    public String getNextId(String key) throws DataException ;
	        
    /**
     * Returns a SequenceGen object that corresponds to the key.
     * @param id
     * @return
     * @throws DataException
     */
    public SequenceGen findById( java.lang.String id) throws DataException ;

}
