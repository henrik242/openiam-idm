<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.User, org.openiam.webadmin.busdel.base.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<jsp:include page="/idman/search.jsp" flush="true" />

<%
		int size = 0;	// # of pages of data
 		int recordCount = 0;
 		int curPage = 0;
 		
 		//Integer pageCount = (Integer)request.getAttribute("pageCount");
 		Integer resultSize = (Integer)request.getAttribute("resultSize");
 		//Integer currentPage = (Integer)request.getAttribute("currentPage");
 		//curPage = currentPage.intValue();

 		//if (pageCount != null) {
 		//	size = pageCount.intValue();
 		//}
 		
 		if (resultSize != null) {
 			recordCount = resultSize.intValue();
 		}
	List userList = (List)request.getAttribute("searchResult");
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
    <td class="tdheader">UserId</td>
    <td class="tdheader">Name</td>
    <td class="tdheader">E-mail</td>
    <td class="tdheader">Status</td>
    <td class="tdheader">Dept</td>
    <td class="tdheader">&nbsp;</td>
  </tr>
  
  <% 
  
      if( userList != null && userList.size() > 0 ) {
    	int x=0;
    	for (int i=0; i < userList.size(); i++ ) {
    		User ud = (User)userList.get(i);
  
  %>
  

   <tr class="normaltext">

  
     <td><%=ud.getUserId()%></td>
     <td>
        <% if (ud.getFirstName() != null || ud.getLastName() != null) { %>
            <%= JSPUtil.display(ud.getFirstName())%>
             <%=JSPUtil.display(ud.getLastName())%>
        <% } %>&nbsp;
    </td>
    <td><%= JSPUtil.display( "" )%> </td>
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
       <a href="idman/user.do?method=identities&personId=<%=ud.getUserId()%>&mode=VIEW">View</a> 
    </td>

  </tr>
  <%   x++;
       }
       }
  %>
<tr>
     <td colspan="3" align="right">
     	<%	if (size > 0 ) { %>
     		<b>Result Page: </b>
     	<%	} %>
     	<%
     		for (int i=0; i < size; i++) {
     			if (i == curPage) {
     	%>
        	<b><a href="idman/userSearch.do?action=page&pagenbr=<%=(i+1)%>"><%= (i+1) %></a></b>
        <%
        		}else{
        %> 

        	<a href="idman/userSearch.do?action=page&pagenbr=<%=(i+1)%>"><%= (i+1) %></a> 
        <%
        		}
        	}
        %>
     </td>
</tr>
</table>
