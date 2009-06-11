package org.openiam.spml2.interf;

import javax.jws.WebService;

/**
 * Provides a consolidated SpmlInterface that is to be used by all SPML Service.
 * @author suneet
 *
 */
@WebService(targetNamespace = "urn:idm.openiam.org/spml2/service")
public interface SpmlComplete extends SpmlCore, SpmlPassword{

}

