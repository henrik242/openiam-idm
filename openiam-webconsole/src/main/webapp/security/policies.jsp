<!-- a list of policies -->
<%@ page language="java" %>
<%@ page import="java.util.*,org.openiam.idm.srvc.policy.dto.Policy,javax.servlet.http.*,org.apache.struts.validator.DynaValidatorForm" %>
<%@ page import="org.openiam.webadmin.admin.JSPUtil" %>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>





<%
	//String mode = "";
	List policyList = (List) request.getAttribute("policies");
    List policyTypes = (List)request.getAttribute("policyTypes");
	pageContext.setAttribute("policyTypes", policyTypes);
	String msg = (String)request.getAttribute("msg");
	
	String policyDefId = (String)session.getAttribute("policyDefId");


%>

		<table  width="700pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Policy Management</h2>
						</td>
						</tr>
					</table>
			</td>
		<% if (msg != null) { %>
	<tr class="error" colspan="3">
		<td align="right" align="center"><%=msg %></td>
	</tr>
	<% } %>
	
			<tr>
				<td>
					
<html:form action="/security/policySearch.do?method=searchDelete">
	<table width="900pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SELECT POLICY TYPE</legend>


				<table class="fieldsetTable"  width="100%" >


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
</td>
</tr>
</table>

	
	<table width="900pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SEARCH RESULTS</legend>
	
		<table class="resourceTable" cellspacing="2" cellpadding="2" >	



		<tr class="header">
			<th>Policy Name</td>
			<th>Description</td>
			<th>Create Date</td>
			<th>Created By</td>
			<th></td>
		</tr>

	<%

	if ((policyList != null) && (!policyList.isEmpty())) {
		int size = policyList.size();
		for (int i=0; i<size; i++) {
			Policy policyValue = (Policy)policyList.get(i);

	%>

		<tr>

			<TD class="tableEntry"><input type="checkbox" name="policyId" value="<%=policyValue.getPolicyId()%>" /> 
				<%=JSPUtil.display(policyValue.getName())%></TD>
			<TD class="tableEntry"><%=JSPUtil.display(policyValue.getDescription())%></TD>
			<TD class="tableEntry"><%=JSPUtil.display(policyValue.getCreateDate())%></TD>
			<TD class="tableEntry"><%=JSPUtil.display(policyValue.getCreatedBy())%></TD>
			
			<% if (policyDefId !=null && policyDefId.equalsIgnoreCase("104")) { %>
				<TD class="tableEntry"><a href="attrPolicy.cnt?policyId=<%=policyValue.getPolicyId()%>">View</a></TD>
			<% } else {%>
				<TD class="tableEntry"><a href="policyDetail.cnt?policyId=<%=policyValue.getPolicyId()%>">View</a></TD>
			<% }  %>
			
		</tr>
		<%
			}
		%>


		<%} else {%>
		<tr>
			<td>No Policies Found</td>
		</tr>
		<%}%>




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

	</td>
 </tr>
</table>