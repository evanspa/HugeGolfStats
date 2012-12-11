<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<script language="JavaScript">

	function newSearch() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewCourse-search.do";
		formObj.submit();
	}

	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelCourse.do";
		formObj.submit();
	}
	
	function nextResultPage() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewCourse-search-results.do";
		formObj.firstSearchResultNum.value="${CourseForm.firstSearchResultNum + CourseForm.maxSearchResultsNum}";
		formObj.submit();
	}
	
	function previousResultPage() {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewCourse-search-results.do";
		formObj.firstSearchResultNum.value="${CourseForm.firstSearchResultNum - CourseForm.maxSearchResultsNum}";
		formObj.submit();
	}
	
	function jumpToPage(aFirstSearchResultNum) {
		var formObj = document.getElementById("mainForm");
		formObj.action="ViewCourse-search-results.do";
		formObj.firstSearchResultNum.value=aFirstSearchResultNum;
		formObj.submit();
	}
</script>

<h2><bean:message key="course.view.searchresults.header" /></h2>

<html:form styleId="mainForm" focus="courseName" action="/secure/ViewCourse-page1">
	
	<html:hidden property="firstSearchResultNum" />
	<html:hidden property="pageAction" value="<%= Constants.TRUE %>" />

	<h3><bean:message key="course.view.searchresults.pickcoursetoview" /></h3>
	<jsp:include page="include-searchresultspaginglinks.jsp" />
	<table class="list-results">
		<tr>
			<th>&nbsp;</th>
			<th style="text-align: center"><a href="ViewCourse-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.COURSES_COURSE_COL %>"><bean:message key="search.coursename" /></a></th>
			<th style="text-align: center"><a href="ViewCourse-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.COURSES_CITY_COL %>"><bean:message key="search.city" /></a></th>
			<th style="text-align: center"><a href="ViewCourse-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.COURSES_STATEPROV_COL %>"><bean:message key="search.stateprovince" /></a></th>
			<th style="text-align: center"><a href="ViewCourse-search-results-sort.do?<%= WebConstants.SORT_COLUMN_PARAM %>=<%= WebConstants.COURSES_COUNTRY_COL %>"><bean:message key="search.country" /></a></th>
		</tr>
		<logic:iterate indexId="i" id="course" name="<%= AttributeKeyConstants.COURSES %>">
			<tr>
				<td style="text-align: center"><span style="font-size:larger">[</span><a href="ViewCourse-page1.do?courseid=${course.id}"><bean:message key="search.course.view" /></a><span style="font-size:larger">]</span></td>
				<td style="text-align: left">${course.description}</td>
				<td style="text-align: left">${course.city}</td>
				<td style="text-align: center">${course.stateProvince.name}</td>
				<td style="text-align: left">${course.stateProvince.country.name}</td>
			</tr>
		</logic:iterate>
		<logic:empty name="<%= AttributeKeyConstants.COURSES %>">
			<tr>
				<td colspan="5">
					<bean:message key="course.search.noresults" />
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
