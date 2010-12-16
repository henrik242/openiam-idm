<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<table width="100%">

	<tr>
      <td colspan="4" class="title">         
          <strong>Organization Policy List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Start Date</td>
              <td class="tdheader" >End Date</td>
          </tr>
	

	<c:forEach items="${policyList}" var="orgPolicy">
		<tr class="tdlight">

			<td><a href="orgPolicyDetail.cnt?orgPolicyId=${orgPolicy.orgPolicyId}">${orgPolicy.name}</a></td>
			<td>${orgPolicy.startDate}  </td>
			<td>${orgPolicy.endDate}  </td>
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
              <td colspan="5" align="left"> <a href="orgPolicyDetail.cnt">New Organization Policy</a></td>
          </tr>
          
          
</table>
