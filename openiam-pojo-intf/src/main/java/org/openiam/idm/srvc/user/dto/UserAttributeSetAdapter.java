package org.openiam.idm.srvc.user.dto;

	import javax.xml.bind.annotation.adapters.XmlAdapter;
	import java.util.*;


	public class UserAttributeSetAdapter extends XmlAdapter<UserAttributeSet, Set<UserAttribute>> {

		@Override
		public UserAttributeSet  marshal(Set<UserAttribute> b) throws Exception {
			UserAttributeSet v = new UserAttributeSet();
			if (b==null) return v;
					
			for (Iterator<UserAttribute> iterator = b.iterator(); iterator.hasNext();) {
				UserAttribute userAttribute = (UserAttribute) iterator.next();
				UserAttributeSet.UserAttributeObj obj = new UserAttributeSet.UserAttributeObj();
				obj.setUserAttribute(userAttribute);
				v.getUserAttributeObj().add(obj);
			}	
			return v;
		}

		@Override
		public Set<UserAttribute> unmarshal(UserAttributeSet v) throws Exception {
			Set<UserAttribute> b = new HashSet<UserAttribute>();
			if (v==null) return b;
			
			List<UserAttributeSet.UserAttributeObj> l = v.getUserAttributeObj();
			for (Iterator<UserAttributeSet.UserAttributeObj> iterator = l.iterator(); iterator.hasNext();) {
				UserAttributeSet.UserAttributeObj obj = (UserAttributeSet.UserAttributeObj) iterator.next();
				b.add(obj.getUserAttribute());
			}
			return b;		
		}
	}

