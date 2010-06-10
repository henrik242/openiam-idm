package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import org.openiam.idm.srvc.user.dto.*;

public class UserAttributeSetAdapter extends XmlAdapter<List<UserAttribute>, Set<UserAttribute>> {

	@Override
	public List<UserAttribute> marshal(Set<UserAttribute> b) throws Exception {		
		return new ArrayList<UserAttribute>(b);
	}

	@Override
	public Set<UserAttribute> unmarshal(List<UserAttribute> v) throws Exception {
		return new HashSet<UserAttribute>(v);
	}

}
