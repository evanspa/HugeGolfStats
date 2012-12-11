<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.numholes" /></th>
	<td><html:select property="numHoles" styleClass="combo">
		<html:option value="9" key="option.nine" />
		<html:option value="18" key="option.eighteen" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.teecolor" /></th>
	<td><html:select property="teeNameId" styleClass="combo">
		<html:options
			collection="<%= AttributeKeyConstants.TEE_NAMES %>"
			property="id"						
			labelProperty="name" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.headwearworn" /></th>
	<td><html:select property="headWearTypeId" styleClass="combo">
		<html:options
			collection="<%= AttributeKeyConstants.HEAD_WEAR_TYPES %>"
			property="id"						
			labelProperty="description" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.eyewearworn" /></th>
	<td><html:select property="eyeWearTypeId" styleClass="combo">
		<html:options
			collection="<%= AttributeKeyConstants.EYE_WEAR_TYPES %>"
			property="id"						
			labelProperty="description" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.pantwearworn" /></th>
	<td><html:select property="pantWearTypeId" styleClass="combo">
		<html:options
			collection="<%= AttributeKeyConstants.PANT_WEAR_TYPES %>"
			property="id"						
			labelProperty="description" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.usescart" /></th>
	<td><html:select property="usesCart" styleClass="combo">
		<html:option value="true" key="option.cart" />
		<html:option value="false" key="option.walk" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.usescaddie" /></th>
	<td><html:select property="usesCaddie" styleClass="combo">
		<html:option value="true" key="option.true" />
		<html:option value="false" key="option.false" />
	</html:select></td>
</tr>