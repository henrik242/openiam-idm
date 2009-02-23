<!--List of groups for the role selected  -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	List spList = (List)request.getAttribute("spList");
	int size = 0;
	if (spList != null) {
		size = spList.size();
	}

%>


<br>

       <table width="100%" align="center" border="0" cellspacing=2 cellpadding=1  >
       	<tr>
       		<td class="tdheader" colspan="4" align="center">Service Provider (SP) List</td>
       	</tr>
       	<tr>
       		<td class="tdheader">SP Id</td>
       		<td class="tdheader">SP Name</td>
       		<td class="tdheader">Protocol</td>
       		<td class="tdheader">Status</td>
       	</tr>
         

         <% if ( spList != null &&  
        		 spList.size() > 0) {%>
         	<%
         		for (int i=0; i<size; i++) {
         	%>
         		<% if ((i%2) != 0) { %>
  					<tr class="tddarknormal">
  				<% } else {%>
  					<tr class="tdlightnormal">
  				<%}%>
	        		<td></td>
	        		<td></td>
	        		<td></td>
	        		<td></td>
       			</tr>   
         	<%	} %>

	  <% } else { %>
           <tr>
                 <td colspan="4" >
                   <b>No SPs Found</b>
                 </td>

           </tr>		  
	  <% }  %>
           <tr>
                 <td colspan="4" >
                   <b><a href="home.jsp?bodyjsp=/feder/sp.jsp">New SP</a></b>
                 </td>

           </tr>
	</table>



