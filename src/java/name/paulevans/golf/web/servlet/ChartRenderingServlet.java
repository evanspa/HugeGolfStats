package name.paulevans.golf.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartUtils;
import name.paulevans.golf.bean.chart.ChartWrapper;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.struts.action.BaseAction;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 * Handles requests to render charts
 * @author pevans
 *
 */
public class ChartRenderingServlet extends HttpServlet {
	
	public static final String CHART_CLASS_PARAM = "chart_class";
	public static final String CHART_ID_PARAM = "chart_id";
	public static final String WIDTH_PARAM = "width";
	public static final String HEIGHT_PARAM = "height";
	public static final String FROM_DATE_PARAM = "from_date";
	public static final String TO_DATE_PARAM = "to_date";
	public static final String PANT_WEAR_TYPE_ID = "pant_wear_type_id";
	public static final String EYE_WEAR_TYPE_ID = "eye_wear_type_id";
	public static final String HEAD_WEAR_TYPE_ID = "head_wear_type_id";
	public static final String WEATHER_TYPE_ID = "weather_type_id";
	public static final String TOURNAMENT_TYPE_ID = "tournament_type_id";
	public static final String LOCOMOTION_TYPE_ID = "locomotion_type_id";
	public static final String BAG_CARRYING_MECHANISM_ID = "bag_carrying_mechanism_id";
	public static final String COURSE_PLAYED_ID = "course_id";
	
	/**
	 * Handles GET requests
	 */
	public void doGet(HttpServletRequest aRequest, 
			HttpServletResponse aResponse) throws IOException {
		
		// locals...
		OutputStream out;
		JFreeChart chart;
		PlayerDAO player;
		HttpSession session;
		CircumstancesBean circumstances;
		Locale locale;
		
		// get the output stream and session...
		out = aResponse.getOutputStream();
		session = aRequest.getSession();
		
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// create the circumstances bean...
		circumstances = getCircumstances(aRequest);
		
		// get the locale object...
		locale = (Locale)session.getAttribute(AttributeKeyConstants.LOCALE);
		
		// create the chart...
		chart = getChart(
				BeanFactory.getDAOUtils(),
				player, 
				BaseAction.parseDateParameter(aRequest, 
						aRequest.getParameter(FROM_DATE_PARAM)),
				BaseAction.parseDateParameter(aRequest, 
						aRequest.getParameter(TO_DATE_PARAM)),
				circumstances,
				aRequest.getParameter(CHART_CLASS_PARAM),
				ChartUtils.getInstance(), locale);
		
		// write the chart to the output stream...
		ChartUtilities.writeChartAsPNG(out, chart, 
				Integer.parseInt(aRequest.getParameter(WIDTH_PARAM)),
				Integer.parseInt(aRequest.getParameter(HEIGHT_PARAM)));
	}
	
	/**
	 * Creates a circumstances bean from the request parameters.
	 * @param aRequest
	 * @return
	 */
	private CircumstancesBean getCircumstances(HttpServletRequest aRequest) {
		
		CircumstancesBean circumstances;
		
		circumstances = new CircumstancesBean();
		circumstances.setBagCarryingMechanismTypeId(Integer.parseInt(
				aRequest.getParameter(BAG_CARRYING_MECHANISM_ID)));
		circumstances.setEyeWearId(Integer.parseInt(
				aRequest.getParameter(EYE_WEAR_TYPE_ID)));
		circumstances.setHeadWearId(Integer.parseInt(
				aRequest.getParameter(HEAD_WEAR_TYPE_ID)));
		circumstances.setLocomotionTypeId(Integer.parseInt(
				aRequest.getParameter(LOCOMOTION_TYPE_ID)));
		circumstances.setPantWearId(Integer.parseInt(
				aRequest.getParameter(PANT_WEAR_TYPE_ID)));
		circumstances.setTournamentTypeId(Integer.parseInt(
				aRequest.getParameter(TOURNAMENT_TYPE_ID)));
		circumstances.setWeatherConditionId(Integer.parseInt(
				aRequest.getParameter(WEATHER_TYPE_ID)));
		circumstances.setCoursePlayedId(Integer.parseInt(
				aRequest.getParameter(COURSE_PLAYED_ID)));
		return circumstances;
	}
	
	/**
	 * Returns the chart based on the inputted mode
	 * @param aChartId
	 * @return
	 */
	private static JFreeChart getChart(DAOUtils aDAOUtils, PlayerDAO aPlayer, 
			Date aFromDate, Date aToDate, CircumstancesBean aCircumstances, 
			String aChartClass, ChartUtils aChartUtils, Locale aLocale) {
	
		ChartWrapper chart;
		
		try {
			chart  = (ChartWrapper)Class.forName(aChartClass).newInstance();
			chart.setLocale(aLocale);
			return chart.getChart(aFromDate, aToDate, aCircumstances, aDAOUtils, aPlayer);
		} catch (Throwable any) {
			throw new RuntimeException(any);
		}
	}
}
