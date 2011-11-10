<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.User, org.openiam.webadmin.admin.JSPUtil" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<jsp:include page="/idman/search.jsp" flush="true" />

<%
		int size = 0;	// # of pages of data
 		int recordCount = 0;
 		int curPage = 0;
 		
 	 	String msg = (String)request.getAttribute("msg");
 		
 		Integer resultSize = (Integer)request.getAttribute("resultSize");

 		
 		if (resultSize != null) {
 			recordCount = resultSize.intValue();
 		}
	List<User> userList = (List)request.getAttribute("userList");
	if (userList == null){
		userList = new ArrayList();
	}
%>



<table  width="800pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SEARCH RESULTS - <%=recordCount%> Records Found</legend>

				
<table class="resourceTable" cellspacing="2" cellpadding="2" >

  <tr class="header">
    <th>Name</th>
    <th>E-mail</th>
		<th>Phone</th>
    <th>User Status</th>
    <th>Acct State</th>
    <th>Dept</th>
    <th></th>
  </tr>
  
  <% 
  
      if( userList != null && userList.size() > 0 ) {
    	for (User ud : userList) {
  
  %>
  

   <tr>

  
     <td  class="tableEntry">
        <% if (ud.getFirstName() != null || ud.getLastName() != null) { %>
           <a href="editUser.cnt?personId=<%=ud.getUserId()%>&menugrp=QUERYUSER"><%= JSPUtil.display(ud.getFirstName())%> <%=JSPUtil.display(ud.getLastName())%> </a>
        <% } %>&nbsp;
    </td>
    <td  class="tableEntry"><%= JSPUtil.display(ud.getEmail() )%> </td>
    <td  class="tableEntry"><%= JSPUtil.display(ud.getAreaCd() )%>-<%= JSPUtil.display(ud.getPhoneNbr() )%> </td>
    <td  class="tableEntry">
      <% if (ud.getStatus() != null) { %>     
          <%=JSPUtil.display(ud.getStatus())%>
      <% } %>&nbsp;
    </td>
     <td  class="tableEntry">
      <% if (ud.getSecondaryStatus() != null) { %>
          <%=JSPUtil.display(ud.getSecondaryStatus())%>
      <% } %>&nbsp;
    </td>

    <td  class="tableEntry">
      <% if (ud.getDeptCd() != null) { %>
         <%=JSPUtil.display(ud.getDeptCd())%>
      <% } %>&nbsp;
    </td>

    <td  class="tableEntry">
         <a href="userChangeStatus.cnt?personId=<%=ud.getUserId()%>&status=DELETED">DELETE</a>
    </td>
    

  </tr>
  <%  
       }
       }
  %>

</table>

</td>
</tr>
</table>