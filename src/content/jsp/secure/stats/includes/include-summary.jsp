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
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgScore" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.eaglesperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumEagles" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.birdiesperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumBirdies" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.parsperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumPars" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.bogeysperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumBogeys" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.dblbogeysperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumDblBogeys" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.tplbogeysperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumTplBogeys" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.par3average" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="par3Avg" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.par4average" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="par4Avg" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.par5average" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="par5Avg" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.puttsperround" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgTotalPutts" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.puttsperroundgir" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgTotalPuttsGir" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.avgputts" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgPutts" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.avgputtsgir" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgPuttsGir" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.girpercentage" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgGirPercentage" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.fairwayshitpercentage" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgFairwaysHitPercentage" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.drivingdistanceavg" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgDrivingDistance" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.sandsaveopportunities" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumSandSaveOpportunities" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.avgsandsaveconversion" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgSandSaveConvertPercentage" /></td>
	</tr>
	<tr>
		<th><bean:message key="summary.updownopportunities" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgNumUpDownOpportunities" /></td>
	</tr>	
	<tr>
		<th><bean:message key="summary.avgupdownconversion" /></th>
		<td><bean:write name="<%= AttributeKeyConstants.SUMMARY_STATS %>" property="avgUpDownConvertPercentage" /></td>
	</tr>			
</table>