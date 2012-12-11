<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "280px"); %>

<h2><bean:message key="profile.saved" /></h2>

<!-- show personal-info section -->
<jsp:include page="profile/include-showpersonalinfo.jsp" />

<!-- show account information (userid/password) section -->
<jsp:include page="profile/include-showaccountinfo.jsp" />

<!-- show player's home course section -->
<jsp:include page="profile/include-showhomecourseinfo.jsp" />

<!-- show player's playing habits section -->
<jsp:include page="profile/include-showplayinghabitsinfo.jsp" />

<!-- show player's stats-to-collect section -->
<jsp:include page="profile/include-showstatstocollectinfo.jsp" />
