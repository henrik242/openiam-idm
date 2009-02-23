package org.openiam.webadmin.login;

import javax.servlet.http.*;
import org.apache.struts.action.*;

/**
 * <p>
 * <code></code> <font face="arial">
 * </font>
 * </p>
 */


public class LoginForm extends ActionForm {

  private String serviceId = null;
  private String password = null;
  private String login = null;
  private String submit = null;
  private String otp = null;

/**
 * @return Returns the otp.
 */
public String getOtp() {
	return otp;
}
/**
 * @param otp The otp to set.
 */
public void setOtp(String otp) {
	this.otp = otp;
}
  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getPassword() {
      return (this.password);
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getLogin() {
      return (this.login);
  }

  public void setLogin(String login) {
      this.login = login;
  }

  public void setSubmit(String submit) {
    this.submit = submit;
  }

  public String getSubmit() {
    return submit;
  }



  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.login = null;
    this.password = null;
    this.serviceId = null;
  }

  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
      System.out.println("inside validate submit = " + submit);
      ActionErrors errors = new ActionErrors();
      if (submit != null) {
        if (submit.equals("login")){
        //  if ((login == null) || (login.length() < 1))
        //      errors.add("login", new ActionError("error.login.idrequired"));
       //   if ((password == null) || (password.length() < 1))
       //       errors.add("password", new ActionError("error.login.pwrequired"));

          //dont do this - mapping is frozen and raises exception 
          //mapping.setInput("/home.jsp?bodyjsp=logon.jsp");
         }
      }
      return errors;

  }


}