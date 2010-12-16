package org.openiam.idm.srvc.msg.dto;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * Object to deliver message through the system
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SysMessageDelivery", propOrder = {
		"msgId",
		"name",
		"targetAudienceType",
		"targetAudience",
		"startDate",
		"endDate",
		"msg",
		"deliverBy",
		"msgFrom",
		"msgSubject",
		"showOnResource"
})
public class SysMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5748586827726042100L;
	protected String msgId;
	protected String name;
	protected String targetAudienceType;
	protected String targetAudience;
	@XmlSchemaType(name = "dateTime")
	protected Date startDate;
	@XmlSchemaType(name = "dateTime")
	protected Date endDate;
	protected String msg;
	protected String showOnResource;
	protected String msgFrom;
	protected String deliverBy;
	protected String msgSubject;
	

	public SysMessage() {
	}

	public SysMessage(String msgDeliveryId) {
		this.msgId = msgDeliveryId;
	}

	public SysMessage(String msgDeliveryId, String name,
			String targetAudienceType, String targetAudience, Date startDate,
			Date endDate, String msg, String showOnResource) {
		this.msgId = msgDeliveryId;
		this.name = name;
		this.targetAudienceType = targetAudienceType;
		this.targetAudience = targetAudience;
		this.startDate = startDate;
		this.endDate = endDate;
		this.msg = msg;
		this.showOnResource = showOnResource;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetAudienceType() {
		return this.targetAudienceType;
	}

	public void setTargetAudienceType(String targetAudienceType) {
		this.targetAudienceType = targetAudienceType;
	}

	public String getTargetAudience() {
		return this.targetAudience;
	}

	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getShowOnResource() {
		return this.showOnResource;
	}

	public void setShowOnResource(String showOnResource) {
		this.showOnResource = showOnResource;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getDeliverBy() {
		return deliverBy;
	}

	public void setDeliverBy(String deliverBy) {
		this.deliverBy = deliverBy;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgSubject() {
		return msgSubject;
	}

	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

}
