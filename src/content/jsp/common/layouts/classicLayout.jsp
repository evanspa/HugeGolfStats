<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	String homePageURL;

	homePageURL = WebConstants.CONTEXT_ROOT + "/Welcome.do";
	if (NewUtil.isLoggedIn(request)) {
		homePageURL = WebConstants.CONTEXT_ROOT + "/secure/Welcome.do";
	}
	request.setAttribute("homepageurl", homePageURL);
%>

<!-- needed to put attributes added by the tiles-defs.xml into scope -->
<tiles:importAttribute scope="request"/>

<html:html xhtml="true" lang="en">

	<!-- include head -->
	<tiles:get name="head"/>

	<body>

	<!-- output the header-page title -->
	<table style="width:100%">
		<tr>
			<td style="text-align:left"><h1><a href="<%= homePageURL %>" title="<bean:message key="alt.titles.gohome" />"><bean:message key="header.heading" /></a></h1></td>
			<td style="text-align:right">
				<% if (NewUtil.isLoggedIn(request)) { %>
					<div style="padding:3px"><bean:message key="greeting.message" />, <span style="font-weight:bold"><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="firstName" /></span>!&nbsp;&nbsp;<bean:message key="header.menu.player.numroundsposted" />&nbsp;<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/RecentRounds.do"><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="totalNumberOfRoundsPosted" /></a></div>
					<div style="padding:3px">
					<bean:message key="header.menu.player.handicapindex" />
						<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex">
							<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/HandicapSummary.do"><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="reducedHandicapIndex" /></a>
						</logic:present>
						<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex">
							<a href="###" onclick="alert('<bean:message key="header.menu.player.handicapindex.whencalculatedmsg" />'); return false;">
								<bean:message key="header.menu.player.handicapindex.notyetcalculated" />
							</a>
						</logic:notPresent>&nbsp;<a href="<%= WebUtils.securePrefix(request) %>/secure/ContactUs.do" title="<bean:message key="alt.titles.toplinks.contactus" />"><bean:message key="toplinks.contactus" /></a>&nbsp;&nbsp;<a href="<%= WebConstants.CONTEXT_ROOT %>/Logout.do" title="<bean:message key="alt.titles.toplinks.loggedin.logout" />"><bean:message key="toplinks.loggedin.logout" /></a></div>
				<% } else { %>
					<a href="<%= WebUtils.securePrefix(request) %>/CreateAccount.do" title="<bean:message key="alt.titles.toplinks.notloggedin.signup" />"><bean:message key="toplinks.notloggedin.signup" /></a>&nbsp;
					<a href="<%= WebUtils.securePrefix(request) %>/secure/MemberHome.do" title="<bean:message key="alt.titles.toplinks.notloggedin.login" />"><bean:message key="toplinks.notloggedin.login" /></a>&nbsp;
					<a href="<%= WebUtils.securePrefix(request) %>/ContactUs.do" title="<bean:message key="alt.titles.toplinks.contactus" />"><bean:message key="toplinks.contactus" /></a>
				<% } %>
			</td>
		</tr>
	</table>

	<div id="header">

		<!-- include menubar -->
		<tiles:get name="menubar"/>
	</div>

	<div id="main">
		<div id="contents">

			<!-- Only output errors-tile if errors are presented -->
			<logic:messagesPresent>
				<tiles:get name="errors"/>
				<br />
			</logic:messagesPresent>

			<!-- ouput the main body content -->
			<tiles:get name="body-content"/>
		</div>
	</div>

	<!-- include footer.jsp -->
	<tiles:get name="footer"/>

	</body>
</html:html>