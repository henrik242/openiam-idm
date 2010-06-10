<!--  menubar.jsp

NO ALTERATIONS REQUIRED ON THIS PAGE.
staticmenu.jsp must be adjusted for each app.

This jsp contains fixed menus for the section area below the header
This will vary with the web application
--><!-- if menuList is available use that. If coming from IndexAction, and userId
was available, the menuList could be extracted there ======================= --><!-- no menuList, so use the fixed menu ============================ --><!-- Dynamic Menus -->
 

<%@page language="java"%>
<%@page session="true"%>

<%@page import="java.util.*, org.openiam.idm.srvc.menu.dto.Menu, org.openiam.idm.srvc.user.dto.User,org.openiam.idm.srvc.user.dto.UserStatusEnum"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<html:base />
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

 <%

  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  String queryString = null;

  if (userId != null) {
	  // l->level t->top
      queryString = "userId=" + userId + "&lg="+login + "&l=t";

  }
  
  
  List menuList = (List) session.getAttribute("topLevelMenus");
  String personId = (String)request.getAttribute("personId");
  User personData = (User)request.getAttribute("personData");
  String currentMenu = (String)session.getAttribute("currentMenu");
  
  List<Menu> menuL3 = (List<Menu>)request.getAttribute("menuL3");
  
  if (currentMenu == null) {
  	currentMenu = "";
  }
  UserStatusEnum status = null;
  if (personData != null) {
  	status = personData.getStatus();
  }
  
  if (menuList != null && !menuList.isEmpty() ) {

  int size = menuList.size();
  int counter = 0;
  Iterator it = menuList.iterator();
    while (it.hasNext()) {
      Menu md = (Menu)it.next();
      String url = md.getUrl();
      if (url != null) {
        if (url.indexOf("?") == -1) {
           url = url + "?";
          } else {    
           url = url + "&";
        }  
       }
      
      
	  if (md.getMenuDesc().startsWith("Edit")  ) {
      	  if (personId != null && (status == null || !status.equals("DELETED") )) {
%>
	<A href="<%=url%>&amp;personId=<%=personId%><%=queryString%>&amp;menuid=<%=md.getId().getMenuId()%>"><%=md.getMenuDesc()%></A>
<%
	  	  }else {
%>
			<%=md.getMenuDesc()%>
<%
		  }
	  }else {
%>
		
    		<A href="<%=url%><%=queryString%>&amp;menuid=<%=md.getId().getMenuId()%>"><%=md.getMenuDesc()%></A>
<%
	 }
          if (size > ++counter) {
            out.print(" | ");
          }
      }
    } 
%>
 

    

 
<%
  Collection menus = (Collection) session.getAttribute("menus");
  if (menus != null) {
%>
<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%
    int size = menus.size();
    int counter = 0;
    Iterator it = menus.iterator();
    while (it.hasNext()) {
      Menu md = (Menu)it.next();
%>
      <a href="<%=md.getUrl()%>"><%=md.getMenuName()%></a>
<%
          if (size > ++counter) {
            out.print(" | ");
          }  
      }    
    }
%>

