package org.openiam.util.db;

import java.util.List;

import org.openiam.util.db.QueryCriteria;

/**
 * Interface to carry out adhoc searches. 
 * @author Suneet Shah
 *
 */
public interface Search {

 void addSearchCriteria(QueryCriteria criteria);
 void clearCriteria();
 void removeCriteria(String fieldName);
 List getCriteria();

}