 <!--  adding/updating menu-->

<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<link href="diamelleapp.css" rel="stylesheet" type="text/css">

<head>
	<script language="JavaScript"> 
		<!-- 
			function refreshParent(url, form) { 
        document.forms[0].target="parentwindow";
			  queryString = 'menu='+ form.menu.value + '&parent='+ form.parent.value; 
			  window.opener.location.href = url + '&' + queryString; 
			  if (window.opener.progressWindow) 
			    window.opener.progressWindow.close() 
			  window.close(); 
			} 

function refreshParent2() {
  window.opener.location.href = window.opener.location.href;
  if (window.opener.progressWindow) {
    window.opener.progressWindow.close()
  }  
  window.close();
}

function checkState(url, closeSelf) {
	if (menuForm.savedStatus.value == '1') {
		window.close();
	}
}
		//--> 
	</script> 
</head>


<% 
	String menuGroup = (String) request.getAttribute("menuGroup");
	DynaValidatorForm menuForm = (DynaValidatorForm) request.getAttribute("menuForm");
  	String mode = (String) menuForm.get("mode"); 
  	String parent = request.getParameter("parent");
  	String savedStatus = (String)request.getAttribute("saved");
  
%>

	<body onload="checkState('user.do?method=history',true);" 
		  onunload='window.opener.location.href = window.opener.location.href' >

		<html:form action = "menu.do?method=saveMenu">
		
			<table cellspacing=2 cellpadding=2  width="100%" align="center">
				<html:hidden property="mode" value="<%=mode%>" />
				<html:hidden property="parent" value="<%=parent%>"/>		
				<input type="hidden" name="savedStatus" value="<%=savedStatus%>">
			
				<tr >
					<td class="title" >
						<%if(mode.equalsIgnoreCase("add")) {%>
							Add Menu
						<% } else {%>
							Edit Menu
						<%}%>
        	</td>
        	<td class="text" align="right">         
             <font size="1" color="red">*</font> Required        
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
  		  				
				<tr >
        	<td class="tddark" align="right">Menu Id</td>
          <td class="tdlight"><html:text property="menu" maxlength="20"/></td>
        </tr>

				<tr >
        	<td class="tddark" align="right">Menu Name<font color="red">*</font></td>
          <td class="tdlight"><html:text property="menuName" size="50" maxlength="50"/></td>
        </tr>
        
				<tr >
        	<td class="tddark" align="right">Language Code<font color="red">*</font></td>
          <td class="tdlight"><html:text property="languageCd" maxlength="20" readonly="true"/></td>
        </tr>  
        
        <tr >
        	<td class="tddark" align="right">Menu Group</td>
          <td class="tdlight"><html:text property="menuGroup" value="<%=menuGroup%>" maxlength="20" readonly="true"/></td>
        </tr>  
        
        <tr >
        	<td class="tddark" align="right">Menu Description</td>
          <td class="tdlight"><html:text property="menuDesc" size="50" maxlength="50"/></td>
        </tr>  
        
        <tr >
        	<td class="tddark" align="right">URL / Menu Action</td>
          <td class="tdlight"><html:text property="mainUrl" size="50"
				maxlength="100" /></td>
        </tr>  
        
        <tr >
        	<td class="tddark" align="right">Active</td>
          <td class="tdlight"><html:checkbox property="active" /></td>
        </tr>  
        
        
         <tr>
		    	  <td colspan="2">&nbsp;</td>
		     </tr>
		 
		   
		     <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		     </tr>  
                       
        <tr >
	        <td align="right" colspan="2">
	        	<html:submit property="submit" value="Save" />
          </td>
        </tr>

     	</table>
     
		</html:form>
	</body>

