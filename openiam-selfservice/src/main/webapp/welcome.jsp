<%@ page language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.util.*"  %>
<%@ page import="org.openiam.idm.srvc.user.dto.User"  %>
<%@ page import="org.openiam.idm.srvc.user.dto.UserAttribute"  %>

<%

  String token = (String)session.getAttribute("token");
  String userId = (String)session.getAttribute("userId");
  String login = (String)session.getAttribute("login");
 %>

    <table width="80%" border="0" cellspacing="1" cellpadding="1">
      <tr align="center" valign="top">
        <td colspan="2" class="normaltext" >Welcome ${user.firstName} ${user.lastName}</td>
      </tr>
      <tr align="center" valign="top">
        <td></td>
      </tr>
      <tr>
		 <td align="right" ><b>Member of Groups:</b></td>
         <td valign="center">
         <c:if test="${groupList != null}" >
         	<table>
			<c:forEach items="${groupList}" var="group">
	       		<tr>
	       			<td>${group.grpName}</td>
	       		</tr>
       		</c:forEach>
       		</table>
       	</c:if>
       	</td>
       </tr>
       <tr>
		<td align="right" ><b>Member of Roles:</b></td>
        <td >
        <c:if test="${roleList != null}" >
         	<table>
			<c:forEach items="${roleList}" var="role">
	       		<tr>
	       			<td>${role.roleName}</td>
	       		</tr>
       		</c:forEach>
       		</table>
       	</c:if>
		</td>
      </tr>
       <tr>
		 <td align="right"><b>Title:</b></td>
         <td valign="center">${user.title} </td>
       </tr>
        <tr>
		 <td align="right"><b>Supervisor:</b></td>
         <td valign="center">${supervisor} </td>
       </tr>
         <tr>
		 <td align="right"><b>Department:</b> </td>
         <td valign="center">${dept}</td>
       </tr>


      <tr>
		 <td align="right"><b>E-mail:</b></td>
         <td valign="center">${user.email}</td>
       </tr>

        <tr>
		 <td align="right"><b>Password Changed:</b> </td>
         <td valign="center">${primaryIdentity.pwdChanged}</td>
       </tr>
       <tr>
		 <td align="right"><b>Days to Password Exp:</b> </td>
         <td valign="center">${daysToExp}</td>
       </tr>
         <tr>
		 <td align="right"><b>Last Login:</b> </td>
         <td valign="center">${primaryIdentity.lastLogin}</td>
       </tr>

       <c:if test="${pendingReq != null}" >
        <tr  valign="top" align="center">
        <td colspan="2">You have <b><a href="myPendingRequest.selfserve?userId=<%=userId%>&lg=<%=login%>&tk=<%=token%>" >${pendingReq} Request(s)</a></b> Pending</td>
         <td> </td>
      </tr>
       </c:if>

      <c:if test="${challenge == false}" >

      <tr  valign="top" align="center">
        <td colspan="2"><font color="red"><b>Warning: Before you continue, please complete the "Challenge Response" from the Self-Service section
        on the right. This will enable your password self-service options.</b></font></td>
         <td> </td>
      </tr>
      </c:if>


    </table>



