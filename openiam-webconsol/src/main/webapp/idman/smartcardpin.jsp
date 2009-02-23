<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,org.apache.struts.validator.*, org.openiam.webadmin.busdel.base.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

  
<table align="center" width="98%" border="0" cellspacing="1" cellpadding="1">

	<tr>
		<td class="tddark" align="center" colspan="2">Insert SmartCard into Reader..</td>
	</tr>

	<tr>
		<td class="tddark" align="right" width="20%">Card PIN:</td>
		<td class="tdlight" width="80%"><input type="password" values=""></td>

	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">Confirm PIN:</td>
		<td class="tdlight" width="80%"><input type="password" values=""></td>

	</tr>
	<tr>
		<td class="tddark" align="right" width="20%"></td>
		<td class="tdlight" width="80%" align="center"><input type="submit" value="Submit"> <input type="reset"> </td>

	</tr>

</table>
<br>

 