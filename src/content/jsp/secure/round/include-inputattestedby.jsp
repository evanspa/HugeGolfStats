<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<table>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.scorer" />
		</th>
		<td>
			<html:text property="scorer" maxlength="256" size="25" /> 
		</td>
	</tr>	
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.attestedby" />
		</th>
		<td>
			<html:text property="attestedBy" maxlength="256" size="25" /> 
		</td>
	</tr>
	<tr>
		<th style="vertical-align:top; width:${thwidth}">
			<bean:message key="round.journalnotes" />
		</th>
		<td>
			<html:textarea property="journalNotes" rows="6" style="width:100%" /> 
		</td>
	</tr>
</table>