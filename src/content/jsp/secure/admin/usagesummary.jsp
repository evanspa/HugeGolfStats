<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.dao.UsageSummaryDAO" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
	UsageSummaryDAO usageSummary;
	NewUtil util;
	SimpleDateFormat sdf;

	util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
	sdf = util.getDatetimeFormatDisplay();
	usageSummary = (UsageSummaryDAO)session.getAttribute(AttributeKeyConstants.USAGE_SUMMARY);
%>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "275px"); %>

<h2><bean:message key="admin.usagesummary.about.heading" /></h2>
<br />
<table class="form-table">
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.totalnumusers" />
		</th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.USAGE_SUMMARY %>" 
				property="totalUserCount" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.totalnumrounds" />
		</th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.USAGE_SUMMARY %>" 
				property="totalNumRounds" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.totalnumcourses" />
		</th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.USAGE_SUMMARY %>" 
				property="totalNumCourses" />
		</td>
	</tr>
</table>
<br />
<table class="form-table">
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.avg18holescore" />
		</th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.USAGE_SUMMARY %>" 
				property="averageEighteenHoleScore" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.high18holescore" />
		</th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.USAGE_SUMMARY %>" 
				property="highestEighteenHoleScore" />
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.low18holescore" />
		</th>
		<td>
			<bean:write name="<%= AttributeKeyConstants.USAGE_SUMMARY %>" 
				property="lowestEighteenHoleScore" />
		</td>
	</tr>
</table>
<br />
<table class="form-table">
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.dateoflastscoreentered" />
		</th>
		<td>
			<%= sdf.format(usageSummary.getDateOfLastScoreEntered()) %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.usagesummary.dateoflastlogin" />
		</td>
		<td>
			<%= sdf.format(usageSummary.getDateOfLastLogin()) %>
		</td>
	</tr>
</table>
