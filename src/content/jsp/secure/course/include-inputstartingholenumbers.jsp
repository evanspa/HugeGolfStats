<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>	
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>	

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.struts.formbean.CourseForm" %>

<%
	CourseForm courseForm;
	String disableStartingHoles;

	courseForm = (CourseForm)session.getAttribute("CourseForm");
	disableStartingHoles = "false";
	if (courseForm.getIsEditMode() && courseForm.getHolesExist()) {
		disableStartingHoles = "true";
	}
	pageContext.setAttribute("disableStartingHoles", disableStartingHoles);
%>
<h3>Input Starting Hole Numbers:</h3>
<table class="form-table">
<tr>
	<th style="width:${thwidth}"><bean:message key="course.selectfront9startinghole" /></th>
		<td>
		<html:select property="front9StartingHole" disabled="${disableStartingHoles}" styleClass="startingholecombo">
			<html:options
				name="<%= AttributeKeyConstants.UTIL %>" 
				property="front9StartingHoles" />
		</html:select>
		<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="course.startingholes.explanation" />'); return false;"><bean:message key="course.startingholes.explanation.link" /></a><span style="font-size: larger">]</span>
		<logic:equal name="disableStartingHoles" value="true">
			<html:hidden property="front9StartingHole" />
			<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="course.startingholes.cantchange.explanation" />'); return false;"><bean:message key="course.startingholes.cantchange.explanation.link" /></a><span style="font-size: larger">]</span>
		</logic:equal>
		<logic:equal name="disableStartingHoles" value="false">
			<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="course.startingholes.whychange" />'); return false;"><bean:message key="course.startingholes.whychange.link" /></a><span style="font-size: larger">]</span>
		</logic:equal>
	</td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message key="course.selectback9startinghole" /></th>
		<td>
		<html:select property="back9StartingHole" disabled="${disableStartingHoles}" styleClass="startingholecombo">
			<html:options
				name="<%= AttributeKeyConstants.UTIL %>" 
				property="back9StartingHoles" />
		</html:select>
		<logic:equal name="disableStartingHoles" value="true">
			<html:hidden property="back9StartingHole" />
		</logic:equal>
	</td>
</tr>
</table>