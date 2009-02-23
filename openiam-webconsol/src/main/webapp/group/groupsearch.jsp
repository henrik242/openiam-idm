<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<html:form action = "/security/group.do?method=searchGroup" >

	<table cellspacing=2 cellpadding=2  width="400" align="center">
	  <tr>
        <td class="title" colspan="6">Filter Groups</td>
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
	         <td class="tddark" align="right">Group Name:</td>
	         <td class="tdlight">
	             <input name="groupName" type="text" size="30" maxlength="30" />
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
	          <td colspan="2" align ="right"  >
	              <input type="submit" name="Submit" value="Submit">&nbsp;
	              <input type="reset" name="Reset" value="Reset">
	          </td>
	   </tr>

	</table>

</html:form>