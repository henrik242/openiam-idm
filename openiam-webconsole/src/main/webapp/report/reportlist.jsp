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
<table  width="800pt" >
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Reports</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>	
<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>AUDIT REPORTS</legend>
	
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
         <tr class="header">
			  	<th>Name</td>
          <th>Description</td>
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
	
	</table>
</td>
</tr>

</table>
