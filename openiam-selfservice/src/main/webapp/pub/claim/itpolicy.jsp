<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<br> 
<form:form commandName="claimAccountCmd">

<table border="0" width="500" align="center">

  <tr>
    <td class="title" colspan="2">
        Claim Account Wizard
    </td>
  </tr>
  
  <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
	<tr>
	  <td>&nbsp;</td>
 </tr>
 
 <tr>
 		<td>
<h1 align="CENTER">SDSU Computing Acceptable Use Policy</h1>
<h3 align="CENTER">Approved by the San Diego State University Senate on April 3, 2001</h3>
<h3 align="CENTER"><a href="http://security.sdsu.edu/policy/aup.html" target="_blank">http://security.sdsu.edu/<WBR>policy/aup.html</a></h3>


<h2 align="left">Acceptable Computing Use</h2>
<p>
<p>
<div align="left">
<b>1.0</b>  Computer users shall be liable for all activities on their
     accounts. All relevant federal and state laws and all University
     regulations shall apply. The University shall reserve the right to
     limit, restrict, or extend computing or communications privileges and
     access to its information resources.
<p>
<b>2.0</b>  Acceptable Use
<p>
<dir>
<b>2.1</b>   University computing and communications resources shall be used
      for the University-related activities for which they are assigned.
<p>
<b>2.2</b>   Proper copyright permissions shall be obtained and sources shall
      be properly cited.
<p>
<b>2.3</b>   Users shall not engage in activities that compromise computer
      security, circumvent controls, disrupt services, or violate computer
      etiquette.
</p></p></dir>
<p>
<b>3.0</b>  Legality and Enforcement
<p>
<dir>
<b>3.1</b>   University policies shall not supersede federal or state
       laws. Illegal actions may result in prosecution.
<p>
<b>3.2</b>   Violations of University computing policies may result in the
       revocation of access or the discontinuance of an account or the loss
       of computing privileges.
</p></dir>
<p>
<b>4.0</b>   Privacy.  Computer files, electronic mail, and computing accounts
       are not absolutely private and may be subject to access by various
       authorized persons, as well as to access in compliance with the
       California Public Records Act.
<p>

<b>5.0</b>   Operational procedures shall be determined by the Instructional
       Technology committee of the Senate and reviewed on a periodic
       basis. Current operational procedures are available at
       <a href="http://security.sdsu.edu/policy/aup-operational.html" target="_blank">http://security.sdsu.edu/<WBR>policy/aup-operational.html</a>.
</p></p></p></p></p></p></div>

</p></p>	
 		</td>
</tr>

  <tr>

    <td class="tdlight" >
        <form:checkbox value="1" path="acceptPolicy"  /> I accept the above policy
     <br><form:errors path="acceptPolicy" cssClass="error" /></td>
  </tr>
    
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="2" align="right"> 
    	  <input type="submit" name="_target1" value="Next"/>
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>
</form:form>


