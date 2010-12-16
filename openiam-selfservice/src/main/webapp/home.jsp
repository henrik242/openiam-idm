
<!-- home -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%

  String bodypage = request.getParameter("bodyjsp");
  if (bodypage == null) {
    bodypage = (String)request.getAttribute("bodyjsp");
  }  
  
  if (bodypage == null) {
    bodypage = "/welcome.jsp";
  }  
  System.out.println("body page=" + bodypage);
  
    String userId = (String)session.getAttribute("userId");
  if (userId != null && userId.length() > 0 ) {
  	// active session. dont show the login page again
  	if (bodypage.contains("login.jsp")) {
  		RequestDispatcher rd = request.getRequestDispatcher("/welcomePage.selfserve");
			rd.forward(request,response);

  	}
  }
  
  String body = bodypage;
  
%>

<tiles:insert page='/homelayout.jsp' flush="true">
  <tiles:put name='permissions' value='/home/permissions.jsp' /> 
    <tiles:put name='sidemenu' 	value='/home/sidemenu.jsp' />
  <tiles:put name='category' value='/home/category.jsp' />
  <tiles:put name='menubar' value='/home/menubar.jsp' />
  <tiles:put name='navbar' value='/home/navbar.jsp' />
  <tiles:put name='body' value='<%=body%>' />
  <tiles:put name='rightsidemenu' 	value='/home/rightsidemenu.jsp' />
  <tiles:put name='footer' value='/home/footer.jsp' />
</tiles:insert>
