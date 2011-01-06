<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="700pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">User Management - Reset Password</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
			<tr>
				<td>
					
<form:form commandName="pswdChangeCmd">
<table width="700pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>RESET PASSWORD FOR ${pswdChangeCmd.firstName} ${pswdChangeCmd.lastName}</legend>

				<table class="fieldsetTable"  width="100%" >

  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->

	<form:hidden path="domainId"    />


  <tr>
    <td><label for="username" class="attribute">Login</LABEL>
    </td>
    <td>
    	<form:input path="principal" size="30"  maxlength="30" readonly="true"   />
    </td>
  </tr>


  <tr>
    <td><label for="username" class="attribute">New Password</LABEL><font color=red>*</font>
    </td>
    <td>
    	<form:password path="password" size="30"  maxlength="30"   /><br>
    	 <form:errors path="password" cssClass="error" />
       
    </td>
  </tr>
  <tr>
    <td><label for="username" class="attribute">Confirm New Password</LABEL><font color=red>*</font>
    </td>
    <td class="plaintext">
       <form:password path="confPassword" size="30"  maxlength="30"   /><br>
        <form:errors path="confPassword" cssClass="error" />
    </td>
  </tr>  
</TABLE>  


  <tr class="buttonRow">

    <td  align="right">
    		<input type="submit" value="Save">  <input type="submit" value="Cancel">
    </td>
  </tr>

</table>
</form:form> 

 </td>
</tr>
</table>




