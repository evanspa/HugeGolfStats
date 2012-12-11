<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>

<% pageContext.setAttribute("isloggedin", NewUtil.isLoggedIn(request)); %>

<script language="JavaScript" src="<%= WebUtils.httpPrefix(request) %>/js/add-edit-course-links.js"></script>
<jsp:include page="include-cancelfunction.jsp" />

<script language="JavaScript">
	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelEditProfile.do";
		formObj.submit();
	}
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditHomeCourse.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}
	
	function stateProvinceSelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditHomeCourse.do?<%= AttributeKeyConstants.STATE_PROVINCE_CHANGED %>=<%= Constants.TRUE %>";
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "235px"); %>

<h2><bean:message key="profile.header.edithomecourse" /></h2>
<br />
<html:form styleId="mainForm" focus="firstName" action="/secure/EditHomeCourse-submit" onsubmit="return validateCreateAccountForm(this);">
	<jsp:include page="include-outputhiddens.jsp" />
	<table class="form-table">
		<jsp:include page="include-inputhomecourse.jsp" />	
	</table>
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button">
			<bean:message key="button.save" />
		</html:submit>
	</p>
</html:form>