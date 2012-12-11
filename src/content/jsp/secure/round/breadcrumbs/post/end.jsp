<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
      
<ul>
	<li><a href="<%= request.getAttribute("homepageurl") %>"><bean:message key="breadcrumbs.home" /></a></li>
	<li><bean:message key="breadcrumbs.round.post.page1" /></li>
	<li><bean:message key="breadcrumbs.round.post.page2" /></li>
	<li><bean:message key="breadcrumbs.round.page3" /></li>
	<li><span style="color:rgb(70,122,167); font-weight:bold"><bean:message key="breadcrumbs.round.post.end" /></span></li>
</ul>