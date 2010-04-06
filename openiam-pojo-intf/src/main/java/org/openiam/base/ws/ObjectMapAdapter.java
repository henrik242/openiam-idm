package org.openiam.base.ws;

import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ObjectMapAdapter extends XmlAdapter<ObjectMap, Map<String, Object>> {

	@Override
	public ObjectMap marshal(Map<String, Object> v) throws Exception {
        ObjectMap map = new ObjectMap();
        if (v==null) return map;
        
        for (Map.Entry<String, Object> e : v.entrySet()) { 
            ObjectMap.ObjectEntry entry = new ObjectMap.ObjectEntry();
            entry.setObject(e.getValue());
            entry.setKey(e.getKey());
            map.getObjectEntry().add(entry);
        }
        return map;
	}

	@Override
    public Map<String, Object> unmarshal(ObjectMap v) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (v==null) return map;
        
        for (ObjectMap.ObjectEntry e : v.getObjectEntry()) {
        	map.put(e.getKey(),e.getObject());
        }
        return map;
    }


}
