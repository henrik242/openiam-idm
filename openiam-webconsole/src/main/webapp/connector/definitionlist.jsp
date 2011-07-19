<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<table width="800pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>PROVISIONING CONNECTORS</legend>
				
						<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	

        <tr class="header">
			  <th>Name</td>
        <th class="tdheader" >Type</td>
			  <th class="tdheader" >Service URL</td>
       </tr>
	

	<c:forEach items="${connectorAry}" var="provisionConnector">
		<tr>

			<td class="tableEntry"><a href="connectorDetail.cnt?connectorId=${provisionConnector.connectorId}">${provisionConnector.name}</a></td>
			<td class="tableEntry"> ${provisionConnector.metadataTypeId}</td>
			<td class="tableEntry"> ${provisionConnector.serviceUrl} </td> 
		</tr>
	</c:forEach>
                  
</table>
 </td>
</tr>
          <tr>
              <td align="left"> <a href="connectorDetail.cnt">New Connector</a></td>
          </tr>
</tr>
</table>