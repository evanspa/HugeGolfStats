<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.dao.ScorecardSummaryDAO" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.LabelStringFactory" %>
<%@ page import="java.util.Locale" %>

<bean:size id="numRounds" name="<%= AttributeKeyConstants.PLAYER %>" property="rounds" />

<h2><bean:message key="recentrounds.scorehistory" />&nbsp;-&nbsp;<bean:message key="recentrounds.mostrecentrounds" arg0="${numRounds}" /></h2>
<br />
<table class="list-results">
	<tr>
		<th>&nbsp;</th>
		<th><a href="RecentRounds-resort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_COURSE_COL %>"><bean:message key="search.coursename" /></a></th>
		<th><a href="RecentRounds-resort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_DATEPLAYED_COL %>"><bean:message key="search.round.dateplayed" /></a></th>
		<th><a href="RecentRounds-resort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_NUMHOLESPLAYED_COL %>"><bean:message key="search.round.numholesplayed" /></a></th>
		<th><a href="RecentRounds-resort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_SCORE_COL %>"><bean:message key="search.round.score" /></a></th>
		<th><a href="RecentRounds-resort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_ISTOURNEY_COL %>"><bean:message key="search.round.istournament" /></a></th>
	</tr>
	
	<%
		// put constants into page scope...
		LabelStringFactory strFactory;
			
		strFactory = LabelStringFactory.getInstance((Locale)session.getAttribute(AttributeKeyConstants.LOCALE));
		pageContext.setAttribute("tscore", Constants.TOURNAMENT_SCORE_TYPE);
		pageContext.setAttribute("tiscore", Constants.TOURNAMENT_INTERNET_SCORE_TYPE);
		pageContext.setAttribute("yes", strFactory.getString("option.true"));
		pageContext.setAttribute("no", strFactory.getString("option.false"));
	%>
	<logic:iterate indexId="i" id="round" name="<%= AttributeKeyConstants.PLAYER %>" property="rounds">
		<%
			ScorecardSummaryDAO summary;
			NewUtil util;
						
			summary = (ScorecardSummaryDAO)pageContext.getAttribute("round");
			util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
			pageContext.setAttribute("datePlayed", util.format(summary.getDatePlayed()));
		%>
		<tr>
			<td style="text-align: center"><span style="font-size:larger">[</span><a href="ViewRound-page1.do?scorecardid=${round.scorecardId}"><bean:message key="search.round.view" /></a><span style="font-size:larger">]</span></td>
			<td style="text-align: left">
				<a href="ViewCourse-page1.do?courseid=${round.courseId}">${round.courseName}</a>
			</td>
			<td style="text-align: center">${datePlayed}</td>
			<td style="text-align: center">${round.numHolesPlayed}</td>
			<td style="text-align: center; font-weight: bold">${round.score}</td>
			<td style="text-align: center">${((round.scoreType == tscore) || (round.scoreType == tiscore)) ? yes : no}</td>
		</tr>
	</logic:iterate>
	<logic:empty name="<%= AttributeKeyConstants.PLAYER %>" property="rounds">
		<tr>
			<td colspan="6">
				<bean:message key="recentrounds.noroundspostedyet" />
			</td>
		</tr>
	</logic:empty>
</table>