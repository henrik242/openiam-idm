<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 
  DynaValidatorForm domainForm = (DynaValidatorForm) request.getAttribute("authenticateDomainForm");

%>


<html:form action = "/policyDomainValidate.do?method=saveAuthenticate">

<table width="650" border="0" cellspacing="2" cellpadding="1" align="center">
	
		
  <tr>
       <td class="tddark" align="right">Name<font color=red>*</font></td>
       <td class="tdlight" colspan="4"> <html:text property = "name" size="30" maxlength="30" />
        </td>
    </tr>
    <tr>
       <td class="tddark" align="right">Description<font color=red>*</font></td>
       <td class="tdlight" colspan="4"> <html:textarea property = "description" cols="60" rows="5"> </html:textarea></td>
    </tr>  
    <tr>
       <td class="tddark" align="right">Challenge Method:<font color=red>*</font></td>
       <td class="tdlight" colspan="4"><select>
            							<option>Select Challenge Method</option>
       									<option>Password Service</option>
       									<option>Basic</option>
       									<option>Form</option>
      								   </select>	
        </td>
    </tr>      
    <tr>
       <td class="tddark" align="center" colspan="4">Authentication Flow</td>
    </tr>     
    <tr>
       <td class="tddark" align="Right">Step 1</td>
       <td class="tdlight" colspan="3" align="right">Add Step | Remove Step</td> 
    </tr>
    <tr>
        <td class="tddark" align="Right">Select Authentication Method</td>
        <td class="tdlight" colspan="3"> <select>
            							<option>Select Authentication Method</option>
       									<option>Password Service</option>
       									<option>Basic</option>
       									<option>Form</option>
      								   </select>	

      </td>
    </tr>    
    <tr>
       <td class="tddark" align="Right"></td>
       <td class="tddark" align="Right"><font color="orange">On Success:</font></td>
	   <td class="tddark"  colspan="2"></td>
    </tr> 
    <tr>
       <td class="tddark" align="right"></td>
       <td class="tddark" align="right">URL:</td>
		<td class="tdlight" colspan="2"><input type="radio"> <input type="text" size="40"></td>
    </tr> 
    <tr>
       <td class="tddark" align="right"></td>
       <td class="tddark" align="right">Call Next Auth Method:</td>
		<td class="tdlight" colspan="2"><input type="radio"> <input type="text" size="40"></td>
    </tr> 
    <tr>
       <td class="tddark" align="right"></td>
       <td class="tddark" align="right">Rule:</td>
		<td class="tdlight" colspan="2" valign="top"><input type="radio"> <html:textarea property = "description" cols="40" rows="8"> </html:textarea> </td>
    </tr> 
    <tr>
       <td class="tddark" align="Right"></td>
       <td class="tddark" align="Right"><font color="orange">On Failure:</font></td>
	   <td class="tddark"  colspan="2"></td>
    </tr> 
    <tr>
       <td class="tddark" align="right"></td>
       <td class="tddark" align="right">URL:</td>
		<td class="tdlight" colspan="2"><input type="radio"> <input type="text" size="40"></td>
    </tr> 
    <tr>
       <td class="tddark" align="right"></td>
       <td class="tddark" align="right">Rule:</td>
		<td class="tdlight" colspan="2" valign="top"><input type="radio"> <html:textarea property = "description" cols="40" rows="8"> </html:textarea> </td>
    </tr> 
          
    <tr>
       <td colspan="4" align ="right">
             <html:submit property="submit" value="Save"/>
             <html:reset/>

       </td>
    </tr>
    
 </table>
</html:form>
