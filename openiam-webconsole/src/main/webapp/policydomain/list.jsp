<!-- a list of all available menus -->

<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,diamelle.ebc.navigator.*,org.apache.struts.validator.DynaValidatorForm"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<style type="text/css">
.error {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #FF0000; text-decoration: none}
.link {	color: #111111; font-family: Verdana; font-size: 8pt;	text-decoration: none; }
a:hover {	color: #111111; font-family: verdana; font-size: 8pt; font-weight: normal; font-style: normal; 
					line-height: normal; font-variant: normal; text-transform: none; background-color: #C9CDD2;}
</style>


<%

	List policyDomainList = (List) request.getAttribute("policyDomainList");
	
%>

			
			<table align="center" width="650">
				<tr>
				<td colspan="3">
					<b>Policy Domains</b>
					
				</td>
			</tr>
			
		     <tr>
		 		   <td colspan="3" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		     </tr>  
			 <tr>
		    	  <td colspan="3">&nbsp;</td>
		     </tr>
		 
		     
			<tr class="tdheader">
				<td>Name</td>
				<td>Description</td>
				<td></td>
			</tr>		
			
			<tr class="tdlight">	
            	<td>Corporate Intranet</td>
            	<td> </td>
            	<td>VIEW </td>
            </tr>
      	     <tr>
		    	  <td colspan="3">&nbsp;</td>
		     </tr>		     
		     <tr>
		 		   <td colspan="3" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		     </tr>       	
       		<tr>
      	  		<td align="left" colspan="3">
	    				<a href="security/policyDomain.do?method=newDomain">New Policy Domain</a>					
  				</td>
			</tr>



		
  	</table>   

			