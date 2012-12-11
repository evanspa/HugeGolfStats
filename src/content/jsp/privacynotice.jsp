<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<h2><bean:message key="privacynotice.heading" /></h2>

<br />
<div class="section">
<p><bean:message arg0="Welcome.do" key="privacynotice.openningtext.a" /></p>
<p><bean:message arg0="TermsOfUse.do" key="privacynotice.openningtext.b" /></p>
<p><bean:message key="privacynotice.openningtext.c" /></p>
</div>

<br />
<div class="section">
<h3><bean:message key="privacynotice.informationcollected.heading" /></h3>
<ul>
	<li><bean:message key="privacynotice.informationcollected.text.a" /></li>
	<li><bean:message key="privacynotice.informationcollected.text.b" /></li>
	<li><bean:message key="privacynotice.informationcollected.text.c" /></li>
	<li><bean:message key="privacynotice.informationcollected.text.d" /></li>
</ul>
</div>

<br />
<div class="section">
<h3><bean:message key="privacynotice.usesinformation.heading" /></h3>
<ul>
	<li><bean:message key="privacynotice.usesinformation.text.a" /></li>
	<li><bean:message key="privacynotice.usesinformation.text.b" /></li>
	<li><bean:message key="privacynotice.usesinformation.text.c" /></li>
	<li><bean:message key="privacynotice.usesinformation.text.d" /></li>
	<li><bean:message key="privacynotice.usesinformation.text.e" /></li>			
</ul>
</div>

<br />
<div class="section">
<h3><bean:message key="privacynotice.informationdisclosure.heading" /></h3>
<ul>
	<li><bean:message key="privacynotice.informationdisclosure.text.a" /></li>
	<li><bean:message key="privacynotice.informationdisclosure.text.b" /></li>
	<li><bean:message key="privacynotice.informationdisclosure.text.c" /></li>
	<li><bean:message key="privacynotice.informationdisclosure.text.d" /></li>
	<li><bean:message arg0="TermsOfUse.do" key="privacynotice.informationdisclosure.text.e" /></li>		
</ul>
</div>

<br />
<div class="section">
<h3><bean:message key="privacynotice.ads.heading" /></h3>
<p><bean:message key="privacynotice.ads.text.a" /></p>		
<p><bean:message key="privacynotice.ads.text.b" /></p>
<p><bean:message key="privacynotice.ads.text.c" /></p>
<p>
	<bean:message key="privacynotice.ads.text.d" />
	<ul>
		<li>
			<strong><bean:message key="privacynotice.ads.text.networks.1.name" /></strong>
			<br />
			<a href="<bean:message key="privacynotice.ads.text.networks.1.url"/>" target="_blank">
				<bean:message key="privacynotice.ads.text.networks.1.url"/>
			</a>
		</li>
	</ul>
</p>
</div>