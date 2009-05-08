<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
<form:form commandName="metadataAttrCmd">
	
	<form:hidden path="metadataElementId"/>
	<form:hidden path="metadataTypeId"/>
	
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Type Attributes</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  <tr>
	  	<td valign="top">
	  		<table>
	  			<tr>
	  				<td class="title">Attribute List</td>
	  			</tr>
		          <c:forEach items="${metadataAttrCmd.elementAry}" var="elementAttr" varStatus="attr">
			     
						<tr class="tdlight">
							<td> <a href="metadataAttribute.cnt?typeId=${elementAttr.metadataTypeId}&attrId=${elementAttr.metadataElementId}&menuGroup=METADATA">${elementAttr.attributeName}</a> </td>
							
						</tr>
				</c:forEach>
	  		</table>
	  	</td>
	  	<td valign="top">
	  		<table>
	  			<tr>
	  				<td colspan="2" class="title" valign="top">Edit Attribute Details</td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Name</td>
	  				<td class="tdlight"><form:input path="attributeName" size="40" maxlength="40"  /></td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Label</td>
	  				<td class="tdlight"><form:input path="label" size="40" maxlength="80"  /></td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Description</td>
	  				<td class="tdlight"><form:input path="description" size="40" maxlength="40"  /></td>
	  			</tr>	  			
	  			<tr>
	  				<td class="tddark">Minimum Length</td>
	  				<td class="tdlight"><form:input path="description" size="5" maxlength="5"  /></td>
	  			</tr>	
	  			<tr>
	  				<td class="tddark">Maximum Length</td>
	  				<td class="tdlight"><form:input path="description" size="5" maxlength="5"  /></td>
	  			</tr>	
	  			<tr>
	  				<td class="tddark">Data Type</td>
	  				<td class="tdlight">
	  				   <form:select path="dataType">
	  				   		<form:option value="-Select-" />
	             		 	<form:option value="Text" />
	             		  	<form:option value="Integer" />
	             		  	<form:option value="Numeric" />
          				</form:select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Text Case</td>
	  				<td class="tdlight">
	  				   <form:select path="textCase">
	  				   		<form:option value="-Select-" />
	             		 	<form:option value="Upper" />
	             		  	<form:option value="Lower" />
	             		  	<form:option value="Mixed" />
          				</form:select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Minimum Value</td>
	  				<td class="tdlight"><form:input path="minValue" size="5" maxlength="5"  /></td>
	  			</tr>	
	  			<tr>
	  				<td class="tddark">Maximum Value</td>
	  				<td class="tdlight"><form:input path="maxValue" size="5" maxlength="5"  /></td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Default Value</td>
	  				<td class="tdlight"><form:input path="defaultValue" size="40" maxlength="80"  /></td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Multi-Valued</td>
	  				<td class="tdlight"><form:checkbox path="multiValue" value="1" /></td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Source of Value<br>
	  				<i>Value List field, Service, Script</i></td>
	  				<td class="tdlight"><form:input path="label" size="40" maxlength="80"  /></td>
	  			</tr>
	  			<tr>
	  				<td class="tddark">Value List<br>
	  					<i>Comma separated list of values</i></td>
	  				<td class="tdlight"><form:textarea path="valueList" rows="4" cols="40" /></td>
	  			</tr>

	  			<tr>
	  				<td class="tddark">Auditable</td>
	  				<td class="tdlight"><form:checkbox path="auditable" value="1" /></td>
	  			</tr>
	  				  			<tr>
	  				<td class="tddark">Required</td>
	  				<td class="tdlight"><form:input path="required" size="40" maxlength="80"  /></td>
	  			</tr>
	  				  			<tr>
	  				<td class="tddark">Self Editable<br>
	  				<i>Editable by the owner of the record</i></td>
	  				<td class="tdlight"><form:input path="selfEditable" size="40" maxlength="80"  /></td>
	  			</tr>
	  				  			<tr>
	  				<td class="tddark">Self Viewable<br>
	  				<i>Viewable by the owner of the record</i> </td>
	  				<td class="tdlight"><form:input path="selfViewable" size="40" maxlength="80"  /></td>
	  			</tr>
	  				  			<tr>
	  				<td class="tddark">UI Object Type</td>
	  				<td class="tdlight"><form:select path="textCase">
	  				   		<form:option value="-Select-" />
	             		 	<form:option value="TEXT" />
	             		  	<form:option value="PASSWORD" />
	             		  	<form:option value="TEXTAREA" />
	             		  	<form:option value="CHECKBOX" />
	             		  	<form:option value="SELECT" />
	             		  	<form:option value="RADIO" />
          				</form:select>

          			</td>
	  			</tr>
	  				  			<tr>
	  				<td class="tddark">UI Size</td>
	  				<td class="tdlight"><form:input path="uiSize" size="20" maxlength="20"  /></td>
	  			</tr>


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
              <td colspan="2" align="right"><input type="submit" name="btn" value="Delete">  <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
</div>