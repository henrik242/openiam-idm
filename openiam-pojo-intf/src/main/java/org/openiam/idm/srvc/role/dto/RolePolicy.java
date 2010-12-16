
package org.openiam.idm.srvc.role.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.BaseObject;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RolePolicy", propOrder = {
    "rolePolicyId",
    "serviceId",
    "roleId",
    "name",
    "value1",
    "value2",
    "action",
    "executionOrder",
    "policyScript",
    "actionQualifier"
})
public class RolePolicy extends BaseObject {

    protected String rolePolicyId;
    protected String serviceId;
    protected String roleId;
    protected String name;
    protected String value1;
    protected String value2;
    protected String action;
    protected Integer executionOrder;
    protected String actionQualifier;
    protected String policyScript;
    

    
    public RolePolicy() {
    }



	public String getRolePolicyId() {
		return rolePolicyId;
	}



	public void setRolePolicyId(String rolePolicyId) {
		this.rolePolicyId = rolePolicyId;
	}



	public String getServiceId() {
		return serviceId;
	}



	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}



	public String getRoleId() {
		return roleId;
	}



	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getValue1() {
		return value1;
	}



	public void setValue1(String value1) {
		this.value1 = value1;
	}



	public String getValue2() {
		return value2;
	}



	public void setValue2(String value2) {
		this.value2 = value2;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public Integer getExecutionOrder() {
		return executionOrder;
	}



	public void setExecutionOrder(Integer executionOrder) {
		this.executionOrder = executionOrder;
	}



	public String getActionQualifier() {
		return actionQualifier;
	}



	public void setActionQualifier(String actionQualifier) {
		this.actionQualifier = actionQualifier;
	}



	public String getPolicyScript() {
		return policyScript;
	}



	public void setPolicyScript(String policyScript) {
		this.policyScript = policyScript;
	}

	
 
}
