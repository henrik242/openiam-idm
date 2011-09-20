package org.openiam.util;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Simplifies the process of getting a reference to a service
 * @author suneet
 *
 */
public class WebServiceHelper {

	private static final Log log = LogFactory.getLog(WebServiceHelper.class);
	
	public static Object createService(String serviceUrl, String serviceNameSpace, String port, Class cls ) {
		try {
			Service service = Service.create(QName.valueOf(serviceUrl));
			service.addPort(new QName(serviceNameSpace,	port),
					SOAPBinding.SOAP11HTTP_BINDING, 
					serviceUrl);
			return service.getPort(new QName(serviceNameSpace,port), cls);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
		
	}
}


