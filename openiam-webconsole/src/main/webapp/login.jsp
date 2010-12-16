<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<form:form commandName="loginCmd" autocomplete="off" >

<table border="0" width="500" align="center">
  <% if (request.getAttribute("message") != null) { %>
   <tr>
 		<td colspan="2" align="center" class="msg" >
 		  <%=request.getAttribute("message") %>
 		</td>
  </tr> 
  <% } %>
    
			<table border="0" align="center"   height="100%" width="500">
				<tr>
					<td align="center" height="100%" class="loginBodyTable">
					   <fieldset class="userform">
							<legend>LOGIN TO OPENIAM IDENTITY MANAGER</legend>
							<table class="fieldsetTable" width="500">
			
			 						 <tr>
   										 <td><label for="username" >User Principal Name:<font color="red">*</font></label>
   								 </td>
								    <td class="userformInput"><form:input path="principal" size="40" maxlength="40"  autocomplete="off" />
								     <br><form:errors path="principal" cssClass="error" />
								    </td>
  								</tr>
									  <tr>
									    <td><label for="username" > Password:<font color="red">*</font></label>
									    </td>
									    <td class="userformInput"><form:password path="password" size="40" maxlength="40" /><br>
									    <form:errors path="password" cssClass="error" /></td>
									  </tr> 
			
								</table>
						</fieldset>
					</td>
			   </tr>
				<tr>
					   <td  width="70%" class="buttonRow" align="right"><input type="Submit" class="Submit" value="Login" /></td>
					   <td ></td>
				  </tr>
				   </table>
				   </td>
			  </tr>
		</table>
  
</table>
</form:form>



