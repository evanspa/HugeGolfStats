<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%= WebConstants.CONTEXT_ROOT %>/js/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"> 
	var cal = new CalendarPopup();
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">

	function collectPerHoleStatsOnChange() {
		var formObj = document.getElementById("mainForm");
	    var enableStatsToCollect = document.getElementById('collectPerHoleStats').options[document.getElementById('collectPerHoleStats').selectedIndex].value;
		if (enableStatsToCollect == 'true') newVal = false; else newVal = true;
		formObj.collectClubUsedOffTee.disabled=newVal;
		formObj.collectTeeShotDistance.disabled=newVal;
		formObj.collectNumPutts.disabled=newVal;
		formObj.collectFairwayHit.disabled=newVal;
		formObj.collectGir.disabled=newVal;
		formObj.collectApproachShotLine.disabled=newVal;
		formObj.collectApproachShotDistance.disabled=newVal;
		formObj.collectSandSave.disabled=newVal;
		formObj.collectUpDown.disabled=newVal;
	}
</SCRIPT>

<h3><bean:message key="round.circumstances" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.date" /></th>
		<td>
			<html:text property="date" size="25" styleClass="field" />
			<span style="font-size: larger">[</span><a href="#" onClick="cal.select(document.forms[1].date,'anchor1','MM/dd/yyyy'); return false;"
   NAME="anchor1" ID="anchor1"><bean:message key="round.pickdate" /></a><span style="font-size: larger">]</span>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.tournament" />
		</th>
		<td>
			<html:select property="isTournamentScore" styleClass="combo">
				<html:option value="true" key="option.true" />
				<html:option value="false" key="option.false" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.weatherconditions" />
		</th>
		<td>
			<html:select property="weatherConditionTypeId" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.WEATHER_CONDITION_TYPES %>"
					property="id"						
					labelProperty="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.headwearworn" />
		</th>
		<td>
			<html:select property="headWearTypeId" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.HEAD_WEAR_TYPES %>"
					property="id"						
					labelProperty="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.eyewearworn" />
		</th>
		<td>
			<html:select property="eyeWearTypeId" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.EYE_WEAR_TYPES %>"
					property="id"						
					labelProperty="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.pantwearworn" />
		</th>
		<td>
			<html:select property="pantWearTypeId" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.PANT_WEAR_TYPES %>"
					property="id"						
					labelProperty="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.cart" />
		</th>
		<td>
			<html:select property="usedCart" styleClass="combo">
				<html:option value="true" key="option.cart" />
				<html:option value="false" key="option.walk" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.caddie" />
		</th>
		<td>
			<html:select property="usedCaddie" styleClass="combo">
				<html:option value="true" key="option.true" />
				<html:option value="false" key="option.false" />
			</html:select>
		</td>
	</tr>	
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.scorer" />
		</th>
		<td>
			<html:text property="scorer" maxlength="256" size="25" styleClass="field" /> 
		</td>
	</tr>	
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.attestedby" />
		</th>
		<td>
			<html:text property="attestedBy" maxlength="256" size="25" styleClass="field" /> 
		</td>
	</tr>
	<tr>
		<th style="vertical-align:top">
			<bean:message key="round.journalnotes" />
		</th>
		<td>
			<html:textarea property="journalNotes" rows="6" style="width:100%" styleClass="field" /> 
		</td>
	</tr>
<!-- for now, we'll comment this it to keep the input as simple as possible

<tr>
		<th>
			<bean:message key="round.sleeves" />
		</th>
		<td>
			<html:select property="longSleevesWorn" styleClass="combo">
				<html:option value="true" key="option.sleeves.long" />
				<html:option value="false" key="option.sleeves.short" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			<bean:message key="round.vest" />
		</th>
		<td>
			<html:select property="vestWorn" styleClass="combo">
				<html:option value="true" key="option.true" />
				<html:option value="false" key="option.false" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			<bean:message key="round.jacket" />
		</th>
		<td>
			<html:select property="jacketWorn" styleClass="combo">
				<html:option value="true" key="option.true" />
				<html:option value="false" key="option.false" />
			</html:select>
		</td>
	</tr>-->

</table>