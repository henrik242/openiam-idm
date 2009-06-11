package org.openiam.webadmin.busdel.security;

import org.openiam.webadmin.busdel.base.NavigationAccess;
import diamelle.security.idquest.*;
import java.rmi.RemoteException;
import java.util.*;


public class IdQuestionAccess extends NavigationAccess  {
  IdQuestionManager idQuest = null;

  public IdQuestionAccess() {
     super();
     try {
     	IdQuestionManagerHome home = (IdQuestionManagerHome)getHome("IdQuestionManager");
     	idQuest = home.create();
     } catch(Exception e) {
       e.printStackTrace();
     }
  }
  
  /**
   * Returns a list of questions for the company. If the setting is GLOBAL, then
   * pass in "GLOBAL" as the companyId 
   * @param companyId
   * @return
   * @throws RemoteException
   */
  public QuestionGroupValue getQuestionsByGroup(String groupId) throws RemoteException {
  	return idQuest.getQuestionsByGroup(groupId);
  }

  public boolean isValue(String login, String serviceId, 
  						 String questGrpId,  List answerList) throws RemoteException {
  	return idQuest.isResponseValid(login, serviceId, questGrpId, answerList);
  }
  
  public List getUserAnswers(String userId) throws RemoteException {
  	return idQuest.getAnswersByUser(userId);
  }
  public List getAllQuestions() throws RemoteException {
  	return idQuest.getAllQuestions();
  }
  public List getSelectedQuestions(String questionList) throws RemoteException {
  	List questIdList = new ArrayList();
  	StringTokenizer tokenizer = new StringTokenizer(questionList,",");
  	while ( tokenizer.hasMoreTokens() ) {
  		String questId = tokenizer.nextToken();
  		questIdList.add(questId);
  	}
  	return idQuest.getSelectedQuestions(questIdList);
  }

  public List getSelectedQuestions(List questionIdList) throws RemoteException {
  	return idQuest.getSelectedQuestions(questionIdList);
  }
  
  public void addUserAnswers(List answerList) throws RemoteException {
	  // set the ids for each object in the list
	  
	  int size = answerList.size();
	  for (int i=0; i < size; i++) {
		  QuestionValue val = (QuestionValue)answerList.get(i);
		  if (val.getAnswerId() == null || val.getAnswerId().length() ==0 ) {
			  val.setAnswerId( getNextId("ANSWER_ID") );
			  answerList.set(i,val);
			  System.out.println("In addAnswer - Id = " + val.getAnswerId() + " questionid = " + val.getQuestionId());
		  }
	  }
	  idQuest.addAnswers(answerList);
  }
  
  public void saveUserAnswers(List answer) throws RemoteException {
	  idQuest.saveAnswers(answer);
  }
  
}