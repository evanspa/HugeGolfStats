package name.paulevans.golf.web.struts.formbean;

import java.util.Locale;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CalendarMonthDAO;
import name.paulevans.golf.dao.DAOUtils;


/**
 * Models the forgot-password form
 * @author Paul
 *
 */
public class ForgotPasswordForm extends BaseForm {
	
	private String birthdayYear, birthdayDay;
	private String userId;
	private String postalCode;
	private CalendarMonthDAO birthdayMonth;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public ForgotPasswordForm() {
		clear();
	}
	
	/**
	 * Clears the instance members
	 *
	 */
	public void clear() {
		birthdayMonth = BeanFactory.newCalendarMonthDAO();
		birthdayYear = null;
		birthdayDay = null;
		userId = null;
	}
	
	/**
	 * Re-loads the lookup objects based on the IDs to get the descriptions
	 * to display on the "viewprofile" screen.
	 * @param aSession
	 */
	public void loadObjects(DAOUtils aDAOUtils, Locale aLocale) {		
		aDAOUtils.loadObjects(birthdayMonth, aLocale);		
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
	 * @param userId
	 */
	public final void setUserId(String emailAddress) {
		this.userId = emailAddress;
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
	public final void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}	
}