<!-- adding new role -->
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.security.auth.*"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
  
  DynaValidatorForm roleForm = (DynaValidatorForm)request.getAttribute("roleForm");
  
  String operationMode = (String) roleForm.get("mode");
  String roleId = (String) request.getAttribute("roleId");

%>

<body>

	<html:form action = "/security/roleValidate.do?method=saveRole">
			<table width="100%" border="0" cellspacing=2 cellpadding=1  align="center">
			<br>
				<html:hidden property="mode" value="<%=operationMode%>" />
				<html:hidden property="serviceId" />			

				<tr>
        	<td class="title" >
        		<%if(operationMode != null && operationMode.equalsIgnoreCase("add")) { %>
	          	<strong>Add Role</strong>
	          <%} else {%>
	          	<strong>Role Details</strong>
	          <%}%>
           <br>
					</td>
					<td class="text" align="right">         
              <font size="1" color="red">*</font> Required        
          </td>
				</tr>
				
				<tr>
			 		<td colspan="2" align="center" bgcolor="8397B4" >
			 		  <font></font>
			 		</td>
			  </tr>
					
					
				<tr>
				  <td colspan="2">&nbsp;</td>
			 </tr>
							 
				<tr>
        	<td class="tddark" align="right">Role id</td>
          <td class="tdlight" >
              <% if(operationMode != null && operationMode.equalsIgnoreCase("add")) { %>
                   <html:text property="roleId" maxlength="20"/>
              <% } else { %>
                   <html:text property="roleId" readonly="true" maxlength="20"/>
              <% } %>
          </td>
        </tr>
        
        <tr>
        	<td class="tddark" align="right">Role name <font color="red">*</font></td>
          <td class="tdlight" ><html:text property="roleName" maxlength="25" /></td>
        </tr>
  
          <tr>
        	<td class="tddark" align="right">Provisioning</td>
          <td class="tdlight" >Workflow: <input type="text" size="30"></td>
        </tr>      
        <tr>
    	     <td>&nbsp;</td>
		    </tr>
		 
		    <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr>
               
        <tr >
	        <td align="right" colspan="2">
	        	<html:submit property="submit" value="Save"/>
          </td>
        </tr>
     	</table>
     
</html:form>
</body>
