<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<table class="statsum">
	<!--<tr>
		<th><bean:message key="summary.statistic" /></th>
		<th><bean:message key="summary.yournumbers" /></th>
	</tr>-->
	<tr>
		<th><bean:message key="summary.scoringaverage" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgScore" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.eaglesperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumEagles" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.birdiesperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumBirdies" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.parsperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumPars" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.bogeysperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumBogeys" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.dblbogeysperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumDblBogeys" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.tplbogeysperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumTplBogeys" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.par3average" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.par3Avg" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.par4average" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.par4Avg" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.par5average" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.par5Avg" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.puttsperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgTotalPutts" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.puttsperroundgir" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgTotalPuttsGir" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.avgputts" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgPutts" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.avgputtsgir" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgPuttsGir" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.girpercentage" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgGirPercentage" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.fairwayshitpercentage" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgFairwaysHitPercentage" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.drivingdistanceavg" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgDrivingDistance" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.sandsaveopportunities" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumSandSaveOpportunities" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.avgsandsaveconversion" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgSandSaveConvertPercentage" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.updownopportunities" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgNumUpDownOpportunities" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.avgupdownconversion" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="summary.avgUpDownConvertPercentage" /></td>
	</tr>			
</table>