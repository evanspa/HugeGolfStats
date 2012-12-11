<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<table class="form-table">
	
	<!-- input player first name -->
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.player.view.search.firstname" />
		</th>
		<td>
			<html:text size="40" property="firstName" styleClass="field" />
		</td>
	</tr>
	
	<!-- input player last name -->
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.player.view.search.lastname" />
		</th>
		<td>
			<html:text size="40" property="lastName" styleClass="field" />
		</td>
	</tr>
	
	<!-- input player zip/postal code -->
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="admin.player.view.search.zipcode" />
		</th>
		<td>
			<html:text size="40" property="postalCode" styleClass="field" />
		</td>
	</tr>	
</table>