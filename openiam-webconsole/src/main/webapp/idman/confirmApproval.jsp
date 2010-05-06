<%@ page import="java.util.*,javax.servlet.http.*" %>
 
<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
 
<%
	Integer count = (Integer)request.getAttribute("COUNT");
	String task = (String)request.getAttribute("OPERATION");
%>
		
<table width="75%" border="0" cellspacing="1" cellpadding="1">
<tr>
	<td colspan="1" class="tdheader">Approval Process Completed</td>
</tr>
<tr>
	<td colspan="1" class="tdlight">
		<%=count%> User(s) have been <%=task%>
	</td>
</tr>
</table>

