package name.paulevans.golf.web.struts.formbean;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.CalendarMonthDAO;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.util.NewUtil;

import org.apache.log4j.Logger;

public class ProfileForm extends BaseForm {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(ProfileForm.class);
	
	// player profile info...
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String postalCode;
	private String ghinNumber;
	private String password;
	private String passwordConfirm;
	private String birthdayYear, birthdayDay;
	private CalendarMonthDAO birthdayMonth;
	private boolean swingsRightHanded;
	private boolean puttsRightHanded;
	private EyeWearDAO eyeWear;
	private PantWearDAO pantWear;
	private HeadWearDAO headWear;
	private int numHoles;
	private TeeNameDAO teeName;
	private int homeCourseCountryId;
	private int homeCourseStateProvinceId;
	private CourseDAO homeCourse;
	private boolean longSleevesWorn;
	private boolean vestWorn;
	private boolean usesCart;
	private boolean usesCaddie;
	private String userId;
	private String collectClubUsedOffTee;
	private String collectTeeShotDistance;
	private String collectNumPutts;
	private String collectFairwayHit;
	private String collectGir;
	private String collectApproachShotLine;
	private String collectApproachShotDistance;
	private String collectSandSave;
	private String collectUpDown;
	
	// indicators as to what the context is...
	private boolean isCreateNewAccount;
	private boolean isEditProfile;
	
	/**
	 * public no-arg constructor
	 */
	public ProfileForm() {
		clear();
	}
	
	/**
	 * Clears the form
	 *
	 */
	public void clear() {
		
		// initializations...
		firstName = null;
		lastName = null;
		emailAddress = null;
		postalCode = null;
		ghinNumber = null;
		password = null;
		passwordConfirm = null;
		birthdayYear = null;
		birthdayDay = null;
		collectClubUsedOffTee = null;
		collectTeeShotDistance = null;
		collectNumPutts = null;
		collectFairwayHit = null;
		collectGir = null;
		collectApproachShotLine = null;
		collectApproachShotDistance = null;
		collectSandSave = null;
		collectUpDown = null;
		swingsRightHanded = true;
		puttsRightHanded = true;
		homeCourseCountryId = 0;
		homeCourseStateProvinceId = 0;
		numHoles = 18;
		teeName = BeanFactory.newTeeNameDAO();
		teeName.setId(Constants.WHITE_TEE_COLOR_ID);
		eyeWear = BeanFactory.newEyeWearDAO();
		pantWear = BeanFactory.newPantWearDAO();
		headWear = BeanFactory.newHeadWearDAO();
		homeCourse = BeanFactory.newCourseDAO();
		birthdayMonth = BeanFactory.newCalendarMonthDAO();
	}
	
	/**
	 * Callback to reset checkbox-backed properties
	 *
	 */
	public void reset() {
		collectClubUsedOffTee = "";
		collectTeeShotDistance = "";
		collectNumPutts = "";
		collectFairwayHit = "";
		collectGir = "";
		collectApproachShotLine = "";
		collectApproachShotDistance = "";
		collectSandSave = "";
		collectUpDown = "";
	}
	
	/**
	 * Re-loads the lookup objects based on the IDs to get the descriptions
	 * to display on the "viewprofile" screen.
	 * @param aSession
	 */
	public void loadObjects(DAOUtils aDAOUtils, Locale aLocale) {		
		aDAOUtils.loadObjects(birthdayMonth, eyeWear, headWear, pantWear, 
				teeName, aLocale);		
	}
	
	/**
	 * Initializes the bean.
	 * @param aUtil
	 * @param aRequest
	 */
	public void init(NewUtil aUtil, HttpServletRequest aRequest) {
		init(aUtil, aRequest, null);
	}
	
	/**
	 * Initializes the bean.
	 * @param aUtil
	 * @param aRequest
	 */
	public void init(NewUtil aUtil, HttpServletRequest aRequest, 
			PlayerDAO aPlayer) {
		
		StateProvinceDAO stateProvince;
		CourseDAO homeCourse;
		Calendar birthdate;
		
		stateProvince = aUtil.getStateProvince(aRequest);
		setCountryId(
				((StateProvinceId)stateProvince.getId()).getCountryId());
		setStateProvinceId(
				((StateProvinceId)stateProvince.getId()).getId());
		setCourseId(Constants.NO_HOME_COURSE_SET_ID);
		if (aPlayer != null) {
			homeCourse = aPlayer.getCourse();
			if (homeCourse != null) {
				setCourseId((Integer)homeCourse.getId());
			}
			setFirstName(aPlayer.getFirstName());
			setLastName(aPlayer.getLastName());
			setEmailAddress(aPlayer.getEmailAddress());
			setUserId(aPlayer.getUserId());
			setPostalCode(aPlayer.getPostalCode());
			birthdate = Calendar.getInstance();
			birthdate.setTime(aPlayer.getBirthdate());
			setBirthdayDay(Integer.toString(birthdate.get(
					Calendar.DAY_OF_MONTH)));
			setBirthdayMonth(birthdate.get(Calendar.MONTH) + 1);
			setBirthdayYear(Integer.toString(birthdate.get(Calendar.YEAR)));
			if (aPlayer.getGHINNumber() != null) {
				setGhinNumber(aPlayer.getGHINNumber());
			}
			setNumHoles(aPlayer.getNumHolesPlayed());
			if (aPlayer.getTeeName().getId() != null) {
				setTeeNameId((Integer)aPlayer.getTeeName().getId());
			}
			setSwingsRightHanded(aPlayer.isSwingsRightHanded());
			setPuttsRightHanded(aPlayer.isPuttsRightHanded());
			setHeadWearTypeId((Integer)aPlayer.getHeadWear().getId());
			setPantWearTypeId((Integer)aPlayer.getPantWear().getId());
			setEyeWearTypeId((Integer)aPlayer.getEyeWear().getId());
			setLongSleevesWorn(aPlayer.isWearsLongSleeves());
			setVestWorn(aPlayer.isWearsVest());
			setUsesCaddie(aPlayer.getUsesCaddie());
			setUsesCart(aPlayer.getUsesCart());
		}
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Setter
	 * @param emailAddress
	 */
	public final void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter
	 * @param firstName
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getGhinNumber() {
		return ghinNumber;
	}
	
	/**
	 * Setter
	 * @param ghinNumber
	 */
	public final void setGhinNumber(String ghinNumber) {
		this.ghinNumber = ghinNumber;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter
	 * @param lastName
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getPassword() {
		return password;
	}
	
	/**
	 * Setter
	 * @param password
	 */
	public final void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	/**
	 * Setter
	 * @param passwordConfirm
	 */
	public final void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public boolean getSwingsRightHanded() {
		return swingsRightHanded;
	}
	
	/**
	 * Setter
	 * @param playsRightHanded
	 */
	public void setSwingsRightHanded(boolean playsRightHanded) {
		this.swingsRightHanded = playsRightHanded;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public boolean getPuttsRightHanded() {
		return puttsRightHanded;
	}
	
	/**
	 * Setter
	 * @param puttsRightHanded
	 */
	public void setPuttsRightHanded(boolean puttsRightHanded) {
		this.puttsRightHanded = puttsRightHanded;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getEyeWearTypeId() {
		return (Integer)eyeWear.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getEyeWearDescription() {
		return eyeWear.getDescription();
	}

	/**
	 * Setter
	 * @param eyeWearTypeId
	 */
	public void setEyeWearTypeId(int eyeWearTypeId) {
		eyeWear.setId(eyeWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public int getHeadWearTypeId() {
		return (Integer)headWear.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getHeadWearDescription() {
		return headWear.getDescription();
	}

	/**
	 * Setter
	 * @param headWearTypeId
	 */
	public void setHeadWearTypeId(int headWearTypeId) {
		headWear.setId(headWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public int getPantWearTypeId() {
		return (Integer)pantWear.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getPantWearDescription() {
		return pantWear.getDescription();
	}

	/**
	 * Setter
	 * @param pantWearTypeId
	 */
	public void setPantWearTypeId(int pantWearTypeId) {
		pantWear.setId(pantWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean getLongSleevesWorn() {
		return longSleevesWorn;
	}

	/**
	 * Setter
	 * @param longSleevesWorn
	 */
	public void setLongSleevesWorn(boolean sleevesWorn) {
		this.longSleevesWorn = sleevesWorn;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean getVestWorn() {
		return vestWorn;
	}

	/**
	 * Setter
	 * @param vestWorn
	 */
	public void setVestWorn(boolean vestWorn) {
		this.vestWorn = vestWorn;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getNumHoles() {
		return numHoles;
	}

	/**
	 * Setter
	 * @param numHoles
	 */
	public void setNumHoles(int numHoles) {
		this.numHoles = numHoles;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getTeeNameId() {
		return (Integer)teeName.getId();
	}

	/**
	 * Setter
	 * @param aTeeNameId
	 */
	public void setTeeNameId(int aTeeNameId) {
		teeName.setId(aTeeNameId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getTeeColorDescription() {
		return teeName.getName();
	}

	/**
	 * Getter - returns the country ID of the player's home course
	 * @return
	 */
	public final int getCountryId() {
		return homeCourseCountryId;
	}

	/**
	 * Setter - sets the country ID of the player's home course
	 * @param aHomeCourseCountryId
	 */
	public final void setCountryId(int aHomeCourseCountryId) {
		this.homeCourseCountryId = aHomeCourseCountryId;
	}

	/**
	 * Getter - returns the state/province ID of the player's home course
	 * @return
	 */
	public final int getStateProvinceId() {
		return homeCourseStateProvinceId;
	}

	/**
	 * Setter - sets the state/province ID of the player's home course
	 * @param homeCourseStateProvinceId
	 */
	public final void setStateProvinceId(
			int homeCourseStateProvinceId) {
		this.homeCourseStateProvinceId = homeCourseStateProvinceId;
	}

	/**
	 * Getter - returns the ID of the player's home course
	 * @return
	 */
	public final int getCourseId() {
		return (Integer)homeCourse.getId();
	}

	/**
	 * Setter - sets the ID of the player's home course
	 * @param homeCourseId
	 */
	public final void setCourseId(int homeCourseId) {
		homeCourse.setId(homeCourseId);
	}	
	
	/**
	 * Getter
	 * @return
	 */
	public final String getHomeCourseDescription() {
		return homeCourse.getDescription();
	}
	
	/**
	 * Setter
	 * @param aHomeCourseDescription
	 */
	public final void setHomeCourseDescription(String aHomeCourseDescription) {
		homeCourse.setDescription(aHomeCourseDescription);
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean getCreateNewAccount() {
		return isCreateNewAccount;
	}

	/**
	 * Setter
	 * @param isCreateNewAccount
	 */
	public void setCreateNewAccount(boolean isCreateNewAccount) {
		this.isCreateNewAccount = isCreateNewAccount;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean isEditProfile() {
		return isEditProfile;
	}

	/**
	 * Setter
	 * @param isEditProfile
	 */
	public void setEditProfile(boolean isEditProfile) {
		this.isEditProfile = isEditProfile;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean getUsesCaddie() {
		return usesCaddie;
	}

	/**
	 * Setter
	 * @param aUsesCaddie
	 */
	public void setUsesCaddie(boolean aUsesCaddie) {
		this.usesCaddie = aUsesCaddie;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean getUsesCart() {
		return usesCart;
	}

	/**
	 * Setter
	 * @param aUsesCart
	 */
	public void setUsesCart(boolean aUsesCart) {
		this.usesCart = aUsesCart;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getPostalCode() {
		return postalCode;
	}

	/**
	 * Setter
	 * @param postalCode
	 */
	public final void setPostalCode(String birthday) {
		this.postalCode = birthday;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getBirthdayDay() {
		return birthdayDay;
	}

	/**
	 * Setter
	 * @param birthdayDay
	 */
	public final void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getBirthdayMonth() {
		return birthdayMonth.getMonthNumber();
	}
	
	/**
	 * Returns the name of the player's birthday-month
	 * @return
	 */
	public final String getBirthdayMonthName() {
		return birthdayMonth.getName();
	}

	/**
	 * Setter
	 * @param birthdayMonth
	 */
	public final void setBirthdayMonth(int aBirthdayMonth) {
		birthdayMonth.setMonthNumber(aBirthdayMonth);
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getBirthdayYear() {
		return birthdayYear;
	}

	/**
	 * Setter
	 * @param birthdayYear
	 */
	public final void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * Setter
	 * @param aUserId
	 */
	public final void setUserId(String aUserId) {
		this.userId = aUserId;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectApproachShotDistance() {
		return collectApproachShotDistance;
	}

	/**
	 * Setter
	 * @param collectApproachShotDistance
	 */
	public final void setCollectApproachShotDistance(
			String collectApproachShotDistance) {
		this.collectApproachShotDistance = collectApproachShotDistance;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectApproachShotLine() {
		return collectApproachShotLine;
	}

	/**
	 * Setter
	 * @param collectApproachShotLine
	 */
	public final void setCollectApproachShotLine(String collectApproachShotLine) {
		this.collectApproachShotLine = collectApproachShotLine;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectClubUsedOffTee() {
		return collectClubUsedOffTee;
	}

	/**
	 * Setter
	 * @param collectClubUsedOffTee
	 */
	public final void setCollectClubUsedOffTee(String collectClubUsedOffTee) {
		this.collectClubUsedOffTee = collectClubUsedOffTee;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectFairwayHit() {
		return collectFairwayHit;
	}

	/**
	 * Setter
	 * @param collectFairwayHit
	 */
	public final void setCollectFairwayHit(String collectFairwayHit) {
		this.collectFairwayHit = collectFairwayHit;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectGir() {
		return collectGir;
	}

	/**
	 * Setter
	 * @param collectGir
	 */
	public final void setCollectGir(String collectGir) {
		this.collectGir = collectGir;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectNumPutts() {
		return collectNumPutts;
	}

	/**
	 * Setter
	 * @param collectNumPutts
	 */
	public final void setCollectNumPutts(String collectNumPutts) {
		this.collectNumPutts = collectNumPutts;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectSandSave() {
		return collectSandSave;
	}

	/**
	 * Setter
	 * @param collectSandSave
	 */
	public final void setCollectSandSave(String collectSandSave) {
		this.collectSandSave = collectSandSave;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectTeeShotDistance() {
		return collectTeeShotDistance;
	}

	/**
	 * Setter
	 * @param collectTeeShotDistance
	 */
	public final void setCollectTeeShotDistance(String collectTeeShotDistance) {
		this.collectTeeShotDistance = collectTeeShotDistance;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectUpDown() {
		return collectUpDown;
	}

	/**
	 * Setter
	 * @param collectUpDown
	 */
	public final void setCollectUpDown(String collectUpDown) {
		this.collectUpDown = collectUpDown;
	}
}
