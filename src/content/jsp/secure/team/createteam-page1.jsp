<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.Constants" %>

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
	
	function countrySelected() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Refresh-CreateTeam-page1.do?<%= AttributeKeyConstants.COUNTRY_CHANGED %>=<%= Constants.TRUE %>"
		formObj.submit();
	}
</script>

<html:form styleId="mainForm" focus="teamName" action="/secure/CreateTeam-page1-submit">
	<table width="85%"><tr><td>
	<div class="box">
		<h3><bean:message key="team.add.page1.header" /></h3>
		<div>
			<p>
				<br />
				<table cellpadding="1" cellspacing="1">
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.country" /><span class="required">*</span>
						</td>
						<td align="left">
							<html:select property="countryId" onchange="countrySelected()">
								<html:options
									collection="<%= AttributeKeyConstants.COUNTRIES %>"
									property="id"						
									labelProperty="name" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.state_province" /><span class="required">*</span>
						</td>
						<td align="left">
							<html:select property="stateProvinceId">
								<html:options
									collection="<%= AttributeKeyConstants.STATE_PROVINCES %>"
									property="id.id"						
									labelProperty="name" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.name" /><span class="required">*</span>
						</td>
						<td align="left">
							<html:text property="teamName" size="30" />
						</td>
					</tr>
					<tr>
						<td align="right" valign="top" style="font-weight: bold">
							<bean:message key="team.description" />
						</td>
						<td align="left">
							<html:textarea property="teamDescription" rows="4" cols="40" />
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.activationDate" /><span class="required">*</span>
						</td>
						<td align="left">
							<html:text property="activationDate" size="25" />
							<span style="font-size: larger">[</span><a href="#" onClick="cal.select(formObj.activationDate,'anchor1','MM/dd/yyyy'); return false;"
   NAME="anchor1" ID="anchor1"><bean:message key="team.pickdate" /></a><span style="font-size: larger">]</span>
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.terminationDate" /><span class="required">*</span>
						</td>
						<td align="left">
							<html:text property="terminationDate" size="25" />
							<span style="font-size: larger">[</span><a href="#" onClick="cal.select(formObj.terminationDate,'anchor1','MM/dd/yyyy'); return false;"
   NAME="anchor1" ID="anchor1"><bean:message key="team.pickdate" /></a><span style="font-size: larger">]</span>
						</td>
					</tr>
					<tr>
						<td align="right" style="font-weight: bold">
							<bean:message key="team.password" /><span class="required">*</span>
						</td>
						<td align="left">
							<html:text property="password" size="25" />
							<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="team.password.explanation" />'); return false;"><bean:message key="team.password.explanation.link" /></a><span style="font-size: larger">]</span>
						</td>
					</tr>
					<tr><td colspan="2"><hr /></td></tr>
					<tr>
						<td colspan="2" align="left">
							<html:submit><bean:message key="button.save" /></html:submit>
								<html:button property="dummy" onclick="cancel()">
								<bean:message key="button.cancel" />
							</html:button>
						</td>
					</tr>
				</table>
			</p>
		</div>
	</div>
	</td></tr></table>
</html:form>
