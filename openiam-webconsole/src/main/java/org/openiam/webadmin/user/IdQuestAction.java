package org.openiam.webadmin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;

import diamelle.security.idquest.QuestionValue;

/**
 * @version 	1.0
 * @author
 */
public class IdQuestAction extends NavigationAction  {
	IdQuestionAccess questAccess = new IdQuestionAccess();
	PolicyAccess policyAccess = new PolicyAccess();
	AuthenticatorAccess authAccess = new AuthenticatorAccess();

	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

		String mode = request.getParameter("mode");
		QuestionValue questVal = null;
		List answerList = new ArrayList();
		
		HttpSession session = request.getSession();
		String userId = (String)request.getParameter("personId");
		
		Enumeration<String> en = request.getParameterNames();
		
		if (mode.equals("new")) {
			
			while (en.hasMoreElements() ) {
				String elem = en.nextElement();
				// find the parameters that question and answers are based on
				int index = elem.indexOf("_");
				if (elem.indexOf("_") > 0 && elem.startsWith("quest")) {
					int strSize = elem.length();
					String questionIndex = elem.substring(index+1, strSize);
					int questIdx = Integer.parseInt( questionIndex );				
					String questionId = request.getParameter("question_" + questIdx);
					String answerText = request.getParameter("answer_" + questIdx);				
				
					if (questionId == null || questionId.length()==0) {
						request.setAttribute("errMsg","Please select a question from the dropdown.");
						return (mapping.findForward("success"));
					}
					if (answerText == null || answerText.length()==0) {
						request.setAttribute("errMsg","Please provide answers to all questions.");
						return (mapping.findForward("success"));
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
			if (isDuplicateQuestions(answerList)) {
				request.setAttribute("errMsg","Please select unique questions..");
				return (mapping.findForward("success"));				
			}
		
		}else {
			// get the current list of answers
			List curAnswerList = questAccess.getUserAnswers(userId);
			//
			while (en.hasMoreElements() ) {
				String elem = en.nextElement();
				// find the answerId in the form and then find the answer object in the 
				// current list. Then update that object with the newly updated information
				
				int index = elem.indexOf("_");
				if (elem.indexOf("_") > 0 && elem.startsWith("answer")) {
					int strSize = elem.length();
					String answerIndex = elem.substring(index+1, strSize);
					int ansIdx = Integer.parseInt( answerIndex );				
					String questionId = request.getParameter("question_" + ansIdx);
					String answerText = request.getParameter("answer_" + ansIdx);				
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
			if (isDuplicateQuestions(answerList)) {
				request.setAttribute("errMsg","Please select unique questions..");
				return (mapping.findForward("success"));				
			}
			AuditLogAccess.logEvent("Identity information for " + userId + " has been modified", 
	        		request.getRemoteHost(), 
	           		(String)session.getAttribute("userId"),
					(String)session.getAttribute("login"), "IDM");

		}
		
		return (mapping.findForward("success"));
    }
    
    private boolean isDuplicateQuestions(List answerList) {
     	if (answerList == null) {
    		return false;
    	}
    	int size = answerList.size();
    	if (size > 1) {
    		for (int i=0; i<size; i++) {
    			QuestionValue questVal = (QuestionValue)answerList.get(i);
    			String questId = questVal.getQuestionId();
    			// see if the question exists at a different index
    			for (int x = 0; x<size; x++) {
    				// make sure we are not checking the same index
    				if (x != i) {
    					QuestionValue tempVal = (QuestionValue)answerList.get(x);
    					String tempQuestId = tempVal.getQuestionId();
    					if (tempQuestId.equals( questId)) {
    						return true;
    					}
    				}
    			}
    		}
    	}
    	
    	return false;
    }
}
