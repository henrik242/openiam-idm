<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<% 
	String msg = (String)request.getAttribute("msg");
%>
<c:if test="${singlePswdChangeCmd.sysId == null}" >
<table border="0" width="600pt" align="center">
	<tr>
    	<td class="error">NO IDENTITY FOUND FOR RESOURCE. </td>
  	</tr>
</table>
</c:if>

<c:if test="${singlePswdChangeCmd.sysId != null}" >

<form:form commandName="singlePswdChangeCmd">
<table border="0" width="600pt" align="center">
	<tr>
	  <td>
 

	  </td>
	</tr>
  <tr>
    <td class="title">Change Password </td>
    <td class="text" align="right">         
        <font size="1" color="red">*</font> Required       
    </td>
  </tr>
  
  <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 	</tr>

	<tr>
	  <td class="error" colspan="2">
	  <% if (msg != null ) { %>
		<%=msg %>
	<% }  %>
	</td>
 	</tr>
 	
  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->

	<form:hidden path="domainId"    />
	<form:hidden path="userId"    />
	<form:hidden path="sysId"    />

  <tr>
    <td class="tddarknormal" align="right">
       Login
    </td>
    <td class="tdlight">
    	<form:input path="principal" size="50"  maxlength="50" readonly="true"   />
    </td>
  </tr>

  <tr>
    <td class="tddarknormal" align="right">    
       Current Password<font color=red>*</font>
    </td>
    <td class="tdlight">
    	<form:password path="currentPassword" size="50"  maxlength="50"   /><br>
    	 <form:errors path="currentPassword" cssClass="error" />
       
    </td>
  </tr>

  <tr>
    <td class="tddarknormal" align="right">    
       New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
    	<form:password path="password" size="50"  maxlength="50"   /><br>
    	 <form:errors path="password" cssClass="error" />
       
    </td>
  </tr>
  <tr>
    <td class="tddarknormal" align="right">    
       Confirm New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
       <form:password path="confPassword" size="50"  maxlength="50"   /><br>
        <form:errors path="confPassword" cssClass="error" />
    </td>
  </tr>  
  
  <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
  	<td>

  	</td>
    <td align="right">
    		<input type="submit" value="Save"> <input type="reset" >
    </td>
  </tr>

</table>
</form:form> 
</c:if>



