<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!-- Migrating to this page for showing policy lists instead of using the struts pages.  -->
   
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Policy List</strong>
      </td>
	
 		<td colspan="3" align="center" class="error" >
	 		<c:if test="${confmsg != null}" > 
	 		  ${confmsg}
	 		</c:if>
	 	</td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  
     
         <tr>
			  <td class="tdheader" >Policy Id</td>
              <td class="tdheader" >Policy Type</td>
              <td class="tdheader" >Name </td>
			  <td class="tdheader" >Status</td>
			  <td class="tdheader" ></td>
          </tr>
	

	<c:forEach items="${policyAry}" var="policy">
		<tr class="tdlight">

			<td> ${policy.policyId}</td>
			<td> ${policy.policyDefId}</td>
			<td> ${policy.name}</td>
			<td> ${policy.status} </td> 
			<td><a href="attrPolicy.cnt?policyId=${policy.policyId}">View Detail</td>
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
              <td colspan="5" align="left"> <a href="attrPolicy.cnt">New Policy</a></td>
          </tr>
          
</table>
   