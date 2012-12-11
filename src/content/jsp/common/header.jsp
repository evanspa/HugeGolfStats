<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

<%
	String homePageURL;

	homePageURL = WebUtils.httpPrefix(request) + "/Welcome.do";
	if (NewUtil.isLoggedIn(request)) {
		homePageURL = WebUtils.httpPrefix(request) + "/secure/Welcome.do";
	}
	request.setAttribute("homepageurl", homePageURL);
%>





<!-- A.3 HEADER BOTTOM -->
<div class="header-bottom">
	<jsp:include page="menubar.jsp"/>
</div>