
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
</HEAD>
<%
	String retUrl = (String)session.getAttribute("retTargetUrl");
	String lg = (String) session.getAttribute("login");
	String userId = (String) session.getAttribute("userId");
	String token = (String) session.getAttribute("token");
%>

<BODY>
<P>Information has been successfully saved.<br>
  		<% if (retUrl != null) {%>
  		<a href="<%=retUrl%>?userId=<%= userId %>&lg=<%=lg %>&token=<%=token %>"> Return to primary application</a>
  		<% }%>
</P>

</BODY>
</html:html>
