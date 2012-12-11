<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- needed to put attributes added by the tiles-defs.xml into scope -->
<tiles:importAttribute scope="request" /> 

<%
	String imageFile;
	
	imageFile = (String)request.getAttribute(AttributeKeyConstants.SCREENSHOT_IMAGEFILE_ATTR);
%>

<html:html xhtml="true" lang="en">
	
	<!-- include head.jsp -->
	<tiles:get name="head" />
	
	<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
	<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->
	
	<!-- <body> tag START -->
	<body>
		<img src="images/screenshots/<%= imageFile %>" />	
	</body>
</html:html>