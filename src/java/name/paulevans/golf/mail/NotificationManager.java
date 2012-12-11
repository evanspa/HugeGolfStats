package name.paulevans.golf.mail;

import java.util.Locale;

import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;

import org.springframework.mail.MailSender;

/**
 * Central interface for sending email notifications to users
 * @author pevans
 *
 */
public interface NotificationManager {
	
	/**
	 * Sets the mail sender
	 * @param aMailSender
	 */
	void setMailSender(MailSender aMailSender);
	
	/**
	 * Sends a notification email to aPlayer noting their account has been
	 * created
	 * @param aUtil
	 * @param aPlayer
	 */
	void notifyAccountCreated(NewUtil aUtil, PlayerDAO aPlayer, Locale aLocale);
	
	/**
	 * Sends an email to aToEmail
	 * @param aUtil
	 * @param aSubject
	 * @param aMessage
	 */
	void notify(NewUtil aUtil, String aToEmail, String aSubject, String aMessage);
	
	/**
	 * Sends a notification email to the site administrator indicating a new 
	 * account has been created
	 * @param aUtil
	 * @param aPlayer
	 * @param aLocale
	 */
	void notifyAdminNewAccountCreated(NewUtil aUtil, PlayerDAO aPlayer, 
			Locale aLocale);
	
	/**
	 * Sends a notification email to the site administrator indicating an error
	 * has been caught
	 * @param aUtil
	 * @param aPlayer
	 * @param aAny
	 * @param aLocale
	 */
	void notifyError(NewUtil aUtil, PlayerDAO aPlayer, Throwable aAny, 
			Locale aLocale);
	
	/**
	 * Sends a notification email to aPlayer noting their new password
	 * @param aUtil
	 * @param aPlayer
	 * @param aNewPassword
	 */
	void notifyNewPassword(NewUtil aUtil, PlayerDAO aPlayer, 
			String aNewPassword, Locale aLocale);
	
	/**
	 * Sends a notification email to aPlayer noting their user id
	 * @param aUtil
	 * @param aPlayer
	 */
	void notifyUserId(NewUtil aUtil, PlayerDAO aPlayer, Locale aLocale);
	
	/**
	 * Sends a notification to the HGS staff - this is from a player using the
	 * "contact us" page
	 */
	void notifyContactUsMessage(NewUtil aUtil, String aFromName, String aFromEmail,
			String aMessage, int aMessageType, PlayerDAO aPlayer, Locale aLocale);	
}
