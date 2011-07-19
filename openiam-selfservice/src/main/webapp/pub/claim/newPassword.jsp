<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% 
System.out.println("newPassword.jsp");
%>
<br> 
<form:form commandName="claimAccountCmd">

<table border="0" width="500" align="center">

  <tr>
    <td class="title" colspan="2">
        Account Claim Wizard - Set New Password
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
    <td class="tddarknormal" align="right">    
       New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
    	<form:password path="password" size="30"  maxlength="30"   /><br>
    	 <form:errors path="password" cssClass="error" />
       
    </td>
  </tr>
  <tr>
    <td class="tddarknormal" align="right">    
       Confirm New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
       <form:password path="confPassword" size="30"  maxlength="30"   /><br>
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
    <td colspan="2" align="right"> 
    	  <input type="submit" name="_target2" value="Previous"/>
    	  <input type="submit" name="_finish" value="Finish" tabindex="1" />   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>
</form:form>


