package org.openiam.idm.srvc.role.dto;

import java.util.Set;

	import javax.xml.bind.annotation.adapters.XmlAdapter;
	import java.util.*;


public class RoleAttributeSetAdapter extends XmlAdapter<RoleAttributeSet, Set<RoleAttribute>> {

	@Override
	public RoleAttributeSet  marshal(Set<RoleAttribute> b) throws Exception {
		RoleAttributeSet v = new RoleAttributeSet();
		if (b==null) return v;
				
		for (Iterator<RoleAttribute> iterator = b.iterator(); iterator.hasNext();) {
			RoleAttribute roleAttribute = (RoleAttribute) iterator.next();
			RoleAttributeSet.RoleAttributeObj obj = new RoleAttributeSet.RoleAttributeObj();
			obj.setRoleAttribute(roleAttribute);
			v.getRoleAttributeObj().add(obj);
		}	
		return v;
	}

	@Override
	public Set<RoleAttribute> unmarshal(RoleAttributeSet v) throws Exception {
		Set<RoleAttribute> b = new HashSet<RoleAttribute>();
		if (v==null) return b;
		
		List<RoleAttributeSet.RoleAttributeObj> l = v.getRoleAttributeObj();
		for (Iterator<RoleAttributeSet.RoleAttributeObj> iterator = l.iterator(); iterator.hasNext();) {
			RoleAttributeSet.RoleAttributeObj obj = (RoleAttributeSet.RoleAttributeObj) iterator.next();
			b.add(obj.getRoleAttribute());
		}
		return b;		
	}
}

	
