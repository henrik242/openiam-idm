<!--List of groups for the role selected  -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<body>
<%



if ((request.getAttribute("grpVal")) != null) {
  GroupValue  grpVal = (GroupValue) request.getAttribute("grpVal");
}

  List grpList = (List) request.getAttribute("grpList");
  List grpRoleList = (List) request.getAttribute("grpRoleList");

  RoleValue roleValue = (RoleValue) request.getAttribute("roleValue");
  String roleId = roleValue.getRoleId();  
  
%>
<br>
<table align="center" border="0" cellspacing=0 cellpadding=0  width="75%" height="40%">
  <tr>
    <td valign="top">
       <html:form action = "/security/roleGroup">
       <table width="100%" align="center" border="0" cellspacing=2 cellpadding=1  >
          

          <% if ( grpList.size() > 0) {%>
          <tr>
               <td align = "center" valign = "top" class="tddark" >
                 <b>List of Groups</b>
                 <html:hidden property="roleId" value="<%=roleId%>" />
               </td>
               <td>&nbsp</td>
               <td class="tddark" align = "center" valign="top">
                 <b>Groups  for <%=roleId%></b>
              </td>
          </tr>
          <tr>
            <td class="tdlight">
              <%

                  Iterator git = grpList.iterator();
                    while (git.hasNext()) {
                      GroupValue gv = (GroupValue)git.next();
                        if(grpRoleList!=null && grpRoleList.contains(gv)){
              %>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <!--a href="<%=request.getContextPath()%>/group.do?groupId=<%=gv.getGrpId()%>&actionCommand=Edit&roleId=<%=roleId%>"-->
                          &nbsp;&nbsp;<%=gv.getGrpName()%><br>
                                 <!--/a-->
             <%
                        } else {
              %>
            <input type="checkbox" name="groupId" value="<%=gv.getGrpId()%>"/>
                                     <!--a href="<!--%=request.getContextPath()%>/group.do?groupId=<!--%=gv.getGrpId()%>&actionCommand=Edit&roleId=<!--%=roleId%>" class="link2"-->
                                       <%=gv.getGrpName()%><br>
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
                         <% if ( grpList.size() > 0) {%>
                            <html:submit property="submit" value=">" />
                         <%}%>
                       </td>
                     </tr>
                     <tr>
                       <td align="center">
                         <% if (grpRoleList.size() > 0 ) { %>
                            <html:submit property="submit" value="<" />
                         <%}%>
                       </td>
                     </tr>
                  </table>
              </td>
              <td class="tdlight">
              <%
        	   if (!grpRoleList.isEmpty()) {
	             Iterator iter = grpRoleList.iterator();
          	     while (iter.hasNext()) {
	               GroupValue gd = (GroupValue)iter.next();
	             %>

                      <input type="checkbox" name="grproleId" value="<%=gd.getGrpId()%>"/>
                       &nbsp;&nbsp;<%=gd.getGrpName()%><br>
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
                    <b>No Groups Available</b>
                  </td>

            </tr>
            <%}%>
        </table>
       </html:form>
    </td>
  </tr>
</table>
</body>

