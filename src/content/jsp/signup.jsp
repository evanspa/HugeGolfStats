<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib  uri="/tags/struts-html" prefix="html" %>

<html:errors/>

<html:form action="/CreateAccount">
	<table>
		<tr>
			<td align="right"><bean:message key="signup.form.firstname" /></td>
			<td align="left"><html:text property="firstName" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<html:submit>
					<bean:message key="signup.form.submitbtn" />
				</html:submit>
			</td>
		</tr>
	</table>
</html:form>