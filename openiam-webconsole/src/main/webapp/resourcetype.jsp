<!-- adding new resource -->
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*"%>
<%@ page import="java.util.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
			DynaValidatorForm resourceTypeForm = (DynaValidatorForm) request.getAttribute("resourceTypeForm");
			String mode = (String) resourceTypeForm.get("mode");
%>
		<br><br>
		<html:errors/>
		<html:form action = "/security/resourcetype.do?method=saveResourceTypes">
			<table cellspacing=2 cellpadding=2  width="700px" align="center">				
				<html:hidden property="mode" value="<%=mode%>" />
	

  		
				<tr>
        	<td colspan="3" class="title">
           <%if(mode.equalsIgnoreCase("add")) { %> 
           		&nbsp;<STRONG> Create Resource Type</STRONG> 
           	<%} else {%> Resource Type Details for <%=(String) request.getAttribute("description")%>
           	<%}%> </td>
				</tr>
		   
		   <tr>
		 		<td colspan="3" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr>
				
				
			<tr>
			  <td>&nbsp;</td>
		 </tr>
				 
				<tr>
        	<td class="tddark" align="right">Resource Type Id</td>
          <td class="tdlight" colspan="2"><html:text property="resourceTypeId" maxlength="20"/></td>
        </tr>

        <tr>
        	<td class="tddark" align="right">Service Type:<font color="red">*</font></td>
            <td class="tdlight" colspan="2"><select>
            	<option></option>
          		<option>Windows 2003 Server</option>
          		<option>HP UX Server</option>
          		<option>Red Hat Linux</option>
          	  </select>
          	  </td>
        </tr>        
        <tr>
        	<td class="tddark" align="right">Type Description <font color="red">*</font></td>
          <td class="tdlight" colspan="2"><html:text property="description" maxlength="25" /></td>
        </tr>
        <tr>
        	<td class="tddark" align="right">Type Attribute:</td>
        	<td class="tdlight"> <input type="text" size="20"></td>
          	<td class="tdlight"> Value: <html:text property="description" maxlength="30" size="30" /> <input type="button" value="Add Attribute"></td>
        </tr>
        <tr>
        	<td class="tddark" align="right">Resource Connector:<font color="red"></font></td>
            <td class="tdlight" colspan="2"><select>
            	<option></option>
          		<option>Windows 2003 Server</option>
          		<option>HP UX Server</option>
          		<option>Red Hat Linux</option>
          	  </select>
          	  </td>
        </tr> 
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="3" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>        
                         
        <tr>
	        <td align="right" colspan="3">
	        	<html:submit property="submit" value="Save"/> 
	        	<html:submit property="submit" value="Reset"/>
          </td>
        </tr>
     	</table>
     
		</html:form>
