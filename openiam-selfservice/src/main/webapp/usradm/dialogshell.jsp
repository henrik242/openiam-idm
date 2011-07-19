<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.User, org.openiam.webadmin.admin.JSPUtil" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<head>

<base target="_self" />

<link href="<%=request.getContextPath()%>/diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<body>

<iframe src ="selsupervisor.jsp" width="100%" height="600">
  <p>Your browser does not support iframes.</p>
</iframe>

</body>
