<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<!-- output hiddens for sand-save attempts -->
<logic:iterate name="RoundForm" property="sandSaveAttemptsMap" id="element">
	<html:hidden property="sandSaveAttempt(${element.key})" value="${element.value}" />
</logic:iterate>

<!-- output hiddens for sand-save conversions -->
<logic:iterate name="RoundForm" property="sandSaveConvertsMap" id="element">
	<html:hidden property="sandSaveConvert(${element.key})" value="${element.value}" />
</logic:iterate>

<!-- output hiddens for up-and-down attempts -->
<logic:iterate name="RoundForm" property="upDownAttemptsMap" id="element">
	<html:hidden property="upDownAttempt(${element.key})" value="${element.value}" />
</logic:iterate>

<!-- output hiddens for up-and-down conversions -->
<logic:iterate name="RoundForm" property="upDownConvertsMap" id="element">
	<html:hidden property="upDownConvert(${element.key})" value="${element.value}" />
</logic:iterate>