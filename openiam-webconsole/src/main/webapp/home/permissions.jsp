<!-- permissions -->


<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*, org.openiam.idm.srvc.menu.dto.Menu;" %>

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
      queryString = "userId=" + userId +  "&lg="+login;
    } else {
      queryString = "userId=" + userId + "&lg="+login;
    }
  }

  List menuList = (List) session.getAttribute("permissions");

  if (menuList != null && !menuList.isEmpty() ) {

  //System.out.println(menuList.toString());
  int size = menuList.size();
  int counter = 0;
  Iterator it = menuList.iterator();
%>
  <tr><td class="title">
<%

    while (it.hasNext()) {
      Menu md = (Menu)it.next();
      String url = md.getUrl();
      if (url != null) {
    	  // sas - 10242007 = l->level p->parent
   		String menuId = "&menuid=" + md.getId().getMenuId() + "&l=p";
        if (url.indexOf("?") == -1) {
           url = url + "?" + queryString + menuId;
          } else {    
           url = url + "&"  + queryString + menuId;
        }  
       }
      
%>
<a href="<%=url%>" class="menuBar"><%=md.getMenuName()%></a>         
<%
 
      }    
    }

    if (userId != null) { 
           
       %>
&nbsp;<a href="logout.do" class="menuBar">Logout</a>
            </font>
         </td>
      </tr>
      <% } %>

</table>


