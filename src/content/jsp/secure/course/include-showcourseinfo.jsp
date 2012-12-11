<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.coursename" /></th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.COURSE %>" 
				property="course.description" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.address.country" /></th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.COURSE %>" 
				property="course.stateProvince.country.name" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.address.state_province" /></th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.COURSE %>" 
				property="course.stateProvince.name" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.address.city" /></th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.COURSE %>" 
				property="course.city" />
		</td>
	</tr>
</table>