package org.openiam.spml2.interf;

import javax.jws.WebService;


import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;

import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.ListTargetsRequestType;
import org.openiam.spml2.msg.ListTargetsResponseType;
/**
 * Interface providing the core operations add, modify, delete, and lookup as defined in SPML v2
 * @author Suneet Shah
 *
 */

public interface SpmlCore {
	
	/**
	 * The add operation enables a requestor to create a new object on a target
	 * Attributes used by the operation are: <br>
	 * <li>PSOId: Unique identifier for the new object
	 * <li>containerId: Object where this new object should be created in. In a directory, it can be a base DN such as: ou=eng, dc=openiam, dc=org
	 * <li>data: Collection of data attributes that are to be stored in the target system
	 * <li>targetId: An id that is unique for the provider and is the system where this new object
	 * is to be created.
	 * <li>returnData:
	 * @param reqType
	 * @return
	 */
	AddResponseType add(AddRequestType reqType);
	
	ModifyResponseType modify(ModifyRequestType reqType);
	
	ResponseType delete(DeleteRequestType reqType);
	
	LookupResponseType lookup(LookupRequestType reqType);
	
	/**
	 * The listTargets operation enables a requestor to determine the set of 
	 * targets that a provider makes available for provisioning and 
	 * (the listTargets operation also enables a requestor) to determine the set of capabilities that the provider supports for each target.
	 * 
	 * @param reqType
	 * @return
	 */
	ListTargetsResponseType listTargets(ListTargetsRequestType reqType);
	

}
