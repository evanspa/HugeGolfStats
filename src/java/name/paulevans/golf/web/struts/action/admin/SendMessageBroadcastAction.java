package name.paulevans.golf.web.struts.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.MessageBroadcastForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes contact-us requests
 * @author pevans
 *
 */
public class SendMessageBroadcastAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(SendMessageBroadcastAction.class);

	/**
	 * Processes the logout request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local variables...
		MessageBroadcastForm form;
		NotificationManager notifyMgr;
		DAOUtils daoUtils;
		String emailAddresses[];
		NewUtil util;
		
		// down-cast the form bean...
		form = (MessageBroadcastForm)aForm;
		
		// get a notification manager...
		notifyMgr = BeanFactory.getNotificationManager();
		
		// get the util object...
		util = getUtil();
		
		// get the DAO utils object...
		daoUtils = getDAOUtils();
		
		// get all the unique email addresses in the system...
		emailAddresses = daoUtils.getAllUniqueEmailAddresses();
		
		// send notifcation email to each address...
		for (String emailAddr : emailAddresses) {
			notifyMgr.notify(util, emailAddr, form.getSubject(), 
					form.getMessage());
		}
		
		// add the number of emails sent to the request...
		aRequest.setAttribute("NumEmailsSent", emailAddresses.length);
		
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}