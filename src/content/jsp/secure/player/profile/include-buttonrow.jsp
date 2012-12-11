<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<tr>
	<td>
		<html:submit>
			<bean:message key="button.save" />
		</html:submit>
		<html:button property="dummy" onclick="cancel()">
			<bean:message key="button.cancel" />
		</html:button>
	</td>
</tr>