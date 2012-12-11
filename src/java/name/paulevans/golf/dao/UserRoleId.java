package name.paulevans.golf.dao;

/**
 * Models a composite-key used by the UserRoleDAO
 * @author Paul
 *
 */
public class UserRoleId  {
	
	private String userId;
	private String roleName;
	
	/**
	 * Get the email address
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Set the email address
	 * @param aUserId
	 */
	public void setUserId(String aUserId) {
		userId = aUserId;
	}
	
	/**
	 * Get the role name
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Set the role name
	 * @param aRoleName
	 */
	public void setRoleName(String aRoleName) {
		roleName = aRoleName;
	}
}
