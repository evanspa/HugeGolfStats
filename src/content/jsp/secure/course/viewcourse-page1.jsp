<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.bean.CourseWrapperBean" %>

<%
	CourseWrapperBean course;
	course = (CourseWrapperBean)request.getAttribute(AttributeKeyConstants.COURSE);
%>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "175px"); %>

<h2><bean:message key="course.view.header" /></h2>
<br />

<!-- show the course information -->
<jsp:include page="include-showcourseinfo.jsp" />

<!-- show the course slope/rating values -->
<jsp:include page="include-showsloperatingvalues.jsp" />
	
<logic:greaterThan name="CourseForm" property="numHoles" value="0">
	
	<!-- show the holes (assuming they have been created) -->
	<jsp:include page="include-showholes.jsp" />
	<br />
</logic:greaterThan>
<logic:equal name="CourseForm" property="numHoles" value="0">
	<br />
	<p>
		<bean:message key="round.courseneedsupdating_holes" 
			arg0="<%= WebUtils.httpPrefix(request) %>" 
			arg1="<%= AttributeKeyConstants.NO_MENU %>"
			arg2="<%= "" + BooleanUtils.toBoolean((String)request.getAttribute(AttributeKeyConstants.NO_MENU)) %>"
			arg3="<%= AttributeKeyConstants.COURSE_ID_PARAM %>" 
			arg4="<%= "" + course.getCourse().getId() %>" />
				&nbsp;
		<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="round.courseneedsupdating_holes_explanation" />'); return false;"><bean:message key="round.courseneedsupdating_holes_explanation_link" /></a><span style="font-size: larger">]</span>
	</p>
</logic:equal>

<% if (!BooleanUtils.toBoolean((String)request.getAttribute(AttributeKeyConstants.NO_MENU))) { %>
		<p>
			<span style="font-size:larger">[</span><html:link href="EditCourse-page1.do?courseid=${CourseObject.course.id}"><bean:message key="course.edit" /></html:link><span style="font-size:larger">]</span>
			<br />
			<br />
			<span style="font-size:larger">[</span><html:link href="PostRound-page1.do?courseid=${CourseObject.course.id}"><bean:message key="course.saved.addround" /></html:link><span style="font-size:larger">]</span>
		</p>
<% } %>
</div>

<%
	// cleanup...
	NewUtil.removeFromSession(AttributeKeyConstants.COURSE, request);
	NewUtil.removeFromSession(AttributeKeyConstants.COURSES, request);
	NewUtil.removeFromSession("CourseForm", request);
%>