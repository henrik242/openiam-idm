<!--  adding/updating new group -->
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
	Map serviceMap = (Map)request.getAttribute("services");
	String operationMode = (String)request.getAttribute("mode");
	if (operationMode == null) {
		operationMode = "add";
	}

	List refGroupList = (List)request.getAttribute("refGroupList");
	pageContext.setAttribute("refGroupList", refGroupList);
%>

	<body>
		
		<br><br>
			<table cellspacing=2 cellpadding=2  width="400" align="center">
				<html:form action = "/security/groupValidate.do?method=saveGroup" >
				<html:hidden property="grpId" />
				<html:hidden property="mode" /> 
  			<tr >
        	<td class="title" colspan="2">
	        	<% if(operationMode.equalsIgnoreCase("add"))
	        	 { %>
	        			Add Group
	        	<% } else { %>
	        			Edit Group
	        	<% } %>
        	</td>
				</tr>
				
				<tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font> </font>
		 		   </td>
		    </tr> 
		      
		    <tr>
		 		   <td>&nbsp;</td>
		    </tr> 
  		  				
		<tr>
        	<td class="tddark" align="right">Group Name<font color="red">*</font></td>
          	<td class="tdlight"><html:text property="groupName" maxlength="20" size="40"/></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Group Description</td>
          	<td class="tdlight"><html:text property="description" maxlength="40" size="40"/></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Group Class</td>
          	<td class="tdlight"><html:text property="groupClass" maxlength="40" size="40"/></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Status</td>
          	<td class="tdlight"></td>
        </tr>        
         <tr>
        	<td class="tddark" align="right">Parent Group</td>
          	<td class="tdlight">
              <html:select property="parentGroup" >
        		<html:options collection="refGroupList" property="value" labelProperty="label"/>
        	 </html:select> 
        	 </td>
        </tr>               
		<tr >
        	<td class="tddark" align="right"></td>
          	<td class="tdlight"><html:checkbox property="inheritFromParent">Inherit from Parent</html:checkbox>
          	</td>
        </tr>
        <tr >
        	<td class="tddark" align="right">Provisioning Method:</td>
          	<td class="tdlight">
          			<html:checkbox property="provMethodEvent">EVENT</html:checkbox><BR>
          			<html:checkbox property="provMethodProcess">PROCESS</html:checkbox><BR>
          	</TD>
        </tr>
		<tr>
        	<td class="tddark" align="right">Process Name</td>
          	<td class="tdlight"><html:text property="provProcessName" maxlength="20" size="40"/></td>
        </tr>          			

        <tr>
		    	  <td>&nbsp;</td>
		    </tr>
		 
		    <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr>  
                       
        <tr >
	        <td align="right" colspan="2">
	        	<html:submit property="submit" value="Save"/>
          </td>
        </tr>

     	</table>
    </html:form> 

	</body>
