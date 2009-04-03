
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,diamelle.ebc.navigator.*,org.openiam.idm.srvc.user.dto.User" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<%

  String sideMenuGroup = (String)request.getAttribute("menuGroup");

  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  String queryString = null;

  if (userId != null) {
      queryString = "userId=" + userId + "&token=" + token + "&lg="+login;

  }
  
  User personData = (User)request.getAttribute("personData");
  List menuList = (List) session.getAttribute("sideMenus");
  String personId = (String)request.getAttribute("personId");

  String status = null;
  if (personData != null) {
  	status = personData.getStatus();
  }
  
  if (menuList != null && !menuList.isEmpty() ) {

  int size = menuList.size();
  int counter = 0;
  if (size > 0 ) {
%>
<table cellspacing = "1" cellpadding="1">
  
<%  
  Iterator it = menuList.iterator();
    while (it.hasNext()) {
      MenuData md = (MenuData)it.next();
      String url = md.getMainUrl();
      if (url != null) {
        if (url.indexOf("?") == -1) {
           url = url + "?";
          } else {    
           url = url + "&";
        }  
       }
      
      
	  if (md.getMenuId().startsWith("EDIT") || md.getMenuId().startsWith("DEL")  ) {
      	  if (personId != null && (status == null || !status.equals("DELETED") )) {
%>
	<tr>
		<td class="link">
	<A href="<%=url%>personId=<%=personId%>&amp;<%=queryString%>&amp;menuid=<%=md.getMenuId()%>"><%=md.getMenuDesc()%></A>
		</td>
	</tr>
<%
	  	  }else {
%>
	<tr>
		<td class="normaltext">
			<%=md.getMenuDesc()%>
		</td>
	</tr>
<%
		  }
	  }else {
%>
	<tr>
		<td class="link">
    		<A href="<%=url%><%=queryString%>&amp;menuid=<%=md.getMenuId()%>"><%=md.getMenuDesc()%></A>
		</td>
	</tr>
<%
	 }
      }
    } 
%>

</table>
<%

  }
%>

<!-- Side Menus - Need to make them dynamic  -->
<% if ( sideMenuGroup!= null && sideMenuGroup.equalsIgnoreCase("MANAGESYS")) { 
	 String connectorId = (String)request.getAttribute("connectorId");
%>
<div id="menu12">
 <ul>
    <!-- CSS Tabs -->
	<li><a href="managedSysConnection.cnt?connectorId=<%=connectorId%>&menuGroup=MANAGESYS">Connection</a></li>
	<li><a href="managedSysAttrMap.cnt?connectorId=<%=connectorId%>&menuGroup=MANAGESYS"">Mapping</a></li>
	<li><a href="managedSysApprover.cnt?connectorId=<%=connectorId%>&menuGroup=MANAGESYS">Approvers</a></li>
 </ul>
</div>
<% } %>

 


