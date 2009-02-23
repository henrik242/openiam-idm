<%@ page language="java"%>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.ebc.navigator.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%
	List userRptList = (List)request.getAttribute("userRptMenu");
	List auditRptList = (List)request.getAttribute("auditRptMenu");
	List passwordRptList = (List)request.getAttribute("passwordRptMenu");
	List provRptList = (List)request.getAttribute("provRptMenu");
	List accessRptList = (List)request.getAttribute("accessRptMenu");
	List otherRptList = (List)request.getAttribute("otherRptMenu");

	
%>
<table width="100%">
	<tr class="tdheader">
		<td colspan="2">User Information Reports</td>
	</tr>
	<%
		if (userRptList != null) {
			int size = userRptList.size();
			for (int i = 0; i< size; i++ ) {	
				MenuData md = (MenuData)userRptList.get(i);

	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getMainUrl()%>" ><%=md.getMenuName()%></a></td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>
	<tr class="tdheader">
		<td colspan="2">Audit Information Reports</td>
	</tr>
	<%
		if (auditRptList != null) {
			int size = auditRptList.size();
			for (int i = 0; i< size; i++ ) {	
				MenuData md = (MenuData)auditRptList.get(i);
	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getMainUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>
	<tr class="tdheader">
		<td colspan="2">Password Information Reports</td>
	</tr>
	<%
		if (passwordRptList != null) {
			int size = passwordRptList.size();
			for (int i = 0; i< size; i++ ) {	
				MenuData md = (MenuData)passwordRptList.get(i);
	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getMainUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>
	<tr class="tdheader">
		<td colspan="2">Provisioning Reports</td>
	</tr>
	<%
		if (provRptList != null) {
			int size = provRptList.size();
			for (int i = 0; i< size; i++ ) {	
				MenuData md = (MenuData)provRptList.get(i);
	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getMainUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>

	<tr class="tdheader">
		<td colspan="2">Access Management Reports</td>
	</tr>
	<%
		if (accessRptList != null) {
			int size = accessRptList.size();
			for (int i = 0; i< size; i++ ) {	
				MenuData md = (MenuData)accessRptList.get(i);
	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getMainUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>
	<tr class="tdheader">
		<td colspan="2">Misc Management Reports</td>
	</tr>
	<%
		if (otherRptList != null) {
			int size = otherRptList.size();
			for (int i = 0; i< size; i++ ) {	
				MenuData md = (MenuData)otherRptList.get(i);
	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getMainUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>

</table>
