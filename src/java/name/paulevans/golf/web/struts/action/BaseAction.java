package name.paulevans.golf.web.struts.action;

import gen.jaxb.name.paulevans.golf.Menu;
import gen.jaxb.name.paulevans.golf.Menubar;
import gen.jaxb.name.paulevans.golf.Menuitem;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.struts.formbean.BaseForm;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the logout
 * @author pevans
 *
 */
public abstract class BaseAction extends Action {
	
	// logger object...
	private static Logger logger = Logger.getLogger(BaseAction.class);
	
	// static members...
	private static final NewUtil util = BeanFactory.getUtilObject();

	/**
	 * Processes the request
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse) 
	throws Exception {
		
		HttpSession httpSession;
		BaseForm form;
		Locale locale;
		
		// get the http session...
		httpSession = aRequest.getSession();
		
		// get the locale object...
		locale = getLocale(aRequest);
		
		// init the util object...
		util.initialize();
		
		// add the util object to the session...
		httpSession.setAttribute(AttributeKeyConstants.UTIL, util);
		
		// add the locale object to the session...
		httpSession.setAttribute(AttributeKeyConstants.LOCALE, locale);
		
		// add the player object to the session if they're logged in...
		addPlayerToSession(aRequest);
		
		// add various "constants" to the session...
		addConstants(httpSession);
		
		// set the locale object...
		if (aForm != null) {
			form = (BaseForm)aForm;
			form.setLocale(locale);
		}

		// set the state of the menubar correctly...
		setMenubarState(util.getMenubar(aRequest), aMapping.getPath() + ".do", util);
		
		// set miscellaneous parameters...
		setRequestParameters(aRequest);
		
		return executeAction(aMapping, aForm, aRequest, aResponse);
	}
	
	/**
	 * Hook to handle miscellaneous request parameters.
	 * @param aRequest
	 */
	private static void setRequestParameters(HttpServletRequest aRequest) {
		
		// set the "nomenu" parameter...
		NewUtil.setNoMenuAttribute(aRequest);
	}
	
	/**
	 * Placeholder to add constants to the session...
	 * @param aSession
	 */
	private void addConstants(HttpSession aSession) {
		aSession.setAttribute(AttributeKeyConstants.NUM_COURSE_HOLES, 
				util.getNumCourseHoles());
	}
	
	/**
	 * Returns the util object.
	 * @return
	 */
	public NewUtil getUtil() {
		return util;
	}
	
	/**
	 * Returns the DAO utils object.
	 * @return
	 */
	public DAOUtils getDAOUtils() {
		return BeanFactory.getDAOUtils();
	}
	
	/**
	 * Adds the player to the session if not already present.
	 * @param aRequest
	 */
	private void addPlayerToSession(HttpServletRequest aRequest) {
		
		// local declarations...
		Principal principal;
		PlayerDAO player;
		HttpSession httpSession;
		
		httpSession = aRequest.getSession();
		
		// only add the player if not already in the http session...
		if ((player = (PlayerDAO)httpSession.getAttribute(
				AttributeKeyConstants.PLAYER)) == null) {
			
			// get the principal object...
			principal = aRequest.getUserPrincipal();
		
			if (principal != null) {
				
				// load the player object...
				player = getDAOUtils().getPlayerByUserId(principal.getName());
				
				// load the player's summary...
				player.refreshSummary(getDAOUtils(), util, getLocale(aRequest));
			
				// add the player to the session...
				httpSession.setAttribute(AttributeKeyConstants.PLAYER, player);
				
				// update the last login date and save the user record...
				player.setDateOfLastLogin(new Date());
				player.save();
				
				// log...
				logger.info("Player: " + player.getEmailAddress() + 
						" added to http session");
			}
		} else {
			// log...
			logger.info("Player already in session - no action taken");
		}
	}
	
	/**
	 * Returns aDateParameter as a date object if it is valid
	 * @param aSession
	 * @param aDateParameter
	 * @return
	 */
	public Date parseDate(HttpServletRequest aRequest, String aDateParameter) {
		return parseDateParameter(aRequest, aDateParameter);
	}
	
	/**
	 * Returns aDateParameter as a date object if it is valid
	 * @param aSession
	 * @param aDateParameter
	 * @return
	 */
	public static Date parseDateParameter(HttpServletRequest aRequest, 
			String aDateParameter) {
		
		Date date;
		NewUtil util;
		HttpSession session;
		
		session = aRequest.getSession();
		date = null;
		if (StringUtils.isNotBlank(aDateParameter)) {
			util = (NewUtil)session.getAttribute(AttributeKeyConstants.UTIL);
			try {
				date = util.parse(aDateParameter);
			} catch (RuntimeException any) {
				date = null;
			}
		}
		return date;
	}

	/**
	 * Set the correct state on the menubar
	 * @param aMenubar
	 * @param aMappingAction
	 */
	private static void setMenubarState(Menubar aMenubar, 
			String aMappingAction, NewUtil aUtil) {

		List<Menu> menuList;
		List<Menuitem> menuItemList;
		boolean isMenuSet;

		isMenuSet = false;
		// init each menu and menu item to not-selected...
		menuList = aMenubar.getMenu();
		for (Menu menu : menuList) {
			menu.setSelected(false);
			if (StringUtils.contains("/" + menu.getAction(), aMappingAction)) {
				menu.setSelected(true);
				isMenuSet = true;
			}
			menuItemList = menu.getMenuitem();
			for (Menuitem menuItem : menuItemList) {
				menuItem.setSelected(false);
				if (StringUtils.contains("/" + menuItem.getAction(), aMappingAction)) {
					menuItem.setSelected(true);
					isMenuSet = true;
					menu.setSelected(true);
				}
			}
		}
		if (!isMenuSet) {
			util.setSelectedMenu(aMappingAction, aMenubar);
		}
	}
	
	
	/**
	 * Hook method implemented by subclasses
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	protected abstract ActionForward executeAction(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest, 
			HttpServletResponse aResponse)throws Exception;  
}