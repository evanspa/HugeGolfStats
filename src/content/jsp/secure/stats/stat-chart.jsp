<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.bean.chart.ChartCategory" %>

<%
	ChartCategory chartCategory;
	chartCategory = (ChartCategory)session.getAttribute(AttributeKeyConstants.CHART_CATEGORY);
%>

<script language="JavaScript">
	var NewAction = '<%= (String)request.getAttribute("pf-action") %>';
</script>

<!-- include common javascript -->
<jsp:include page="includes/common/include-javascript.jsp" />

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><%= chartCategory.getTitle() %></h2>
<br />
<jsp:include page="includes/common/include-printerfriendly.jsp" />
<br /><br />
<html:form styleId="mainForm" action="<%= (String)request.getAttribute("form-action") %>" onsubmit="return validateCreateAccountForm(this);">
	<html:hidden name="StatisticsForm" property="chartName" />
	<table class="form-table">
		<tr>
			<th style="width:${thwidth}"><bean:message key="yourstats.pickchart" /></th>
			<td><jsp:include page="includes/include-chartschoice.jsp" /></td>
		</tr>
	</table>
			
	<!-- input the course played -->
	<jsp:include page="includes/common/include-courseplayed.jsp" />

	<!-- input circumstances to filter-on -->
	<jsp:include page="includes/common/include-circumstancesinput.jsp" />

	<!-- input date range to filter-on -->
	<jsp:include page="includes/common/include-datesinput.jsp" />
			
	<p>
		<html:button property="dummy" onclick="clearDates(); return false;" styleClass="button">
			<bean:message key="button.cleardates" />
		</html:button>
		<html:submit styleClass="button" />
	</p>
	<br />
	<div class="section">
		<jsp:include page="includes/common/include-chart.jsp" />
	</div>
</html:form>