<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<script type="text/javascript">
    <!--


    function showSupervisorDialog(idfield, namefield) {
        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE ");

        if (msie > 0) {
            dialogReturnValue = window.showModalDialog("user/dialogshell.jsp", null, "dialogWidth:670px;dialogHeight:600px;");
            document.getElementById(idfield).value = dialogReturnValue.id;
            document.getElementById(nameField).value = dialogReturnValue.name;
        } else {
            dialogReturnValue = window.showModalDialog("user/selsupervisor.jsp", null, "dialogWidth:670px;dialogHeight:600px;");
            document.getElementById(idfield).value = dialogReturnValue.id;
            document.getElementById(namefield).value = dialogReturnValue.name;
        }
    }

    function initializeFields(idApproverfield, nameApproverfield, idApprfield, nameApprfield, idRejfield, nameRejfield) {

        document.getElementById(idApproverfield).value = "";
        document.getElementById(nameApproverfield).value = "";
        document.getElementById(idApprfield).value = "";
        document.getElementById(nameApprfield).value = "";
        document.getElementById(idRejfield).value = "";
        document.getElementById(nameRejfield).value = "";


    }


    //-->
</script>

<table width="800pt">
<tr>
    <td>
        <table width="100%">
            <tr>
                <td class="pageTitle" width="70%">
                    <h2 class="contentheading">Resource Management</h2>
                </td>
            </tr>
        </table>
    </td>
        <% 	String msg = (String)request.getAttribute("msg");
	if (msg != null) { %>
<tr>
    <td class="msg" colspan="5" align="center">
        <b><%=msg %>
        </b>
    </td>
</tr>
<% } %>

<tr>
<td>
<form:form commandName="approvalCmd">
    <form:hidden path="resId"/>
    <form:hidden path="managedSysId"/>

    <table width="800pt">
        <tr>
            <td align="center" height="100%">
                <fieldset class="userform">
                    <legend>RESOURCE APPROVER(S): ${approvalCmd.resourceName }</legend>

                    <table class="resourceTable" cellspacing="2" cellpadding="2">

                        <tr class="header">
                            <th></td>
                            <th>Approver Type</td>
                            <th>Approver - User</td>
                            <th>Notify User On Approval</td>
                            <th>Notify User on Reject</td>
                        </tr>
                        <c:forEach items="${approvalCmd.approverAssoc}" var="approverAssoc" varStatus="assoc">

                            <tr class="tdlight">
                                <td class="tableEntry"><form:checkbox path="approverAssoc[${assoc.index}].selected"/>
                                    <form:hidden path="approverAssoc[${assoc.index}].approverAssocId"/>
                                    <form:hidden path="approverAssoc[${assoc.index}].approverLevel"/>
                                </td>
                                <td class="tableEntry">
                                    <form:select path="approverAssoc[${assoc.index}].associationType"
                                                 onchange="initializeFields('approverAssoc${assoc.index}.approverUserId', 'approverAssoc${assoc.index}.approverName','approverAssoc${assoc.index}.notifyUserOnApprove', 'approverAssoc${assoc.index}.notifyUserOnApproveName', 'approverAssoc${assoc.index}.notifyUserOnReject', 'approverAssoc${assoc.index}.notifyUserOnRejectName'  );">
                                        <form:option value="" label="-Please Select-"/>
                                        <form:option value="USER" label="USER"/>
                                        <form:option value="SUPERVISOR" label="SUPERVISOR"/>
                                    </form:select>
                                </td>


                                <td class="tableEntry"><form:hidden
                                        path="approverAssoc[${assoc.index}].approverUserId"/>
                                    <form:input path="approverAssoc[${assoc.index}].approverName" readonly="true"/>
                                    <a href="javascript:showSupervisorDialog('approverAssoc${assoc.index}.approverUserId', 'approverAssoc${assoc.index}.approverName' );">Select
                                        Approver</a>
                                </td>
                                <td class="tableEntry">
                                     <form:select path="approverAssoc[${assoc.index}].approveNotificationUserType" >
                                        <form:option value="USER" label="USER"/>
                                        <form:option value="SUPERVISOR" label="SUPERVISOR"/>
                                        <form:option value="TARGET_USER" label="TARGET_USER"/>
                                    </form:select>

                                    <form:hidden
                                        path="approverAssoc[${assoc.index}].notifyUserOnApprove"/>
                                    <form:input path="approverAssoc[${assoc.index}].notifyUserOnApproveName"
                                                readonly="true"/>
                                    <a href="javascript:showSupervisorDialog('approverAssoc${assoc.index}.notifyUserOnApprove', 'approverAssoc${assoc.index}.notifyUserOnApproveName' );">Select
                                        User</a>
                                </td>
                                <td class="tableEntry">
                                    <form:select path="approverAssoc[${assoc.index}].rejectNotificationUserType" >
                                        <form:option value="USER" label="USER"/>
                                        <form:option value="SUPERVISOR" label="SUPERVISOR"/>
                                        <form:option value="TARGET_USER" label="TARGET_USER"/>
                                    </form:select>


                                    <form:hidden
                                        path="approverAssoc[${assoc.index}].notifyUserOnReject"/>
                                    <form:input path="approverAssoc[${assoc.index}].notifyUserOnRejectName"
                                                readonly="true"/>
                                    <a href="javascript:showSupervisorDialog('approverAssoc${assoc.index}.notifyUserOnReject', 'approverAssoc${assoc.index}.notifyUserOnRejectName' );">Select
                                        User</a>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>

                    <tr>
                        <td align="right"><input type="submit" name="btn" value="Delete"
                                                 onclick="return confirm('Are you sure you want to delete this stage in the process?');"/>
                            <input type="submit" name="btn" value="Save"/> <input type="submit" name="btn"
                                                                                  value="Cancel"/></td>
                    </tr>


    </table>
    </TD>
    </TR>
    </TABLE>
</form:form>

</td>
</tr>
</table>
