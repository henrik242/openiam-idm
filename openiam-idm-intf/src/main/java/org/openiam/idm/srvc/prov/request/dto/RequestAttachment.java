package org.openiam.idm.srvc.prov.request.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

/**
 *  Domain model to represent attachments that may be associated with provisioning request.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestAttachment", propOrder = {
    "requestAttachmentId",
    "provRequestId",
    "name",
    "attachment",
	"userId",
    "attachmentDate"
})
public class RequestAttachment implements java.io.Serializable {

	private String requestAttachmentId;
	private String provRequestId;

	private String name;
	private String attachment;
	private String userId;
	private String attachmentDate;

	public RequestAttachment() {
	}


	public String getRequestAttachmentId() {
		return this.requestAttachmentId;
	}

	public void setRequestAttachmentId(String requestAttachmentId) {
		this.requestAttachmentId = requestAttachmentId;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAttachmentDate() {
		return this.attachmentDate;
	}

	public void setAttachmentDate(String attachmentDate) {
		this.attachmentDate = attachmentDate;
	}


	public String getProvRequestId() {
		return provRequestId;
	}


	public void setProvRequestId(String provRequestId) {
		this.provRequestId = provRequestId;
	}

}
