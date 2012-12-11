<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>

<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="3600"/>
	<meta name="revisit-after" content="2 days"/>
	<meta name="robots" content="index,follow"/>
	<meta name="copyright" content="HugeGolfStats.com"/>
	<meta name="author" content="Paul Evans"/>
	<meta name="distribution" content="global"/>
	<meta name="description" content="Your page description here ..."/>
	<meta name="keywords" content="Your keywords, keywords, keywords, here ..."/>

	<link rel="stylesheet" type="text/css" href="<%= WebConstants.CONTEXT_ROOT %>/css/basic.css"/>
	<link rel="stylesheet" type="text/css" href="<%= WebConstants.CONTEXT_ROOT %>/css/tabs.css"/>

	<title><bean:message key="title.prefix"/>&nbsp;:&nbsp;<bean:message name="title"/></title>
</head>