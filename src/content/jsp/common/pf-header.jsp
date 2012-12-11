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

<!-- A.1 HEADER TOP -->
<div class="header-top">
        
	<!-- Sitelogo and sitename -->
	<a class="sitelogo" href="<%= homePageURL %>" title="<bean:message key="alt.titles.gohome" />"></a>
	<div class="sitename">
		<h1><a href="<%= homePageURL %>" title="<bean:message key="alt.titles.gohome" />"><bean:message key="header.heading" /></a></h1>
		<h2><bean:message key="header.subheading" /></h2>
	</div>	            
</div>