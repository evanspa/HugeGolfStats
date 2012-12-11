<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<script language="Javascript">

	function closeWindow() {
	    //alert(window.opener.location.href);
	    if (window.opener.location.href.indexOf('PostRound') != -1) {
		    window.opener.location.href='Refresh-PostRound-page1.do?stateprovincechanged=false';
		} else if (window.opener.location.href.indexOf('EditRound') != -1) {
			window.opener.location.href='Refresh-EditRound-page1.do?stateprovincechanged=false';
		}
		window.close();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "205px"); %>

<h2><bean:message key="course.saved.header" /></h2>
<br />

<!-- show the course information -->
<jsp:include page="include-showcourseinfo.jsp" />

<!-- show the course slope/rating values -->
<jsp:include page="include-showsloperatingvalues.jsp" />
		
<!-- show the holes for this course -->
<jsp:include page="include-showholes.jsp" />

<p>
<% if (!BooleanUtils.toBoolean((String)request.getAttribute(
					AttributeKeyConstants.NO_MENU))) { %>
	<br />
	<span style="font-size: larger">[</span><html:link href="EditCourse-page1.do?courseid=${CourseObject.course.id}"><bean:message key="course.editagain" /></html:link><span style="font-size: larger">]</span>
	<br />
	<br />
	<span style="font-size: larger">[</span><html:link href="PostRound-page1.do?courseid=${CourseObject.course.id}"><bean:message key="course.saved.addround" /></html:link><span style="font-size: larger">]</span>
<% } else { %>
	<br />
	<span style="font-size: larger">[</span><a href="EditCourse-page1.do?courseid=${CourseObject.course.id}&<%= AttributeKeyConstants.NO_MENU %>=true"><bean:message key="course.editagain" /></a><span style="font-size: larger">]</span>
	<br />
	<br />
	<html:button property="dummy" onclick="closeWindow()" styleClass="button">
		<bean:message key="button.closewindow" />
	</html:button>
<% } %>
</p>


<%
	// cleanup...
	NewUtil.removeFromSession(AttributeKeyConstants.COURSES, request);
%>