package name.paulevans.golf.web.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Creates an golf course
 * @author pevans
 *
 */
public class SearchCoursesAction extends BaseAction {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(
			SearchCoursesAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		CourseForm form;
		CourseDAO searchResults[];
		DAOUtils daoUtils;
		HttpSession session;
		
		// downcast the form...
		form = (CourseForm)aForm;
		
		// get the session...
		session = aRequest.getSession();
		
		// get the daoUtils object...
		daoUtils = getDAOUtils();
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// get the search results...
		searchResults = daoUtils.searchCourses(form.getCity(), 
				form.getCountryId(), form.getStateProvinceId(), 
				form.getCourseName(), form.getFirstSearchResultNum(), 
				form.getMaxSearchResultsNum());
		
		// add the total result count into the session if this isn't a 
		// paging-action...
		if (StringUtils.equals(form.getPageAction(), Constants.FALSE)) {
			session.setAttribute(AttributeKeyConstants.TOTAL_RESULTS_COUNT, 
					daoUtils.searchCoursesCount(form.getCity(), 
							form.getCountryId(), form.getStateProvinceId(), 
							form.getCourseName()));
		}
		
		// add the results to the request...
		session.setAttribute(AttributeKeyConstants.COURSES, searchResults);	
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
