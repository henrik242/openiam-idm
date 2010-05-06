package org.openiam.idm.srvc.msg.service;

import javax.jws.WebService;

import org.openiam.idm.srvc.msg.dto.NotificationRequest;

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
	
	void sendWithCC(String from, String to,String cc, String subject, String msg);
	
	/**
	 * Sends an email all users with OpenIAM
	 */
	void sendToAllUsers();
	/**
	 * Sends an email all users in a specific group
	 * @param groupId
	 */
	void sendToGroup(String groupId);
	
	/**
	 * Sends out a notification based on the information defined in the notification request.
	 * @param req
	 */
	void sendNotification(NotificationRequest req);
}
