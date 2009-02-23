<!--  adding/updating entitlement-->

<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
	String operationMode = request.getParameter("mode");
%>


	<body>
		<br><br>
		<html:form action = "serviceValidate.do?method=saveService">
		
			<table cellspacing=2 cellpadding=2  width="100%" align="center">
				<html:hidden property="mode" value="<%=operationMode%>"/> 
				<tr >
        	<td class="title" colspan="2"><strong>
						<%if(operationMode.equalsIgnoreCase("add")) { %>
							Add Service
						<% } else { %>
							Edit Service
						<%}%>
        	</strong></td>
				</tr>
				
				<tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 	</tr>
  		  				
			<tr>
        	  <td class="tddark" align="right">Service Id</td>
	          <td class="tdlight">
	           <%if(operationMode.equalsIgnoreCase("add")) { %>
	                <html:text property="serviceId" maxlength="20"/>
	            <% } else { %>  
	                 <html:text property="serviceId" maxlength="20" readonly="true" />
	            <%}%>
	          </td>
	        </tr>

				<tr>
        	<td class="tddark" align="right">Service Name<font color="red">*</font></td>
          <td class="tdlight"><html:text property="serviceName" maxlength="40"/></td>
        </tr>
        
				<tr>
		    	  <td>&nbsp;</td>
		    </tr>
		 
		    <tr>
		 		   <td colspan="4" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr>
                       
        <tr>
	        <td align="right" colspan="2">
	        	<html:submit property="submit" value="Save"/>
          </td>
        </tr>

     	</table>
     
		</html:form>
	</body>


