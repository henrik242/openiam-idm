<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Provisioning Connectors</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Type</td>
              <td class="tdheader" >Class Name </td>
			  <td class="tdheader" >Service URL</td>
			  <td class="tdheader" ></td>
          </tr>
	

	<c:forEach items="${connectorAry}" var="provisionConnector">
		<tr class="tdlight">

			<td> ${provisionConnector.name}</td>
			<td> ${provisionConnector.metadataTypeId}</td>
			<td> ${provisionConnector.className}</td>
			<td> ${provisionConnector.serviceUrl} </td> 
			<td><a href="connectorDetail.cnt?connectorId=${provisionConnector.connectorId}">View Detail</a></td>
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
              <td colspan="5" align="left"> <a href="connectorDetail.cnt">View Detail</a></td>
          </tr>
          
          
</table>
