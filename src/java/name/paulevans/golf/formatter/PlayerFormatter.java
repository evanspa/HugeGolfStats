package name.paulevans.golf.formatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;

/**
 * Used to return human-readable and nicely formatted strings of data from a 
 * player DAO
 * @author pevans
 *
 */
public class PlayerFormatter {
	
	private PlayerDAO player;
	private SimpleDateFormat dtTimeFormatter;
	private SimpleDateFormat dtFormatter;
	
	/**
	 * public constructor
	 * @param aPlayerDAO
	 */
	public PlayerFormatter(PlayerDAO aPlayerDAO, 
			SimpleDateFormat aDtFormatter,
			SimpleDateFormat aDtTimeFormatter) {
		player = aPlayerDAO;
		dtFormatter = aDtFormatter;
		dtTimeFormatter = aDtTimeFormatter;
	}
	
	/**
	 * Returns the formatted object
	 * @return
	 */
	public PlayerDAO getPlayer() {
		return player;
	}
	
	/**
	 * Returns the user id
	 * @return
	 */
	public String getUserId() {
		return player.getUserId();
	}
	
	/**
	 * Returns the last update date as a formatted string
	 * @return
	 */
	public String getLastUpdateDate() {
		
		Date lastUpdateDate;
		
		lastUpdateDate = player.getLastUpdateDate();
		return lastUpdateDate != null ? dtTimeFormatter.format(lastUpdateDate) : "";
	}
	
	/**
	 * Returns the date created as a formatted string
	 * @return
	 */
	public String getDateCreated() {
		
		Date dateCreated;
		
		dateCreated = player.getDateCreated();
		return dateCreated != null ? dtTimeFormatter.format(dateCreated) : "";
	}
	
	/**
	 * Returns the date of last login as a formatted string
	 * @return
	 */
	public String getDateOfLastLogin() {
		
		Date lastLogin;
		
		lastLogin = player.getDateOfLastLogin();
		return lastLogin != null ? dtTimeFormatter.format(lastLogin) : "";
	}
	
	/**
	 * Returns the number of rounds as a string
	 * @return
	 */
	public String getNumRounds() {
		return Integer.toString(player.getNumRounds());
	}
	
	/**
	 * Returns the postal code
	 * @return
	 */
	public String getPostalCode() {
		return player.getPostalCode();
	}

	/**
	 * Returns the ID as a string
	 * @return
	 */
	public String getId() {
		return player.getId().toString();
	}
	
	/**
	 * Returns the last name
	 * @return
	 */
	public String getLastName() {
		return player.getLastName();
	}
	
	/**
	 * Returns the first name
	 * @return
	 */
	public String getFirstName() {
		return player.getFirstName();
	}
	
	/**
	 * Returns an array of formatters given an array of DAOs
	 * @param aPlayers
	 * @param aUtil
	 * @return
	 */
	public static PlayerFormatter[] convert(PlayerDAO aPlayers[], 
			NewUtil aUtil) {
		
		List<PlayerFormatter> formatters;
		
		formatters = new ArrayList<PlayerFormatter>();
		for (PlayerDAO player : aPlayers) {
			formatters.add(new PlayerFormatter(player, 
					aUtil.getDateFormatDisplay(),
					aUtil.getDatetimeFormatDisplay()));
		}
		return (PlayerFormatter[])formatters.toArray(
				new PlayerFormatter[formatters.size()]);
	}
}
