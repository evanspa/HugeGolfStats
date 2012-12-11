<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.AttributeKeyConstants" %>

<h2 class="pagetitle"><bean:message key="taketour.heading" /></h2>

<br />
<div class="section">
<h3><a name="statssummary"></a><bean:message key="taketour.statssummary.heading" /></h3>
<p><bean:message key="taketour.statssummary.text" /></p>
<p>
  	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=stats-summary.png">
  		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_stats-summary.jpg" />
  	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=stats-summary.png"><bean:message key="taketour.statssummary.image-view" /></a><span style="font-size:larger">]</span>
</p>
</div>

<br />
<div class="section">
<h3><a name="handicapsummary"></a><bean:message key="taketour.handicapsummary.heading" /></h3>
<p><bean:message key="taketour.handicapsummary.text" /></p>
<p>
  	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=handicap-summary.png">
  		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_handicap-summary.jpg" />
  	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=handicap-summary.png"><bean:message key="taketour.handicapsummary.image-view" /></a><span style="font-size:larger">]</span>
</p>
</div>

<br />
<div class="section">
<h3><a name="recentrounds"></a><bean:message key="taketour.recentrounds.heading" /></h3>
<p><bean:message key="taketour.recentrounds.text" /></p>
<p>
  	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=recentrounds.png">
  		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_recentrounds.jpg" />
  	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=recentrounds.png"><bean:message key="taketour.recentrounds.image-view" /></a><span style="font-size:larger">]</span>
</p>
</div>

<br />
<div class="section">
<h3><a name="postingaround"></a><bean:message key="taketour.postingaround.heading" /></h3>
<p><bean:message key="taketour.postingaround.text" /></p>
<p>
  	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=post-round-page1.png">
  		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_post-round-page1.jpg" />
  	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=post-round-page1.png"><bean:message key="taketour.postingaround.page1.image-view" /></a><span style="font-size:larger">]</span>
</p>
<p>
  	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=post-round-page2.png">
  		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_post-round-page2.jpg" />
  	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=post-round-page2.png"><bean:message key="taketour.postingaround.page2.image-view" /></a><span style="font-size:larger">]</span>
</p>
<p>
  	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=post-round-page3.png">
  		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_post-round-page3.jpg" />
  	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=post-round-page3.png"><bean:message key="taketour.postingaround.page3.image-view" /></a><span style="font-size:larger">]</span>
</p>
</div>

<br />	
<div class="section">
<h3><a name="statistics"></a><bean:message key="taketour.statistics.heading" /></h3>
<p><bean:message key="taketour.statistics.text" /></p>
<p>
	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=avgputtsperhole.png">
		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_avgputtsperhole.jpg" />
	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=avgputtsperhole.png"><bean:message key="taketour.statistics.avgputtsperhole.image-view" /></a><span style="font-size:larger">]</span>
</p>
<p>
	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=teeshotaccuracy.png">
		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_teeshotaccuracy.jpg" />
	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=teeshotaccuracy.png"><bean:message key="taketour.statistics.teeshotaccuracy.image-view" /></a><span style="font-size:larger">]</span>
</p>
<p>
	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=putttypes.png">
		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_putttypes.jpg" />
	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=putttypes.png"><bean:message key="taketour.statistics.putttypes.image-view" /></a><span style="font-size:larger">]</span>
</p>	
</div>

<br />
<div class="section">
<h3><a name="playerprofile"></a><bean:message key="taketour.playerprofile.heading" /></h3>
<p><bean:message key="taketour.playerprofile.text" /></p>
<p>
	<a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=playerprofile.png">
		<img border="0" src="<%= WebUtils.httpPrefix(request) %>/images/screenshots/thumbnails/th_playerprofile.jpg" />
	</a>
	<br />
	<span style="font-size:larger">[</span><a href="ViewScreenshot.do?<%= AttributeKeyConstants.IMAGEFILE_PARAM %>=playerprofile.png"><bean:message key="taketour.playerprofile.image-view" /></a><span style="font-size:larger">]</span>
</p>
</div>
