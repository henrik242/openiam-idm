package org.openiam.idm.srvc.org.service;

import java.util.Date;
import java.util.Map;

import org.openiam.idm.srvc.org.dto.OrganizationAttribute;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public final class OrganizationDataServiceClient {

	private static final QName SERVICE_NAME = new QName(
			"urn://idm.openiam.org/srvc/org/service/",
			"OrganizationDataWebService");
	private static final QName PORT_NAME = new QName(
			"urn://idm.openiam.org/srvc/org/service/",
			"OrganizationDataServicePort");

	private OrganizationDataServiceClient() {

	}

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "client-beans.xml" });

		OrganizationDataService port = (OrganizationDataService) context
				.getBean("client");

		/*
		 * Service service = Service.create(SERVICE_NAME); // Endpoint Address
		 * String endpointAddress =
		 * "http://localhost:8080/openiam-ws/OrganizationDataService";
		 * 
		 * // Add a port to the Service service.addPort(PORT_NAME,
		 * SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
		 * 
		 * OrganizationDataService port =
		 * service.getPort(OrganizationDataService.class);
		 */

		/*
		 * JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		 * 
		 * factory.getInInterceptors().add(new LoggingInInterceptor());
		 * factory.getOutInterceptors().add(new LoggingOutInterceptor());
		 * factory.setServiceClass(OrganizationDataService.class);
		 * factory.setAddress
		 * ("http://localhost:8080/openiam-ws/OrganizationDataService");
		 * OrganizationDataService port = (OrganizationDataService)
		 * factory.create();
		 */

	
		//System.out.println("Invoking updateOrganization...");
		//org.openiam.idm.srvc.org.dto.Organization _updateOrganization_organization = new org.openiam.idm.srvc.org.dto.Organization();
		//port.updateOrganization(_updateOrganization_organization);
	
		System.out.println("Invoking getTopLevelOrganizations...");
		java.util.List<org.openiam.idm.srvc.org.dto.Organization> _getTopLevelOrganizations__return = port
				.getTopLevelOrganizations();
		System.out.println("getTopLevelOrganizations.result="
				+ _getTopLevelOrganizations__return);
	
		System.out.println("Invoking subOrganizations...");
		java.lang.String _subOrganizations_orgId = "mySubOrg";
		java.util.List<org.openiam.idm.srvc.org.dto.Organization> _subOrganizations__return = port
				.subOrganizations(_subOrganizations_orgId);
		System.out.println("subOrganizations.result="
				+ _subOrganizations__return);
		
		org.openiam.idm.srvc.org.dto.Organization org = new org.openiam.idm.srvc.org.dto.Organization();
		org.setOrgId("testclientOrgId");
		org.setOrganizationName("OPENIAM LLC ");
		org.setCreatedBy("arun");
		org.setCreateDate(new Date());
		System.out.println("Invoking addOrganization...");
				port.addOrganization(org);
		
		
		System.out.println("Invoking getAllAttributes...");
		java.lang.String _getAllAttributes_orgId = "myOrg";
		Map<String, OrganizationAttribute>
		_getAllAttributes__return =
		port.getAllAttributes(_getAllAttributes_orgId);
		System.out.println("getAllAttributes.result=" +
		_getAllAttributes__return);
		
		System.out.println("Invoking getAttribute...");
		java.lang.String _getAttribute_attributeId = "";
		org.openiam.idm.srvc.org.dto.OrganizationAttribute _getAttribute__return = port
				.getAttribute(_getAttribute_attributeId);
		System.out.println("getAttribute.result=" + _getAttribute__return);

 


	/*
		System.out.println("Invoking removeAttribute...");
		org.openiam.idm.srvc.org.dto.OrganizationAttribute _removeAttribute_organizationAttribute = new org.openiam.idm.srvc.org.dto.OrganizationAttribute();
		port.removeAttribute(_removeAttribute_organizationAttribute);

		System.out.println("Invoking getOrganization...");
		java.lang.String _getOrganization_orgId = "";
		org.openiam.idm.srvc.org.dto.Organization _getOrganization__return = port
				.getOrganization(_getOrganization_orgId);
		System.out
				.println("getOrganization.result=" + _getOrganization__return);

		System.out.println("Invoking search...");
		java.lang.String _search_name = "";
		java.lang.String _search_type = "";
		java.util.List<org.openiam.idm.srvc.org.dto.Organization> _search__return = port
				.search(_search_name, _search_type);
		System.out.println("search.result=" + _search__return);


		
				System.out.println("Invoking addAttribute...");
		org.openiam.idm.srvc.org.dto.OrganizationAttribute _addAttribute_organizationAttribute = new org.openiam.idm.srvc.org.dto.OrganizationAttribute();
		port.addAttribute(_addAttribute_organizationAttribute);



		System.out.println("Invoking updateAttribute...");
		org.openiam.idm.srvc.org.dto.OrganizationAttribute _updateAttribute_organizationAttribute = new org.openiam.idm.srvc.org.dto.OrganizationAttribute();
		port.updateAttribute(_updateAttribute_organizationAttribute);

		System.out.println("Invoking containsChildren...");
		java.lang.String _containsChildren_orgId = "";
		boolean _containsChildren__return = port
				.containsChildren(_containsChildren_orgId);
		System.out.println("containsChildren.result="
				+ _containsChildren__return);

		System.out.println("Invoking isRootOrganization...");
		java.lang.String _isRootOrganization_orgId = "";
		boolean _isRootOrganization__return = port
				.isRootOrganization(_isRootOrganization_orgId);
		System.out.println("isRootOrganization.result="
				+ _isRootOrganization__return);

		System.out.println("Invoking removeOrganization...");
		java.lang.String _removeOrganization_orgId = "";
		port.removeOrganization(_removeOrganization_orgId);

		System.out.println("Invoking removeAllAttributes...");
		java.lang.String _removeAllAttributes_orgId = "";
		port.removeAllAttributes(_removeAllAttributes_orgId);
		*/
    
		System.exit(0);

	}

}
