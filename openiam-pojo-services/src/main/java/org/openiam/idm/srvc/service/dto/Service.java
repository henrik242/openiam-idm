package org.openiam.idm.srvc.service.dto;

// Generated Apr 19, 2007 12:21:40 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class to represent services connected to the IdM system.
 * @author Suneet Shah
 * @version 2
 */
public class Service implements java.io.Serializable {

	// Fields    

	private String serviceId;

	private String serviceName;

	private String status;

	private String locationIpAddress;

	private String companyOwnerId;

	private Date startDate;

	private Date endDate;

	private String licenseKey;

	private String serviceType;

	private String parentServiceId;

	private String rootResourceId;

	private String accessControlModel;

	private String param1;

	private String param2;

	private String param3;

	private String param4;

	private String param5;

	private Set<RequestApprover> requestApprovers = new HashSet<RequestApprover>(0);

	private Set<ServiceConfig> serviceConfigs = new HashSet<ServiceConfig>(0);

	private Set<RequestForm> requestForms = new HashSet<RequestForm>(0);

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** minimal constructor */
	public Service(String serviceId) {
		this.serviceId = serviceId;
	}

	/** full constructor */
	public Service(String serviceId, String serviceName, String status,
			String locationIpAddress, String companyOwnerId, Date startDate,
			Date endDate, String licenseKey, String serviceType,
			String parentServiceId, String rootResourceId,
			String accessControlModel, String param1, String param2,
			String param3, String param4, String param5,
			Set<RequestApprover> requestApprovers,
			Set<ServiceConfig> serviceConfigs, Set<RequestForm> requestForms) {
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.status = status;
		this.locationIpAddress = locationIpAddress;
		this.companyOwnerId = companyOwnerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.licenseKey = licenseKey;
		this.serviceType = serviceType;
		this.parentServiceId = parentServiceId;
		this.rootResourceId = rootResourceId;
		this.accessControlModel = accessControlModel;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
		this.param5 = param5;
		this.requestApprovers = requestApprovers;
		this.serviceConfigs = serviceConfigs;
		this.requestForms = requestForms;
	}

	// Property accessors
	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocationIpAddress() {
		return this.locationIpAddress;
	}

	public void setLocationIpAddress(String locationIpAddress) {
		this.locationIpAddress = locationIpAddress;
	}

	public String getCompanyOwnerId() {
		return this.companyOwnerId;
	}

	public void setCompanyOwnerId(String companyOwnerId) {
		this.companyOwnerId = companyOwnerId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLicenseKey() {
		return this.licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getParentServiceId() {
		return this.parentServiceId;
	}

	public void setParentServiceId(String parentServiceId) {
		this.parentServiceId = parentServiceId;
	}

	public String getRootResourceId() {
		return this.rootResourceId;
	}

	public void setRootResourceId(String rootResourceId) {
		this.rootResourceId = rootResourceId;
	}

	public String getAccessControlModel() {
		return this.accessControlModel;
	}

	public void setAccessControlModel(String accessControlModel) {
		this.accessControlModel = accessControlModel;
	}

	public String getParam1() {
		return this.param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return this.param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return this.param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return this.param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return this.param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public Set<RequestApprover> getRequestApprovers() {
		return this.requestApprovers;
	}

	public void setRequestApprovers(Set<RequestApprover> requestApprovers) {
		this.requestApprovers = requestApprovers;
	}

	public Set<ServiceConfig> getServiceConfigs() {
		return this.serviceConfigs;
	}

	public void setServiceConfigs(Set<ServiceConfig> serviceConfigs) {
		this.serviceConfigs = serviceConfigs;
	}

	public Set<RequestForm> getRequestForms() {
		return this.requestForms;
	}

	public void setRequestForms(Set<RequestForm> requestForms) {
		this.requestForms = requestForms;
	}

}
