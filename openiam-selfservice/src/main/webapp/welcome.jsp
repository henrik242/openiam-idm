<%@ page language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%@ page import="java.util.*"  %>
<%@ page import="org.openiam.idm.srvc.user.dto.User"  %>
<%@ page import="org.openiam.idm.srvc.user.dto.UserAttribute"  %>


    <table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr align="center" valign="top">
        <td colspan="2" class="normaltext" >Welcome ${user.firstName} ${user.lastName}</td>
      </tr>
      <tr align="center" valign="top">
        <td></td>
      </tr>
      <tr>
		 <td align="left" >Member of Groups:</td>
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
		<td align="left" >Member of Roles:</td>
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
		 <td align="right">Functional Title: </td>
         <td valign="center">${user.title} </td>
       </tr>
      <tr>
		 <td align="right">Job Classification: </td>
         <td valign="center">${user.jobCode}</td>
       </tr>
      <tr>
		 <td align="right">E-mail: </td>
         <td valign="center">${user.email}</td>
       </tr>
       <tr>
		 <td align="right">Agency: </td>
         <td valign="center">${user.deptCd}</td>
       </tr>
       <tr>
		 <td align="right">Division: </td>
         <td valign="center">${user.division}</td>
       </tr>
    
      
      <c:if test="${challenge == false}" >

      <tr  valign="top" align="center">
        <td colspan="2"><font color="red"><b>Warning: Before you continue, please complete the "Challenge Response" from the Self-Service section
        on the right. This will enable your password self-service options.</b></font></td>
         <td> </td>
      </tr>      
      </c:if>
      

    </table>

