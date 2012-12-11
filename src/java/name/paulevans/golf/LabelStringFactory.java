package name.paulevans.golf;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Used to obtain i18n label-strings
 * @author pevans
 *
 */
public class LabelStringFactory {
	
	//	 singleton instance...
	private static LabelStringFactory instance;
	
	// misc i18n resource bundle keys...
	public static final String GENERAL_LABEL_NOTAPPLICABLE = 
		"general.label.notapplicable";
	public static final String MENU_ITEM_EDITPROFILE = 
		"menu.loggedin.menuitem.editprofile";
	
	// email label keys...
	public static final String NOTIFY_ACCTCREATED_SUBJECT = 
		"notify.acctcreated.subject";
	public static final String NOTIFY_ACCTCREATED_MESSAGE = 
		"notify.acctcreated.message";
	public static final String NOTIFY_NEWPASSWORD_SUBJECT = 
		"notify.newpassword.subject";
	public static final String NOTIFY_NEWPASSWORD_MESSAGE = 
		"notify.newpassword.message";
	public static final String NOTIFY_USERID_SUBJECT = 
		"notify.userid.subject";
	public static final String NOTIFY_USERID_MESSAGE = 
		"notify.userid.message";	
	public static final String NOTIFY_ADMIN_ACCTCREATED_SUBJECT = 
		"notify.admin.acctcreated.subject";
	public static final String NOTIFY_ADMIN_ACCTCREATED_MESSAGE = 
		"notify.admin.acctcreated.message";
	public static final String NOTIFY_ADMIN_ERRORCAUGHT_SUBJECT = 
		"notify.admin.errorcaught.subject";
	public static final String NOTIFY_ADMIN_ERRORCAUGHT_MESSAGE = 
		"notify.admin.errorcaught.message";		
	
	// resource bundle for the locale...
	private ResourceBundle resources;
	
	/**
	 * Private constructor
	 *
	 */
	private LabelStringFactory(Locale aLocale) {
		
		// load the resource bundle for the default locale...
		resources = ResourceBundle.getBundle(
				Constants.I18N_RESOURCE_FILE_NAME_PREFIX, aLocale);
	}
	
	/**
	 * Returns the label given the inputted key.
	 * @param aKey
	 * @return
	 */
	public String getString(String aKey) {
		return resources.getString(aKey);
	}
		
	/**
	 * Returns the singleton instance.
	 * @return
	 */
	public synchronized static LabelStringFactory getInstance(Locale aLocale) {
		if (instance == null) {
			instance = new LabelStringFactory(aLocale);
		}
		return instance;
	}
}
