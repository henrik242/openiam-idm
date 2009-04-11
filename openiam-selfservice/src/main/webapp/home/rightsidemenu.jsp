
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,org.openiam.idm.srvc.menu.dto.Menu" %>
<%@ page import="org.openiam.idm.srvc.user.dto.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	String userId = (String)session.getAttribute("userId");
	Menu[] publicLeftMenuAry = (Menu[])session.getAttribute("publicLeftMenuGroup");
	Menu[] publicRightMenuAry1 = (Menu[])session.getAttribute("publicRightMenuGroup1");
	Menu[] publicRightMenuAry2 = (Menu[])session.getAttribute("publicRightMenuGroup2");
	

%>
<table border="0">
	<tr>
		<td><B>Access Management Center</B></td>
	</tr>
	
	<% if (userId == null && publicRightMenuAry1 != null ) { 
			for (Menu m: publicRightMenuAry1) {
				if (m.getPublicUrl() != null && m.getPublicUrl()) {
	%>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;<a href="<%=m.getUrl() %>"><%=m.getMenuName() %></a></td>
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
	<% if (userId == null && publicRightMenuAry2 != null ) { 
			for (Menu m: publicRightMenuAry2) {
				if (m.getPublicUrl() != null && m.getPublicUrl()) {
	%>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;<a href="<%=m.getUrl() %>"><%=m.getMenuName() %></a></td>
  </tr>
  	<% } else 	{ %>	
  <tr>
    <td class="normaltext">&nbsp;&nbsp;&nbsp;<%=m.getMenuName() %></td>
  </tr>	
	<%			}
			} 
		} 
	%>
	
	
  
</table>


 


