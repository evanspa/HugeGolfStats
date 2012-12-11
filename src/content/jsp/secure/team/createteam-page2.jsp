<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%= WebUtils.httpPrefix(request) %>/js/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"> 
	var cal = new CalendarPopup();
</SCRIPT>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelTeam.do";
		formObj.submit();
		<% if (BooleanUtils.toBoolean((String)request.getAttribute(
				AttributeKeyConstants.NO_MENU))) { %>
				
			window.opener = self;
			window.close();
		<% } %>
	}

</script>

<html:form styleId="mainForm" focus="teamName" action="/secure/CreateTeam-page1-submit">
	<table width="85%"><tr><td>
	<div class="box">
		<h3><bean:message key="team.add.page2.header" /></h3>
		<div>
			<p>
				<br />
				<table cellpadding="1" cellspacing="1">
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.name" />
						</td>
						<td align="left">
							<bean:write name="TeamForm" property="teamName" />
						</td>
					</tr>
					<tr>
						<td align="right" valign="top" style="font-weight: bold">
							<bean:message key="team.description" />
						</td>
						<td align="left">
							<bean:write name="TeamForm" property="teamDescription" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.activationDate" />
						</td>
						<td align="left">
							<bean:write name="TeamForm" property="activationDate" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.terminationDate" />
						</td>
						<td align="left">
							<bean:write name="TeamForm" property="terminationDate" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.password" />
						</td>
						<td align="left">
							<bean:write name="TeamForm" property="password" />
						</td>
					</tr>
					<tr><td colspan="2"><hr /></td></tr>
				</table>
			</p>
		</div>
	</div>
	</td></tr></table>
</html:form>
