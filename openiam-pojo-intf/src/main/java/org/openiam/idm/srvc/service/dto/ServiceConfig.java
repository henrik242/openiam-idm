package org.openiam.idm.srvc.service.dto;

// Generated Apr 19, 2007 12:21:40 AM by Hibernate Tools 3.2.0.beta8

/**
 * <code>ServiceConfig</code> is used to define the configuration parameters for a service
 * @version 3
 * @author Suneet Shah
 */
public class ServiceConfig implements java.io.Serializable {

	// Fields    

	private String serviceConfigId;

	private Service service;

	private String name;

	private String value;

	// Constructors

	/** default constructor */
	public ServiceConfig() {
	}

	/** minimal constructor */
	public ServiceConfig(String serviceConfigId) {
		this.serviceConfigId = serviceConfigId;
	}

	/** full constructor */
	public ServiceConfig(String serviceConfigId, Service service, String name,
			String value) {
		this.serviceConfigId = serviceConfigId;
		this.service = service;
		this.name = name;
		this.value = value;
	}

	// Property accessors
	public String getServiceConfigId() {
		return this.serviceConfigId;
	}

	public void setServiceConfigId(String serviceConfigId) {
		this.serviceConfigId = serviceConfigId;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
