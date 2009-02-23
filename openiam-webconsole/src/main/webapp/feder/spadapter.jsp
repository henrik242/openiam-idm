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

%>

<br>
			<table cellspacing=2 cellpadding=2  width="650" align="center">
  			<tr>
	        	<td class="title" colspan="2">
		        	<% if(operationMode.equalsIgnoreCase("add"))
		        	 { %>
		        			Add SP Handler
		        	<% } else { %>
		        			Edit SP Handler
		        	<% } %>
	        	</td>
			</tr>
				
			<tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font> </font>
		 		   </td>
		    </tr> 
		      
		    <tr>
		 		   <td colspan="2">&nbsp;</td>
		    </tr> 
  		  				
		<tr>
        	<td class="tddark" align="right">Sp Handler Id<font color="red">*</font></td>
          	<td class="tdlight"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Sp Handler Name</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Token Method</td>
          	<td class="tdlight">
          		<select>
          			<option></option>
          			<option>Query Parameter</option>
          			<option>Cookie</option>
          		</select>
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Token Container Name</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Domain Name</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr>		                
		<tr>
        	<td class="tddark" align="right">Cookie Path</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr>	        			
		<tr>
        	<td class="tddark" align="right">Encode Cookie</td>
          	<td class="tdlight"><input type="checkbox"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Cookie Life</td>
          	<td class="tdlight"><input type="checkbox"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Encryption Seed</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr>        
		<tr>
        	<td class="tddark" align="right">Encryption Algorightm</td>
          	<td class="tdlight">
          		<select>
          			<option></option>
          			<option>AES</option>
          			<option>3DES</option>
          		</select>
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Authentication Service URL</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr> 
		<tr>
        	<td class="tddark" align="right">Logout Service URL</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr> 
                        
        	<tr>
		    	  <td colspan="2">&nbsp;</td>
		    </tr>
		 
		    <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr>  
                       
        <tr >
	        <td align="right" colspan="2">
	        	<input type="submit" value="Save" /> <input type="Reset">
          </td>
        </tr>

     	</table>



