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
		        			Add Identity Provider
		        	<% } else { %>
		        			Edit Identity Provider
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
        	<td class="tddark" align="right">IdP Id<font color="red">*</font></td>
          	<td class="tdlight"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">IdP Name:</td>
          	<td class="tdlight"><input type="text" size="40"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">IdP Protocol</td>
          	<td class="tdlight">
          						<select>
          							<option>SAML 2</option>
          							<option>SAML 1.1</option>
          							<option>WS-Federation</option>
          					    </select>
         						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Authentication Provider</td>
          	<td class="tdlight">
          						<select>
          							<option>OpenIAM Default Service</option>
          					    </select>
         						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Profile</td>
          	<td class="tdlight">
          		          		<select>
          							<option>Post</option>
          							<option>Artifact</option>
          					    </select>
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Gateway Host</td>
          	<td class="tdlight"><input type="text" value="http://demo.openiam.com/" size="40" >
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Subject Qualifier</td>
          	<td class="tdlight"><input type="text" value="">
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Subject Format</td>
          	<td class="tdlight"><select>
          							<option>E-mail address</option>
          							<option>X-509</option>
          							<option>Custome</option>
          					    </select>
          					    <textarea cols="60" rows="3"></textarea>
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Gateway Transfer URL</td>
          	<td class="tdlight"><input type="text" value="fed/transfer.do" size="40">
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Error URL</td>
          	<td class="tdlight"><input type="text" value="fed/transferErr.do" size="40">
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Sign Assertion</td>
          	<td class="tdlight"><input type="checkbox"></td>
        </tr>  

		<tr>
        	<td class="tddark" align="right">Enable Federated Provisioning</td>
          	<td class="tdlight"><input type="checkbox">
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Provisioning Receiver URL</td>
          	<td class="tdlight"><input type="text" value="" size="40"></td>
        </tr> 
        
		<tr>
        	<td class="tddark" align="right">Status</td>
          	<td class="tdlight"></td>
        </tr>       
  	<tr>
   	<td colspan="2" class="tddark" align="center">Assertion Attribute Mapping</td>
    </tr> 
	<tr>
       	<td class="tddark" align="right">Attribute Name:</td>
         	<td class="tdlight">
         						<select>
         							<option> </option>
         							<option>userId</option>
         							<option>loginId</option>
         							<option>email</option>
         							<option>phone</option>
         							<option>address</option>
         							<option>Custom Attribute</option>
         					    </select>
         					     <textarea cols="60" rows="3"></textarea><br>
         					    <a href="">Add</a> | <a href="">Delete</a>
         						
         	</td>
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
	        	<input type="submit" value="Save" /> <input type="Reset">
          </td>
        </tr>

     	</table>



