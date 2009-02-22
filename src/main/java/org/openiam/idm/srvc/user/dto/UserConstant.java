package org.openiam.idm.srvc.user.dto;

/**
 * Defines constants that are used by the User service.
 * @author Suneet Shah
 * @version 2
 */
public class UserConstant {

	/**
	 * Flag indicating the mode of association between a user and role has not been assigned
	 */
	public static final int UN_ASSIGNED = 0;
	/**
	 * Flag indicating the mode of association between a user and role is direct.
	 */
	public static final int DIRECT = 1;
	/**
	 * Flag indicating the mode of association between a user and role is indirect and likely through a 
	 * group.
	 */
	public static final int INDIRECT = 2;
}
