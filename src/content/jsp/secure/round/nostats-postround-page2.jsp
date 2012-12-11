<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebConstants" %>

<script language="JavaScript">

	function previous() {
		document.forms[1].action="Previous-PostRound-page1.do";
		document.forms[1].submit();
	}
	
	function cancel() {
		document.forms[1].action="CancelRound.do";
		document.forms[1].submit();
	}
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="round.post.page2.header" /></h2>

<html:form action="/secure/nostats-PostRound-page2-submit">
	
	<input type="hidden" name="<%= WebConstants.MODE %>" value="<%= WebConstants.INPUT_OVERALL_SCORE %>" />
	
	<!-- output hiddens for tees -->
	<logic:iterate id="hole" name="RoundForm" property="allHolesPlayed">
		<html:hidden property="holesPlayed(${hole.key})" value="${hole.value}" />
	</logic:iterate>
	
	<!-- output hiddens for stats to collect -->
	<jsp:include page="include-outputhiddens.jsp" />

	<!-- output course information -->
	<jsp:include page="include-showcourseinformation.jsp" />
			
	<!-- input the overall score -->
	<jsp:include page="include-inputoverallscore.jsp" />
	
	<!-- input round circumstances -->
	<jsp:include page="include-inputcircumstances.jsp" />

	<br />
	<p>
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button">
			<bean:message key="button.saveround" />
		</html:submit>
		<html:button property="dummy" onclick="previous()" styleClass="button">
			<bean:message key="button.goback" />
		</html:button>
	</p>						
</html:form>

<script language="Javascript">
    document.forms[1].overallScore.focus();
</script>
