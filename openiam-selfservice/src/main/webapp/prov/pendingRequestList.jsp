<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%




  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  String queryString = null;


  if (userId != null) {
      queryString = "&userId=" + userId + "&lg="+login + "&tk=" + token;

  }


%>


<table width="100%">
    <tr>
  <td colspan="4" class="title">
      <strong>Pending Requests</strong>
  </td>
</tr>

<tr>
     <td colspan="4" align="center" bgcolor="8397B4" >
       <font></font>
     </td>
</tr>
<tr>
   <td class="tddarknormal" align="right" colspan="4"></td>
</tr>


    <tr class="tdlight">
      <td>Create Date</td>
      <td>Requestor</td>
      <td>Status</td>
      <td>Reason</td>
   </tr>
<c:if test="${reqList != null}" >
<c:forEach items="${reqList}" var="provisionRequest">
    <tr>
        <td><a href="requestDetail.selfserve?requestId=${provisionRequest.requestId}<%=queryString%>"> ${provisionRequest.requestDate}</a></td>
        <td> ${provisionRequest.requestorId}</td>
        <td> ${provisionRequest.status}</td>
        <td> ${provisionRequest.requestReason}</td>
    </tr>
</c:forEach>
</c:if>
<tr>
      <td colspan="4">&nbsp;</td>
</tr>

<tr>
        <td colspan="4" align="center" bgcolor="8397B4" >
         <font></font>
        </td>
</tr>

</table>

