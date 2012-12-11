<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>	
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>	

<%@ page import="name.paulevans.golf.web.struts.formbean.RoundForm" %>
<%@ page import="name.paulevans.golf.dao.CourseDAO" %>
<%@ page import="name.paulevans.golf.dao.CourseSlopeRatingDAO" %>
<%@ page import="name.paulevans.golf.dao.TeeId" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>

<bean:size name="RoundForm" property="startingHoles" id="numStartingHoles" />	

<%
	RoundForm form;
	CourseDAO coursePlayed;
	int teeNameId, loop;
	Integer startingHoles[];
	TeeId teeId;
	CourseSlopeRatingDAO slopeRating;

	form = (RoundForm)session.getAttribute("RoundForm");
	coursePlayed = form.getCourseDAO();
	teeId = (TeeId)form.getTee().getId();
	teeNameId = teeId.getTeeNameId();
	startingHoles = form.getStartingHoles();
%>

<h3><bean:message key="round.courseplayed" /></h3>		
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.country" /></th>
		<td><bean:write name="RoundForm" property="countryDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.state_province" /></th>
		<td><bean:write name="RoundForm" property="stateProvinceDescription" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.course" /></th>
		<td>
			<a href="ViewCourse-page1.do?courseid=${RoundForm.courseId}">
				<%= coursePlayed.getDescription() %>
			</a>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.coursetee" /></th>
		<td><bean:write name="RoundForm" property="teeName" /></td>
	</tr>
	<logic:equal name="numStartingHoles" value="1">
		<%
			for (loop = 0; loop < startingHoles.length; loop++) {
				pageContext.setAttribute("i", startingHoles[loop]);
				slopeRating = coursePlayed.getSlopeRating(teeNameId, startingHoles[loop]);
		%>
			<tr>
				<th style="width:${thwidth}"><bean:message key="round.parholes" />&nbsp;${i} - ${i + 8}:</th>
				<td><bean:write name="RoundForm" property="nineHolePar(${i})" /></td>
			</tr>
			<tr>
				<th style="width:${thwidth}"><bean:message key="round.slopeholes" />&nbsp;${i} - ${i + 8}:</th>
				<td>
					<%= slopeRating != null ? 
							(slopeRating.getSlope() != null ? slopeRating.getSlope().toString() : "") : "" %>
				</td>
			</tr>
			<tr>
				<th style="width:${thwidth}"><bean:message key="round.ratingholes" />&nbsp;${i} - ${i + 8}:</th>
				<td>
					<%= slopeRating != null ? 
							(slopeRating.getRating() != null ? slopeRating.getRating().toString() : "") : "" %>
				</td>
			</tr>			
		<% } %>
	</logic:equal>
	<logic:greaterThan name="numStartingHoles" value="1">
		<tr>
			<th style="width:${thwidth}"><bean:message key="round.totalpar" /></th>
			<td><bean:write name="RoundForm" property="totalPar" /></td>
		</tr>
		<tr>
			<th style="width:${thwidth}"><bean:message key="round.overallslope" /></th>
			<td><bean:write name="RoundForm" property="tee.overallSlope" /></td>
		</tr>
		<tr>
			<th style="width:${thwidth}"><bean:message key="round.overallrating" /></th>
			<td><bean:write name="RoundForm" property="tee.overallRating" /></td>
		</tr>
	</logic:greaterThan>
</table>