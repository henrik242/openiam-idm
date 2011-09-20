package org.openiam.idm.srvc.auth.context;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Base credential object from which all other concrete credentials are inherited.
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseCredential", propOrder = {
})
public abstract class BaseCredential implements Credential {

	public BaseCredential() {
		
	}
}
