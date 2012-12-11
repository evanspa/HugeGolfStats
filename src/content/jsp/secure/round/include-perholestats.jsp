<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<tr style="background-color: yellow">
	<td>
		<table>
			<tr>
				<td align="right" style="font-weight: bold">
					<bean:message key="round.collectperholestats" />
				</td>
				<td align="left">
					<html:select styleId="collectPerHoleStats" property="collectPerHoleStats" onchange="collectPerHoleStatsOnChange(); return false;">
						<html:option value="true" key="option.true" />	
						<html:option value="false" key="option.false" />
					</html:select>
				</td>
			</tr>
		</table>
	</td>
</tr>