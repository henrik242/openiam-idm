
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,diamelle.security.resource.*,diamelle.security.auth.*" %>
<%@ page import="org.apache.struts.action.DynaActionForm" %>

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
		List resourceTypes = (List)request.getAttribute("resourceTypes");
		DynaActionForm resourceTypeForm = (DynaActionForm) request.getAttribute("resourceTypeForm");
		String mode = (String) resourceTypeForm.get("mode");
%>

	<body>
		<html:form action="security/resourcetype.do?method=removeResourceTypes">

		<table cellspacing=2 cellpadding=2 width=400 align="center">
		<html:hidden property="mode" value="<%=mode%>" />
			<tr>
    		<td colspan = "2" class="title">Resource Type</td>
			</tr>			
 		   <tr>
		 		<td colspan="3" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr>
				
				
		  <tr>
			  <td>&nbsp;</td>
		 </tr>
 
      <tr class="tddark">
          <td  align="center">Resource Type</td>			
          <td  align="center">Resource ID</td>
     
       </tr>

    
      <% if (resourceTypes != null) {
           Iterator it = resourceTypes.iterator();
           int x=0;
           while (it.hasNext()) {
              ResourceTypeValue val = (ResourceTypeValue) it.next();
              String resourceTypeId = val.getResourceTypeId();
              String description = val.getDescription();
         	
		     if ((x%2) != 0) {				       
		   %>
		   <tr class="tddark">
		   <% } else {%>
		   <tr class="tdlight">
		   <%}%>
					   
          <td>
          <input type="checkbox" name="resourceTypeId" value="<%=resourceTypeId%>"/>
          <a href="security/resourcetype.do?method=editResourceTypes&resourceTypeId=<%=resourceTypeId%>">
            <%=description%>
           </a>
         </td>
     
 			
          <td><%=resourceTypeId%></td>
     
       </tr>
      <%
      	x++;
        }
      %>
  
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>    
   
    
    
     <%}%>
      <tr>
            <td align="left" >
      			<a href="security/resourcetype.do?method=addResourceTypes">New Resource Type</a>
      		</td>
			  	<td align="right">
			     <html:submit property="submit" value="Delete Item" />
			    </td>
	</tr>


</table>
	

</html:form>  
<table align="center" width="300">
		<% if(mode.equalsIgnoreCase("add") || mode.equalsIgnoreCase("edit")) { %>     
			<tr><td colspan="4">
    		<jsp:include page="resourcetype.jsp">
    			<jsp:param name="mode" value="<%=mode%>" />
    		</jsp:include>
  		</td></tr>
 		<%}%>

 
</table>
</body>
