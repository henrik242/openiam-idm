package org.openiam.idm.srvc.user.dto;

import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UserAttributeMapAdapter extends XmlAdapter<UserAttributeMap, Map<String, UserAttribute>> {

	@Override
	public UserAttributeMap marshal(Map<String, UserAttribute> v) throws Exception {
        UserAttributeMap map = new UserAttributeMap();
        if (v==null) return map;
        
        for (Map.Entry<String, UserAttribute> e : v.entrySet()) { 
            UserAttributeMap.UserAttributeEntry entry = new UserAttributeMap.UserAttributeEntry();
            entry.setUserAttribute(e.getValue());
            entry.setKey(e.getKey());
            map.getUserAttributeEntry().add(entry);
        }
        return map;
	}

	@Override
    public Map<String, UserAttribute> unmarshal(UserAttributeMap v) throws Exception {
        Map<String, UserAttribute> map = new HashMap<String, UserAttribute>();
        if (v==null) return map;
        
        for (UserAttributeMap.UserAttributeEntry e : v.getUserAttributeEntry()) {
        	map.put(e.getKey(),e.getUserAttribute());
        }
        return map;
    }


}
