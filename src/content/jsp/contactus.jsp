<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.Constants" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "230px"); %>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action = "Welcome.do";
		formObj.submit();
	}

</script>

<h2><bean:message key="contactus.header" /></h2>
<br />
<html:form styleId="mainForm" focus="firstName" action="${requestScope.formActionPrefix}/ContactUs-submit">

	<!-- input personal-info section -->
	<table class="form-table">
		<tr>
			<th style="width:${thwidth}"><bean:message key="contactus.name" /><span class="required">*</span></th>
			<td><html:text size="55" property="name" styleClass="field" /></td>
		</tr>
		<tr>
			<th style="width:${thwidth}"><bean:message key="contactus.emailaddress" /><span class="required">*</span></th>
			<td><html:text size="55" property="fromEmail" styleClass="field" /></td>
		</tr>
		<tr>
			<th style="width:${thwidth}"><bean:message key="contactus.about" /></th>
			<td>
				<html:select property="messageType" styleClass="combo">
					<html:option value="<%= "" + Constants.MSGTYPE_ENHANCEMENTREQUEST %>"><bean:message key="contactus.about.options.enhancementrequest" /></html:option>
					<html:option value="<%= "" + Constants.MSGTYPE_GENERALQUESTION %>"><bean:message key="contactus.about.options.generalquestion" /></html:option>
					<html:option value="<%= "" + Constants.MSGTYPE_OTHER %>"><bean:message key="contactus.about.options.other" /></html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<th style="width:${thwidth}; vertical-align:top"><bean:message key="contactus.message" /><span class="required">*</span></th>
			<td><html:textarea rows="15" property="message" styleClass="field" style="width:100%" /></td>
		</tr>
	</table>
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button" />
	</p>
</html:form>