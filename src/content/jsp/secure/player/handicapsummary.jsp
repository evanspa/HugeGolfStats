<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.dao.ScorecardSummaryDAO" %>

<bean:size id="numRounds" name="<%= AttributeKeyConstants.PLAYER %>" property="recentMergedRounds" />
<bean:size id="numTRounds" name="<%= AttributeKeyConstants.PLAYER %>" property="eligibleTournamentRounds" />

<h2><bean:message key="hcpsummary.header" /></h2>
<br />
<table class="list-results">
	<tr>
		<th style="text-align:right"><bean:message key="hcpsummary.handicapindex" /></th>
		<td>
			<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex">
				<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex" />
			</logic:present>
			<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex">
				<a href="###" onclick="alert('<bean:message key="header.menu.player.handicapindex.whencalculatedmsg" />'); return false;">
					<bean:message key="header.menu.player.handicapindex.notyetcalculated" />
				</a>
			</logic:notPresent>
		</td>
	</tr>
	<tr>
		<th style="text-align:right"><bean:message key="hcpsummary.handicapindexreductionvalue" /></th>
		<td>
			<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="reductionValue">
				<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="reductionValue" />
			</logic:present>
			<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="reductionValue">
				<bean:message key="general.label.notapplicable" />
			</logic:notPresent>
		</td>
	</tr>
	<tr>
		<th style="text-align:right"><bean:message key="hcpsummary.reducedhandicapindex" /></th>
		<td>
			<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="reducedHandicapIndex">
				<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="reducedHandicapIndex" />
			</logic:present>
			<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="reducedHandicapIndex">
				<a href="###" onclick="alert('<bean:message key="header.menu.player.handicapindex.whencalculatedmsg" />'); return false;">
					<bean:message key="header.menu.player.handicapindex.notyetcalculated" />
				</a>
			</logic:notPresent>
		</td>
	</tr>
</table>
<!--<h1><bean:message key="hcpsummary.scorehistory" />&nbsp;<bean:message key="hcpsummary.mostrecentrounds" arg0="${numRounds}" /></h1>-->
<br />
<table class="list-results">
	<tr>
		<th><a href="###" onclick="alert('<bean:message key="hcpsummary.usedexplanation" />'); return false;"><bean:message key="hcpsummary.headers.used" /></a></th>
		<th><a href="###" onclick="alert('<bean:message key="hcpsummary.scoretypeexplanation" />'); return false;"><bean:message key="hcpsummary.headers.scoretype" /></a></th>
		<th><bean:message key="hcpsummary.headers.dateplayed" /></th>
		<th><bean:message key="hcpsummary.headers.score" /></th>
		<th><bean:message key="hcpsummary.headers.ratingslope" /></th>
		<th><bean:message key="hcpsummary.headers.differential" /></th>
		<th><bean:message key="hcpsummary.headers.coursename" /></th>
	</tr>
	<logic:equal name="numRounds" value="0">
		<tr>
			<td colspan="7"><bean:message key="hcpsummary.noeligibleroundspostedyet" /></td>
		</tr>
	</logic:equal>
	<logic:greaterThan name="numRounds" value="0">
		<logic:iterate indexId="i" id="round" name="<%= AttributeKeyConstants.PLAYER %>" property="recentMergedRounds">
			<%
				ScorecardSummaryDAO summary;
				NewUtil util;
				
				summary = (ScorecardSummaryDAO)pageContext.getAttribute("round");
				util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
				pageContext.setAttribute("datePlayed", util.format(summary.getDatePlayed()));
			%>
			<tr>
				<td style="font-family: courier; text-align: center">${round.usedInHandicapCalculation ? "*" : "&nbsp;"}</td>
				<td style="font-family: courier; text-align: center">${round.scoreType}</td>
				<td style="text-align: center">${datePlayed}</td>
				<td style="font-weight: bold; text-align: center">
					<logic:notEqual name="round" property="scoreType" value="<%= Constants.COMBINED_SCORE_TYPE %>">
						<a href="ViewRound-page1.do?scorecardid=${round.id}">${round.score}</a>
					</logic:notEqual>
					<logic:equal name="round" property="scoreType" value="<%= Constants.COMBINED_SCORE_TYPE %>">
						${round.score}
					</logic:equal>
				</td>
				<td style="text-align:center">${round.rating} / ${round.slope}</td>
				<td style="text-align:center">${round.differential}</td>
				<td style="text-align: left">
					<a href="ViewCourse-page1.do?courseid=${round.courseId}">${round.courseName}</a>
				</td>
			</tr>
		</logic:iterate>
	</logic:greaterThan>
</table>
<br />
<h2><bean:message key="hcpsummary.twolowesttournamentscores" /></h2>
<br />
<table class="list-results">
	<tr>
		<th><bean:message key="hcpsummary.headers.scoretype" /></th>
		<th><bean:message key="hcpsummary.headers.dateplayed" /></th>
		<th><bean:message key="hcpsummary.headers.score" /></th>
		<th><bean:message key="hcpsummary.headers.ratingslope" /></th>
		<th><bean:message key="hcpsummary.headers.differential" /></th>
		<th><bean:message key="hcpsummary.headers.coursename" /></th>
	</tr>
	<logic:equal name="numTRounds" value="0">
		<tr>
			<td colspan="6"><bean:message key="hcpsummary.noeligibletournamentroundspostedyet" /></td>
		</tr>
	</logic:equal>
	<logic:greaterThan name="numTRounds" value="0">
		<logic:iterate indexId="i" id="round" name="<%= AttributeKeyConstants.PLAYER %>" property="eligibleTournamentRounds">
			<%
				ScorecardSummaryDAO summary;
				NewUtil util;
			
				summary = (ScorecardSummaryDAO)pageContext.getAttribute("round");
				util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
				pageContext.setAttribute("datePlayed", util.format(summary.getDatePlayed()));
			%>
			<tr class="${i % 2 == 0 ? "searchresult-even" : "searchresult-odd"}">
				<td style="text-align: center; font-family: courier">${round.scoreType}</td>
				<td style="text-align: center">${datePlayed}</td>
				<td style="text-align: center; font-weight: bold">
					<a href="ViewRound-page1.do?scorecardid=${round.id}">${round.score}</a>
				</td>
				<td style="text-align: center">${round.rating} / ${round.slope}</td>
				<td style="text-align: center">${round.differential}</td>
				<td style="text-align: left">
					<a href="ViewCourse-page1.do?courseid=${round.courseId}">${round.courseName}</a>
				</td>
			</tr>
		</logic:iterate>
	</logic:greaterThan>
</table>
</div>
