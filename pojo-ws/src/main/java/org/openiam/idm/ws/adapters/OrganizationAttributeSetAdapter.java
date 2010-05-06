package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import org.openiam.idm.srvc.org.dto.*;

public class OrganizationAttributeSetAdapter extends XmlAdapter<List<OrganizationAttribute>, Set<OrganizationAttribute>> {

	@Override
	public List<OrganizationAttribute> marshal(Set<OrganizationAttribute> b) throws Exception {		
		return new ArrayList<OrganizationAttribute>(b);
	}

	@Override
	public Set<OrganizationAttribute> unmarshal(List<OrganizationAttribute> v) throws Exception {
		return new HashSet<OrganizationAttribute>(v);
	}

}