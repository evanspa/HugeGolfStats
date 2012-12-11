package name.paulevans.golf.web.struts.formbean;

import java.util.Locale;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.DAOUtils;

import org.apache.struts.validator.ValidatorForm;

/**
 * Base form bean.
 * @author pevans
 *
 */
public abstract class BaseForm extends ValidatorForm {
	
	/**
	 * Not applicable value
	 */
	public static final int NA_VALUE = -1;
	
	// locale object...
	private Locale locale;
	
	// DAO utils object...
	private DAOUtils daoUtils;
	
	// dummy property referenced in the struts <html:button> tag...
	private String dummy;
	
	// start and end seach result numbers - used for pagination...
	private int firstSearchResultNum;
	private int maxSearchResultsNum;
	
    // stores if the user paged-through search results by clicking 'previous' 
	// or 'next'...
	private String pageAction; 
	
	// generate mode parameter useful in validation routines...
	private String mode;
	
	/**
	 * Returns the DAOUtils object
	 * @return
	 */
	public DAOUtils getDAOUtils() {
		return BeanFactory.getDAOUtils();
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getDummy() {
		return dummy;
	}

	/**
	 * Setter
	 * @param aDummy
	 */
	public final void setDummy(String aDummy) {
		dummy = aDummy;
	}

	/**
	 * Getter
	 * @return
	 */
	public final Locale getLocale() {
		return locale;
	}

	/**
	 * Setter
	 * @param aLocale
	 */
	public final void setLocale(Locale aLocale) {
		locale = aLocale;
	}

	/**
	 * @return the mode
	 */
	public final String getMode() {
		return mode;
	}

	/**
	 * @param aMode the mode to set
	 */
	public final void setMode(String aMode) {
		this.mode = aMode;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getFirstSearchResultNum() {
		return firstSearchResultNum;
	}

	/**
	 * Setter
	 * @param firstSearchResultNum
	 */
	public final void setFirstSearchResultNum(int firstSearchResultNum) {
		this.firstSearchResultNum = firstSearchResultNum;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getMaxSearchResultsNum() {
		return maxSearchResultsNum;
	}

	/**
	 * Setter
	 * @param maxSearchResultsNum
	 */
	public final void setMaxSearchResultsNum(int maxSearchResultNum) {
		this.maxSearchResultsNum = maxSearchResultNum;
	}
	
	/**
	 * Getter
	 * @return Constants.TRUE or Constants.FALSE
	 */
	public final String getPageAction() {
		return pageAction;
	}

	/**
	 * Setter
	 * @param pageAction Constants.TRUE or Constants.FALSE
	 */
	public final void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}
}
