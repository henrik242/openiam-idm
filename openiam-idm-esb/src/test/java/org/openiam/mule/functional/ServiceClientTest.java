package org.openiam.mule.functional;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.openiam.base.id.UUIDGen;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.PSOIdentifierType;


public class ServiceClientTest {

	public static void main(String [ ] args) {
		System.out.println("test..");
		
		AddRequestType addReqType = new AddRequestType();
		
        PSOIdentifierType idType = new PSOIdentifierType("test-user",null, "target");
        addReqType.setPsoID(idType);
        String requestId = "R1234343";
        addReqType.setRequestID(requestId);
        addReqType.setTargetID("123343434");
        
		
		QName SERVICE_NAME = new QName("http://www.openiam.org/service/connector", "ActiveDirectoryConnector");
		QName PORT_NAME = new QName("http://www.openiam.org/service/connector", "BasicHttpBinding_ConnectorService");

		String endpointAddress = "http://win2003.iamdev.local/ServiceHost/ActiveDirectoryConnector.svc";
		
		Service service = Service.create(SERVICE_NAME);

		
		// Add a port to the Service
		service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
		        
		ConnectorService connector = service.getPort(ConnectorService.class);
		

		System.out.println(connector.add(addReqType));

	}

	
}
