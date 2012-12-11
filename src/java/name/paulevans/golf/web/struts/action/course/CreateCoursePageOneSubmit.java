package name.paulevans.golf.web.struts.action.course;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class CreateCoursePageOneSubmit extends BaseAction {
	
	/**
	 * Empty implementation.
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		CourseForm form;
		Map<String,String> parValues, courseTees;
		int loop, numHoles, holeNum;
		String teeIds[];
		String key, defaultParValue;
		
		// downcast the form...
		form = (CourseForm)aForm;
		
		// get the array of tee color ids...
		courseTees = form.getCourseTees();
		teeIds = (String[])courseTees.values().toArray(
    			new String[courseTees.size()]);
		
		// get the default par value...
		/*defaultParValue = getUtil().getDefaultParValue();
		
		// init the par values to the default...
		parValues = form.getParValues();
		numHoles = form.getNumHoles();
		for (loop = 0; loop < teeIds.length; loop++) {
			
			// init par values...
			for (holeNum = 1; holeNum <= numHoles; holeNum++) {
				key = teeIds[loop] + "-" + holeNum;
				parValues.put(key, defaultParValue);
			}
		}*/
	
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}