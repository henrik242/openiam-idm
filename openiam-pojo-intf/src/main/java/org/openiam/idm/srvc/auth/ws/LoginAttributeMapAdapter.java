package org.openiam.idm.srvc.auth.ws;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.openiam.idm.srvc.auth.dto.LoginAttribute;

public class LoginAttributeMapAdapter extends XmlAdapter<LoginAttributeMap, Map<String,LoginAttribute> > {
    
	public LoginAttributeMap marshal(Map<String, LoginAttribute> v) throws Exception {
        LoginAttributeMap map = new LoginAttributeMap();
        if (v==null) return map;
        
        for (Map.Entry<String, LoginAttribute> e : v.entrySet()) { 
            LoginAttributeMap.LoginAttributeEntry oae = new LoginAttributeMap.LoginAttributeEntry();
            oae.setLoginAttribute(e.getValue());
            oae.setId(e.getKey());
            map.getLoginAttributeEntry().add(oae);
        }
        return map;
    }

    public Map<String, LoginAttribute> unmarshal(LoginAttributeMap v) throws Exception {
        Map<String, LoginAttribute> map = new LinkedHashMap<String, LoginAttribute>();
        if (v==null) return map;
        for (LoginAttributeMap.LoginAttributeEntry e : v.getLoginAttributeEntry()) {
        	map.put(e.getId(),e.getLoginAttribute());
        }
        return map;
    }
}
