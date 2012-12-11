<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Course Tees:</h3>

<!-- Output the existing tees -->
<table class="form-table">
<tr>
	<th style="width:${thwidth}"><bean:message key="course.existing-tees" /></th>
	<td>
		<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="course.edit.checkedtees.explanation" />'); return false;"><bean:message key="course.edit.checkedtees.explanation.link" /></a><span style="font-size: larger">]</span>
	</td>
</tr>
<c:forEach var="tee" items="${CourseForm.readOnlyTees}">
	<tr>
		<td align="right">${tee.key}</td>
		<td align="left">
			<html:checkbox property="courseTee(${tee.key})" value="${tee.value}" disabled="true" />
				
			<!-- for each tee outputted as disabled, we need to have a hidden so that the 
			     value is included in the submit; disabled-checkboxes will not be included
			     in the submit -->
			<html:hidden property="courseTee(${tee.key})" value="${tee.value}" />
		</td>
	</tr>
</c:forEach>

<!-- Output the additional tees the user can check-on -->
<tr>
	<th style="width:${thwidth}"><bean:message key="course.additional-tees" /></th>
	<td>&nbsp;</td>
</tr>
<c:forEach var="tee" items="${sessionScope.TeeNames}">
	<logic:notEqual name="CourseForm" property="readOnlyTee(${tee.name})" value="${tee.id}">
		<tr>
			<td>${tee.name}</td>
			<td><html:checkbox property="courseTee(${tee.name})" value="${tee.id}" /></td>
		</tr>
	</logic:notEqual>
</c:forEach>
</table>