package name.paulevans.golf.web.struts.formbean;

import java.util.Locale;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.WeatherConditionDAO;
import name.paulevans.golf.util.NewUtil;


/**
 * Statistics form bean.
 * @author pevans
 *
 */
public class StatisticsForm extends BaseForm {
	
	// instance members...
	private String fromDate;
	private String toDate;
	private String chartClass, chartName;
	private EyeWearDAO eyeWearDAO;
	private HeadWearDAO headWearDAO;
	private PantWearDAO pantWearDAO;
	private WeatherConditionDAO weatherConditionDAO;
	private Integer tournamentTypeId;
	private Integer locomotionTypeId;
	private Integer bagCarryingMechanismId;
	private CourseDAO coursePlayed;
	private LabelStringFactory strFactory;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public StatisticsForm() {
		eyeWearDAO = BeanFactory.newEyeWearDAO();
		headWearDAO = BeanFactory.newHeadWearDAO();
		pantWearDAO = BeanFactory.newPantWearDAO();
		weatherConditionDAO = BeanFactory.newWeatherConditionDAO();	
		coursePlayed = BeanFactory.newCourseDAO();
		setEyeWearTypeId(Constants.ALL_OPTION_VAL);
		setHeadWearTypeId(Constants.ALL_OPTION_VAL);
		setPantWearTypeId(Constants.ALL_OPTION_VAL);
		setWeatherConditionTypeId(Constants.ALL_OPTION_VAL);
		setTournamentTypeId(Constants.ALL_OPTION_VAL);
		setLocomotionTypeId(Constants.ALL_OPTION_VAL);
		setBagCarryingMechanismId(Constants.ALL_OPTION_VAL);
		setCoursePlayedId(Constants.ALL_OPTION_VAL);
	}
	
	/**
	 * Loads the descriptions associated with the daos
	 * @param aDAOUtils
	 * @param aLocale
	 */
	public void loadDescriptions(DAOUtils aDAOUtils, Locale aLocale) {
		aDAOUtils.loadObjects(
			NewUtil.equal((Integer)eyeWearDAO.getId(), Constants.ALL_OPTION_VAL) ? null : eyeWearDAO, 
			NewUtil.equal((Integer)headWearDAO.getId(), Constants.ALL_OPTION_VAL) ? null : headWearDAO,
			NewUtil.equal((Integer)pantWearDAO.getId(), Constants.ALL_OPTION_VAL) ? null : pantWearDAO,
			NewUtil.equal((Integer)weatherConditionDAO.getId(), Constants.ALL_OPTION_VAL) ? null : weatherConditionDAO,
			NewUtil.equal((Integer)coursePlayed.getId(), Constants.ALL_OPTION_VAL) ? null : coursePlayed					
		);
		strFactory = LabelStringFactory.getInstance(aLocale);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public int getCoursePlayedId() {
		return (Integer)coursePlayed.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getCoursePlayedDescription() {
		return NewUtil.equal((Integer)coursePlayed.getId(), Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : coursePlayed.getDescription();
	}
	
	/**
	 * Setter
	 * @param aCourseId
	 */
	public void setCoursePlayedId(int aCourseId) {
		coursePlayed.setId(aCourseId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public int getBagCarryingMechanismId() {
		return bagCarryingMechanismId;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getBagCarryingMechanismDescription() {
		return NewUtil.equal(bagCarryingMechanismId, Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : (strFactory.getString(
					NewUtil.equal(bagCarryingMechanismId, Constants.TRUE_INTEGER) ? 
							Constants.OPTION_TRUE_LBL_KEY : 
							Constants.OPTION_FALSE_LBL_KEY));
	}

	/**
	 * Setter
	 * @param aUsedCaddie
	 */
	public void setBagCarryingMechanismId(int aUsedCaddie) {
		bagCarryingMechanismId = aUsedCaddie;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getLocomotionTypeId() {
		return locomotionTypeId;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getLocomotionTypeDescription() {
		return NewUtil.equal(locomotionTypeId, Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : (strFactory.getString(
					NewUtil.equal(locomotionTypeId, Constants.TRUE_INTEGER) ? 
							Constants.OPTION_CART_LBL_KEY : 
							Constants.OPTION_WALK_LBL_KEY));
	}

	/**
	 * Setter
	 * @param aUsedCart
	 */
	public void setLocomotionTypeId(int aUsedCart) {
		locomotionTypeId = aUsedCart;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getPantWearTypeId() {
		return (Integer)pantWearDAO.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getPantWearTypeDescription() {
		return NewUtil.equal((Integer)pantWearDAO.getId(), Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : pantWearDAO.getDescription();
	}	

	/**
	 * Setter
	 * @param aPantWearTypeId
	 */
	public final void setPantWearTypeId(int aPantWearTypeId) {
		pantWearDAO.setId(aPantWearTypeId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getEyeWearTypeId() {
		return (Integer)eyeWearDAO.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getEyeWearTypeDescription() {
		return NewUtil.equal((Integer)eyeWearDAO.getId(), Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : eyeWearDAO.getDescription();
	}

	/**
	 * Setter
	 * @param aEyeWearTypeId
	 */
	public final void setEyeWearTypeId(int aEyeWearTypeId) {
		eyeWearDAO.setId(aEyeWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getHeadWearTypeId() {
		return (Integer)headWearDAO.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getHeadWearTypeDescription() {
		return NewUtil.equal((Integer)headWearDAO.getId(), Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : headWearDAO.getDescription();
	}

	/**
	 * Setter
	 * @param aHeadWearTypeId
	 */
	public final void setHeadWearTypeId(int aHeadWearTypeId) {
		headWearDAO.setId(aHeadWearTypeId);
	}
	
	/**
	 * Setter
	 * @param aTourneyTypeId
	 */
	public final void setTournamentTypeId(int aTourneyTypeId) {
		this.tournamentTypeId = aTourneyTypeId;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getTournamentTypeId() {
		return tournamentTypeId;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getTournamentTypeDescription() {
		return NewUtil.equal(tournamentTypeId, Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : (strFactory.getString(
					NewUtil.equal(tournamentTypeId, Constants.TRUE_INTEGER) ? 
							Constants.OPTION_TRUE_LBL_KEY : 
							Constants.OPTION_FALSE_LBL_KEY));
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getWeatherConditionTypeId() {
		return (Integer)weatherConditionDAO.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getWeatherConditionTypeDescription() {
		return NewUtil.equal((Integer)weatherConditionDAO.getId(), Constants.ALL_OPTION_VAL) ? 
				strFactory.getString(Constants.ALL_OPTION_LBL_KEY) : weatherConditionDAO.getDescription();
	}

	/**
	 * Setter
	 * @param aWeatherConditionTypeId
	 */
	public final void setWeatherConditionTypeId(int aWeatherConditionTypeId) {
		weatherConditionDAO.setId(aWeatherConditionTypeId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getChartDescription() {
		return strFactory.getString(chartClass);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getFromDate() {
		return fromDate;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getFromDateDescription() {
		return fromDate;
	}
	
	/**
	 * Setter
	 * @param aFromDate
	 */
	public final void setFromDate(String aFromDate) {
		this.fromDate = aFromDate;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getToDate() {
		return toDate;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getToDateDescription() {
		return toDate;
	}
	
	/**
	 * Setter
	 * @param aToDate
	 */
	public final void setToDate(String aToDate) {
		this.toDate = aToDate;
	}

	/**
	 * @return the chartClass
	 */
	public final String getChartClass() {
		return chartClass;
	}

	/**
	 * @param aChartLabelKey the chartClass to set
	 */
	public final void setChartClass(String aChartLabelKey) {
		chartClass = aChartLabelKey;
	}

	/**
	 * @return the chartName
	 */
	public final String getChartName() {
		return chartName;
	}

	/**
	 * @param aChartName the chartName to set
	 */
	public final void setChartName(String aChartName) {
		chartName = aChartName;
	}
}
