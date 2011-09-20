<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<table  width="800pt" >
	<tr>
		<td>
			<table width="100%">
				<tr>
					<td class="pageTitle" width="70%">
						<h2 class="contentheading">Metadata</h2>
				</td>
				</tr>
			</table>
	</td>
		<tr>
		<td>

<form:form commandName="metadataTypeListCmd">

<table width="600pt">
		<tr>
			<td align="center" height="100%">
		     <fieldset class="userform" >
					<legend>METADATA TYPES</legend>

<table class="fieldsetTable"  width="600pt" >

     <tr>
		<td class="plaintext">Select Type Category:</td>
         <td>

           <form:select path="categoryId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${categoryAry}" itemValue="categoryId" itemLabel="categoryName"/>
          </form:select>

         </td>
  	</tr>
         <tr>
    	  <td></td>
    	  <td class="error"><form:errors path="categoryId" /></td>
    	</tr>
	</table>


          <tr class="buttonRow">
              <td align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>

</form:form>

<c:if test="${searchResults != null}" >


<table width="600pt">
	<tr>
		<td align="center" height="100%">
	     <fieldset class="userform" >
				<legend>METADATA TYPE LIST - ${searchResults} Type(s) found.</legend>



		<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">
		    <tr class="header">
			  	<th>Type ID</td>
		        <th>Description</td>
				<th>Status</td>
		    </tr>


	<c:forEach items="${typeAry}" var="metadataType">
		<tr>

			<td class="tableEntry"><a href="metadataType.cnt?typeId=${metadataType.metadataTypeId}&menuGroup=METADATA"> ${metadataType.metadataTypeId}</a></td>
			<td class="tableEntry"> ${managedSys.description}</td>
			<td class="tableEntry"> ${ManagedSys.active}</td>
		</tr>
	</c:forEach>
	</table>
</td>
</tr>

          <tr>
              <td colspan="5" align="left"> <a href="metadataType.cnt?typeId=NEW">New Metadata</a></td>
          </tr>


</table>

</c:if>