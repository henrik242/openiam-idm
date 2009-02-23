<!--list of groups and list of groups to which a user belongs (if any) -->

<%@ page language="java" %>
<%@ page session="true" %>

<%@ page import="java.util.*,org.apache.struts.validator.*" %>
<%@ page import="org.openiam.idm.srvc.role.dto.Role" %>
<%@ page import="org.openiam.webadmin.busdel.base.JSPUtil" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%! 

  // Method to see if a role exists in the roleAry

 public boolean contains (Role[] roleAry, Role rl ) {
	  if (roleAry == null)
		  return false;
	  
	  int size = roleAry.length;
	  for (int i=0; i<size; i++) {
		  Role tempRole = roleAry[i];
		  if ( tempRole.getId().getServiceId().equals(rl.getId().getServiceId()) && 
			   tempRole.getId().getRoleId().equals(rl.getId().getRoleId()) ) {
				  return true;
		  }
	  }
	  return false;
  }

%>


<%

  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("userForm");

  Role[] roleAry = (Role[]) request.getAttribute("roleAry");
  Role[] userRoleAry = (Role[]) request.getAttribute("userRoleAry");

  String personId = (String) request.getAttribute("personId");
  

%>

<html:form action="/idman/userRole.do?method=edit">
<table cellspacing=0 cellpadding=0  width="100%" height="100%">

  <% if (roleAry != null && roleAry.length != 0) {
  %>
  
  <tr>
    <td valign="top">
      <html:errors/>
         <table border="0" align="center" cellspacing=2 cellpadding=1 width="98%">
             
              <html:hidden property="personId" value="<%=personId%>" />
             
              <tr>
              <% if (roleAry.length > 0) {%>
                <td class="tddark"  align = "center" valign="top" class="normalTxtBold">
                       List of Roles
                </td>
                <td width="5%" bgcolor="#98ABCE">&nbsp</td>
            <%}%>

                 <td class="tddark"  align = "center" valign="top" >
                     <b>Roles for User:</b> <%=userForm.get("firstName")%> <%=userForm.get("lastName")%>
                 </td>
              </tr>
              <tr>
                 <td class="tdtab">
                 	<table>
                 		
                 <%
                 	String curServiceId = null;
                 	int size = roleAry.length;
                 	for (int i=0; i < size; i++ ) {
                 		Role rl = roleAry[i];
                 		
                 		if (curServiceId == null || !curServiceId.equals(rl.getId().getServiceId())) {
                 			curServiceId = rl.getId().getServiceId();
                 	%>
                    <tr>
                    	<td colspan=2 class="title"><b>Security Domain: <%=rl.getId().getServiceId() %></b></td>
                    </tr>                 		
                 	<%
                 		}
                 		
                 		if (userRoleAry != null && contains(userRoleAry, rl)) {
                 %>
                    <tr>
                    	<td></td>
                    	<td class="normaltext"><%=rl.getRoleName()%></td>
                    </tr>

                 <%
                    } else {
                 %>
               		<tr>
                    	<td><input type="checkbox" name="roleId" value="<%=rl.getId().getServiceId()%>*<%=rl.getId().getRoleId()%>"/></td>
                    	<td class="normaltext"><%=rl.getRoleName()%>
                    		 <html:hidden property="personId" />
                    	</td>
                    </tr>
                 <%
                         }
                    }
                 //}
                 %>
                 	</table>
                 </td>
                 <td>
                <table>
                     <tr>
                  <td align="center" >
                    <% if (roleAry.length > 0 )  {%>
                      <html:submit property="submit" value=">" />

                    <%}%>
                  </td>
                  </tr>
                  <tr>
                  	<td align = "center">
	                  <% if (userRoleAry !=null && userRoleAry.length > 0 ) { %>
	                      <html:submit property="submit" value="<" />
	                  <%}%>
                	</td>
              	</tr>
              	</table>
              </td>

                  <td class="tdtab">
 <%
                           if (userRoleAry != null &&  userRoleAry.length > 0) {
                        	     int sz = userRoleAry.length;
                            	for (int i=0; i<sz;i++) {
                             		Role rl = userRoleAry[i];
                       %>	
                          <input type = checkbox name ="userroleId" value="<%=rl.getId().getServiceId()%>*<%=rl.getId().getRoleId()%>"/>
                               <%=rl.getRoleName()%><br>
                      <% }} else {%>
                        &nbsp;
                        <%}%>
                      </td>
              </tr>        
          </table>
       </td>
    </tr>
    <% } else { %>
    <tr>
       <td valign="top"  align="center" bgcolor="#cccccc"> No Roles available</td>
    </tr>
    <% } %>
</table>
</html:form>
