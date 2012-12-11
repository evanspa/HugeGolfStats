<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.bean.chart.ChartCategory" %>
<%@ page import="name.paulevans.golf.bean.chart.ChartGroup" %>
<%@ page import="name.paulevans.golf.bean.chart.ChartWrapper" %>
<%@ page import="name.paulevans.golf.web.struts.formbean.StatisticsForm" %>
<%@ page import="java.util.List" %>

<%
	ChartCategory chartCategory;
	StatisticsForm form;
	List<ChartGroup> chartGroupsLst;
	ChartGroup chartGroups[];
	List<ChartWrapper> chartsLst;
	ChartWrapper charts[];
	int loop, innerLoop;
	String chartClass, optionTitle;
	boolean makeSelected, doSelectionCheck;
	
	chartCategory = (ChartCategory)session.getAttribute(AttributeKeyConstants.CHART_CATEGORY);
	chartGroupsLst = chartCategory.getChartGroups();
	chartGroups = chartGroupsLst.toArray(new ChartGroup[chartGroupsLst.size()]);
	form = (StatisticsForm)session.getAttribute("StatisticsForm");
	makeSelected = false;
	doSelectionCheck = true;
%>

<html:select name="StatisticsForm" property="chartClass" onchange="chartSelected()" styleClass="widecombo">
	<%
		for (loop = 0; loop < chartGroups.length; loop++) {
			chartsLst = chartGroups[loop].getCharts();
			charts = chartsLst.toArray(new ChartWrapper[chartsLst.size()]);
			for (innerLoop = 0; innerLoop < charts.length; innerLoop++) {
				chartClass = charts[innerLoop].getClass().getName();
				optionTitle = charts[innerLoop].getTitle();
				if (doSelectionCheck) {
					if (chartClass.equals(form.getChartClass())) {
						makeSelected = true;
						doSelectionCheck = false; // we've found our selected option, so we can turn off subsequent comparisons...
					}
				}
				if (makeSelected) { %>
					<option value="<%= chartClass %>" title="<%= optionTitle %>" selected="selected"><%= optionTitle %></option>
	<%				makeSelected = false; // we've got our selected option; we can now turn off this boolean...
				} else {			%>
					<option value="<%= chartClass %>" title="<%= optionTitle %>"><%= optionTitle %></option>
	<%			}
			}
			if (loop + 1 < chartGroups.length) { %>
				<option value="-">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</option>
	<%		}
		}
	%>		
</html:select>