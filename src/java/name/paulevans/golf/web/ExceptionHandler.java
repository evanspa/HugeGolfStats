package name.paulevans.golf.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.util.NewUtil;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;

/**
 * Global exception handler
 * @author Paul
 *
 */
public class ExceptionHandler extends
	org.apache.struts.action.ExceptionHandler {
	
	/**
	 * Logger object...
	 */
	private static final Logger logger = Logger.getLogger(
			ExceptionHandler.class);
	
	/**
	 * Exception handler
	 */
	public ActionForward execute(Exception aException, 
			ExceptionConfig aExceptionConfig, ActionMapping aMapping, 
			ActionForm aForm, HttpServletRequest aRequest, 
			HttpServletResponse aResponse) {
		
		// local declarations...
		NotificationManager notificationMgr;
		NewUtil newUtil;
		HttpSession session;
		
		// get the session...
		session = aRequest.getSession();
		
		// get the util object...
		newUtil = BeanFactory.getUtilObject();
		
		// log the exception...
		logger.error(ExceptionUtils.getFullStackTrace(aException));
		
		// send email notification to site admin...
		notificationMgr = BeanFactory.getNotificationManager();
		notificationMgr.notifyError(newUtil, NewUtil.getPlayer(aRequest), 
				aException, (Locale)session.getAttribute(
						AttributeKeyConstants.LOCALE));
		
		// add exception message to the request...
		aRequest.setAttribute(AttributeKeyConstants.EXCEPTION_MESSAGE_PARAM, 
				aException.getLocalizedMessage());
		
		// return exception-caught forward...
		return aMapping.findForward(WebConstants.EXCEPTION_CAUGHT_FORWARD);
	}

}
