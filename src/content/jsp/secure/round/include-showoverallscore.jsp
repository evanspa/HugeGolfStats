<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<h3><bean:message key="round.showoverallscore.header" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.overallscore" /></th>
		<td><bean:write name="RoundForm" property="overallScore" /></td>
	</tr>
</table>