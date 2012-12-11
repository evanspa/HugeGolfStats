<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<script language="JavaScript">
	var GoBackAction = '<%= (String)request.getAttribute("goback-action") %>';
</script>

<!-- include common javascript -->
<jsp:include page="includes/common/include-pfjavascript.jsp" />

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="yourstats.summary.heading" /></h2>
<br />
<jsp:include page="includes/common/include-print.jsp" />
<br /><br />
<html:form styleId="mainForm" action="/secure/PF-Summary-stats-refresh" onsubmit="return validateCreateAccountForm(this);">
		
	<!-- input the course played -->
	<jsp:include page="includes/common/include-showcourseplayed.jsp" />

	<!-- input circumstances to filter-on -->
	<jsp:include page="includes/common/include-showcircumstancesinput.jsp" />

	<!-- input date range to filter-on -->
	<jsp:include page="includes/common/include-showdatesinput.jsp" />
			
	<br />
	<div class="section">
		<jsp:include page="includes/include-summary.jsp" />
	</div>
</html:form>
