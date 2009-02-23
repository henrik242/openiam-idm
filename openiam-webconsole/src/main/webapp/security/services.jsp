<!-- list of all entitlements  -->

<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	System.out.println("services.jsp.....");
  Map services = (Map) request.getAttribute("services");
  DynaValidatorForm serviceForm = (DynaValidatorForm) request.getAttribute("serviceForm");
  String mode = (String) serviceForm.get("mode");
		
%>

	<body>
		<table cellspacing=2 cellpadding=2 width="50%" align="center">
		<html:form action = "/service.do?method=removeService" >
		<html:hidden property="mode" value="<%=mode%>"/> 
			<tr>
    		<td colspan="2" class="title" >	
					<strong>Services</strong>
   			</td>
			</tr>	
			
			<tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr> 
		      
		    <tr>
		 		   <td>&nbsp;</td>
		    </tr> 
		    
			
			
			<% if(services != null) {
			      int x=0;
						Iterator serviceKeyItr = services.keySet().iterator();
						Iterator serviceValItr = services.values().iterator();
						while(serviceKeyItr.hasNext()) {
						   String serviceId = (String) serviceKeyItr.next();						
               String serviceName = (String) serviceValItr.next();						
              
			%>
			
					<%
					     if ((x%2) != 0) {
					       
					   %>
					   <tr class="tddark">
					   <% } else {%>
					   <tr class="tdlight">
					   <%}%>
			      <td colspan="2">
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="serviceId" value="<%=serviceId%>"/>
                  <a href="service.do?method=editService&serviceId=<%=serviceId%>">
                  	<%=serviceName%><br>
                  </a>
						</td>
						
						
					</tr>
					
      	<%
      	  x++;
      	}%>
       
			  
			 			 
			<%} else {%>
				<tr >
					<td colspan="2" class="tdlight">No Services Found</td>
				</tr>
			<%}%>
			
			
			<tr>
		    	  <td>&nbsp;</td>
		    </tr>
		 
		    <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr>   
			
			<tr>
				<td>
  					<a href="service.do?method=addService">New Service</a>
  		  </td>
			  <td align="right"  >
			    	<html:submit property="submit" value="Delete" />
				</td>
			</tr>
		
		</table>
	</html:form>  

	<table align="center" width="50%">
 		<% if(mode.equalsIgnoreCase("add") || mode.equalsIgnoreCase("edit")) { %>     
			<tr><td colspan="2">
    		<jsp:include page="service.jsp">
    			<jsp:param name="mode" value="<%=mode%>" />
    		</jsp:include>
  		</td></tr>
 		<%}%>
	</table> 
	 
	</body>
