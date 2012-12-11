package name.paulevans.golf.web.struts.action.account;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.ForgotUserIdForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the request to notify the user of their forgotten-userid
 * @author pevans
 *
 */
public class NotifyUserIdAction extends BaseAction {
	
	// logger object...
	private static Logger logger = Logger.getLogger(NotifyUserIdAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		ForgotUserIdForm form;
		String emailAddress;
		Date birthday;
		NotificationManager notifyMgr;
		PlayerDAO player;
		boolean validInputs;
		
		form = (ForgotUserIdForm)aForm;
		emailAddress = form.getEmailAddress();
		player = getDAOUtils().getPlayerByEmailAddress(emailAddress);
		validInputs = false;
		if (player != null) {
			 
			// validate the player's birthday and resident postal-code matches 
			// what the user entered on the form...
			birthday = player.getBirthdate();
			if (getUtil().datesMatch(birthday, form.getBirthdayMonth(), 
					form.getBirthdayDay(), form.getBirthdayYear())) {
				if (player.getPostalCode().equals(form.getPostalCode())) {
					validInputs = true;
				}
			}
		}
		if (validInputs) {
			notifyMgr = BeanFactory.getNotificationManager();
			notifyMgr.notifyUserId(getUtil(), player, getLocale(aRequest));
			return aMapping.findForward(WebConstants.SUCCESS);	
		} else {
			return aMapping.findForward(WebConstants.FAILURE);
		}
	}
}