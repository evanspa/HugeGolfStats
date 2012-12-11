	
	function editCourse(aURL, aTitle, aWindowProperties) {
	  editCourse(aURL, aTitle, aWindowProperties, -1);
	}
	
	function editCourse(aURL, aTitle, aWindowProperties, aNoHomeCourseId) {
		var courseid;
		var url;
		var formObj = document.getElementById("mainForm");
		
		courseid = formObj.courseId.options[formObj.courseId.selectedIndex].value;
		if (courseid == aNoHomeCourseId) {
			alert("'edit course' link not valid for this selection");
		} else {
			url = aURL + courseid;
			window.open(url, aTitle, aWindowProperties);
		}
	}
	
	function addCourse(aURL, aTitle, aWindowProperties) {
		var countryid;
		var stateProvinceId;
		var url;
		var formObj = document.getElementById("mainForm");
		
		countryId = formObj.countryId.options[formObj.countryId.selectedIndex].value;
		stateProvinceId = formObj.stateProvinceId.options[formObj.stateProvinceId.selectedIndex].value;
		url = aURL + '&countryid=' + countryId + '&stateprovinceid=' + stateProvinceId;
		window.open(url, aTitle, aWindowProperties);
	}