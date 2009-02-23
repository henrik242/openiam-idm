<!--List of groups for the role selected  -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.openiam.idm.srvc.user.dto.User" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%! 

  // Method to see if a role exists in the roleAry

 public boolean contains (User[] userAry, User usr ) {
	  if (userAry == null)
		  return false;
	  
	  int size = userAry.length;
	  for (int i=0; i<size; i++) {
		  User tempUser = userAry[i];
		  if ( tempUser.getUserId().equals(usr.getUserId()) ) {
				  return true;
		  }
	  }
	  return false;
  }

%>

<%

  List<User> userList = (List<User>) request.getAttribute("userList");
  User[] userRoleAry = (User[]) request.getAttribute("userRoleAry");

  RoleValue roleValue = (RoleValue) request.getAttribute("roleValue");
  String roleId = roleValue.getRoleId();  
  
%>
<br>
<table align="center" border="0" cellspacing=0 cellpadding=0  width="75%" height="40%">
  <tr>
    <td valign="top">
       <html:form action = "/security/roleUser">
       <table width="100%" align="center" border="0" cellspacing=2 cellpadding=1  >
          

          <% if ( userList.size() > 0) {%>
          <tr>
               <td align = "center" valign = "top" class="tddark" >
                 <b>List of Users</b>
                 <html:hidden property="roleId" value="<%=roleId%>" />
               </td>
               <td>&nbsp</td>
               <td class="tddark" align = "center" valign="top">
                 <b>Users  for <%=roleId%></b>
              </td>
          </tr>
          <tr>
            <td class="tdlight">
              <%

                  Iterator uit = userList.iterator();
                  while (uit.hasNext()) {
                      	User uv = (User)uit.next();
                        if(userRoleAry !=null && contains(userRoleAry,uv)){
              %>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <!--a href="<%=request.getContextPath()%>/group.do?personId=<%=uv.getUserId()%>&actionCommand=Edit&roleId=<%=roleId%>"-->
                          &nbsp;&nbsp;<%=uv.getFirstName()%> <%=uv.getLastName()%><br>
                                 <!--/a-->
             <%
                        } else {
              %>
            <input type="checkbox" name="userId" value="<%=uv.getUserId()%>"/>
                                     <!--a href="<!--%=request.getContextPath()%>/group.do?groupId=<!--%=gv.getGrpId()%>&actionCommand=Edit&roleId=<!--%=roleId%>" class="link2"-->
                                       <%=uv.getFirstName()%> <%=uv.getLastName()%><br>
                                     <!--/a-->
               <%
                         }
                    }

              %>

              </td>
              <td width="15%">
                 <table align="center" width="5%">
                    <tr>
                       <td align="center">
                         <% if (userList != null &&  userList.size() > 0) {%>
                            <html:submit property="submit" value=">" />
                         <%}%>
                       </td>
                     </tr>
                     <tr>
                       <td align="center">
                         <% if (userRoleAry != null && userRoleAry.length > 0 ) { %>
                            <html:submit property="submit" value="<" />
                         <%}%>
                       </td>
                     </tr>
                  </table>
              </td>
              <td class="tdlight">
              <%
        	   if (userRoleAry != null && userRoleAry.length > 0) {
	            	int size = userRoleAry.length;
	            	for (int i=0; i < size; i++ ) {
	            		User usr = (User)userRoleAry[i];
        		   
	             %>

                      <input type="checkbox" name="userroleId" value="<%=usr.getUserId()%>"/>
                       &nbsp;&nbsp;<%=usr.getFirstName()%> <%=usr.getLastName()%><br>
                     <%
                      }
                    } else {%>
                        &nbsp;
                <%}%>
              </td>
           </tr>

          <%} else {%>
            <tr>
                  <td align = "center" valign="top" >
                    <b>No Users Associated with this Role</b>
                  </td>

            </tr>
            <%}%>
        </table>
       </html:form>
    </td>
  </tr>
</table>


