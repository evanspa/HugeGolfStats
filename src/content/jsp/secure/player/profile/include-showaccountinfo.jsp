<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<h3>[<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditAccountInfo.do"><bean:message key="action.edit" /></a>]&nbsp;&nbsp;<bean:message key="profile.header.accountinfo" /></h3>

<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.display.userid" /></th>
		<td><bean:write name="ProfileForm" property="userId" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.display.password" /></th>
		<td align="left">**********</td>
	</tr>
</table>
