<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "190px"); %>

<h1 class="pagetitle"><bean:message key="msgbroadcast-sent.header" /></h1>

<div class="column1-unit">
	<div class="generalform">
		<p>
			The following broadcast message was sent successfully to <span style="font-size:larger">[</span>${NumEmailsSent}<span style="font-size:larger">]</span> users:
			<br><br>
			<em>
				SUBJECT: <bean:write name="MsgBroadcastForm" property="subject" />
				<br><br>
				<bean:write name="MsgBroadcastForm" property="message" />
			</em>
		</p>
	</div>
</div>