<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<h3>[<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditHomeCourse.do"><bean:message key="action.edit" /></a>]&nbsp;&nbsp;<bean:message key="profile.header.homecourseinfo" /></h3>

<table class="form-table"> 
	<tr>
		<th style="width:${thwidth}"><bean:message key="profile.homecourse" /></th>
		<td>
			<logic:equal value="<%= Integer.toString(Constants.NO_HOME_COURSE_SET_ID) %>" name="ProfileForm" property="courseId">
				<bean:message key="profile.homecoursenotset" />
			</logic:equal>
			<logic:notEqual value="<%= Integer.toString(Constants.NO_HOME_COURSE_SET_ID) %>" name="ProfileForm" property="courseId">
				<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/ViewCourse-page1.do?courseid=<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="course.id" />">
					<bean:write name="ProfileForm" property="homeCourseDescription" />
				</a>
			</logic:notEqual>
		</td>
	</tr>
</table>
