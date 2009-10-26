
<%
System.out.println("index.jsp called.");
RequestDispatcher rd = request.getRequestDispatcher("/login.selfserve");
rd.forward(request,response);
%>

