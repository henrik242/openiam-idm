<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="700pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Synchronization Transaction Log</h2>
						</td>
						</tr>
					</table>
			</td>
	
			<tr>
				<td>

<html:form commandName="syncExecLogCmd">
	<table width="900pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SELECT SYNCHRONIZATION LOG</legend>


				<table class="fieldsetTable"  width="100%" >


	<tr class="tdlightnormal">
		<td align="right"></td>
		<td> 

      <!-- 		<html:select property="policyDefId">
           		<html:options collection="policyTypes" property="value" labelProperty="label"/>
     				</html:select> 
 		-->
        </td>
        <td><html:submit property="submit" value="Search" /></td>
	</tr>

</TABLE>
</html:form>
			</td>
		</tr>
	</table>


 <TABLE>
          <tr>
			  <td class="tdheader" >Date</td>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Status</td>
              <td class="tdheader" >Start Date</td>
              <td class="tdheader" >End Date</td>

          </tr>



          <tr>
              <td colspan="5" align="left"> <a href="syncConfiguration.cnt">New Synchronization Configuration</a></td>
          </tr>
          
          
</table>
