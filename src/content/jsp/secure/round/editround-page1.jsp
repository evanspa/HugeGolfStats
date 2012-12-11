<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<script language="JavaScript">

	function previous() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Previous-EditRound-search-results.do";
		formObj.submit();
	}

	function refreshSelects() {
		var formObj = document.getElementById("mainForm");
		formObj.action="EditRound-page1.do?<%= AttributeKeyConstants.SCORECARD_ID_PARAM %>=${scorecardid}";
		formObj.submit();
	}
	
	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelRound.do";
		formObj.submit();
	}	
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditRound-page1.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}
	
	function stateProvinceSelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditRound-page1.do?<%= AttributeKeyConstants.STATE_PROVINCE_CHANGED %>=<%= Constants.TRUE %>";
		formObj.submit();
	}
	
	function courseSelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditRound-page1.do?<%= AttributeKeyConstants.COURSE_CHANGED %>=<%= Constants.TRUE %>";
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "250px"); %>

<h2><bean:message key="round.edit.page1.header" /></h2>
<html:form styleId="mainForm" action="/secure/EditRound-page1-submit" onsubmit="return validateCreateAccountForm(this);">
	
	<!-- output various hiddens including the checkbox-backed hiddens -->
	<input type="hidden" name="<%= AttributeKeyConstants.DROP_DOWN_REFRESH_PARAM %>" value="<%= Constants.FALSE %>" />

	<!-- output checkbox properties -->
	<jsp:include page="include-checkboxproperties.jsp" />

	<!-- input the course -->
	<jsp:include page="include-pickcourse.jsp" />

	<!-- input the circumstances -->
	<jsp:include page="include-inputcircumstances.jsp" />
		
	<!-- input which stats to collect -->
	<jsp:include page="include-inputstatstocollect.jsp" />
		
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button">
			<bean:message key="button.next" />
		</html:submit>
		<logic:present name="<%= AttributeKeyConstants.ROUNDS %>">
			<html:button property="dummy" onclick="previous()" styleClass="button">
				<bean:message key="button.backtosearchresults" />
			</html:button>
		</logic:present>
	</p>
</html:form>

<script language="Javascript">
  collectPerHoleStatsOnChange();
</script>