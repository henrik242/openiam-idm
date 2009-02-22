package org.openiam.idm.srvc.org.dto;

	import javax.xml.bind.annotation.adapters.XmlAdapter;
	import java.util.*;


	public class OrganizationAttributeSetAdapter extends XmlAdapter<OrganizationAttributeSet, Set<OrganizationAttribute>> {

		@Override
		public OrganizationAttributeSet  marshal(Set<OrganizationAttribute> b) throws Exception {
			OrganizationAttributeSet v = new OrganizationAttributeSet();
			if (b==null) return v;
					
			for (Iterator<OrganizationAttribute> iterator = b.iterator(); iterator.hasNext();) {
				OrganizationAttribute organizationAttribute = (OrganizationAttribute) iterator.next();
				OrganizationAttributeSet.OrganizationAttributeObj obj = new OrganizationAttributeSet.OrganizationAttributeObj();
				obj.setOrganizationAttribute(organizationAttribute);
				v.getOrganizationAttributeObj().add(obj);
			}	
			return v;
		}

		@Override
		public Set<OrganizationAttribute> unmarshal(OrganizationAttributeSet v) throws Exception {
			Set<OrganizationAttribute> b = new HashSet<OrganizationAttribute>();
			if (v==null) return b;
			
			List<OrganizationAttributeSet.OrganizationAttributeObj> l = v.getOrganizationAttributeObj();
			for (Iterator<OrganizationAttributeSet.OrganizationAttributeObj> iterator = l.iterator(); iterator.hasNext();) {
				OrganizationAttributeSet.OrganizationAttributeObj obj = (OrganizationAttributeSet.OrganizationAttributeObj) iterator.next();
				b.add(obj.getOrganizationAttribute());
			}
			return b;		
		}
	}

