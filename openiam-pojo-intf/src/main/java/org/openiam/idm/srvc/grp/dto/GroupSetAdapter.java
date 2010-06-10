package org.openiam.idm.srvc.grp.dto;



import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;


public class GroupSetAdapter extends XmlAdapter<GroupSet, Set<Group>> {

@Override
public GroupSet  marshal(Set<Group> b) throws Exception {
	GroupSet v = new GroupSet();
	if (b==null) return v;
			
	for (Iterator<Group> iterator = b.iterator(); iterator.hasNext();) {
		Group group = (Group) iterator.next();
		GroupSet.GroupObj obj = new GroupSet.GroupObj();
		obj.setGroup(group);
		v.getGroupObj().add(obj);
	}	
	return v;
}

@Override
public Set<Group> unmarshal(GroupSet v) throws Exception {
	Set<Group> b = new HashSet<Group>();
	if (v==null) return b;
	
	List<GroupSet.GroupObj> l = v.getGroupObj();
	for (Iterator<GroupSet.GroupObj> iterator = l.iterator(); iterator.hasNext();) {
		GroupSet.GroupObj obj = (GroupSet.GroupObj) iterator.next();
		b.add(obj.getGroup());
	}
	return b;		
}
}

