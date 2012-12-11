<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebConstants" %>

<h3>[<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditPersonalInfo.do"><bean:message key="action.edit" /></a>]&nbsp;&nbsp;<bean:message key="profile.header.personalinfo" /></h3>

<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.firstname" /></td>
		<td><bean:write name="ProfileForm" property="firstName" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.lastname" /></th>
		<td><bean:write name="ProfileForm" property="lastName" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.emailaddress" /></th>
		<td><bean:write name="ProfileForm" property="emailAddress" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.birthday" /></th>
		<td><bean:write name="ProfileForm" property="birthdayMonthName" />&nbsp;<bean:write name="ProfileForm" property="birthdayDay" />,&nbsp;<bean:write name="ProfileForm" property="birthdayYear" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.postalcode" /></th>
		<td><bean:write name="ProfileForm" property="postalCode" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.ghinnumber" /></th>
		<td><bean:write name="ProfileForm" property="ghinNumber" /></td>
	</tr>
</table>