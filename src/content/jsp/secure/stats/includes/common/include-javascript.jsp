<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@ page import="name.paulevans.golf.web.WebUtils" %>
<%@ page import="name.paulevans.golf.web.WebConstants" %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%= WebConstants.CONTEXT_ROOT %>/js/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"> 
	var cal = new CalendarPopup();
	
	function clearDates() {
		var formObj = document.getElementById("mainForm");
		formObj.toDate.value="";
		formObj.fromDate.value="";
	}
	
	function chartSelected() {
	
		var chartDropDown;
		var formObj = document.getElementById("mainForm");
	
		chartDropDown = formObj.chartClass;
		if (chartDropDown.options[chartDropDown.selectedIndex].value != '-') {
			formObj.chartName.value = chartDropDown.options[chartDropDown.selectedIndex].title;
			formObj.submit();
		}
	}
	
	function showPrinterFriendlyVersion() {
	
		var chartDropDown;
		var formObj = document.getElementById("mainForm");
	
		formObj.action=NewAction;
		if (formObj.chartClass != null) {
			chartDropDown = formObj.chartClass;
			formObj.chartName.value = chartDropDown.options[chartDropDown.selectedIndex].title;
		}
		formObj.submit();
	}
</SCRIPT>