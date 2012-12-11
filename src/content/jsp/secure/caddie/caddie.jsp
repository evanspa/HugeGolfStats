<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<h1 class="pagetitle"><bean:message key="caddie.about.heading" /></h1>

<div class="column1-unit">
	<div class="contentpane">
		<p><bean:message key="caddie.about.text" /></p>
		<p>
			<ul>
				<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/Caddie.do"><bean:message key="menu.loggedin.menuitem.coursereport" /></a></li>	
			</ul>
		</p>
	</div>
</div>