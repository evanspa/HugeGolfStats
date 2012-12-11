package name.paulevans.golf.web.struts.action.account;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.util.EncryptionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.ForgotPasswordForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.Ostermiller.util.RandPass;

/**
 * Processes the new-password request
 * @author pevans
 *
 */
public class CreateAndNotifyNewPassword extends BaseAction {
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(
			CreateAndNotifyNewPassword.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		ForgotPasswordForm form;
		String newPassword, userId;
		Date birthday;
		NotificationManager notifyMgr;
		PlayerDAO player;
		boolean validInputs;
		EncryptionUtils encryptionUtils;
		
		encryptionUtils = BeanFactory.getEncryptionUtils();
		form = (ForgotPasswordForm)aForm;
		userId = form.getUserId();
		player = getDAOUtils().getPlayerByUserId(userId);
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
			newPassword = new RandPass(RandPass.NONCONFUSING_ALPHABET).getPass(
					Constants.NEW_PASSWORD_LENGTH);
			player.setPasswd(encryptionUtils.digest(newPassword));
			getDAOUtils().saveAccount(player);
			notifyMgr = BeanFactory.getNotificationManager();
			notifyMgr.notifyNewPassword(getUtil(), player, newPassword, 
					getLocale(aRequest));
			return aMapping.findForward(WebConstants.SUCCESS);	
		} else {
			return aMapping.findForward(WebConstants.FAILURE);
		}
	}
}