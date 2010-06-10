<!-- a list of policies -->
<%@ page language="java" %>
<%@ page import="java.util.*,org.openiam.idm.srvc.policy.dto.Policy,javax.servlet.http.*,org.apache.struts.validator.DynaValidatorForm" %>
<%@ page import="org.openiam.webadmin.admin.JSPUtil" %>


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
	
	String policyDefId = (String)session.getAttribute("policyDefId");


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
	

<table cellspacing="2" cellpadding="2" width="600" align="center">

		<tr>

			<td colspan="6" class="title"><strong>Policies</strong></td>
		</tr>


		<tr>
			<td colspan="6" align="center" bgcolor="8397B4"><font></font></td>
		</tr>

		<tr>
			<td class="tdheader" align="center">Policy Name</td>
			<td class="tdheader" align="center">Description</td>
			<td class="tdheader" align="center">Create Date</td>
			<td class="tdheader" align="center">Created By</td>
			<td class="tdheader"></td>
		</tr>

	<%

	if ((policyList != null) && (!policyList.isEmpty())) {
		int size = policyList.size();
		for (int i=0; i<size; i++) {
			Policy policyValue = (Policy)policyList.get(i);

	%>

		<tr>

			<TD class="plaintext"><input type="checkbox" name="policyId" value="<%=policyValue.getPolicyId()%>" /> 
				<%=JSPUtil.display(policyValue.getName())%></TD>
			<TD class="plaintext"><%=JSPUtil.display(policyValue.getDescription())%></TD>
			<TD class="plaintext"><%=JSPUtil.display(policyValue.getCreateDate())%></TD>
			<TD class="plaintext"><%=JSPUtil.display(policyValue.getCreatedBy())%></TD>
			
			<% if (policyDefId !=null && policyDefId.equalsIgnoreCase("104")) { %>
				<TD class="plaintext"><a href="attrPolicy.cnt?policyId=<%=policyValue.getPolicyId()%>">View</a></TD>
			<% } else {%>
				<TD class="plaintext"><a href="policyDetail.cnt?policyId=<%=policyValue.getPolicyId()%>">View</a></TD>
			<% }  %>
			
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
			<% if (policyDefId !=null && policyDefId.equalsIgnoreCase("104")) { %>
				<a href="attrPolicy.cnt">New Policy</a>	
			<% }else { %>
	    		<a href="policyDetail.cnt?policyDefId=<%=(String)session.getAttribute("policyDefId")%>">New Policy</a>					
			<% } %>
			</td>
		</tr>

</table>

</html:form>

