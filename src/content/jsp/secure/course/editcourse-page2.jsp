<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<script language="JavaScript">

	function previous() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Previous-EditCourse-page1.do";
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
		formObj.action="Refresh-EditCourse-page1-submit.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}	
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "205px"); %>

<h2><bean:message key="course.edit.page2.header" /></h2>
<br />
<html:form styleId="mainForm" focus="courseName" action="/secure/EditCourse-page2-submit" onsubmit="return validateCreateAccountForm(this);">
	
	<jsp:include page="/jsp/secure/common/nomenu-include.jsp" />

	<!-- output hiddens for checkbox-backed properties so if the user goes back 
 	 	 to the previous page, the correct checkboxes will be checked (the 
		 values are reset in the formbean's 'reset()' method due to the nature 
 	 	 of html checkboxes -->
	<logic:iterate id="tee" name="CourseForm" property="courseTees">
		<html:hidden property="courseTee(${tee.key})" value="${tee.value}" />
	</logic:iterate>	
	<html:hidden property="promptYardages" value="${CourseForm.promptYardages}" />
	<html:hidden property="promptHandicaps" value="${CourseForm.promptHandicaps}" />

	<!-- input course name -->
	<jsp:include page="include-inputcoursename.jsp" />
				
	<!-- input address info -->
	<jsp:include page="include-inputcourseaddress.jsp" />
					
	<!-- input the starting hole numbers -->
	<jsp:include page="include-inputstartingholenumbers.jsp" />

	<!-- input the slope/rating values -->
	<jsp:include page="include-inputsloperatingvalues.jsp" />

	<!-- input the hole-pars -->
	<jsp:include page="include-inputholes.jsp" />					

	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button"><bean:message key="button.next" /></html:submit>
		<html:button property="dummy" onclick="previous()" styleClass="button">
			<bean:message key="button.previous" />
		</html:button>
	</p>
</html:form>
