<%@ page import="javax.naming.*,java.util.*,diamelle.util.Log" %>
<%@ page language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="rptIncidentCmd">
<table border="0" width="600pt" align="center">

  <tr>
    <td class="title">
        Report Security Incident
    </td>
    <td class="text" align="right">         
              
    </td>

  </tr>
  
  <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td class="tddark">From:</td>
	 <td class="tdlight"> <form:input path="from" size="40" maxlength="40" readonly="true" /></td>
   </tr>

  
  <tr>
    <td class="tddark">Subject:
    </td>
    <td class="tdlight"><form:input path="subject" size="40" maxlength="40" /></td>
  </tr>
  
  <tr>
    <td class="tddark" >
       Message<font color=red>*</font>
    </td>
    <td class="tdlight">
    	<form:textarea path="message" rows="4" cols="60" />
    </td>

  </tr>


  
  
    <tr>
	    <td></td>
	    <td></td>

	</tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>

    </tr>

  <tr>

    <td align="right" colspan="2">
    	<input type="submit" value="Submit">
    </td>

  </tr>

</table>

</form:form>


