package name.paulevans.golf.web.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.util.SortUtils;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Sorts the courses search-results
 * @author pevans
 *
 */
public class SortSearchResultsAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		CourseDAO courses[];
		
		// get the courses search-results...
		courses = (CourseDAO[])aRequest.getSession().getAttribute(
				AttributeKeyConstants.COURSES);
		
		// sort the courses...
		SortUtils.sort(courses, aRequest.getParameter(
				WebConstants.SORT_COLUMN_PARAM), aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
