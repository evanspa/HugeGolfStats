<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelCreateAccount.do";
		formObj.submit();
	}
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-CreateAccount.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}
	
	function stateProvinceSelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-CreateAccount.do?<%= AttributeKeyConstants.STATE_PROVINCE_CHANGED %>=<%= Constants.TRUE %>";
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "300px"); %>

<h2><bean:message key="signup.header" /></h2>

<html:form styleId="mainForm" focus="firstName" action="/CreateAccount-submit" onsubmit="return validateCreateAccountForm(this);">
		
	<!-- input personal-info section -->
	<h3><bean:message key="profile.header.personalinfo" /></h3>
	<table class="form-table"><jsp:include page="secure/player/profile/include-inputpersonalinfo.jsp" /></table>
			
	<!-- show account information (userid/password) section -->
	<h3><bean:message key="profile.header.accountinfo" /></h3>
	<table class="form-table"><jsp:include page="secure/player/profile/include-inputaccountinfo.jsp" /></table>

	<!-- show player's home course section -->
	<h3><bean:message key="profile.header.homecourseinfo" /></h3>
	<table class="form-table"><jsp:include page="secure/player/profile/include-inputhomecourse.jsp" /></table>

	<!-- show player's playing habits section -->
	<h3><bean:message key="profile.header.playinghabits" /></h3>
	<table class="form-table"><jsp:include page="secure/player/profile/include-inputplayinghabits.jsp" /></table>

	<p>
		<html:submit styleClass="button">
			<bean:message key="button.createaccount" />
		</html:submit>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
	</p>
</html:form>
