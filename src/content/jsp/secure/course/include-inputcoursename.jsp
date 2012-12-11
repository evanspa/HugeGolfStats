<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>	
	
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="course.coursename" /><span class="required">*</span></th>
		<td><html:text size="55" property="courseName" styleClass="field" /></td>
	</tr>
</table>