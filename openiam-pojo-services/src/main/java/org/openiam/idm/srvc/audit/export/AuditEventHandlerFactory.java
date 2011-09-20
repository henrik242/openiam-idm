package org.openiam.idm.srvc.audit.export;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.tools.java.ClassNotFound;

import java.util.ResourceBundle;

/**
 * Factory instantiates the appropriate ExportAuditEvent object
 * User: suneetshah
 * Date: 9/18/11
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuditEventHandlerFactory {

    static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
    private static final Log log = LogFactory.getLog(AuditEventHandlerFactory.class);

    public static ExportAuditEvent createInstance() {


		try {
            String handlerName =  res.getString("EXPORT_AUDIT_EVENT_HANDLER");
            if (handlerName == null || handlerName.length() == 0) {
                return null;
            }

            Class cls = Class.forName(handlerName);
			return (ExportAuditEvent)cls.newInstance();
        }catch(ClassNotFoundException cnfe ) {
              log.error(cnfe.getMessage(),cnfe);
        }catch(IllegalAccessException ia) {
			log.error(ia.getMessage(),ia);

		}catch(InstantiationException ie) {
			log.error(ie.getMessage(),ie);
		}catch(Exception e) {
            log.error(e.getMessage(),e);
        }
		return null;


    }

}
