<%@ page language="java" contentType="text/html;"   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="synchListCmd">

<table width="60%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Synchronization Configuration</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

     <tr>
		<td class="plaintext">Name:</td>
         <td><input type="text" value="HRSynch">    
	
         </td>
  	</tr>
     <tr>
		<td class="plaintext" align="center" colspan="2"><b>Source System</b></td>
  	</tr>
     <tr>
		<td class="plaintext">Connection URL:</td>
         <td><input type="text" value="jdbc:oracle:thin:@AXPRD03:1521:HRBCPRD" size="60"></td>
  	</tr>
     <tr>
		<td class="plaintext">Driver Class</td>
         <td><input type="text" value="oracle.jdbc.OracleDriver"  size="60"></td>
  	</tr>  		
     <tr>
		<td class="plaintext">Login</td>
         <td><input type="text" value="ESEC_VW0"  size="30"></td>
  	</tr>
    <tr>
		<td class="plaintext">Password</td>
         <td><input type="password" value="Esec4vw"  size="30"></td>
  	</tr>
    <tr>
		<td class="plaintext">Query</td>
         <td><textarea cols="60" rows="6" wrap="virtual" >
         select EMPLID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, LOCATION, DEPTID, EMPL_STATUS, LAST_4DIGITS_SSN, LOCATION_DESCR   
		 from PS_OC_SECURITY_VW 
         	 </textarea>
         </td>
  	</tr>
     <tr>
		<td class="plaintext" align="center" colspan="2"><b>Batch Configuration</b></td>
  	</tr>
     <tr>
		<td class="plaintext">Polling Interval</td>
         <td><input type="text" value="30"  size="10"> 
         	 <select>
         	 	<option>min</option>
         	 	<option>hr</option>
         	 	<option>day</option>
         	 </select>
         	  </td>
  	</tr>
      <tr>
		<td class="plaintext">Start Date</td>
         <td><input type="text" value=""  size="30"></td>
  	</tr>
      <tr>
		<td class="plaintext">End Date</td>
         <td><input type="text" value=""  size="30"></td>
  	</tr> 	
     <tr>
		<td class="plaintext" align="center" colspan="2"><b>Synchronization Logic</b></td>
  	</tr>  	 	
     <tr>
		<td class="plaintext"></td>
         <td><textarea cols="100" rows="25" wrap="virtual">
 User iamUser = userService.getUser(employeeId);
 Login UDlogin = loginService.getLogin("OCUD", employeeId);
 Login UClogin = loginService.getLogin("OCCC", employeeId);
 Login OCGOVlogin = loginService.getLogin("USR_SEC_DOMAIN", employeeId);
 
 String ocgovCanName=null;
 String ucCanName = null;
 String udLogin = null;
 
 if  (OCGOVlogin != null &&  OCGOVlogin.getCanonicalName() != null) {
  ocgovCanName =  OCGOVlogin.getCanonicalName();
 }

 if ( ocgovCanName != null && ocgovCanName.contains("Disabled") ) {
 
  log("Mail box holder: " + iamUser.getUserId() );
  
 }else {
 
 if (iamUser == null) {
   // new User 
   if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") )) {
						  
  String reason = "firstName + " " + lastName + " PS EID=" + employeeId + "DEPT ID=" + deptid + locationBasedDomain + " ADD Domain Group: HRMS PS";
  IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "ADD USER", status, employeeId, "USER", reason, "3001" );
  auditDataService.addLog(log);

	}else {
					  // logic to enable and disable account
  if (location.startsWith("UT")) {
  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
	  (UDlogin != null && UDlogin.getStatus() != null && UDlogin.getStatus().equalsIgnoreCase("DISABLED") )	)   {

	  String reason = firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL";
	  IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "STATUS CHNG-ENABLE", status, employeeId, "USER", reason, "3001" );
	  auditDataService.addLog(log);
			  
  }				  
  
  if (status != null && (status.equals("R") || status.equals("T") || status.equals("D")) &&   
	  (UDlogin != null && UDlogin.getStatus() == null  ) )	   {

	  String reason = firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL";
	  IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "STATUS CHNG-DISABLE", status, employeeId, "USER", reason, "3001" );
	  auditDataService.addLog(log);

  }
 }else if (location.startsWith("CC")) {

  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
	 (UClogin != null && UClogin.getStatus() != null && UClogin.getStatus().equalsIgnoreCase("DISABLED") )	)   {
  
	  String reason = firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL";
  	  IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "STATUS CHNG-ENABLE", status, employeeId, "USER", reason, "3001" );
  	  auditDataService.addLog(log);
		  					  
  }				  
  
  if (status != null && (status.equals("R") || status.equals("T") || status.equals("D")) &&   
      (UClogin != null && UClogin.getStatus() == null  ) )	   {
	  
	  String reason = firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL";
  	  IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "STATUS CHNG-DISABLE", status, employeeId, "USER", reason, "3001" );
  	  auditDataService.addLog(log);
	  	  
  }
		  
 }else {  
  if (OCGOVlogin != null) {

  if (status != null && (status.equals("A") || status.equals("L") || status.equals("P") || status.equals("S") ) &&   
  (OCGOVlogin != null && OCGOVlogin.getStatus() != null && OCGOVlogin.getStatus().startsWith("DISABLE") )	)   {

   String reason = firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL";
   IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "STATUS CHNG-ENABLE", status, employeeId, "USER", reason, "3001" );
   auditDataService.addLog(log);
		  
	  
  }				  	  
  if (status != null && (status.equals("R") || status.equals("T") || status.equals("D")) &&   
	  (OCGOVlogin != null && (OCGOVlogin.getStatus() == null || OCGOVlogin.getStatus().startsWith("NO_PWDEXP"))  ))	   {

	  String reason = firstName + " " + lastName + " PS-STATUS=" + status + " IAM-STATUS=" + iamUser.getStatus() + " PS EID=" + employeeId + " DOM=OC UTIL";
  	  IdmAuditLog log = new IdmAuditLog(new Date(System.currentTimeMillis(), "STATUS CHNG-DISABLE", status, employeeId, "USER", reason, "3001" );
  	  auditDataService.addLog(log);			  
  }
}
	 }
  }
 }
}

        	 </textarea>
         </td>
  	</tr> 	
  	
  	  	
    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="2" align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>

</form:form>

