package org.openiam.selfsrvc.pswd;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;

/**
 * Command object for the ForgotLoginController
 * @author suneet
 *
 */
public class ForgotLoginCommand implements Serializable {
	 


	/**
	 * 
	 */
	private static final long serialVersionUID = 7461609519199171165L;

	private String emailAddres;
	private String phone;
	private String firstName;
	private String lastName;
	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	private String submit;
	
	public ForgotLoginCommand( ) {
		super();
		
	}	
	

	

	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}




	public String getEmailAddres() {
		return emailAddres;
	}




	public void setEmailAddres(String emailAddres) {
		this.emailAddres = emailAddres;
	}
	


	
	

	

}
