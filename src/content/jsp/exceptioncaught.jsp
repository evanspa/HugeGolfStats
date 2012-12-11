<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<table width="80%"><tr><td>
<div class="box">
<h3></h3>
<div>
	<p>
		<span style="font-family: arial; font-size: large; font-type: bold">
			<bean:message key="exception.header" />
		</span>
	</p>
	<p>
		<bean:message key="exception.explanation" />
	</p>
	<p>
		<span style="font-family: arial; font-size: large; font-type: bold">
			<bean:message key="exception.message.header" />
		</span>
		<br />
		<br />
		<%= request.getAttribute(AttributeKeyConstants.EXCEPTION_MESSAGE_PARAM) %>
	</p>
</div>
</td></tr></table>
