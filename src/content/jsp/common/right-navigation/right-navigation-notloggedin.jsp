<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.dao.PlayerDAO" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

<!-- B.2 MAIN NAVIGATION -->
<div class="main-navigation">

	<!-- Navigation Level 3 -->
    <div class="round-border-topleft"></div>
    <h1 class="first"><bean:write name="<%= AttributeKeyConstants.UTIL %>" property="todaysDate" /></h1>
		
	<!-- Subtitle -->
	<h2><bean:message key="rightnav.notloggedin.greeting" /></h2>
	<p><bean:message key="rightnav.notloggedin.fromheremsg" /></p>

	<!-- Navigation with grid style -->
	<dl class="nav3-bullet">
		<dt><a href="<%= WebUtils.securePrefix(request) %>/secure/MemberHome.do"><bean:message key="rightnav.notloggedin.login" /></a></dt>
		<dt><a href="<%= WebUtils.securePrefix(request) %>/CreateAccount.do"><bean:message key="rightnav.notloggedin.createaccount" /></a></dt>
		<dt><a target="_blank" href="scorecard-pdf/hugegolfstats-20061128-scorecard.pdf"><bean:message key="rightnav.downloadscorecard" /></a></dt>
		<dt><a href="<%= WebUtils.httpPrefix(request) %>/TakeTour.do"><bean:message key="rightnav.taketour" /></a></dt>
		<dt><a href="<%= WebUtils.httpPrefix(request) %>/FAQ.do"><bean:message key="rightnav.viewfaq" /></a></dt>
	</dl>                       
</div>