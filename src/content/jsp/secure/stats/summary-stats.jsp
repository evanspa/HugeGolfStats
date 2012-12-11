<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<script language="JavaScript">
	var NewAction = '<%= (String)request.getAttribute("pf-action") %>';
</script>

<!-- include common javascript -->
<jsp:include page="includes/common/include-javascript.jsp" />

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="yourstats.summary.heading" /></h2>
<br />
<jsp:include page="includes/common/include-printerfriendly.jsp" />

<html:form styleId="mainForm" action="/secure/Summary-stats-refresh" onsubmit="return validateCreateAccountForm(this);">
		
	<!-- input the course played -->
	<jsp:include page="includes/common/include-courseplayed.jsp" />

	<!-- input circumstances to filter-on -->
	<jsp:include page="includes/common/include-circumstancesinput.jsp" />

	<!-- input date range to filter-on -->
	<jsp:include page="includes/common/include-datesinput.jsp" />
			
	<p>
		<html:button property="dummy" onclick="clearDates(); return false;" styleClass="button">
			<bean:message key="button.cleardates" />
		</html:button>
		<html:submit styleClass="button" />
	</p>
	<div class="section">
		<jsp:include page="includes/include-summary.jsp" />
	</div>
</html:form>
