package org.openiam.idm.srvc.user.dto;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;

public class UserNoteSetAdapter extends XmlAdapter<UserNoteSet, Set<UserNote>> {

	@Override
	public UserNoteSet  marshal(Set<UserNote> b) throws Exception {
		UserNoteSet v = new UserNoteSet();
		if (b==null) return v;
				
		for (Iterator<UserNote> iterator = b.iterator(); iterator.hasNext();) {
			UserNote userNote = (UserNote) iterator.next();
			UserNoteSet.UserNoteItem item = new UserNoteSet.UserNoteItem();
			item.setUserNote(userNote);
			v.getUserNoteItem().add(item);
		}	
		return v;
	}

	@Override
	public Set<UserNote> unmarshal(UserNoteSet v) throws Exception {
		Set<UserNote> b = new HashSet<UserNote>();
		if (v==null) return b;
		
		List<UserNoteSet.UserNoteItem> l = v.getUserNoteItem();
		for (Iterator<UserNoteSet.UserNoteItem> iterator = l.iterator(); iterator.hasNext();) {
			UserNoteSet.UserNoteItem item = (UserNoteSet.UserNoteItem) iterator.next();
			b.add(item.getUserNote());
		}
		return b;		
	}

}



