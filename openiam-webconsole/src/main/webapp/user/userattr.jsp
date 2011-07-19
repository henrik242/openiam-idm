<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


		<table  width="850pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">User Management - Extended Attributes</h2>
						</td>
						</tr>
					</table>
				</td>
			</tr>
<% 	String msg = (String)request.getAttribute("msg");
	if (msg != null) { %> 
   <tr>
 		<td class="msg" colspan="5" align="center"  >
 		  <b><%=msg %></b>
 		</td>
  </tr> 
<% } %>
			<tr>
				<td>		
<form:form commandName="userAttrCmd">
<form:hidden path="perId" />
	<table width="850pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>EDIT ATTRIBUTES</legend>

		<table class="resourceTable" cellspacing="2" cellpadding="2" width="100%" >	
    			<tr class="header">
    				<th></td>
    				<th>NAME</td>
    				<th>VALUE</td>
    				<th>METADATA ELEMENT</td>
    				<th>ATTRIBUTE GROUP</td>
    			</tr>
    			<c:forEach items="${userAttrCmd.attributeList}" var="userAttr" varStatus="attr">
		  
				<tr>
					<td class="tableEntry"><form:checkbox path="attributeList[${attr.index}].selected" /></td>
					<td class="tableEntry">
					<form:hidden path="attributeList[${attr.index}].id" />
					<form:hidden path="attributeList[${attr.index}].userId" />
					 <c:if test="${userAttrCmd.attributeList[attr.index].id == null}" >
						<form:input path="attributeList[${attr.index}].name" size="20" maxlength="50" />
					 </c:if>
					 <c:if test="${userAttrCmd.attributeList[attr.index].id != null}" >
					 	${userAttrCmd.attributeList[attr.index].name}
					 	<form:hidden path="attributeList[${attr.index}].name" />
					 </c:if>
					</td>
					<td class="tableEntry"><form:input path="attributeList[${attr.index}].value" size="25" maxlength="50" />
					</td>
		  <c:if test="${elementAry == null}" >
				   <td class="tableEntry"></td>
		 </c:if>	
		  
		  <c:if test="${elementAry != null}" >
		    			<td class="tableEntry">	
		    			<form:select path="attributeList[${attr.index}].metadataElementId" >
            					<form:option value="" label="-Please Select-"/>
            					<form:options items="${elementAry}" itemValue="metadataElementId" itemLabel="attributeName"/>
       					</form:select>   
       					</td>
		    </c:if>
					<td class="tableEntry"><form:input path="attributeList[${attr.index}].attrGroup" size="20" maxlength="20" /></td>
				</tr>
				
			</c:forEach>
			</table>
 	</td>	
	</tr>    
   		<tr>
				<td class="buttonRow" align="right">
   					<input type="submit" name="saveBtn" value="Delete"/> <input type="submit" name="saveBtn" value="Save"/>    <input type="submit" name="_cancel" value="Cancel" />
   				</td>
   		</tr>
   
</table>
</form:form>
	</td>
 </tr>
</table>

