package name.paulevans.golf.web.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepares creating a golf course
 * @author pevans
 *
 */
public class PrepareCreateCourseAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		CourseForm courseForm;
		StateProvinceDAO stateProvince;
		NewUtil util;
		HttpSession httpSession;
		String countryId, stateProvinceId;
		
		httpSession = aRequest.getSession();
		courseForm = (CourseForm)aForm;
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// clear out the form (in case there's still one in the session)...
		courseForm.clear();
		
		// get the util object...
		util = getUtil();
		
		// set default number of course holes from ctx parameter...
		courseForm.setNumHoles(util.getDefaultNumCourseHoles());
		
		// initialize form with state-province/country IDs...
		// first try to get from request...
		countryId = aRequest.getParameter("countryid");
		if (StringUtils.isNotBlank(countryId)) {
			stateProvinceId = aRequest.getParameter("stateprovinceid");
		} else {
			stateProvince = util.getStateProvince(aRequest);
			countryId = Integer.toString((
					(StateProvinceId)stateProvince.getId()).getCountryId());
			stateProvinceId = (
					(StateProvinceId)stateProvince.getId()).getId().toString();
		}
		stateProvince = util.getStateProvince(aRequest);
		courseForm.setCountryId(Integer.parseInt(countryId));
		courseForm.setStateProvinceId(Integer.parseInt(stateProvinceId));
		
		// put the collection of countries into the session...
		httpSession.setAttribute(AttributeKeyConstants.COUNTRIES,
				getDAOUtils().getCountries());		
		
		// put the collection state-provinces into the session...
		httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				getDAOUtils().getStateProvinces(courseForm.getCountryId()));	
		
		// put the collection of tee colors into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getTeeNames(),
				AttributeKeyConstants.TEE_NAMES);
		
		// set default values...
		courseForm.setDefaultParValue(getUtil().getDefaultParValue());
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
