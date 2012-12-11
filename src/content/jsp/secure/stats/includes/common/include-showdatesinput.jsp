<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<br />
<table class="form-table">
<tr>
	<th style="width:${thwidth}"><bean:message key="yourstats.fromdate" /></th>
	<td><bean:write name="StatisticsForm" property="fromDateDescription" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message key="yourstats.todate" /></th>
	<td><bean:write name="StatisticsForm" property="toDateDescription" /></td>
</tr>
</table>
