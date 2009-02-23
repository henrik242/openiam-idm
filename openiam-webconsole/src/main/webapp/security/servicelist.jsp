<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.ebc.user.*" %>

<style type="text/css">
.error {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #FF0000; text-decoration: none}
.link1 {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #111111; text-decoration: none}
.link2 {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #111111; text-decoration: none}
</style>


<table width="70%" border="0" cellspacing="2" cellpadding="1">
  <tr>
    <td  align="left" class="link2" bgcolor="#cccccc"><strong>Service Id</strong></td>
    <td  align="left" class="link2" bgcolor="#cccccc"><strong>Name</strong></td>
  </tr>
<%
 
Map serviceMap = (Map)request.getAttribute("servicelist");
String mode = (String)request.getAttribute("mode");


if (serviceMap != null) {
  Iterator it = serviceMap.keySet().iterator();
  while (it.hasNext()) {
    String key = (String)it.next();
    String name = (String)serviceMap.get(key);
%>
  <tr>
    <td align="left" bgcolor="#eeeeee"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=key%>
  <a href="service.do?mode=EDIT&id=<%=key%>">Edit</a> | <a href="service.do?mode=DEL&id=<%=key%>">Remove</a></font> </td>
    <td align="left" bgcolor="#eeeeee"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=name%></font></td>
  </tr>
<%
  }
}else {
%>
  <tr>
    <td colspan="7"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
No Services Found.</font>
   </td>
  </tr>
<%
}
%>
  <tr>
    <td colspan="2"><div align="center"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="service.do?mode=NEW">Add Service</a></font></div></td>
  </tr>
</table>

<% if ( mode != null) { %>
      <jsp:include page="addservice.jsp" flush="true" />

<% 
  }
%>
