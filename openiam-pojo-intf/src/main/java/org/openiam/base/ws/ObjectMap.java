
package org.openiam.base.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectMap", propOrder = {
    "objEntry"
})
public class ObjectMap {

    protected List<ObjectMap.ObjectEntry> objEntry;

    public List<ObjectMap.ObjectEntry> getObjectEntry() {
        if (objEntry == null) {
            objEntry = new ArrayList<ObjectMap.ObjectEntry>();
        }
        return this.objEntry;
    }



    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "obj"
    })
    public static class ObjectEntry {

        protected Object obj;
        @XmlAttribute
        protected String key;

        /**
         * Gets the value of the userAttribute property.
         * 
         * @return
         *     possible object is
         *     {@link UserAttribute }
         *     
         */
        public Object getObject() {
            return obj;
        }

        /**
         * Sets the value of the userAttribute property.
         * 
         * @param value
         *     allowed object is
         *     {@link UserAttribute }
         *     
         */
        public void setObject(Object value) {
            this.obj = value;
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
