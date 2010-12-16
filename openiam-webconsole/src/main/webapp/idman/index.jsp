
<%
System.out.println("Idman Index.jsp called.");
RequestDispatcher rd = request.getRequestDispatcher("/idman/index.do");
rd.forward(request,response);
%>

