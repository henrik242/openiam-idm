package org.openiam.idm.srvc.continfo.dto;


import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AddressMapAdapter extends XmlAdapter<AddressMap, Map<String, Address>> {

	@Override
	public AddressMap marshal(Map<String, Address> v) throws Exception {
        AddressMap map = new AddressMap();
        if (v==null) return map;
        
        for (Map.Entry<String, Address> e : v.entrySet()) { 
            AddressMap.AddressEntry entry = new AddressMap.AddressEntry();
            entry.setAddress(e.getValue());
            entry.setKey(e.getKey());
            map.getAddressEntry().add(entry);
        }
        return map;
	}

	@Override
    public Map<String, Address> unmarshal(AddressMap v) throws Exception {
        Map<String, Address> map = new HashMap<String, Address>();
        if (v==null) return map;
        
        for (AddressMap.AddressEntry e : v.getAddressEntry()) {
        	map.put(e.getKey(),e.getAddress());
        }
        return map;
    }


}
