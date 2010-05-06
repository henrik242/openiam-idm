<!-- adding new resource --><!-- Javascript method for calling a PopupPage -->
<%@page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.openiam.webadmin.busdel.base.*"%>
<%@page import="java.util.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


	<SCRIPT language="javascript" src="js/date-picker.js"></SCRIPT>

	 
	<script language="Javascript">
		<!--
 			function open_url(URL) {
   			p_item = arguments[0];
   			vWinCal = window.open(URL,"Window","width=350,height=400,status=nos,resizable=no,top=100,left=350,scrollbars=yes");
   			vWinCal.opener = self;
   			ggWinCal = vWinCal;
 			}
 		//-->
	</script>
	

<% 
  DynaValidatorForm serviceForm = (DynaValidatorForm) request.getAttribute("serviceForm");
  String mode = (String) serviceForm.get("mode");
  
  	List statusList = (List)session.getAttribute("serviceStatus");
	pageContext.setAttribute("serviceStatus",statusList);

  	List typeList = (List)session.getAttribute("serviceType");
	pageContext.setAttribute("serviceType",typeList);	

  	List companyList = (List)session.getAttribute("companyList");
	pageContext.setAttribute("companyList",companyList);	

	// the type of communication options.
  	List comTypeList = (List)session.getAttribute("communicationOptions");
	pageContext.setAttribute("communicationOptions" , comTypeList);	
	
%>
		<html:form action = "/maint/service.do?method=saveService">
			<table cellspacing=2 cellpadding=2  width="700" align="center">
				<html:hidden property="mode" value="<%=mode%>" />

			<tr class="title">
    			<td colspan="3">Service Details</td>
			</tr>	
			  <tr>
 				<td colspan="3" bgcolor="8397B4" >
 		  		<font></font>
 				</td>
  			</tr>	
  			<tr>
	  			<td colspan="3">&nbsp;</td>
 			</tr>
				
				 
		<tr class="tdlight">
        	<td align="right">Service Id</td>
          	<td>
        	<% if (mode.equalsIgnoreCase("edit")) { %>  	
          	<html:text property="serviceId"  maxlength="20" readonly="true"/>
        	<% } else { %>
        	<html:text property="serviceId"  maxlength="20" readonly="false"/>
        	<% } %>
        	</td>
        </tr>
        
        <tr class="tdlight">
        	<td align="right">Service Name<font color="red">*</font>
        	<td><html:text property="serviceName" maxlength="40" size="40"/></td>
        </tr>
        <tr class="tdlight">
        	<td align="right">HOST / IP<font color="red">*</font>:
        	<td><html:text property="locationIpAddress" maxlength="255" size="40" /></td>
        </tr>

        <tr class="tdlight">
        	<td align="right">Connector:<font color="red">*</font>:
        	<td>
        	    <html:select property="serviceType">
           			<html:options collection="serviceType" property="value" labelProperty="label"/>
     			</html:select> 
        	</td>
        </tr>
         <tr class="tdlight">
        	<td align="right">Base AD Container: </td>
        	<td><html:text property="baseContainer" maxlength="40" size="40" />
        	</td>
        </tr>
        
        <tr class="tdlight">
        	<td align="right">Directory Domain: </td>
        	<td><html:text property="directoryDomain" maxlength="40" size="40" />
        	</td>
        </tr>

        <tr class="tdlight">
        	<td align="right">Domain DNS Name: </td>
        	<td><html:text property="dnsDomainName" maxlength="40" size="40" />
        	</td>
        </tr>

        <tr class="tdlight">
        	<td align="right">Authenticate User Id / Password:</td>
        	<td>
        	   <html:text property="dirAuthUserId" maxlength="100" size="45" /><br>
        	   <html:password property="dirAuthUserPassword" maxlength="100" size="45" />
        	</td>
        </tr>
         <tr class="tdlight">
        	<td align="right">Communication:
        	<td>
        	    <html:select property="commType">
           			<html:options collection="communicationOptions" property="value" labelProperty="label"/>
     			</html:select> 
        	

        	</td>
        </tr>
         <tr class="tdlight">
        	<td align="right">Certificate:
        	<td><html:text property="licenseKey" maxlength="32" size="40" readonly="true"/><input type="submit" value="Gen Cert"> <input type="submit" value="Import Cert">
        	</td>
        </tr>
         <tr class="tdlight">
        	<td align="right">Subscribe to Events:
        	<td><input type="checkbox">Create - Custom Hander: <input type="text" size="30"><br>
        		<input type="checkbox">Update - Custom Hander: <input type="text" size="30"><br>
        		<input type="checkbox">Delete - Custom Hander: <input type="text" size="30"><br>
        	</td>
        </tr>
        <tr class="tdlight">
        	<td align="right">Current Application Status
        	<td><%=JSPUtil.display( serviceForm.get("status") )%> 
        	<html:hidden property="status" />
        	</td>
        </tr>
        <tr class="tdlight">
        	<td align="right">Service Started On:
        	<td> <%=serviceForm.get("startDate")%>	
        		<html:hidden property="startDate" /> 
        	</td>
        </tr>
        <tr class="tdlight">
        	<td align="right">Service Ended On:
        	<td><%=serviceForm.get("endDate")%>
        		<html:hidden property="endDate" /> 
        	</td>
        </tr>
 
        <tr class="tdlight">
        	<td align="right">Company:
        	<td>
        		<html:select property="companyOwnerId">
        		<html:options collection="companyList" property="value" labelProperty="label"/>
        	  </html:select>  
        	</td>
        </tr>

        <tr class="tdlight">
        	<td align="right">Policy Domain: 
        	<td><html:text property="parentServiceId" maxlength="20" /></td>
        </tr>
 
        <tr>
    	  <td colspan="2">&nbsp;</td>
	   </tr>
			 
	    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
	    </tr>      
     
        <tr>
	        <td align="right" colspan="2">
	        <html:submit property="submit" value="Save"/> 
	        <!-- 
	        <html:submit property="submit" value="Synchronize Resources"/> 
	        -->
	        <html:reset />
          </td>
        </tr>
     	</table>
		</html:form>
		
		<table cellspacing=2 cellpadding=2  width="700" align="center">
			<tr>
			<td align="right">
				        <% if (mode.equalsIgnoreCase("edit")) { %> 
				        <html:form action = "/maint/service.do?method=testConnection">
			          	<html:submit property="submit" value="Test Connection" />
			          	<html:hidden property="serviceId" />
			          	</html:form>
        				<% }  %>
			
			</td>
			</tr>
		</table>
