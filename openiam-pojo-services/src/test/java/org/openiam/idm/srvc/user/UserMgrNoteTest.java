package org.openiam.idm.srvc.user;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserMgrNoteTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user10;
	UserNote userNote, userNote2;

	static String userId;
	static String noteId, noteId2;
	
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
		userNote = (UserNote)ctx.getBean("userNote");
		userNote2 = (UserNote)ctx.getBean("userNote2");		
		
	} 

	
	@Test
	public void testAddUser() {
		user = userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		userId = user.getUserId();
		
	}
	
	

	
	public void testAddUserNote() {
		testAddUser();
		
		User usr = userMgr.getUserWithDependent(userId,false);
		Set<UserNote> noteSet = usr.getUserNotes();
		userNote.setUserId(usr.getUserId());
		noteSet.add(userNote);
		userMgr.updateUser(usr);		
		
		// check that it worked
		assertNotNull( userMgr.getAllNotes(userId) ); 
	}

	public void testAddUserNoteDirect() {
		User usr = userMgr.getUserWithDependent(userId,false);
		userNote2.setUserId(usr.getUserId());
		
		UserNote note = userMgr.addNote(userNote2);
		noteId2 = note.getUserNoteId();
		noteId = String.valueOf( Integer.parseInt(noteId2)-1 );
		
		//	 check that it worked
		UserNote testNote = userMgr.getNote(noteId2);
		assertNotNull(testNote);	
	
	}	

	public void testGetUserNotes() {
		List<UserNote> userNoteList = userMgr.getAllNotes(userId);
		assertEquals(2, userNoteList.size());

	}	
	
	public void testUpdateUserNote() {
		UserNote updNote = null;
		User usr = userMgr.getUserWithDependent(userId,false);
		
		updNote = usr.getUserNote(noteId);
		updNote.setDescription("updatedNotes for this user.");
		usr.saveNote(updNote);
		
		userMgr.updateUser(usr);			

		//	 check that it worked
		UserNote testNote = userMgr.getNote(noteId);
		assertNotNull(testNote);	
		this.assertEquals("updatedNotes for this user.", testNote.getDescription());

		
	}
	
	
	public void testUpdateUserNoteDirect() {
		UserNote userNote2 = userMgr.getNote(noteId2);
		userNote2.setDescription("2nd note was updated..");
		userMgr.updateNote(userNote2);

		//	 check that it worked
		UserNote testNote = userMgr.getNote(noteId2);
		assertNotNull(testNote);	
		this.assertEquals("2nd note was updated..", testNote.getDescription());

		
	}

	
	public void testRemoveUserNote() {
		User usr = userMgr.getUserWithDependent(userId,false);
		Set<UserNote> noteSet = usr.getUserNotes();
		Iterator<UserNote> it = noteSet.iterator();
		while(it.hasNext()) {
			UserNote nt = it.next();
			if (nt.getUserNoteId().equals(noteId)){
				//noteSet.remove(nt);
				it.remove();
			}
		}	
		userMgr.updateUser(usr);			

		UserNote testNote = userMgr.getNote(noteId);
		assertNull(testNote);	

	
	}	

	public void testRemoveUserNoteDirect() {
		UserNote userNote2 = userMgr.getNote(noteId2);
		
		userMgr.removeNote(userNote2);

		UserNote testNote = userMgr.getNote(noteId2);
		assertNull(testNote);	
					
	}	

	public void testRemoveAllUserNotesDirect() {
		
		userMgr.removeAllNotes(userId); ;
		
		List<UserNote> noteList = userMgr.getAllNotes(userId);
		assertNull(noteList);	
					
	}
	

		
}








