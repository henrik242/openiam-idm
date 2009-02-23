<!--  adding/updating new policy -->
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.policy.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<%

String policyId = request.getParameter("policyId");

List policyTypes = (List)request.getAttribute("policyTypes");
pageContext.setAttribute("policyTypes", policyTypes);

%>



<html:form action="/security/policyValidate.do?method=savePolicy">

		<html:hidden property="policyId" />
<table width="80%" border="0" cellspacing="2" cellpadding="1">

		<tr>
			<td colspan="2" align="center" bgcolor="8397B4"><font></font></td>
		</tr>
		<tr>
			<td colspan="2"> </td>
		</tr>


		<tr>
			<td class="tddark" align="right">Policy Name<font color="red">*</font></td>
			<td class="tdlight"><html:text property="policyName" maxlength="20" size="30"/></td>
		</tr>
		<tr>
			<td class="tddark" align="right">Description</td>
			<td class="tdlight"><html:text property="description" maxlength="40" size="50"/></td>
		</tr>
		<tr>
			<td class="tddark" align="right">Type</td>
			
<% if ((policyId == null)||(policyId.length() == 0)||(policyId.equalsIgnoreCase("null"))) { %>			
			
			<td class="tdlight">
      			<html:select property="policyDefId">
           		<html:options collection="policyTypes" property="value" labelProperty="label"/>
     				</html:select> 
			</td>

<% } else { %>

			<td class="tdlight">
      			<html:select property="policyDefId" disabled="true">
           		<html:options collection="policyTypes" property="value" labelProperty="label"/>
     				</html:select> 
			</td>
<% } %>
		</tr>
         <tr>
    	    <td class="tddark" align="right">Enabled</td>
    	    <td class="tdlight" align="left">
				<html:checkbox property="enabled"/>
    	    </td>
        </tr>

         <tr>
    	    <td class="tddark" align="right">Non-editable fields:</td>
	   	    <td class="tdlight"></td>
         </tr>

        <tr>
       	    <td class="tddark" align="right">Create Date</td>
    	    <td class="tdlight" align="left">
				<html:text property="createDate" readonly="true"></html:text>
    	    </td>
        </tr>
        <tr>
       	    <td class="tddark" align="right">Created By</td>
    	    <td class="tdlight" align="left">
				<html:text property="createdBy" readonly="true"></html:text>
    	    </td>
        </tr>
        <tr>
       	    <td class="tddark" align="right">Last Update</td>
    	    <td class="tdlight" align="left">
				<html:text property="lstUpdate" readonly="true"></html:text>
    	    </td>
        </tr>
        <tr>
       	    <td class="tddark" align="right">Last Updated By</td>
    	    <td class="tdlight" align="left">
				<html:text property="lstUpdateBy" readonly="true"></html:text>
    	    </td>
        </tr>



		<tr></tr>

		<tr>
			<td colspan="2" align="center" bgcolor="8397B4"><font></font></td>
		</tr>

		<tr>
			<td align="right" colspan="2">
			<html:submit property="submit" value="Save" /> <html:reset/></td>
		</tr>
	</table>
</html:form>



