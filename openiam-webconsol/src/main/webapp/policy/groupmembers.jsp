<!-- a list of policies -->
<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.policy.*,org.apache.struts.validator.DynaValidatorForm" %>
<%@ page import="org.openiam.idm.srvc.grp.dto.Group" %>
<%@ page import="org.openiam.webadmin.busdel.base.JSPUtil" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% 
String policyId = request.getParameter("policyId");
List<Group> groups = (List)request.getAttribute("groups");
pageContext.setAttribute("groups", groups);
List m = (List)request.getAttribute("members");

%>

<%@page import="org.openiam.webadmin.busdel.base.JSPUtil;"%>
<html:form action="/security/groupPolicy.do?method=groups">

 		<html:hidden property="policyId" value="<%= policyId %>"/>


<table cellspacing=2 cellpadding=2 width="600" align="center">


	<tr class="tdlight">
		<td>Please select a Group to add to Policy Members:</td>
		<td align="left"> 

      			<html:select property="groupId">
           		<html:options collection="groups" property="value" labelProperty="label"/>
     				</html:select> 
        </td>
	</tr>

</table>
	

<table cellspacing=2 cellpadding=2 width="600" align="center">

		<tr>

			<td colspan="5" class="title"><strong>Members</strong></td>
		</tr>


		<tr>
			<td colspan="5" align="center" bgcolor="8397B4"><font></font></td>
		</tr>

		<tr>
			<td class="tdheader" align="center" width="20%">Policy Member Id</td>
			<td class="tdheader" align="center" width="20%">Policy Id</td>
			<td class="tdheader" align="center" width="20%">Member Type</td>
			<td class="tdheader" align="center" width="20%">Object Id</td>
			<td class="tdheader" align="center">Description</td>
		</tr>

	<%

	if ((m != null) && (!m.isEmpty())) {
		int size = m.size();
		for (int i=0; i<size; i++) {
		
			 PolicyMemberValue member = (PolicyMemberValue)m.get(i);

	%>

		<% if ((i % 2) != 0) {	%>
		<tr class="tddarknormal">
		<%} else {				%>
		<tr class="tdlightnormal">
		<%}						%>
			<TD><input type="checkbox" name="policyMemberId" value="<%= member.getPolicyMemberId() %>" /> 
				<%=JSPUtil.display(member.getPolicyMemberId())%></TD>
			<TD align="center"><%=JSPUtil.display(member.getPolicyId())%></TD>
			<TD align="center"><%=JSPUtil.display(member.getObjectType())%></TD>
			<TD align="center"><%=JSPUtil.display(member.getObjectId())%></TD>
			<TD align="center"><%=JSPUtil.display(member.getServiceId())%></TD>
			
		</tr>
		<%
		}	
		%>


		<%} else {%>
		<tr>
			<td colspan="5" align="center">No Members have been added for this policy</td>
		</tr>
		<%}%>



		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>

		<tr>
			<td colspan="5" align="center" bgcolor="8397B4"><font></font></td>
		</tr>


		<tr>
			<td colspan="5" align="right">
			<html:submit property="submit" value="Add" />
			<html:submit property="submit" value="Delete" />		
			</td>
		</tr>

</table>

</html:form>

