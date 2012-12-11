<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<!-- The "no-menu" flag is used to indicate whether or not the "menu" section
of the application should be displayed or not.  This include is used to make
sure the no-menu flag is re-submitted as a hidden. -->
<input type="hidden" 
		name="<%= AttributeKeyConstants.NO_MENU %>" 
		value="<%= BooleanUtils.toBoolean((String)request.getAttribute(
				AttributeKeyConstants.NO_MENU)) %>" />