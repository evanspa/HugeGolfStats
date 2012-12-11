<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3><bean:message key="course.overallsloperatingvalues" /></h3>
<table class="form-table">
	<tr>
		<th style="text-align:center"><bean:message key="course.tee" /></th>
		<th style="text-align:center"><bean:message key="course.teecolor" /></th>
		<th style="text-align:center"><bean:message key="course.overallslope" /></th>
		<th style="text-align:center"><bean:message key="course.overallrating" /></th>
	</tr>
	<logic:iterate indexId="loop" id="tee" name="CourseForm" property="courseTees">
		<tr>
			<td style="text-align: center">${tee.key}</td>
			<td style="background-color:${tee.key}">&nbsp;</td>
			<td style="text-align: center"><bean:write name="CourseForm" property="slopeValue(overall|${tee.value})" /></td>
			<td style="text-align: center"><bean:write name="CourseForm" property="ratingValue(overall|${tee.value})" /></td>
		</tr>
	</logic:iterate>
</table>

<h3><bean:message key="course.9holesloperatingvalues" /></h3>
<table class="form-table">
	<tr>
		<th style="text-align:center"><bean:message key="course.tee" /></th>
		<th style="text-align:center"><bean:message key="course.teecolor" /></th>
		<th style="text-align:center"><bean:message key="course.tee.slope" /> / <bean:message key="course.front9" /></th>
		<th style="text-align:center"><bean:message key="course.tee.slope" /> / <bean:message key="course.back9" /></th>
		<th style="text-align:center"><bean:message key="course.tee.rating" /> / <bean:message key="course.front9" /></th>
		<th style="text-align:center"><bean:message key="course.tee.rating" /> / <bean:message key="course.back9" /></th>
	</tr>
	<logic:iterate indexId="loop" id="tee" name="CourseForm" property="courseTees">
		<tr>
			<td style="text-align: center">${tee.key}</td>
			<td style="background-color:${tee.key}">&nbsp;</td>
			<td style="text-align:center"><bean:write name="CourseForm" property="slopeValue(front|${tee.value})" /></td>
			<td style="text-align:center"><bean:write name="CourseForm" property="slopeValue(back|${tee.value})" /></td>
			<td style="text-align:center"><bean:write name="CourseForm" property="ratingValue(front|${tee.value})" /></td>
			<td style="text-align:center"><bean:write name="CourseForm" property="ratingValue(back|${tee.value})" /></td>
		</tr>
	</logic:iterate>
</table>