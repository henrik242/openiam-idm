<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="diamelle.base.composite.*,diamelle.security.auth.*,java.util.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

<script language="JavaScript"> 
<!-- 
function refreshParent() { 
  document.forms[0].target="parentwindow";
  //window.opener.location.href = window.opener.location.href; 
  if (window.opener.progressWindow) 
    window.opener.progressWindow.close() 
  window.close(); 
} 



//--> 
</script> 

<body topmargin=0 leftmargin=0>

<%
  String mode = (String)request.getAttribute("mode");
  String personId = (String)request.getAttribute("personId");
  String detail =  (String)request.getAttribute("activetab");

  String errorString = (String)request.getAttribute("errormessage");

  LoginValue lv = new LoginValue();


  if (request.getAttribute("loginValue") != null)
    lv = (LoginValue) request.getAttribute("loginValue");

  if (lv.getPassword() == null )
    lv.setPassword("");

  if (lv.getPasswordEquivalentToken() == null )
    lv.setPasswordEquivalentToken("");

%>



<html:form action="idman/userIdentity.do?method=saveIdentity">
<input type=hidden name="mode" value="<%=mode%>" >
<input type=hidden name="detail" value="<%=detail%>" >

<div align="center">
<table border="0" cellpadding="2" cellspacing="1">
  <tr>
    <td class="title">
      Login details
    </td>
    <td align="right" class="text">
      <font color=red>*</font>Required
    </td>
  </tr>
  <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
  <tr>
	  <td>&nbsp;</td>
 </tr>

  <% if (errorString != null) {%>
 
          <%=errorString%>
      
  <% }%>

        <tr>
          <td class="tddark" align="right" >
             Login:<font color="red"> * </font>
          </td>
  
          <td class="tdlight">
              <% if (lv != null && lv.getLogin() != null) {%>
                 <input type="text" name = "id" value="<%=lv.getLogin()%>" readonly="true" />
              <% } else { %>
                 <html:text property="id"  maxlength="20"/>
              <% } %>
          </td>
         </tr>
         <tr>
           <td class="tddark" align="right" >
              Security Domain:<font color="#FF0000">*</font>
           </td>
           <td class="tdlight">
               <% if (lv != null && lv.getService() != null) {%>
                     <input type="text" name = "serviceId" value="<%=lv.getService()%>" readonly="true" />
               <% } else {%>
                     <select name ="serviceId">
                       <%
                        Map serviceList = (Map) request.getAttribute("serviceList");
                        if (serviceList != null && !serviceList.isEmpty()) {
                            Iterator serviceItr = serviceList.keySet().iterator();
                            Iterator Iter = serviceList.values().iterator();

                            while (serviceItr.hasNext()&& Iter.hasNext()) {
                            String serviceId = (String) serviceItr.next();
                            String serviceName = (String) Iter.next();
                       %>
                        <option value="<%=serviceId%>"><%=serviceName%></option>
                      <%}%>
                    </select>
           </td>
                <%}}%>
         </tr>

         <tr>
              <td class="tddark" align="right" >Password:</td>
              <td class="tdlight"><input type="password" name="password"  value="<%=lv.getPassword()%>" maxlength="20" size="20" ></td>
         </tr>
 
         <tr>
              <td class="tddark" align="right" >
                  Password Expiry:
              </td>
              <td class="tdlight">
                <% if (lv != null && lv.getPasswordExp() != null ) { %>
                  <input type="text" name="pwdExp" value="<%=lv.getPasswordExp().toString()%>" maxlength="20" size="20" >
                <%} else {%>
                   <input type="text" name="pwdExp" maxlength="20" size="20" >
                <%}%>
              </td>
         </tr>
         
         <tr>
              <td class="tddark" align="right" >Password Token:</td>
              <td class="tdlight"><input type="text" name="pwdToken"  value="<%=lv.getPasswordEquivalentToken()%>" maxlength="20" size="20" ></td>
         </tr>

         <tr>
              <td class="tddark" align="right" >User Id:</td>
              <td class="tdlight"><%=personId%>
                 <input name="personId" type="hidden" value="<%=personId%>" size="20" maxlength="20" readonly="true" > </td>
         </tr>
         
         <tr>
		    	  <td>&nbsp;</td>
		    </tr>
		 
		    <tr>
		 		   <td colspan="4" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		    </tr>
    
         <tr>
              <td  colspan="2" align="right">
                 <% if (lv != null && lv.getLogin() != null) {%>
                    <input name="submit" value="Save" type="submit" onclick="refreshParent()"/> 

                 <% } else { %>
                    <input name ="submit" value ="Add" type="submit" onclick="refreshParent()">
                 <% } %>
                 &nbsp;
                 <input name="reset" type="reset">
 							</td>
          </tr>
      
</table>

</html:form>
 
</body>
