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
		        			Add Service Provider
		        	<% } else { %>
		        			Edit Service Provider
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
        	<td class="tddark" align="right">Sp Id<font color="red">*</font></td>
          	<td class="tdlight"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Sp Name</td>
          	<td class="tdlight"><input type="text" value="" size="30"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Status</td>
          	<td class="tdlight"></td>
        </tr>

		<tr>
        	<td class="tddark" align="right">Protocol</td>
          	<td class="tdlight">
          		<select>
          			<option></option>
          			<option>SAML 2</option>
          			<option>SAML 1.1</option>
					<option>SAML 1.0</option>
          			<option>WS-Federation</option>
          			<option>Kerberos</option>
					<option>OpenIAM Proprietary</option>
          		</select>
          	</td>
        </tr>        
                
 	 <tr>
       	<td class="tddark" align="right">Base URL:</td>
         	<td class="tdlight"><input type="text" size="30"></td>
     </tr>             			
 	 <tr>
       	<td class="tddark" align="right">Proxy URL:</td>
         	<td class="tdlight"><input type="text" size="30"></td>
     </tr>
	 <tr>
       	<td class="tddark" align="right">Import Certificate (URL)</td>
         	<td class="tdlight"><input type="text" size="30"></td>
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
   		<td colspan="2" class="tddark" align="center">SAML Profile</td>
    </tr>
	 <tr>
       	<td class="tddark" align="right"></td>
         	<td class="tdlight">
         		<table>
         			<tr>
         				<td>Single Sign On</td>
         				<td>Single Logout</td>
         				<td>Other</td>
         			</tr>
         			<tr>
         				<td><input type="checkbox">IdP Initiated  <a href="">Configure</a><br>
         				<input type="checkbox">SP Initiated <a href="">Configure</a></td>
         				<td><input type="checkbox">IdP Initiated <a href="">Configure</a><br>
         				<input type="checkbox">SP Initiated <a href="">Configure</a></td>
         				<td><input type="checkbox">Attribute Query <a href="">Configure</a></td>
         			</tr>
         		</table>
         	</td>
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
   	<td colspan="2" class="tddark" align="center">Organization Information</td>
    </tr>
	 <tr>
       	<td class="tddark" align="right">Organization Name:</td>
         	<td class="tdlight"><input type="text" size="30"></td>
     </tr>             			
	 <tr>
       	<td class="tddark" align="right">Contact:</td>
         	<td class="tdlight"><input type="text" size="30"></td>
     </tr>
	 <tr>
       	<td class="tddark" align="right">Contact Phone:</td>
         	<td class="tdlight"><input type="text" size="30"></td>
     </tr>
	 <tr>
       	<td class="tddark" align="right">Contact E-Mail:</td>
         	<td class="tdlight"><input type="text" size="30"></td>
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



