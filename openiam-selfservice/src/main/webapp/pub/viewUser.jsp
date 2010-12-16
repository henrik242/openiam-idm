<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*, org.openiam.webadmin.busdel.base.*" %>
<%@ page import="org.openiam.idm.srvc.user.dto.*" %>
<%@ page import="org.openiam.idm.srvc.continfo.dto.*" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<table width="600pt" border="0" cellspacing="2" cellpadding="1" align="center">

	<tr>
      <td colspan="3" class="title">         
          <strong>User Information</strong>
      </td>
      <td colspan="1" class="text" align="right">         
          <font size="1" color="red">*</font> Required         
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
  
   <tr>
       <td class="tddark" align="right" width="20%">Name</td>
       <td class="tdlightnormal" colspan="3">${userData.firstName} ${userData.middleInit} ${userData.lastName}</td>
    </tr>
    <tr>
         <td class="tddark"  align="right" width="20%">Title</td>
         <td class="tdlightnormal" width="30%">${userData.title} </td>
        <td class="tddark"  align="right" width="20%">User Type</td>
         <td class="tdlightnormal" width="30%">${userData.employeeType}</td>    
    </tr>
    <tr>
				 <td class="tddark" align="right" width="20%">Organization Name</td>
				 <td class="tdlight" valign="middle" width="30%">${org.organizationName} </td>
		         <td class="tddark"  align="right" width="20%">Division</td>
		         <td class="tdlightnormal" width="30%">${userData.division}</td>
     </tr>
        
    <tr>
		 <td class="tddark"  align="right" width="20%">Manager</td>
		 <td class="tdlight" width="30%"> 
			  <table>
	          <c:if test="${supervisor != null}" >
	          <c:forEach items="${supervisor}" var="manager">		
				<tr>
					 <td colspan="2"> 
					 <a href="pub/viewUser.selfserve?personId=${manager.supervisor.userId}">${manager.supervisor.firstName } ${manager.supervisor.lastName }</a>		
					</td>
	
				</tr>
			</c:forEach>
			</c:if>
			</table>
         </td>

         <td class="tddark" align="right" width="20%">Department</td>
         <td class="tdlightnormal" width="30%">${userData.deptName} </td>
		</tr>
    <tr>
         <td class="tddark"  align="right" width="20%">Job Function</td>
         <td class="tdlightnormal" width="30%">${userData.jobCode}</td>
		 <td class="tddark"  align="right" width="20%">Cost Center</td>
         <td class="tdlightnormal" width="30%">${userData.costCenter}</td>      
    </tr>    
        
       
   <tr>
   	<td colspan="4" class="tddark" align="center">Contact Information</td>
   	<td></td>
   </tr>
  
  <tr>
       <td class="tddark" align="right" rowspan="2">Address</td>
       <td class="tdlightnormal" rowspan="2">
       	<table>
       		<tr>
       			<td>${userData.bldgNum} ${userData.address1} ${userData.streetDirection}</td>
       		</tr>
       		<tr>
       			<td>${userData.address2}</td>
       		</tr>
       		<tr>
       			<td>${userData.city} ${userData.state} ${userData.postalCd} </td>
       		</tr>
       		<tr>
       			<td>${userData.countryCd}</td>
       		</tr>
       	</table>
   
       </td>
       <td class="tddark" align="right" >Phone Numbers</td>
       <td class="tdlightnormal" > 
       	<table width="100%" cellpadding="2" border="0">
			<c:if test="${userData.phone != null}" >
			<c:forEach items="${userData.phone}" var="phone">
	       		<tr>
	       			<td>${phone.name}:</td>
	       			<td>${phone.phoneNbr}</td>
	       			<td>${phone.phoneExt}</td>
	       		</tr>
       		</c:forEach>
       		</c:if>

       	</table>
       
       </td>
   </tr>
 
  <tr>

       <td class="tddark" align="right">E-mail</td>
       <td class="tdlightnormal"> 
          <table width="100%" cellpadding="2" border="0">
          <c:if test="${userData.emailAddress != null}" >
          <c:forEach items="${userData.emailAddress}" var="emailAddress">
       		<tr>
       			<td>${emailAddress.name}:</td>
       			<td>${emailAddress.emailAddress} </td>
       		</tr>
       		</c:forEach>
       	  </c:if>

       	</table>

       </td>
   </tr>


   <tr>
   	<td colspan="4" class="tddark" align="center">Direct Reports</td>
   	<td></td>
   </tr>    


          <c:if test="${directReports != null}" >
          <c:forEach items="${directReports}" var="emp">		
			<tr>
				 <td colspan="2"> 
				 <a href="pub/viewUser.selfserve?personId=${emp.employee.userId}">${emp.employee.firstName } ${emp.employee.lastName }</a>
		
				</td>
				<td>${emp.employee.email}</td>
				<td>
				<c:if test="${emp.employee.areaCd != null}" >
				(${emp.employee.areaCd})-${emp.employee.phoneNbr} ext: ${emp.employee.phoneExt}
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</c:if>
	    <c:if test="${directReports == null}" >
		<tr>
			<td colspan="4"> No Direct Reports Found. </td>
		</tr>
		</c:if>

    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>
  
     
 </table>
</html:form>




