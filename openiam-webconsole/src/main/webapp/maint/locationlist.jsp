<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<table width="100%">
<c:if test="${msg != null}" >
   <tr>
 		<td class="msg" colspan="2" align="center"  >
 		  ${msg}
 		</td>
  </tr> 
</c:if>

	<tr>
      <td colspan="5" class="title">         
          <strong>Location List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Address</td>
              <td class="tdheader" >City</td>
			  <td class="tdheader" >State</td>
          </tr>
	

	<c:forEach items="${locationAry}" var="location">
		<tr class="tdlight">

			<td>${location.name}</td>
			<td> <a href="locationDetail.cnt?locationId=${location.locationId}">${location.bldgNum} ${location.streetDirection} ${location.address1}</a> </td>
			<td> ${location.city}</td>
			<td> ${location.state} </td> 
		</tr>
	</c:forEach>

         <tr>
    	  <td colspan="5">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="5" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="5" align="left"> <a href="locationDetail.cnt">New Location</a></td>
          </tr>
          
          
</table>
