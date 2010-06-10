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
          <strong>List of Batch Based Tasks</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Frequency</td>
              <td class="tdheader" >Enabled</td>
              <td class="tdheader" >Last Exec Time</td>

          </tr>
	

	<c:forEach items="${taskAry}" var="task">
		<tr class="tdlight">

			<td><a href="batchDetail.cnt?taskId=${task.taskId}">${task.taskName}</a></td>
			<td> ${task.frequencyUnitOfMeasure} </td>
			<td> ${task.enabled} </td>
			<td> ${task.lastExecTime} </td>
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
              <td colspan="5" align="left"> <a href="batchDetail.cnt">New Batch Task</a></td>
          </tr>
          
          
</table>
