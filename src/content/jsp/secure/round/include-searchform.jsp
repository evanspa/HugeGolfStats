<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%= WebConstants.CONTEXT_ROOT %>/js/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"> 
	var cal = new CalendarPopup();
</SCRIPT>

<table class="form-table">

	<!-- input country -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.country" /></th>
		<td>
			<html:select styleId="countryDD" property="countryId" onchange="countrySelected()" styleClass="combo">
				<html:option key="option.any" value="<%= "" + Constants.ANY_OPTION_VAL %>" />
				<html:options
					collection="<%= AttributeKeyConstants.COUNTRIES %>"
					property="id"						
					labelProperty="name" />
			</html:select>
		</td>
	</tr>
	
	<!-- input state/province -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.state_province" /></th>
		<td>
			<html:select styleId="stateDD" property="stateProvinceId" styleClass="combo">
				<html:option key="option.any" value="<%= "" + Constants.ANY_OPTION_VAL %>" />
				<html:options
					collection="<%= AttributeKeyConstants.STATE_PROVINCES %>"
					property="id.id"						
					labelProperty="name" />	
			</html:select>
		</td>
	</tr>
	
	<!-- input city -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.city" /></th>
		<td><html:text size="40" property="city"  styleClass="field"/></td>
	</tr>
	
	<!-- input course name -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.course" /></th>
		<td><html:text size="40" property="courseName"  styleClass="field"/></td>
	</tr>	
	
	<!-- input date -->
	<tr>
		<th style="width:${thwidth}"><bean:message key="search.round.date" /></th>
		<td>
			<html:text size="25" property="date" styleClass="field"/>
			[<a href="#" onClick="cal.select(document.forms[1].date,'anchor1','MM/dd/yyyy'); return false;"
   NAME="anchor1" ID="anchor1"><bean:message key="round.pickdate" /></a>]
		</td>
	</tr>
</table>