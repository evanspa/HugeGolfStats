<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<logic:equal name="CourseForm" property="numHoles" value="18">
	<h3><bean:message key="course.inputoverallsloperatingvalues" /></h3>
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
				<td style="text-align: center"><html:text size="10" maxlength="5" property="slopeValue(overall|${tee.value})" styleClass="field" /></td>
				<td style="text-align: center"><html:text size="10" maxlength="5" property="ratingValue(overall|${tee.value})" styleClass="field" /></td>
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
				<td style="text-align:center"><html:text size="10" maxlength="5" property="slopeValue(front|${tee.value})" styleClass="field" /></td>
				<td style="text-align:center"><html:text size="10" maxlength="5" property="slopeValue(back|${tee.value})" styleClass="field" /></td>
				<td style="text-align:center"><html:text size="10" maxlength="5" property="ratingValue(front|${tee.value})" styleClass="field" /></td>
				<td style="text-align:center"><html:text size="10" maxlength="5" property="ratingValue(back|${tee.value})" styleClass="field" /></td>
			</tr>
		</logic:iterate>
	</table>
</logic:equal>

<logic:equal name="CourseForm" property="numHoles" value="9">
</logic:equal>