<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "175px"); %>

<h2><bean:message key="course.added.header" /></h2>

<h3 style="color:purple; font-family:arial">***&nbsp;<bean:message key="course.saved.registerscores" />&nbsp;***</h3>

<!-- show the course information -->
<jsp:include page="include-showcourseinfo.jsp" />

<!-- show the course slope/rating values -->
<jsp:include page="include-showsloperatingvalues.jsp" />
		
<!--  show the holes for this course -->
  	<jsp:include page="include-showholes.jsp" />
<p>
<% if (!BooleanUtils.toBoolean((String)request.getAttribute(
					AttributeKeyConstants.NO_MENU))) { %>
	<br />
	<span style="font-size: larger">[</span><html:link href="EditCourse-page1.do?courseid=${CourseObject.course.id}"><bean:message key="course.edit" /></html:link><span style="font-size: larger">]</span>
	<br />
	<br />
	<span style="font-size: larger">[</span><html:link href="PostRound-page1.do?courseid=${CourseObject.course.id}"><bean:message key="course.saved.addround" /></html:link><span style="font-size: larger">]</span>
<% } else { %>
	<span style="font-size: larger">[</span><a href="EditCourse-page1.do?courseid=${CourseObject.course.id}&<%= AttributeKeyConstants.NO_MENU %>=true"><bean:message key="course.editagain" /><span style="font-size: larger">]</span>
	</a>
	<br />
	<br />
	<html:button property="dummy" onclick="closeWindow()" styleClass="button">
		<bean:message key="button.closewindow" />
	</html:button>
<% } %>
</p>
