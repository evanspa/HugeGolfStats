<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<%
	String topHeaderLinksInclude;

topHeaderLinksInclude = "top-header-links-notloggedin.jsp";
	if (NewUtil.isLoggedIn(request)) {
		topHeaderLinksInclude = "top-header-links-loggedin.jsp";
	}
%>

<jsp:include page="<%= topHeaderLinksInclude %>" />