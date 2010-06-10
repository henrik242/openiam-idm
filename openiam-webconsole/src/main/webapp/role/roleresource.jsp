<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
	
<form:form commandName="roleResCmd">
	<form:hidden path="roleId" />
	<form:hidden path="domainId" />
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Role - Resource Association</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

    <tr>
    	<td colspan="4">
    		<table>
    			<tr class="tdlight">
    				<td>ID</td>
    				<td>Resource Name</td>
    				<td>Parent</td>
    			</tr>
		      <c:forEach items="${roleResCmd.resourceList}" var="resourceList" varStatus="res">
		  
				<tr class="plaintext">
								<td>
									<form:checkbox path="resourceList[${res.index}].selected" />
									${resourceList.resourceId}
								</td>
								<td>${resourceList.name}</td>
								<td>${resourceList.resourceParent}</td>
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