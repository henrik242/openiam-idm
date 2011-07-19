package org.openiam.idm.srvc.user.dto;


	import java.util.ArrayList;
	import java.util.List;
	import javax.xml.bind.annotation.XmlAccessType;
	import javax.xml.bind.annotation.XmlAccessorType;
	import javax.xml.bind.annotation.XmlType;


	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "userSet", propOrder = {
	    "userObj"
	})
	public class UserSet {

	    protected List<UserSet.UserObj> userObj;

	    /**
	     * Gets the value of the userObj property.
	     * 
	     * <p>
	     * This accessor method returns a reference to the live list,
	     * not a snapshot. Therefore any modification you make to the
	     * returned list will be present inside the JAXB object.
	     * This is why there is not a <CODE>set</CODE> method for the userObj property.
	     * 
	     * <p>
	     * For example, to add a new item, do as follows:
	     * <pre>
	     *    getUserObj().add(newObj);
	     * </pre>
	     * 
	     * 
	     * <p>
	     * Objects of the following type(s) are allowed in the list
	     * {@link UserSet.UserObj }
	     * 
	     * 
	     */
	    public List<UserSet.UserObj> getUserObj() {
	        if (userObj == null) {
	            userObj = new ArrayList<UserSet.UserObj>();
	        }
	        return this.userObj;
	    }


	    /**
	     * <p>Java class for anonymous complex type.
	     * 
	     * <p>The following schema fragment specifies the expected content contained within this class.
	     * 
	     * <pre>
	     * &lt;complexType>
	     *   &lt;complexContent>
	     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	     *       &lt;sequence>
	     *         &lt;element name="user" type="{urn:idm.openiam.org/srvc/user/dto}user" minOccurs="0"/>
	     *       &lt;/sequence>
	     *     &lt;/restriction>
	     *   &lt;/complexContent>
	     * &lt;/complexType>
	     * </pre>
	     * 
	     * 
	     */
	    @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "user"
	    })
	    public static class UserObj {

	        protected User user;

	        /**
	         * Gets the value of the user property.
	         * 
	         * @return
	         *     possible object is
	         *     {@link User }
	         *     
	         */
	        public User getUser() {
	            return user;
	        }

	        /**
	         * Sets the value of the user property.
	         * 
	         * @param value
	         *     allowed object is
	         *     {@link User }
	         *     
	         */
	        public void setUser(User value) {
	            this.user = value;
	        }

	    }

	}

