<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>

<% pageContext.setAttribute("isloggedin", NewUtil.isLoggedIn(request)); %>

<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.homecoursecountry" /></th>
	<td><html:select property="countryId" onchange="countrySelected()" styleClass="combo">
		<html:options
			collection="<%= AttributeKeyConstants.COUNTRIES %>"
			property="id"						
			labelProperty="name" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.homecoursestateprovince" /></th>
	<td><html:select property="stateProvinceId" onchange="stateProvinceSelected()" styleClass="combo">
		<html:options
			collection="<%= AttributeKeyConstants.STATE_PROVINCES %>"
			property="id.id"						
			labelProperty="name" />
	</html:select></td>
</tr>
<tr>
	<th style="width:${thwidth}; text-align:right"><bean:message key="profile.homecourse" /></th>
	<td>
		<html:select property="courseId" styleClass="combo">
			<html:options
				collection="<%= AttributeKeyConstants.COURSES %>"
				property="id"						
				labelProperty="description" />
			<html:option value="<%= Integer.toString(Constants.NO_HOME_COURSE_SET_ID) %>" 
				key="option.nohomecourseset" />
		</html:select>
		<logic:equal value="true" name="ProfileForm" property="createNewAccount">
			<span style="font-size: larger">[</span><a href="#" onclick="alert('<bean:message key="profile.homecoursemsgcontents" />'); return false;">
				<bean:message key="profile.homecoursemsg" /></a><span style="font-size: larger">]</span>
		</logic:equal>
		<logic:equal value="true" name="ProfileForm" property="editProfile">
			<span style="font-size: larger">[</span><a href="###" 
				target="_blank" 
				onclick="addCourse('<%= WebUtils.httpPrefix(request) %>/secure/AddCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true', 'AddCourse', 'height=700,width=950,scrollbars=yes,resizable=yes'); return false;"><bean:message key="action.addcourse" /></a><span style="font-size: larger">]</span>
			<span style="font-size: larger">[</span><a href="###" 
				target="_blank" 
				onclick="editCourse('<%= WebUtils.httpPrefix(request) %>/secure/EditCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true&courseid=', 
								'EditCourse', 
								'height=700,width=950,scrollbars=yes,resizable=yes',
								<%= Constants.NO_HOME_COURSE_SET_ID %>); return false;"><bean:message key="action.editcourse" /></a><span style="font-size: larger">]</span>
			<span style="font-size: larger">[</span><a href="#" onclick="stateProvinceSelected(); return false;">
				<bean:message key="action.refresh" /></a><span style="font-size: larger">]</span>
		</logic:equal>
	</td>
</tr>