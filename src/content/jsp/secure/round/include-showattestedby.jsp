<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<table class="roundcircumstances">
	<tr>
		<td align="right" style="font-weight: bold">
			<bean:message key="round.scorer" />
		</td>
		<td align="left">
			<bean:write name="RoundForm" property="scorer" /> 
		</td>
	</tr>	
	<tr>
		<td align="right" style="font-weight: bold">
			<bean:message key="round.attestedby" />
		</td>
		<td align="left">
			<bean:write name="RoundForm" property="attestedBy" /> 
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold" valign="top">
			<bean:message key="round.journalnotes" />
		</td>
		<td align="left">
			<html:textarea name="RoundForm" property="journalNotes" readonly="true" rows="6" cols="21" /> 
		</td>
	</tr>
</table>