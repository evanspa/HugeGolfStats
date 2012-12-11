<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib  uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<logic:messagesPresent>

	<div class="column1-unit">
		<fieldset>
			<legend class="error">&nbsp;<bean:message key="errors.header" />&nbsp;</legend>
		   	<ul>
				<html:messages id="error">
    				<li><bean:write filter="false" name="error" /></li>
   				</html:messages>
   			</ul>
		</fieldset>
	</div>
</logic:messagesPresent>