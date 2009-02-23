<!-- list of all entitlements  -->

<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	System.out.println("entitlementslist.jsp.....");
  List entitlementList = (List) request.getAttribute("entitlementList");
  DynaValidatorForm entitlementForm = (DynaValidatorForm) request.getAttribute("entitlementForm");
  String mode = (String) entitlementForm.get("mode");
		
%>

<html:html locale = "true">
	<body>
		<table cellspacing=2 cellpadding=2 width="400" align="center">
		<html:form action = "/entitlement.do?method=removeEntitlement" >
		<html:hidden property="mode" value="<%=mode%>"/> 
			<tr>
    		<td align="center" class="tddark">	
					<strong>Entitlement Name</strong>
   			</td>

 	  		<td align="center" class="tddark">	
					<strong>Entitlement Value</strong>
   			</td>
			</tr>		
			
			<% if(entitlementList != null) {
			
						Iterator entitlementListItr = entitlementList.iterator();
						while(entitlementListItr.hasNext()) {
              EntitlementValue entitlementValue = (EntitlementValue) entitlementListItr.next();						
			%>
			
					<tr class="tdlight">
			      <td >
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="entitlementId" value="<%=entitlementValue.getEntitlementId()%>"/>
                  <a href="entitlement.do?method=editEntitlement&entitlementId=<%=entitlementValue.getEntitlementId()%>" class="link">
                  	<%=entitlementValue.getEntitlementName()%><br>
                  </a>
						</td>
						
						<td><%=entitlementValue.getEntitlementValue()%></td>
					</tr>
					
      	<%}%>
       
			  <tr class="tdlight">
			  	<td width="50%" align="center" colspan="2">
			    	<html:submit property="submit" value="Delete" />
			    </td>
			  </tr>
			 			 
			<%} else {%>
				<tr >
					<td colspan="2" class="tdlight" align="center">No Entitlements Found</td>
				</tr>
			<%}%>
			
			<tr>
				<td colspan="2" align="right" >
					<a href="entitlement.do?method=addEntitlement">New Entitlement</a>
				</td>
			</tr>
		
		</table>
	</html:form>  

	<table align="center" width="50%">
 		<% if(mode.equalsIgnoreCase("add") || mode.equalsIgnoreCase("edit")) { %>     
			<tr><td colspan="2">
    		<jsp:include page="entitlement.jsp">
    			<jsp:param name="mode" value="<%=mode%>" />
    		</jsp:include>
  		</td></tr>
 		<%}%>
	</table> 
	 
	</body>
</html:html>