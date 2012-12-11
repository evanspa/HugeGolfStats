<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<script language="JavaScript">
	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="<%= WebUtils.httpPrefix(request) %>/Welcome.do";
		formObj.submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="forgotpassword.header" /></h2>
<br />
<html:form styleId="mainForm" focus="monthNumber" action="/ForgotPassword-submit">
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.birthday" /><span class="required">*</span></th>
		<td>
			<html:select property="birthdayMonth" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.CALENDAR_MONTHS %>"
					property="monthNumber"
					labelProperty="name" />
			</html:select>
			<html:text property="birthdayDay" size="2" styleClass="field" />,<html:text property="birthdayYear" size="5" styleClass="field" />&nbsp;<span style="font-size: smaller; font-family: arial">(<bean:message key="profile.birthday.form" />)</span>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.postalcode" /><span class="required">*</span></th>
		<td><html:text property="postalCode" size="15" styleClass="field" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.userid" /><span class="required">*</span></th>
		<td><html:text property="userId" size="45" styleClass="field" /></td>
	</tr>
</table>
<p>
	<input type="submit" value="Submit" class="button" />
	<html:button property="dummy" onclick="cancel()" styleClass="button">
		<bean:message key="button.cancel" />
	</html:button>
</p>
</html:form>
