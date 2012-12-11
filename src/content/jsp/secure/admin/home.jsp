<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>

<h2><bean:message key="admin.about.heading" /></h2>
<br />
<div class="section">
	<p><bean:message key="admin.about.text" /></p>
	<p>
		<ul>
			<li><a href="<%= WebUtils.httpPrefix(request) %>/secure/admin/UsageSummary.do"><bean:message key="menu.loggedin.menuitem.usagesummary" /></a></li>
			<li><a href="<%= WebUtils.httpPrefix(request) %>/secure/admin/ViewPlayer-search.do"><bean:message key="menu.loggedin.menuitem.searchplayers" /></a></li>
			<li><a href="<%= WebUtils.httpPrefix(request) %>/secure/admin/SendBroadcastEmail.do"><bean:message key="menu.loggedin.menuitem.sendbroadcastemail" /></a></li>
			<li><a href="<%= WebUtils.httpPrefix(request) %>/secure/admin/ViewSystemInfo.do"><bean:message key="menu.loggedin.menuitem.systeminfo" /></a></li>
		</ul>
	</p>
</div>
