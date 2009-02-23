
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,diamelle.security.resource.*" %>
<%@ page import="java.util.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<style type="text/css">
.error {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #FF0000; text-decoration: none}
.link1 {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #000000; text-decoration: none}
.link2 {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #111111; text-decoration: none}
</style>

<body>
<%
   System.out.println("in privileges jsp...");
   List privilege = (List)request.getAttribute("privilege");
   DynaValidatorForm privilegeForm = (DynaValidatorForm) request.getAttribute("privilegeForm");
   String mode = (String) privilegeForm.get("mode");
 
%>

  <html:html locale = "true">
	<body>
		<table cellspacing=2 cellpadding=2 width="400" align="center">
		<html:form action="security/privilege.do?method=removePrivilege">
		<html:hidden property="mode" value="<%=mode%>" />
			<tr>
    		<td colspan="2" align="center" bgcolor="#cccccc" class="th">	
					<strong>Privileges</strong>
   			</td>
			</tr>			
      <tr>
          <td bgcolor="#eeeeee" class="label" align="center">
				Privilege
         </td>
     
 			
          <td bgcolor="#eeeeee" class="label" align="center">
				ID
         </td>
     
       </tr>
     
      <% if (privilege != null) {
           Iterator it = privilege.iterator();
           while (it.hasNext()) {
               PrivilegeValue privilegeVal = (PrivilegeValue) it.next();
               String privilegeId = privilegeVal.getPrivilegeId();
               String description = privilegeVal.getDescription();
      %>
      <tr>
          <td bgcolor="#eeeeee" class="normalTxtBlack">
          <input type="checkbox" name="privilegeId" value="<%=privilegeId%>"/>
          <a href="privilege.do?method=editPrivilege&privilegeId=<%=privilegeId%>" class="link2">
            <%=description%>
           </a>
         </td>
          <td bgcolor="#eeeeee" class="normalTxtBlack">
           <%=privilegeId%>
      </tr>
      <%
        }
      %>
      
      <tr bgcolor="#eeeeee">
			  	<td width="50%" align="center" colspan="2">
			     <html:submit property="submit" value="Delete Item" />
			    </td>
			  </tr>
    
    
     <%}%>
     <tr>
      <td class = "link2" align="center"  colspan="2">
      <a href="privilege.do?method=addPrivilege">New Privilege</a>
       
      </td>
    </tr>
</html:form> 
</table>
	 
<table align="center" width="400">
		<% if(mode.equalsIgnoreCase("add") || mode.equalsIgnoreCase("edit")) { %>     
			<tr><td colspan="2">
    		<jsp:include page="privilege.jsp">
    			<jsp:param name="mode" value="<%=mode%>" />
    		</jsp:include>
  		</td></tr>
 		<%}%>

</table>
</html:html>
</body>
