package name.paulevans.golf.web.struts.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.ProfileForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Edits an account
 * @author pevans
 *
 */
public class PrepareViewAccountAction extends BaseAction {
	
	// logger object...
	private final static Logger logger = Logger.getLogger(
			PrepareViewAccountAction.class);

	/**
	 * Processes the account-view request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		ProfileForm form;
		HttpSession httpSession;
		PlayerDAO player;
		
		// get the session...
		httpSession = aRequest.getSession();
		
		// down-cast the action form...
		form = (ProfileForm)aForm;	
		
		// clear the form...
		form.clear();
		
		// set ctx-indicator...
		form.setCreateNewAccount(false);
		form.setEditProfile(true);
		
		// init the form bean...
		form.init(getUtil(), aRequest, player = NewUtil.getPlayer(aRequest));
		
		// put the calendar months into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getMonths(
				getLocale(aRequest)), AttributeKeyConstants.CALENDAR_MONTHS);
					
		// refresh lookup-objects on the form bean so the descriptions are
		// available to display on the next screen...
		form.loadObjects(getDAOUtils(), getLocale(aRequest));
		if (player.getCourse() != null) {
			form.setHomeCourseDescription(player.getCourse().getDescription());
		}
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
