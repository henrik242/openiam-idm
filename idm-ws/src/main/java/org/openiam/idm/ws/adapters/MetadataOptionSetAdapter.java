package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import org.openiam.idm.srvc.meta.dto.*;

public class MetadataOptionSetAdapter extends XmlAdapter<List<MetadataOption>, Set<MetadataOption>> {

	@Override
	public List<MetadataOption> marshal(Set<MetadataOption> b) throws Exception {		
		return new ArrayList<MetadataOption>(b);
	}

	@Override
	public Set<MetadataOption> unmarshal(List<MetadataOption> v) throws Exception {
		return new HashSet<MetadataOption>(v);
	}

}
