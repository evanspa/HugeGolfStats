<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>

<script language="JavaScript">
	function cancel() {
		var formObj = document.getElementById("forgotemailaddr");
		formObj.action="<%= WebUtils.httpPrefix(request) %>/Welcome.do";
		formObj.submit();
	}	
</script>

<br />
<html:form styleId="forgotemailaddr" focus="firstName" action="/ForgotEmailAddress-submit">
	<table cellpadding="1" cellspacing="1">
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="forgotemailaddress.firstname" />
			</td>
			<td align="left">
				<html:text size="35" property="firstName" />
			</td>
		</tr>
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="forgotemailaddress.lastname" />
			</td>
			<td align="left">
				<html:text size="35" property="lastName" />
			</td>
		</tr>		
		<tr>
			<td align="center" colspan="2">
				<input type="submit" value="Submit" />
				<html:button property="dummy" onclick="cancel()">
					<bean:message key="button.cancel" />
				</html:button>
			</td>
		</tr>
	</table>
</html:form>