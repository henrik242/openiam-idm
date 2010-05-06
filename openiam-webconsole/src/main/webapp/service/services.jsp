<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%@ page import="java.util.*,diamelle.security.resource.*,org.openiam.webadmin.busdel.base.*" %>
<%@ page import="java.util.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>
<%@ page import="org.openiam.idm.srvc.service.service.*, org.openiam.idm.srvc.service.dto.*" %>

<%
   List services = (List)request.getAttribute("services");   
   DynaValidatorForm serviceForm = (DynaValidatorForm) request.getAttribute("serviceForm");
   String mode=null;

   if(serviceForm!=null) { 
   	mode=(String)serviceForm.get("mode");
   }
 
%>

		<table cellspacing=2 cellpadding=2 width="700" align="center">

		<html:form action="/maint/service.do?method=removeService">

			<tr class="title">
    			<td colspan="6">Services</td>
			</tr>	
			  <tr>
 				<td colspan="6" bgcolor="8397B4" >
 		  		<font></font>
 				</td>
  			</tr>	
  			<tr>
	  			<td colspan="6">&nbsp;</td>
 			</tr>	
			<tr class="tdheader">
	    		<td>Service ID</td>
	    		<td>Service Name</td>   		
	    		<td>Status</td>   		
	    		<td>Type</td>   		
	    		<td>Location</td>  
	    		<td>License</td> 		
			</tr>
      
      <%
       	if (services != null) {
			int size = services.size();
			for (int i = 0; i < size; i++) {	
				Service val = (Service)services.get(i);
  			
     			if ((i % 2) != 0) {
      %>

   <tr class="tddarknormal">
   <% } else {%>
   <tr class="tdlightnormal">
   <%}%>

          <td>
          <input type="checkbox" name="serviceId" value="<%=val.getServiceId()%>"/>
           <a href="maint/service.do?method=editService&serviceId=<%=val.getServiceId()%>" ><%=val.getServiceId()%></a>
          </td>
          
          <td><%=val.getServiceName()%></td>
          <td><%=JSPUtil.display(val.getStatus())%></td>
          <td><%=JSPUtil.display(val.getServiceType())%></td>
          <td><%=JSPUtil.display(val.getLocationIpAddress())%></td>
          <td><%=JSPUtil.display(val.getLicenseKey())%></td>


      </tr>
      <%
        }
      %>
       <tr>
    	  <td colspan="6">&nbsp;</td>
	   </tr>
			 
	    <tr>
 		   <td colspan="6" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
	    </tr>
      
      
      <tr>
      	<td><a href="maint/service.do?method=addService">New Service</a></td>
	  	<td align="right" colspan="6">
	     <html:submit property="submit" value="Delete" />
	     <input type="reset" name="Reset" value="Reset">
	    </td>
	  </tr>
	<%
		}
	%>

</table>

	
</html:form>  

	<%if (mode!=null){ 
		if(mode.equalsIgnoreCase("add") || mode.equalsIgnoreCase("edit")) { %>     
			<tr><td colspan="2">
    		<jsp:include page="service.jsp">
    			<jsp:param name="mode" value="<%=mode%>" />
    		</jsp:include>
  		</td></tr>
 		<%}%>
    
    <%}%>

