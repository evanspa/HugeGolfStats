package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Edit a round
 * @author pevans
 *
 */
public class PrepareSearchRoundsAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		RoundForm form;
		StateProvinceDAO stateProvince;
		HttpSession httpSession;
		
		httpSession = aRequest.getSession();
		form = (RoundForm)aForm;
		form.initStructures();
		
		/// initialize form with state-province/country IDs...
		stateProvince = getUtil().getStateProvince(aRequest);
		//form.setCountryId(
		//		((StateProvinceId)stateProvince.getId()).getCountryId());
		//form.setStateProvinceId(
		//		(Integer)((StateProvinceId)stateProvince.getId()).getId());
		form.setCountryId(Constants.ANY_OPTION_VAL);
		form.setStateProvinceId(Constants.ANY_OPTION_VAL);
		
		// put the collection of countries into the session...
		httpSession.setAttribute(AttributeKeyConstants.COUNTRIES,
				getDAOUtils().getCountries());		
		
		// put the collection state-provinces into the session...
		httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				getDAOUtils().getStateProvinces(form.getCountryId()));	
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
