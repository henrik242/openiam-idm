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
						<h2 class="contentheading">Metadata</h2>
				</td>
				</tr>
			</table>
	</td>
	<tr>
		<td>



<div>
<form:form commandName="metadataAttrCmd">

	<form:hidden path="element.metadataElementId"/>
	<form:hidden path="element.metadataTypeId"/>


	<table width="850pt class="bodyTable" height="100%"">
		<tr>
			<td>
					<fieldset class="userformSearch" >
					<legend>TYPE ATTRIBUTES </legend>

						<table class="fieldsetTable"  width="100%" >


				  		  <tr>
							  	<td valign="top">
							  		<table>
							  			<tr>
							  				<td><label for="username" class="attribute">Attribute List</label></td>
							  			</tr>
								          <c:forEach items="${metadataAttrCmd.elementAry}" var="elementAttr" varStatus="attr">

												<tr>
													<td> <label for="username" class="attribute"> <a href="metadataAttribute.cnt?typeId=${elementAttr.metadataTypeId}&attrId=${elementAttr.metadataElementId}&menuGroup=METADATA">${elementAttr.attributeName}</a> </label> </td>

												</tr>
										</c:forEach>
							  		</table>
							  	</td>
							  	<td valign="top">
							  		<table>
							  			<tr>
							  				<td colspan="2" class="title" valign="top"><label for="username" class="attribute">Edit Attribute Details</label></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Name</label></td>
							  				<td class="tdlight"><form:input path="element.attributeName" size="40" maxlength="40"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Label</label></td>
							  				<td class="tdlight"><form:input path="element.label" size="40" maxlength="80"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Description</label></td>
							  				<td class="tdlight"><form:input path="element.description" size="40" maxlength="40"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Minimum Length</label></td>
							  				<td class="tdlight"><form:input path="element.minLen" size="5" maxlength="5"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Maximum Length</label></td>
							  				<td class="tdlight"><form:input path="element.maxLen" size="5" maxlength="5"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Data Type</label></td>
							  				<td class="tdlight">
							  				   <form:select path="element.dataType">
							             		 	<form:option value="Text" />
							             		  	<form:option value="Integer" />
							             		  	<form:option value="Numeric" />
						          				</form:select>
							  				</td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Text Case</label></td>
							  				<td class="tdlight">
							  				   <form:select path="element.textCase">
							             		  	<form:option value="Mixed" />
							             		 	<form:option value="Upper" />
							             		  	<form:option value="Lower" />
						          				</form:select>
							  				</td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Minimum Value</label></td>
							  				<td class="tdlight"><form:input path="element.minValue" size="5" maxlength="5"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Maximum Value</label></td>
							  				<td class="tdlight"><form:input path="element.maxValue" size="5" maxlength="5"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Default Value</label></td>
							  				<td class="tdlight"><form:input path="element.defaultValue" size="40" maxlength="80"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Multi-Valued</label></td>
							  				<td class="tdlight"><form:checkbox path="element.multiValue" value="1" /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Source of Value</label><br>
							  				<i>Value List field, Service, Script</i></td>
							  				<td class="tdlight"><form:input path="element.label" size="40" maxlength="80"  /></td>
							  			</tr>
							  			<tr>
							  				<td><label for="username" class="attribute">Value List</label><br>
							  					<i>Comma separated list of values</i></td>
							  				<td class="tdlight"><form:textarea path="element.valueList" rows="4" cols="40" /></td>
							  			</tr>

							  			<tr>
							  				<td><label for="username" class="attribute">Auditable</label></td>
							  				<td class="tdlight"><form:checkbox path="element.auditable" value="1" /></td>
							  			</tr>
							  				  			<tr>
							  				<td><label for="username" class="attribute">Required</label></td>
							  				<td class="tdlight"><form:input path="element.required" size="40" maxlength="80"  /></td>
							  			</tr>

							  				  			<tr>
							  				<td><label for="username" class="attribute">UI Object Type</label></td>
							  				<td class="tdlight"><form:select path="element.textCase">
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
							  				<td><label for="username" class="attribute">UI Size</label></td>
							  				<td class="tdlight"><form:input path="element.uiSize" size="20" maxlength="20"  /></td>
							  			</tr>


							  		</table>
							  	</td>
						  </tr>



						          <tr>
						              <td><label for="username" class="attribute"><a href="metadataAttribute.cnt?menuGroup=METADATA&typeId="+ <%=request.getAttribute("typeId") %> >New</a> 			  </label></td>

						              <td class="buttonRow" align="right">
						           	  <input type="submit" name="btn" value="Delete"> <input type="submit" name="btn" value="Submit"/>

						          </tr>


	</table>

	</form:form>
	</div>