<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% 
System.out.println("authquestion.jsp");
%>
<br> 
<form:form commandName="claimAccountCmd">

<table border="0" width="500" align="center">

  <tr>
    <td class="title" colspan="2">
        Account Claim Wizard - Select user
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
      REDID:<font color="red">*</font>
    </td>
    <td class="tdlight" ><form:input path="redid" size="40" maxlength="40" /><br>
        <form:errors path="redid" cssClass="error" />
    </td>
  </tr>

   <tr>
    <td class="tddark" align="right">
      Date of Birth(MM/DD/YYYY):<font color="red">*</font>
    </td>
    <td class="tdlight" ><form:input path="dob" size="40" maxlength="40" /><br>
        <form:errors path="dob" cssClass="error" />
     </td>
  </tr>

  <tr>
    <td class="tddark" align="right">
      Registration Code:<font color="red">*</font>
    </td>
    <td class="tdlight" ><form:input path="regid" size="40" maxlength="40" /><br>
        <form:errors path="regid" cssClass="error" />
     </td>
  </tr>


    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="2" align="right">

          <input type="submit" name="_target2" value="Next"/>
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>
</form:form>


