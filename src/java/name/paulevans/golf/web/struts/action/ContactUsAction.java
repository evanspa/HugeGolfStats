package name.paulevans.golf.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.formbean.ContactUsForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes contact-us requests
 * @author pevans
 *
 */
public class ContactUsAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(ContactUsAction.class);

	/**
	 * Processes the logout request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local variables...
		ContactUsForm form;
		NotificationManager notifyMgr;
		
		// down-cast the form bean...
		form = (ContactUsForm)aForm;
		
		// get a notification manager...
		notifyMgr = BeanFactory.getNotificationManager();
		
		// send the message...
		notifyMgr.notifyContactUsMessage(getUtil(), form.getName(), 
				form.getFromEmail(), form.getMessage(), form.getMessageType(), 
				getUtil().getPlayer(aRequest), getLocale(aRequest));		
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}