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
			  <td class="tddark">Attribute Group</td>
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
	          			<td class="tddark">Attribute Name</td>
	          			<td class="tddark">Is Auth. Src</td>
	          			<td class="tddark">Rule</td>
	          			<td class="tddark">Status</td>
          			</tr>
          			         	
          			<c:forEach items="${attrMapCmd.attrMapAry}" var="sysAttr" varStatus="attr">
	     
						<tr class="tdlight">
							<td> ${sysAttr.targetAttributeName} </td>
							<td> <form:checkbox path="attrMapAry[${attr.index}].authoritativeSrc" /> </td>
							<td> 
								<form:select path="attrMapAry[${attr.index}].rule" >
	              					<form:option value="-" label="-Please Select-"/>
	              					<form:options items="${attrPolicyAry}" itemValue="policyId" itemLabel="name"/>
	         					</form:select>   
							 </td>
							 <td> <form:select path="attrMapAry[${attr.index}].rule">
       				              <form:option value="-" label="-Please Select-"/>
       				              <form:option value="ACTIVE" label="ACTIVE"/>
       				              <form:option value="IN-ACTIVE" label="IN-ACTIVE"/>
          					  </form:select>
          					</td>

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
              <td colspan="2" align="right"> <input type="submit" name="btn" value="Save"> <input type="reset" name="btn" value="Reset"> </td>
          </tr>
</table>

</form:form>
</div>