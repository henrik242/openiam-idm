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
	
<form:form commandName="rolePolicyCmd">
	<form:hidden path="roleId" />
	<form:hidden path="domainId" />
	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>MANAGE ROLE POLICIES</legend>

   
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
    			<tr class="header">
	    			<th></th>
    				<th>Order</th>
    				<th>Label</th>
    				<th>Action</th>
    				<th>Days</th>
    				<th>Days Relative To</th>
    				<th>Handler Script</th>
    			</tr>
		      <c:forEach items="${rolePolicyCmd.policyList}" var="policyList" varStatus="policy">
		  
				<tr class="plaintext">
								<td class="tableEntry">
									<form:hidden path="policyList[${policy.index}].rolePolicyId" />
									<form:checkbox path="policyList[${policy.index}].selected" />
								</td>
								<td class="tableEntry">
								<form:select path="policyList[${policy.index}].executionOrder">
			    				  <form:option value="1" />
										<form:option value="2" />
										<form:option value="3" />
										<form:option value="4" />
										<form:option value="5" />
										<form:option value="6" />
										<form:option value="7" />
										<form:option value="8" />
								</form:select>

								</td>
								<td class="tableEntry">
									<form:input path="policyList[${policy.index}].name" />
								</td>
								<td class="tableEntry">
									<form:select path="policyList[${policy.index}].action">
				    				  <form:option value="" 		label="-PLEASE SELECT-" />
				    				  <form:option value="NOTIFY"  	label="NOTIFY"/>
									  <form:option value="DISABLE"  label="DISABLE" />
									  <form:option value="DELETE" 	label="DELETE" />
									</form:select>
								</td>								
								<td class="tableEntry">									
									<form:input path="policyList[${policy.index}].value1" />
								</td>	
								<td class="tableEntry">
									<form:select path="policyList[${policy.index}].actionQualifier">
				    				  <form:option value="FROM_START"  	label="FROM START DATE"/>
									  <form:option value="FROM_END"  	label="FROM END DATE" />
									</form:select>
								</td>
								<td class="tableEntry">
									<form:input path="policyList[${policy.index}].policyScript" size="30" maxlength="60" />
								</td>
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
              <td colspan="2" align="right">
              <input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete these policies?');" />
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>
</form:form>

</div>