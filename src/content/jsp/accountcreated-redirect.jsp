<%@ page import="name.paulevans.golf.web.WebUtils" %>

<%
	response.sendRedirect(WebUtils.httpPrefix(request) + "/CreateAccount-submit-display.do");
%>