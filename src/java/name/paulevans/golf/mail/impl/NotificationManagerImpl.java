package name.paulevans.golf.mail.impl;

import java.text.MessageFormat;
import java.util.Locale;

import name.paulevans.golf.Constants;
import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.util.NewUtil;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Notification manager implementation
 * @author pevans
 *
 */
public class NotificationManagerImpl implements NotificationManager {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(
			NotificationManagerImpl.class);
	
	// label string factory object...
	private LabelStringFactory strFactory;
	
	// the mail sender object...
	private MailSender mailSender;
	
	/**
	 * Sends a notification email to the player noting their new password
	 * @param aUtil
	 * @param aPlayer
	 * @param aNewPassword
	 */
	public void notifyNewPassword(NewUtil aUtil, PlayerDAO aPlayer, 
			String aNewPassword, Locale aLocale) {
		
		SimpleMailMessage msg;
		String messageText;
		
		strFactory = LabelStringFactory.getInstance(aLocale);
		msg = new SimpleMailMessage();
		msg.setTo(aPlayer.getEmailAddress());
		msg.setFrom(aUtil.getAdminEmail());
		messageText = strFactory.getString(
				LabelStringFactory.NOTIFY_NEWPASSWORD_MESSAGE);
		msg.setSubject(strFactory.getString(
				LabelStringFactory.NOTIFY_NEWPASSWORD_SUBJECT));
		msg.setText(MessageFormat.format(messageText, aNewPassword, 
				strFactory.getString(
						LabelStringFactory.MENU_ITEM_EDITPROFILE)));
		try {
			mailSender.send(msg);
			logger.info("New password email-notification sent to: [" + 
					aPlayer.getEmailAddress() + "]");
		} catch (MailException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Sends an email to aToEmail
	 * @param aUtil
	 * @param aSubject
	 * @param aMessage
	 */
	public void notify(NewUtil aUtil, String aToEmail, String aSubject, String aMessage) {
		
		SimpleMailMessage msg;
		
		msg = new SimpleMailMessage();
		msg.setTo(aToEmail);
		msg.setFrom(aUtil.getAdminEmail());
		msg.setSubject(aSubject);
		msg.setText(aMessage);
		try {
			mailSender.send(msg);
			logger.info("Email notification [SUBJECT: " + aSubject + 
					"] sent to: [" + aToEmail + "]");
		} catch (MailException e) {
			logger.error(e);
		}
	}

	/**
	 * Sends a notification email to the player noting their account has been
	 * created
	 * @param aPlayer
	 */
	public void notifyAccountCreated(NewUtil aUtil, PlayerDAO aPlayer,
			Locale aLocale) {
		
		SimpleMailMessage msg;
		String messageText;
		
		strFactory = LabelStringFactory.getInstance(aLocale);
		msg = new SimpleMailMessage();
		messageText = strFactory.getString(
				LabelStringFactory.NOTIFY_ACCTCREATED_MESSAGE);
		msg.setTo(aPlayer.getEmailAddress());
		msg.setFrom(aUtil.getAdminEmail());
		msg.setSubject(strFactory.getString(
				LabelStringFactory.NOTIFY_ACCTCREATED_SUBJECT));
		msg.setText(messageText = MessageFormat.format(messageText, 
				aPlayer.getFirstName(), aUtil.getSuggestionsEmail()));	
		try {
			mailSender.send(msg);
			logger.info("Account-created email-notification sent to: [" + 
					aPlayer.getEmailAddress() + "]");
		} catch (MailException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Sends a notification email to the site administrator indicating a new 
	 * account has been created
	 * @param aUtil
	 * @param aPlayer
	 * @param aLocale
	 */
	public void notifyAdminNewAccountCreated(NewUtil aUtil, PlayerDAO aPlayer, 
			Locale aLocale) {
		
		SimpleMailMessage msg;
		String messageText;
		
		strFactory = LabelStringFactory.getInstance(aLocale);
		msg = new SimpleMailMessage();
		msg.setTo(aUtil.getAdminEmail());
		msg.setFrom(aUtil.getAdminEmail());
		msg.setSubject(strFactory.getString(
				LabelStringFactory.NOTIFY_ADMIN_ACCTCREATED_SUBJECT));
		messageText = strFactory.getString(
				LabelStringFactory.NOTIFY_ADMIN_ACCTCREATED_MESSAGE);
		msg.setText(messageText = MessageFormat.format(messageText, 
				aPlayer.getFirstName() + " " + aPlayer.getLastName(), 
				aPlayer.getUserId()));
		try {
			mailSender.send(msg);
		} catch (MailException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Sends a notification email to the site administrator indicating an error
	 * has been caught
	 * @param aUtil
	 * @param aPlayer
	 * @param aAny
	 * @param aLocale
	 */
	public void notifyError(NewUtil aUtil, PlayerDAO aPlayer, Throwable aAny, 
			Locale aLocale) {
		
		SimpleMailMessage msg;
		String messageText;
		
		strFactory = LabelStringFactory.getInstance(aLocale);
		msg = new SimpleMailMessage();
		msg.setTo(aUtil.getAdminEmail());
		msg.setFrom(aUtil.getAdminEmail());
		msg.setSubject(strFactory.getString(
				LabelStringFactory.NOTIFY_ADMIN_ERRORCAUGHT_SUBJECT));
		messageText = strFactory.getString(
				LabelStringFactory.NOTIFY_ADMIN_ERRORCAUGHT_MESSAGE);
		msg.setText(messageText = MessageFormat.format(messageText, 
				aPlayer != null ? aPlayer.getUserId() : "no player in ctx", 
						aUtil.getTodaysDate(), aAny.getMessage(),
				ExceptionUtils.getFullStackTrace(aAny)));
		try {
			mailSender.send(msg);
		} catch (MailException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Sends a notification email to aPlayer noting their user id
	 * @param aUtil
	 * @param aPlayer
	 */
	public void notifyUserId(NewUtil aUtil, PlayerDAO aPlayer, Locale aLocale) {
		
		SimpleMailMessage msg;
		String messageText;
		
		strFactory = LabelStringFactory.getInstance(aLocale);
		msg = new SimpleMailMessage();
		messageText = strFactory.getString(
				LabelStringFactory.NOTIFY_USERID_MESSAGE);
		msg.setTo(aPlayer.getEmailAddress());
		msg.setFrom(aUtil.getAdminEmail());
		msg.setSubject(strFactory.getString(
				LabelStringFactory.NOTIFY_USERID_SUBJECT));
		msg.setText(messageText = MessageFormat.format(messageText, 
				aPlayer.getUserId()));	
		try {
			mailSender.send(msg);
			logger.info("User Id email-notification sent to: [" + 
					aPlayer.getEmailAddress() + "]");
		} catch (MailException e) {
			logger.error(e);
		}		
	}
	
	/**
	 * Sends a notification to the HGS staff - this is from a player using the
	 * "contact us" page
	 */
	public void notifyContactUsMessage(NewUtil aUtil, String aFromName, 
			String aFromEmail, String aMessage, int aMessageType, 
			PlayerDAO aPlayer, Locale aLocale) {
	
		SimpleMailMessage msg;
		StringBuffer messagePreface;
		
		messagePreface = new StringBuffer();
		messagePreface.append("[User logged-in? ").append(aPlayer != null ? "Yes" : "No");
		messagePreface.append("]");
		messagePreface.append("\n[Inputted from-name: {").append(aFromName).append("}]");
		if (aPlayer != null) {
			messagePreface.append("\n[Logged-in user's email address: {").append(aPlayer.getEmailAddress());
			messagePreface.append("}]\n[Logged-in user's name: {").append(aPlayer.getFirstName());
			messagePreface.append(" ").append(aPlayer.getLastName()).append("}]");
			messagePreface.append("\n[Logged-in user's id: {").append(aPlayer.getId());
			messagePreface.append("}]");
		}
		messagePreface.append("\n\n");
		msg = new SimpleMailMessage();
		msg.setTo(aUtil.getAdminEmail());
		msg.setFrom(aFromEmail);
		msg.setSubject(getContactUsSubject(aMessageType));
		msg.setText(messagePreface + aMessage);
		try {
			mailSender.send(msg);
			logger.info("'Contact Us' email-notification sent to HGS admin [" + 
					aUtil.getAdminEmail() + "]");
		} catch (MailException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Used by the notifyContactUs* methods to get the message subject
	 * @param aMessageType
	 * @return
	 */
	private static final String getContactUsSubject(int aMessageType) {
		switch (aMessageType) {
		case Constants.MSGTYPE_ENHANCEMENTREQUEST:
			return Constants.MSGTYPE_ENHANCEMENTREQUEST_LABEL;
		case Constants.MSGTYPE_GENERALQUESTION:
			return Constants.MSGTYPE_GENERALQUESTION_LABEL;
		case Constants.MSGTYPE_OTHER:
			return Constants.MSGTYPE_OTHER_LABEL;
		default:
			return Constants.MSGTYPE_UNKNOWN_LABEL;
		}
	}

	/**
	 * Sets the mail sender
	 * @param aMailSender
	 */
	public void setMailSender(MailSender aMailSender) {
		mailSender = aMailSender;
	}
}
