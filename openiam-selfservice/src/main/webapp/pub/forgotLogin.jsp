<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 




<form:form commandName="forgotLoginCmd">
<table border="0" width="600pt" align="center">
	<tr>
	  <td>
 

	  </td>
	</tr>
  <tr>
    <td class="title">Forgot Login</td>
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
    <td class="tddarknormal" align="right">Name (First-Last)</td>
    <td class="tdlight">
    	<form:input path="firstName" size="30"  maxlength="30" /> <form:input path="lastName" size="30"  maxlength="30" /><br>
    	 <form:errors path="firstName" cssClass="error" /> <form:errors path="lastName" cssClass="error" />
    </td>
  </tr>  
  <tr>
    <td class="tddarknormal" align="right">Phone Number</td>
    <td class="tdlight">
    	<form:input path="phone" size="30"  maxlength="30" /><br>
    	 <form:errors path="phone" cssClass="error" />
     </td>
  </tr>  
  <tr>
    <td class="tddarknormal" align="right">Email Address</td>
    <td class="tdlight">
    	<form:input path="email" size="60"  maxlength="60" /><br>
    	 <form:errors path="email" cssClass="error" />
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




