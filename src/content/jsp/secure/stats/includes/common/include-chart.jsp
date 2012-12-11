<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.servlet.ChartRenderingServlet" %>

<img src="<%= WebConstants.CONTEXT_ROOT %>/secure/ChartRenderingServlet?<%= ChartRenderingServlet.CHART_CLASS_PARAM %>=${StatisticsForm.chartClass}&<%= ChartRenderingServlet.WIDTH_PARAM %>=635&<%= ChartRenderingServlet.HEIGHT_PARAM %>=400&<%= ChartRenderingServlet.FROM_DATE_PARAM %>=${StatisticsForm.fromDate}&<%= ChartRenderingServlet.TO_DATE_PARAM %>=${StatisticsForm.toDate}&<%= ChartRenderingServlet.PANT_WEAR_TYPE_ID %>=${StatisticsForm.pantWearTypeId}&<%= ChartRenderingServlet.EYE_WEAR_TYPE_ID %>=${StatisticsForm.eyeWearTypeId}&<%= ChartRenderingServlet.HEAD_WEAR_TYPE_ID %>=${StatisticsForm.headWearTypeId}&<%= ChartRenderingServlet.WEATHER_TYPE_ID %>=${StatisticsForm.weatherConditionTypeId}&<%= ChartRenderingServlet.TOURNAMENT_TYPE_ID %>=${StatisticsForm.tournamentTypeId}&<%= ChartRenderingServlet.LOCOMOTION_TYPE_ID %>=${StatisticsForm.locomotionTypeId}&<%= ChartRenderingServlet.BAG_CARRYING_MECHANISM_ID %>=${StatisticsForm.bagCarryingMechanismId}&<%= ChartRenderingServlet.COURSE_PLAYED_ID %>=${StatisticsForm.coursePlayedId}" />