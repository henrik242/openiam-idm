<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,org.apache.struts.validator.*, org.openiam.webadmin.busdel.base.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

  
<table align="center" width="98%" border="0" cellspacing="1" cellpadding="1">

	<tr>
		<td class="tddark" align="center" colspan="4">Card Certificates</td>
	</tr>
	<tr>
		<td class="tddark" align="center">Issued To</td>
		<td class="tddark" align="center">Issued By</td>
		<td class="tddark" align="center">Expiration Date</td>
		<td class="tddark" align="center"></td>
	</tr>

	<tr>
		<td class="tdlight" align="right"></td>
		<td class="tdlight"></td>
		<td class="tdlight"></td>
		<td class="tdlight">
			<a href="" OnClick="return confirmMsg('Are you sure you want to delete this certificate');">Delete</a>
		</td>
	</tr>

</table>
<br>

 