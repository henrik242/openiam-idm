<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


   		<table  width="500pt" >
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Security Doamin</h2>
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
<table width="500pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SECURITY DOMAIN LIST</legend>
	
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	 
          <tr  class="header">
			  			<th>Name</td>
              <th >Status</td>
          </tr>
	

				<c:forEach items="${domainAry}" var="domain">
					<tr>
			
						<td class="tableEntry"><a href="secDomainDetail.cnt?domainId=${domain.domainId}">${domain.name}</a></td>
						<td class="tableEntry"> ${domain.status} </td>
					</tr>
				</c:forEach>

			</table>
          <tr>
              <td align="right"> <a href="secDomainDetail.cnt">New Security Domain</a></td>
          </tr>
          
          
</table>
</td>
</tr>
</table>
