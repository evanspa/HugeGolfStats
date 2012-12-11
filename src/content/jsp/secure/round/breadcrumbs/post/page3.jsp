<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
      
<ul>
	<li><a href="<%= request.getAttribute("homepageurl") %>"><bean:message key="breadcrumbs.home" /></a></li>
	<li><a href="#" onclick="start(); return false;"><bean:message key="breadcrumbs.round.post.page1" /></a></li>
	<li><a href="#" onclick="previous(); return false;"><bean:message key="breadcrumbs.round.post.page2" /></a></li>
	<li><bean:message key="breadcrumbs.round.page3" /></li>
</ul>