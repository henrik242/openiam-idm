package org.openiam.idm.srvc.grp.dto;

	import java.util.Map;
	import java.util.HashMap;

	import javax.xml.bind.annotation.adapters.XmlAdapter;

	public class GroupAttributeMapAdapter extends XmlAdapter<GroupAttributeMap, Map<String, GroupAttribute>> {

		@Override
		public GroupAttributeMap marshal(Map<String, GroupAttribute> v) throws Exception {
	        GroupAttributeMap map = new GroupAttributeMap();
	        if (v==null) return map;
	        
	        for (Map.Entry<String, GroupAttribute> e : v.entrySet()) { 
	            GroupAttributeMap.GroupAttributeEntry entry = new GroupAttributeMap.GroupAttributeEntry();
	            entry.setGroupAttribute(e.getValue());
	            entry.setKey(e.getKey());
	            map.getGroupAttributeEntry().add(entry);
	        }
	        return map;
		}

		@Override
	    public Map<String, GroupAttribute> unmarshal(GroupAttributeMap v) throws Exception {
	        Map<String, GroupAttribute> map = new HashMap<String, GroupAttribute>();
	        if (v==null) return map;
	        
	        for (GroupAttributeMap.GroupAttributeEntry e : v.getGroupAttributeEntry()) {
	        	map.put(e.getKey(),e.getGroupAttribute());
	        }
	        return map;
	    }


	}

