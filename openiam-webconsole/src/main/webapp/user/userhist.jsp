<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>User - Audit Log (${numOfDays} Day Snapshot)</strong>
      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
   <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
			<font color="red"></font>
       </td>
    </tr>
    <tr>
    	<td>
   		<table cellpadding="1" cellspacing="2">
    			<tr class="tdlight">
    				<td>DATE</td>
    				<td>ACTION</td>
    				<td>ACTION STATUS</td>
    				<td>REASON</td>
    				<td>PRINCIPAL</td>
    			</tr>
		      <c:forEach items="${auditLog}" var="log" >
		  
				<tr class="plaintext">
					<td><fmt:formatDate type="both" value="${log.actionDatetime}" /></td>
					<td>${log.actionId}</td>
					<td>${log.actionStatus}</td>
					<td>${log.reason}</td>
					<td>${log.principal}</td>
				</tr>
				
			</c:forEach>
			</table>
 	</td>	
	</tr>    
   


    <tr>
    	  <td colspan="4">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>


  
</table>



