
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


	<script language ="javascript" src="js/date-picker.js"></script>

	<!-- Javascript method for calling a PopupPage -->
	<script language="Javascript">
		<!--
 			function open_url(URL) {
   			p_item = arguments[0];
   			vWinCal = window.open(URL,"Window","width=350,height=400,status=nos,resizable=no,top=100,left=350,scrollbars=yes");
   			vWinCal.opener = self;
   			ggWinCal = vWinCal;
 			}
 		//-->
	</script>

<html:form action="/showReport.do?name=utilize" target="_blank">
      <table border="0">
	   <tr>
	      <td colspan="2" class="title">         
	          <strong>Enter Report Parameters</strong>
	      </td>
	   </tr>
        <tr>
 			<td colspan="2" align="center" bgcolor="8397B4" > <font></font></td>
  		</tr>
        <tr>
          <td class="tddark">Login:</td>
          <td class="tdlight"><html:text property="login" size="20"/></td>
        </tr>
        <tr>
          <td class="tddark">Service:</td>
          <td class="tdlight"><html:text property="service" size="20"/></td>
        </tr>
        <tr>
          <td class="tddark">URL Filter:</td>
          <td class="tdlight"><input type="text" name="filter" size="20"/></td>
        </tr>
        <tr>
          <td class="tddark">Start Date:</td>
          <td class="tdlight">
          <html:text property="startDate" size="20" readonly="true"/>
		 			<a href="javascript:show_calendar('reportParamForm.startDate');" >
 						<img src="images/show-calendar.gif" width="24" height="22" border="0" >
 					</a>
          </td>
        </tr>
        <tr>
          <td class="tddark">End Date:</td>
          <td class="tdlight"><html:text property="endDate" size="20" readonly="true"/>
		 			<a href="javascript:show_calendar('reportParamForm.endDate');" >
 						<img src="images/show-calendar.gif" width="24" height="22" border="0" >
 					</a>

          </td>
        </tr>
        <tr>
 			<td colspan="2" align="center" bgcolor="8397B4" > <font></font></td>
  		</tr>
        <tr class="tdlight">
          <td colspan="2" align="right"><html:submit /> <html:reset /></td>
        </tr>
      </table>
</html:form>