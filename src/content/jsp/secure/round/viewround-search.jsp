<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelRound.do";
		formObj.submit();
	}
	
	function onLoad() {
		countryDD = document.getElementById('countryDD');
		stateDD = document.getElementById('stateDD');
	    selectedCountry = countryDD.options[countryDD.selectedIndex].value;
	    if (selectedCountry == <%= Constants.ANY_OPTION_VAL %>) {
		    stateDD.selectedIndex = 0;
	        stateDD.disabled = true;
	    }
	}
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		countryDD = document.getElementById('countryDD');
		stateDD = document.getElementById('stateDD');
	    selectedCountry = countryDD.options[countryDD.selectedIndex].value;
	    if (selectedCountry == <%= Constants.ANY_OPTION_VAL %>) {
		    stateDD.selectedIndex = 0;
	        stateDD.disabled = true;
	    } else {
	    	formObj.action="Refresh-ViewRound-search.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>";
	    	formObj.submit();
	    }		
	}	
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="round.view.search.header" /></h2>
<br />
<html:form styleId="mainForm" focus="courseName" action="/secure/ViewRound-search-results">
	
	<html:hidden property="firstSearchResultNum" value="0" />
	<html:hidden property="maxSearchResultsNum" value="<%= Constants.MAX_RESULT_COUNT %>" />
	<html:hidden property="pageAction" value="<%= Constants.FALSE %>" />

	<jsp:include page="include-searchform.jsp" />
			
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:reset styleClass="button"><bean:message key="button.reset" /></html:reset>
		<html:submit styleClass="button"><bean:message key="button.search" /></html:submit>
	</p>
</html:form>

<script language="Javascript">
	onLoad();
</script>