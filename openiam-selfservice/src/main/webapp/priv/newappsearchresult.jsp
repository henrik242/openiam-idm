<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.*,org.openiam.webadmin.busdel.base.*" %>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<jsp:include page="search.jsp" flush="true" />

<%
		int size = 0;	// # of pages of data
 		int recordCount = 0;
 		int curPage = 0;
 		
 		Integer pageCount = (Integer)request.getAttribute("pageCount");
 		Integer resultSize = (Integer)request.getAttribute("resultSize");
 		Integer currentPage = (Integer)request.getAttribute("currentPage");
 		curPage = currentPage.intValue();

 		if (pageCount != null) {
 			size = pageCount.intValue();
 		}
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
     <td colspan="5">
        <b>Search Results - <%=recordCount%> Records Found.</b>
     </td>
  </tr>
  <tr>
    <td class="tdheader">UserId</td>
    <td class="tdheader">Name</td>
    <td class="tdheader">Phone</td>
    <td class="tdheader">Dept</td>
    <td class="tdheader">Country</td>
    <td class="tdheader"></td>
  </tr>
  
  <% 
  
      if( userList != null && userList.size() > 0 ) {
    	int x=0;
    	for (int i=0; i < userList.size(); i++ ) {
    		User ud = (User)userList.get(i);
  
  %>
  
  <%
     if ((x%2) != 0) {
       
   %>
   <tr class="tddarknormal">
   <% } else {%>
   <tr class="tdlightnormal">
   <%}%>
  
     <td ><%=ud.getUserId()%></td>
     <td >
        <% if (ud.getFirstName() != null || ud.getLastName() != null) { %>
            <%=ud.getFirstName()%>
             <%=ud.getLastName()%>
        <% } %>&nbsp;
    </td>
    <td> </td>
    
    <td >

    </td>
    <td></td>

    <td >
       <a href="priv/newAppl.do?method=directory&personId=<%=ud.getUserId()%>">Update Access Rights</a> 
    </td>

  </tr>
  <%   x++;
       }
       }
  %>

</table>
