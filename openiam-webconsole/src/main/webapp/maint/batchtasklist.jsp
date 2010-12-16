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
								<h2 class="contentheading">SCHEDULED TASKS</h2>
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
						<legend>SCHEDULED TASK LIST</legend>

			<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
          <tr class="header">
			  			<th>Name</td>
              <th>Frequency</td>
              <th>Enabled</td>
              <th>Last Exec Time</td>
          </tr>
	

	<c:forEach items="${taskAry}" var="task">
		<tr>

			<td class="tableEntry"><a href="batchDetail.cnt?taskId=${task.taskId}">${task.taskName}</a></td>
			<td class="tableEntry"> ${task.frequencyUnitOfMeasure} </td>
			<td class="tableEntry"> ${task.enabled} </td>
			<td class="tableEntry"> ${task.lastExecTime} </td>
		</tr>
	</c:forEach>
</table>

          <tr>
              <td colspan="5" align="left"> <a href="batchDetail.cnt">New Batch Task</a></td>
          </tr>
          
</table>
</td>
</tr>
</table>
