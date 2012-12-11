<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<h3>[<a href="<%= WebConstants.CONTEXT_ROOT %>/secure/EditScorecard.do"><bean:message key="action.edit" /></a>]&nbsp;&nbsp;<bean:message key="profile.header.scorecard" /></h3>

<table class="form-table">	
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectclubusedofftee" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectClubUsedOffTee"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectClubUsedOffTee"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectteeshotdistance" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectTeeShotDistance"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectTeeShotDistance"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectnumputts" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectNumPutts"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectNumPutts"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectfairwayhit" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectFairwayHit"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectFairwayHit"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectgir" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectGir"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectGir"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectapproachshotline" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectApproachShotLine"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectApproachShotLine"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectapproachshotdistance" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectApproachShotDistance"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectApproachShotDistance"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectsandsave" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectSandSave"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectSandSave"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
	<tr>
		<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectupdown" /></th>
		<td>
			<logic:equal value="true" name="PlayerObject" property="collectUpDown"><bean:message key="option.true" /></logic:equal>
			<logic:equal value="false" name="PlayerObject" property="collectUpDown"><bean:message key="option.false" /></logic:equal>
		</td>
	</tr>
</table>
