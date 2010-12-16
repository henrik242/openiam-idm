/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.synch.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * Object containing the supporting details of how the synchronization should work
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SynchRequest", propOrder = {
    "fileName",
    "lastExecTime",
    "sql",
    "dbDriver",
    "connectionUrl",
    "userName",
    "password",
    "validationScript",
    "transformationScript",
    "deleteScript",
    "deleteScript",
    "processScript"
})
public class SynchRequest {

	// for csv file sync
	String fileName;
	
    @XmlSchemaType(name = "dateTime")
	Date lastExecTime;
	
	// database synch
	String sql;
	String dbDriver;
	String connectionUrl;
	String userName;
	String password;
	
	
	// script urls
	String validationScript;
	String transformationScript;
	String deleteScript;
	String processScript;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getLastExecTime() {
		return lastExecTime;
	}
	public void setLastExecTime(Date lastExecTime) {
		this.lastExecTime = lastExecTime;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}
	public String getValidationScript() {
		return validationScript;
	}
	public void setValidationScript(String validationScript) {
		this.validationScript = validationScript;
	}
	public String getTransformationScript() {
		return transformationScript;
	}
	public void setTransformationScript(String transformationScript) {
		this.transformationScript = transformationScript;
	}
	public String getDeleteScript() {
		return deleteScript;
	}
	public void setDeleteScript(String deleteScript) {
		this.deleteScript = deleteScript;
	}
	public String getProcessScript() {
		return processScript;
	}
	public void setProcessScript(String processScript) {
		this.processScript = processScript;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
