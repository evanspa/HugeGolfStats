<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>

<% pageContext.setAttribute("isloggedin", NewUtil.isLoggedIn(request)); %>
<tr>
	<th style="width:${thwidth}; text-align:right">
		<logic:equal name="isloggedin" value="true">
			<bean:message key="profile.display.userid" />
		</logic:equal>
		<logic:notEqual name="isloggedin" value="true">
			<bean:message key="profile.userid" /><span class="required">*</span>
		</logic:notEqual>
	</th>
	<td>
		<logic:equal name="isloggedin" value="true">
			<bean:write name="ProfileForm" property="userId" />
		</logic:equal>
		<logic:notEqual name="isloggedin" value="true">
			<html:text property="userId" size="30" styleClass="field" />
		</logic:notEqual>
	</td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.password" /><span class="required">*</span></th>
	<td><html:password property="password" size="30" styleClass="field" /></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.reenterpassword" /><span class="required">*</span></th>
	<td><html:password property="passwordConfirm" size="30" styleClass="field" /></td>
</tr>

