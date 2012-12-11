<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.servlet.ChartRenderingServlet" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>


<h2><bean:message key="summary.header" /></h2>

<br />
<div class="section">
	<table>
		<tr>
			<td style="vertical-align:top; padding:10px">
				<jsp:include page="include-statisticssummary.jsp" />
			</td>
			<td style="vertical-align:top; padding:10px">
				<table>
					<tr>
						<td style="border: thin solid #CCC">
							<img src="<%= WebConstants.CONTEXT_ROOT %>/secure/ChartRenderingServlet?<%= ChartRenderingServlet.CHART_CLASS_PARAM %>=name.paulevans.golf.bean.chart.scoring.EighteenHoleScoresTrend&<%= ChartRenderingServlet.WIDTH_PARAM %>=635&<%= ChartRenderingServlet.HEIGHT_PARAM %>=400&<%= ChartRenderingServlet.FROM_DATE_PARAM %>=&<%= ChartRenderingServlet.TO_DATE_PARAM %>=&<%= ChartRenderingServlet.PANT_WEAR_TYPE_ID %>=-99&<%= ChartRenderingServlet.EYE_WEAR_TYPE_ID %>=<%= Constants.ALL_OPTION_VAL %>&<%= ChartRenderingServlet.HEAD_WEAR_TYPE_ID %>=<%= Constants.ALL_OPTION_VAL %>&<%= ChartRenderingServlet.WEATHER_TYPE_ID %>=<%= Constants.ALL_OPTION_VAL %>&<%= ChartRenderingServlet.TOURNAMENT_TYPE_ID %>=<%= Constants.ALL_OPTION_VAL %>&<%= ChartRenderingServlet.LOCOMOTION_TYPE_ID %>=<%= Constants.ALL_OPTION_VAL %>&<%= ChartRenderingServlet.BAG_CARRYING_MECHANISM_ID %>=<%= Constants.ALL_OPTION_VAL %>&<%= ChartRenderingServlet.COURSE_PLAYED_ID %>=<%= Constants.ALL_OPTION_VAL %>" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>