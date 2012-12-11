package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PostRoundPageOneSubmitAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(
			PostRoundPageOneSubmitAction.class);

	/**
	 * Process the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		RoundForm roundForm;
		HttpSession httpSession;
		
		httpSession = aRequest.getSession();
		
		// downcast form...
		roundForm = (RoundForm)aForm;
		
		// load the holes...
		roundForm.loadHoles();
		
		if (BooleanUtils.toBoolean(roundForm.getCollectPerHoleStats())) {
			
			// set defaults...
			roundForm.setDefaultPuttValue(getUtil().getDefaultPuttValue());
			
			// add tee shot accuracy types to the session...
			SessionUtils.addToSession(httpSession, 
					getDAOUtils().getTeeShotAccuracyTypes(getLocale(aRequest)),
					AttributeKeyConstants.TEE_SHOT_ACCURACY_TYPES);
			
			// add green-in-regulation types to the session...
			SessionUtils.addToSession(httpSession, 
					getDAOUtils().getGreenInRegulationTypes(getLocale(aRequest)),
					AttributeKeyConstants.GREEN_IN_REGULATION_TYPES);
			
			// add approach shot line types to the session...
			SessionUtils.addToSession(httpSession, 
					getDAOUtils().getApproachShotLineTypes(getLocale(aRequest)),
					AttributeKeyConstants.APPROACH_SHOT_LINE_TYPES);
			
			// add approach shot distance types to the session...
			SessionUtils.addToSession(httpSession, 
					getDAOUtils().getApproachShotDistanceTypes(getLocale(aRequest)),
					AttributeKeyConstants.APPROACH_SHOT_DISTANCE_TYPES);		
			
			// add golf club types to the session...
			SessionUtils.addToSession(httpSession, 
					getDAOUtils().getGolfClubTypes(getLocale(aRequest)),
					AttributeKeyConstants.GOLF_CLUB_TYPES);	
			
			return aMapping.findForward(WebConstants.COLLECT_STATS);
		} else {
			return aMapping.findForward(WebConstants.NOT_COLLECT_STATS);
		}
	}
}