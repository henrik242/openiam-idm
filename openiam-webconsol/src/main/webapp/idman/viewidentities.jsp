<%@ page language="java" %>
<%@ page import="java.util.*, org.openiam.webadmin.busdel.base.*,diamelle.security.auth.*,diamelle.ebc.user.*,java.text.*" %>

<SCRIPT LANGUAGE="JavaScript"> 
<!-- Begin 

function openWin(url, title, form) { 
     window.name="parentwindow";
     title="";
     win = window.open(url, title, 
          "scrollbars=no,title=no,status=no,width=290,height=240,resizable=no,top=250,left=322"); 
} 

function confirmMsg(msg) {
    return confirm(msg);  
}
//  End --> 

</script> 

<%
	DateFormat df = DateFormat.getDateInstance();
   String activeTab = (String) request.getAttribute("activetab");
   String updatedetail = (String) request.getAttribute("updatedetail");
   String personId = (String) request.getAttribute("personId");
   String msg = (String)request.getAttribute("msg");
    if (msg == null)
  	msg = " ";
%>
<br>
<table width="100%" border="0" cellspacing="1" cellpadding="1">
      <tr>
    	<td colspan="8"><font color="red"><%=msg%></font></td>
    </tr>
    

<%
 
  List loginList  = (List)request.getAttribute("detailView");
  String changedPassword = null;
  String passwordExp = null;
  if (loginList != null) {
   Iterator it = loginList.iterator();
   while (it.hasNext()) {
      LoginValue loginVal = (LoginValue)it.next();
      loginVal.getPasswordExp();
      loginVal.getChangePassword();
      if (loginVal.getChangePassword() == null) {
      	changedPassword = " ";
      }else {
      	changedPassword = loginVal.getChangePassword().toString();
      }
      if (loginVal.getPasswordExp() == null) {
      	passwordExp = " ";
      }else {
      	passwordExp = loginVal.getPasswordExp().toString();
      }
     %>
  <tr>
  	<td colspan="10" align="center">
  		<table width="75%" cellspacing=1 cellpadding=1>
 			<tr>
  				<td align="right" class="tddark" width="40%"><b>Identity:</b></td>
 				<td class="tdlight" width="40%"><%=loginVal.getLogin()%></td>
  				<td class="tdlight" width="20%"></td>
  			</tr>

  			<tr>
  				<td align="right" class="tddark" width="40%"><b>Security Domain:</b></td>
  				<td class="tdlight" width="40%"><%=loginVal.getService()%></td>
  				<td class="tdlight" width="20%">
  				<a href="javascript:openWin('idman/userIdentity.do?method=identityForm&personId=<%=personId%>&loginId=<%=loginVal.getLogin()%>&serviceId=<%=loginVal.getService()%>');">
             View Login
         			</a></td>
  			</tr>
  			<tr>
  				<td align="right" class="tddark" width="40%"><b>Password Expiration:</b></td>
  				<td class="tdlight" width="40%"><%=passwordExp%></td>
  				<td class="tdlight" width="20%">
  				<a href="idman/userIdentity.do?method=deleteIdentity&personId=<%=personId%>&login=<%=loginVal.getLogin()%>&serviceId=<%=loginVal.getService()%>" OnClick="return confirmMsg('Are you sure you want to delete this Login');" >Delete Login</a> 
  				</td>
  			</tr>
  			<tr>
  				<td align="right" class="tddark" width="40%"><b>Last Password Date Change:</b></td>
  				<td class="tdlight" width="40%"><%=changedPassword%></td>
  				<td class="tdlight" width="20%">
  			     <a href="idman/userIdentity.do?method=resetPswd&personId=<%=personId%>&loginId=<%=loginVal.getLogin()%>&serviceId=<%=loginVal.getService()%>" OnClick="return confirmMsg('Are you sure you want to reset this password');">Reset Password
         		</a>
  				</td>
  			</tr>
   			<tr>
  				<td align="right" class="tddark" width="40%"><b>Locked:</b></td>
  				<td class="tdlight" width="40%"><%=String.valueOf(loginVal.isLocked()).toUpperCase() %></td>
  				<td class="tdlight" width="20%">
  				<a href="idman/userIdentity.do?method=unlockPswd&personId=<%=personId%>&loginId=<%=loginVal.getLogin()%>&serviceId=<%=loginVal.getService()%>" OnClick="return confirmMsg('Are you sure you want to Unlock this login');">Unlock Login</a>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" class="tddark" width="40%"><b>First Time Login:</b></td>
  				<td class="tdlight" width="40%"><%= String.valueOf( loginVal.isResetPassword()).toUpperCase() %></td>
  				<td class="tdlight" width="20%"></td>
  			</tr>
  			<tr>
  				<td align="right" class="tddark" width="40%"><b>Identity Status:</b></td>
  				<td class="tdlight" width="40%"><%= JSPUtil.display( loginVal.getStatus() ) %></td>
  				<td class="tdlight" width="20%"></td>
  			</tr>

   			<tr>
  				<td align="right" class="tddark" width="40%"><b>Logged in From:</b></td>
  				<td class="tdlight" width="40%"> <%= JSPUtil.display( loginVal.getCurrentLoginHost() ) %> </td>
  				<td class="tdlight" width="20%"></td>
  			</tr>
   			<tr>
  				<td align="right" class="tddark" width="40%"><b>Auth Failure Count</b></td>
  				<td class="tdlight" width="40%"><%= loginVal.getAuthFailureCount() %></td>
  				<td class="tdlight" width="20%"></td>
  			</tr>
   			<tr>
  				<td align="right" class="tddark" width="40%"><b>Last Auth Attempt</b></td>
  				<td class="tdlight" width="40%"><%= JSPUtil.display( loginVal.getLastAuthAttempt() ) %></td>
  				<td class="tdlight" width="20%"></td>
  			</tr>
   			<tr>
  				<td align="right" class="tddark" width="40%"><b>Create Date</b></td>
  				<td class="tdlight" width="40%">
  					<% if (loginVal.getCreateDate() != null) { %>
	  					<%= JSPUtil.display( loginVal.getCreatedBy() ) %> on 
	  					<%= JSPUtil.display( loginVal.getCreateDate() ) %>
  					<% } %>
  				</td>
  				<td class="tdlight" width="20%"></td>
  			</tr>
  		</table>
  	</td>
   
  </tr>

<%}}%>

   <tr>
 		<td colspan="10" align="center" >
 		  &nbsp;
 		</td>
  </tr>
  <tr>
 		<td colspan="10" align="center" bgcolor="FFFFFF" >
 		  <font></font>
 		</td>
  </tr>
  
  <tr>
    <td colspan="10" align="right">
        <a href="javascript:openWin('idman/userIdentity.do?method=identityForm&personId=<%=personId%>', 'Identity', this.form);"/>
          Add Login
       </a>
    </td>
  </tr>
</table>

