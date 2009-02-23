<%@ page import="java.util.*,javax.servlet.http.*,diamelle.base.prop.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<html:form action="/security/policyAttr.do?method=saveAttribs">

<table width="100%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="2" class="title">         
          <strong>Policy Rules</strong>
      </td>     
	</tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
   </tr>
    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Password length:</td>
    	  <td class="tdlight" align="right">
			  <html:text property="PWD_LEN" size="15"/>-
			  <html:text property="PWD_LEN" size="15"/>
    	  </td>
    </tr>

	<tr>
	  <td>&nbsp;</td>
 	</tr>
 		
 	<logic:iterate id="policyAttr" name="policyAttrForm" property="policyAttr">
		<tr>
	 	  	<td class="tddarknormal">
		    	<html:hidden name="policyAttr" property="policyAttrId" indexed="true"/>
	 	    	<bean:write name="question" property="questionText" />
	    	</td>
	    </tr>
	    <tr>
	 	  	<td class="tdlightnormal">
		    	<html:text name="question" property="ansText" indexed="true" size="60"/>
	    	</td>
 		</tr>
 	</logic:iterate>

    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		</td>
  </tr>
    <tr>
       <td colspan="2" align ="right"><input type="submit" value="Save"> <input type="reset">

       </td>
    </tr>  
 </table>

 
</html:form>
