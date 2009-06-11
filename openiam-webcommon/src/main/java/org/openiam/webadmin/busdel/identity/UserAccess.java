package org.openiam.webadmin.busdel.identity;

import diamelle.base.composite.*;
import diamelle.common.user.UserNoteValue;

import java.util.*;
import org.openiam.webadmin.busdel.base.*;
import diamelle.ebc.user.*;
import diamelle.security.auth.*;
import diamelle.util.*;

import java.rmi.*;
import javax.naming.*;

public class UserAccess extends NavigationAccess
{
  private Context ctx = null;
  private UserServiceHome userHome = null;
  private UserService user = null;
  private UserManager userMgr = null;
  private UserManagerHome userMgrHome = null;
  //AuthenticatorAccess authAccess = null; 

  public UserAccess() throws Exception {
    try {
      ctx = DiamelleBaseAccess.getInitialContext();
     // authAccess = new AuthenticatorAccess(); 
      userHome = (UserServiceHome)ctx.lookup("java:comp/env/ejb/UserService");
      userMgrHome = (UserManagerHome)ctx.lookup("java:comp/env/ejb/UserManager");
      user = userHome.create();
      userMgr = userMgrHome.create();
  
    }catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns a list of UserData objects that meet the
   * @param search
   * @return
   */
  public List findUser(UserSearch search) {
  try {
    return user.searchUser(search);
   }catch(Exception e) {
    Log.error(e.getMessage(), e);
    return null;
   }
  }

  /**
   * Returns a single User
   * @param userId
   * @return
   */
  public UserData getUser(String userId) {
  try {
  		return user.getUser(userId);
   }catch(Exception e) {
   		Log.error(e.getMessage(), e);
   		return null;
   }
  }

  public Map getAllAttributes(String userId) throws RemoteException {
  	return user.getAllAttributes(userId);
  }
  public Map getAllAttachments(String userId) throws RemoteException {
  	return user.getAllAttachments(userId);
  }
  public Object getTabDetail(String detailView,String userId) throws RemoteException {
    try {
    if (detailView.equalsIgnoreCase("ATTRIBUTE")) {
      return user.getAllAttributes(userId);
    }
    if (detailView.equalsIgnoreCase("ATTACHMENT")) {
      return user.getAllAttachments(userId);
    }
    if (detailView.equalsIgnoreCase("ORGTREE")) {
      return user.getUserStaff(userId);
    }
    if (detailView.equalsIgnoreCase("PHONE")) {
      return user.getAllPhoneNbrs(userId);
    }
    if (detailView.equalsIgnoreCase("ADDRESS")) {
      return user.getAllAddresses(userId);
    }
    if (detailView.equalsIgnoreCase("EMAIL")) {
      return user.getAllEmails(userId);
    }
    } catch (Exception e) {
         e.printStackTrace();
    }
    return null;
  }

 public List getEmployees(String userId) throws RemoteException {
   return user.getUserStaff(userId);
 }

 public Component getAttribute(String userId, String attrId) {
   try{
     return user.getAttribute(userId,attrId);
   }catch(Exception e) {
     Log.error(e.getMessage(), e);
     return null;
   }
 }

 public Component getAttributeByName(String userId, String name) {
   try{
     Map mp = user.getAllAttributes(userId);
     Iterator it = mp.values().iterator();
     while (it.hasNext()) {
       Component comp = (Component)it.next();
       if (comp.getName().equalsIgnoreCase(name)) {
         return comp;
       }
     }
     return null;
   }catch(Exception e) {
     Log.error(e.getMessage(), e);
     return null;
   }

 }

 public void removeAttribute(String userId, String attrId) {
   try{
     user.removeAttribute(userId,attrId);
   }catch(Exception e) {
     Log.error(e.getMessage(), e);
   }
 }

 public void removeAllAttributes(String userId) {
    try{
      user.removeAllAttributes(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    }
  }
 
 public void removeUser(String userId) {
   try{
     user.removeUser(userId);
   }catch(Exception e) {
     Log.error(e.getMessage(), e);
   }
 }


 public void saveAttribute(Component comp, String userId) throws RemoteException {
   if (comp.getId() == null || comp.getId().length() ==0) {
     comp.setId(this.getNextId("USER_ATTR_ID"));
     user.addAttribute(userId, comp);
   }else {
     user.saveAttribute(userId,comp);
   }
 }

 public Phone getPhone(String userId, String phoneId) throws RemoteException {
     return user.getPhoneNbrById(userId,phoneId);
 }
 public void removePhone(String userId, String phoneId) throws RemoteException {
     user.removePhoneNbrById(userId,phoneId);
 }
 public void savePhone(String userId, Phone phone) throws java.rmi.RemoteException {
   if (phone.getId() == null || phone.getId().length() ==0) {
     phone.setId(getNextId("PHONE_ID"));
     user.addPhone(phone);
   }else {
     user.savePhone(phone);
   }

 }
 public void removeAllPhones(String userId) {
    try{
      user.removeAllPhoneNbrs(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    }
  }

 public void removeAllAddresses(String userId) {
    try{
      user.removeAllAddresses(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    }
  }
 
 public void removeAllEmails(String userId) {
    try{
      user.removeAllEmails(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    }
  }
 public void removeAllAttachments(String userId) {
    try{
      user.removeAllAttachments(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    }
  }
 public void removeUserFromAllGroups(String userId) {
    try{
    	this.userMgr.removeUserGroups(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    }
  } 
 public void removeAllUserLogins(String userId) {
    try{
    	userMgr.removeUserLogins(userId);
    }catch(Exception e) {
      Log.error(e.getMessage(), e);
    } 	
 }
 public void createUser(UserData ud) throws RemoteException {
 	
   if(ud.getId() == null || ud.getId().length()==0) {
   	String newUserId = getNextId("USER_ID");
   	ud.setId(newUserId);
   }
   user.addUser(ud);
 }

 public void saveUser(UserData ud) throws RemoteException {
   user.saveUser(ud);
 }

 public Address getAddress(String userId, String addressId) throws RemoteException {
     return user.getAddressById(userId,addressId);
 }
 public void removeAddress(String userId, String addressId) throws RemoteException {
     user.removeAddressById(userId,addressId);
 }

 public Address getAddressByName(String userId, String name) throws RemoteException {
    return user.getAddressByDescription(userId,name);
}
 public Phone getPhoneByName(String userId, String name) throws RemoteException {
    return user.getPhoneNbrByDescription(userId,name);
} 
 
public Email getEmailByName(String userId, String name) throws RemoteException {
    return user.getEmailByDescription(userId,name);
}
 
 public void saveAddress(String userId, Address address) throws RemoteException {

   if (address.getId() == null  || address.getId().length() ==0) {
     address.setId(getNextId("ADDRESS_ID"));
     user.addAddress(address);
   }else {
     user.saveAddress(address);
   }

 }


 public Email getEmail(String userId, String emailId) throws RemoteException {
     return user.getEmailById(userId,emailId);
 }
 public void removeEmail(String userId, String emailId) throws RemoteException {
     user.removeEmailById(userId,emailId);
 }
 public void saveEmail(String userId, Email address) throws RemoteException {
   if (address.getId() == null || address.getId().length() ==0) {
     address.setId(getNextId("EMAIL_ID"));
     user.addEmail(address);
   }else {
     user.saveEmail(address);
   }

 }

 public AttachmentProperty getAttachment(String userId, String attachmentId) throws RemoteException {
     return user.getAttachmentById(userId,attachmentId);
 }
 public void removeAttachmentProperty(String userId, String attachmentId) throws RemoteException {
     user.removeAttachment(userId,attachmentId);
 }
 public void saveAttachment(String userId, AttachmentProperty prop) throws RemoteException {
   if (prop.getId() == null || prop.getId().length() ==0) {
     prop.setId(getNextId("ATTACHMENT_ID"));
     user.addAttachment(userId,prop);
   }else {
     user.saveAttachment(userId, prop);
   }

 }


 public void removeEmployeeFromUser(String userId, String employeeId) throws RemoteException {
     user.removeUserStaff(userId,employeeId);
 }
 public void addEmployeeToUser(String userId, String employeeId) throws RemoteException {
   user.addUserStaff(userId, employeeId);
 }


 // returns a list of groups to which the user belongs
   public List getUserGroups(String searchValue) throws java.rmi.RemoteException {
          List userList = userMgr.getUserGroups(searchValue);
          return userList;
   }

   //deletes the user from the group
   public void deleteUserGroup(String userId, String groupId) throws java.rmi.RemoteException {
         userMgr.removeUserGroup(userId,groupId);
   }

   // adds the user to the group
      public void addUserGroup(String userId, String groupId) throws java.rmi.RemoteException {
            userMgr.addUserToGroup(userId,groupId);
   }


   // saves the user details new/existing
   public void  getUser(String userId, org.apache.struts.validator.DynaValidatorForm df,boolean b, String edit) throws Exception {
      UserData userData = user.getUser(userId);
      
      //     User user = userHome.findByPrimaryKey(new UserKey(userId));
      //     UserData userData = new UserData();
           if (user != null) {
              userData.setFirstName((String)df.get("firstName"));
              userData.setMiddleInit((String)df.get("middleName"));
              userData.setLastName((String)df.get("lastName"));
              userData.setLoginId((String)df.get("login"));
              userData.setPassword((String)df.get("password"));
              userData.setEmail((String)df.get("email"));
              userData.setUserInd((String)df.get("userInd"));
              userData.setCompanyId((String)df.get("companyId"));
          	  user.saveUser(userData);

              // data for adding email
               if(b == true) {
                  Email em = EmailFactory.create();
                  em.setId(getNextId("EMAIL_ID"));
                  em.setParentId(userData.getId());
                  em.setEmailAddress(userData.getEmail());
                  em.setDescription(userData.getEmail());

                  user.addEmail(em);
               } else {
                   if (edit.equals("edit")) {
                     String email = (String) df.get("email");
                     String emailId = (String) df.get("mailId");
                     if (emailId.length() != 0) {
                        Email em = user.getEmailById(userData.getId(), (String)df.get("mailId") );
                       // Email em = user.getEmailById((String)df.get("mailId"));
                        em.setParentId(userData.getId());
                        em.setEmailAddress(userData.getEmail());
                        em.setDescription(userData.getEmail());
                        user.addEmail(em);
                     } else {
                          Email em = EmailFactory.create();
                          em.setId(getNextId("EMAIL_ID"));
                          em.setParentId(userData.getId());
                          em.setEmailAddress(userData.getEmail());
                          em.setDescription(userData.getEmail());
                          user.addEmail(em);
                     }
                   }

               }
           }
   }

  // returns the user details based on the userid
   public UserData getUserData(String userId) throws Exception {
          return user.getUser(userId);
   }

   // gets a list of users based on the search value(loginid and lastname)
  // public List getUsers(SearchItem searchItem) throws java.rmi.RemoteException {
  //      List userList = userMgr.getUsers(searchItem);
  //      return userList;
  // }

   // returns a list of users based on the group
   public List getUserByGrpName(String searchValue) throws java.rmi.RemoteException {
          List userList = userMgr.getUsersByGrpName(searchValue);
          return userList;
   }

   // adds a user to the user table
   public void create(UserData userData) throws Exception {
   user.addUser(userData);
   }



   public List getUserList(UserSearch userSearch) throws java.rmi.RemoteException {
         List userList = (List) user.searchUser(userSearch);
         return userList;
   }
   
   public List getAccountContacts(String accountId) throws RemoteException {
   		return user.getAccountContacts(accountId);
   }

   public Address getDefaultAddress(String userId) throws RemoteException {
	  return user.getDefaultAddress(userId);
   }

   public Email getDefaultEmail(String userId) throws RemoteException {
   	  return user.getDefaultEmail(userId);
   }	

   public Phone getDefaultPhone(String userId) throws RemoteException {
	  return user.getDefaultPhoneNbr(userId);
   }

   public Map getAllAddresses(String userId) throws RemoteException {
	  return  user.getAllAddresses(userId);
   }

   public Map getAllEmails(String userId) throws RemoteException {
 	  return user.getAllEmails(userId);
   }	

   public Map getAllPhoneNbrs(String userId) throws RemoteException {
	  return user.getAllPhoneNbrs(userId);
   }   
   
   public List getUserNotes(String userId) throws RemoteException{
   		return user.getUserNotes(userId);
   }
   
   public void addUserNote(UserNoteValue val) throws RemoteException {
   		if (val.getUserNoteId() == null || val.getUserNoteId().length()==0) {
   			val.setUserNoteId( getNextId("NOTE_ID"));
   		}
   		user.addUserNote(val);
   }
}