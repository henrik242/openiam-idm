<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,org.openiam.webadmin.busdel.base.*,diamelle.ebc.user.*" %>


<%

  String policyId = request.getParameter("polId");
  if (policyId == null || policyId.length() == 0){
    policyId = (String)request.getAttribute("policyId");
  }
  List optList = (List)request.getAttribute("tabOptions");

  if (optList != null) {
      Iterator r = optList.iterator();
      int size = optList.size();
      String activeJsp = null;

%>

<table width="600" align="center" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
   	<table cellspacing="0" cellpadding="0" border="0" >
    	<tr>
    <%
      
      while (r.hasNext()) {
        TabOption option = (TabOption) r.next();

        if (option.isActive()) {
           activeJsp = option.getRelatedPage();
          
    %>
       
        <td bgcolor="#98ABCE" valign="top" height="10">
	    		<img src="images/lt_tabnotch.gif" width="5" height="5" border="0" align="top">
          	<a href="<%=option.getUrlParam()%>&policyId=<%=policyId%>" class="link2">
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
             <a href="<%=option.getUrlParam()%>&policyId=<%=policyId%>" class="whiteLink">
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
<% 
	if (activeJsp != null && activeJsp.length() > 0) { 
%>

  <tr>
    <td bgcolor="#98ABCE" align="center">
         <jsp:include page="<%=activeJsp%>" flush="true">
				<jsp:param name="policyId" value="<%=policyId%>" />         
         </jsp:include>
    </td>
  </tr>
<% } %>

</table>
<% } %>