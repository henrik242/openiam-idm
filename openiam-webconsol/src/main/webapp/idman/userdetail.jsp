<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%

  Collection categoryList = null;
  String menuType = null;
  String bodypage = (String) request.getParameter("bodyjsp");


  if (bodypage == null)	{
    bodypage = "user.jsp";
  }
  String bodyJsp = bodypage;

  // show a navigator menu instead of category
   categoryList = (Collection) session.getAttribute("categories");
   menuType = (String)session.getAttribute("menuType");
   String personId = (String)request.getAttribute("personId");
   //userId = "1000";
   String userMode = (String)request.getAttribute("usermode");

   if (userMode == null)
   	userMode = new String();
%>

   <tiles:insert page='idman/template.jsp' flush='true'>

	<tiles:put name='header' value='home/header.jsp' />
	<tiles:put name='body' value='<%=bodyJsp%>' />
	<%
	if (userMode != null) {
	%>
		<tiles:put name='sidebar' value='/home/editbar.jsp' />
	<%
	}else {
    %>
        <tiles:put name='sidebar' value='sidebar.jsp' />
     <%
     }
     %>
        <tiles:put name='permissions' value='/home/permissions.jsp' />
        <tiles:put name='commonheader' value='/commonheader.jsp' />
   <% if (personId != null) {%>
        <tiles:put name='tab' value='/idman/detailtab.jsp' />
   <%}%>
        <tiles:put name='footer' value='/home/footer.jsp' />
   </tiles:insert>
