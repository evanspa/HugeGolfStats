<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<h2><bean:message key="rounds.about.heading" /></h2>
<br />
<div class="section">
		<p><bean:message key="rounds.about.text" /></p>
		<p>
			<ul>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/PostRound-page1.do"><bean:message key="menu.loggedin.menuitem.addround" /></a></li>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditRound-search.do"><bean:message key="menu.loggedin.menuitem.editround" /></a></li>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/ViewRound-search.do"><bean:message key="menu.loggedin.menuitem.searchrounds" /></a></li>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/RecentRounds2.do"><bean:message key="menu.loggedin.menuitem.recentrounds" /></a></li>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/HandicapSummary2.do"><bean:message key="menu.loggedin.menuitem.handicapsummary" /></a></li>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/scorecard-pdf/hugegolfstats-20061128-scorecard.pdf" target="_blank"><bean:message key="menu.notloggedin.menuitem.scorecard" /></a></li>
			</ul>
		</p>
</div>