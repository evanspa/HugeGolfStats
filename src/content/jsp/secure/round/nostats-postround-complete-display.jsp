<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="round.saved.header" /></h2>

<!-- show the course information -->
<jsp:include page="include-showcourseinformation.jsp" />		
		
<!-- show the overall score -->
<jsp:include page="include-showoverallscore.jsp" />
	
<!-- show the circumstances for the round -->
<jsp:include page="include-showcircumstances.jsp" />

<br />
<p>
	<span style="font-size: larger">[</span><html:link href="EditRound-page1.do?scorecardid=${scorecardid}">
	<bean:message key="round.edit" /></html:link><span style="font-size: larger">]</span>
</p>
<p>
	<span style="font-size: larger">[</span><html:link href="PostRound-page1.do">
	<bean:message key="round.saved.addnewround" /></html:link><span style="font-size: larger">]</span>
</p>