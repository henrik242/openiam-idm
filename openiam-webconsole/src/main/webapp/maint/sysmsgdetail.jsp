<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="sysMsgCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>System Message Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>  

           <tr>
			  <td class="tddark">Name<font color="red">*</font></td>
              <td class="tdlightnormal">
               <form:hidden path="msg.msgId"  />
               <form:input path="msg.name" size="40" maxlength="40" /></td>
          </tr>
            <tr>
              <td class="tddark">Deliver Through</td>
			  <td class="tdlightnormal">
			  	 <form:select path="msg.deliverBy">
				  <form:option value="" label="-Please Select-"  />
	              <form:option value="EMAIL" label="EMAIL" />
				  <form:option value="SELF-SERVICE" label="SELF-SERVICE" />
          		</form:select>
			  </td>
		  </tr>
            <tr>
              <td class="tddark">Audience Type</td>
			  <td class="tdlightnormal">
			  	 <form:select path="msg.targetAudienceType">
				  <form:option value="" label="-Please Select-"  />
	              <form:option value="ALL" label="ALL" />
				  <form:option value="GROUP" label="GROUP" />
				  <form:option value="DEPT" label="DEPARTMENT" />
				   <form:option value="DIV" label="DIVISION" />
          		</form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark">Audience</td>
			  <td class="tdlightnormal"><form:input path="msg.targetAudience" size="40" maxlength="100"  /></td>
		  </tr>
           <tr>
			  <td class="tddark">Start Date</td>
              <td class="tdlightnormal" ><form:input path="msg.startDate" size="20" /></td>
          </tr>
          <tr>
			  <td class="tddark">End Date</td>
              <td class="tdlightnormal" ><form:input path="msg.endDate" size="20" /></td>
          </tr>
          <tr>
			  <td class="tddark">Subject</td>
              <td class="tdlightnormal" ><form:input path="msg.msgSubject" size="70" /></td>
          </tr>
          <tr>
			  <td class="tddark">Message</td>
              <td class="tdlightnormal" >
              	<form:textarea path="msg.msg" rows="20" cols="70" /></td>
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
              <td colspan="2" align="right">
              <c:if test="${sysMsgCmd.msg.msgId != null}" >
              <input type="submit" name="btn" value="Delete">  
              <input type="submit" name="btn" value="Send Msg">
              </c:if>
              
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
