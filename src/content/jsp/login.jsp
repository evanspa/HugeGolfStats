<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<script type="text/javascript">
	function cancel() {
		var formObj = document.getElementById("mainForm");
		formObj.action="<%= WebUtils.httpPrefix(request) %>/Welcome.do";
		formObj.submit();
	}	
</script>

<!-- set the header-column width -->
<% request.setAttribute("thwidth", "125px"); %>

<bean:cookie id="userId" name="<%= WebConstants.USER_ID_COOKIE_NAME %>" value="" />

<logic:equal name="userId" property="value" value="">
	<bean:define id="focusField" value="j_username" />
</logic:equal>
<logic:notEqual name="userId" property="value" value="">
	<bean:define id="focusField" value="j_password" />
</logic:notEqual>

<h2><bean:message key="login.header" /></h2>


<% pageContext.setAttribute("createaccturl", WebConstants.CONTEXT_ROOT + "/CreateAccount.do"); %>
<p><bean:message key="login.explanation" arg0="${createaccturl}" /></p>


<form name="j_security_check" id="mainForm" action="<%= WebUtils.securePrefix(request) %>/j_security_check" method="POST">
<table class="form-table">
	<tr>
		<th style="width:${thwidth}; text-align:right"><bean:message key="login.form.userid" /></th>
		<td><input size="35" type="text" name="j_username" value="${userId.value}" class="field" /></td>
	</tr>
	<tr>
		<th style="width:${thwidth}; text-align:right"><bean:message key="login.form.password" /></th>
		<td><input size="35" type="password" name="j_password" class="field" /></td>
	</tr>
</table>

<p>
	<html:button property="dummy" onclick="cancel()" styleClass="button">
		<bean:message key="button.cancel" />
	</html:button>
	<input type="submit" value="Login" class="button" />
</p>
</form>

<br />
<hr />
<p>
	<span style="font-size: larger">[</span><a href="<%= WebConstants.CONTEXT_ROOT %>/ForgotUserId.do"><bean:message key="login.forgotuserid" /></a><span style="font-size: larger">]</span>
<br />
	<span style="font-size: larger">[</span><a href="<%= WebConstants.CONTEXT_ROOT %>/ForgotPassword.do"><bean:message key="login.forgotpassword" /></a><span style="font-size: larger">]</span>
</p>

<!-- Copied from a struts-sample - need to manually add since
     j_security_check is a special page handled by the container -->
<script type="text/javascript" language="JavaScript">
  var focusControl = document.forms["j_security_check"].elements["${focusField}"];
  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
</script>