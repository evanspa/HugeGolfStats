<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelCourse.do";
		formObj.submit();
	}
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditCourse-search.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>";
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "150px"); %>

<h2><bean:message key="course.edit.search.header" /></h2>
<br />

<html:form styleId="mainForm" focus="courseName" action="/secure/EditCourse-search-results">
	
	<html:hidden property="firstSearchResultNum" value="0" />
	<html:hidden property="maxSearchResultsNum" value="<%= Constants.MAX_RESULT_COUNT %>" />
	<html:hidden property="pageAction" value="<%= Constants.FALSE %>" />

	<jsp:include page="include-searchform.jsp" />
	<br />
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:reset styleClass="button"><bean:message key="button.reset" /></html:reset>
		<html:submit styleClass="button">
			<bean:message key="button.search" />
		</html:submit>
	</p>
</html:form>
