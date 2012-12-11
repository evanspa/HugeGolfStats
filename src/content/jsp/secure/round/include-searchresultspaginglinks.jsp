<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.struts.formbean.RoundForm" %>

<bean:size id="numResults" name="<%= AttributeKeyConstants.ROUNDS %>" />

<%
	int numPages, numSearchResults;
	RoundForm form;
	
	form = (RoundForm)session.getAttribute("RoundForm");
	numSearchResults = (Integer)session.getAttribute(AttributeKeyConstants.TOTAL_RESULTS_COUNT);
	numPages = numSearchResults / form.getMaxSearchResultsNum();
	if (numSearchResults % form.getMaxSearchResultsNum() == 0) {
		numPages--;
	}
	pageContext.setAttribute("numPages", numPages);
%>

<logic:notEmpty name="<%= AttributeKeyConstants.ROUNDS %>">
	<span style="font-weight: bold; font-size: smaller; font-family: arial">
		<bean:message key="pagination.searchresultpages" />
		<logic:greaterThan name="RoundForm" property="firstSearchResultNum" value="0">
			&nbsp;&nbsp;<a href="#" onclick="previousResultPage()"><<</a>
		</logic:greaterThan>
		&nbsp;
		<c:forEach var="j" begin="0" end="${numPages}">
			<logic:equal name="RoundForm" property="firstSearchResultNum" value="${j*RoundForm.maxSearchResultsNum}">
				<span style="color: blue; font-weight: bold; font-size: large">${j+1}</span>
			</logic:equal>
			<logic:notEqual name="RoundForm" property="firstSearchResultNum" value="${j*RoundForm.maxSearchResultsNum}">
				<a href="#" onclick="jumpToPage(${j*RoundForm.maxSearchResultsNum})">${j+1}</a>
			</logic:notEqual>
		</c:forEach>
		<logic:greaterThan name="TotalResultsCount" value="${numResults + RoundForm.firstSearchResultNum}">
			&nbsp;&nbsp;<a href="#" onclick="nextResultPage()">>></a>
		</logic:greaterThan>
	</span>
</logic:notEmpty>