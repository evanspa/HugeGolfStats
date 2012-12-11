<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="java.text.*" %>
<%@ page import="org.apache.commons.lang.*" %>
<%@ page import="java.util.*" %>

<%
	int loop;
	String abbrValue, abbrName, value, name;
	Runtime rt;
	NumberFormat numFormat	;

	rt = Runtime.getRuntime();
	numFormat = NumberFormat.getInstance();
%>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "300px"); %>

<h2><bean:message key="admin.systeminfo.about.heading" /></h2>
<h3>Runtime Information</h3>
	<table class="form-table">
		<tr>
			<th style="width:${thwidth}">Available processors to JVM</th>
			<td><%= rt.availableProcessors() %></td>
		</tr>
		<tr>
			<th style="width:${thwidth}">JVM free memory [Runtime.freeMemory()]</th>
			<td><%= numFormat.format(rt.freeMemory()) %> bytes</td>
		</tr>
		<tr>
			<th style="width:${thwidth}">JVM max memory [Runtime.maxMemory()]</th>
			<td><%= numFormat.format(rt.maxMemory()) %> bytes</td>
		</tr>
	</table>

	<h3>Environment Variables</h3>
	<table class="form-table">
		<%
			Map<String,String> vals = System.getenv();
			Set<String> keys = vals.keySet();
			String keysArray[] = keys.toArray(new String[keys.size()]);
			for (loop = 0; loop < keysArray.length; loop++) { %>
				<tr>
					<%
							name = keysArray[loop];
							abbrName = StringUtils.abbreviate(name, 25);
							value = vals.get(keysArray[loop]);
							abbrValue = value;
							if (value != null && value.length() > 4) {
								abbrValue = StringUtils.abbreviate(value, 70);
							}
						%>
					<th title='<%= name %>' style="width:${thwidth}"><%= abbrName %></th>	
					<td title='<%= value %>'><%= abbrValue %></td>
				</tr>
		<% } %>
	</table>

	<h3>System Properties</h3>
	<table class="form-table">
		<%
			Properties props = System.getProperties();
			Enumeration names = props.propertyNames();
			while (names.hasMoreElements()) { %>
				<tr>
					<%
							name = (String)names.nextElement();
							abbrName = StringUtils.abbreviate(name, 25);
							value = props.getProperty(name);
							abbrValue = value;
							if (value != null && value.length() > 4) {
								abbrValue = StringUtils.abbreviate(value, 64);
							}
						%>
					<th title='<%= name %>' style="width:${thwidth}"><%= abbrName %></th>	
					<td title='<%= value %>'><%= abbrValue %></td>
				</tr>
		<% } %>
	</table>
</div>
