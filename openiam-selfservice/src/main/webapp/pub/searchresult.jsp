<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.*,org.openiam.webadmin.busdel.base.*" %>
<%@ page import="org.openiam.idm.srvc.continfo.dto.EmailAddress,org.openiam.idm.srvc.continfo.dto.Phone" %>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<jsp:include page="search.jsp" flush="true" />

<%
 	int recordCount = 0;
	int maxResultSize = 100;
		
 	Integer resultSize = (Integer)request.getAttribute("resultSize");
 	Integer maxSize = (Integer)request.getAttribute("maxResultSize");
 	if (maxSize != null) {
 		maxResultSize = maxSize.intValue();
 	}
 	
 	List userList = (List)request.getAttribute("userList");
	if (userList == null){
		userList = new ArrayList();
	}
	if (resultSize != null) {
		recordCount = resultSize.intValue();
	}
%>




<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
  <tr>
     <td colspan="5">
        <b>Search Results - <%=recordCount%> Records Found.</b>
     </td>
  </tr>
  <tr>
    <td class="tdheader">Last Name</td>
    <td class="tdheader">First Name</td>
		<td class="tdheader">M.I.</td>
    <td class="tdheader">Department</td>
    <td class="tdheader">Phone #</td>
	  <td class="tdheader">E-mail Address</td>
    <td class="tdheader"></td>
  </tr>
  
  <% 
  
      if( userList != null && userList.size() > 0 ) {
    	int x=0;
    	int size = userList.size();
    	// Only show the maxium # of records as defined in the maxResultSize parameter.
    	if (size > maxResultSize) {
    		size = maxResultSize;
    	}
    	for (int i=0; i < userList.size(); i++ ) {
    		User ud = (User)userList.get(i);
    		EmailAddress em = null;
				Phone ph = null;
    		
	 		 	Set emailSet = ud.getEmailAddress();
			 	Iterator<EmailAddress> it = emailSet.iterator();
			 	if (it.hasNext()) {
			 		em = it.next();
			 	}

	 		 	Set phoneSet = ud.getPhone();
			 	Iterator<Phone> itPhone = phoneSet.iterator();
			 	if (itPhone.hasNext()) {
			 		ph = itPhone.next();
			 	}
 
  %>
  
  <%
     if ((x%2) != 0) {
       
   %>
   <tr class="tddarknormal">
   <% } else {%>
   <tr class="tdlightnormal">
   <%}%>
  
     <td >
     	  <% if ( ud.getLastName() != null) { %>
             <%=ud.getLastName()%>
        <% } %>
     	</td>
     <td >
        <% if (ud.getFirstName() != null ) { %>
            <%=ud.getFirstName()%>
        <% } %>&nbsp;
    </td>
     <td >
        <% if (ud.getMiddleInit() != null ) { %>
            <%=ud.getMiddleInit()%>
        <% } %>&nbsp;
    </td>
    <td >
      <% if (ud.getDeptName() != null) { %>
         <%=ud.getDeptName()%>
      <% } %>&nbsp;
    </td>
 
    <td> 
      <% if (ph != null && ph.getPhoneNbr() != null) { %> 
    	<%= ph.getPhoneNbr() %>
    	<% } %>		
    </td>
    

    <td>
    	<% if (em != null && em.getEmailAddress() != null) { %> 
    	<%= em.getEmailAddress() %>
    	<% } %>	
    </td>

    <td >
       <a href="pub/directory.do?method=detail&personId=<%=ud.getUserId()%>">View</a> 
    </td>

  </tr>
  <%   x++;
       }
       }
  %>

</table>
