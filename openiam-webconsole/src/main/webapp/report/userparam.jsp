<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



             
<form:form commandName="userReportCmd" target="_blank">

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>User List Report Parameters</strong>
      </td>
   </tr>   
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Department:</td>
              <td class="tdlightnormal">
           <form:select path="deptCd" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${deptList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>  
              </td>
          </tr>
          <tr>
			  <td class="tddark">Role:</td>
              <td class="tdlightnormal">
          <form:select path="role" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <c:forEach items="${roleList}" var="role">
                <form:option value="${role.id.serviceId}*${role.id.roleId}" label="${role.id.serviceId}-${role.roleName}" />
              </c:forEach>
          </form:select> 
              
              
              </td>
          </tr>


         <tr>
    	  <td colspan="2">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="2" align="right">

              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>
</form:form>
