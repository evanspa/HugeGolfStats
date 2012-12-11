<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>	

<%@ page import="name.paulevans.golf.Constants" %>
	
<table class="form-table">
<tr>
	<th style="width:${thwidth}">
		<bean:message key="course.makehome" />
	</th>
	<td>
		<html:checkbox property="makeHome" value="<%= Constants.TRUE %>" />
	</td>
</tr>
</table>