<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="600pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">User Management - Groups</h2>
						</td>
						</tr>
					</tabl

<form:form commandName="usergrpCmd">
<form:hidden path="perId" />
          	
	<table>
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SELECT GROUPS FOR USER</legend>
	
		<table class="resourceTable" cellspacing="2" cellpadding="2" >	
	
    			<tr class="header">
    				<th>Group Name</td>
    				<th>Inherit From Parent</td>
    				<th>Status</td>
    			</tr>
		      <c:forEach items="${usergrpCmd.groupList}" var="groupList" varStatus="group">
		  
				<tr>
								<td class="tableEntry">
									<form:checkbox path="groupList[${group.index}].selected" />
									${groupList.grpName} 
									<form:hidden path="groupList[${group.index}].grpId" />
								</td>
								<td class="tableEntry">${groupList.inheritFromParent}</td>
								<td class="tableEntry">${groupList.status}</td>
				</tr>
				
			</c:forEach>
			</table>
	</td>	
	</tr>    
  <tr>
    <td align="right"> 
          <input type="submit" value="Save"/> <input type="submit" value="Cancel"/>  
    </td>
   </table>

  
</table>
</TD>
</TR>
</TABLE>




</form:form>

	</td>
 </tr>
</table>

