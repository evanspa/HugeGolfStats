package name.paulevans.golf.web.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Edit a golf course
 * @author pevans
 *
 */
public class PrepareSearchCoursesAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		CourseForm courseForm;
		StateProvinceDAO stateProvince;
		HttpSession httpSession;
		
		httpSession = aRequest.getSession();
		courseForm = (CourseForm)aForm;
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// clear the course form...
		courseForm.clear();
		
		/// initialize form with state-province/country IDs...
		stateProvince = getUtil().getStateProvince(aRequest);
		courseForm.setCountryId(
				((StateProvinceId)stateProvince.getId()).getCountryId());
		courseForm.setStateProvinceId(
				(Integer)((StateProvinceId)stateProvince.getId()).getId());
		
		// put the collection of countries into the session...
		httpSession.setAttribute(AttributeKeyConstants.COUNTRIES,
				getDAOUtils().getCountries());		
		
		// put the collection state-provinces into the session...
		httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				getDAOUtils().getStateProvinces(courseForm.getCountryId()));	
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
