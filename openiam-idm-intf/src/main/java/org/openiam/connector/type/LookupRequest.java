
package org.openiam.connector.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LookupRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LookupRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}RequestType">
 *       &lt;sequence>
 *         &lt;element name="psoID" type="{urn:oasis:names:tc:SPML:2:0}PSOIdentifierType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="returnData" type="{urn:oasis:names:tc:SPML:2:0}ReturnDataType" default="everything" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LookupRequest", propOrder = {
    "searchValue",
    "searchQuery",
    "returnData"
})
public class LookupRequest   extends RequestType
{

    @XmlElement(required = true)
    protected String searchValue;
    
    protected String searchQuery;
    
    protected ReturnData returnData;

 
    /**
     * Gets the value of the returnData property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnData }
     *     
     */
    public ReturnData getReturnData() {
        if (returnData == null) {
            return ReturnData.EVERYTHING;
        } else {
            return returnData;
        }
    }

    /**
     * Sets the value of the returnData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnData }
     *     
     */
    public void setReturnData(ReturnData value) {
        this.returnData = value;
    }

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

}
