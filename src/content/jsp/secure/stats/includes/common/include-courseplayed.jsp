<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>


<h3><bean:message key="yourstats.courseplayed.header" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}">
			<bean:message key="yourstats.courseplayed.inputcourseplayed" />
		</th>
		<td>
			<html:select property="coursePlayedId" styleClass="combo">
				<html:option key="option.all" value="<%= "" + Constants.ALL_OPTION_VAL %>" />
				<html:options
					collection="<%= AttributeKeyConstants.COURSES_PLAYED %>"
					property="id"						
					labelProperty="description" />
			</html:select>
		</td>
	</tr>
</table>