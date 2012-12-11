<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.Constants" %>

<!--<tr><td align="left" colspan="2"><bean:message key="profile.header.scorecard" /></td></tr>-->
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectclubusedofftee" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectClubUsedOffTee" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectteeshotdistance" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectTeeShotDistance" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectnumputts" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectNumPutts" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectfairwayhit" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectFairwayHit" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectgir" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectGir" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectapproachshotline" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectApproachShotLine" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectapproachshotdistance" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectApproachShotDistance" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectsandsave" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectSandSave" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="profile.collectupdown" /></th>
	<td>&nbsp;<html:checkbox name="PlayerObject" property="collectUpDown" value="<%= Constants.TRUE %>" /></td>
</tr>

