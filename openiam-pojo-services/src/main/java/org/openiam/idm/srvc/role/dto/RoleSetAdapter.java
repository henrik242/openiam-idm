package org.openiam.idm.srvc.role.dto;



	import javax.xml.bind.annotation.adapters.XmlAdapter;
	import java.util.*;


public class RoleSetAdapter extends XmlAdapter<RoleSet, Set<Role>> {

	@Override
	public RoleSet  marshal(Set<Role> b) throws Exception {
		RoleSet v = new RoleSet();
		if (b==null) return v;
				
		for (Iterator<Role> iterator = b.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			RoleSet.RoleObj obj = new RoleSet.RoleObj();
			obj.setRole(role);
			v.getRoleObj().add(obj);
		}	
		return v;
	}

	@Override
	public Set<Role> unmarshal(RoleSet v) throws Exception {
		Set<Role> b = new HashSet<Role>();
		if (v==null) return b;
		
		List<RoleSet.RoleObj> l = v.getRoleObj();
		for (Iterator<RoleSet.RoleObj> iterator = l.iterator(); iterator.hasNext();) {
			RoleSet.RoleObj obj = (RoleSet.RoleObj) iterator.next();
			b.add(obj.getRole());
		}
		return b;		
	}
}

