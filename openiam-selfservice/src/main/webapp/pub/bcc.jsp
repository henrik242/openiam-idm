<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,diamelle.security.idquest.*" %>
<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
String message = (String)request.getAttribute("message");
%>

<html:form action="/unLockUser.do?method=processBCCAnswers">
<table border="0" width="80%" align="center">
	<tr>
		<td colspan="2">
			<% if (message !=null && message.length() > 0) { %>
				<font color="red"><%=message %></font>
			<% } %>
		</td>
	</tr>

   <tr>
    <td class="title" colspan="2">
        Un-Lock Account
    </td>
  </tr> 
  <tr>
 		<td align="center" bgcolor="8397B4" colspan="2">
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td colspan="2">&nbsp;</td>
 	</tr>

		  <tr>
				<td class="tddarknormal" align="right">SSN (Last 4 Digits):</td>
		  		<td class="tdlightnormal"><input type="text" name="ssn" size="30">
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Date of Birth(MMDDYYYY)</td>
		  		<td class="tdlightnormal"><input type="text" name="dateOfBirth" size="30">
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Employee Id:</td>
		  		<td class="tdlightnormal"><input type="text" name="employeeId" size="30">
				</td>
		   </tr>
 
 	
  <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td align="center" bgcolor="8397B4" colspan="2">
 		    <font></font>
 		   </td>
    </tr>
  <tr>
    <td align="left">
    </td>

    <td align="right">
       	<html:submit property="submit" value="Next"/>  &nbsp;<html:reset/>
    </td>
  </tr>

</table>
</html:form>




