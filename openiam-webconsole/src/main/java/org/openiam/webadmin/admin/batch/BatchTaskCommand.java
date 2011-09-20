package org.openiam.webadmin.admin.batch;

import java.io.Serializable;


import org.openiam.idm.srvc.batch.dto.BatchTask;;

/**
 * Command object for the ConnectorDefinitionController
 * @author suneet
 *
 */
public class BatchTaskCommand implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8741198791323527695L;
	protected BatchTask batch = new BatchTask();
    

	public BatchTask getBatch() {
		return batch;
	}


	public void setBatch(BatchTask batch) {
		this.batch = batch;
	}


	public BatchTaskCommand() {
    	
    }


	


	

}
