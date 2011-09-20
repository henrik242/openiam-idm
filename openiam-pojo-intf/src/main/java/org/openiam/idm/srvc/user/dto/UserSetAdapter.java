package org.openiam.idm.srvc.user.dto;

	import javax.xml.bind.annotation.adapters.XmlAdapter;
	import java.util.*;


	public class UserSetAdapter extends XmlAdapter<UserSet, Set<User>> {

		@Override
		public UserSet  marshal(Set<User> b) throws Exception {
			UserSet v = new UserSet();
			if (b==null) return v;
					
			for (Iterator<User> iterator = b.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				UserSet.UserObj obj = new UserSet.UserObj();
				obj.setUser(user);
				v.getUserObj().add(obj);
			}	
			return v;
		}

		@Override
		public Set<User> unmarshal(UserSet v) throws Exception {
			Set<User> b = new HashSet<User>();
			if (v==null) return b;
			
			List<UserSet.UserObj> l = v.getUserObj();
			for (Iterator<UserSet.UserObj> iterator = l.iterator(); iterator.hasNext();) {
				UserSet.UserObj obj = (UserSet.UserObj) iterator.next();
				b.add(obj.getUser());
			}
			return b;		
		}
	}
