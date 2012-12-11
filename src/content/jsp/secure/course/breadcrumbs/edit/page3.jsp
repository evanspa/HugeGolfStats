<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
      
<ul>
	<li><a href="<%= request.getAttribute("homepageurl") %>"><bean:message key="breadcrumbs.home" /></a></li>
	<li><a href="#" onclick="start(); return false;"><bean:message key="breadcrumbs.course.edit.page1" /></a></li>
	<li><a href="#" onclick="previous(); return false;"><bean:message key="breadcrumbs.course.edit.page2" /></a></li>
	<li><bean:message key="breadcrumbs.course.page3" /></li>
</ul>