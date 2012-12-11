<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.Constants" %>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelAdmin.do";
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "225px"); %>

<h2><bean:message key="admin.player.view.search.header" /></h2>
<br />
<html:form styleId="mainForm" focus="firstName" action="/secure/admin/ViewPlayer-search-results">	
	<html:hidden property="firstSearchResultNum" value="0" />
	<html:hidden property="maxSearchResultsNum" value="<%= Constants.MAX_RESULT_COUNT %>" />
	<html:hidden property="pageAction" value="<%= Constants.FALSE %>" />	
			
	<jsp:include page="include-playersearchform.jsp" />
		
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