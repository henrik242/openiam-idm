
<% 
	String policyDefId = (String)request.getAttribute("policyDefId");
	
	if (policyDefId.equalsIgnoreCase("103")) {
%>
<jsp:include page="/policy/authpolicyDetail.jsp" flush="true" />
<%	} else if (policyDefId.equalsIgnoreCase("100")) {%>
<jsp:include page="/policy/passwordpolicyDetail.jsp" flush="true" />
<%	} else {%>
<jsp:include page="/policy/policyDetail.jsp" flush="true" />

<%	} %>
 