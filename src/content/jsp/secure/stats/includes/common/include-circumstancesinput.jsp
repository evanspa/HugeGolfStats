<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>

<h3><bean:message key="yourstats.circumstances.header" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.tournament" />
		</th>
		<td>
			<html:select property="tournamentTypeId" styleClass="combo">
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
				<html:option value="<%= "" + Constants.TRUE_INTEGER %>" key="option.true" />
				<html:option value="<%= "" + Constants.FALSE_INTEGER %>" key="option.false" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.weatherconditions" />
		</th>
		<td>
			<html:select property="weatherConditionTypeId" styleClass="combo">
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
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
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
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
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
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
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
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
			<html:select property="locomotionTypeId" styleClass="combo">
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
				<html:option value="<%= "" + Constants.TRUE_INTEGER %>" key="option.cart" />
				<html:option value="<%= "" + Constants.FALSE_INTEGER %>" key="option.walk" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="round.caddie" />
		</th>
		<td>
			<html:select property="bagCarryingMechanismId" styleClass="combo">
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
				<html:option value="<%= "" + Constants.TRUE_INTEGER %>" key="option.true" />
				<html:option value="<%= "" + Constants.FALSE_INTEGER %>" key="option.false" />
			</html:select>
		</td>
	</tr>	
</table>