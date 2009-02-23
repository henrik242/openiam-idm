<%@ page import="java.util.*,javax.servlet.http.*,diamelle.ebc.user.*" %>
 
<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
 

<html:form action="/assignToken.do?method=assignToken">

		
<table width="100%" border="0" cellspacing="1" cellpadding="1">

<tr>
	<td colspan=4 class="error">
	
	    <logic:messagesPresent>        
	         <html:messages id="error" header="errors.header">
	            <li><bean:write name="error"/></li>
	         </html:messages>    
	     </logic:messagesPresent> 
	
	</td>
</tr>
<tr>
	<td colspan="5" class="tdheader">Assign Tokens</td>
</tr>
  <tr> 
    <td class="tdheader">User Name</td>
    <td class="tdheader">Status</td>
    <td class="tdheader">Create Date</td>
    <td class="tdheader">Created By</td>
    <td class="tdheader"></td>
  </tr>
  <%
  	java.util.List userList = (java.util.List)request.getAttribute("userList");
  	if (userList != null) {
  		int size = userList.size();
  		for (int i=0; i<size; i++) {
  		UserData ud = (UserData)userList.get(i);
     	if ((i%2) != 0) {     
  %>
   		<tr class="tddark"> 
 		<% }else { %>
   		<tr class="tdlight"> 
 		<% }%>
    <td>
    	<input type="checkbox" name="personId" value="<%=ud.getId()%>">
	   	<%=ud.getFirstName()%> <%=ud.getLastName()%> 
    </td>
    <td><%=ud.getStatusId()%></td>
    <td><%=ud.getCreateDate()%></td>
    <td><%=ud.getCreatedBy()%></td>
    <td>
    	<a href="user.do?method=phone&mode=VIEW&personId=<%=ud.getId()%>">View</a>    
    </td>
  </tr>
	  <%
	  	} // end for
	  %>

  <tr> 
    <td colspan="4" align="center">
    	<html:submit property="submit" value="Approve"/>&nbsp;
    	<html:submit property="submit" value="Reject"/>&nbsp;
    	<html:reset/>
    </td>
  </tr>
  <%} else {%>
  <tr> 
    <td colspan="4">No users pending token assignment found.</td>
  </tr>
  <% }%>
</table>
</html:form>
