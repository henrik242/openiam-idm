<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% 
System.out.println("unlockuser.jsp");
%>
<br> 
<form:form commandName="unlockUserCmd">

<table border="0" width="500" align="center">

  <tr>
    <td class="title" colspan="2">
        Unlock Account / Forgot Password Wizard
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
    <td class="tddark" align="right">
       Enter Login Id:<font color="red">*</font>
    </td>
    <td class="tdlight" ><form:input path="principal" size="40" maxlength="40" />
     <br><form:errors path="principal" cssClass="error" /></td>
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
    	  <input type="submit" name="_target1" value="Next"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>
</form:form>


