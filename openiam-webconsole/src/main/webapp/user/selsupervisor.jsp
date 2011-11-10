<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.User, org.openiam.webadmin.admin.JSPUtil" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<head>

<base target="_self" />

<link href="<%=request.getContextPath()%>/diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<% List<User> userList = (List<User>)session.getAttribute("supervisorList"); %>

<script type="text/javascript">
function selSupervisor(userId, name)
{

  var o = new Object();
  o.id = userId;
  o.name = name;
  window.returnValue = o; 
}
</script>

<body>

<form action="<%=request.getContextPath() %>/selectSupervisor.cnt" method="post" target="_self">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>Select User</strong>
      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
   <tr>
       <td class="plaintext" align="right">First Name</td>
       <td class="plaintext" ><input type="text" name="firstName" size="30"></td>

     	<td class="plaintext" align="right">Last Name</td>
       <td class="plaintext" ><input type="text" name="lastName" size="30"></td>
    </tr>

    
   
	<tr>
 		<td class="plaintext"></td>
		<td class="plaintext"> 


		</td>
		
	</tr>

    <tr>
    	  <td colspan="4">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="4" align="right"> 
          <input type="submit" value="Search"/>  <input type="submit" value="Close" onClick="window.close();"/>   
    </td>
  </tr>
  
</table>
</form>

		
	<% 
	if (userList != null) {
	%>	
 <table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="4" class="title">         
          <strong>Search Results</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

		<tr class="plaintext">

			<td>Name </td>
			<td>Department</td>
			<td></td>
		</tr>
	<%  if( userList != null) {	
		for (User user : userList) { 
			String lastName = user.getLastName();
			if (lastName == null) {
				lastName = " ";
			}else {
				lastName = lastName.replace("'"," ");
			}
	%>

		<tr class="plaintext">

			<td> <%=user.getFirstName() %> <%=user.getLastName() %></td>
			<td> <%=JSPUtil.display(user.getDeptCd())%> </td>
			<td> </td>
			<td>
			<a href="javascript:selSupervisor('<%=user.getUserId()%>','<%=user.getFirstName() %> <%=lastName %>' );window.close();">Select</a></td>
		</tr>
	<% 	}
	}else {
%>
		<tr class="plaintext">

			<td colspan="4">No records found</td>
		</tr>
<%
	}
%>
	</table>
<%
	} 
	%>
</body>
