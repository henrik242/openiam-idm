<%@ page language="java" %>
<%@ page session="true" %>
<%@page import="java.util.*, org.openiam.idm.srvc.menu.dto.Menu, org.openiam.idm.srvc.user.dto.User,org.openiam.idm.srvc.user.dto.UserStatusEnum"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%  
	String bpmToken = (String)request.getSession().getAttribute("identityKey");
	String rMenu = (String)request.getAttribute("hideRMenu");  
	if (rMenu == null ||  !rMenu.equals("1")) {

%>	

<table border="0" width="180">
<%
System.out.println("menubar.jsp");
  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  
  List<Menu> privLeftMenuList = (List<Menu>)session.getAttribute("privateLeftMenuGroup");
  List<Menu> privRightMenuList1 = (List<Menu>)session.getAttribute("privateRightMenuGroup1");
  List<Menu> privRightMenuList2 = (List<Menu>)session.getAttribute("privateRightMenuGroup2");
  List<Menu> privRightMenuList3 = (List<Menu>)session.getAttribute("privateRightMenuGroup3");	

  
  String queryString = null;

  if (userId != null) {
    if (token != null) {
      queryString = "userId=" + userId + "&lg="+login + "&identityKey=" + bpmToken;
    } else {
      queryString = "userId=" + userId + "&lg="+login + "&identityKey=" + bpmToken;
    }
  }


%>
	<tr>
		<td><B>Access Management Center</B></td>
	</tr>
	
	<% if (userId != null && privRightMenuList1 != null ) { 
			for (Menu m: privRightMenuList1) {
				if (m.getSelected()) {
					String url = m.getUrl();
				     if (url != null) {
				         if (url.indexOf("?") == -1) {
				            url = url + "?" + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         } else {    
				            url = url + "&"  + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         }  
				      }

	%>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;<a href="<%=url %>"><%=m.getMenuName() %></a></td>
  </tr>
  	<% } else 	{ %>	
  <tr>
    <td class="normaltext">&nbsp;&nbsp;&nbsp;<%=m.getMenuName() %></td>
  </tr>	
	<%			}
			} 
		} 
	%>
  <tr>
  	<td><br><b>Self-Service Center</b></td>
	</tr>
	<% if (userId != null && privRightMenuList2 != null ) { 
			for (Menu m: privRightMenuList2) {
				if (m.getSelected()) {
					String url = m.getUrl();
				     if (url != null) {
				         if (url.indexOf("?") == -1) {
				            url = url + "?" + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         } else {    
				            url = url + "&"  + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         }  
				      }
				     if (m.getUrl().toLowerCase().contains("http")) {				  	 
	%>
					  <tr>
					    <td>&nbsp;&nbsp;&nbsp;<a href="pub/launchProcess.selfserve?menuId=<%=m.getId().getMenuId()%>"><%=m.getMenuName() %></a></td>
					  </tr>
	<% 				} else { %>
					  <tr>
					    <td>&nbsp;&nbsp;&nbsp;<a href="<%=m.getUrl()%>"><%=m.getMenuName() %></a></td>
					  </tr>
	<% 				}
				}
			}
		} 
	%>	
	

 <tr>
  	<td><br><b>Reports</b></td>
	</tr>
	<% if (userId != null && privRightMenuList3 != null ) { 
			for (Menu m: privRightMenuList3) {
				if (m.getSelected()) {
					String url = m.getUrl();
				     if (url != null) {
				         if (url.indexOf("?") == -1) {
				            url = url + "?" + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         } else {    
				            url = url + "&"  + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         }  
				      }

	%>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;<a href="<%=url %>"><%=m.getMenuName() %></a></td>
  </tr>
  	<% } else 	{ %>	
  <tr>
    <td class="normaltext">&nbsp;&nbsp;&nbsp;<%=m.getMenuName() %></td>
  </tr>	
	<%			}
			} 
		} 
	%>




  <tr>
  	<td class="bodytext">
&nbsp;&nbsp;&nbsp;<a href="logout.do"> Logout</a>
		</td>
	</tr>
         
  

      
</table>

<% } %>

