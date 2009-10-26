
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,org.openiam.idm.srvc.menu.dto.Menu" %>
<%@ page import="org.openiam.idm.srvc.user.dto.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%

	String userId = (String)session.getAttribute("userId");
	List<Menu> publicLeftMenuList = (List<Menu>)session.getAttribute("publicLeftMenuGroup");
	List<Menu> publicRightMenuList1 = (List<Menu>)session.getAttribute("publicRightMenuGroup1");
	List<Menu> publicRightMenuList2 = (List<Menu>)session.getAttribute("publicRightMenuGroup2");
	

%>
<table border="0">
	<tr>
		<td><B>Access Management Center</B></td>
	</tr>
	
	<% if (userId == null && publicRightMenuList1 != null ) { 
			for (Menu m: publicRightMenuList1) {
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
	<% if (userId == null && publicRightMenuList2 != null ) { 
			for (Menu m: publicRightMenuList2) {
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


 


