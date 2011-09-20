
<!--Navigator Bar-->

<%@ page language="java" %>
<%@ page session="true" %>

<%@ page import="java.util.*, diamelle.ebc.navigator.*, diamelle.common.cat.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:base/>




<!-- Category Bar -->

<%
  NavigatorBar navBar = (NavigatorBar) session.getAttribute("navBar");
  NavigatorBarItem item = null;
  List navList = null;
  int size = 0;
  if (navBar != null) {
%>


<table width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr><td height="15" valign="middle">
    <font face="Verdana, Arial, Helvetica, sans-serif" size="1">
    <bean:message key="showcategories.heading"/>
    </font>
    <font face="Verdana, Arial, Helvetica, sans-serif" size="1">
<%
    navList = navBar.getNavigatorList();
    size = navList.size();
    for (int i = 0; i < size; i++) {
      if ( i > 0)
        out.print(" > ");
      item = (NavigatorBarItem) navList.get(i);
%>
  <a href="<%=item.getUrl()%>"><%=item.getMenuName()%></a>
<%
    }
%>
    </font>
    </td>
  </tr>
 </table>

<%
}
%>