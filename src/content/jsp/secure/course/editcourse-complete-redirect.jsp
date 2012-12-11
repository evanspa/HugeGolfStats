<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.bean.CourseWrapperBean" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<%
	String noMenuParam;
	String courseIdParam;
	CourseWrapperBean courseWrapper;

	noMenuParam = AttributeKeyConstants.NO_MENU + "=";
	if (BooleanUtils.toBoolean((String)request.getAttribute(AttributeKeyConstants.NO_MENU))) {
		noMenuParam += Constants.TRUE;
	} else {
		noMenuParam += Constants.FALSE;
	}
	courseWrapper = (CourseWrapperBean)session.getAttribute(AttributeKeyConstants.COURSE);
	courseIdParam = AttributeKeyConstants.COURSE_ID_PARAM + "=" + courseWrapper.getCourse().getId().toString();
	response.sendRedirect(WebUtils.httpPrefix(request) + 
			"/secure/EditCourse-complete-display.do?" + noMenuParam + "&" + courseIdParam);
%>