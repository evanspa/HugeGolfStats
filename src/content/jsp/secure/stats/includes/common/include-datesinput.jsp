<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<h3><bean:message key="yourstats.dateinput.header" /></h3>
<table class="form-table">
<tr>
	<th style="width:${thwidth}"><bean:message key="yourstats.fromdate" /></th>
	<td>
		<html:text name="StatisticsForm" property="fromDate" size="20" styleClass="field" />
		<span style="font-size: larger">[</span><a href="#" 
		onClick="cal.select(document.forms[1].fromDate,'anchor1','MM/dd/yyyy'); return false;" 
		NAME="anchor1" ID="anchor1"><bean:message key="yourstats.pickdate" /></a><span style="font-size: larger">]</span>
	</td>
</tr>
<tr>
	<th style="width:${thwidth}"><bean:message key="yourstats.todate" /></th>
	<td>
		<html:text name="StatisticsForm" property="toDate" size="20" styleClass="field" />
		<span style="font-size: larger">[</span><a href="#" 
		onClick="cal.select(document.forms[1].toDate,'anchor1','MM/dd/yyyy'); return false;" 
		NAME="anchor1" ID="anchor1"><bean:message key="yourstats.pickdate" /></a><span style="font-size: larger">]</span>
	</td>
</tr>
</table>
