package org.openiam.idm.srvc.auth.context;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Interface to define credential objects that will be used during the authentication process
 * 
 * @author Suneet Shah
 *
 */
@XmlSeeAlso({
	PasswordCredential.class
})
public interface Credential {

}
