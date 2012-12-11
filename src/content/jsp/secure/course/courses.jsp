<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<h2><bean:message key="courses.about.heading" /></h2>
<br />
<div class="section">
	<p><bean:message key="courses.about.text" /></p>
	<p>
		<ul>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/AddCourse-page1.do"><bean:message key="menu.loggedin.menuitem.addcourse" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditCourse-search.do"><bean:message key="menu.loggedin.menuitem.editcourse" /></a></li>
			<li><a href="<%= WebConstants.CONTEXT_ROOT %>/secure/ViewCourse-search.do"><bean:message key="menu.loggedin.menuitem.searchcourses" /></a></li>
		</ul>
	</p>
</div>
