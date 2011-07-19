package org.openiam.idm.srvc.grp.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "groupAttributeMap", propOrder = {
	    "groupAttributeEntry"
	})
	public class GroupAttributeMap {

	    protected List<GroupAttributeMap.GroupAttributeEntry> groupAttributeEntry;

	    public List<GroupAttributeMap.GroupAttributeEntry> getGroupAttributeEntry() {
	        if (groupAttributeEntry == null) {
	            groupAttributeEntry = new ArrayList<GroupAttributeMap.GroupAttributeEntry>();
	        }
	        return this.groupAttributeEntry;
	    }


	    @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "groupAttribute"
	    })
	    public static class GroupAttributeEntry {

	        protected GroupAttribute groupAttribute;
	        @XmlAttribute
	        protected String key;

	        /**
	         * Gets the value of the groupAttribute property.
	         * 
	         * @return
	         *     possible object is
	         *     {@link GroupAttribute }
	         *     
	         */
	        public GroupAttribute getGroupAttribute() {
	            return groupAttribute;
	        }

	        /**
	         * Sets the value of the groupAttribute property.
	         * 
	         * @param value
	         *     allowed object is
	         *     {@link GroupAttribute }
	         *     
	         */
	        public void setGroupAttribute(GroupAttribute value) {
	            this.groupAttribute = value;
	        }

	        /**
	         * Gets the value of the key property.
	         * 
	         * @return
	         *     possible object is
	         *     {@link String }
	         *     
	         */
	        public String getKey() {
	            return key;
	        }

	        /**
	         * Sets the value of the key property.
	         * 
	         * @param value
	         *     allowed object is
	         *     {@link String }
	         *     
	         */
	        public void setKey(String value) {
	            this.key = value;
	        }

	    }

	}

