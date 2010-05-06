
package oasis.names.tc.spml._2._0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModificationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ExtensibleType">
 *       &lt;sequence>
 *         &lt;element name="component" type="{urn:oasis:names:tc:SPML:2:0}SelectionType" minOccurs="0"/>
 *         &lt;element name="data" type="{urn:oasis:names:tc:SPML:2:0}ExtensibleType" minOccurs="0"/>
 *         &lt;element name="capabilityData" type="{urn:oasis:names:tc:SPML:2:0}CapabilityDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="modificationMode" type="{urn:oasis:names:tc:SPML:2:0}ModificationModeType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModificationType", propOrder = {
    "component",
    "data",
    "capabilityData"
})
public class ModificationType
    extends ExtensibleType
{

    protected SelectionType component;
    protected ExtensibleType data;
    protected List<CapabilityDataType> capabilityData;
    @XmlAttribute
    protected ModificationModeType modificationMode;

    /**
     * Gets the value of the component property.
     * 
     * @return
     *     possible object is
     *     {@link SelectionType }
     *     
     */
    public SelectionType getComponent() {
        return component;
    }

    /**
     * Sets the value of the component property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectionType }
     *     
     */
    public void setComponent(SelectionType value) {
        this.component = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensibleType }
     *     
     */
    public ExtensibleType getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensibleType }
     *     
     */
    public void setData(ExtensibleType value) {
        this.data = value;
    }

    /**
     * Gets the value of the capabilityData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the capabilityData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCapabilityData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CapabilityDataType }
     * 
     * 
     */
    public List<CapabilityDataType> getCapabilityData() {
        if (capabilityData == null) {
            capabilityData = new ArrayList<CapabilityDataType>();
        }
        return this.capabilityData;
    }

    /**
     * Gets the value of the modificationMode property.
     * 
     * @return
     *     possible object is
     *     {@link ModificationModeType }
     *     
     */
    public ModificationModeType getModificationMode() {
        return modificationMode;
    }

    /**
     * Sets the value of the modificationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModificationModeType }
     *     
     */
    public void setModificationMode(ModificationModeType value) {
        this.modificationMode = value;
    }

}
