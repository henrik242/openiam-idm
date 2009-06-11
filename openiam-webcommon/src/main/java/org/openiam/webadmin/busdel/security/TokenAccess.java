/*
 * Created on Jan 7, 2005
 *
 */
package org.openiam.webadmin.busdel.security;

import java.rmi.RemoteException;
import java.util.List;

import org.openiam.webadmin.busdel.base.NavigationAccess;
import diamelle.security.auth.LoginValue;
import diamelle.security.token.*;

public class TokenAccess extends NavigationAccess {

	TokenManager tokenMgr = null;
	
	public TokenAccess() {
	     super();
	      try {
	      	TokenManagerHome tokenHome = (TokenManagerHome)getHome("TokenManager");
	      	tokenMgr = null;
	      	//tokenHome.create();
	      } catch(Exception e) {
	        e.printStackTrace();
	      }
	}
	public List search(TokenSearch search) throws RemoteException {
		return tokenMgr.search(search);		
	}
	
	public void generateTokens(String companyId, int requestedTokens, int licensedQty, String createdBy) throws RemoteException {
		tokenMgr.createTokens(companyId, requestedTokens, licensedQty, createdBy);
	}
	
	public TokenValue getToken(String tokenSerialNbr)throws RemoteException {
		return tokenMgr.getToken(tokenSerialNbr);		
	}
	public void saveToken(TokenValue val) throws RemoteException{
		tokenMgr.saveToken(val);
	}
	public void addTokenUserRequest(TokenRequestValue val) throws RemoteException {
		if (val.getRequestId() == null) {
			val.setRequestId(getNextId("TOKEN_REQ_ID"));
		}
		tokenMgr.addUserRequest(val);
	}
	public void saveTokenUserRequest(TokenRequestValue val) throws RemoteException {
		tokenMgr.saveUserRequest(val);
	}
	public TokenRequestValue getUserRequest(String userId) throws RemoteException {
		return tokenMgr.getUserRequest(userId);
	}
	
	public TokenRequestValue assignTokenToUser(String userId) throws RemoteException {
		return tokenMgr.assignUserToToken(userId);
	}
	public TokenValue getAvailableToken() throws RemoteException{
		return tokenMgr.getAvailableToken();
	}
	public List getUsersPendingAssignment() throws RemoteException {
		return tokenMgr.getUsersPendingAssignment();
	}
	
	public List getTokenNotes(String serialNbr) throws RemoteException {
		return tokenMgr.getTokenNotes(serialNbr);
	}
	public TokenNoteValue getTokenNote(String tokenSerialNbr, String noteId) throws RemoteException{
		return tokenMgr.getTokenNote(tokenSerialNbr, noteId);
	}
	public void addTokenNote(TokenNoteValue val) throws RemoteException {
   		if (val.getTokenNoteId() == null || val.getTokenNoteId().length()==0) {
   			val.setTokenNoteId( getNextId("NOTE_ID"));
   		}
		tokenMgr.addTokenNote(val);	
	}
	public void saveTokenNote(TokenNoteValue val) throws RemoteException {
		tokenMgr.saveTokenNote(val);		
	}
	public void removeTokenNote(String tokenSerialNbr, String noteId) throws RemoteException {
		tokenMgr.removeTokenNote(tokenSerialNbr, noteId);			
	}
	
	public boolean tokenAuth( LoginValue val) throws RemoteException {
		return tokenMgr.tokenAuth(val);
	}
	public String getActivationCode( String tokenSerialNbr) throws RemoteException {
		return tokenMgr.genActivationCode(tokenSerialNbr);
	}
}

