<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>

<!-- Navigation Level 1 -->
<div class="nav1">
	<ul>
		<li><a href="<%= WebUtils.httpPrefix(request) %>/Welcome.do" title="<bean:message key="alt.titles.toplinks.home" />"><bean:message key="toplinks.home" /></a></li>
    	<li><a href="<%= WebUtils.httpPrefix(request) %>/Welcome.do" title="<bean:message key="alt.titles.toplinks.about" />"><bean:message key="toplinks.about" /></a></li>
      	<li><a href="<%= WebUtils.securePrefix(request) %>/ContactUs.do" title="<bean:message key="alt.titles.toplinks.contactus" />"><bean:message key="toplinks.contactus" /></a></li>		
		<li><a href="<%= WebUtils.securePrefix(request) %>/CreateAccount.do" title="<bean:message key="alt.titles.toplinks.notloggedin.signup" />"><bean:message key="toplinks.notloggedin.signup" /></a></li>
		<li><a href="<%= WebUtils.securePrefix(request) %>/secure/MemberHome.do" title="<bean:message key="alt.titles.toplinks.notloggedin.login" />"><bean:message key="toplinks.notloggedin.login" /></a></li>
   	</ul>
</div> 