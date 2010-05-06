package org.openiam.util.db;

import java.util.List;
import java.util.ArrayList;
import org.openiam.util.db.QueryCriteria;

/**
 * <code>Search</code> is used to define search parameters when looking for a
 * particular user. For example to search for a user with the last name of smith, you
 * use the following line:<br>
 * <pre>
 * search.setLastName("smith");
 * </pre>
 * However to get a list of all users whose last names begin with 'sm', you would do
 * the following.<br>
 * <pre>
 *  search.setLastName("sm");
 *  search.addSearchOperator("LastName",Operation.LIKE);
 * </pre>
 * If no operation is defined for a given attribute, the '=' operation
 * is assumed.
 */

public class SearchImpl implements Search  {


	  
	  protected List<QueryCriteria> criteriaList = new ArrayList();
	  
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.dto.UserSearch#addSearchCriteria(org.openiam.util.db.QueryCriteria)
	 */
	public void addSearchCriteria(QueryCriteria criteria) {
		criteriaList.add(criteria);
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.dto.UserSearch#clearCriteria()
	 */
	public void clearCriteria() {
		criteriaList.clear();
	}
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.dto.UserSearch#removeCriteria(java.lang.String)
	 */
	public void removeCriteria(String fieldName) {
	
		int size = criteriaList.size();
		for (int i=0; i<0;i++) {
			QueryCriteria crit = criteriaList.get(i);
			if (crit.getPropertyName().equalsIgnoreCase(fieldName)) {
				criteriaList.remove(i);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.dto.UserSearch#getCriteria()
	 */
	public List getCriteria() {
		if (criteriaList.isEmpty())
			return null;
		return criteriaList;
	}
}
