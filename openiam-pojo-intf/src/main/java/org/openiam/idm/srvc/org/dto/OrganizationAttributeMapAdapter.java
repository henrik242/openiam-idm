package org.openiam.idm.srvc.org.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class OrganizationAttributeMapAdapter extends XmlAdapter<OrganizationAttributeMap, Map<String, OrganizationAttribute>> {
    
	public OrganizationAttributeMap marshal(Map<String, OrganizationAttribute> v) throws Exception {
        OrganizationAttributeMap map = new OrganizationAttributeMap();
        if (v==null) return map;
        
        for (Map.Entry<String, OrganizationAttribute> e : v.entrySet()) { 
            OrganizationAttributeMap.OrganizationAttributeEntry oae = new OrganizationAttributeMap.OrganizationAttributeEntry();
            oae.setOrganizationAttribute(e.getValue());
            oae.setId(e.getKey());
            map.getOrganizationAttributeEntry().add(oae);
        }
        return map;
    }

    public Map<String, OrganizationAttribute> unmarshal(OrganizationAttributeMap v) throws Exception {
        Map<String, OrganizationAttribute> map = new LinkedHashMap<String, OrganizationAttribute>();
        if (v==null) return map;
        for (OrganizationAttributeMap.OrganizationAttributeEntry e : v.getOrganizationAttributeEntry()) {
        	map.put(e.getId(),e.getOrganizationAttribute());
        }
        return map;
    }
}
