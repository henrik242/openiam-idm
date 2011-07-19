<%@ page contentType="text/html" language="java" import="java.util.*,diamelle.security.log.*" %>
<span class="title">Search Results</span>

<table border="0" cellpadding=1 cellspacing = 2 width="100%">   
<%
 		List resultList = (List) request.getAttribute("searchResult");
  %>
<tr >
    <td class="tdheader" align="left">Log Id</td>
    <td class="tdheader" align="left">User Id </td>
    <td class="tdheader" align="left">Time</td>
    <td class="tdheader" align="left">Service</td>
    <td class="tdheader" align="left">Host / IP</td>
    <td class="tdheader" align="left">Description</td>
   </tr>
<%
    if(resultList != null && resultList.size() > 0 ) {
    for (int i=0; i<resultList.size(); i++ ) {
    	AccessLogValue val = (AccessLogValue)resultList.get(i);

%>
 <%
     if ((i%2) != 0) {
       
   %>
   <tr class="tddark">
   <% } else {%>
   <tr class="tdlight">
   <%}%>

      <td align="left"><%=val.getAccessLogId()%></td>
      <td align="left"><%=val.getUserId()%></td>
      <td align="left"><%=val.getLogTime()%></td>
      <td align="left"><%=val.getServiceId()%></td>
      <td align="left"><%=val.getHostIp()%></td>
      <td align="left"><%=val.getDescription()%></td>
 </tr>
<% }
  }else { 
%>

<tr>
  <td align=center class="normalTxtBlack" colspan="11">
    No records found!
  </td>
</tr>
<% }
%>
</table>

