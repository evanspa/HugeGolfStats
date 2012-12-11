<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.struts.formbean.RoundForm" %>
<%@ page import="org.apache.commons.lang.*" %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%= WebConstants.CONTEXT_ROOT %>/js/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"> 
	var cal = new CalendarPopup();
</SCRIPT>

<%

	RoundForm form = (RoundForm)session.getAttribute("RoundForm");
	String journalNotes = form.getJournalNotes();
%>

<h3><bean:message key="round.circumstances" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.date" /></th>
		<td><bean:write name="RoundForm" property="date" /></td>
	</tr>
	<tr>
		<th><bean:message key="round.tournament" /></th>
		<td>
			<logic:equal name="RoundForm" property="isTournamentScore" value="true">
				<bean:message key="option.true" />
			</logic:equal>
			<logic:equal name="RoundForm" property="isTournamentScore" value="false">
				<bean:message key="option.false" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.weatherconditions" /></th>
		<td><bean:write name="RoundForm" property="weatherConditionDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.headwearworn" /></th>
		<td><bean:write name="RoundForm" property="headWearDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.eyewearworn" /></th>
		<td><bean:write name="RoundForm" property="eyeWearDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.pantwearworn" /></th>
		<td><bean:write name="RoundForm" property="pantWearDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.cart" /></th>
		<td>
			<logic:equal value="true" name="RoundForm" property="usedCart">
				<bean:message key="option.cart" />
			</logic:equal>
			<logic:equal value="false" name="RoundForm" property="usedCart">
				<bean:message key="option.walk" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.caddie" /></th>
		<td>
			<logic:equal value="true" name="RoundForm" property="usedCaddie">
				<bean:message key="option.true" />
			</logic:equal>
			<logic:equal value="false" name="RoundForm" property="usedCaddie">
				<bean:message key="option.false" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.scorer" /></th>
		<td><bean:write name="RoundForm" property="scorer" /></td>
	</tr>	
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.attestedby" /></td>
		<td><bean:write name="RoundForm" property="attestedBy" /></td>
	</tr>
	<tr>
		<th style="vertical-align:top; width:${thwidth}"><bean:message key="round.journalnotes" /></th>
		<td><%= StringUtils.replace(WordUtils.wrap(StringEscapeUtils.escapeHtml(journalNotes), 80), System.getProperty("line.separator"), "<br>") %></td>
	</tr>
	
<!-- for now, we'll comment this it to keep the input as simple as possible
	<tr>
		<th><bean:message key="round.sleeves" /></th>
		<td>
			<logic:equal name="RoundForm" property="longSleevesWorn" value="true">
				<bean:message key="option.sleeves.long" />
			</logic:equal>
			<logic:equal name="RoundForm" property="longSleevesWorn" value="false">
				<bean:message key="option.sleeves.short" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th><bean:message key="round.vest" /></th>
		<td>
			<logic:equal name="RoundForm" property="vestWorn" value="true">
				<bean:message key="option.true" />
			</logic:equal>
			<logic:equal name="RoundForm" property="vestWorn" value="false">
				<bean:message key="option.false" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th><bean:message key="round.jacket" /></th>
		<td>
			<logic:equal name="RoundForm" property="jacketWorn" value="true">
				<bean:message key="option.true" />
			</logic:equal>
			<logic:equal name="RoundForm" property="jacketWorn" value="false">
				<bean:message key="option.false" />
			</logic:equal>
		</td>
	</tr>-->
</table>