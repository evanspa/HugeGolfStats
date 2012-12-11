<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.bean.chart.ChartCategory" %>

<h2><bean:message key="yourstats.about.heading" /></h2>
<br />
<div class="section"
	<p><bean:message key="yourstats.about.text" /></p>
	<p>
		<ul>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/Summary-stats.do"><bean:message key="menu.loggedin.menuitem.statisticssummary" /></a></li>	
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/Scoring-stats.do?<%= AttributeKeyConstants.CHART_CATEGORY_PARAM %>=<%= ChartCategory.CHART_CATEGORY_SCORING %>"><bean:message key="menu.loggedin.menuitem.scoringstats" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/Putting-stats.do?<%= AttributeKeyConstants.CHART_CATEGORY_PARAM %>=<%= ChartCategory.CHART_CATEGORY_PUTTING %>"><bean:message key="menu.loggedin.menuitem.puttingstats" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/Teeshot-stats.do?<%= AttributeKeyConstants.CHART_CATEGORY_PARAM %>=<%= ChartCategory.CHART_CATEGORY_TEESHOT %>"><bean:message key="menu.loggedin.menuitem.teeshotstats" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/GIR-stats.do?<%= AttributeKeyConstants.CHART_CATEGORY_PARAM %>=<%= ChartCategory.CHART_CATEGORY_GIR %>"><bean:message key="menu.loggedin.menuitem.girstats" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/Approachshot-stats.do?<%= AttributeKeyConstants.CHART_CATEGORY_PARAM %>=<%= ChartCategory.CHART_CATEGORY_APPROACHSHOT %>"><bean:message key="menu.loggedin.menuitem.approachshotstats" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/AroundGreen-stats.do?<%= AttributeKeyConstants.CHART_CATEGORY_PARAM %>=<%= ChartCategory.CHART_CATEGORY_AROUNDGREEN %>"><bean:message key="menu.loggedin.menuitem.aroundgreenstats" /></a></li>
		</ul>
	</p>
</div>