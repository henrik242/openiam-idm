<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<div>        
<form:form commandName="attrMapCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Managed System Attribute Map</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tddark">Managed System Id</td>
              <td class="tdlightnormal"><form:input path="managedSysId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
			  <td class="tddark">Group Type</td>
              <td class="tdlightnormal">
		              	  <form:select path="objectGroupType">
				              <form:option value="-Select a value" />
				              <form:option value="USER" />
				              <form:option value="GROUP" />
				              <form:option value="ROLE" />
			              </form:select>
			  </td>
          </tr>
          <tr>
          	<td colspan="2">
          		<table width="100%">
          			<tr>
	          			<td class="tddark">ID</td>
	          			<td class="tddark">Attribute Name</td>
	          			<td class="tddark">Is Auth. Src</td>
	          			<td class="tddark">Rule</td>
	          			<td class="tddark">Status</td>
	          			<td class="tddark">Start Date</td>
	          			<td class="tddark">End Date</td>
          			</tr>
          			         			
	          		<c:forEach items="${attrMapCmd.mapAry}" var="mapAry" varStatus="attributeMapId">
					<tr class="tdlight">
						<td> ${mapAry.attributeMapId}</td>
						<td> ${mapAry.targetAttributeName} </td>
						<td> checkbox</td>
						<td> <select></select> </td> 
						<td> </td>
						<td> </td>
						<td> </td>
					</tr>
					</c:forEach>
				</table>
				

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
              <td colspan="2" align="right"><input type="submit" name="btn" value="Delete"> <input type="submit" name="btn" value="Add Attribute">  <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
</div>