package org.openiam.webadmin.access;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

public class RoleUserAction extends NavigationAction{

   public ActionForward execute (ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res) throws IOException ,ServletException {

      System.out.println("in roleUser action .... ");
      try {
           SecurityAccess securityAccess = new SecurityAccess();
           
           WebApplicationContext  webContext = getWebApplicationContext();
           RoleDataService roleDataSrvc = (RoleDataService)webContext.getBean("roleDataService");
           
           HttpSession session = req.getSession();
           String serviceId =  (String)session.getAttribute("serviceId");
           

           DynaValidatorForm roleForm = (DynaValidatorForm) form;
           String submit = (String) roleForm.get("submit");
           String roleId = (String) roleForm.get("roleId");
           
           // used when we forward back to role.
           req.setAttribute("roleId", roleId);

             if (submit.equals(">")) {
                // to add a group to a role
                if (req.getParameterValues("userId") != null) {
                  String[] userId = req.getParameterValues("userId");
                  for(int i = 0; i < userId.length ; i++) {
                	  roleDataSrvc.addUserToRole(serviceId, roleId, userId[i]);
                     //securityAccess.addGroupForRole(grpId[i],roleId);
                	  //roleDataSrvc.addGroupToRole("IDM", roleId, grpId[i]);
                	  //String serviceId, String roleId, String groupId
                  }
                }

             } else if (submit.equals("<")) {
                   if (req.getParameterValues("userroleId") != null) {
                      String[] userId = req.getParameterValues("userroleId");
                      for(int i = 0; i < userId.length ; i++)
                        //securityAccess.deleteGrpRole(grpId[i],roleId);
                    	  roleDataSrvc.removeUserFromRole(serviceId, roleId, userId[i]);
                   }
             }
      } catch (Exception e) {
              e.printStackTrace();
              return (mapping.findForward("groupslist"));
      }
      return (mapping.findForward("success"));
   }

}