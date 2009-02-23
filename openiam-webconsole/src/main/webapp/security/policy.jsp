<!--  adding/updating new policy -->
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.policy.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

	<table width="600" align="center" border="0" cellspacing="0" cellpadding="0">

<%
System.out.println("policy.jsp....");


PolicyValue policy = (PolicyValue) request.getAttribute("policy");
String policyId = null;
if (policy != null) { 
	  policyId = policy.getPolicyId();

%>
		
		<tr class="title">
			<td align="left"><%= policy.getPolicyId() %> <%=policy.getName() %></td>
			<td align="right"><%=policy.getDescription() %></td>
		</tr>

<% 
} else {
%>
		<tr class="title">
			<td align="left">New Policy</td>
			<td align="right"></td>
		</tr>

<% 
}
%>



		<tr></tr>

		<tr>
			<td colspan="2" align="center" bgcolor="8397B4"><font></font></td>
		</tr>

	</table>
	<br>

	<jsp:include page="/policy/policytab.jsp" flush="true" >
				<jsp:param name="polId" value="<%=policyId%>" />
	</jsp:include>


