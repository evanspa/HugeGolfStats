<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<table class="form-table">

	<!-- input country -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.address.country" /></th>
		<td>
			<html:select property="countryId" onchange="countrySelected()" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.COUNTRIES %>"
					property="id"						
					labelProperty="name" />
			</html:select>
		</td>
	</tr>
	
	<!-- input state/province -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.address.state_province" /></th>
		<td>
			<html:select property="stateProvinceId" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.STATE_PROVINCES %>"
					property="id.id"						
					labelProperty="name" />
			</html:select>
		</td>
	</tr>
	
	<!-- input city -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.address.city" /></th>
		<td><html:text size="40" property="city" styleClass="field" /></td>
	</tr>
	
	<!-- input course name -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.coursename" /></th>
		<td><html:text size="40" property="courseName" styleClass="field" /></td>
	</tr>	
</table>