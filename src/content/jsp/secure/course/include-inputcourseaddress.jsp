<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<h3>Course Address Information:</h3>

<!-- input country -->
<table class="form-table">
<tr>
	<th style="width:${thwidth}"><bean:message key="course.address.country" /><span class="required">*</span></th>
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
	<th style="width:${thwidth}"><bean:message key="course.address.state_province" /><span class="required">*</span></th>
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
	<th style="width:${thwidth}"><bean:message key="course.address.city" /><span class="required">*</span></th>
	<td><html:text size="55" property="city" styleClass="field" /></td>
</tr>
</table>