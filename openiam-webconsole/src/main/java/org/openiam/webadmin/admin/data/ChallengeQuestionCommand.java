package org.openiam.webadmin.admin.data;

import java.io.Serializable;

import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;


/**
 * Command object for the ChallengeQuestionController
 * @author suneet
 *
 */
public class ChallengeQuestionCommand implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8741198791323527695L;
	protected IdentityQuestion idQuest = new IdentityQuestion();
    

	public ChallengeQuestionCommand() {
    	
    }


	public IdentityQuestion getIdQuest() {
		return idQuest;
	}


	public void setIdQuest(IdentityQuestion idQuest) {
		this.idQuest = idQuest;
	}

	


	

}
