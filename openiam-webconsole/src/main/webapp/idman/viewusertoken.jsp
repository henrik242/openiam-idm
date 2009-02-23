<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*, org.openiam.webadmin.busdel.base.*,diamelle.base.composite.*,diamelle.security.token.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<SCRIPT LANGUAGE="JavaScript"> 
<!-- Begin 

function openWin(url, title, form) { 
     window.name="parentwindow";
     title="";
     win = window.open(url, title, 
          "scrollbars=no,title=no,status=no,width=350,height=220,resizable=yes,top=200,left=300"); 
} 

function confirmMsg(msg) {
    return confirm(msg);  
}
//  End --> 

</script> 

 <%
 	List hpList = (List)session.getAttribute("hpList");
	pageContext.setAttribute("hpList", hpList);	
	
  	TokenRequestValue val  = (TokenRequestValue)request.getAttribute("token");
  	TokenValue tokenVal = (TokenValue)request.getAttribute("tokenDetail");
	if (val == null) {
	  	val = new TokenRequestValue();
	}
	if (tokenVal == null) {
	  	tokenVal = new TokenValue();
	} 
 	
  String personId = (String) request.getAttribute("personId");
  String status = (String)request.getAttribute("status");

  String msg = (String)request.getAttribute("msg");
  if (msg == null)
  	msg = " ";

 %>

<form action="userToken.do" method="post">

  <input type="hidden" name="personId" value="<%=personId%>"/>
  <input type="hidden" name="requestId" value="<%=val.getRequestId()%>"/>

<table align="center" width="98%" border="0" cellspacing="1" cellpadding="1">
    <tr>
    	<td colspan="5"><font color="red"><%=msg%></font></td>
    </tr>

	<tr>
		<td class="tddark" align="center" colspan="2">Token Information</td>
		<td class="tddark" align="center" colspan="3">Token Request Information</td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">Token Serial Number:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display( val.getTokenSerialNbr())%></td>

		<td class="tddark" align="right" width="20%">HP Model:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(val.getHpModel())%></td>	
		<td class="tddark" width="20%">
		<a href="javascript:openWin('viewTokenRequest.do?method=view&personId=<%=personId%>', 'Token Request', this.form);"/>
		Request Token	
		</td>

	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">Activation Code:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(tokenVal.getActivationCode())%></td>
		<td class="tddark" align="right" width="20%">HP Serial Nbr:</td>
		<td class="tdlight" width="20%"> <%=JSPUtil.display(val.getHpSerialNbr())%> </td>
		<td class="tddark" width="20%"><a href="viewTokenRequest.do?method=assign&personId=<%=personId%>">Assign Token</a></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">Token Status:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(tokenVal.getStatus())%></td>
		<td class="tddark" align="right" width="20%">HP Phone Number:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(val.getPhoneNumber())%></td>
		<td class="tddark" width="20%"><a href="viewTokenRequest.do?method=syncToken&personId=<%=personId%>">Sync Token</a></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">Approval Date:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(tokenVal.getApproveDate())%></td>
		<td class="tddark" align="right" width="20%">Request Status:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(val.getRequestStatus())%></td>
		<td class="tddark" width="20%"><a href="viewTokenRequest.do?method=stopToken&personId=<%=personId%>">Stop Token</a></td>
	</tr>

	<tr>
		<td class="tddark" align="right" width="20%">Approved By:</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(tokenVal.getApprovedBy())%></td>
		<td class="tddark" align="right" width="20%">Request Date</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(val.getRequestDate())%></td>
		<td class="tddark" width="20%">
		<a href="viewTokenRequest.do?method=forgotToken&personId=<%=personId%>">Forgot Token</a></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%"></td>
		<td class="tdlight" width="20%"></td>
		<td class="tddark" align="right" width="20%">Token Assigned Date</td>
		<td class="tdlight" width="20%"><%=JSPUtil.display(val.getAssignDate())%></td>
		<td class="tddark" width="20%"></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%"></td>
		<td class="tdlight" width="20%"></td>
		<td class="tddark" align="right" width="20%">New Model?</td>
		<td class="tdlight" width="20%"><%=val.isNewModel()%></td>
		<td class="tddark" width="20%"></td>
	</tr>

	<tr>
		<td class="tddark" align="right" width="20%"></td>
		<td class="tdlight" width="20%"></td>
		<td class="tddark" align="right" width="20%">New Token Requested?</td>
		<td class="tdlight" width="20%"><%=val.isNewTokenRequest() %></td>
		<td class="tddark" width="20%"></td>	
	</tr>

</table>
</form>
