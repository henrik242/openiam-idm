
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 
  DynaValidatorForm domainForm = (DynaValidatorForm) request.getAttribute("policyDomainForm");

%>





<html:form action = "/policyDomainValidate.do?method=save">



<table width="100%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="2" class="title">         
          <strong>Policy Domain Details</strong>

      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 </tr>
  <tr>
       <td class="tddark" align="right">Name<font color=red>*</font></td>
       <td class="tdlight"> <html:text property = "name" size="30" maxlength="30" />
        <html:hidden property = "id"  />
        </td>
    </tr>
    <tr>
       <td class="tddark" align="right">Description<font color=red>*</font></td>
       <td class="tdlight"> <html:textarea property = "description" cols="60" rows="5"> </html:textarea></td>
    </tr>  
     
   

    
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="3" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>
  
    <tr >
       <td colspan="3" align ="right">
             <html:submit property="submit" value="Save"/>
             <html:reset/>

       </td>
    </tr>
    
 </table>
</html:form>

<jsp:include flush="true" page="domaintabpane.jsp"></jsp:include>



