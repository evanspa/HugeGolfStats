package name.paulevans.golf.web.struts.action.stats;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.bean.chart.ChartCategory;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.StatisticsForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public abstract class StatsAdapterAction extends BaseAction {

	/**
	 * Prepares the approach-shot stats screen
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// locals...
		StatisticsForm form;
		PlayerDAO player;
		ChartCategory chartCategory;
		String categoryParameter;
		Locale locale;
		HttpSession session;
		
		// get the session...
		session = aRequest.getSession();
		
		// get the form...
		form = (StatisticsForm)aForm;
		
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// get the locale...
		locale = getLocale(aRequest);
		
		// prep the form...
		prepareForm(aRequest, getDAOUtils(), locale);
		
		// put the collection of courses played into the request...
		SessionUtils.addToSession(session, getDAOUtils().getCoursesPlayed((Integer)player.getId()),
				AttributeKeyConstants.COURSES_PLAYED);
		
		// get the category parameter...
		categoryParameter = aRequest.getParameter(AttributeKeyConstants.CHART_CATEGORY_PARAM);
		
		// create the appropriate chart category object...
		chartCategory = BeanFactory.getChartCategory(categoryParameter);
		chartCategory.propagateLocale(locale);
		
		// add the category object to the request as an attribute...
		session.setAttribute(AttributeKeyConstants.CHART_CATEGORY, chartCategory);
		
		// hook call to allow subclasses to take some action-specific action...
		hook(form, aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
	
	/**
	 * Meant to be overridden in subclasses
	 * @param aForm
	 */
	public abstract void hook(StatisticsForm aForm, HttpServletRequest aRequest);
	
	/**
	 * Preps the statistics form bean
	 * @param aRequest
	 * @param aDAOUtils
	 * @param aLocale
	 */
	public static void prepareForm(HttpServletRequest aRequest, 
			DAOUtils aDAOUtils, Locale aLocale) {
		
		HttpSession session;
		
		session = aRequest.getSession();
		
		// put the collection of eye-wear types into the session...
		SessionUtils.addToSession(session, aDAOUtils.getEyeWearTypes(
				aLocale), AttributeKeyConstants.EYE_WEAR_TYPES);
	
		// put the collection of head-wear types into the session...
		SessionUtils.addToSession(session, aDAOUtils.getHeadWearTypes(
				aLocale), AttributeKeyConstants.HEAD_WEAR_TYPES);
		
		// put the collection of pant-wear types into the session...
		SessionUtils.addToSession(session, aDAOUtils.getPantWearTypes(
				aLocale), AttributeKeyConstants.PANT_WEAR_TYPES);
		
		// put the collection of weather-condition types for the round into 
		// the session...
		SessionUtils.addToSession(session, 
				aDAOUtils.getWeatherConditionTypes(aLocale),
				AttributeKeyConstants.WEATHER_CONDITION_TYPES);
	}
}