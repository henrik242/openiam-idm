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
								<h2 class="contentheading">Role Management</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>       
	
<form:form commandName="roleResCmd">
	<form:hidden path="roleId" />
	<form:hidden path="domainId" />
	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>ASSOCIATE RESOURCES TO A ROLE</legend>

   
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
    			<tr class="header">
    				<th>Resource Name</td>
    				<th>Parent</td>
    			</tr>
		      <c:forEach items="${roleResCmd.resourceList}" var="resourceList" varStatus="res">
		  
				<tr class="plaintext">
								<td class="tableEntry">
									<form:checkbox path="resourceList[${res.index}].selected" />
									${resourceList.name}
								</td>
								<td class="tableEntry">${resourceList.resourceParent}</td>
				</tr>
				
			</c:forEach>
			</table>
	</td>	
	</tr>    


    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="2" align="right"> <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>
</form:form>

</div>