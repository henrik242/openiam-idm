<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="changeStatusCmd">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>Change User Status</strong>
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
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
			<font color="red"></font>
       </td>
    </tr>

    
   
	<tr>
 		<td class="plaintext"></td>
		<td class="plaintext"> 
		Are you sure you want to change the user status to ${changeStatusCmd.userStatus} for ${changeStatusCmd.firstName} ${changeStatusCmd.lastName} ?
	<form:hidden path="userId" /> 
	<form:hidden path="userStatus" /> 		

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
          <input type="submit" value="Submit"/>   
    </td>
  </tr>
  
</table>

</form:form>

