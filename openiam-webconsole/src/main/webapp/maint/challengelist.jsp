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
          <strong>Challenge Response Question List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Status</td>

          </tr>
	

	<c:forEach items="${questList}" var="quest">
		<tr class="tdlight">

			<td><a href="challengeQuest.cnt?questId=${quest.identityQuestionId}">${quest.questionText}</a></td>
			<td> ${quest.active} </td>
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
              <td colspan="5" align="left"> <a href="challengeQuest.cnt">New Challenge Question</a></td>
          </tr>
          
          
</table>
