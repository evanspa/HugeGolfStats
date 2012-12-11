<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<script language="JavaScript">

	function start() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Previous-AddCourse-page1.do";
		formObj.submit();
	}

	function previous() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Previous-AddCourse-page2.do";
		formObj.submit();
	}
	
	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelCourse.do";
		formObj.submit();
		<% if (BooleanUtils.toBoolean((String)request.getAttribute(
				AttributeKeyConstants.NO_MENU))) { %>
				
			window.opener = self;
			window.close()
		<% } %>
	}	
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-AddCourse-page2-submit.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "220px"); %>

<h2><bean:message key="course.add.page1.header" /></h2>
<br />
<html:form styleId="mainForm" focus="courseName" action="/secure/AddCourse-page1-submit" onsubmit="return validateCreateAccountForm(this);">
	
	<jsp:include page="/jsp/secure/common/nomenu-include.jsp" />

	<html:hidden property="numHoles" value="18" />
	<html:hidden property="firstSearchResultNum" value="0" />
	<html:hidden property="maxSearchResultsNum" value="25" />

	<!-- input course name -->
	<jsp:include page="include-inputcoursename.jsp" />
					
	<!-- input address info -->
	<jsp:include page="include-inputcourseaddress.jsp" />

	<!-- choose to make this course the player's home course -->
	<jsp:include page="include-inputmakehome.jsp" />

	<!-- input the starting hole numbers -->
	<jsp:include page="include-inputstartingholenumbers.jsp" />

	<!-- input the slope/rating values -->
	<jsp:include page="include-inputcoursetees.jsp" />				

	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button"><bean:message key="button.next" /></html:submit>
	</p>
</html:form>
