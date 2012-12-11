package name.paulevans.golf.web.struts.action.course;

import gen.hibernate.name.paulevans.golf.Course;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.bean.CourseWrapperBean;
import name.paulevans.golf.bean.TeeWrapperBean;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.CourseForm;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Creates a course
 * @author pevans
 *
 */
public class SaveCourseAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private final static Logger logger = Logger.getLogger(
			SaveCourseAction.class);

	/**
	 * Processes the account-create request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		CourseForm form;
		CourseWrapperBean courseWrapper;
		StateProvinceDAO stateProvince;
		StateProvinceId stateProvinceId;
		CourseDAO course;
		HttpSession httpSession;
		boolean isEdit;
		String teeNameIds[];
		Set<HoleDAO> holes;
		Set<TeeWrapperBean> tees;
		CourseSlopeRatingDAO courseSlopeRatingDAOs[];
		
		// down-cast the action form...
		form = (CourseForm)aForm;	
		
		// get the HTTP session...
		httpSession = aRequest.getSession();
		
		// get course DAO from session and remove it (will exist if we're doing 
		// an 'edit' instead of an 'add'...
		course = (CourseDAO)httpSession.getAttribute(
				AttributeKeyConstants.COURSE);
		httpSession.removeAttribute(AttributeKeyConstants.COURSE);
		isEdit = course != null;
	
		// create the state province object...
		stateProvinceId = new StateProvinceId();
		stateProvinceId.setCountryId(form.getCountryId());
		stateProvinceId.setId(form.getStateProvinceId());
		stateProvince = BeanFactory.newStateProvinceDAO();
		stateProvince.setId(stateProvinceId);
		
		// create and populate the course object...
		courseWrapper = new CourseWrapperBean(isEdit ? course : 
			BeanFactory.newCourseDAO());
		
		// set the address-info of the course...
		courseWrapper.getCourse().setStateProvince(stateProvince);
		courseWrapper.getCourse().setCity(form.getCity());
		
		courseWrapper.getCourse().setDescription(form.getCourseName());
		courseWrapper.getCourse().setClubName(form.getCourseName());
		courseWrapper.getCourse().setFrontNineStartingHole(
				form.getFront9StartingHole());
		courseWrapper.getCourse().setBackNineStartingHole(
				form.getBack9StartingHole());
		
		// get the tee color ids...
		teeNameIds = SaveCourseAction.getTeeNameIds(form);
		
		// create the holes set...
		holes = createHolesSet(form, teeNameIds, courseWrapper.getCourse(), 
				isEdit);
		
		// create the tees set...
		tees = createTees(teeNameIds, form, holes, getDAOUtils());
		
		// get the course slope-rating daos...
		courseSlopeRatingDAOs = form.createCourseSlopeRatingValues();
		
		logger.info("sca.a.isEdit: " + isEdit);
		logger.info("sca.a.courseid: " + ((Course)courseWrapper.getCourse().getDelegate()).getId());
			
		// save the course, tees and holes...
		getDAOUtils().saveCourse(courseWrapper, teeNameIds, 
				holes, tees, courseSlopeRatingDAOs,
				BooleanUtils.toBoolean(form.getMakeHome()), 
				getUtil().getPlayer(aRequest));
		
		// load the tee colors and state-province so we have the labels...
		getDAOUtils().load(tees, stateProvince);
		courseWrapper.setTees(tees);
		courseWrapper.getCourse().setStateProvince(stateProvince);
			
		// add the course wrapper to the request as an attribute...
		httpSession.setAttribute(AttributeKeyConstants.COURSE, courseWrapper);
			
		// log the transaction...
		logger.info("Course saved successfully [course: " + 
				courseWrapper.getCourse().getDescription() + "]");
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
	
	/**
	 * Returns the array of tee name Ids
	 * @param aForm
	 * @return
	 */
	public static String[] getTeeNameIds(CourseForm aForm) {
		
		int numKeys;
		
		numKeys = aForm.getCourseTees().size();
		return aForm.getCourseTees().values().toArray(new String[numKeys]);
	}
	
	/**
	 * Creates and returns the set of tee objects associated with the course.
	 * @param aTeeNameIds
	 * @param aCourse
	 * @param aForm
	 * @param aSession
	 * @return
	 */
	public static Set<TeeWrapperBean> createTees(String aTeeNameIds[], 
			CourseForm aForm, Set<HoleDAO> aHoles, DAOUtils aUtil) {
		
		Set<TeeWrapperBean> tees;
		Set<HoleDAO> holes;
		int totalTeeDistance, totalTeePar, teeNameId;
		TeeWrapperBean tee;
		TeeId teeId;
		TeeNameDAO teeName;
		
		tees = new HashSet<TeeWrapperBean>();	
		for (int i = 0; i < aTeeNameIds.length; i++) {
			tee = new TeeWrapperBean(BeanFactory.newTeeDAO());
			
			// create and set the composite kee object...
			teeId = new TeeId();
			teeId.setTeeNameId(teeNameId = Integer.parseInt(aTeeNameIds[i]));
			holes = aUtil.getHoles(aHoles, teeNameId);
			
			// set the composite key object...
			tee.getTee().setId(teeId);
			
			// set the tee color
			teeName = BeanFactory.newTeeNameDAO();
			teeName.setId((Integer)teeId.getTeeNameId());
			tee.getTee().setTeeName(teeName);
			
			// set the total-distance and total-par properties...
			totalTeeDistance = 0;	
			totalTeePar = 0;
			for (HoleDAO hole : holes) {
				totalTeeDistance += hole.getYards();
				totalTeePar += hole.getPar();
				hole.setTee(tee.getTee());
			}
			tee.setTotalDistance(totalTeeDistance);
			tee.setTotalPar(totalTeePar);
			tees.add(tee);
		}
		return tees;
	}
	
	/**
	 * Constructs the set of hole objects from the form bean.
	 * @param aForm
	 * @return
	 */
	public static Set<HoleDAO> createHolesSet(CourseForm aForm, 
			String aTeeNameIds[], CourseDAO aCourse, boolean aIsEdit) {
		
		Set<HoleDAO> holes;
		TeeDAO tee;
		TeeId teeId;
		
		holes = new HashSet<HoleDAO>();

		// loop over each tee color...
		for (int i = 0; i < aTeeNameIds.length; i++) {
			teeId = new TeeId();
			teeId.setTeeNameId(Integer.parseInt(aTeeNameIds[i]));
			tee = BeanFactory.newTeeDAO();
			tee.setId(teeId);			
			
			// loop over the front and back nines...
			createHoles(holes, aCourse, aIsEdit, tee, teeId, aForm, 
					aTeeNameIds[i], aForm.getFront9StartingHole());
			createHoles(holes, aCourse, aIsEdit, tee, teeId, aForm, 
					aTeeNameIds[i], aForm.getBack9StartingHole());
		}
		return holes;
	}
	
	/**
	 * Populates the aHoles object
	 * @param aHoles
	 * @param aCourse
	 * @param aIsEdit
	 * @param aTee
	 * @param aTeeId
	 * @param aForm
	 * @param aTeeNameId
	 * @param aStartingHole
	 */
	private static void createHoles(Set<HoleDAO> aHoles, CourseDAO aCourse, 
			boolean aIsEdit, TeeDAO aTee, TeeId aTeeId, CourseForm aForm, 
			String aTeeNameId, int aStartingHole) {
		
		String key;
		int lastHole;
		HoleDAO hole, existingHole;
		
		lastHole = aStartingHole + 8;
		for (int j = aStartingHole; j <= lastHole; j++) {
			hole = BeanFactory.newHoleDAO();
			key = aTeeNameId + "-" + j;
			hole.setTee(aTee);
			hole.setNumber(j);
			hole.setPar(NumberUtils.toInt(aForm.getParValue(key)));
			hole.setYards(NumberUtils.toInt(aForm.getYardageValue(key)));
			hole.setHandicap(NumberUtils.toInt(aForm.getHandicapValue(key)));
			// set the id on the hole if we're in 'edit' mode...
			if (aIsEdit) { 
				existingHole = aCourse.getHole(aTeeId.getTeeNameId(), j);
				// would be null if the user added new tees or holes while 
				// editing the course...
				if (existingHole != null) { 
					// set the id so the 'saveOrUpdate' call does a 
					// SQL-update instead of a SQL-insert...
					hole.setId(existingHole.getId());
				}
			}
			aHoles.add(hole);
		}
	}
}
