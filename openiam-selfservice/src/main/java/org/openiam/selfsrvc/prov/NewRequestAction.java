//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.selfsrvc.prov;


import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
//import org.openiam.selfsrvc.util.*;

import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.*;

import diamelle.common.org.*;
import diamelle.common.service.Service;
import diamelle.common.service.ServiceMgr;
import diamelle.common.status.StatusCodeValue;
import diamelle.ebc.user.*;
//import diamelle.common.company.*;
import diamelle.security.auth.*;


import diamelle.util.Log;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;

/** 
 * MyEclipse Struts
 * Creation date: 02-25-2005
 * 
 * XDoclet definition:
 * @struts:action path="/customerProfile" name="customerProfileForm" input="/customerProfile.jsp" scope="request" validate="true"
 */
public class NewRequestAction extends NavigationDispatchAction {

	String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://olpsdb01.ocfl.net:3306/user_account_management";
	String username ="openiam";
	String password = "ss0126";

	
	public NewRequestAction() {

	}
	
	

	
   /** 
     * Creates a new User or Saves a Users details if its an existing user.
     */
     public ActionForward saveRequest ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
 	              
    	 return (mapping.findForward("success"));

      
        
    }
     
 
 

 
    /** Private methods **/

    /**
     *  Retrieves the information of the user that is logged in.
     */
    public ActionForward view (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resb){
        HttpSession session = request.getSession();
        String personId = null;
        //DynaValidatorForm userForm = (DynaValidatorForm) form;
        
        session.setAttribute("departmentList", department());
        session.setAttribute("divisionList", division());

        return (mapping.findForward("new"));

    }
    
    private void loadStaticData() {
    	
    	department();
    }
    
    private List department() {
 		Connection con = null;
 		List departmentList = new ArrayList();


 		try {
		  

 			String sql = "SELECT ID, DEPARTMENT FROM DEPARTMENTS";
 			Class driverObject = Class.forName(driver);
 			con = DriverManager.getConnection(url, username, password);
 
 			PreparedStatement stmt = con.prepareStatement(sql);
 			  
 			ResultSet rs = stmt.executeQuery();
 			departmentList.add(new LabelValueBean("",""));
 		    while (rs.next()) {
 				  String id = rs.getString(1);
 				  String department = rs.getString(2);
 				  System.out.println("Department:" + department);
 		      	  LabelValueBean label = new LabelValueBean(department, id);
 		       	  departmentList.add(label);

 		    }

 			  
 		}catch(Exception e) {
 			e.printStackTrace();
 		}finally {
 			try {
 			con.close();
 			}catch(Exception ex) {
 				ex.printStackTrace();
 			}
 		}
 		return departmentList;
    	
    }

    private List division() {
 		Connection con = null;
 		List departmentList = new ArrayList();


 		try {
		  

 			String sql = "SELECT ID, DIVISION FROM DIVISIONS";
 			Class driverObject = Class.forName(driver);
 			con = DriverManager.getConnection(url, username, password);
 
 			PreparedStatement stmt = con.prepareStatement(sql);
 			  
 			ResultSet rs = stmt.executeQuery();
 			departmentList.add(new LabelValueBean("",""));
 		    while (rs.next()) {
 				  String id = rs.getString(1);
 				  String department = rs.getString(2);
 				  System.out.println("Department:" + department);
 		      	  LabelValueBean label = new LabelValueBean(department, id);
 		       	  departmentList.add(label);

 		    }

 			  
 		}catch(Exception e) {
 			e.printStackTrace();
 		}finally {
 			try {
 			con.close();
 			}catch(Exception ex) {
 				ex.printStackTrace();
 			}
 		}
 		return departmentList;
    	
    }


}