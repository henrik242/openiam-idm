package org.openiam.idm.srvc.user.service;


import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.HibernateException;

import org.openiam.base.id.SequenceGenDAO;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.util.db.Search;
import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.OperationConstant;

/**
 * Data access implementation for domain model class User and UserWS. UserWS is similar to User,
 * however, the interface has been simplified to support usage in a web service.
 * @see org.openiam.idm.srvc.user
 * @author Suneet Shah
 */
public class UserDAOImpl implements UserDAO{



	private static final Log log = LogFactory.getLog(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	private SequenceGenDAO seqGenDao;
	private String emailSearchVal;
	private String phoneSearchVal;
	private Integer maxResultSetSize;
	private String dbType;


	
	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}

	   
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void add(User usr) {
		log.debug("persisting User instance");
		try {
			// If the object has not been assigned an id, we should automatically assign one
			// based on the predefined id generator. Hibernate ID generator does not give us 
			// the flexibility to let the user assign the id.
			/*
			 if (usr.getUserId() == null || 
				usr.getUserId().length() == 0) {			
				usr.setUserId( this.seqGenDao.getNextId("USER_ID"));
			}
			*/
			
			sessionFactory.getCurrentSession().persist(usr);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User update(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			// Since contact info objects can be shared between users and other entities, the 
			// filters will help ensure that we only see the User related object.
			
			session.enableFilter("parentTypeFilter")
					.setParameter("parentFilter", "USER");
			
			User instance = (User)session.get("org.openiam.idm.srvc.user.dto.User", id);
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	
	public User findByName(String firstName, String lastName) {
		try{
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.User u " + 
					" where u.lastName = :lastName and u.firstName = :firstName");
			qry.setString("firstName", firstName);
			qry.setString("lastName", lastName);
			List<User> results = (List<User>)qry.list();

			if (results != null && !results.isEmpty() ) {
				return results.get(0);
			}else {
				return null;
			}
		} catch (HibernateException re) {
		log.error("get failed", re);
		throw re;
	}
	}
	
	public List findByStatus(String status) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.User u " + 
				" where u.status = :status order by u.lastName asc");
		qry.setString("status", status);
		List<User> results = (List<User>)qry.list();
		return results;
	}
	
	public List findByLastUpdateRange(Date startDate, Date endDate) {
		log.debug("finding User based on the lastUpdate date range that is provided");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =  session.createCriteria(org.openiam.idm.srvc.user.dto.User.class);
			criteria.add(Restrictions.between("lastUpdate", startDate, endDate));
			return criteria.list();
		
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("findByLastUpdateRange failed.", re);
			throw re;
		}
	}
	

	public List<User> search(UserSearch search) {
		if (dbType != null &&  dbType.equalsIgnoreCase("ORACLE_INSENSITIVE")) {
			return searchOracleInsensitive(search);
		}
		return defaultSearch(search);
	}
	
private List<User> searchOracleInsensitive(UserSearch search) {
		
		boolean firstName = false;
		boolean lastName = false;
		boolean nickName = false;
		boolean status = false;
		boolean secondaryStatus = false;
		boolean deptCd = false;
		boolean division = false;
		boolean phoneAreaCd = false;
		boolean phoneNbr = false;
		boolean employeeId = false;
		boolean groupId = false;
		boolean roleId = false;
		boolean emailAddress = false;
		boolean orgId = false;
		boolean userId = false;
		boolean principal = false;
		boolean domainId = false;
		boolean attributeName = false;
		boolean attributeValue = false;
		boolean metadataElementId = false;
		boolean showInSearch = false;
		boolean locationId = false;
		boolean createDate = false;
		
	    boolean userTypeInd = false;
	    boolean classification = false;
	    boolean orgName = false;
	    boolean startDate = false;
	    boolean lastDate = false;
		   
	
		String select = " select /*+ INDEX(IDX_USER_FIRSTNAME_UPPER) INDEX(IDX_USER_LASTNAME_UPPER) INDEX(IDX_LOGIN_PRINCIPAL_UPPER) INDEX(IDX_UA_NAME_UPPER)  */ " +
				" DISTINCT u.USER_ID, u.TYPE_ID, " +
		" u.TITLE, u.MIDDLE_INIT, u.LAST_NAME, u.FIRST_NAME," + 
		" u.BIRTHDATE, u.STATUS, u.SECONDARY_STATUS, u.DEPT_NAME, u.DEPT_CD, " + 
		" u.LAST_UPDATE, u.CREATED_BY, u.CREATE_DATE, u.SEX, " + 
		" u.USER_TYPE_IND, u.SUFFIX, u.PREFIX, u.LAST_UPDATED_BY," + 
		" u.LOCATION_NAME, u.LOCATION_CD, u.EMPLOYEE_TYPE, u.EMPLOYEE_ID, " +
		" u.JOB_CODE, u.MANAGER_ID, u.COMPANY_OWNER_ID, u.COMPANY_ID, " +
		" u.LAST_DATE, u.START_DATE, u.COST_CENTER, u.DIVISION," +
		" u.PASSWORD_THEME, u.NICKNAME, u.MAIDEN_NAME, u.MAIL_CODE, " + 
		" u.COUNTRY, u.BLDG_NUM, u.STREET_DIRECTION, u.SUITE,  " +
		" u.ADDRESS1, u.ADDRESS2, u.ADDRESS3, u.ADDRESS4, u.ADDRESS5, u.ADDRESS6, u.ADDRESS7," +
		" u.CITY, u.STATE, u.POSTAL_CD, u.EMAIL_ADDRESS, u.ALTERNATE_ID, " +
		" u.PHONE_NBR, u.PHONE_EXT, u.AREA_CD, u.COUNTRY_CD, u.CLASSIFICATION, u.SHOW_IN_SEARCH " +
		" from 	USERS u " +
		"  		LEFT JOIN LOGIN lg ON ( lg.USER_ID = u.USER_ID) " + 
		"  		LEFT JOIN EMAIL_ADDRESS em ON ( em.PARENT_ID = u.USER_ID) " + 
		"  		LEFT JOIN PHONE p ON ( p.PARENT_ID = u.USER_ID) " + 
		"  		LEFT JOIN USER_ATTRIBUTES ua ON ( ua.USER_ID = u.USER_ID) " + 
		"  		LEFT JOIN USER_GRP g ON ( g.USER_ID = u.USER_ID) " + 
		"  		LEFT JOIN COMPANY c ON ( c.COMPANY_ID = u.COMPANY_ID) " + 
		"	 	LEFT JOIN USER_ROLE_VW urv on (u.USER_ID = urv.USER_ID) "  ;
 
	
		
		StringBuffer where = new StringBuffer();

		if (search.getShowInSearch() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.SHOW_IN_SEARCH = :showInSearch ");
			showInSearch = true;
		}
	
	
		if (search.getUserId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.USER_ID = :userId ");
			userId = true;
		}
		
		if (search.getUserTypeInd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.USER_TYPE_IND = :userTypeInd ");
			userTypeInd = true;
		}

		if (search.getLocationCd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.LOCATION_CD = :locationCd ");
			locationId = true;
		}
		
		if (search.getClassification() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.CLASSIFICATION = :classification ");
			classification = true;
		}
		
		if (search.getFirstName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" UPPER(u.FIRST_NAME) like :firstName ");
			firstName = true;
		}
		if (search.getLastName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" UPPER( u.LAST_NAME) like :lastName ");
			lastName = true;
		}
		if (search.getNickName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.NICKNAME like :nickName ");
			nickName = true;
		}
		
		if (search.getStatus() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.STATUS = :status ");
			status = true;
		}

		if (search.getSecondaryStatus() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.SECONDARY_STATUS = :secondaryStatus ");
			secondaryStatus = true;
		}
		
		

		if (search.getCreateDate() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.CREATE_DATE <= :createDate ");
			createDate = true;
		}

		if (search.getStartDate() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.START_DATE = :startDate ");
			startDate = true;
		}
		if (search.getLastDate() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.LAST_DATE = :lastDate ");
			lastDate = true;
		}
		
		
		
		if (search.getDeptCd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.DEPT_CD = :deptCd ");
			deptCd = true;
		}
		if (search.getDivision() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.DIVISION = :division ");
			division = true;
		}
		
		if (search.getEmployeeId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.EMPLOYEE_ID = :employeeId ");
			employeeId =true;
		}
		if (search.getOrgId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.COMPANY_ID = :orgId ");
			orgId = true;
		}
		if (search.getOrgName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" UPPER(c.COMPANY_NAME) like :orgName ");
			orgName = true;
		}
		if (search.getPhoneAreaCd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" p.AREA_CD = :phoneAreaCd ");
			phoneAreaCd =true;
		}
		if (search.getPhoneNbr() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" p.PHONE_NBR = :phoneNbr ");
			phoneNbr = true;
		}

		if (search.getEmailAddress() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			//where.append(" u.EMAIL_ADDRESS = :emailAddress ");
			where.append(" em.EMAIL_ADDRESS = :emailAddress ");
			emailAddress = true;
		}		
		if (!search.getGroupIdList().isEmpty()) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" g.GRP_ID in (:groupList) ");
			groupId =true;
		}
		if (!search.getRoleIdList().isEmpty()) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" urv.ROLE_ID in (:roleList) ");
			where.append(" and urv.SERVICE_ID = :domainId ");
			roleId = true;
		}

		/* Login  */
		if (search.getPrincipal() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" UPPER(lg.LOGIN) like :principal ");
			principal = true;
		}
		
		if (search.getDomainId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" lg.SERVICE_ID = :domainId ");
			domainId = true;
		}

		if (search.getLoggedIn() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			if (search.getLoggedIn().equalsIgnoreCase("Yes")) {
				where.append(" lg.LAST_LOGIN IS NOT NULL ");
			}else {
				where.append(" lg.LAST_LOGIN IS NULL ");
			}

		}
		
		/* User Attributes fields */
		if (search.getAttributeName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" UPPER(ua.NAME) = :attributeName ");
			attributeName = true;
		}
		if (search.getAttributeValue() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" UPPER(ua.VALUE) like :attributeValue ");
			attributeValue = true;
		}
		if (search.getAttributeElementId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" ua.METADATA_ID = :elementId ");
			metadataElementId = true;
		}
		
		if ( where.length() > 0) {
			select = select + " WHERE " + where.toString() ;
		}
		
		select = select + "  ORDER BY u.LAST_NAME, u.FIRST_NAME"; 
		log.debug("search select: " + select);
		
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery qry = session.createSQLQuery(select);
		qry.addEntity(User.class);

		if (userId) {
			qry.setString("userId", search.getUserId() ); 
		}
		if (firstName) {
			qry.setString("firstName", search.getFirstName().toUpperCase() ); 
		}
		if (lastName) {
			qry.setString("lastName", search.getLastName().toUpperCase()); 
		}		
		if (nickName) {
			qry.setString("nickName", search.getNickName()); 
		}	
		if (status) {
			qry.setString("status", search.getStatus()); 
		}	
		if (secondaryStatus) {
			qry.setString("secondaryStatus", search.getSecondaryStatus()); 
		}
		
		
		
		if (createDate) {
			qry.setDate("createDate", search.getCreateDate());
		}
		if (startDate) {
			qry.setDate("startDate", search.getStartDate());
		}
		if (lastDate) {
			qry.setDate("lastDate", search.getLastDate());
		}
		
		
		if (deptCd) {
			qry.setString("deptCd", search.getDeptCd()); 
		}	
		if (division) {
			qry.setString("division", search.getDivision()); 
		}
		if (locationId) {
			qry.setString("locationCd", search.getLocationCd());
		}
		
		if (employeeId) {
			qry.setString("employeeId", search.getEmployeeId()); 
		}	
		if (orgId) {
			qry.setString("orgId", search.getOrgId()); 
		}	
		if (orgName) {
			qry.setString("orgName", search.getOrgName().toUpperCase()); 
		}
		if (phoneAreaCd) {
			qry.setString("phoneAreaCd", search.getPhoneAreaCd()); 
		}	
		if (phoneNbr) {
			qry.setString("phoneNbr", search.getPhoneNbr()); 
		}			
		if (emailAddress) {
			qry.setString("emailAddress", search.getEmailAddress().toUpperCase()); 
		}	
		if (principal) {
			qry.setString("principal", search.getPrincipal().toUpperCase()); 
		}
		if (domainId) {
			qry.setString("domainId", search.getDomainId()); 
		}		
		if (attributeName) {
			qry.setString("attributeName", search.getAttributeName().toUpperCase() ); 
		}
		if (attributeValue) {
			qry.setString("attributeValue", search.getAttributeValue().toUpperCase() ); 
		}		
		if (metadataElementId) {
			qry.setString("elementId", search.getAttributeElementId() ); 
		}
		if (showInSearch) {
			qry.setInteger("showInSearch", search.getShowInSearch() ); 
		}
		if (groupId) {
			qry.setParameterList("groupList", search.getGroupIdList());
			//qry.setString("groupId", search.getGroupId()); 
		}
		if (roleId) {
			qry.setParameterList("roleList", search.getRoleIdList());
			//qry.setString("role", search.getRoleId()); 
		}		
		if (classification) {
			qry.setString("classification", search.getClassification() ); 
		}
		if (userTypeInd) {
			qry.setString("userTypeInd", search.getUserTypeInd() ); 
		}		
		
		if (search.getMaxResultSize() != null && search.getMaxResultSize().intValue() > 0) {
			qry.setFetchSize(search.getMaxResultSize().intValue());
			qry.setMaxResults(search.getMaxResultSize().intValue());			
		} else {
			qry.setFetchSize(this.maxResultSetSize);
			qry.setMaxResults(this.maxResultSetSize);
		}
try{
		List<User> result = (List<User>) qry.list();
		if (result == null || result.size() == 0) {
			log.debug("search result is null");
			return null;
		}
		log.debug("search resultset size=" + result.size());
		return result;			
}catch(Exception e) {
	e.printStackTrace();
}
return null;

		
	}
	
	private List<User> defaultSearch(UserSearch search) {
		
		boolean firstName = false;
		boolean lastName = false;
		boolean nickName = false;
		boolean status = false;
		boolean secondaryStatus = false;
		boolean deptCd = false;
		boolean division = false;
		boolean phoneAreaCd = false;
		boolean phoneNbr = false;
		boolean employeeId = false;
		boolean groupId = false;
		boolean roleId = false;
		boolean emailAddress = false;
		boolean orgId = false;
		boolean userId = false;
		boolean principal = false;
		boolean domainId = false;
		boolean attributeName = false;
		boolean attributeValue = false;
		boolean metadataElementId = false;
		boolean showInSearch = false;
		boolean locationCd = false;
		
	    boolean userTypeInd = false;
	    boolean classification = false;
	    boolean orgName = false;
		   
	    boolean startDate = false;
	    boolean lastDate = false;
		   
	
		String select = " select DISTINCT u.USER_ID, u.TYPE_ID, " +
		" u.TITLE, u.MIDDLE_INIT, u.LAST_NAME, u.FIRST_NAME," + 
		" u.BIRTHDATE, u.STATUS, u.SECONDARY_STATUS, u.DEPT_NAME, u.DEPT_CD, " + 
		" u.LAST_UPDATE, u.CREATED_BY, u.CREATE_DATE, u.SEX, " + 
		" u.USER_TYPE_IND, u.SUFFIX, u.PREFIX, u.LAST_UPDATED_BY," + 
		" u.LOCATION_NAME, u.LOCATION_CD, u.EMPLOYEE_TYPE, u.EMPLOYEE_ID, " +
		" u.JOB_CODE, u.MANAGER_ID, u.COMPANY_OWNER_ID, u.COMPANY_ID, " +
		" u.LAST_DATE, u.START_DATE, u.COST_CENTER, u.DIVISION," +
		" u.PASSWORD_THEME, u.NICKNAME, u.MAIDEN_NAME, u.MAIL_CODE, " + 
		" u.COUNTRY, u.BLDG_NUM, u.STREET_DIRECTION, u.SUITE,  " +
		" u.ADDRESS1, u.ADDRESS2, u.ADDRESS3, u.ADDRESS4, u.ADDRESS5, u.ADDRESS6, u.ADDRESS7," +
		" u.CITY, u.STATE, u.POSTAL_CD, u.EMAIL_ADDRESS, u.ALTERNATE_ID, " +
		" u.PHONE_NBR, u.PHONE_EXT, u.AREA_CD, u.COUNTRY_CD, u.CLASSIFICATION, u.SHOW_IN_SEARCH " +
		" from 	USERS u " +
		"  		LEFT JOIN LOGIN lg ON ( lg.USER_ID = u.USER_ID) " + 
		"  		LEFT JOIN EMAIL_ADDRESS em ON ( em.PARENT_ID = u.USER_ID) " + 
		"  		LEFT JOIN PHONE p ON ( p.PARENT_ID = u.USER_ID) " + 
		"  		LEFT JOIN USER_ATTRIBUTES ua ON ( ua.USER_ID = u.USER_ID) " + 
		"  		LEFT JOIN USER_GRP g ON ( g.USER_ID = u.USER_ID) " + 
		"  		LEFT JOIN COMPANY c ON ( c.COMPANY_ID = u.COMPANY_ID) " + 
		"	 	LEFT JOIN USER_ROLE_VW urv on (u.USER_ID = urv.USER_ID) "  ;
 
	
		
		StringBuffer where = new StringBuffer();

		if (search.getShowInSearch() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.SHOW_IN_SEARCH = :showInSearch ");
			showInSearch = true;
		}
	
	
		if (search.getUserId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.USER_ID = :userId ");
			userId = true;
		}
		
		if (search.getUserTypeInd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.USER_TYPE_IND = :userTypeInd ");
			userTypeInd = true;
		}

		if (search.getClassification() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.CLASSIFICATION = :classification ");
			classification = true;
		}
		
		if (search.getLocationCd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.LOCATION_CD = :locationCd ");
			locationCd = true;
		}
		
		if (search.getFirstName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.FIRST_NAME like :firstName ");
			firstName = true;
		}
		if (search.getLastName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.LAST_NAME like :lastName ");
			lastName = true;
		}
		if (search.getNickName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.NICKNAME like :nickName ");
			nickName = true;
		}
		
		if (search.getStatus() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.STATUS = :status ");
			status = true;
		}

		if (search.getSecondaryStatus() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.SECONDARY_STATUS = :secondaryStatus ");
			secondaryStatus = true;
		}
		
		if (search.getStartDate() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.START_DATE = :startDate ");
			startDate = true;
		}
		if (search.getLastDate() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.LAST_DATE = :lastDate ");
			lastDate = true;
		}
		
		
		
		if (search.getDeptCd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.DEPT_CD = :deptCd ");
			deptCd = true;
		}
		if (search.getDivision() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.DIVISION = :division ");
			division = true;
		}
		
		if (search.getEmployeeId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.EMPLOYEE_ID = :employeeId ");
			employeeId =true;
		}
		if (search.getOrgId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" u.COMPANY_ID = :orgId ");
			orgId = true;
		}
		
		if (search.getOrgName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" c.COMPANY_NAME like :orgName ");
			orgName = true;
		}
		
		
		if (search.getPhoneAreaCd() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" p.AREA_CD = :phoneAreaCd ");
			phoneAreaCd =true;
		}
		if (search.getPhoneNbr() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" p.PHONE_NBR = :phoneNbr ");
			phoneNbr = true;
		}

		if (search.getEmailAddress() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			//where.append(" u.EMAIL_ADDRESS = :emailAddress ");
			where.append(" em.EMAIL_ADDRESS = :emailAddress ");
			emailAddress = true;
		}		
		if (!search.getGroupIdList().isEmpty()) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" g.GRP_ID in (:groupList) ");
			groupId =true;
		}
		if (!search.getRoleIdList().isEmpty()) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" urv.ROLE_ID in (:roleList) ");
			where.append(" and urv.SERVICE_ID = :domainId ");
			roleId = true;
		}

		/* Login  */
		if (search.getPrincipal() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" lg.LOGIN like :principal ");
			principal = true;
		}
		if (search.getDomainId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" lg.SERVICE_ID = :domainId ");
			domainId = true;
		}
		if (search.getLoggedIn() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			if (search.getLoggedIn().equalsIgnoreCase("Yes")) {
				where.append(" lg.LAST_LOGIN IS NOT NULL");
			}else {
				where.append(" lg.LAST_LOGIN IS NULL");
			}

		}
		
		
		/* User Attributes fields */
		if (search.getAttributeName() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" ua.NAME = :attributeName ");
			attributeName = true;
		}
		if (search.getAttributeValue() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" ua.VALUE like :attributeValue ");
			attributeValue = true;
		}
		if (search.getAttributeElementId() != null) {
			if (where.length() > 0 ) {
				where.append(" and ");
			}
			where.append(" ua.METADATA_ID = :elementId ");
			metadataElementId = true;
		}
		
		if ( where.length() > 0) {
			select = select + " WHERE " + where.toString() ;
		}
		
		select = select + "  ORDER BY u.LAST_NAME, u.FIRST_NAME"; 
		log.debug("search select: " + select);
		
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery qry = session.createSQLQuery(select);
		qry.addEntity(User.class);

		if (userId) {
			qry.setString("userId", search.getUserId() ); 
		}
		if (firstName) {
			qry.setString("firstName", search.getFirstName() ); 
		}
		if (lastName) {
			qry.setString("lastName", search.getLastName()); 
		}		
		if (nickName) {
			qry.setString("nickName", search.getNickName()); 
		}	
		if (status) {
			qry.setString("status", search.getStatus()); 
		}	
		if (secondaryStatus) {
			qry.setString("secondaryStatus", search.getSecondaryStatus()); 
		}
		
		if (startDate) {
			qry.setDate("startDate", search.getStartDate());
		}
		if (lastDate) {
			qry.setDate("lastDate", search.getLastDate());
		}

		
		if (deptCd) {
			qry.setString("deptCd", search.getDeptCd()); 
		}	
		
		if (locationCd) {
			qry.setString("locationCd", search.getLocationCd());
		}
		
		if (division) {
			qry.setString("division", search.getDivision()); 
		}
		
		if (employeeId) {
			qry.setString("employeeId", search.getEmployeeId()); 
		}	
		if (orgId) {
			qry.setString("orgId", search.getOrgId()); 
		}	
		if (orgName) {
			qry.setString("orgName", search.getOrgName());
		}
		if (phoneAreaCd) {
			qry.setString("phoneAreaCd", search.getPhoneAreaCd()); 
		}	
		if (phoneNbr) {
			qry.setString("phoneNbr", search.getPhoneNbr()); 
		}			
		if (emailAddress) {
			qry.setString("emailAddress", search.getEmailAddress()); 
		}	
		if (principal) {
			qry.setString("principal", search.getPrincipal()); 
		}
		if (domainId) {
			qry.setString("domainId", search.getDomainId()); 
		}		
		if (attributeName) {
			qry.setString("attributeName", search.getAttributeName() ); 
		}
		if (attributeValue) {
			qry.setString("attributeValue", search.getAttributeValue() ); 
		}		
		if (metadataElementId) {
			qry.setString("elementId", search.getAttributeElementId() ); 
		}
		if (showInSearch) {
			qry.setInteger("showInSearch", search.getShowInSearch() ); 
		}
		if (groupId) {
			qry.setParameterList("groupList", search.getGroupIdList());
			//qry.setString("groupId", search.getGroupId()); 
		}
		if (roleId) {
			qry.setParameterList("roleList", search.getRoleIdList());
			//qry.setString("role", search.getRoleId()); 
		}		
		if (classification) {
			qry.setString("classification", search.getClassification() ); 
		}
		if (userTypeInd) {
			qry.setString("userTypeInd", search.getUserTypeInd() ); 
		}		
		
		if (search.getMaxResultSize() != null && search.getMaxResultSize().intValue() > 0) {
			qry.setFetchSize(search.getMaxResultSize().intValue());
			qry.setMaxResults(search.getMaxResultSize().intValue());			
		} else {
			qry.setFetchSize(this.maxResultSetSize);
			qry.setMaxResults(this.maxResultSetSize);
		}
try{
		List<User> result = (List<User>) qry.list();
		if (result == null || result.size() == 0) {
			log.debug("search result is null");
			return null;
		}
		log.debug("search resultset size=" + result.size());
		return result;			
}catch(Exception e) {
	e.printStackTrace();
}
return null;

		
	}
	

	public List<User> findByOrganization(String orgId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.User u " +
				" where u.companyId = :orgId order by u.lastName asc");
		qry.setString("orgId", orgId);
		List<User> results = (List<User>)qry.list();
		return results;
	
	}
	
	public List<User> findStaff(String supervisorId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
				" inner join org.openiam.idm.srvc.user.dto.User u " + 
				" where s.supervisor = :supervisorId and  s.staff = u.userId order by u.userId asc");
		qry.setString("supervisor", supervisorId);
		List<User> results = (List<User>)qry.list();
		return results;
	}

	public List<User> findSupervisors(String staffId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
				" inner join org.openiam.idm.srvc.user.dto.User u " + 
				" where s.staff = :staffId and  s.supervisor = u.userId order by u.userId asc");
		qry.setString("supervisor", staffId);
		List<User> results = (List<User>)qry.list();
		return results;
	}	
	
	
	public SequenceGenDAO getSeqGenDao() {
		return seqGenDao;
	}

	public void setSeqGenDao(SequenceGenDAO seqGenDao) {
		this.seqGenDao = seqGenDao;
	}


	public String getEmailSearchVal() {
		return emailSearchVal;
	}


	public void setEmailSearchVal(String emailSearchVal) {
		this.emailSearchVal = emailSearchVal;
	}


	public String getPhoneSearchVal() {
		return phoneSearchVal;
	}


	public void setPhoneSearchVal(String phoneSearchVal) {
		this.phoneSearchVal = phoneSearchVal;
	}


	public Integer getMaxResultSetSize() {
		return maxResultSetSize;
	}


	public void setMaxResultSetSize(Integer maxResultSetSize) {
		this.maxResultSetSize = maxResultSetSize;
	}


	public String getDbType() {
		return dbType;
	}


	public void setDbType(String dbType) {
		this.dbType = dbType;
	}




	
}
