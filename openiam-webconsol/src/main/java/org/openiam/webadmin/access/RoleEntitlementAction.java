package org.openiam.webadmin.access;

import org.openiam.webadmin.busdel.security.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

import org.openiam.webadmin.busdel.base.*;

public class RoleEntitlementAction extends NavigationAction {

    public ActionForward execute (ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException ,ServletException {

         System.out.println("in role entitlement action ..");

         DynaValidatorForm roleForm = (DynaValidatorForm) form;
         String submit = (String) roleForm.get("submit");
         String roleId = (String) roleForm.get("roleId");

         try {

			SecurityAccess securityAccess = new SecurityAccess();

            if (submit != null && submit.equals("<")) {
                   if (req.getParameterValues("enId") != null ) {
                       String[] entId = req.getParameterValues("enId");
                       for(int i = 0; i < entId.length; i++) {
                           securityAccess.deleteRoleEntitlement(roleId,entId[i]);
                       }
                   }

            } else if (submit.equals(">")) {
                   // adds entitlement to the role
                   if (req.getParameterValues("entitlementId") != null ) {
                       String[] entitleId = req.getParameterValues("entitlementId");
                       for(int i = 0; i < entitleId.length; i++) {
                               securityAccess.addRoleEntitlement(roleId,entitleId[i]);
                       }
                   }
            }
         } catch (Exception e) {
                e.printStackTrace();
         }
         return (mapping.findForward("success"));
    }

}