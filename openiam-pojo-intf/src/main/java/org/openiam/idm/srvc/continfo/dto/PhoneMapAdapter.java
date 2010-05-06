package org.openiam.idm.srvc.continfo.dto;

import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;



public class PhoneMapAdapter extends XmlAdapter<PhoneMap, Map<String, Phone>> {

	@Override
	public PhoneMap marshal(Map<String, Phone> v) throws Exception {
        PhoneMap map = new PhoneMap();
        if (v==null) return map;
        
        for (Map.Entry<String, Phone> e : v.entrySet()) { 
            PhoneMap.PhoneEntry entry = new PhoneMap.PhoneEntry();
            entry.setPhone(e.getValue());
            entry.setKey(e.getKey());
            map.getPhoneEntry().add(entry);
        }
        return map;
	}

	@Override
    public Map<String, Phone> unmarshal(PhoneMap v) throws Exception {
        Map<String, Phone> map = new HashMap<String, Phone>();
        if (v==null) return map;
        
        for (PhoneMap.PhoneEntry e : v.getPhoneEntry()) {
        	map.put(e.getKey(),e.getPhone());
        }
        return map;
    }


}
