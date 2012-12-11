<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.BeanFactory" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<!-- 
	We're using a scriptlet here instead of taglibs to output some dynamic
	content.  The purpose is that the util object might not be in the session
	in the case of user's opening a fresh browser and having a secure-page
	bookmarked.  In this case, the request for the secure page is captured
	by the container and the login page is displayed; in this event, the 
	associated action has not executed and therefore the util object (which is
	*always* assumed to be in the session) will not yet be in the session.
 -->

<%
	NewUtil util;
	boolean isLoggedIn;
	
	util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
	if (util == null) {
		util = BeanFactory.getUtilObject();
		util.initialize();
	}
	isLoggedIn = NewUtil.isLoggedIn(request);
%>

<!-- C. FOOTER AREA -->      
<div class="footer">
	<p>
	    <!--<a href="#"><bean:message key="footer.advertisewithus" /></a>&nbsp;|&nbsp;-->
		<a href="<%= WebConstants.CONTEXT_ROOT + (isLoggedIn ? "/secure" : "") %>/Welcome.do"><bean:message key="footer.aboutus" /></a>&nbsp;|&nbsp;
		<a href="<%= WebConstants.CONTEXT_ROOT + (isLoggedIn ? "/secure" : "") %>/TermsOfUse.do"><bean:message key="footer.termsofuse" /></a>&nbsp;|&nbsp;
		<a href="<%= WebConstants.CONTEXT_ROOT + (isLoggedIn ? "/secure" : "") %>/PrivacyNotice.do"><bean:message key="footer.privacypolicy" /></a>
	</p>
	<p><bean:message key="footer.copyrightnotice" />&nbsp;|&nbsp;<bean:message key="footer.releaseversion" />&nbsp;<%= Constants.RELEASE_VERSION %></p>
</div>