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
                            <th>Approver - Role</td>
                            <th>Notify User On Approval</td>
                            <th>Notify User on Reject</td>
                            <th>Apply Delegation Rules</td>
                        </tr>
                        <c:forEach items="${approvalCmd.approverAssoc}" var="approverAssoc" varStatus="assoc">

                            <tr class="tdlight" valign="top">
                                <td class="tableEntry"><form:checkbox path="approverAssoc[${assoc.index}].selected"/>
                                    <form:hidden path="approverAssoc[${assoc.index}].approverAssocId"/>
                                    <form:hidden path="approverAssoc[${assoc.index}].approverLevel"/>
                                </td>
                                <td class="tableEntry" valign="top">
                                    <form:select path="approverAssoc[${assoc.index}].associationType"
                                                 onchange="initializeFields('approverAssoc${assoc.index}.approverUserId', 'approverAssoc${assoc.index}.approverName','approverAssoc${assoc.index}.notifyUserOnApprove', 'approverAssoc${assoc.index}.notifyUserOnApproveName', 'approverAssoc${assoc.index}.notifyUserOnReject', 'approverAssoc${assoc.index}.notifyUserOnRejectName'  );">
                                        <form:option value="" label="-Please Select-"/>
                                        <form:option value="USER" label="USER"/>
                                        <form:option value="SUPERVISOR" label="SUPERVISOR"/>
                                        <form:option value="ROLE" label="ROLE"/>
                                    </form:select>
                                </td>


                                <td class="tableEntry" valign="top"><form:hidden
                                        path="approverAssoc[${assoc.index}].approverUserId"/>
                                    <form:input path="approverAssoc[${assoc.index}].approverName" readonly="true"/>
                                    <a href="javascript:showSupervisorDialog('approverAssoc${assoc.index}.approverUserId', 'approverAssoc${assoc.index}.approverName' );">Select
                                        Approver</a>
                                </td>
                                <td class="tableEntry" valign="top">
                                    <form:select path="approverAssoc[${assoc.index}].approverRoleId" >
                                        <form:option value="" label="-Please Select-"/>
                                          <c:forEach items="${approvalCmd.roleList}" var="role">
                                            <form:option value="${role.id.serviceId}*${role.id.roleId}" label="${role.id.serviceId}-${role.roleName}" />
                                          </c:forEach>
                                    </form:select>
                                </td>

                                <td class="tableEntry" valign="top">
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
                                <td class="tableEntry" valign="top">
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
                                <td class="tableEntry" valign="top">
                                    <form:select path="approverAssoc[${assoc.index}].applyDelegationFilter" >
                                        <form:option value="0" label="NO"/>
                                        <form:option value="1" label="YES"/>
                                    </form:select>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>

                    <tr>
                        <td align="right"><input type="submit" name="btn" value="Delete"
                                                 onclick="return confirm('Are you sure you want to delete this stage in the process?');"/>
                            <input type="submit" name="btn" value="Save"/> <input type="submit" name="_cancel" value="Cancel" /></td>
                    </tr>
                    <tr>
                           <td>
                               <i>Approvers for a resource can be either a User, a users supervisor or a Role. The system
                                   does not allow you to mix and match options.<br>
                                   <b>User:</b> You must select a user from the search box<br>
                                   <b>Supervisor:</b> You do not need to select a value. The system will notify the supervisor the user in question<br>
                                   <b>Role:</b> Everyone in a role will be able to act on a request<br>
                                   While multiple users can be defined as approvers for a resource, only 1 role can be defined as an approver.
                           </td>
                   </tr>


    </table>
    </TD>
    </TR>
    </TABLE>
</form:form>

</td>
</tr>
</table>
