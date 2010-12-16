<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="usergrpCmd">
<form:hidden path="perId" />
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>User - Group Association</strong>
      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
   <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
			<font color="red"></font>
       </td>
    </tr>
    <tr>
    	<td>
  		<table>
    			<tr class="tdlight">
    				<td>Group Name</td>
    				<td>Inherit From Parent</td>
    				<td>Status</td>
    			</tr>
		      <c:forEach items="${usergrpCmd.groupList}" var="groupList" varStatus="group">
		  
				<tr class="plaintext">
								<td>
									<form:checkbox path="groupList[${group.index}].selected" />
									${groupList.grpName} 
									<form:hidden path="groupList[${group.index}].grpId" />
								</td>
								<td>${groupList.inheritFromParent}</td>
								<td>${groupList.status}</td>
				</tr>
				
			</c:forEach>
			</table>
	</td>	
	</tr>    
   


    <tr>
    	  <td colspan="4">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="4" align="right"> 
          <input type="submit" value="Submit"/>   
    </td>
  </tr>
  
</table>

</form:form>

