<%@ page import="java.util.*, diamelle.security.auth.*, diamelle.ebc.navigator.*" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>






<%
  
  List menus = (List) request.getAttribute("menus");
  List permissions = (List) request.getAttribute("permissions");

  String name = null;
  RoleValue roleValue = (RoleValue) request.getAttribute("roleValue");
  String roleId = roleValue.getRoleId();
  
  String menu = (String) request.getAttribute("menu");
%>

<html:form action="/security/permission.do" >
<html:hidden property="roleId" value="<%=roleId%>" />
<html:hidden property="menu" value="<%=menu%>" />

<br>

<table align="center" border="0" width="80%" cellspacing=2 cellpadding=1 >
   <tr>
      <td colspan="3" >
         <%
	      NavigatorBar navBar = (NavigatorBar) session.getAttribute("menuNavBar");
	      NavigatorBarItem item = null;
	       if (navBar != null) {
	         List navList = navBar.getNavigatorList();
	         int size = navList.size();
	 
	         for (int i = 0; i < size; i++) {
	           item = (NavigatorBarItem) navList.get(i);
	           if ( i > 0)
	             out.print(" | ");
	     %>
	  
	         <a href="<%=item.getUrl()%>"><%=item.getMenuName()%></a>
	 
	     <%
	         }
	       }
	 %>   
         
         
      </td>  
   </tr>
   <tr>
    <td class="tdlight" align="center" height="15">
        <b>Permissions</b>
    </td>
    <td>&nbsp;</td>
    <td class="tdlight" align="center" height="15" >
        <b>Permissions in Role</b>
    </td>
    <tr>
       <td class="tdlight" valign="top">&nbsp;&nbsp;
          <% if (menus != null){ 
                 if (!menus.isEmpty()) { %>
            Select Menus to drill down
          <%} else { %>
            
            No Sub Menus
          <%}}%><br>
         <%
           if (menus !=null && !menus.isEmpty()) {
            menus.removeAll(permissions); 
            Iterator rit = menus.iterator();
              while (rit.hasNext()) {
                MenuData menuData = (MenuData) rit.next();   
                
                
                
          %>
           
           <% if (permissions != null && permissions.contains(menuData)) { %>
               <a href="security/permission.do?method=permissionList&roleId=<%=roleId%>&menu=<%=menuData.getMenuId()%>">
                 <%=menuData.getMenuName()%> 
               </a><BR>
            
           <% } else { %>
               <input type="checkBox" name="menId" value="<%=menuData.getMenuId()%>"/>
               <a href="security/role.do?method=permissions&roleId=<%=roleId%>&menu=<%=menuData.getMenuId()%>">
                 <%=menuData.getMenuName()%> 
               </a><BR>
          <%
          }
        }} %>
            
      </td>
      <td>
        <table align="center" width="5%">
          <tr>
            <td>
                 <html:submit property="submit" value=">" title= "add permissions role" />                
            </td>
          </tr>
          <tr>
            <td> <% if (permissions != null && !permissions.isEmpty()) { %>
                    <html:submit value="<" property="submit" title= "remove permissions from Role" />
                  <% } else { %>
                    <html:submit property="submit" disabled="true" value="<" />                    
                  <% } %>
            </td>
          </tr>
        </table>
      </td>
      
      <td class="tdlight" valign="top">
          <%
             if (permissions != null && !permissions.isEmpty()) {
             Iterator itc = permissions.iterator();
               while (itc.hasNext()) {
                  MenuData menuData = (MenuData) itc.next();
                      
               
          %>
            <input type="checkBox" name="perId" value="<%=menuData.getMenuId()%>"/>
            <%=menuData.getMenuName()%><br>
          <%
             }
           }
          %>
          &nbsp;
      </td>
   </tr>
</table>
</html:form>