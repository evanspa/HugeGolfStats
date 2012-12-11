package name.paulevans.golf.web.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepare to edit a course
 * @author pevans
 *
 */
public class PrepareEditCourseAction extends BaseAction {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(
			PrepareEditCourseAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		CourseForm courseForm;
		NewUtil util;
		HttpSession httpSession;
		CourseDAO course;
		String courseHoles[];
		int numExistingCourseHoles;
		List<String> modifiedCourseHolesList;
		
		httpSession = aRequest.getSession();
		courseForm = (CourseForm)aForm;
		
		// clear the course form...
		courseForm.clear();
		courseForm.setEditMode(true);
		
		// get the util object...
		util = getUtil();
		
		// create and load the course dao...
		course = BeanFactory.newCourseDAO();
		course.setId(Integer.parseInt(aRequest.getParameter(
				AttributeKeyConstants.COURSE_ID_PARAM)));
		course.load();

		// init form bean using the course DAO...
		courseForm.initialize(util, course);

		// only allow the user to edit the existing holes or add new holes..
		courseHoles = util.getNumCourseHoles();
		numExistingCourseHoles = course.getNumTotalHoles();
		modifiedCourseHolesList = new ArrayList<String>();
		for (String numHoles : courseHoles) {
			if (Integer.parseInt(numHoles) >= numExistingCourseHoles) {
				modifiedCourseHolesList.add(numHoles);
			}
		}
		httpSession.setAttribute(AttributeKeyConstants.NUM_COURSE_HOLES, 
				modifiedCourseHolesList.toArray(
						new String[modifiedCourseHolesList.size()]));

		// put the collection of countries into the session...
		httpSession.setAttribute(AttributeKeyConstants.COUNTRIES,
				getDAOUtils().getCountries());		

		// put the collection state-provinces into the session...
		httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				getDAOUtils().getStateProvinces(
						courseForm.getCountryId()));	

		// put the collection of tee colors into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getTeeNames(),
				AttributeKeyConstants.TEE_NAMES);

		// set default values...
		courseForm.setDefaultParValue(getUtil().getDefaultParValue());

		// put the course into the session...
		httpSession.setAttribute(AttributeKeyConstants.COURSE, course);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
