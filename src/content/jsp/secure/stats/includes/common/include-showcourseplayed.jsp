<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<br />
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="yourstats.courseplayed.showcourseplayed" /></th>
		<td><bean:write name="StatisticsForm" property="coursePlayedDescription" />
	</tr>
</table>