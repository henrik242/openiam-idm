package org.openiam.selfsrvc.pswd;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class IdentityQuestionCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4513024965714401771L;

	protected String password;
	protected String confPassword;
	protected String domainId;
	protected String principal;
	protected String submit;
	protected List<UserIdentityAnswer> answerList;
	protected List<IdentityQuestion> questionList;
	protected int questionCount;
	

	
	public IdentityQuestionCommand( ) {
		super();
		
	}	
	
	public IdentityQuestionCommand( String principal,
			String domainId, String password,
			String confPassword ) {
		super();
		this.confPassword = confPassword;
		this.domainId = domainId;
		this.password = password;
		this.principal = principal;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public List<UserIdentityAnswer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<UserIdentityAnswer> answerList) {
		this.answerList = answerList;
	}

	public List<IdentityQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<IdentityQuestion> questionList) {
		this.questionList = questionList;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	


	
	

	

}
