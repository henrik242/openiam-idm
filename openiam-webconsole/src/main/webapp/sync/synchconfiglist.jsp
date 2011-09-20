<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<table width="800pt" >
	<c:if test="${msg != null}" >
	   <tr>
	 		<td class="msg" align="center"  >
	 		  ${msg}
	 		</td>
	  </tr> 
	</c:if>
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>Synchronization Configuration List</legend>
				
						<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	       
   
          <tr class="header">
			  <th>Name</td>
			  <th>Type</td>
              <th>Status</td>

          </tr>
 	<c:forEach items="${configList}" var="config">
 	
          <tr>
			  			<td class="tableEntry" ><a href="syncConfiguration.cnt?objId=${config.synchConfigId}&menugrp=SYNCUSER">${config.name}</a></td>
              <td class="tableEntry" >${config.synchAdapter}</td>
              <td class="tableEntry" >${config.status}</td>
          </tr>
	</c:forEach>	

</table>
 </td>
</tr>
          <tr>
              <td colspan="5" align="left"> <a href="syncConfiguration.cnt?menugrp=SYNCUSER">New Synchronization Configuration</a></td>
          </tr>
          
          
</table>
