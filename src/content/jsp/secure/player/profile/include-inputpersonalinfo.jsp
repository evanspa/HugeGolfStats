<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.firstname" /><span class="required">*</span></td>
	<td><html:text property="firstName" size="25" styleClass="field" /></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.lastname" /><span class="required">*</span></th>
	<td><html:text property="lastName" size="25" styleClass="field" /></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.emailaddress" /><span class="required">*</span></th>
	<td><html:text property="emailAddress" size="50" styleClass="field" /></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.birthday" /><span class="required">*</span></th>
	<td><html:select property="birthdayMonth" styleClass="monthcombo">
		<html:options
			collection="<%= AttributeKeyConstants.CALENDAR_MONTHS %>"
			property="monthNumber"						
			labelProperty="name" />
	</html:select>
	<html:text property="birthdayDay" size="2" styleClass="field" />,<html:text property="birthdayYear" size="5" styleClass="field" />&nbsp;<span style="font-size: smaller; font-family: arial">(<bean:message key="profile.birthday.form" />)</span></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.postalcode" /><span class="required">*</span></th>
	<td><html:text property="postalCode" size="15" styleClass="field" /></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.ghinnumber" /></th>
	<td><html:text property="ghinNumber" size="15" styleClass="field" /></td>
</tr>