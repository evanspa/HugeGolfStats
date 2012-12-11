<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<script language="JavaScript" src="<%= WebConstants.CONTEXT_ROOT %>/js/add-edit-course-links.js"></script>

<h3><bean:message key="round.courseplayed" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.country" /></th>
		<td>
			<html:select property="countryId" onchange="countrySelected()" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.COUNTRIES %>"
					property="id"						
					labelProperty="name" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.state_province" /></th>
		<td>
			<html:select property="stateProvinceId" onchange="stateProvinceSelected()" styleClass="combo">
				<html:options
					collection="<%= AttributeKeyConstants.STATE_PROVINCES %>"
					property="id.id"						
					labelProperty="name" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.course" /></th>
		<td>
			<logic:notEmpty name="<%= AttributeKeyConstants.COURSES %>">
				<html:select property="courseId" onchange="courseSelected()" styleClass="combo">
					<html:options
						collection="<%= AttributeKeyConstants.COURSES %>"
						property="id"						
						labelProperty="description" />
				</html:select>
				<span style="font-size: larger">[</span><a href="###" 
					onclick="addCourse('<%= WebConstants.CONTEXT_ROOT %>/secure/AddCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true', 'AddCourse', 'height=700,width=1275,scrollbars=yes,resizable=yes'); return false;"><bean:message key="action.addcourse" /></a><span style="font-size: larger">]</span>
				<span style="font-size: larger">[</span><a href="###" 
					onclick="editCourse('<%= WebConstants.CONTEXT_ROOT %>/secure/EditCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true&courseid=', 'EditCourse', 'height=700,width=1275,scrollbars=yes,resizable=yes'); return false;"><bean:message key="action.editcourse" /></a><span style="font-size: larger">]</span>
			</logic:notEmpty>
			<logic:empty name="<%= AttributeKeyConstants.COURSES %>">
				<html:hidden property="courseId" value="-1" />
				<bean:message key="round.courseneedsadding" />&nbsp;
				<span style="font-size: larger">[</span><a href="###" 
					onclick="addCourse('<%= WebConstants.CONTEXT_ROOT %>/secure/AddCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true', 'AddCourse', 'height=700,width=1275,scrollbars=yes,resizable=yes'); return false;"><bean:message key="action.addcourse" /></a><span style="font-size: larger">]</span>
			</logic:empty>
			<span style="font-size: larger">[</span><a href="###" onclick="stateProvinceSelected(); return false;"><bean:message key="action.refresh" /></a><span style="font-size: larger">]</span>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.coursetee" /></th>
		<td>
			<logic:notEmpty name="<%= AttributeKeyConstants.COURSE_TEES %>">
				<html:select property="courseTeeNameId" styleClass="combo">
					<html:options
						collection="<%= AttributeKeyConstants.COURSE_TEES %>"
						property="id.teeNameId"						
						labelProperty="teeName.name" />
				</html:select>
			</logic:notEmpty>
			<logic:notEmpty name="<%= AttributeKeyConstants.COURSES %>">
				<!-- Only print this message if there are courses; if there are
				     no courses, there will already be a message on the screen
				     to add a new course, and this message is not needed or 
				     relevant -->
				<logic:empty name="<%= AttributeKeyConstants.COURSE_TEES %>">
					<bean:message key="round.courseneedsupdating_tees" 
						arg0="<%= WebUtils.httpPrefix(request) %>" 
						arg1="<%= AttributeKeyConstants.NO_MENU %>" 
						arg2="<%= AttributeKeyConstants.COURSE_ID_PARAM %>"
						arg3="1275" />
				</logic:empty>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<th style="vertical-align:top"><bean:message key="round.holesplayed" /></th>
		<td>
			<table>
				<logic:greaterThan name="RoundForm" property="numAvailableHoles" value="0">
					<% pageContext.setAttribute("delimeter", WebConstants.DELIMETER); %>
					<tr>
						<td style="width:100px">
							<bean:message key="round.holes" /> ${sessionScope.RoundForm.front9StartingHole} - ${sessionScope.RoundForm.front9StartingHole + 8}:
						</td>
						<td>
							<html:checkbox 
								property="holesPlayed(${sessionScope.RoundForm.front9StartingHole}${delimeter}${sessionScope.RoundForm.front9StartingHole + 8})" 
								value="true" />
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="round.holes" /> ${sessionScope.RoundForm.back9StartingHole} - ${sessionScope.RoundForm.back9StartingHole + 8}:
						</td>
						<td>
							<html:checkbox 
								property="holesPlayed(${sessionScope.RoundForm.back9StartingHole}${delimeter}${sessionScope.RoundForm.back9StartingHole + 8})" 
								value="true" />
						</td>
					</tr>				
				</logic:greaterThan>
				<logic:notEmpty name="<%= AttributeKeyConstants.COURSES %>">
					<logic:equal name="RoundForm" property="numAvailableHoles" value="0">
						<tr><td>
						<bean:message key="round.courseneedsupdating_holes.newwindow" 
							arg0="<%= WebUtils.httpPrefix(request) %>" 
							arg1="<%= AttributeKeyConstants.NO_MENU %>"
							arg2="<%= AttributeKeyConstants.COURSE_ID_PARAM %>" 
							arg3="1275" />
						&nbsp;
						<span style="font-size: larger">[</span><a href="###" onclick="alert('<bean:message key="round.courseneedsupdating_holes_explanation" />'); return false;"><bean:message key="round.courseneedsupdating_holes_explanation_link" /></a><span style="font-size: larger">]</span>
						</td></tr>
					</logic:equal>
				</logic:notEmpty>
			</table>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.collectperholestats" /></th>
		<td>
			<html:select styleId="collectPerHoleStats" property="collectPerHoleStats" onchange="collectPerHoleStatsOnChange(); return false;" styleClass="combo">
				<html:option value="true" key="option.true" />	
				<html:option value="false" key="option.false" />
			</html:select>
		</td>
	</tr>
</table>