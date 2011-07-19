package org.openiam.idm.srvc.synch.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SynchConfig", 
		propOrder = { "synchConfigId",
		"name",
		"status", 
		"synchAdapter",
		"fileName",
		"managedSysId",
		"loadMatchOnly",
		"updateAttribute",
		"synchFrequency",
		"synchType",
		"processRule",
		"validationRule",
		"transformationRule",
		"matchFieldName",
		"matchManagedSysId",
		"matchSrcFieldName",
		"srcLoginId",
		"srcPassword", 
		"srcHost", 
		"driver", 
		"connectionUrl", 
		"query", 
		"queryTimeField", 
		"lastExecTime", 
		"customMatchRule",
		"customMatchAttr",
		"customAdatperScript",
		"baseDn",
        "lastRecProcessed"
})
/**
 * Object containing the configuration for a synchronization task
 */
public class SynchConfig implements java.io.Serializable {

	private String synchConfigId;
	private String name;
	private String status;
	private String synchAdapter;
	private String fileName;
	private String managedSysId;
	private Integer loadMatchOnly;
	private Integer updateAttribute;
	private String synchFrequency;
	private String synchType;
	//private String deleteRule;
	private String processRule;
	private String validationRule;
	private String transformationRule;
	private String matchFieldName;
	private String matchManagedSysId;
	private String matchSrcFieldName;
	private String srcLoginId;
	private String srcPassword;
	private String srcHost;
	private String driver;
	private String connectionUrl;
	private String query;
	private String queryTimeField;
	@XmlSchemaType(name = "dateTime")
	private java.util.Date lastExecTime;
    private String lastRecProcessed;
	private String customMatchRule;
	private String customAdatperScript;
	private String customMatchAttr;
	private String baseDn;
	
     


	
	public SynchConfig() {
	}

	public SynchConfig(String synchConfigId) {
		this.synchConfigId = synchConfigId;
	}


	public String getSynchConfigId() {
		return this.synchConfigId;
	}

	public void setSynchConfigId(String synchConfigId) {
		this.synchConfigId = synchConfigId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getManagedSysId() {
		return this.managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public Integer getLoadMatchOnly() {
		return this.loadMatchOnly;
	}

	public void setLoadMatchOnly(Integer loadMatchOnly) {
		this.loadMatchOnly = loadMatchOnly;
	}

	public Integer getUpdateAttribute() {
		return this.updateAttribute;
	}

	public void setUpdateAttribute(Integer updateAttribute) {
		this.updateAttribute = updateAttribute;
	}

	public String getSynchFrequency() {
		return this.synchFrequency;
	}

	public void setSynchFrequency(String synchFrequency) {
		this.synchFrequency = synchFrequency;
	}


	public String getProcessRule() {
		return this.processRule;
	}

	public void setProcessRule(String processRule) {
		this.processRule = processRule;
	}


	public String getTransformationRule() {
		return this.transformationRule;
	}

	public void setTransformationRule(String transformationRule) {
		this.transformationRule = transformationRule;
	}

	public String getMatchFieldName() {
		return this.matchFieldName;
	}

	public void setMatchFieldName(String matchFieldName) {
		this.matchFieldName = matchFieldName;
	}

	public String getMatchSrcFieldName() {
		return this.matchSrcFieldName;
	}

	public void setMatchSrcFieldName(String matchSrcFieldName) {
		this.matchSrcFieldName = matchSrcFieldName;
	}

	public String getMatchManagedSysId() {
		return matchManagedSysId;
	}

	public void setMatchManagedSysId(String matchManagedSysId) {
		this.matchManagedSysId = matchManagedSysId;
	}

	public String getSrcLoginId() {
		return srcLoginId;
	}

	public void setSrcLoginId(String srcLoginId) {
		this.srcLoginId = srcLoginId;
	}

	public String getSrcPassword() {
		return srcPassword;
	}

	public void setSrcPassword(String srcPassword) {
		this.srcPassword = srcPassword;
	}

	public String getSrcHost() {
		return srcHost;
	}

	public void setSrcHost(String srcHost) {
		this.srcHost = srcHost;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQueryTimeField() {
		return queryTimeField;
	}

	public void setQueryTimeField(String queryTimeField) {
		this.queryTimeField = queryTimeField;
	}



	public String getCustomMatchRule() {
		return customMatchRule;
	}

	public void setCustomMatchRule(String customMatchRule) {
		this.customMatchRule = customMatchRule;
	}

	public String getCustomAdatperScript() {
		return customAdatperScript;
	}

	public void setCustomAdatperScript(String customAdatperScript) {
		this.customAdatperScript = customAdatperScript;
	}

	public String getSynchAdapter() {
		return synchAdapter;
	}

	public void setSynchAdapter(String synchAdapter) {
		this.synchAdapter = synchAdapter;
	}

	public String getValidationRule() {
		return validationRule;
	}

	public void setValidationRule(String validationRule) {
		this.validationRule = validationRule;
	}

	public String getSynchType() {
		return synchType;
	}

	public void setSynchType(String synchType) {
		this.synchType = synchType;
	}

	public String getCustomMatchAttr() {
		return customMatchAttr;
	}

	public void setCustomMatchAttr(String customMatchAttr) {
		this.customMatchAttr = customMatchAttr;
	}

	public java.util.Date getLastExecTime() {
		return lastExecTime;
	}

	public void setLastExecTime(java.util.Date lastExecTime) {
		this.lastExecTime = lastExecTime;
	}

    public String getBaseDn() {
        return baseDn;
    }

    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }

    @Override
    public String toString() {
        return "SynchConfig{" +
                "synchConfigId='" + synchConfigId + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", synchAdapter='" + synchAdapter + '\'' +
                ", fileName='" + fileName + '\'' +
                ", managedSysId='" + managedSysId + '\'' +
                ", loadMatchOnly=" + loadMatchOnly +
                ", updateAttribute=" + updateAttribute +
                ", synchFrequency='" + synchFrequency + '\'' +
                ", synchType='" + synchType + '\'' +
                ", processRule='" + processRule + '\'' +
                ", validationRule='" + validationRule + '\'' +
                ", transformationRule='" + transformationRule + '\'' +
                ", matchFieldName='" + matchFieldName + '\'' +
                ", matchManagedSysId='" + matchManagedSysId + '\'' +
                ", matchSrcFieldName='" + matchSrcFieldName + '\'' +
                ", srcLoginId='" + srcLoginId + '\'' +
                ", srcPassword='" + srcPassword + '\'' +
                ", srcHost='" + srcHost + '\'' +
                ", driver='" + driver + '\'' +
                ", connectionUrl='" + connectionUrl + '\'' +
                ", query='" + query + '\'' +
                ", queryTimeField='" + queryTimeField + '\'' +
                ", lastExecTime=" + lastExecTime +
                ", customMatchRule='" + customMatchRule + '\'' +
                ", customAdatperScript='" + customAdatperScript + '\'' +
                ", customMatchAttr='" + customMatchAttr + '\'' +
                ", baseDn='" + baseDn + '\'' +
                '}';
    }

    public String getLastRecProcessed() {
        return lastRecProcessed;
    }

    public void setLastRecProcessed(String lastRecProcessed) {
        this.lastRecProcessed = lastRecProcessed;
    }
}
