<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<html:form action = "/auditDomainValidate.do?method=saveAudit">

<table width="600" border="0" cellspacing="2" cellpadding="1" align="center">
	
		
    <tr>
       <td class="tdlight" align="right">Parameters to Capture in Log:</td>
       <td class="tdlight"> 
       		<select multiple size="5">
       			<option value="Login">Login</option>
       			<option value="userId">userId</option>
       		</select>
        </td>
    </tr>
    <tr>
       <td class="tdlight" align="right" valign="middle">Audit Events:</td>
       <td class="tdlight">
       	<table border="0" cellspacing="5">
       		<tr>
       			<td>
		<html:checkbox property="Auth_Success">Authentication Success</html:checkbox><br>
       	<html:checkbox property="Auth_Fail">Authentication Fail</html:checkbox><br>
       	<html:checkbox property="Authorization_Success">Authorization Success</html:checkbox><br>
       	<html:checkbox property="Authorization_Fail">Authorization Fail</html:checkbox>
       			</td>
       			<td>
       	<html:checkbox property="Object_Create">Object Create</html:checkbox><br>
       	<html:checkbox property="Object_Update">Object Update</html:checkbox><br>
       	<html:checkbox property="Object_Delete">Object Delete</html:checkbox>
       			</td>
       		</tr>
       	</table>

        </td>
    </tr>
    <tr>
       <td class="tdlight" align="right" valign="middle">Audit Record Format:</td>
       <td class="tdlight"> 
       		<html:textarea property = "recordFormat" cols="80" rows="8"> </html:textarea>
        </td>
    </tr>
    <tr>
       <td align="right" colspan="2">
             <html:submit property="submit" value="Save"/>
             <html:reset/>

       </td>
    </tr>
</table>

</html:form>


