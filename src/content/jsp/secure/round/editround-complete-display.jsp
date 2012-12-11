<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="round.edit.saved.header" /></h2>

<!-- show the course information -->
<jsp:include page="include-showcourseinformation.jsp" />
		
<!-- show the scores -->
<jsp:include page="include-showscores.jsp" />		
		
<!-- show the round statistics -->
<jsp:include page="include-showroundstats.jsp" />
	
<!-- show the circumstances for the round -->
<jsp:include page="include-showcircumstances.jsp" />

<br />
<p>
	<span style="font-size: larger">[</span><html:link href="EditRound-page1.do?scorecardid=${scorecardid}">
	<bean:message key="round.editagain" /></html:link><span style="font-size: larger">]</span>
</p>
<p>
	<span style="font-size: larger">[</span><html:link href="PostRound-page1.do">
	<bean:message key="round.saved.addnewround" /></html:link><span style="font-size: larger">]</span>
</p>

<%
	// cleanup...
	NewUtil.removeFromSession(AttributeKeyConstants.SCORECARD_ID_PARAM, request);
	NewUtil.removeFromSession(AttributeKeyConstants.ROUNDS, request);
%>