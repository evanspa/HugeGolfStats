package name.paulevans.golf.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.formbean.ContactUsForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes contact-us requests
 * @author pevans
 *
 */
public class PrepareContactUsAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(PrepareContactUsAction.class);

	/**
	 * Processes the logout request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local variables...
		PlayerDAO player;
		ContactUsForm form;
		
		// cast the form object...
		form = (ContactUsForm)aForm;
		
		// attempt to get the player object...
		player = getUtil().getPlayer(aRequest);
		
		// if player exists, initialize the from-email field...
		if (player != null) {
			form.setName(player.getFirstName() + " " + player.getLastName());
			form.setFromEmail(player.getEmailAddress());
		}
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}