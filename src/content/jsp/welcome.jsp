<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="name.paulevans.golf.util.NewUtil" %>

<h2><bean:message key="welcome.about.heading"/></h2>

<p><bean:message key="welcome.about.text"/></p>

<h2><bean:message key="welcome.scoretracker.heading"/></h2>

<p>
	<bean:message
			arg0="scorecard-pdf/hugegolfstats-20061128-scorecard.pdf"
			key="welcome.scoretracker.text"/>
</p>

<h2><bean:message key="welcome.statistics.heading"/></h2>

<p><bean:message key="welcome.statistics.text"/></p>

<%
	// check to see if the user is logged-in...
	if (!NewUtil.isLoggedIn(request)) { %>

		<h2><bean:message key="welcome.gettingstarted.heading"/></h2>
        <p>
			<bean:message
				arg0="CreateAccount.do"
				arg1="TakeTour.do"
				arg2="FAQ.do"
				key="welcome.gettingstarted.text"/>
		</p>
<%	} %>
