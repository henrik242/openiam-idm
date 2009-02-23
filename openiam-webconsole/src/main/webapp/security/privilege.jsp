<!-- adding new resource -->
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*"%>
<%@ page import="java.util.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
  System.out.println("in privilege jsp...");
  DynaValidatorForm privilegeForm = (DynaValidatorForm) request.getAttribute("privilegeForm");
  String mode = (String) privilegeForm.get("mode");
  
%>
<html:html locale="true">
	<body>
		<br><br>
		<html:errors/>
		<html:form action = "/security/privilege.do?method=savePrivilege">
			<table cellspacing=2 cellpadding=2  width="100%" align="center">
				
							
				<html:hidden property="mode" value="<%=mode%>" />
				
				<tr bgcolor="#cccccc">
        	<td align="center" colspan="2" class="th">
        		<%if(mode.equalsIgnoreCase("add")) { %>
	          	<strong>Add Privileges</strong>
	          <%} else {%>
	          	<strong>Privileges Details for <%=(String) request.getParameter("privilegeId")%></strong>
	          <%}%>
           <br>(Fields marked with  *  are compulsory)
					</td>
				</tr>
				 
				<tr bgcolor="#eeeeee">
        	<td class="label" align="right">Privilege Id</td>
          <td><html:text property="privilegeId" maxlength="20"/></td>
        </tr>
        
        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Description <font color="red">*</font></td>
          <td><html:text property="description" maxlength="25" /></td>
        </tr>
               
        <tr bgcolor="#eeeeee">
	        <td align="center" colspan="2">
	        	<html:submit property="submit" value="Save"/>
          </td>
        </tr>
     	</table>
     
		</html:form>
	</body>
</html:html>