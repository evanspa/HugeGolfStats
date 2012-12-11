<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

<script language="Javascript">

	function viewCourse(aURL, aTitle, aWindowProperties) {
		window.open(aURL, aTitle, aWindowProperties);
	}
	
</script>
<table width="100%" border="0">
	<tr>
		<th width="25%" align="right" nowrap="nowrap">
			<bean:message key="header.player" />
		</th>
		<td align="left" nowrap="nowrap">
			<bean:write
				name="<%= AttributeKeyConstants.PLAYER %>"
				property="firstName" />&nbsp;
			<bean:write
				name="<%= AttributeKeyConstants.PLAYER %>"
				property="lastName" />
		</td>
		<th width="25%" align="right" nowrap="nowrap">
			<bean:message key="header.todaysdate" />
		</th>
		<td align="left" nowrap="nowrap">
			<bean:write
				name="<%= AttributeKeyConstants.UTIL %>"
				property="todaysDate" />
		</td>
		<th width="25%" align="right" nowrap="nowrap">
			<bean:message key="header.player.numroundsposted" />
		</th>
		<td align="left" nowrap="nowrap">
			<bean:write
					name="<%= AttributeKeyConstants.PLAYER %>"
					property="totalNumberOfRoundsPosted" />
		</td>
	</tr>
	<tr>
		<th width="25%" align="right" nowrap="nowrap">
			<bean:message key="header.handicapindex" />
		</th>
		<td align="left" nowrap="nowrap">
			<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex">
				<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="reducedHandicapIndex" />
			</logic:present>

			<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="handicapIndex">
				<a href="###" onclick="alert('<bean:message key="header.menu.player.handicapindex.whencalculatedmsg" />'); return false;">
					<bean:message key="header.menu.player.handicapindex.notyetcalculated" />
				</a>
			</logic:notPresent>
		</td>
		<th width="25%" align="right" nowrap="nowrap">
			<bean:message key="header.homecourse" />
		</th>
		<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="course">
			<td align="left" nowrap="nowrap">
				<a href="###" 
					target="_blank" 
					onclick="viewCourse('<%= WebUtils.httpPrefix(request) %>/secure/ViewCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true&courseid=<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="course.id" />', 'ViewCourse', 'height=700,width=950,scrollbars=yes,resizable=yes'); return false;">
						<bean:write
							name="<%= AttributeKeyConstants.PLAYER %>"
							property="course.description" />
				</a>
			</td>
		</logic:present>
		<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="course">
			<td align="left">
				<bean:message key="profile.homecoursenotset" />
			</td>
		</logic:notPresent>
	</tr>
</table>