
<%
System.out.println("Main index.jsp called.");
RequestDispatcher rd = request.getRequestDispatcher("/login.cnt");
rd.forward(request,response);
%>

