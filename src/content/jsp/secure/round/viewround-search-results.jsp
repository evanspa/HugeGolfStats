<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.dao.ScorecardSummaryDAO" %>
<%@ page import="name.paulevans.golf.LabelStringFactory" %>
<%@ page import="java.util.Locale" %>

<script language="JavaScript">
	
	function newSearch() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewRound-search.do";
		formObj.submit();
	}

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelRound.do";
		formObj.submit();
	}

	function nextResultPage() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewRound-search-results.do";
		formObj.firstSearchResultNum.value="${RoundForm.firstSearchResultNum + RoundForm.maxSearchResultsNum}";
		formObj.submit();
	}
	
	function previousResultPage() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewRound-search-results.do";
		formObj.firstSearchResultNum.value="${RoundForm.firstSearchResultNum - RoundForm.maxSearchResultsNum}";
		formObj.submit();
	}
	
	function jumpToPage(aFirstSearchResultNum) {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewRound-search-results.do";
		formObj.firstSearchResultNum.value=aFirstSearchResultNum;
		formObj.submit();
	}
</script>

<h2><bean:message key="round.view.searchresults.header" /></h2>
<html:form styleId="mainForm" focus="courseName" action="/secure/ViewRound-page1">
		
	<html:hidden property="firstSearchResultNum" />
	<html:hidden property="pageAction" value="<%= Constants.TRUE %>" />
			
	<h3><bean:message key="round.view.searchresults.pickroundtoview" /></h3>
	<jsp:include page="include-searchresultspaginglinks.jsp" />
	<table class="list-results">
		<tr>
			<th style="text-align:center">&nbsp;</th>
			<th style="text-align:center"><a href="ViewRound-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_COURSE_COL %>"><bean:message key="search.coursename" /></a></th>
			<th style="text-align:center"><a href="ViewRound-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_DATEPLAYED_COL %>"><bean:message key="search.round.dateplayed" /></a></th>
			<th style="text-align:center"><a href="ViewRound-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_NUMHOLESPLAYED_COL %>"><bean:message key="search.round.numholesplayed" /></a></th>
			<th style="text-align:center"><a href="ViewRound-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_SCORE_COL %>"><bean:message key="search.round.score" /></a></th>
			<th style="text-align:center"><a href="ViewRound-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_ISTOURNEY_COL %>"><bean:message key="search.round.istournament" /></a></th>
			<th style="text-align:center"><a href="ViewRound-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.ROUNDS_CITY_COL %>"><bean:message key="search.city" /></a></th>
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
		<logic:iterate indexId="i" id="round" name="<%= AttributeKeyConstants.ROUNDS %>">
			<%
				ScorecardSummaryDAO summary;
				NewUtil util;
						
				summary = (ScorecardSummaryDAO)pageContext.getAttribute("round");
				util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
				pageContext.setAttribute("datePlayed", util.format(summary.getScorecard().getDate()));
			%>
			<tr>
				<td style="text-align: center"><span style="font-size:larger">[</span><a href="ViewRound-page1.do?scorecardid=${round.scorecard.id}"><bean:message key="search.round.view" /></a><span style="font-size:larger">]</span></td>
				<td style="text-align: left">${round.scorecard.tee.course.description}</td>
				<td style="text-align: center">${datePlayed}</td>
				<td style="text-align: center">${round.numHolesPlayed}</td>
				<td style="text-align: center; font-weight: bold">${round.score}</td>
				<td style="text-align: center">${((round.scoreType == tscore) || (round.scoreType == tiscore)) ? yes : no}</td>
				<td style="text-align: left">${round.scorecard.tee.course.city}</td>
			</tr>
		</logic:iterate>
		<logic:empty name="<%= AttributeKeyConstants.ROUNDS %>">
			<tr>
				<td colspan="7">
					<bean:message key="round.search.noresults" />
				</td>
			</tr>
		</logic:empty>
	</table>
	<br />
	<jsp:include page="include-searchresultspaginglinks.jsp" />
	<br />
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:button property="dummy" onclick="newSearch()" styleClass="button">
			<bean:message key="button.newsearch" />
		</html:button>
	</p>
</html:form>
