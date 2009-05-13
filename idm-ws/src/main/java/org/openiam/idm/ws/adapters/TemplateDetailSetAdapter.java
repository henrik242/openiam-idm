package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import org.openiam.idm.srvc.meta.dto.*;

public class TemplateDetailSetAdapter extends XmlAdapter<List<TemplateDetail>, Set<TemplateDetail>> {

	@Override
	public List<TemplateDetail> marshal(Set<TemplateDetail> b) throws Exception {		
		return new ArrayList<TemplateDetail>(b);
	}

	@Override
	public Set<TemplateDetail> unmarshal(List<TemplateDetail> v) throws Exception {
		return new HashSet<TemplateDetail>(v);
	}

}
