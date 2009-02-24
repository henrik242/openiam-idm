<%@ page import="java.util.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 

	List provTask = (List)request.getAttribute("provTask"); 
	int recordCount = 0;
	if (provTask != null && !provTask.isEmpty()) {
		recordCount = provTask.size();
	}
%>


<table width="600px" border="0" cellspacing="1" cellpadding="1" align="center">

  <tr>
    <td></td>
    <td align="right" colspan="5"><b>Filter:</b>
    	<select>
    		<option>Please select status</option>
    		<option selected>All</option>
    		<option>Open</option>
    		<option>Canceled</option>
			<option>Rejected</option>
    		<option>Approved</option>
    	</select>
    <b>Date Range:</b>
    <input type="text" size="10">- <input type="text" size="10">
    <input type="submit" value="Go"></td>
  </tr>

  <tr>
    <td class="tdheader">Request Id</td>
    <td class="tdheader">Requestor</td>
    <td class="tdheader">Description</td>
    <td class="tdheader">Request Date</td>
    <td class="tdheader">Completion Date</td>
    <td class="tdheader">Status</td>
  </tr>
  <% 
  	if (recordCount == 0) {	
  %>
  	<tr>
  		<td class="tdlightnormal" colspan="5"> 0 Requests found. 
  		</td>
  	</tr>
  <%} %>
  <% 
  
 
  %>
  
  <%
//     if ((x%2) != 0) {
       
   %>
   <tr class="tddarknormal">
   <%// } else {%>
   <tr class="tdlightnormal">
   <%//}%>
  
     <td ></td>
     <td >

    </td>
    <td></td>
    <td>

    </td>
    
    <td >

    </td>

    <td >
     </td>

  </tr>
  <% 
  	if (recordCount > 0) {	
  %>
  <tr>
     <td colspan="6">
        <b>Search Results - <%=recordCount%> Records Found.</b>
     </td>
  </tr>
  <%} %>

  <%   //x++;
     //  }
     //  }
  %>

</table>
