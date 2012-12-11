<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="name.paulevans.golf.web.struts.formbean.RoundForm" %>
<%@ page import="name.paulevans.golf.web.struts.formbean.BaseForm" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>

<style type="text/css">
	.inputscores {font-size:90%}
</style>

<h3>Input Scores:</h3>

<!-- Input the scores for each hole -->

<%
	Integer startingHoles[];
	RoundForm form;
	int loop, hole;
	String oddColor = "#FFFFFF";
	String evenColor = "#CCFFCC";
	String bgColor;
	
	form = (RoundForm)session.getAttribute("RoundForm");
	startingHoles = form.getStartingHoles();
	for (loop = 0; loop < startingHoles.length; loop++) {
	%>

			<table class="enter-round-scores-table ">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.holenum" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { %>
			<td style="text-align:center;background-color:#FFFFCC;border: medium solid #FFF;font-weight:bold;padding-top:5px;padding-bottom:5px"><%= hole %></td>
	<% } %>
				</tr>
<!-- Start PAR DISPLAY input -->				
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.par" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td style="text-align:center;border: medium solid #FFF;padding-top:5px;padding-bottom:5px">
				<bean:write name="RoundForm" property="par(${hole})" />
			</td>
	<% } %>
				</tr>
<!-- End PAR DISPLAY input -->				
<!-- Start SCORES input -->				
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.score" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) {
			pageContext.setAttribute("hole", hole);
	%>
			<td>
				<html:select 
					property="score(${hole})" style="width:100%">
					<html:options
						name="<%= AttributeKeyConstants.UTIL %>" 
						property="scoreValues" />
				</html:select>
			</td>
	<% } %>
				</tr>
<!-- End SCORES input -->
<!-- Start TEE CLUB USED input -->				
	<logic:equal name="RoundForm" property="collectClubUsedOffTee" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.teeshotclub" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) {
			pageContext.setAttribute("hole", hole);
	%>
			<td>
				<html:select 
					property="teeShotGolfClub(${hole})" style="width:100%">
					<html:options
						collection="<%= AttributeKeyConstants.GOLF_CLUB_TYPES %>" 
						property="id" 
						labelProperty="description" />
				</html:select>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End TEE CLUB USED input -->
<!-- Start TEE SHOT DISTANCE input -->				
	<logic:equal name="RoundForm" property="collectTeeShotDistance" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.teeshotdistance" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) {
		pageContext.setAttribute("hole", hole);
	%>
			<td class="cellinput">
	<%			if (form.getPar(Integer.toString(hole)).equals("3")) { %>
					<html:hidden property="teeShotDistance(${hole})" value="<%= Integer.toString(BaseForm.NA_VALUE) %>" />
					<bean:message key="general.label.notapplicable" />
	<%			} else { %>
					<html:text property="teeShotDistance(${hole})" maxlength="3" style="width:100%" />
	<%			} %>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End TEE SHOT DISTANCE input -->
<!-- Start NUM PUTTS input -->				
	<logic:equal name="RoundForm" property="collectNumPutts" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.numputts" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="cellinput">
				<html:select  
					property="numPutts(${hole})" style="width:100%">
					<html:options
						name="<%= AttributeKeyConstants.UTIL %>" 
						property="puttValues" />
				</html:select>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End NUM PUTTS input -->
<!-- Start TEE SHOT ACCURACY input -->				
	<logic:equal name="RoundForm" property="collectFairwayHit" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.teeshotaccuracy" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) {
		pageContext.setAttribute("hole", hole);
	%>
			<td class="cellinput">
	<%			if (form.getPar(Integer.toString(hole)).equals("3")) { %>
					<html:hidden property="teeShotAccuracy(${hole})" value="<%= Integer.toString(BaseForm.NA_VALUE) %>" />
					<bean:message key="general.label.notapplicable" />
	<%			} else { %>
					<html:select 
						property="teeShotAccuracy(${hole})" style="width:100%">
						<html:options
							collection="<%= AttributeKeyConstants.TEE_SHOT_ACCURACY_TYPES %>" 
							property="id" 
							labelProperty="description" />
					</html:select>
	<%			} %>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End TEE SHOT ACCURACY input -->
<!-- Start GREEN-IN-REGULATION input -->				
	<logic:equal name="RoundForm" property="collectGir" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.greeninregulation" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="cellinput">
				<html:select 
					property="greenInRegulation(${hole})" style="width:100%">
					<html:options
						collection="<%= AttributeKeyConstants.GREEN_IN_REGULATION_TYPES %>" 
						property="id" 
						labelProperty="description" />
				</html:select>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End GREEN-IN-REGULATION input -->
<!-- Start APPROACH SHOT - LINE input -->				
	<logic:equal name="RoundForm" property="collectApproachShotLine" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.approachshotline" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="cellinput">
	<%			if (form.getPar(Integer.toString(hole)).equals("3")) { %>
					<html:hidden property="approachShotLine(${hole})" value="<%= Integer.toString(BaseForm.NA_VALUE) %>" />
					<bean:message key="general.label.notapplicable" />
	<%			} else { %>
					<html:select property="approachShotLine(${hole})" style="width:100%">
						<html:options
							collection="<%= AttributeKeyConstants.APPROACH_SHOT_LINE_TYPES %>" 
							property="id" 
							labelProperty="description" />
					</html:select>
	<%			} %>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End APPROACH SHOT - LINE input -->
<!-- Start APPROACH SHOT - DISTANCE input -->				
	<logic:equal name="RoundForm" property="collectApproachShotDistance" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.approachshotdistance" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="cellinput">
	<%			if (form.getPar(Integer.toString(hole)).equals("3")) { %>
					<html:hidden property="approachShotDistance(${hole})" value="<%= Integer.toString(BaseForm.NA_VALUE) %>" />
					<bean:message key="general.label.notapplicable" />
	<%			} else { %>
					<html:select property="approachShotDistance(${hole})" style="width:100%">
						<html:options
							collection="<%= AttributeKeyConstants.APPROACH_SHOT_DISTANCE_TYPES %>" 
							property="id" 
							labelProperty="description" />
					</html:select>
	<%			} %>
			</td>

	<% } %>
				</tr>
	</logic:equal>
<!-- End APPROACH SHOT - DISTANCE input -->
<!-- Start SAND SAVE input -->				
	<logic:equal name="RoundForm" property="collectSandSave" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.sandsave" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td>
				<bean:message key="round.attempt" />&nbsp;<html:checkbox property="sandSaveAttempt(${hole})" value="true" />
				<br />
				<bean:message key="round.convert" />&nbsp;<html:checkbox property="sandSaveConvert(${hole})" value="true" />
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End SAND SAVE input -->
<!-- Start UP AND DOWN input -->				
	<logic:equal name="RoundForm" property="collectUpDown" value="<%= Constants.TRUE %>">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.upanddown" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td>
				<bean:message key="round.attempt" />&nbsp;<html:checkbox property="upDownAttempt(${hole})" value="true" />
				<br />
				<bean:message key="round.convert" />&nbsp;<html:checkbox property="upDownConvert(${hole})" value="true"/>
			</td>
	<% } %>
				</tr>
	</logic:equal>
<!-- End UP AND DOWN input -->
				<tr>
			</table>
		<% if ((loop+1) < startingHoles.length) { %>
				<br />
		<% } %>
<% } %>