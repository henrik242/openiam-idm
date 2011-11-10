<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


		<table  width="900pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">User Management - Identities</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
			<tr>
				<td>
<form:form commandName="identityUserCmd">
<form:hidden path="perId" />
		<table>
				<tr>
						<td>

      <c:forEach items="${identityUserCmd.principalList}" var="principalList" varStatus="principal">
				<table width="900pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>USER IDENTITY </legend>
				
								<table class="fieldsetTable"  width="100%" >
		 
							<tr>
								<td><label for="username" class="attribute">Login</label></td>
								<td  colspan="2"><form:input path="principalList[${principal.index}].id.login" size="60"  />
								<form:hidden path="principalList[${principal.index}].id.domainId" />
								<form:hidden path="principalList[${principal.index}].userId" /></td>
								<td><form:checkbox path="principalList[${principal.index}].selected" />Delete</td>
							</tr>
							<tr>
								<td><label for="username" class="attribute">Security Domain</label></td>
								<td class="tdlight" colspan="3">${principalList.id.domainId} </td>
							</tr>
							<tr>
								<td><label for="username" class="attribute">Managed System</label></td>
								<td class="tdlight" colspan="3">${principalList.id.managedSysId} - ${principalList.managedSysName}  </td>
							</tr>

							<tr>
								<td><label for="username" class="attribute">Locked<label></td>
								<td><label for="username" class="attribute">
									 <c:if test="${principalList.isLocked == 0}" >
										NO
										</c:if>	 
 									 <c:if test="${principalList.isLocked != 0}" >
										YES
										</c:if>	 
									</label></td>
								<td><label for="username" class="attribute">Authn Fail Count</lable></td>
								<td><label for="username" class="attribute">${principalList.authFailCount}<label></td>
							</tr>
							<tr>
								<td><label for="username" class="attribute">Last Login</label></td>
								<td><label for="username" class="attribute">${principalList.lastLogin}<label></td>
								<td><label for="username" class="attribute">Last Authn Attempt</lable></td>
								<td><label for="username" class="attribute">${principalList.lastAuthAttempt}</label</td>
							</tr>
							<tr>
								<td><label for="username" class="attribute">First Time Login</label></td>
								<td><label for="username" class="attribute">
									<c:if test="${principalList.firstTimeLogin == 0}" >
										NO
										</c:if>	 
 									 <c:if test="${principalList.firstTimeLogin!= 0}" >
										YES
										</c:if>	
										</label></td>
								<td><label for="username" class="attribute">Pswd Changed</label></td>
								<td><label for="username" class="attribute">${principalList.pwdChanged}</label></td>
							</tr>

							<tr>
								<td><label for="username" class="attribute">Pswd Expiration</label></td>
								<td><label for="username" class="attribute">${principalList.pwdExp}</label></td>
								<td><label for="username" class="attribute">Grace Period</label></td>
								<td><label for="username" class="attribute">${principalList.gracePeriod}</label></td>
							</tr>
							<tr>
								<td><label for="username" class="attribute">Identity Status</label></td>
								<td><label for="username" class="attribute">${principalList.status}</label></td>
								<td></td>
								<td></td>
							</tr>

						</table>
					</td>
					
				</tr>
			</TABLE>
			</c:forEach>
 
   

	<tr>
    <td class="buttonRow"align="right"> 
     
          <input type="submit" value="Save"/>  <input type="submit" name="_cancel" value="Cancel" />
    </td>
  </tr>
  
</table>

</form:form>

 </td>
</tr>
</table>
