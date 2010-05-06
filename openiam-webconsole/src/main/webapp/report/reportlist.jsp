<%@ page language="java"%>
<%@ page import="java.util.*,javax.servlet.http.*,org.openiam.idm.srvc.menu.dto.Menu" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%
	List<Menu> userRptList = (List<Menu>)request.getAttribute("userRptMenu");
	List<Menu> auditRptList = (List<Menu>)request.getAttribute("auditRptMenu");
	List<Menu> passwordRptList = (List<Menu>)request.getAttribute("passwordRptMenu");
	List<Menu> provRptList = (List<Menu>)request.getAttribute("provRptMenu");
	List<Menu> accessRptList = (List<Menu>)request.getAttribute("accessRptMenu");
	List<Menu> otherRptList = (List<Menu>)request.getAttribute("otherRptMenu");

	
%>
<table width="100%">
	<tr class="tdheader">
		<td colspan="2">User Information Reports</td>
	</tr>
	<%
		if (userRptList != null) {
			int size = userRptList.size();
			for (int i = 0; i< size; i++ ) {	
				Menu md = (Menu)userRptList.get(i);

	%>
	<tr bgcolor="#F5F7F9">
		<td>
			<a href="<%=md.getUrl()%>" ><%=md.getMenuName()%></a></td>
		<td></td>
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
				Menu md = (Menu)auditRptList.get(i);
	%>
	<tr class="plaintext">
		<td>
			<a href="<%=md.getUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td></td>
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
				Menu md = (Menu)passwordRptList.get(i);
	%>
	<tr class="plaintext">
		<td>
			<a href="<%=md.getUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td></td>
	</tr>
	<%
			}
		}
	%>
<!-- 
	<tr class="tdheader">
		<td colspan="2">Provisioning Reports</td>
	</tr>
	<%
		if (provRptList != null) {
			int size = provRptList.size();
			for (int i = 0; i< size; i++ ) {	
				Menu md = (Menu)provRptList.get(i);
	%>
	<tr class="plaintext">
		<td>
			<a href="<%=md.getUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td></td>
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
				Menu md = (Menu)accessRptList.get(i);
	%>
	<tr class="plaintext">
		<td>
			<a href="<%=md.getUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
-->
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
				Menu md = (Menu)otherRptList.get(i);
	%>
	<tr class="plaintext">
		<td>
			<a href="<%=md.getUrl()%>"><%=md.getMenuName()%></a>
		</td>
		<td><%=md.getMenuDesc()%></td>
	</tr>
	<%
			}
		}
	%>

</table>
