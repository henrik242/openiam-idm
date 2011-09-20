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
								<h2 class="contentheading">Challenge Response Questions</h2>
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
<table width="600pt" >
				<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>Challenge Response Question List</legend>
				
				<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
          <tr class="header">
			  			<th>Name</td>
              <th>Status</td>
          </tr>
          


	<c:forEach items="${questList}" var="quest">
		<tr>
			<td class="tableEntry"><a href="challengeQuest.cnt?questId=${quest.identityQuestionId}">${quest.questionText}</a></td>
			<td class="tableEntry">${quest.active} </td>
		</tr>
	</c:forEach>

          
</table>
          <tr>
              <td colspan="2" align="left"> <a href="challengeQuest.cnt">New Challenge Question</a></td>
          </tr>
          
</td>
</tr>
</table>
