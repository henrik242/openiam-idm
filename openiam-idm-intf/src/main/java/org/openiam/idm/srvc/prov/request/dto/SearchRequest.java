package org.openiam.idm.srvc.prov.request.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * SearchRequest is a DTO enabling ad-hoc searches. Define the values by which the search should be carried and then
 * pass it on to the search operation.
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchRequest", propOrder = {
    "requestId",
    "requestorId",
    "status",
    "startDate",
    "endDate",
    "approverId",
    "level",
    "requestForOrgList",
    "roleIdList"
})
public class SearchRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8956796587364871510L;
	private String requestId;
	private String requestorId;
	private String status;
    @XmlSchemaType(name = "dateTime")
	private Date startDate;
    @XmlSchemaType(name = "dateTime")
	private Date endDate;
	private String approverId;
	private int level;

    protected List<String> roleIdList = new ArrayList<String>();
    protected List<String> requestForOrgList = new ArrayList<String>();
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestorId() {
		return requestorId;
	}
	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<String> getRequestForOrgList() {
        return requestForOrgList;
    }

    public void setRequestForOrgList(List<String> requestForOrgList) {
        this.requestForOrgList = requestForOrgList;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "requestId='" + requestId + '\'' +
                ", requestorId='" + requestorId + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", approverId='" + approverId + '\'' +
                ", level=" + level +
                ", roleIdList=" + roleIdList +
                ", requestForOrgList=" + requestForOrgList +
                '}';
    }
}
