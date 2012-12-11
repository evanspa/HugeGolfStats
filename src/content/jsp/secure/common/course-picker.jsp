<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

[<a href="###" 
	target="_blank" 
	onclick="window.open('<%= WebUtils.httpPrefix(request) %>/secure/AddCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true', 'AddCourse', 'height=700,width=950,scrollbars=yes,resizable=yes'); return false;">
	<bean:message key="action.addcourse" /></a>]
[<a href="###" 
	target="_blank" 
	onclick="openWindow('<%= WebUtils.httpPrefix(request) %>/secure/EditCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true&courseid=', 'EditCourse', 'height=700,width=950,scrollbars=yes,resizable=yes'); return false;">
	<bean:message key="action.editcourse" /></a>]
			