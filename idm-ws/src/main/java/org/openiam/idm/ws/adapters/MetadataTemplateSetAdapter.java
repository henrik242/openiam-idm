package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import org.openiam.idm.srvc.meta.dto.*;

public class MetadataTemplateSetAdapter extends XmlAdapter<List<MetadataTemplate>, Set<MetadataTemplate>> {

	@Override
	public List<MetadataTemplate> marshal(Set<MetadataTemplate> b) throws Exception {		
		return new ArrayList<MetadataTemplate>(b);
	}

	@Override
	public Set<MetadataTemplate> unmarshal(List<MetadataTemplate> v) throws Exception {
		return new HashSet<MetadataTemplate>(v);
	}

}
