<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,org.apache.struts.validator.*, org.openiam.webadmin.busdel.base.*" %>

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
 
 	
  String personId = (String) request.getAttribute("personId");
  String status = (String)request.getAttribute("status");

  String msg = (String)request.getAttribute("msg");
  if (msg == null)
  	msg = " ";

 %>


  <input type="hidden" name="personId" value="<%=personId%>"/>
 
<table align="center" width="98%" border="0" cellspacing="1" cellpadding="1">
    <tr>
    	<td colspan="3"><font color="red"><%=msg%></font></td>
    </tr>

	<tr>
		<td class="tddark" align="center" colspan="3">Physical Access Badge Information</td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">Badge Status:</td>
		<td class="tdlight" width="20%"></td>
		<td class="tdlight" width="60%" rowspan="6"></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">
		<a href="idman/userIdentity.do?method=resetPswd&personId=<%=personId%>%>" OnClick="return confirmMsg('Are you sure you want to terminate this card');">Assign Badge</a>		
		</td>
		<td class="tdlight" width="20%"></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">
				<a href="idman/userIdentity.do?method=resetPswd&personId=<%=personId%>%>" OnClick="return confirmMsg('Are you sure you want to terminate this card');">Access Rights</a>		
		</td>
		<td class="tdlight" width="20%"></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">
				<a href="idman/userIdentity.do?method=resetPswd&personId=<%=personId%>%>" OnClick="return confirmMsg('Are you sure you want to change the status to Forgot Badge?');">Forgot Badge</a>		
		</td>
		<td class="tdlight" width="20%"></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">
				<a href="idman/userIdentity.do?method=resetPswd&personId=<%=personId%>%>" OnClick="return confirmMsg('Are you sure you want to suspend this badge');">Suspend Badge</a>		
		</td>
		<td class="tdlight" width="20%"></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">
		<a href="idman/userIdentity.do?method=resetPswd&personId=<%=personId%>%>" OnClick="return confirmMsg('Are you sure you want to terminate this badge');">Terminate Badge</a>	
		</td>
		<td class="tdlight" width="20%"></td>
	</tr>
</table>
<br>