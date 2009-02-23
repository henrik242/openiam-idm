<!--List of groups for the role selected  -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	List idpList = (List)request.getAttribute("idpList");
	int size = 0;
	if (idpList != null) {
		size = idpList.size();
	}

%>


<br>

       <table width="100%" align="center" border="0" cellspacing=2 cellpadding=1  >
       	<tr>
       		<td class="tdheader" colspan="4" align="center">Identity Provider (IdP) List</td>
       	</tr>
       	<tr>
       		<td class="tdheader">IdP Id</td>
       		<td class="tdheader">IdP Name</td>
       		<td class="tdheader">Adapter Type</td>
       		<td class="tdheader">Status</td>
       	</tr>
         

         <% if ( idpList != null &&  
        		 idpList.size() > 0) {%>
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
                   <b>No IdPs Found</b>
                 </td>

           </tr>		  
	  <% }  %>
           <tr>
                 <td colspan="4" >
                   <b><a href="home.jsp?bodyjsp=/feder/idp.jsp">New IdP</a></b>
                 </td>

           </tr>
	</table>



