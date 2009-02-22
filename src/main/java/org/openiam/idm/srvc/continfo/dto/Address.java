package org.openiam.idm.srvc.continfo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Jun 12, 2007 10:46:13 PM by Hibernate Tools 3.2.0.beta8

/**
 * Address transfer object.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
    "isActive",
    "address1",
    "address2",
    "address3",
    "address4",
    "address5",
    "address6",
    "address7",
    "addressId",
    "city",
    "country",
    "description",
    "isDefault",
    "parentId",
    "parentType",
    "postalCd",
    "state"
})
public class Address implements java.io.Serializable {
	
	

	// Fields    
	
	    protected Boolean isActive = new Boolean("True");
	    protected String address1;
	    protected String address2;
	    protected String address3;
	    protected String address4;
	    protected String address5;
	    protected String address6;
	    protected String address7;
	    protected String addressId;
	    protected String city;
	    protected String country;
	    protected String description;
	    protected Integer isDefault = new Integer(0);
	    protected String parentId;
	    protected String parentType;
	    protected String postalCd;
	    protected String state;
	    protected String name;
	

	// Constructors




	/** default constructor */
	public Address() {
	}

	/** minimal constructor */
	public Address(String addressId) {
		this.addressId = addressId;
	}

	/** full constructor */
	public Address(String addressId, String country, String address1,
			String address2, 
			String address3, String address4,  String address5,  String address6, String address7, 
			String city, String state, String postalCd,
			Integer isDefault, String description) {
		this.addressId = addressId;
		this.country = country;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.address4 = address4;
		this.address5 = address5;
		this.address6 = address6;
		this.address7 = address7;
		this.city = city;
		this.state = state;
		this.postalCd = postalCd;
		this.isDefault = isDefault;
		this.description = description;
	}
	


	
	public boolean equals(Object adr) {
		
		Address newAdr = (Address)adr;
		
		if (this == newAdr)
			return true;
		if (newAdr == null || newAdr.getClass() != this.getClass())
			return false;
		
		return ( (addressId == newAdr.getAddressId() || 
				 ( addressId != null && addressId.equals(newAdr.getAddressId()))) && 
				 
				 (this.address1 == newAdr.getAddress1() ||  
				 ( address1 != null && address1.equals(newAdr.getAddress1()))) && 
				 
				 (this.address2 == newAdr.getAddress2() ||  
				 ( address2 != null && address2.equals(newAdr.getAddress2()))) && 
				 
				 (this.address3 == newAdr.getAddress3() ||  
				 ( address3 != null && address3.equals(newAdr.getAddress3()))) && 
						 
				 (this.address4 == newAdr.getAddress4() ||  
				 ( address4 != null && address4.equals(newAdr.getAddress4()))) &&
								 
				 (this.address5 == newAdr.getAddress5() ||  
				 ( address5 != null && address5.equals(newAdr.getAddress5()))) && 
				 
				 (this.address6 == newAdr.getAddress6() ||  
				 ( address6 != null && address6.equals(newAdr.getAddress6()))) && 
				 
				 (this.address7 == newAdr.getAddress7() ||  
				 ( address7 != null && address7.equals(newAdr.getAddress7()))) && 
				 
				 
				 (this.city == newAdr.getCity() ||  
				 ( city != null && city.equals(newAdr.getCity()))) && 
				 
				 (this.country == newAdr.getCountry() ||  
				 ( country != null && country.equals(newAdr.getCountry()))) && 
				
				 (this.description == newAdr.getDescription() ||  
				 ( description != null && description.equals(newAdr.getDescription()))) && 
				
				 (this.isDefault.intValue() == newAdr.isDefault.intValue() )  &&
				 
				 (this.parentId == newAdr.getParentId() ||  
  			     ( parentId != null && parentId.equals(newAdr.getParentId()))) && 
  			    		
 			    (this.parentType == newAdr.getParentType() ||  
				( parentType != null && parentType.equals(newAdr.getParentType()))) && 
 			    
				(this.postalCd == newAdr.getPostalCd() ||  
				( postalCd != null && postalCd.equals(newAdr.getPostalCd()))) && 
 			    
				(this.state == newAdr.getState() ||  
				( state != null && state.equals(newAdr.getState()))) 		) ; 
		

	}


	
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getAddressId() == null ? 0 : this.getAddressId().hashCode());
		result = 37 * result
				+ (getAddress1() == null ? 0 : this.getAddress1().hashCode());
		result = 37 * result
				+ (getAddress2() == null ? 0 : this.getAddress2().hashCode());
		
		result = 37 * result
		+ (getAddress3() == null ? 0 : this.getAddress3().hashCode());
		
		result = 37 * result
		+ (getAddress4() == null ? 0 : this.getAddress4().hashCode());
		
		result = 37 * result
		+ (getAddress5() == null ? 0 : this.getAddress5().hashCode());
		
		result = 37 * result
		+ (getAddress6() == null ? 0 : this.getAddress6().hashCode());
		
		result = 37 * result
		+ (getAddress7() == null ? 0 : this.getAddress7().hashCode());		
		
		result = 37 * result
				+ (getCity() == null ? 0 : this.getCity().hashCode());
		result = 37 * result
				+ (getDescription() == null ? 0 : this.getDescription().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getAddress1() == null ? 0 : this.getAddress1().hashCode());

		return result;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer base = new StringBuffer( super.toString() );
		
		base.append("addressId=" + addressId +
					" country=" + country + 
					" address1=" + address1 + 
					" address2=" + address2 + 
					" city=" + city + 
					" state=" + state + 
					" postalCd=" + postalCd + 
					" description=" + description + 
					" parentId=" + parentId + 
					" parentType=" + parentType + 
					" isActive=" + isActive);
		
		return base.toString();
	}

	// Property accessors
	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCd() {
		return this.postalCd;
	}

	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}

	public Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the Id of the parent that owns this address. The parent may be another entity like a 
	 * USER, ORGANIZATION, etc
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * Associates the address with a parent entity, such as USER or ORGANIZATION that owns this address.
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * Returns the type of the parent.  
	 * @return
	 */
	public String getParentType() {
		return parentType;
	}
	/**
	 * Sets the type of the parent.  While the parent type can be anything you choose, a few 
	 * constants are defined in the ContactConstants clss.
	 * @param parentType
	 */
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	
	/**
	 * Indicates if the address is currently active if the value is 
	 * true and inactive if the value false.
	 * @return
	 */
	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAddress5() {
		return address5;
	}

	public void setAddress5(String address5) {
		this.address5 = address5;
	}

	public String getAddress6() {
		return address6;
	}

	public void setAddress6(String address6) {
		this.address6 = address6;
	}

	public String getAddress7() {
		return address7;
	}

	public void setAddress7(String address7) {
		this.address7 = address7;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
