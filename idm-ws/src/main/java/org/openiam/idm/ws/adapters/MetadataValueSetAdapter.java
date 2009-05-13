package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import org.openiam.idm.srvc.meta.dto.*;

public class MetadataValueSetAdapter extends XmlAdapter<List<MetadataValue>, Set<MetadataValue>> {

	@Override
	public List<MetadataValue> marshal(Set<MetadataValue> b) throws Exception {		
		return new ArrayList<MetadataValue>(b);
	}

	@Override
	public Set<MetadataValue> unmarshal(List<MetadataValue> v) throws Exception {
		return new HashSet<MetadataValue>(v);
	}

}
