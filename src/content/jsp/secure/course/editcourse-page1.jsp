<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<script language="JavaScript">

	function previous() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Previous-EditCourse-search-results.do";
		formObj.submit();
	}

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelCourse.do";
		formObj.submit();
		<% if (BooleanUtils.toBoolean((String)request.getAttribute(
				AttributeKeyConstants.NO_MENU))) { %>
			window.opener = self;
			window.close();
		<% } %>
	}
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-EditCourse-page1.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "205px"); %>

<h2><bean:message key="course.edit.page1.header" /></h2>
<br />
<html:form styleId="mainForm" focus="courseName" action="/secure/EditCourse-page1-submit" onsubmit="return validateCreateAccountForm(this);">
	
	<jsp:include page="/jsp/secure/common/nomenu-include.jsp" />
	<html:hidden property="numHoles" value="18" />

	<!-- input course name -->
	<jsp:include page="include-inputcoursename.jsp" />
					
	<!-- input address info -->
	<jsp:include page="include-inputcourseaddress.jsp" />
					
	<!-- input the starting hole numbers -->
	<jsp:include page="include-inputstartingholenumbers.jsp" />
					
	<!-- input available tees -->
	<jsp:include page="include-editcoursetees.jsp" />

	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button"><bean:message key="button.next" /></html:submit>
		<logic:present name="<%= AttributeKeyConstants.COURSES %>">
			<html:button property="dummy" onclick="previous()" styleClass="button">
				<bean:message key="button.backtosearchresults" />
			</html:button>
		</logic:present>
	</p>
</html:form>
