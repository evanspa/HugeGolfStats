<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>

<%
	String rightNavInclude;

	rightNavInclude = "right-navigation-notloggedin.jsp";
	if (NewUtil.isLoggedIn(request)) {
		rightNavInclude = "right-navigation-loggedin.jsp";
	}
%>

<jsp:include page="<%= rightNavInclude %>" />