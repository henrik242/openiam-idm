<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*, diamelle.common.cat.*,org.openiam.idm.srvc.menu.dto.Menu,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<table border="0" width="180">
<%

  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  
  	Menu[] privLeftMenuAry = (Menu[])session.getAttribute("privateLeftMenuGroup");
	Menu[] privRightMenuAry1 = (Menu[])session.getAttribute("privateRightMenuGroup1");
	Menu[] privRightMenuAry2 = (Menu[])session.getAttribute("privateRightMenuGroup2");
	

  
  String queryString = null;

  if (userId != null) {
    if (token != null) {
      queryString = "userId=" + userId + "&token=" + token + "&lg="+login;
    } else {
      queryString = "userId=" + userId + "&lg="+login;
    }
  }


%>
	<tr>
		<td><B>Access Management Center</B></td>
	</tr>
	
	<% if (userId != null && privRightMenuAry1 != null ) { 
			for (Menu m: privRightMenuAry1) {
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
	<% if (userId != null && privRightMenuAry2 != null ) { 
			for (Menu m: privRightMenuAry2) {
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



