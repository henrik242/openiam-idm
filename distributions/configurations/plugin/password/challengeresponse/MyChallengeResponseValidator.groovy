
import org.openiam.idm.srvc.pswd.service.ChallengeResponseValidator;
import org.openiam.idm.srvc.pswd.dto.ChallengeResponseUser;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.exception.data.IdentityAnswerNotFoundException;
import org.openiam.exception.data.PrincipalNotFoundException;
import java.util.List
import java.util.ArrayList


// import groovy packages for sql
import groovy.sql.*



public class MyChallengeResponseValidator implements ChallengeResponseValidator  {

/**
	 * Returns the list of questions based on the parameters specified in the ChallengeResponseUser object.
	 * @param req
	 * @return
	 */
	List<IdentityQuestion> getQuestions(ChallengeResponseUser req) {
		System.out.println("getQuestions called in MyChallengeRespnseValidator");
		
		List<IdentityQuestion> questList = new ArrayList<IdentityQuestion>()
		
		def db='jdbc:mysql://localhost:3306/gilt'
		def user='demouser'
		def password='demouser'
		def driver='com.mysql.jdbc.Driver'
		
		// connect to the db
		def sql = Sql.newInstance(db,user, password, driver)
		
		println("connected to the db")
		// query the table
		
		sql.eachRow('select IDENTITY_QUESTION_ID, QUESTION_TEXT from IDENTITY_QUESTION') { id ->
			IdentityQuestion question = new IdentityQuestion()
			question.identityQuestionId = id.IDENTITY_QUESTION_ID
			question.questionText = id.QUESTION_TEXT
			questList.add(question)
		}
			
		return questList;
		
	}
	
	/**
	 * Retrives a specific question that is identified by the question id.
	 * @param questionId
	 * @return
	 */
	IdentityQuestion getQuestion(String questionId) {
		return null;
	}
	
	/*
	 * Checks if the user response is valid
	 */
	boolean isResponseValid(ChallengeResponseUser req, List<UserIdentityAnswer> newAnswerList) {
		// get the answers for the user
		List<IdentityQuestion> questList = new ArrayList<IdentityQuestion>()
		def isValid = true;
		
		def db='jdbc:mysql://localhost:3306/gilt'
		def user='demouser'
		def password='demouser'
		def driver='com.mysql.jdbc.Driver'
		
		// connect to the db
		def sql = Sql.newInstance(db,user, password, driver)
		
		sql.eachRow('select u.IDENTITY_ANS_ID, u.IDENTITY_QUESTION_ID, u.QUESTION_TEXT, u.USER_ID, u.QUESTION_ANSWER  ' +
		            ' from USER_IDENTITY_ANS u, LOGIN l ' + 
		            ' WHERE l.LOGIN = ? and l.USER_ID = u.USER_ID ', [req.principal] ) { ans ->
		     
		  def size = newAnswerList.size()
		        
		  for ( count in 0..(size-1)) {
		  	UserIdentityAnswer newAns = newAnswerList.get(count)
		  	if (ans.IDENTITY_ANS_ID == newAns.identityAnsId ) {
		  		println("answer ids match")
					
					if (ans.QUESTION_ANSWER != newAns.questionAnswer ) {
						println("answers dont match=" + newAns.questionAnswer)
						isValid = false;
					} 		
		  	}
		  }         
		            
		}
	  return isValid

	}
	
	
	/**
	 * Saves the answers that a user submits for a set of questions.
	 * @param ansList
	 */
	void saveAnswers(List<UserIdentityAnswer> ansList ) {
	
	}
	
	/**
	 * returns the answers to questions that a user previously provided.
	 * @param userId
	 * @return
	 */
	List<UserIdentityAnswer> answersByUser(String userId) {
			List<UserIdentityAnswer> answerList = new ArrayList<UserIdentityAnswer>()
		
		def db='jdbc:mysql://localhost:3306/gilt'
		def user='demouser'
		def password='demouser'
		def driver='com.mysql.jdbc.Driver'
		
		// connect to the db
		def sql = Sql.newInstance(db,user, password, driver)
		
		sql.eachRow('select IDENTITY_ANS_ID, IDENTITY_QUESTION_ID, QUESTION_TEXT, ' + 
							  'USER_ID, QUESTION_ANSWER from USER_IDENTITY_ANS where USER_ID = ?',
							  [userId]) { a ->
			UserIdentityAnswer ans = new UserIdentityAnswer()
			ans.identityAnsId = a.IDENTITY_ANS_ID
			ans.identityQuestionId = a.IDENTITY_QUESTION_ID
			ans.questionText = a.QUESTION_TEXT
			ans.userId = a.USER_ID
			ans.questionAnswer = a.QUESTION_ANSWER
			answerList.add(ans)
		}
	
	return answerList
	}
	
}

