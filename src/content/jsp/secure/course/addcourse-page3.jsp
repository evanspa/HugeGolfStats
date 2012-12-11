<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<script language="JavaScript">

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
<% request.setAttribute("thwidth", "175px"); %>

<h2><bean:message key="course.add.page3.header" /></h2>

<html:form styleId="mainForm" focus="courseName" action="/secure/AddCourse-page3-submit" onsubmit="return validateCreateAccountForm(this);">

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
	<html:hidden property="validateSlopeValues" value="1" />
	<html:hidden property="validateRatingValues" value="1" />

	<!-- input course name -->
	<jsp:include page="include-inputcoursename.jsp" />
					
	<!-- input address info -->
	<jsp:include page="include-inputcourseaddress.jsp" />
			
	<!-- choose to make this course the player's home course -->
	<jsp:include page="include-inputmakehome.jsp" />

	<!-- input the slope/rating values -->
	<jsp:include page="include-inputsloperatingvalues.jsp" />

	<!-- input the hole-pars -->
	<jsp:include page="include-showholes.jsp" />					

	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button"><bean:message key="button.savecourse" /></html:submit>
		<html:button property="dummy" onclick="previous()" styleClass="button">
			<bean:message key="button.goback" />
		</html:button>
	</p>
</html:form>