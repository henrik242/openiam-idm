<%@ page import="java.util.*,javax.servlet.http.*,diamelle.base.prop.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
String policyId = request.getParameter("policyId");

List domainList = (List)session.getAttribute("domainList");
pageContext.setAttribute("domainList", domainList);


%>

<html:form action="/security/rulesPolicyValidate.do?method=rules">


<table width="600" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="2" class="title">         
          <strong>Password Policy</strong>
      </td>     
	</tr>


   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		<html:hidden property="policyId" value="<%= policyId %>"/>
 		</td>
   </tr>
    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Assigned to Policy Domain: </td>
    	  <td class="tdlight" align="right" width="25%">
		<html:select property="secDomainId" >
        	<html:options collection="domainList" property="value" labelProperty="label"/>
     	</html:select> 
    	  </td>
    </tr>

     <tr>
    	  <td class="tddarknormal" align="right">Password history versions: </td>
    	  <td class="tdlight" align="right" width="25%">
			  <html:text property="PWD_HIST_VER" size="5"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Password expiration: </td>
    	  <td class="tdlight" align="right">
			  <html:text property="PWD_EXPIRATION" size="5"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Password length:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="PWD_LEN" size="5"/>-
			  <html:text property="PWD_LEN_MAX" size="5"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Days to password expiration warning:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="PWD_EXP_WARN" size="5"/>
    	  </td>
    </tr>


     <tr>
    	  <td class="tddark" align="center" colspan="2">Password must contain:</td>
     </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Numeric characters(Min-Max):</td>
    	  <td class="tdlight" align="right">
			  <html:text property="NUMERIC_CHARS" size="5"/> -
			  <html:text property="NUMERIC_CHARS_MAX" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Uppercase characters(Min-Max):</td>
    	  <td class="tdlight" align="right">
			  <html:text property="UPPERCASE_CHARS" size="5"/> -
			  <html:text property="UPPERCASE_CHARS_MAX" size="5"/>
			</td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Lowercase characters(Min-Max):</td>
    	  <td class="tdlight" align="right">
			  <html:text property="LOWERCASE_CHARS" size="5"/> -
			  <html:text property="LOWERCASE_CHARS_MAX" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Non-alpha numeric characters(Min-Max):</td>
    	  <td class="tdlight" align="right">
			  <html:text property="NON_ALPHA_CHARS" size="5"/> -
			  <html:text property="NON_ALPHA_CHARS_MAX" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Alpha numeric symbols(Min-Max):</td>
    	  <td class="tdlight" align="right">
			  <html:text property="ALPHA_CHARS" size="5"/> -
			  <html:text property="ALPHA_CHARS_MAX" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Number of consecutive characters allowed:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="CONSECUTIVE_CHARS" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Dictionary Check</td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="DICTIONARY_CHECK"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Reject password = Login Id?</td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="PWD_LOGIN"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Reject password = First / Last name?</td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="PWD_NAME"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Reject Password containing vowels?</td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="VOWELS_IN_PWD"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Reject passwords that begin or end with a numeric character:</td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="REJECT_NUM_START"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Password to contain numeric chars at following positions:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="HAS_NUMERIC_AT" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Password to contain alpha numeric chars at following positions:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="HAS_ALPHA_NUM_AT" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Force password to contain non-alpha numeric chars at following positions:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="FORCE_NON_ALPHA_NUM_AT" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddark" align="center" colspan="2">Self Service:</td>
     </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Number of questions to display:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="QUEST_COUNT" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Source of questions:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="QUEST_SRC" size="20"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Question List:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="QUEST_LIST" size="20"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddark" align="center" colspan="2">New User Initial Password</td>
     </tr>
     <tr>
    	  <td class="tddarknormal" align="right"><input type="radio" checked="checked"> Fixed:</td>
    	  <td class="tdlight" align="right"><input type="text" size="20" value="passwd00"/></td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right"><input type="radio"> Attribute:</td>
    	  <td class="tdlight" align="right"><input type="text" size="20"/></td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right"><input type="radio"> Rule:</td>
    	  <td class="tdlight" align="right">
			<textarea rows="4" cols="30">
			</textarea>
		  </td>
    </tr>
    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		</td>
  </tr>
    <tr>
       <td colspan="2" align ="right">
       <html:submit property="submit" value="Save"/>
       </td>
    </tr>  
 </table>

 
</html:form>
