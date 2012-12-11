<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<%
	String noMenuParam;

	noMenuParam = AttributeKeyConstants.NO_MENU + "=";
	if (BooleanUtils.toBoolean((String)request.getAttribute(AttributeKeyConstants.NO_MENU))) {
		noMenuParam += Constants.TRUE;
	} else {
		noMenuParam += Constants.FALSE;
	}
	response.sendRedirect(WebUtils.httpPrefix(request) + "/secure/EditProfile-submit-display.do?" + noMenuParam);
%>