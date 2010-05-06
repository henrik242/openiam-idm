package org.openiam.idm.srvc.user;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.util.db.*;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;;

public class UserMgrSearchTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user10;

	static String userId;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBean");
		user10 = (User)ctx.getBean("userBean10");
	
		
	} 



	public void testUserSearch_lastName() {
		

		UserSearch search = new UserSearch();
		search.setLastName("S%");
		List results = userMgr.search(search);
		assertNotNull(results);
		
		// iterate through list to ensure fetching.
		
		int size = results.size();
		for (int i = 0; i < size; i++) {
			User usr = (User) results.get(i);
			System.out.println ( usr.getFirstName() + " " + usr.getLastName() + " email=" + usr.getEmail() );
		 
		}
		
	}

	public void testUserSearch_firstName() {
		
		QueryCriteria qc = new QueryCriteria();
		
		//Search search = new SearchImpl();
		UserSearch search = new UserSearch();
		search.setFirstName("s%");
		//search.addSearchCriteria(qc);
		List results = userMgr.search(search);
		assertNotNull(results);
		this.assertEquals(3, results.size());

		int size = results.size();
		for (int i = 0; i < size; i++) {
			User usr = (User) results.get(i);
			System.out.println ( usr.getFirstName() + " " + usr.getLastName() );
		}
		
	}

	public void testUserSearch_firstAndLastName() {
		
		UserSearch search = new UserSearch();

		search.setFirstName("J%");
		search.setLastName("M%");
		
		List results = userMgr.search(search);
		assertNotNull(results);
		this.assertEquals(5, results.size());
		
	}


	public void testUserSearch_Status() {
		
		UserSearch search = new UserSearch();
		search.setStatus("APPROVED");

		
		List results = userMgr.search(search);
		assertNotNull(results);
		this.assertEquals(8, results.size());
		
	}

public void testUserInGroup() {
	UserSearch search = new UserSearch();
	List<String> groupList = new ArrayList<String>();
	groupList.add("END_USER_GRP");
	search.setGroupIdList(groupList);
	List results = userMgr.search(search);
	assertNotNull(results);
	this.assertEquals(1, results.size());
		
	}
	public void testUserInRole() {
	UserSearch search = new UserSearch();
	List<String> roleList = new ArrayList<String>();
	roleList.add("END_USER");
	search.setRoleIdList(roleList);
	List results = userMgr.search(search);
	assertNotNull(results);
	this.assertEquals(1, results.size());
		
	}

 

}








