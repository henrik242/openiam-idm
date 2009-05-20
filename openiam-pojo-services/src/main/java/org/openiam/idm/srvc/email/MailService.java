package org.openiam.idm.srvc.email;

import javax.jws.WebService;

/**
 * Provides methods to be able to send emails.
 * @author suneet
 *
 */
@WebService
public interface MailService {

	/**
	 * Sends an email to a specific user
	 */
	void send(String from, String to, String Subject, String msg);
	/**
	 * Sends an email all users with OpenIAM
	 */
	void sendToAllUsers();
	/**
	 * Sends an email all users in a specific group
	 * @param groupId
	 */
	void sendToGroup(String groupId);
}
