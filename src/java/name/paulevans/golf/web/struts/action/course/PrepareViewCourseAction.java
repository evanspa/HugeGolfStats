package name.paulevans.golf.web.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.bean.CourseWrapperBean;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Creates an golf course
 * @author pevans
 *
 */
public class PrepareViewCourseAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		CourseForm courseForm;
		CourseDAO course;
		
		courseForm = (CourseForm)aForm;
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// create and load course object...
		course = BeanFactory.newCourseDAO();
		course.setId(Integer.parseInt(aRequest.getParameter(
				AttributeKeyConstants.COURSE_ID_PARAM)));
		course.load();
		
		// add the course wrapper to the request as an attribute...
		aRequest.setAttribute(AttributeKeyConstants.COURSE, 
				new CourseWrapperBean(course));

		// init form bean using the course DAO...
		courseForm.initialize(getUtil(), course);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
