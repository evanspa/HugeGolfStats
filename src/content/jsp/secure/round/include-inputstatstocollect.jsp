<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.Constants" %>

<h3><bean:message key="round.statstocollect" /></h3>
<table class="form-table">
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectclubusedofftee" /></th>
	<td>&nbsp;<html:checkbox property="collectClubUsedOffTee" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectteeshotdistance" /></th>
	<td>&nbsp;<html:checkbox property="collectTeeShotDistance" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectnumputts" /></th>
	<td>&nbsp;<html:checkbox property="collectNumPutts" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectfairwayhit" /></th>
	<td>&nbsp;<html:checkbox property="collectFairwayHit" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectgir" /></th>
	<td>&nbsp;<html:checkbox property="collectGir" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectapproachshotline" /></th>
	<td>&nbsp;<html:checkbox property="collectApproachShotLine" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectapproachshotdistance" /></th>
	<td>&nbsp;<html:checkbox property="collectApproachShotDistance" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectsandsave" /></th>
	<td>&nbsp;<html:checkbox property="collectSandSave" value="<%= Constants.TRUE %>" /></td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message arg0="blue" arg1="bold" key="round.collectupdown" /></th>
	<td>&nbsp;<html:checkbox property="collectUpDown" value="<%= Constants.TRUE %>" /></td>
</tr>
</table>