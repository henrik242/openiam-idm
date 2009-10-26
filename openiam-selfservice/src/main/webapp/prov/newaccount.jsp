<%@ page import="java.util.*,javax.servlet.http.*" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%
	List departmentList = (List)session.getAttribute("departmentList");

	pageContext.setAttribute("departmentList", departmentList);

	List divList = (List)session.getAttribute("divisionList");

	pageContext.setAttribute("divisionList", divList);
%>
<html:form action = "/priv/newAccountReq.do?method=saveRequest">
<table border="0" width="100%" align="center">


    <td class="title" colspan="2">
       New User Account Request
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
				<td class="tddarknormal" align="center" colspan="2">All fields are required</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Full Name of New Users Supervisor</td>
		  		<td class="tdlightnormal">
					<input type="text" name=firstName size="30"> <input type="text" name=lastName size="30">
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Full Name of New User</td>
		  		<td class="tdlightnormal">
					<input type="text" name=firstName size="30"> <input type="text" name=lastName size="30">
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Affiliation</td>
		  		<td class="tdlightnormal">
					<select>
						<option></option>
					</select>
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Company</td>
		  		<td class="tdlightnormal">
					<select>
						<option></option>
					</select>
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Division</td>
		  		<td class="tdlightnormal">
					<html:select property="division" >
			        	<html:options collection="divisionList" property="value" labelProperty="label"/>
			     	</html:select> 
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Department</td>
		  		<td class="tdlightnormal">
					<html:select property="department" >
			        	<html:options collection="departmentList" property="value" labelProperty="label"/>
			     	</html:select> 
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Does the user an e-mail account</td>
		  		<td class="tdlightnormal">
					<input type="radio">Yes <input type="radio">No
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Does the user need remote access to mail?</td>
		  		<td class="tdlightnormal">
					<input type="radio">Yes <input type="radio">No
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Does the user need remote access to files/applications?</td>
		  		<td class="tdlightnormal">
					<input type="radio">Yes <input type="radio">No
				</td>
		   </tr>

		  <tr>
				<td class="tddarknormal" align="right">User Name of an existing user to model:</td>
		  		<td class="tdlightnormal">
					<input type="text" name="userName" size="30">
				</td>
		   </tr>

		  <tr>
				<td class="tddarknormal" align="right">Request Justification</td>
		  		<td class="tdlightnormal">
					<textarea rows=3 cols=60>
					</textarea>
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
		<input type="submit" value="Submit" /> <input type="reset" />
    </td>
  </tr>

</table>
</html:form>
