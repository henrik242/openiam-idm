<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.User, org.openiam.selfsrvc.JSPUtil" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<jsp:include page="/usradm/search.jsp" flush="true" />

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




<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
  <tr>
     <td colspan="6" class="normaltext">
        <b>Search Results - <%=recordCount%> Records Found.</b>
     </td>
  </tr>
  <tr>
    <td class="tdheader">Name</td>
    <td class="tdheader">E-mail</td>
    <td class="tdheader">Status</td>
    <td class="tdheader">Dept</td>
    <td class="tdheader">User ID</td>
     <td class="tdheader"></td>
  </tr>
  
  <% 
  
      if( userList != null && userList.size() > 0 ) {
    	int x=0;
    	for (User ud : userList) {
  
  %>
  

   <tr class="normaltext">

  
     <td>
        <% if (ud.getFirstName() != null || ud.getLastName() != null) { %>
           <a href="editUser.selfserve?personId=<%=ud.getUserId()%>&menugrp=SELF_QUERYUSER"><%= JSPUtil.display(ud.getFirstName())%> <%=JSPUtil.display(ud.getLastName())%> </a>
        <% } %>&nbsp;
    </td>
    <td><%= JSPUtil.display(ud.getEmail() )%> </td>
    <td>
      <% if (ud.getStatus() != null) { %>     
          <%=JSPUtil.display(ud.getStatus())%>
      <% } %>&nbsp;
    </td>
    
    <td>
      <% if (ud.getDeptCd() != null) { %>
         <%=JSPUtil.display(ud.getDeptCd())%>
      <% } %>&nbsp;
    </td>
    <td>
         <%=ud.getUserId()%>
    </td>
    <td>
         <a href="userChangeStatus.selfserve?personId=<%=ud.getUserId()%>&status=DELETED">DELETE</a>
    </td>
    

  </tr>
  <%   x++;
       }
       }
  %>

</table>
