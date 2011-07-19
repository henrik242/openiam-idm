<%@ page language="java" pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree" %>
<%@ taglib uri="/WEB-INF/requesttags.tld" prefix="request" %>
<%@ taglib uri="/WEB-INF/tabbedpanetag.tld" prefix="tabs" %>


<style type="text/css">
    .activeTab {
        Font-Family: Arial;
        Font-Size  : 12px;
        Font-Weight: Bold;
        Background-Color: #FFFFFF;
        Border-Top:   1px solid #000000;
        Border-Left:  1px solid #000000;
        Border-Right: 1px solid #000000;
    }
    .inactiveTab {
        Font-Family: Arial;
        Font-Size  : 12px;
        Font-Weight: Boldx;
        Background-Color: #EEEEEE;
        Border     : 1px solid #000000;
    }
    .tabContent {
        Font-Family: Arial;
        Font-Size  : 12px;
        Font-Weight: Boldx;
        Background-Color: #FFFFFF;
        Border-Left:  1px solid #000000;
        Border-Right: 1px solid #000000;
        Border-Bottom:   1px solid #000000;
    }
    a {
        Text-Decoration: None;
    }
</style>

    <tabs:tabbedPane defaultTab="1" >
    <table cellspacing="0" cellpadding="5" width="600">
        <tr>
            <td class="<tabs:tab tabId="1" active="activeTab" inactive="inactiveTab"/>"><a href="policyDomain.do?method=viewDomainDetail&tab=1">Authentication</a></td>
            <td class="<tabs:tab tabId="2" active="activeTab" inactive="inactiveTab"/>"><a href="resourceTreeView.do?method=view&tab=2">Resources</a></td>
            <td class="<tabs:tab tabId="5" active="activeTab" inactive="inactiveTab"/>"><a href="policyDomain.do?method=viewDomainDetail&tab=5">Audit</a></td>
         </tr>
        <tr><td colspan="7" class="tabContent"><br/><br/>
            <tabs:tabContent tabId="1"> <jsp:include flush="true" page="authenticate.jsp" /> </tabs:tabContent>
            <tabs:tabContent tabId="2"> <jsp:include flush="true" page="resources.jsp" />  </tabs:tabContent>
            <tabs:tabContent tabId="5"> <jsp:include flush="true" page="audit.jsp" />  </tabs:tabContent>
        	</td>
        </tr>
    </table>
    </tabs:tabbedPane>


