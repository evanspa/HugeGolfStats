<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<script language="JavaScript">

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
</script>

<h1 class="pagetitle"><bean:message key="course.add.duplicatesfound" /></h1>

<div class="column1-unit">
	<div class="inputcourseform">
		<p><bean:message key="course.add.duplicatesfound.message" /></p>
		<table>
			<tr>
				<th style="text-align:center">&nbsp;</th>
				<th style="text-align:center"><bean:message key="search.coursename" /></th>
				<th style="text-align:center"><bean:message key="search.city" /></th>
				<th style="text-align:center"><bean:message key="search.stateprovince" /></th>
				<th style="text-align:center"><bean:message key="search.country" /></th>
			</tr>
			<logic:iterate indexId="i" id="course" name="<%= AttributeKeyConstants.COURSES %>">
				<tr>
					<td style="text-align:center">
						<span style="font-size:larger">[</span><a href="ViewCourse-page1.do?courseid=${course.id}"><bean:message key="search.course.view" /></a><span style="font-size:larger">]</span>
					</td>
					<td style="text-align:center">${course.description}</td>
					<td style="text-align:center">${course.city}</td>
					<td style="text-align:center">${course.stateProvince.name}</td>
					<td style="text-align:center">${course.stateProvince.country.name}</td>
				</tr>
			</logic:iterate>
		</table>
	
		<html:form styleId="mainForm" focus="courseName" action="/secure/AddCourse-dups-continue">
	
			<!-- output hiddens for checkbox-backed properties so if the user goes back 
 	 	 		  to the previous page, the correct checkboxes will be checked (the 
 	  	 		  values are reset in the formbean's 'reset()' method due to the nature 
 	 	 		  of html checkboxes -->
			<logic:iterate id="tee" name="CourseForm" property="courseTees">
				<html:hidden property="courseTee(${tee.key})" value="${tee.value}" />
			</logic:iterate>	
			<html:hidden property="promptYardages" value="${CourseForm.promptYardages}" />
			<html:hidden property="promptHandicaps" value="${CourseForm.promptHandicaps}" />
			<html:hidden property="makeHome" value="${CourseForm.makeHome}" />

			<br />
			<br />
			<p>
			<html:button property="dummy" onclick="cancel()" styleClass="longbutton">
				<bean:message key="button.addcourse.dupsfound.cancel" />
			</html:button>
			<html:submit property="dummy" styleClass="longbutton">
				<bean:message key="button.addcourse.dupsfound.continue" />
			</html:submit>
			</p>
		</html:form>
	</div>
</div>