<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="name.paulevans.golf.web.struts.formbean.CourseForm" %>

<!-- create a variable that stores the size of the number of tees -->
<bean:size name="CourseForm" property="courseTees" id="numTees" />

<h3><bean:message key="course.holes.header" /></h3>

<!-- show the holes -->
<table class="form-table">
<%
	CourseForm form;
	int outerLoop, numHoles;
	

	form = (CourseForm)session.getAttribute("CourseForm");
	numHoles = form.getNumHoles();
	for (outerLoop = 1; outerLoop <= (numHoles / 9); outerLoop++) {
		pageContext.setAttribute("i", outerLoop);
		if (outerLoop == 1) {
			pageContext.setAttribute("startingHole", form.getFront9StartingHole());
		} else {
			pageContext.setAttribute("startingHole", form.getBack9StartingHole());
		}
%>
	<tr>
		<td>
			<table>
			
				<!-- hole number -->
				<tr>
					<td>&nbsp;</td>
					<td style="font-weight:bold"><bean:message key="course.hole" /></td>
					<c:forEach var="j" begin="${startingHole}" end="${startingHole+8}">
						<td style="text-align:center;background-color:#FFFFCC;border: medium solid #FFF;font-weight:bold;padding:8px">${j}</td>
					</c:forEach>
					<td style="text-align:center; font-weight:bold; background-color:#CCCCFF;border: medium solid #FFF">
						<bean:message key="course.totalpar" />
					</td>			
				</tr>
				<logic:iterate indexId="loop" id="tee" name="CourseForm" property="courseTees">
				
					<!-- show par -->
					<tr>
						<td style="background-color:${tee.key}">&nbsp;</td>
						<td><bean:write name="tee" property="key" /> - <bean:message key="course.par" /></td>
						<c:forEach var="j" begin="${startingHole}" end="${startingHole+8}">
							<td style="padding:8px"><bean:write name="CourseForm" property="parValue(${tee.value}-${j})" /></td>
						</c:forEach>
						<td style="text-align:center; background-color: #EEEEEE; font-weight: bold">
							<bean:write name="CourseForm" property="nineHolePar(${tee.value}-${startingHole})" />
						</td>		
					</tr>					
				</logic:iterate>
			</table>
		</td>
	</tr>
<% } %>	
</table>