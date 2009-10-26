<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% 
System.out.println("login.jsp");
%>
<br> 
<form:form commandName="loginCmd">

<table border="0" width="400" align="center">
	<tr>
		<td class="error" colspan="2">${message}</td>
	</tr>

  <tr>
    <td class="title">
        Login
    </td>
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

  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->
  <tr>
    <td class="tddark" align="right">
       Enter Login Id:<font color="red">*</font>
    </td>
    <td class="tdlight" ><form:input path="principal" size="40" maxlength="40" />
     <br><form:errors path="principal" cssClass="error" /></td>
  </tr>
  <tr>
    <td class="tddark" align="right">    
       Enter Password:<font color="red">*</font>
    </td>
    <td class="tdlight"><form:password path="password" size="40" maxlength="40" /><br>
    <form:errors path="password" cssClass="error" /></td>
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
    <td colspan="2" align="right"><input type="Submit" value="Login" /> <input type="Reset" />  </td>
  </tr>

</table>
</form:form>


