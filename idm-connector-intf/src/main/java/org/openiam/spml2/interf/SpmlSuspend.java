package org.openiam.spml2.interf;

import org.openiam.spml2.msg.suspend.SuspendRequestType;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.ActiveRequestType;
import org.openiam.spml2.msg.suspend.ActiveResponseType;
import org.openiam.spml2.msg.ResponseType;

/**
 * The Suspend Capability defines three operations: suspend, resume and active.
 * <li> The suspend operation disables an object (immediately or on a specified date).
 * <li> The resume operation re-enables an object (immediately or on a specified date).
 * <li> The active operation tests whether an object is currently suspended.
 * 
 * @author suneet shah
 */
public interface SpmlSuspend {

	/**
	 * The suspend operation enables a requestor to disable an object.
	 * @param request
	 */
	ResponseType suspend(SuspendRequestType request);
	/**
	 * The resume operation enables a requestor to re-enable an object that has been suspended. 
	 * @param request
	 */
	ResponseType resume(ResumeRequestType request);
	/**
	 * The active operation enables a requestor to determine whether a specified object has been suspended.
	 * @param request
	 * @return
	 */
	ActiveResponseType active(ActiveRequestType request);
}
