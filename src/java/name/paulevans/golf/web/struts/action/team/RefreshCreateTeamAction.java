package name.paulevans.golf.web.struts.action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.TeamForm;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Process the request of the user changing the country 
 * selections
 * @author pevans
 *
 */
public class RefreshCreateTeamAction extends BaseAction {
	
	// logger object...
	private final static Logger logger = Logger.getLogger(
			RefreshCreateTeamAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		TeamForm form;
		HttpSession httpSession;
		StateProvinceDAO stateProvinceDAOs[];
		StateProvinceId stateProvinceId;
		
		// get the session...
		httpSession = aRequest.getSession();
		
		// down-cast the action form...
		form = (TeamForm)aForm;	
		
		// populate the form and session...
		if (StringUtils.equals(aRequest.getParameter(
				AttributeKeyConstants.COUNTRY_CHANGED), Constants.TRUE)) {
		
			// put the collection state-provinces into the session...
			httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
					stateProvinceDAOs = getDAOUtils().getStateProvinces(
							form.getCountryId()));
			stateProvinceId = (StateProvinceId)stateProvinceDAOs[0].getId();
			form.setStateProvinceId(stateProvinceId.getId());			
		}
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
