<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "190px"); %>

<script language="JavaScript">

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action = "Admin.do";
		formObj.submit();
	}

</script>

<h1 class="pagetitle"><bean:message key="msgbroadcast.header" /></h1>

<div class="column1-unit">
	<div class="generalform">
		<html:form styleId="mainForm" focus="subject" action="/secure/admin/SendBroadcastEmail-submit">
		
			<!-- input personal-info section -->
			<table>
				<tr>
					<th style="width:${thwidth}"><bean:message key="msgbroadcast.subject" /></th>
					<td>
						<html:text size="55" property="subject" styleClass="field" />
					</td>
				</tr>
				<tr>
					<th style="width:${thwidth}; vertical-align:top"><bean:message key="msgbroadcast.message" /><span class="required">*</span></th>
					<td><html:textarea rows="15" property="message" styleClass="field" style="width:100%" /></td>
				</tr>
			</table>

			<br />
			<br />
			<p>
				<html:button property="dummy" onclick="cancel()" styleClass="button">
					<bean:message key="button.cancel" />
				</html:button>
				<html:submit styleClass="button" />
			</p>
		</html:form>
	</div>
</div>