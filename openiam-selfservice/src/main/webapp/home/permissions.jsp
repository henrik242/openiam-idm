<!-- permissions -->


<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*, diamelle.common.cat.*,diamelle.ebc.navigator.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<table>
<%

  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  String queryString = null;

  if (userId != null) {
    if (token != null) {
      queryString = "userId=" + userId + "&token=" + token + "&lg="+login;
    } else {
      queryString = "userId=" + userId + "&lg="+login;
    }
  }

  List menuList = (List) session.getAttribute("permissions");

  if (menuList != null && !menuList.isEmpty() ) {
  	int size = menuList.size();
  	int counter = 0;
  	Iterator it = menuList.iterator();
%>
  <tr>
  	<td class="bodytext">
<%

    while (it.hasNext()) {
      MenuData md = (MenuData)it.next();
      String url = md.getMainUrl();
      if (url != null) {
        if (url.indexOf("?") == -1) {
           url = url + "?" + queryString + "&menuid=" + md.getMenuId() + "&l=p";
        } else {    
           url = url + "&"  + queryString + "&menuid=" + md.getMenuId() + "&l=p";
        }  
       }
      
%>

        <a href="<%=url%>"><%=md.getMenuName()%></a>          

<%
          if (size >= ++counter) {
            out.print(" | ");
          }
		 
      }
%>
<a href="logout.do"> Logout</a>
<%
   }    
 %>     

            
  
        </td>
      </tr>
      
</table>


