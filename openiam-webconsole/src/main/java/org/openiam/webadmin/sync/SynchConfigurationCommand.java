package org.openiam.webadmin.sync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.dto.SynchConfigDataMapping;


/**
 * Command object for Synchronization Configuration
 * @author suneet
 *
 */
public class SynchConfigurationCommand implements Serializable {

	SynchConfig syncConfig = new SynchConfig();
	List<SynchConfigDataMapping> mappingList = new ArrayList<SynchConfigDataMapping>();
	
	String errorText;
	
	public SynchConfig getSyncConfig() {
		return syncConfig;
	}
	public void setSyncConfig(SynchConfig syncConfig) {
		this.syncConfig = syncConfig;
	}
	public List<SynchConfigDataMapping> getMappingList() {
		return mappingList;
	}
	public void setMappingList(List<SynchConfigDataMapping> mappingList) {
		this.mappingList = mappingList;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	



	

}
