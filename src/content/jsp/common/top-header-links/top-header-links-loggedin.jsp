<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>

<!-- Navigation Level 1 -->
<div class="nav1">
	<ul>
		<li><a href="<%= WebUtils.httpPrefix(request) %>/secure/Welcome.do" title="<bean:message key="alt.titles.toplinks.home" />"><bean:message key="toplinks.home" /></a></li>
    	<li><a href="<%= WebUtils.httpPrefix(request) %>/secure/Welcome.do" title="<bean:message key="alt.titles.toplinks.about" />"><bean:message key="toplinks.about" /></a></li>
      	<li><a href="<%= WebUtils.securePrefix(request) %>/ContactUs.do" title="<bean:message key="alt.titles.toplinks.contactus" />"><bean:message key="toplinks.contactus" /></a></li>		
		<li><a href="<%= WebUtils.httpPrefix(request) %>/Logout.do" title="<bean:message key="alt.titles.toplinks.loggedin.logout" />"><bean:message key="toplinks.loggedin.logout" /></a></li>
   	</ul>
</div> 