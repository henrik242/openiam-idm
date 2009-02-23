<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.action.*,org.apache.struts.validator.*,diamelle.app.base.*,diamelle.base.composite.*,diamelle.security.token.*" %>
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>


<SCRIPT LANGUAGE="JavaScript"> 
<!-- Begin 

function loadParent(url, closeSelf){
	self.opener.location = url;
	if(closeSelf) self.close();
}
function checkState(url, closeSelf) {
	if (userTokenForm.savedStatus.value == '1') {
		loadParent(url,closeSelf);
	}
}

//  End --> 

</script>  

 <%
 	DynaActionForm form = (DynaActionForm)request.getAttribute("userTokenForm");
 	
 	List hpList = (List)session.getAttribute("hpList");
	pageContext.setAttribute("hpList", hpList);	
	String personId = (String)request.getAttribute("personId");
	String savedStatus = (String)request.getAttribute("saved");

 %>
 
<html:html>
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

<body onload="checkState('user.do?method=token',true);" >

<html:form action="/userToken.do?method=saveRequest">

  <html:hidden property="personId" value="<%=personId%>"/>
  <html:hidden property="userRequestId" />
  <html:hidden property="reqDate" />
  <html:hidden property="assignDate" />
  <input type="hidden" name="savedStatus" value="<%=savedStatus%>">

	<table align="center" width="98%" border="0" cellspacing="1" cellpadding="1">
	<tr>
		<td colspan="2">
			<font color="red">
		    <logic:messagesPresent>        
		         <html:messages id="error" header="errors.header">
		            <li><bean:write name="error"/></li>
		         </html:messages>    
		     </logic:messagesPresent>
		     </font> 
		</td>
	</tr>
	<tr>
		<td class="title" colspan="2">Token Request Information</td>
	</tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		</td>
  </tr>	
	
	<tr>
		<td class="tddark" align="right" width="20%">HP Model:</td>
		<td class="tdlight" width="20%">
			<html:select property="hpModel">
				<html:options collection="hpList" property="value" labelProperty="label"/>
			</html:select>
		</td>	
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">New HP Model?</td>
		<td class="tdlight" width="20%"><html:text property="newHpModel"/>
		</td>	
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">HP Serial Nbr:*</td>
		<td class="tdlight" width="20%"><html:text property="hpSerialNbr"/></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">HP Phone Number:*</td>
		<td class="tdlight" width="20%"><html:text property="phoneNumber"/></td>
	</tr>
	<tr>
		<td class="tddark" align="right" width="20%">New Token Request:</td>
		<td class="tdlight" width="20%"><html:checkbox property="newTokenReq"/></td>
	</tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		</td>
  </tr>	
	<tr>
		<td class="tdlight" width="20%" colspan="2" align="right">
			<html:submit/> <html:reset/></td>
	</tr>

</table>
</html:form>

</body>
</html:html>
