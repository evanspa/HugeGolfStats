<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<script language="JavaScript">

	function newSearch() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewPlayer-search.do";
		formObj.submit();
	}

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelAdmin.do";
		formObj.submit();
	}
	
	function nextResultPage() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewPlayer-search-results.do";
		formObj.firstSearchResultNum.value="${ProfileForm.firstSearchResultNum + ProfileForm.maxSearchResultsNum}";
		formObj.submit();
	}
	
	function previousResultPage() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewPlayer-search-results.do";
		formObj.firstSearchResultNum.value="${ProfileForm.firstSearchResultNum - ProfileForm.maxSearchResultsNum}";
		formObj.submit();
	}
	
	function jumpToPage(aFirstSearchResultNum) {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewPlayer-search-results.do";
		formObj.firstSearchResultNum.value=aFirstSearchResultNum;
		formObj.submit();
	}
</script>

<h2><bean:message key="admin.player.view.searchresults.header" /></h2>

<html:form styleId="mainForm" action="/secure/admin/ViewPlayer-search">
			
	<html:hidden property="firstSearchResultNum" />
	<html:hidden property="pageAction" value="<%= Constants.TRUE %>" />

	<h3><bean:message key="admin.player.searchresults.pickplayertoview" /></h3>
	<jsp:include page="include-searchresultspaginglinks.jsp" />
	<table class="list-results">
		<tr>
			<th>&nbsp;</th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_ID_COL %>"><bean:message key="search.playerid" /></a></th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_USERID_COL %>"><bean:message key="search.userid" /></a></th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_LASTNAME_COL %>"><bean:message key="search.lastname" /></a></th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_FIRSTNAME_COL %>"><bean:message key="search.firstname" /></a></th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_NUMROUNDS_COL %>"><bean:message key="search.numrounds" /></a></th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_LASTLOGIN_COL %>"><bean:message key="search.lastlogin" /></a></th>
			<th><a href="ViewPlayer-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.PLAYERS_DATECREATED_COL %>"><bean:message key="search.datecreated" /></a></th>
		</tr>
		<logic:iterate indexId="i" id="player" name="<%= AttributeKeyConstants.PLAYERS %>">
			<tr class="${i % 2 == 0 ? "searchresult-even" : "searchresult-odd"}">
				<td><span style="font-size:larger">[</span><a href="ViewPlayer-page1.do?playerid=${player.id}"><bean:message key="search.player.view" /></a><span style="font-size:larger">]</span></td>
				<td>${player.id}</td>
				<td>${player.userId}</td>
				<td>${player.lastName}</td>
				<td>${player.firstName}</td>
				<td>${player.numRounds}</td>
				<td>${player.dateOfLastLogin}</td>
				<td>${player.dateCreated}</td>
			</tr>
		</logic:iterate>
		<logic:empty name="<%= AttributeKeyConstants.PLAYERS %>">
			<tr>
				<td colspan="8">
					<bean:message key="admin.player.search.noresults" />
				</td>
			</tr>
		</logic:empty>
	</table>
	<br />
	<jsp:include page="include-searchresultspaginglinks.jsp" />
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