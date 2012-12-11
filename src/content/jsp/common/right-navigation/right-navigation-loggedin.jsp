<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>
<%@ page import="name.paulevans.golf.util.NewUtil" %>
<%@ page import="name.paulevans.golf.dao.PlayerDAO" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="name.paulevans.golf.web.WebUtils" %>

<script language="Javascript">
	function viewCourse(aURL, aTitle, aWindowProperties) {
		window.open(aURL, aTitle, aWindowProperties);
	}
</script>

<%
	PlayerDAO player;
	player = NewUtil.getPlayer(request);
%>

<!-- B.2 MAIN NAVIGATION -->
<div class="main-navigation">

	<!-- Navigation Level 3 -->
    <div class="round-border-topleft"></div>
    <h1 class="first"><bean:write name="<%= AttributeKeyConstants.UTIL %>" property="todaysDate" /></h1>
		
	<!-- Subtitle -->
	<h2><bean:message key="rightnav.loggedin.welcomemsg" arg0="${PlayerObject.firstName}" /></h2>

    <!-- Navigation with grid style -->
	<ul>
		<li><bean:message key="rightnav.loggedin.yourhandicap" />&nbsp;<a href="<%= WebUtils.httpPrefix(request) %>/secure/HandicapSummary.do"><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="reducedHandicapIndex" /></a></li>
		<li>
			<bean:message key="rightnav.loggedin.yourhomecourse" />&nbsp;
			<logic:present name="<%= AttributeKeyConstants.PLAYER %>" property="course">
				<a href="###" 
					target="_blank" 
					onclick="viewCourse('<%= WebUtils.httpPrefix(request) %>/secure/ViewCourse-page1.do?<%= AttributeKeyConstants.NO_MENU %>=true&courseid=<bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="course.id" />', 'ViewCourse', 'height=750,width=1275,scrollbars=yes,resizable=yes'); return false;">
						<%= StringUtils.abbreviate(player.getCourse().getDescription(), 28) %>
				</a>
			</logic:present>
			<logic:notPresent name="<%= AttributeKeyConstants.PLAYER %>" property="course">
				<bean:message key="profile.homecoursenotset" />
			</logic:notPresent>
		</li>
		<li><bean:message key="rightnav.loggedin.totalroundsposted" />&nbsp;<a href="<%= WebUtils.httpPrefix(request) %>/secure/RecentRounds.do"><bean:write name="<%= AttributeKeyConstants.PLAYER %>" property="totalNumberOfRoundsPosted" /></a></li>
		<li><a href="<%= WebUtils.httpPrefix(request) %>/Logout.do"><bean:message key="rightnav.loggedin.logout" /></a></li>
	</ul>

    <!-- Title -->                
    <h1><bean:message key="rightnav.loggedin.quicklinks" /></h1>  

    <!-- Subtitle -->
    <h2><bean:message key="rightnav.loggedin.actions.header" /></h2>

    <!-- Navigation with bullets -->                    
    <dl class="nav3-bullet">
       	<dt><a href="<%= WebUtils.httpPrefix(request) %>/secure/PostRound-page1.do"><bean:message key="rightnav.loggedin.actions.links.postround" /></a></dt>
       	<dt><a href="<%= WebUtils.httpPrefix(request) %>/secure/Profile.do"><bean:message key="rightnav.loggedin.actions.links.editprofile" /></a></dt>
    </dl>    

    <!-- Subtitle -->
    <!-- <h2><bean:message key="rightnav.loggedin.favoritecharts.header" /></h2>-->

    <!-- Navigation with bullets -->                    
    <!--<dl class="nav3-bullet">
       	<dt><a href="#"><bean:message key="rightnav.loggedin.favoritecharts.links.avgscoresperround" /></a></dt>
        <dt><a href="#"><bean:message key="rightnav.loggedin.favoritecharts.links.scorehistory" /></a></dt>
		<dt><a href="#"><bean:message key="rightnav.loggedin.favoritecharts.links.avgputtsperround" /></a></dt>
		<dt><a href="#"><bean:message key="rightnav.loggedin.favoritecharts.links.avgputtsgirperround" /></a></dt>
    </dl>-->              
</div>
