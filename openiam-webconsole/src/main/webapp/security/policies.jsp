<!-- a list of policies -->
<%@ page language="java" %>
<%@ page import="java.util.*,org.openiam.webadmin.busdel.base.*,javax.servlet.http.*,diamelle.security.policy.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<style type="text/css">
.error {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 9pt;
	color: #FF0000;
	text-decoration: none
}

.link {
	color: #111111;
	font-family: Verdana;
	font-size: 8pt;
	text-decoration: none;
}

a:hover {
	color: #111111;
	font-family: verdana;
	font-size: 8pt;
	font-weight: normal;
	font-style: normal;
	line-height: normal;
	font-variant: normal;
	text-transform: none;
	background-color: #C9CDD2;
}
</style>

<SCRIPT LANGUAGE="JavaScript">
<!-- Begin

function openWin(url, title) {
		win = window.open(url, title, 
    			"scrollbars=yes,status=yes,width=700,height=300");
}

//  End -->

</script>

<%
	//String mode = "";
	List policyList = (List) request.getAttribute("policies");
    List policyTypes = (List)request.getAttribute("policyTypes");
	pageContext.setAttribute("policyTypes", policyTypes);
	String msg = (String)request.getAttribute("msg");

	//DynaValidatorForm policyForm =
	//	(DynaValidatorForm) request.getAttribute("policyForm");
	//if ( policyForm != null) {
//		mode = (String) policyForm.get("mode");
	//}

%>
<html:form action="/security/policySearch.do?method=searchDelete">
<table>

	<% if (msg != null) { %>
	<tr class="error" colspan="3">
		<td align="right" align="center"><%=msg %></td>
	</tr>
	<% } %>
	<tr class="tdlightnormal">
		<td align="right"></td>
		<td> 

      			<html:select property="policyDefId">
           		<html:options collection="policyTypes" property="value" labelProperty="label"/>
     				</html:select> 

        </td>
        <td><html:submit property="submit" value="Search" /></td>
	</tr>

</TABLE>
	

<table cellspacing=2 cellpadding=2 width="600" align="center">

		<tr>

			<td colspan="6" class="title"><strong>Policies</strong></td>
		</tr>


		<tr>
			<td colspan="6" align="center" bgcolor="8397B4"><font></font></td>
		</tr>

		<tr>
			<td class="tdheader" align="center">Policy Name</td>
			<td class="tdheader" align="center">Description</td>
			<td class="tdheader" align="center">Enabled</td>
			<td class="tdheader" align="center">Create Date</td>
			<td class="tdheader" align="center">Created By</td>
			<td class="tdheader"></td>
		</tr>

	<%

	if ((policyList != null) && (!policyList.isEmpty())) {
		int size = policyList.size();
		for (int i=0; i<size; i++) {
			PolicyValue policyValue = (PolicyValue)policyList.get(i);

	%>

		<% if ((i % 2) != 0) {	%>
		<tr class="tddarknormal">
		<%} else {				%>
		<tr class="tdlightnormal">
		<%}						%>
			<TD><input type="checkbox" name="policyId" value="<%=policyValue.getPolicyId()%>" /> 
				<%=JSPUtil.display(policyValue.getName())%></TD>
			<TD><%=JSPUtil.display(policyValue.getDescription())%></TD>
			<TD><%=JSPUtil.display( new Boolean(policyValue.isEnabled()))%></TD>
			<TD><%=JSPUtil.display(policyValue.getCreateDate())%></TD>
			<TD><%=JSPUtil.display(policyValue.getCreatedBy())%></TD>
			<TD><a href="security/policy.do?method=policy&policyId=<%=policyValue.getPolicyId()%>">view</TD>
			
		</tr>
		<%
		}	
		%>


		<%} else {%>
		<tr>
			<td colspan="6" class="plaintext">No Policies Found</td>
		</tr>
		<%}%>



		<tr>
			<td colspan="6">&nbsp;</td>
		</tr>

		<tr>
			<td colspan="6" align="center" bgcolor="8397B4"><font></font></td>
		</tr>


		<tr>
			<td align="left"><html:submit property="submit" value="Delete" /></td>
			<TD colspan="4"></TD>
			<td align="right">
	    		<a href="security/policy.do?method=newPolicy">New Policy</a>					
			</td>
		</tr>

</table>

</html:form>

