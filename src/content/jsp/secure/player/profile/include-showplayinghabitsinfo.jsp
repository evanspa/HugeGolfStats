<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<h3>[<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditPlayingHabits.do"><bean:message key="action.edit" /></a>]&nbsp;&nbsp;<bean:message key="profile.header.playinghabits" /></h3>

<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.numholes" /></th>
		<td><bean:write name="ProfileForm" property="numHoles" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.teecolor" /></th>
		<td><bean:write name="ProfileForm" property="teeColorDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.headwearworn" /></th>
		<td><bean:write name="ProfileForm" property="headWearDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.eyewearworn" /></th>
		<td><bean:write name="ProfileForm" property="eyeWearDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.pantwearworn" /></th>
		<td><bean:write name="ProfileForm" property="pantWearDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.usescart" /></th>
		<td>
			<logic:equal value="true" name="ProfileForm" property="usesCart">
				<bean:message key="option.cart" />
			</logic:equal>
			<logic:equal value="false" name="ProfileForm" property="usesCart">
				<bean:message key="option.walk" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.usescaddie" /></th>
		<td>
			<logic:equal value="true" name="ProfileForm" property="usesCaddie"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="ProfileForm" property="usesCaddie"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
</table>