//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.webadmin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.rmi.RemoteException;
import java.util.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;

import diamelle.common.status.StatusCodeValue;
import diamelle.security.auth.GroupValue;

/** 
 * MyEclipse Struts
 * Creation date: 02-22-2005
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 */
public class HelpdeskIndexAction extends NavigationAction {

	// --------------------------------------------------------- Instance Variables
	SecurityAccess secAccess = new SecurityAccess();

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			loadStaticData(session);
			List maintMenu = navAccess.getMenuList("HELPDESK");
			session.setAttribute("topLevelMenus", maintMenu);
		}catch(RemoteException re) {
			re.printStackTrace();
		}		
		return mapping.findForward("home");



	}
	private void loadStaticData(HttpSession session) throws RemoteException {
		session.setAttribute("operationList", getOperationStatusList());
		session.setAttribute("groupList", getGroupList());
		
		List hpList = new ArrayList();
        hpList.add(new LabelValueBean(""," "));
        hpList.add(new LabelValueBean("Motorola 760","MT760"));
        hpList.add(new LabelValueBean("Motorola 388","MT388"));
        hpList.add(new LabelValueBean("Motorola 630","MT630"));
        hpList.add(new LabelValueBean("New","New Token Request"));
        session.setAttribute("hpList", hpList);

	}
	private List getOperationStatusList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
        CodeAccess cdAccess = new CodeAccess();
        List codeList = cdAccess.getCodesByService("100","IDM","OPERATION","en");
        if (codeList != null && codeList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i<codeList.size(); i++) {       		
        		StatusCodeValue val = (StatusCodeValue)codeList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getStatusCd());
        	 	newCodeList.add(label);
        	}
        }
        return newCodeList;
    }
	private List getGroupList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
    	List grpList = secAccess.getAllGroups();
         if (grpList != null && grpList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i = 0; i < grpList.size(); i++) {       		
        		GroupValue val = (GroupValue)grpList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
        	 	newCodeList.add(label);
        	}
        }
        return newCodeList;
    }

}