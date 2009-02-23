<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.openiam.webadmin.busdel.base.*" %>
<%@page import="org.openiam.idm.srvc.org.dto.Organization;"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<jsp:include page="/idman/orgsearch.jsp" flush="true" />

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
	List orgList = (List)request.getAttribute("orgList");
	if (orgList == null){
		orgList = new ArrayList();
	}
%>




<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
  <tr>
     <td colspan="3">
        <b>Search Results - <%=recordCount%> Records Found.</b>
     </td>
  </tr>
  <tr>
    <td class="tdheader">Id</td>
    <td class="tdheader">Organization Name</td>
    <td class="tdheader">Type</td>
  </tr>
  
  <% 
  
      if( orgList != null && orgList.size() > 0 ) {
    	int x=0;
    	for (int i=0; i < orgList.size(); i++ ) {
    		Organization org = (Organization)orgList.get(i);
  
  %>
  
  <%
     if ((x%2) != 0) {
       
   %>
   <tr class="tddarknormal">
   <% } else {%>
   <tr class="tdlightnormal">
   <%}%>
  
     <td ><%=org.getOrgId()%></td>
     <td >
        <% if (org.getOrganizationName() != null ) { %>
            <%= JSPUtil.display(org.getOrganizationName())%>
        <% } %>&nbsp;
    </td>
    <td >
      <% if (org.getMetadataTypeId() != null) { %>     
          <%=JSPUtil.display(org.getMetadataTypeId())%>
      <% } %>&nbsp;
    </td>
    


    <td >
       <a href="idman/org.do?method=view&orgId=<%=org.getOrgId()%>&mode=VIEW">View</a> 
    </td>

  </tr>
  <%   x++;
       }
       }
  %>

</table>
