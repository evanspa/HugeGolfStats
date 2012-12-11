<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="name.paulevans.golf.web.struts.formbean.RoundForm" %>
<%@ page import="name.paulevans.golf.LabelStringFactory" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.Locale" %>

<!-- Show the scores for each hole -->
<h3><bean:message key="round.scores" /></h3>

<!-- Show the legend -->
<jsp:include page="include-showscorelegend.jsp" />
<br />
<%
	Integer startingHoles[];
	boolean collectedNumPutts, collectedGIR, collectedTeeClubUsed, collectedTeeShotAccuracy, 
		collectedTeeShotDistance;
	RoundForm form;
	int loop, hole, score, par;
	String scoreClass, holeStr, inOut, numPutts, hitGIR, hitFairway;
	LabelStringFactory strFactory;
	
	strFactory = LabelStringFactory.getInstance((Locale)session.getAttribute(AttributeKeyConstants.LOCALE));
	form = (RoundForm)session.getAttribute("RoundForm");
	startingHoles = form.getStartingHoles();
	collectedNumPutts = BooleanUtils.toBoolean(form.getCollectNumPutts());
	collectedGIR = BooleanUtils.toBoolean(form.getCollectGir());
	collectedTeeClubUsed = BooleanUtils.toBoolean(form.getCollectClubUsedOffTee());
	collectedTeeShotAccuracy = BooleanUtils.toBoolean(form.getCollectFairwayHit());
	collectedTeeShotDistance = BooleanUtils.toBoolean(form.getCollectTeeShotDistance());
	numPutts = null;
	for (loop = 0; loop < startingHoles.length; loop++) {
		pageContext.setAttribute("startingHole", startingHoles[loop]);
	%>
		<table>
		<tr>
		<td>
			<table class="enter-round-scores-table">
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.holenum" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { %>
			<td class="rounddisplay" style="text-align:center;background-color:#FFFFCC;border: medium solid #FFF;font-weight:bold;padding-top:5px;padding-bottom:5px">
				<%= hole %>
			</td>
	<% } %>
				</tr>
<!-- Start PAR DISPLAY display -->				
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.par" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="rounddisplay" style="text-align:center;border: medium solid #FFF;padding-top:5px;padding-bottom:5px">
				<bean:write name="RoundForm" property="par(${hole})" />
			</td>
	<% } %>
				</tr>
<!-- End PAR DISPLAY display -->				
<!-- Start SCORES display -->				
				<tr>
					<th style="width:${thwidth}"><bean:message key="round.score" /></th>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) {
			pageContext.setAttribute("hole", hole);
			holeStr = Integer.toString(hole);
			score = Integer.parseInt(form.getScore(holeStr));
			par = Integer.parseInt(form.getPar(holeStr));
			if (collectedNumPutts) {
				numPutts = form.getNumPutts(holeStr);
			}
			scoreClass = "par"; // init to white...
			if (score == (par + 1)) {  // bogey...
			    scoreClass = "bogey";
			} else if (score == (par - 1)) {  // birdie...
			    scoreClass = "birdie";
			} else if (score <= (par - 2)) {  // eagle or better...
			    scoreClass = "eagle";
			    if (score == 1) {
			    	scoreClass = "ace"; // hole-in-one!!!
			    }
			} else if (score == (par + 2)) { // double bogey...
			    scoreClass = "dblbogey";
			} else if (score >= (par + 3)) { // triple bogey or worse...
			    scoreClass = "dblbogey";
			}
	%>
			<td class="<%= scoreClass %>" style="text-align:center;padding-top:10px;padding-bottom:10px">
				<bean:write name="RoundForm" property="score(${hole})" />
			</td>
	<% } %>
				</tr>
<!-- End SCORES display -->
<!-- Start TEE CLUB USED display -->	
	<% if (collectedTeeClubUsed) { %>		
				<tr>
					<td style="text-align:right;font-size:75%;width:${thwidth}" nowrap="nowrap">
						<bean:message key="round.view.scores.clubusedofftee" />
					</td>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="rounddisplay" style="text-align:center;font-size:75%">
				<%= form.getTeeShotGolfClubDisplay(Integer.toString(hole), request.getLocale()) %>
			</td>
	<% } %>
				</tr>
	<% } %>
<!-- End TEE CLUB USED display -->
<!-- Start TEE SHOT DISTANCE display -->	
	<% if (collectedTeeShotDistance) { %>		
				<tr>
					<td style="text-align:right;font-size:75%;width:${thwidth}" nowrap="nowrap">
						<bean:message key="round.view.scores.teeshotdistance" />
					</td>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="rounddisplay" style="text-align:center;font-size:75%">			
	<%			if (form.getPar(Integer.toString(hole)).equals("3")) { %>
					&nbsp;
	<%			} else { %>
					&nbsp;<bean:write name="RoundForm" property="teeShotDistance(${hole})" />&nbsp;
	<%			} %>
			</td>
	<% } %>
				</tr>
	<% } %>
<!-- End TEE SHOT DISTANCE display -->
<!-- Start PUTTs display -->	
	<% if (collectedNumPutts) { %>		
				<tr>
					<td style="text-align:right;font-size:75%;width:${thwidth}" nowrap="nowrap">
						<bean:message key="round.view.scores.numputts" />
					</td>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
	%>
			<td class="rounddisplay" style="text-align:center;font-size:75%">
				<bean:write name="RoundForm" property="numPutts(${hole})" />
			</td>
	<% } %>
				</tr>
	<% } %>
<!-- End PUTTs display -->
<!-- Start TEE SHOT ACCURACY display -->	
	<% if (collectedTeeShotAccuracy) { %>		
				<tr>
					<td style="text-align:right;font-size:75%;width:${thwidth}" nowrap="nowrap">
						<bean:message key="round.view.scores.hitfairway" />
					</td>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
			hitFairway = strFactory.getString("option.false.short");
			if (form.getTeeShotAccuracy(Integer.toString(hole)).equals(
					Integer.toString(Constants.FAIRWAY_HIT_ID))) {
				hitFairway = strFactory.getString("option.true.short");			}
	%>
			<td class="rounddisplay" style="text-align:center;font-size:75%">				
	<%			if (form.getPar(Integer.toString(hole)).equals("3")) { %>
					&nbsp;
	<%			} else { %>
					<%= hitFairway %>
	<%			} %>
			</td>
	<% } %>
				</tr>
	<% } %>
<!-- End TEE SHOT ACCURACY display -->
<!-- Start GIR display -->	
	<% if (collectedGIR) { %>		
				<tr>
					<td style="text-align:right;font-size:75%;width:${thwidth}" nowrap="nowrap">
						<bean:message key="round.view.scores.hitgir" />
					</td>
	<% for (hole = startingHoles[loop]; hole < (startingHoles[loop]+9); hole++) { 
			pageContext.setAttribute("hole", hole);
			hitGIR = strFactory.getString("option.false.short");
			if (!(form.getGreenInRegulation(Integer.toString(hole)).equals(
					Integer.toString(Constants.GIR_NO_GIR)))) {
				hitGIR = strFactory.getString("option.true.short");	
			}
	%>
			<td class="rounddisplay" style="text-align:center;font-size:75%;width:${thwidth}">
				<%= hitGIR %>
			</td>
	<% } %>
				</tr>
	<% } %>
<!-- End GIR display -->	
			</table>
			</td>
			<td style="vertical-align:top;padding-top:2px">
				<table class="enter-round-scores-table">
					<tr>
						<%
							inOut = strFactory.getString("round.in");
							if ((loop == 0) && (startingHoles.length == 2)) { // front-nine of 18-hole round...
								inOut = strFactory.getString("round.out");
							} 
						%>
						<td style="text-align:center;background-color:#FFFFCC;border: medium solid #FFF;font-weight:bold;padding-top:5px;padding-bottom:5px"><%= inOut %></td>
						<% if ((loop == 1) || (startingHoles.length == 1)) { %>
							<td class="rounddisplay" style="text-align:center;background-color:#FFFFCC;border: medium solid #FFF;font-weight:bold;padding-top:5px;padding-bottom:5px">
								<bean:message key="round.total" />
							</td>
						<%	} %>
					</tr>
					<tr>
						<td class="rounddisplay" style="text-align:center;border: medium solid #FFF;padding-top:5px;padding-bottom:5px">
							<bean:write name="RoundForm" property="nineHolePar(${startingHole})" />
						</td>
						<% 
							if ((loop == 1) || (startingHoles.length == 1)) { %>
								<td class="rounddisplay" style="text-align:center;border: medium solid #FFF;padding-top:5px;padding-bottom:5px">
									<bean:write name="RoundForm" property="totalPar" />
								</td>
						<%	} %>
					</tr>
					<tr>
						<td class="rounddisplay" style="text-align:center; font-weight:bold; background-color:#CCCCFF;padding-top:8px;padding-bottom:8px">
							<bean:write name="RoundForm" property="nineHoleScore(${startingHole})" />
						</td>
						<% 
							if ((loop == 1) || (startingHoles.length == 1)) { %>
								<td class="rounddisplay" style="text-align:center; font-weight:bold; background-color:#CCCCFF;padding-top:8px;padding-bottom:8px">
									<bean:write name="RoundForm" property="totalScore" />
								</td>
						<%	} %>
					</tr>
					<tr><td>&nbsp;</td><% if ((loop == 1) || (startingHoles.length == 1)) out.print("<td>&nbsp;</td>"); %></tr>
					<tr><td>&nbsp;</td><% if ((loop == 1) || (startingHoles.length == 1)) out.print("<td>&nbsp;</td>"); %></tr>
					<tr><td>&nbsp;</td><% if ((loop == 1) || (startingHoles.length == 1)) out.print("<td>&nbsp;</td>"); %></tr>
				</table>
			</td>
			</tr>
			</table>
		<% if ((loop+1) < startingHoles.length) { %>
				<br />
		<% } %>
<% } %>
