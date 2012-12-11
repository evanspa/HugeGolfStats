<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<%
	String noMenuParam;
	String scorecardIdParam;

	noMenuParam = AttributeKeyConstants.NO_MENU + "=";
	if (BooleanUtils.toBoolean((String)request.getAttribute(AttributeKeyConstants.NO_MENU))) {
		noMenuParam += Constants.TRUE;
	} else {
		noMenuParam += Constants.FALSE;
	}
	
	scorecardIdParam = AttributeKeyConstants.SCORECARD_ID_PARAM + "=" + 
		session.getAttribute(AttributeKeyConstants.SCORECARD_ID_PARAM).toString();
	response.sendRedirect(WebUtils.httpPrefix(request) + 
			"/secure/nostats-PostRound-complete-display.do?" + noMenuParam + "&" + scorecardIdParam);
%>

