<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
      
<ul>
	<li><a href="<%= request.getAttribute("homepageurl") %>"><bean:message key="breadcrumbs.home" /></a></li>
	<li><bean:message key="breadcrumbs.round.post.page1" /></li>
	<li><bean:message key="breadcrumbs.round.post.nostats.page2" /></li>
	<li><bean:message key="breadcrumbs.round.post.end" /></li>
</ul>