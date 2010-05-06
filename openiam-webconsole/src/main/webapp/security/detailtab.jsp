<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,diamelle.security.auth.RoleValue, org.openiam.webadmin.busdel.base.*,diamelle.ebc.user.*" %>

<!-- detailtab.jsp -->

<%  
  RoleValue roleValue = (RoleValue) request.getAttribute("roleValue");
  
  String roleId = null;
  if (roleValue != null)
    roleId = roleValue.getRoleId();
  // temp fix
  if (roleId == null)
    roleId = (String) session.getAttribute("roleId");
  
  List optList = (List)request.getAttribute("tabOptions");
  if (optList != null) {
      Iterator r = optList.iterator();
      int size = optList.size(); 
      

%>
<br>
<table width="650" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
        <td>
            RoleId : <b><%=roleId%></b>
        </td>
        <td align="right">
            Role Name : <b><%=roleValue.getRoleName()%></b>
        </td>
      </tr>
       <tr>
			 		<td colspan="2" align="center"  >
			 		  &nbsp;
			 		</td>
			  </tr>
      <tr>
			 		<td colspan="2" align="center" bgcolor="8397B4" >
			 		  <font></font>
			 		</td>
	  </tr>
	  <tr>
			 		<td colspan="2" align="center"  >
			 		  &nbsp;
			 		</td>
			  </tr>
		 <tr>
			 		<td colspan="2" align="center"  >
			 		  &nbsp;
			 		</td>
			  </tr>
</table>
<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
   	<table cellspacing="0" cellpadding="0" border="0" >
     
    	<tr>
    <%
      String activeJsp = null;
      

      while (r.hasNext()) {
        TabOption option = (TabOption) r.next();

        if (option.isActive()) {
          activeJsp = option.getRelatedPage();
    %>
       
        <td bgcolor="#98ABCE" valign="top" height="10">
	    		<img src="images/lt_tabnotch.gif" width="5" height="5" border="0" align="top">
          	<a href="<%=option.getUrlParam()%>&roleId=<%=roleId%>" >
               <%=option.getTitle()%>
            </a>
          <img src="images/rt_tabnotch.gif" width="5" height="5" border="0" align="top"></td>
		    <td width="1"></td>
        
    <%
        } else {
          // not active
    %>
        <td bgcolor="#DBDFE9" height="10" >
        	<img src="images/lt_tabnotch.gif" border="0" align="top">
             <a href="<%=option.getUrlParam()%>&roleId=<%=roleId%>" >
                <%=option.getTitle()%>
             </a>
          <img src="images/rt_tabnotch.gif" width="5" height="5" border="0" align="top">
        </td>
		    <td width="1"></td>
    <%
        }
      }
    %>
     </tr>
   </table>
  </tr>
  <tr>
    <td bgcolor="#98ABCE">
         <jsp:include page="<%=activeJsp%>" flush="true" />
    </td>
  </tr>

</table>
<% } %>