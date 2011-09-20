package org.openiam.selfsrvc.claim;

import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class AccountClaimCommand implements Serializable {
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190211705748357722L;

    Integer acceptPolicy = new Integer(0);

    protected String redid;
    protected String regid;
    protected Date dob;
    protected String userId;

    protected List<UserIdentityAnswer> answerList;
	protected List<IdentityQuestion> questionList;
	protected int questionCount;

	protected String password;
	protected String confPassword;
	protected String domainId;

    protected String principal;
	protected String submit;

	protected int requiredCorrect;


	

	public AccountClaimCommand() {
		super();
		
	}	
	
	public AccountClaimCommand(String principal,
                               String domainId, String password,
                               String confPassword) {
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

	public int getRequiredCorrect() {
		return requiredCorrect;
	}

	public void setRequiredCorrect(int requiredCorrect) {
		this.requiredCorrect = requiredCorrect;
	}




    public String getRedid() {
        return redid;
    }

    public void setRedid(String redid) {
        this.redid = redid;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }


    public Integer getAcceptPolicy() {
        return acceptPolicy;
    }

    public void setAcceptPolicy(Integer acceptPolicy) {
        this.acceptPolicy = acceptPolicy;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
