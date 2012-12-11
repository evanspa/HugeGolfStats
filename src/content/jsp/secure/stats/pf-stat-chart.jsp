<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.bean.chart.ChartCategory" %>

<%
	ChartCategory chartCategory;
	chartCategory = (ChartCategory)session.getAttribute(AttributeKeyConstants.CHART_CATEGORY);
%>

<script language="JavaScript">
	var GoBackAction = '<%= (String)request.getAttribute("goback-action") %>';
</script>

<!-- include common javascript -->
<jsp:include page="includes/common/include-pfjavascript.jsp" />

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><%= chartCategory.getTitle() %></h2>
<br />
<jsp:include page="includes/common/include-print.jsp" />
<br /><br />
<html:form styleId="mainForm" action="<%= (String)request.getAttribute("form-action") %>" onsubmit="return validateCreateAccountForm(this);">
	<table class="form-table">
		<tr>
			<th style="width:${thwidth}"><bean:message key="yourstats.pickchart" /></th>
			<td><bean:write name="StatisticsForm" property="chartName" /></td>
		</tr>
	</table>
			
	<!-- show the course played -->
	<jsp:include page="includes/common/include-showcourseplayed.jsp" />

	<!-- show circumstances to filter-on -->
	<jsp:include page="includes/common/include-showcircumstancesinput.jsp" />

	<!-- show date range to filter-on -->
	<jsp:include page="includes/common/include-showdatesinput.jsp" />
			
	<br />
	<div class="section"><jsp:include page="includes/common/include-chart.jsp" /></div>
</html:form>