<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



   		<table  width="600pt" >
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Locations</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
			<c:if test="${msg != null}" >
			   <tr>
			 		<td class="msg" colspan="2" align="center"  >
			 		  ${msg}
			 		</td>
			  </tr> 
			</c:if>
		<tr>
				<td>	

<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>LOCATION LIST</legend>
	
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="600pt">	 
          <tr class="header">
			  			<th>Name</td>
              <th class="tdheader" >Address</td>
              <th class="tdheader" >City</td>
			  			<th class="tdheader" >State</td>
          </tr>
	

					<c:forEach items="${locationAry}" var="location">
						<tr>
				
							<td class="tableEntry">${location.name}</td>
							<td class="tableEntry"><a href="locationDetail.cnt?locationId=${location.locationId}">${location.bldgNum} ${location.streetDirection} ${location.address1}</a> </td>
							<td class="tableEntry">${location.city}</td>
							<td class="tableEntry">${location.state} </td> 
						</tr>
					</c:forEach>

			</table>
          <tr>
              <td colspan="5" align="left"> <a href="locationDetail.cnt">New Location</a></td>
          </tr>
          
          
</table>
</td>
</tr>
</table>