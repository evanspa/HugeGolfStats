<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<script language="JavaScript">

	function previous() {
		var formObj = document.getElementById("mainForm");
		formObj.action="Previous-PostRound-page1.do";
		formObj.submit();
	}
	
	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="CancelRound.do";
		formObj.submit();
	}

</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "200px"); %>

<h2><bean:message key="round.post.page2.header" /></h2>
<html:form styleId="mainForm" action="/secure/PostRound-page2-submit" onsubmit="return validateCreateAccountForm(this);">
	
	<!-- output hiddens for tees -->
	<logic:iterate id="hole" name="RoundForm" property="allHolesPlayed">
		<html:hidden property="holesPlayed(${hole.key})" value="${hole.value}" />
	</logic:iterate>
	
	<!-- output hiddens for stats to collect -->
	<jsp:include page="include-outputhiddens.jsp" />

	<!-- output course information -->
	<jsp:include page="include-showcourseinformation.jsp" />

	<!-- input hole-scores -->
	<jsp:include page="include-inputscores.jsp" />
	
	<!-- input round circumstances -->
	<jsp:include page="include-inputcircumstances.jsp" />
	
	<br />
	<p>			
		<html:button property="dummy" onclick="cancel()" styleClass="button">
			<bean:message key="button.cancel" />
		</html:button>
		<html:submit styleClass="button">
			<bean:message key="button.next" />
		</html:submit>
		<html:button property="dummy" onclick="previous()" styleClass="button">
			<bean:message key="button.previous" />
		</html:button>
	</p>
</html:form>
