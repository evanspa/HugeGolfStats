package name.paulevans.golf.web.struts.formbean;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CountryDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;

import org.apache.log4j.Logger;

/**
 * Models the team form
 * @author Paul
 *
 */
public class TeamForm extends BaseForm {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(TeamForm.class);
	
	private String teamName;
	private String teamDescription;
	private String activationDate;
	private String terminationDate;
	private String password;
	private StateProvinceDAO stateProvince;
	private DAOUtils daoUtils;
	
	/**
	 * public constructor
	 */
	public TeamForm() {
		stateProvince = BeanFactory.newStateProvinceDAO();
		daoUtils = BeanFactory.getDAOUtils();
	}
	
	/**
	 * Loads the DAO objects so the descriptions are available
	 *
	 */
	public void loadDescriptions() {
		daoUtils.loadObjects(stateProvince);
	}	
	
	/**
	 * Getter
	 * @return
	 */
	public final int getCountryId() {
		return (Integer)stateProvince.getCountry().getId();
	}
	
	/**
	 * Setter
	 * @param aCountryId
	 */
	public final void setCountryId(int aCountryId) {
		StateProvinceId stateProvinceId;
		CountryDAO country;
		
		country = stateProvince.getCountry();
		country.setId(aCountryId);
		stateProvince.setCountry(country);
		stateProvinceId = (StateProvinceId)stateProvince.getId();
		stateProvinceId.setCountryId(aCountryId);
		stateProvince.setId(stateProvinceId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getStateProvinceId() {
		return (Integer)((StateProvinceId)stateProvince.getId()).getId();
	}
	
	/**
	 * Setter
	 * @param aStateProvinceId
	 */
	public final void setStateProvinceId(int aStateProvinceId) {
		
		StateProvinceId stateProvinceId;
		
		stateProvinceId = (StateProvinceId)stateProvince.getId();
		stateProvinceId.setId((Integer)aStateProvinceId);
		stateProvince.setId(stateProvinceId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getCountryDescription() {
		return stateProvince.getCountry().getName();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getStateProvinceDescription() {
		return stateProvince.getName();
	}
	
	/**
	 * @return the teamName
	 */
	public final String getTeamName() {
		return teamName;
	}

	/**
	 * @param aTeamName the teamName to set
	 */
	public final void setTeamName(String aTeamName) {
		this.teamName = aTeamName;
	}

	/**
	 * @return the teamDescription
	 */
	public final String getTeamDescription() {
		return teamDescription;
	}

	/**
	 * @param aTeamDescription the teamDescription to set
	 */
	public final void setTeamDescription(String aTeamDescription) {
		this.teamDescription = aTeamDescription;
	}

	/**
	 * @return the activationDate
	 */
	public final String getActivationDate() {
		return activationDate;
	}

	/**
	 * @param aActivationDate the activationDate to set
	 */
	public final void setActivationDate(String aActivationDate) {
		this.activationDate = aActivationDate;
	}

	/**
	 * @return the terminationDate
	 */
	public final String getTerminationDate() {
		return terminationDate;
	}

	/**
	 * @param aTerminationDate the terminationDate to set
	 */
	public final void setTerminationDate(String aTerminationDate) {
		this.terminationDate = aTerminationDate;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param aPassword the password to set
	 */
	public final void setPassword(String aPassword) {
		this.password = aPassword;
	}
}