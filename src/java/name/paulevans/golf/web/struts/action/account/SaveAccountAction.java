package name.paulevans.golf.web.struts.action.account;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.auth.RoleConstants;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.dao.UserRoleDAO;
import name.paulevans.golf.dao.UserRoleId;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.util.EncryptionUtils;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.ProfileForm;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Creates an account
 * @author pevans
 *
 */
public class SaveAccountAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private final static Logger logger = Logger.getLogger(
			SaveAccountAction.class);

	/**
	 * Processes the account-create request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		ProfileForm form;
		PlayerDAO player;
		UserRoleDAO userRole;
		UserRoleId userRoleCompositeKey;
		HeadWearDAO headWear;
		PantWearDAO pantWear;
		EyeWearDAO eyeWear;
		TeeNameDAO teeColor;
		CourseDAO course;
		boolean isEditMode;
		HttpSession session;
		EncryptionUtils encryptionUtils;
		
		// down-cast the action form...
		form = (ProfileForm)aForm;	
		
		// get the session...
		session = aRequest.getSession();
		
		// get the encryption utils object...
		encryptionUtils = BeanFactory.getEncryptionUtils();
		
		// get (or create) and populate the player object...
		isEditMode = true;
		player = NewUtil.getPlayer(aRequest);
		if (player == null) {
			isEditMode = false;
			player = BeanFactory.newPlayerDAO();
			player.setDateCreated(new Date());
			player.setLastUpdateDate(player.getDateCreated());
		} else {
			player.setLastUpdateDate(new Date());
		}
		player.setFirstName(form.getFirstName());
		player.setLastName(form.getLastName());
		player.setEmailAddress(form.getEmailAddress());
		player.setUserId(form.getUserId());
		player.setPostalCode(form.getPostalCode());
		player.setBirthdate(getUtil().parse(form.getBirthdayMonth() + "/" + 
				form.getBirthdayDay() + "/" + form.getBirthdayYear()));
		player.setGHINNumber(StringUtils.isBlank(
				form.getGhinNumber()) ? null : form.getGhinNumber());
		
		// If we're creating a new account, the validation rule will guarantee
		// the password is not empty.  If the user is already logged in and is
		// simply updating his/her profile, it is possible and ok that the
		// password will be empty - in this case, we don't want to set our
		// player DAO with an empty password.
		if (StringUtils.isNotBlank(form.getPassword())) {
			player.setPasswd(encryptionUtils.digest(form.getPassword()));
		}
		player.setSwingsRightHanded(form.getSwingsRightHanded());
		player.setPuttsRightHanded(form.getPuttsRightHanded());
		headWear = BeanFactory.newHeadWearDAO();
		headWear.setId(new Integer(form.getHeadWearTypeId()));
		player.setHeadWear(headWear);
		pantWear = BeanFactory.newPantWearDAO();
		pantWear.setId(new Integer(form.getPantWearTypeId()));
		player.setPantWear(pantWear);
		eyeWear = BeanFactory.newEyeWearDAO();
		eyeWear.setId(new Integer(form.getEyeWearTypeId()));
		player.setEyeWear(eyeWear);
		player.setNumHolesPlayed(form.getNumHoles());
		teeColor = BeanFactory.newTeeNameDAO();
		teeColor.setId(new Integer(form.getTeeNameId()));
		player.setTeeName(teeColor);
		player.setWearsVest(form.getVestWorn());
		player.setWearsLongSleeves(form.getLongSleevesWorn());
		player.setUsesCaddie(form.getUsesCaddie());
		player.setUsesCart(form.getUsesCart());
		setScorecardValues(player, form);
		
		// create and set the userRole object comp-id object...
		userRole = BeanFactory.newUserRoleDAO();
		userRoleCompositeKey = new UserRoleId();
		userRoleCompositeKey.setUserId(player.getUserId());
		userRoleCompositeKey.setRoleName(RoleConstants.USER_ROLE);
		userRole.setId(userRoleCompositeKey);
		
		// set the player's home course if set...
		course = null;
		if (form.getCourseId() != Constants.NO_HOME_COURSE_SET_ID) {
			course = BeanFactory.newCourseDAO();
			course.setId(new Integer(form.getCourseId()));
			course.load();
			form.setHomeCourseDescription(course.getDescription());
		} 
		player.setCourse(course);
		
		// save the account...
		getDAOUtils().saveAccount(userRole, player);
		
		// send a notification email to the player if we've create a 
		// NEW account...
		if (!isEditMode) {
			sendNotification(player, getLocale(aRequest));
			notifyAdminNewAccount(player, getLocale(aRequest));
		}
		
		// refresh lookup-objects on the form bean so the descriptions are
		// available to display on the next screen...
		form.loadObjects(getDAOUtils(), getLocale(aRequest));
		
		// re-add the player to the session...
		session.setAttribute(AttributeKeyConstants.PLAYER, player);
		
		// log the transaction...
		logger.info("Account saved [player email address: " + 
				player.getEmailAddress() + "]");
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
	
	/**
	 * Set the collect-stat values on the player
	 * @param aPlayer
	 * @param aForm
	 */
	private void setScorecardValues(PlayerDAO aPlayer, ProfileForm aForm) {
		
		// init values to false (this step is necessary because we're using
		// checkboxes instead of yes/no dropdowns...
		aPlayer.setCollectApproachShotDistance(false);
		aPlayer.setCollectApproachShotLine(false);
		aPlayer.setCollectNumPutts(false);
		aPlayer.setCollectFairwayHit(false);
		aPlayer.setCollectGir(false);
		aPlayer.setCollectClubUsedOffTee(false);
		aPlayer.setCollectTeeShotDistance(false);
		aPlayer.setCollectSandSave(false);
		aPlayer.setCollectUpDown(false);
		
		// set values from form...
		aPlayer.setCollectApproachShotDistance(BooleanUtils.toBoolean(
				aForm.getCollectApproachShotDistance()));
		aPlayer.setCollectApproachShotLine(BooleanUtils.toBoolean(
				aForm.getCollectApproachShotLine()));
		aPlayer.setCollectNumPutts(BooleanUtils.toBoolean(
				aForm.getCollectNumPutts()));
		aPlayer.setCollectFairwayHit(BooleanUtils.toBoolean(
				aForm.getCollectFairwayHit()));
		aPlayer.setCollectGir(BooleanUtils.toBoolean(aForm.getCollectGir()));
		aPlayer.setCollectClubUsedOffTee(BooleanUtils.toBoolean(
				aForm.getCollectClubUsedOffTee()));
		aPlayer.setCollectTeeShotDistance(BooleanUtils.toBoolean(
				aForm.getCollectTeeShotDistance()));
		aPlayer.setCollectSandSave(BooleanUtils.toBoolean(
				aForm.getCollectSandSave()));
		aPlayer.setCollectUpDown(BooleanUtils.toBoolean(
				aForm.getCollectUpDown()));
	}
	
	/**
	 * Sends a notification email to the player
	 * @param aPlayer
	 */
	private void sendNotification(PlayerDAO aPlayer, Locale aLocale) {
		
		NotificationManager notifyMgr;
		
		notifyMgr = BeanFactory.getNotificationManager();
		notifyMgr.notifyAccountCreated(getUtil(), aPlayer, aLocale);
	}
	
	/**
	 * Sends a notification email to the site administrator
	 * @param aPlayer
	 */
	private void notifyAdminNewAccount(PlayerDAO aPlayer, Locale aLocale) {
		
		NotificationManager notifyMgr;
		
		notifyMgr = BeanFactory.getNotificationManager();
		notifyMgr.notifyAdminNewAccountCreated(getUtil(), aPlayer, aLocale);
	}
}
