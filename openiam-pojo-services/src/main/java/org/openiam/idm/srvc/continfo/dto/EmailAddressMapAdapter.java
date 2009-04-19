package org.openiam.idm.srvc.continfo.dto;

import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EmailAddressMapAdapter extends XmlAdapter<EmailAddressMap, Map<String, EmailAddress>> {

	@Override
	public EmailAddressMap marshal(Map<String, EmailAddress> v) throws Exception {
        EmailAddressMap map = new EmailAddressMap();
        if (v==null) return map;
        
        for (Map.Entry<String, EmailAddress> e : v.entrySet()) { 
            EmailAddressMap.EmailAddressEntry entry = new EmailAddressMap.EmailAddressEntry();
            entry.setEmailAddress(e.getValue());
            entry.setKey(e.getKey());
            map.getEmailAddressEntry().add(entry);
        }
        return map;
	}

	@Override
    public Map<String, EmailAddress> unmarshal(EmailAddressMap v) throws Exception {
        Map<String, EmailAddress> map = new HashMap<String, EmailAddress>();
        if (v==null) return map;
        
        for (EmailAddressMap.EmailAddressEntry e : v.getEmailAddressEntry()) {
        	map.put(e.getKey(),e.getEmailAddress());
        }
        return map;
    }


}
