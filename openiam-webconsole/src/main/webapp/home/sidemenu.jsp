
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,org.openiam.idm.srvc.menu.dto.Menu,org.openiam.idm.srvc.user.dto.User, org.openiam.idm.srvc.user.dto.UserStatusEnum" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%

  String sideMenuGroup = (String)request.getAttribute("menuGroup");
  List<Menu> menuL3 = (List<Menu>)request.getAttribute("menuL3");
  String objId = (String)request.getAttribute("objId");
  
  
  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  String queryString = null;

  if (userId != null) {
      queryString = "userId=" + userId +  "&lg="+login;

  }
  
  User personData = (User)request.getAttribute("personData");
  List<Menu> menuList = (List<Menu>) session.getAttribute("sideMenus");
  String personId = (String)request.getAttribute("personId");
  
  String roleId = (String)request.getAttribute("roleid");
  String domainId = (String)request.getAttribute("domainid");
  

  UserStatusEnum status = null;
  if (personData != null) {
  	status = personData.getStatus();
  }
  
%>




<% if ( sideMenuGroup!= null && sideMenuGroup.equalsIgnoreCase("METADATA")) { 
	 String typeId = (String)request.getAttribute("typeId");
%>
<div id="menu12">
 <ul>
    <!-- CSS Tabs -->
	<li><a href="metadataType.cnt?typeId=<%=typeId%>&menuGroup=METADATA">Type</a></li>
	<li><a href="metadataAttribute.cnt?typeId=<%=typeId%>&menuGroup=METADATA">Attributes</a></li>

 </ul>
</div>
<% } %>
<% if ( menuList != null ) { 
%>
<div id="menu12">

 <% for (Menu menu : menuList) { 
	 	String url = null;
	 	if ( menu.getUrl().contains("?") ) {
 			url = menu.getUrl() + "&" + queryString + "&menuid=" + menu.getId().getMenuId();
 		}else {
 			url = menu.getUrl() + "?" + queryString + "&menuid=" + menu.getId().getMenuId();
	 	}
	 	if (personId != null) {
	 		url = url + "&personId=" + personId;
	 	}
 %>
    <!-- CSS Tabs -->
	<a href="<%=url%>"><%=menu.getMenuName() %></a><br>
  <% } %>
  

</div>
<% } %> 
<% 
	if ( menuL3 != null ) { 
%>

<div id="menu12">

 <% for (Menu menu : menuL3) { 
	 	String url = null;
	 	if ( menu.getUrl().contains("?") ) {
 			url = menu.getUrl() + "&" +  "menuid=" + menu.getId() + "&menugrp=" + menu.getMenuGroup() + "&objId=" + objId;
 		}else {
 			url = menu.getUrl() + "?" +  "menuid=" + menu.getId().getMenuId() + "&menugrp=" + menu.getMenuGroup() + "&objId=" + objId;
	 	}
	 	if (personId != null) {
	 		url = url + "&personId=" + personId;
	 	}
	 	
 %>
    <!-- CSS Tabs -->
	<a href="<%=url%>"><%=menu.getMenuName() %></a><br>
  <% } %>
  

</div>
<%
	}
%>




