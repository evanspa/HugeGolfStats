<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.struts.formbean.RoundForm" %>
<%@ page import="org.apache.commons.lang.BooleanUtils" %>

<%
	RoundForm form;
	boolean collectNumPutts, collectGir;

	form = (RoundForm)session.getAttribute("RoundForm");
	collectNumPutts = BooleanUtils.toBoolean(form.getCollectNumPutts());
	collectGir = BooleanUtils.toBoolean(form.getCollectGir());
%>

<h3><bean:message key="round.holescorecount" /></h3>
<table class="form-table">
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.numeagles" /></th>
		<td><bean:write name="RoundForm" property="numEagles" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.numbirdies" /></th>
		<td><bean:write name="RoundForm" property="numBirdies" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.numpars" /></th>
		<td><bean:write name="RoundForm" property="numPars" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.numbogeys" /></th>
		<td><bean:write name="RoundForm" property="numBogeys" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.numdblbogeys" /></th>
		<td><bean:write name="RoundForm" property="numDblBogeys" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.numtplbogeys" /></th>
		<td><bean:write name="RoundForm" property="numTplBogeys" /></td>
	</tr>
</table>
<h3><bean:message key="round.puttingstats" /></h3>
<table class="form-table">
	<tr>
		<th style="width:200px"><bean:message key="round.stats.totalputts" /></th>
		<td>
			<logic:equal name="RoundForm" property="collectNumPutts" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="totalPutts" />
			</logic:equal>
			<logic:equal name="RoundForm" property="collectNumPutts" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.avgputtsperhole" /></th>
		<td>
			<logic:equal name="RoundForm" property="collectNumPutts" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="avgPuttsPerHoleDisplay" />
			</logic:equal>
			<logic:equal name="RoundForm" property="collectNumPutts" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.totalputtsgir" /></th>
		<td>
			<%
				if (collectNumPutts && collectGir) { %>
					<bean:write name="RoundForm" property="totalPuttsGIRDisplay" />
			<% } else { %>
					<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.avgputtsperholegir" /></th>
		<td>
			<% if (collectNumPutts && collectGir) { %>
					<bean:write name="RoundForm" property="avgPuttsPerHoleGIRDisplay" />
			<% } else { %>
					<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.num0putts" /></th>
		<td>
			<% if (collectNumPutts) { %>
					<bean:write name="RoundForm" property="numZeroPutts" />
			<% } else { %>
					<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.num1putts" /></th>
		<td>
			<% if (collectNumPutts) { %>
					<bean:write name="RoundForm" property="numOnePutts" />
			<% } else { %>
					<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.num2putts" /></th>
		<td>
			<% if (collectNumPutts) { %>
					<bean:write name="RoundForm" property="numTwoPutts" />
			<% } else { %>
					<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.num3putts" /></th>
		<td>
			<% if (collectNumPutts) { %>
				<bean:write name="RoundForm" property="numThreePutts" />
			<% } else { %>
				<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.num4putts" /></th>
		<td>
			<% if (collectNumPutts) { %>
				<bean:write name="RoundForm" property="numFourPutts" />
			<% } else { %>
				<bean:message key="round.stats.notcollected" />
			<% } %>
		</td>
	</tr>
</table>
<h3><bean:message key="round.avgsbyholetype" /></h3>
<table class="form-table">
	<tr>
		<th style="width:200px"><bean:message key="round.stats.par3avg" /></th>
		<td><bean:write name="RoundForm" property="avgDisplay(3)" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.par4avg" /></th>
		<td><bean:write name="RoundForm" property="avgDisplay(4)" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.par5avg" /></th>
		<td><bean:write name="RoundForm" property="avgDisplay(5)" /></td>
	</tr>
</table>
<h3><bean:message key="round.otherstats" /></h3>
<table class="form-table">
	<tr>
		<th style="width:200px"><bean:message key="round.stats.fairways" /></th>
		<td>				
			<logic:equal name="RoundForm" property="collectFairwayHit" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="fairwaysHitPercentageDisplay" />&nbsp;(<bean:write name="RoundForm" property="numFairwaysHit" />&nbsp;<bean:message key="round.stats.outof" />&nbsp;<bean:write name="RoundForm" property="numFairways" />)
			</logic:equal>
			<logic:equal name="RoundForm" property="collectFairwayHit" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.girs" /></th>
		<td>						
			<logic:equal name="RoundForm" property="collectGir" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="GIRsPercentageDisplay" />&nbsp;(<bean:write name="RoundForm" property="numGIRs" />)
			</logic:equal>
			<logic:equal name="RoundForm" property="collectGir" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.sandsaves" /></th>
		<td>
			<logic:equal name="RoundForm" property="collectSandSave" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="sandSaveConversionPercentageDisplay" />&nbsp;(<bean:write name="RoundForm" property="numSandSaveConversions" />&nbsp;<bean:message key="round.stats.outof" />&nbsp;<bean:write name="RoundForm" property="numSandSaveAttempts" />)
			</logic:equal>
			<logic:equal name="RoundForm" property="collectSandSave" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.upanddowns" /></th>
		<td>
			<logic:equal name="RoundForm" property="collectUpDown" value="<%= Constants.TRUE %>">
			<bean:write name="RoundForm" property="upAndDownConversionPercentageDisplay" />&nbsp;(<bean:write name="RoundForm" property="numUpAndDownConversions" />&nbsp;<bean:message key="round.stats.outof" />&nbsp;<bean:write name="RoundForm" property="numUpAndDownAttempts" />)
			</logic:equal>
			<logic:equal name="RoundForm" property="collectUpDown" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.avgdrivedistance" /></th>
		<td>
			<logic:equal name="RoundForm" property="collectTeeShotDistance" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="avgTeeShotDistanceDisplay" />
			</logic:equal>
			<logic:equal name="RoundForm" property="collectTeeShotDistance" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message key="round.stats.longestdrive" /></th>
		<td>
			<logic:equal name="RoundForm" property="collectTeeShotDistance" value="<%= Constants.TRUE %>">
				<bean:write name="RoundForm" property="longestDrive" />
			</logic:equal>
			<logic:equal name="RoundForm" property="collectTeeShotDistance" value="<%= Constants.FALSE %>">
				<bean:message key="round.stats.notcollected" />
			</logic:equal>
		</td>
	</tr>
</table>
