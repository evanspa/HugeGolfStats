<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<jsp:include page="include-cancelfunction.jsp" />

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "280px"); %>

<h2><bean:message key="profile.header.editplayinghabits" /></h2>
<br />
<html:form styleId="mainForm" focus="firstName" action="/secure/EditPlayingHabits-submit" onsubmit="return validateCreateAccountForm(this);">
	<jsp:include page="include-outputhiddens.jsp" />
	<table class="form-table">
		<jsp:include page="include-inputplayinghabits.jsp" />	
	</table>
	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button">
			<bean:message key="button.save" />
		</html:submit>
	</p>
</html:form>