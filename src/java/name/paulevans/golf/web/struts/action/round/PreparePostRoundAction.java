package name.paulevans.golf.web.struts.action.round;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PreparePostRoundAction extends BaseAction {
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(PreparePostRoundAction.class);

	/**
	 * Empty implementation.
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		RoundForm form;
		HttpSession httpSession;
		CourseDAO courses[];
		
		// downcast the form object and 'reset' it only if this request wasn't
		// a result of the user choosing a different course/state/etc from a
		// drop-down on the initial input screen...
		form = (RoundForm)aForm;
		form.initStructures();
		
		// get the session...
		httpSession = aRequest.getSession();
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// initialize the form bean...
		form.init(getUtil(), aRequest);
		
		// prepare the form...
		courses = prepareForm(aRequest.getSession(), getDAOUtils(), getUtil(), 
				form, aRequest, null, getLocale(aRequest));
		
		// prepare the holes-played info...
		prepareHolesPlayed(httpSession, getDAOUtils(), getUtil(), form, aRequest, 
				null, courses);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
	
	/**
	 * Prepares the round form for posting/editing a round
	 * @param aHttpSession
	 * @param aDAOUtils
	 * @param aUtil
	 * @param aForm
	 * @param aRequest
	 */
	public static CourseDAO[] prepareForm(HttpSession aHttpSession, DAOUtils aDAOUtils,
			NewUtil aUtil, RoundForm aForm, HttpServletRequest aRequest,
			ScorecardDAO aScorecard, Locale aLocale) {
		
		CourseDAO courses[];
		
		// put the collection of countries into the session...
		SessionUtils.addToSession(aHttpSession, aDAOUtils.getCountries(),
				AttributeKeyConstants.COUNTRIES);
		
		// put the collection state-provinces into the session...
		aHttpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				aDAOUtils.getStateProvinces(aForm.getCountryId()));
		
		// put the collection of courses into the session...
		aHttpSession.setAttribute(AttributeKeyConstants.COURSES, 
				courses = aDAOUtils.getCourses(aForm.getCountryId(), 
						aForm.getStateProvinceId()));
		
		// put the collection of eye-wear types into the session...
		SessionUtils.addToSession(aHttpSession, aDAOUtils.getEyeWearTypes(
				aLocale), AttributeKeyConstants.EYE_WEAR_TYPES);
	
		// put the collection of head-wear types into the session...
		SessionUtils.addToSession(aHttpSession, aDAOUtils.getHeadWearTypes(
				aLocale), AttributeKeyConstants.HEAD_WEAR_TYPES);
		
		// put the collection of pant-wear types into the session...
		SessionUtils.addToSession(aHttpSession, aDAOUtils.getPantWearTypes(
				aLocale), AttributeKeyConstants.PANT_WEAR_TYPES);
		
		// put the collection of weather-condition types for the round into 
		// the session...
		SessionUtils.addToSession(aHttpSession, 
				aDAOUtils.getWeatherConditionTypes(aLocale),
				AttributeKeyConstants.WEATHER_CONDITION_TYPES);
		return courses;
	}
	
	/**
	 * Prepares the holes-played information associated with the form
	 * @param aHttpSession
	 * @param aDAOUtils
	 * @param aUtil
	 * @param aForm
	 * @param aRequest
	 * @param aScorecard
	 * @param courses
	 */
	public static void prepareHolesPlayed(HttpSession aHttpSession, 
			DAOUtils aDAOUtils, NewUtil aUtil, RoundForm aForm, 
			HttpServletRequest aRequest, ScorecardDAO aScorecard, 
			CourseDAO courses[]) {
		
		CourseDAO course;
		int numHoles, front9StartingHole, back9StartingHole;
		Set<TeeDAO> tees;
		
		course = BeanFactory.newCourseDAO();
		tees = new HashSet<TeeDAO>();

		// put the collection of course tees (or lack-of) and number of holes 
		// into the session...
		if (aForm.getCourseId() == -1) {
			numHoles = 0;
			front9StartingHole = Constants.NULL_ID_VAL;
			back9StartingHole = Constants.NULL_ID_VAL;
			if (courses.length > 0) { // the user probably picked a 
				// country/state-province that contained
				// no courses, added a course and then hit the 'refresh' link...
				course.setId((Integer)courses[0].getId());
				course.loadTees();
				tees = course.getTees(); 
				numHoles = aDAOUtils.getNumHoles((Integer)courses[0].getId());
				front9StartingHole = course.getFrontNineStartingHole();
				back9StartingHole = course.getBackNineStartingHole();
				setHolesPlayed(aForm, aScorecard, front9StartingHole,
						back9StartingHole);
			}
		} else if (courses.length == 0) {
			numHoles = 0;
			front9StartingHole = Constants.NULL_ID_VAL;
			back9StartingHole = Constants.NULL_ID_VAL;
		} else if (!aDAOUtils.inCollection(aForm.getCourseId(), courses)) {
			aForm.setCourseId((Integer)courses[0].getId());
			course.setId((Integer)courses[0].getId());
			course.loadTees();
			tees = course.getTees(); 
			numHoles = aDAOUtils.getNumHoles(aForm.getCourseId());
			front9StartingHole = course.getFrontNineStartingHole();
			back9StartingHole = course.getBackNineStartingHole();
			setHolesPlayed(aForm, aScorecard, front9StartingHole,
					back9StartingHole);
		} else {
			course.setId(aForm.getCourseId());
			course.loadTees();
			tees = course.getTees();
			numHoles = aDAOUtils.getNumHoles(aForm.getCourseId());
			front9StartingHole = course.getFrontNineStartingHole();
			back9StartingHole = course.getBackNineStartingHole(); 
			setHolesPlayed(aForm, aScorecard, front9StartingHole,
					back9StartingHole);
		}
		aHttpSession.setAttribute(AttributeKeyConstants.COURSE_TEES, tees);
		aForm.setNumAvailableHoles(numHoles);
		aForm.setFront9StartingHole(front9StartingHole);
		aForm.setBack9StartingHole(back9StartingHole);
	}
	
	/**
	 * Initializes the form's holes-played data structure 
	 * @param aForm
	 * @param aScorecard
	 * @param front9StartingHole
	 * @param back9StartingHole
	 */
	private static void setHolesPlayed(RoundForm aForm, ScorecardDAO aScorecard,
			int front9StartingHole, int back9StartingHole) {
	
		Set<ScoreDAO> scores;
		
		if (aScorecard != null) {
			scores = aScorecard.getScores();
			if (scores.size() > 9) {
				aForm.setHolesPlayed(front9StartingHole + 
						WebConstants.DELIMETER + (front9StartingHole + 8), 
						Constants.TRUE);
				aForm.setHolesPlayed(back9StartingHole + 
						WebConstants.DELIMETER + (back9StartingHole + 8), 
						Constants.TRUE);
			} else {
				aForm.setHolesPlayed(
						aScorecard.getStartingHole().getNumber() + 
						WebConstants.DELIMETER + 
						(aScorecard.getStartingHole().getNumber() + 8), 
						Constants.TRUE);
			}
		}
	}
	
	/**
	 * Returns an empty Tee array.
	 * @return
	 */
	private static TeeDAO[] emptyTeeCollection() {
		return new TeeDAO[0]; 
	}
	
	/**
	 * Returns true if aCourseId is found within the collection of aCourses.
	 * @param aCourseId
	 * @param aCourses
	 * @return
	 */
	private static boolean isCourseIdInCollection(int aCourseId, 
			CourseDAO aCourses[]) {
		
		boolean isInCollection;
		int loop;
		
		isInCollection = false;
		for (loop = 0; loop < aCourses.length; loop++) {
			if (((Integer)aCourses[loop].getId()) == aCourseId) {
				isInCollection = true;
				break;
			}
		}
		return isInCollection;
	}
}