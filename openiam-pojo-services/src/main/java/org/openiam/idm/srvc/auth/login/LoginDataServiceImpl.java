package org.openiam.idm.srvc.auth.login;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.util.encrypt.*;

import java.util.*;

import javax.jws.WebService;

@WebService(endpointInterface = "org.openiam.idm.srvc.auth.login.LoginDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/auth/service", 
		serviceName = "LoginWebService")
public class LoginDataServiceImpl implements LoginDataService {

	protected LoginDAO loginDao;
	protected LoginAttributeDAO loginAttrDao;
	
	protected Cryptor cryptor;

	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	boolean encrypt = true;	// default encryption setting
	
	public Login addLogin(Login login) {
		if (login == null)
			throw new NullPointerException("Login is null");
		
		// encrypt the password before saving it
       if (encrypt && login.getPassword() != null) {
        	login.setPassword(cryptor.encrypt(login.getPassword()));
        };

        
        if (login.getCreateDate() == null)
        	login.setCreateDate(new Date(System.currentTimeMillis()));
	
		return loginDao.add(login);

	}

	public Login getLogin(String serviceId, String login) {
		if (serviceId == null)
			throw new NullPointerException("service is null");
		if (login == null)
			throw new NullPointerException("Login is null");
		LoginId id = new LoginId(serviceId, login, serviceId);
		
		Login lg = loginDao.findById(id);
		System.out.println("Lg=" + lg);

		// decrypt the password and then return the object
		if (lg != null && lg.getPassword() != null) {
			lg.setPassword( cryptor.decrypt(lg.getPassword()) ) ;
		}
		//
		return lg;
	}

	public Login[] getLoginByUser(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		List<Login> loginList = loginDao.findUser(userId);
		if (loginList == null || loginList.size() == 0)
			return null;
		
		int size = loginList.size();
		Login[] loginAry = new Login[size];
		
		// decrypt the passwords for each login
		for (int i=0;i<size;i++) {
			Login lg = loginList.get(i);
			if (lg.getPassword() != null) {
				System.out.println("Login password = " + lg.getPassword());
				lg.setPassword( cryptor.decrypt(lg.getPassword()) ) ;
			}
			loginAry[i] = lg;
		}
		return loginAry;
		
		
	}
	
	public Login[] getLoginByDomain(String domain) {
		if (domain == null)
			throw new NullPointerException("domain is null");
		List<Login> loginList = loginDao.findLoginByDomain(domain);
		if (loginList == null || loginList.size() == 0)
			return null;
		
		int size = loginList.size();
		Login[] loginAry = new Login[size];
		
		// decrypt the passwords for each login
		for (int i=0;i<size;i++) {
			Login lg = loginList.get(i);
			if (lg.getPassword() != null) {
				System.out.println("Login password = " + lg.getPassword());
				lg.setPassword( cryptor.decrypt(lg.getPassword()) ) ;
			}
			loginAry[i] = lg;
		}
		return loginAry;
		
	}

	public void lockLogin(String serviceId, String login) {
		// TODO Auto-generated method stub

	}

	public void removeLogin(String serviceId, String login) {
		if (login == null)
			throw new NullPointerException("Login is null");
		Login loginDto = new Login();
		LoginId id = new LoginId(serviceId, login, null);
		loginDto.setId(id);
		loginDao.remove(loginDto);
	}

	public void unLockLogin(String serviceId, String login) {
		// TODO Auto-generated method stub

	}

	public void updateLogin(Login login) {
		if (login == null)
			throw new NullPointerException("Login is null");
		
		// encrypt the password before saving it
       if (encrypt && login.getPassword() != null) {
        	login.setPassword(cryptor.encrypt(login.getPassword()));
       };
        
       loginDao.update(login);
	}

	public LoginDAO getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public LoginAttributeDAO getLoginAttrDao() {
		return loginAttrDao;
	}

	public void setLoginAttrDao(LoginAttributeDAO loginAttrDao) {
		this.loginAttrDao = loginAttrDao;
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor crypt) {
		this.cryptor = crypt;
	}

}
