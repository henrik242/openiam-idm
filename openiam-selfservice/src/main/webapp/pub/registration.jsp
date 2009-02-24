<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*" %>
<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<html:form action="/registration.do?method=save">
<table border="0" width="70%" align="center">

	<tr>
		<td class="title" align="center" colspan="2">Registration for Non-Orange County Employee</td>
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
		<td class="tddark" align="right">First Name:</td>
		<td class="tdlight">
			<html:text property="firstName" size="30" maxlength="30"/>

		</td>
  </tr> 
  <tr>
    <td class="tddark" align="right">
       Last Name:<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="lastName" size="30" maxlength="30"/>
    </td>
  </tr>
  <tr>
    <td class="tddark" align="right">
       E-Mail Address:<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="email" size="30" maxlength="30"/>
    </td>
  </tr>

  <tr>
    <td class="tddark" align="right">
       Phone:<font color=red>*</font>
    </td>
    <td class="tdlight">
      (<html:text property="areaCode" size="3" maxlength="3"/>)<html:text property="phone" size="8" maxlength="8"/> - Ext. - <html:text property="phoneExt" size="8" maxlength="8"/> 
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
    <td align="left">
    </td>

    <td align="right">
       	<html:submit property="submit" value="Save"/>  &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>


