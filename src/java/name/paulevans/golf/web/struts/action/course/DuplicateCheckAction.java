package name.paulevans.golf.web.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Checks to see if a similarly-named course already exists within the database
 * @author pevans
 *
 */
public class DuplicateCheckAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private final static Logger logger = Logger.getLogger(
			DuplicateCheckAction.class);

	/**
	 * Checks to see if a course with a similar name already exists within the
	 * database
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		CourseForm form;
		CourseDAO searchResults[];
		HttpSession session;
		
		// down-cast the action form...
		form = (CourseForm)aForm;	
		
		// get the session...
		session = aRequest.getSession();
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// get the search results...
		searchResults = getDAOUtils().searchCourses(form.getCity(), 
				form.getCountryId(), form.getStateProvinceId(), 
				form.getCourseName(), form.getFirstSearchResultNum(), 
				form.getMaxSearchResultsNum());
		
		if (searchResults.length == 0) {
			// return success...
			return aMapping.findForward(WebConstants.SUCCESS);
		} else {
			// potential duplicates found; add the results to the request...
			session.setAttribute(AttributeKeyConstants.COURSES, searchResults);
			return aMapping.findForward(WebConstants.FAILURE);
		}
	}
}
