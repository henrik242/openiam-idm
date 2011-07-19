package org.openiam.idm.srvc.msg.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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
	@WebMethod
	void send(String from, String to, String Subject, String msg);
	
	@WebMethod
	void sendWithCC(String from, String to,String cc, String subject, String msg);
	
	/**
	 * Sends an email all users with OpenIAM
	 */
	
	@WebMethod
	void sendToAllUsers();
	/**
	 * Sends an email all users in a specific group
	 * @param groupId
	 */
	@WebMethod
	void sendToGroup(String groupId);
	
	/**
	 * Sends out a notification based on the information defined in the notification request.
	 * @param req
	 */
	
	@WebMethod
	boolean sendNotification(
			@WebParam(name = "req", targetNamespace = "")
			NotificationRequest req);
}
