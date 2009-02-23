<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.*" %>

<HTML>
<HEAD>
<TITLE>
Diamelle Technologies - Identity Manager Console
</TITLE>
</HEAD>
<BODY>
<H1>
<%
try {
RequestDispatcher rd = request.getRequestDispatcher("/security/index.do");
rd.forward(request,response);
} catch (Exception e) {
  e.printStackTrace();
}
%>
</H1>
</BODY>
</HTML>
