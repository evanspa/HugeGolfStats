package name.paulevans.golf.web.struts.action;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.formbean.MemberHomeForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the member home request
 * @author pevans
 *
 */
public class MemberHomeAction extends BaseAction {
	
	// logger object...
	private static Logger logger = Logger.getLogger(MemberHomeAction.class);

	/**
	 * Processes the member home request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		PlayerDAO player;
		
		player = NewUtil.getPlayer(aRequest);
		
		// process the request...
		process(aRequest, aResponse, getUtil(), getDAOUtils(), 
				(MemberHomeForm)aForm, getLocale(aRequest));
		
		// put the collection of courses played into the request...
		aRequest.getSession().setAttribute(
				AttributeKeyConstants.COURSES_PLAYED, 
				getDAOUtils().getCoursesPlayed((Integer)player.getId()));
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}

	/**
	 * Prepares the member-home screen
	 * @param aRequest
	 * @param aResponse
	 * @param aUtil
	 */
	public static final void process(HttpServletRequest aRequest, 
			HttpServletResponse aResponse, NewUtil aUtil,
			DAOUtils aDAOUtils, Locale aLocale) {	
		process(aRequest, aResponse, aUtil, aDAOUtils, null, aLocale);
	}
	
	/**
	 * Prepares the member-home screen
	 * @param aRequest
	 * @param aResponse
	 * @param aUtil
	 */
	public static final void process(HttpServletRequest aRequest, 
			HttpServletResponse aResponse, NewUtil aUtil,
			DAOUtils aDAOUtils, MemberHomeForm aForm, Locale aLocale) {
		
		Cookie userIdCookie;
		PlayerDAO player;
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// refresh the player's summary...
		player.refreshSummary(aDAOUtils, aUtil, aLocale);
		
		// set email-address cookie...
		userIdCookie = new Cookie(WebConstants.USER_ID_COOKIE_NAME, 
				player.getUserId());
		userIdCookie.setMaxAge(WebConstants.USER_ID_COOKIE_MAX_AGE);
		aResponse.addCookie(userIdCookie);
	}
	
	/*private static void printCookies(HttpServletRequest aRequest) {
		
		Cookie cookies[];
		
		cookies = aRequest.getCookies();
		System.out.printf("cookies.length: %d\n", cookies.length);
		for (int i = 0; i < cookies.length; i++) {
			System.out.printf("cookie[%d].getName(): %s", i, cookies[i].getName());
			System.out.printf(", cookie[%d].getValue(): %s\n", i, cookies[i].getValue());
		}
	}*/
}