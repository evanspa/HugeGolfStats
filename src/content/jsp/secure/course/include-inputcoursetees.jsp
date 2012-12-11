<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Course Tees:</h3>
<table class="form-table">
<tr>
	<th style="width:${thwidth}">
		<bean:message key="course.available-tees" /><span class="required">*</span>
	</th>
	<td>&nbsp;</td>
</tr>

<c:forEach var="tee" items="${sessionScope.TeeNames}">
	<tr>
		<td align="right">${tee.name}</td>
		<td align="left">
			<html:checkbox 
				property="courseTee(${tee.name})" 
				value="${tee.id}" />
		</td>
	</tr>
</c:forEach>
</table>