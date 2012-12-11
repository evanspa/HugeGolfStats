<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<SCRIPT LANGUAGE="JavaScript"> 

	function doGoBack() {
		var formObj = document.getElementById("mainForm");
		formObj.action=GoBackAction;
		formObj.submit();
	}

	function doPrint() {
		window.print();
	}
</SCRIPT>