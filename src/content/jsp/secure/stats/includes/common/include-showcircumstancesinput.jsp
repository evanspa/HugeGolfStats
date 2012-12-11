<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<br />
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.tournament" /></th>
		<td><bean:write name="StatisticsForm" property="tournamentTypeDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.weatherconditions" /></th>
		<td><bean:write name="StatisticsForm" property="weatherConditionTypeDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.headwearworn" /></th>
		<td><bean:write name="StatisticsForm" property="headWearTypeDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.eyewearworn" /></th>
		<td><bean:write name="StatisticsForm" property="eyeWearTypeDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.pantwearworn" /></th>
		<td><bean:write name="StatisticsForm" property="pantWearTypeDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.cart" /></th>
		<td><bean:write name="StatisticsForm" property="locomotionTypeDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.caddie" /></th>
		<td><bean:write name="StatisticsForm" property="bagCarryingMechanismDescription" /></td>
	</tr>	
</table>