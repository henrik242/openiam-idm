package org.openiam.selfsrvc.pswd;

import java.rmi.RemoteException;
import java.util.*;

import javax.naming.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;



import diamelle.security.policy.*;
import diamelle.security.idquest.*;
import diamelle.security.auth.*;

import diamelle.util.Log;

/**
 * Displays the identityquestions that are used to confirm a persons identity.
 * Flow:
 * look up the policy for this person
 * Check the source of of the questions
 * If a list of questions is defined, then get them.
 * else allow them to pick a set number of questions from the question bank.
 * if a reply exists for these questions, then show that too.
 * 
 * Check the person
 * @version 	1.0
 * @author
 */
public class IdQuestionAction extends DispatchAction {
	IdQuestionAccess questAccess = new IdQuestionAccess();
	PolicyAccess policyAccess = new PolicyAccess();
	AuthenticatorAccess authAccess = new AuthenticatorAccess();

	
	public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		Context ctx = null;
		String serviceId = null;
		String strQuestionCount = null;
		String questionSrc = null;
		String strQuestionList = null;
		List questionDropdownList = null;

		
		HttpSession session = request.getSession();
		
		String login = request.getParameter("lg");
		String userId = (String)session.getAttribute("userId");
		
		// lookup the service
		try {
			ctx = new InitialContext();
			serviceId = (String) ctx.lookup("java:comp/env/serviceId");
			
		}catch(NamingException ne) {
			Log.error(ne.getMessage(),ne);
		}
		
		if (serviceId == null || login == null) {
			List loginList = authAccess.getAllLogins(userId);
			if (loginList != null) {
				int size = loginList.size();
				for (int i=0; i < size; i++) {
					LoginValue val = (LoginValue)loginList.get(i);
					if (val.getService().equals("USR_SEC_DOMAIN")) {
						serviceId = val.getService();
						login = val.getLogin();				
					}
				}
			}
		}
		List policyAttr = policyAccess.getPolicyForLogin(PolicyConstants.POLICY_TYPE_PASSWORD, 
									serviceId, login);
		// get the parameters
		int size = policyAttr.size();
		for (int i=0; i < size; i++) {
			PolicyAttrValue attrValue = (PolicyAttrValue)policyAttr.get(i);
			if (attrValue.getName().equals("QUEST_COUNT")) {
				strQuestionCount = attrValue.getValue1();	
				request.setAttribute("questCount",  strQuestionCount);
			}
			if (attrValue.getName().equals("QUEST_SRC")) {
				questionSrc = attrValue.getValue1();			
				request.setAttribute("questionSrc",  questionSrc);
			}			
			if (attrValue.getName().equals("QUEST_LIST")) {
				strQuestionList = attrValue.getValue1();		
			}
		} 
		
		// check if answers to questions already exist for this user
		List questionList = null;
		List answerList = questAccess.getUserAnswers(userId);
		if (answerList == null || answerList.isEmpty()) {					
			request.setAttribute("mode","new"); 
		}else {
			request.setAttribute("mode","edit");
		}
		questionList = getQuestions(questionSrc, strQuestionList);
		request.setAttribute("answerList", answerList);
		request.setAttribute("questionDropdown", getQuestionDropDownList(questionList));

		return mapping.findForward("view");
	}
	

	private List getQuestions(String questionSrc, String questionList) throws RemoteException {
		// get the answers for this user
		// if none exist, then get the list of questions from the policy
		// if the questions are defined in the policy then get the list of questions
		// if they are user selected then get the list of questions in the question bank.
		if (questionSrc.equalsIgnoreCase("USER")) {
			// user needs to pick the questions.
			return this.questAccess.getAllQuestions();
		}else {
			// policy based definition of which questions to use.
			return this.questAccess.getSelectedQuestions(questionList);
		}		
	}
	
	private List getQuestionDropDownList(List answerList) throws RemoteException {
    	ArrayList dropdownList = new ArrayList();
         if (answerList != null && answerList.size() > 0) {
         	dropdownList.add(new LabelValueBean("",""));
        	for (int i=0; i < answerList.size(); i++) {       		
        		QuestionValue  val = (QuestionValue)answerList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getQuestionText(),val.getQuestionId());
        	 	dropdownList.add(label);
        	}
        }
        return dropdownList;
    }

	
	public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		String mode = request.getParameter("mode");
		QuestionValue questVal = null;
		List answerList = new ArrayList();
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		Enumeration en = (Enumeration)request.getParameterNames();
		
		if (mode.equals("new")) {
			
			while (en.hasMoreElements() ) {
				String elem = (String)en.nextElement();
				// find the parameters that question and answers are based on
				int index = elem.indexOf("_");
				if (elem.indexOf("_") > 0 && elem.startsWith("quest")) {
					int strSize = elem.length();
					String questionIndex = elem.substring(index+1, strSize);
					int questIdx = Integer.parseInt( questionIndex );				
					String questionId = request.getParameter("question_" + questIdx);
					String answerText = request.getParameter("answer_" + questIdx);				
				
					// validate that the user has picked questions for each entry
					if (questionId == null || questionId.length() == 0) {
						request.setAttribute("errmsg","Please provide a response for each set of options listed.");
						return this.view(mapping, form, request, response);
					}
					if (answerList != null && !answerList.isEmpty()) {
						if ( isDuplicateQuestion(answerList) ) {
							request.setAttribute("errmsg","Please select unique questions.");
							return this.view(mapping, form, request, response);							
						}
					}					
					// populate the value object
					questVal = new QuestionValue();
					questVal.setAnsText(answerText);
					questVal.setAnsUserId(userId);
					questVal.setQuestionId(questionId);
					answerList.add(questVal);
					}
			}
			questAccess.addUserAnswers(answerList);
		
		}else {
			// get the current list of answers
			List curAnswerList = questAccess.getUserAnswers(userId);
			//
			while (en.hasMoreElements() ) {
				String elem = (String)en.nextElement();
				// find the answerId in the form and then find the answer object in the 
				// current list. Then update that object with the newly updated information
				
				int index = elem.indexOf("_");
				if (elem.indexOf("_") > 0 && elem.startsWith("answer")) {
					int strSize = elem.length();
					String answerIndex = elem.substring(index+1, strSize);
					int ansIdx = Integer.parseInt( answerIndex );				
					String questionId = request.getParameter("question_" + ansIdx);
					String answerText = request.getParameter("answer_" + ansIdx);				

					// validate that the user has picked questions for each entry
					if (questionId == null || questionId.length() == 0) {
						request.setAttribute("errmsg","Please provide a response for each set of options listed.");
						return this.view(mapping, form, request, response);
					}
					if (curAnswerList != null && !curAnswerList.isEmpty()) {
						if ( isDuplicateQuestion(curAnswerList) ) {
							request.setAttribute("errmsg","Please select unique questions.");
							return this.view(mapping, form, request, response);							
						}
					}
					
					// find the current object
					for (int i=0; i < curAnswerList.size(); i++) {
						QuestionValue val = (QuestionValue)curAnswerList.get(i);
						if (val.getAnswerId().equalsIgnoreCase(answerIndex)) {
							// match found
							val.setQuestionId(questionId);
							val.setAnsText(answerText);
							answerList.add(val);
							break;
						}
					}
				}
			}
			questAccess.saveUserAnswers(answerList);	
		}
		
		return mapping.findForward("confirm");
	}
	
	private boolean isDuplicateQuestion(List ansList) {
		
		int size = ansList.size();
		for (int i=0; i < size; i++) {
			QuestionValue questVal = (QuestionValue)ansList.get(i);
			String questionId = questVal.getQuestionId();
			// see if this question exist elsewhere in the list
			for (int x = 0; x < size; x++ ) {
				// dont check the same record as what we pulled in the upper loop
				if (x != i) {
					QuestionValue questVal2 = (QuestionValue)ansList.get(x);
					if (questionId.equals(questVal2.getQuestionId())) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
}
