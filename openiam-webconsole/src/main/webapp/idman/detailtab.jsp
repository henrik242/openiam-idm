<%@ page import="java.util.*,org.openiam.webadmin.busdel.base.*" %>
<%@ page import="org.openiam.idm.srvc.user.dto.User" %>
<%

  String userId = request.getParameter("personId");
  if (userId == null || userId.length() == 0){
    userId = (String)request.getAttribute("personId");
  }
  List optList = (List)request.getAttribute("tabOptions");

  if (optList != null) {
      Iterator r = optList.iterator();
      int size = optList.size();

%>

<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0">
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
          	<a href="<%=option.getUrlParam()%>&personId=<%=userId%>" class="link2">
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
             <a href="<%=option.getUrlParam()%>&personId=<%=userId%>" class="whiteLink">
                <%=option.getTitle()%>
             </a>
          <img src="images/rt_tabnotch.gif" width="5" height="5" border="0" align="top">
        </td>
		    <td width="1"></td>
    <%
        }
      }
    %>
    </tR>
   </table>
  </tr>
  <tr>
    <td bgcolor="#98ABCE">
         <jsp:include page="<%=activeJsp%>" flush="true" />
    </td>
  </tr>

</table>
<% } %>