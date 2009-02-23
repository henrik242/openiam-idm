<%@ page language="java"%>
<%@ page import="java.util.*,javax.servlet.http.*,org.openiam.idm.srvc.user.dto.User, org.openiam.webadmin.busdel.base.*" %>
 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
 
<%
	String userId = (String)session.getAttribute("userId");
%>
<html:form action="idman/approveUser.do?method=approveUser">
		
<table width="600pt" border="0" cellspacing="1" cellpadding="1">
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
	<td colspan="5" class="tdheader">Approve User</td>
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
  		User ud = (User)userList.get(i);
     	if ((i%2) != 0) {     
  %>
   		<tr class="tddark"> 
 		<% }else { %>
   		<tr class="tdlight"> 
 		<% }%>
    <td>
    	<%
    		if (!userId.equalsIgnoreCase(ud.getCreatedBy())) {
    	%>
    	<input type="checkbox" name="personId" value="<%=ud.getUserId()%>">
    	<% } %>
    	<%=ud.getFirstName()%> <%=ud.getLastName()%>
    </td>
    <td><%= JSPUtil.display( ud.getStatus() )%> </td>
    <td><%= JSPUtil.display( ud.getCreateDate() )%></td>
    <td><%= JSPUtil.display( ud.getCreatedBy() )%></td>
    <td><a href="idman/user.do?method=identities&mode=VIEW&personId=<%=ud.getUserId()%>">View</a>
    </td>
  </tr>
	  <%
	  	} // end for
	  %>

  <tr> 
    <td colspan="4" align="center">
    	<html:submit property="submit" value="Approve"/>&nbsp;
    	<html:submit property="submit" value="Reject"/>&nbsp;<html:reset/></td>
  </tr>
  <%} else {%>
  <tr> 
    <td colspan="4">No users pending approval found.</td>
  </tr>
  <% }%>
</table>
</html:form>
