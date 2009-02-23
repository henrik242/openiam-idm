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
          	<td class="tdlight"><input type="text" size="30"></td>
        </tr>
		<tr>
        	<td class="tddark" align="right">IdP Protocol</td>
          	<td class="tdlight">
          						<select>
          							<option>SAML 2</option>
          							<option>SAML 1.1</option>
          							<option>WS-Federation</option>
          							<option>Kerberos</option>
          							<option>SUN AM</option>
          							<option>Oracle AM</option>
          					    </select>
         						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">IdP Handler</td>
          	<td class="tdlight">
          						<select>
          							<option>Java J2EE</option>
          					    </select>
          					    <a href="home.jsp?bodyjsp=/feder/spadapter.jsp">Edit</a>
          						
          	</td>
        </tr>
		<tr>
        	<td class="tddark" align="right">Status</td>
          	<td class="tdlight"></td>
        </tr>       
  	<tr>
   	<td colspan="2" class="tddark" align="center">Adapter Contract</td>
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
         					    <input type="text" size="30">
         					    <a href="">Add</a> | <a href="">Edit</a> | <a href="">Delete</a>
         						
         	</td>
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
         					    <input type="text" size="30">
         					    <a href="">Add</a> | <a href="">Edit</a> | <a href="">Delete</a>
         						
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



