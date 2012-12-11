package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.UserRoles;

import java.io.Serializable;

import name.paulevans.golf.dao.UserRoleDAO;
import name.paulevans.golf.dao.UserRoleId;

/**
 * Hibernate userRole dao
 * @author Paul
 *
 */
public class HibernateUserRoleDAO extends HibernateDAOAdapter 
implements UserRoleDAO {

	// delegate object - generated Hibernate type...
	private UserRoles userRole;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateUserRoleDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aUserRoleDelegate
	 */
	public HibernateUserRoleDAO(UserRoles aUserRoleDelegate) {
		userRole = aUserRoleDelegate;
	}
	
	/**
	 * Sets the userRole delegate
	 */
	public void setDelegate(Object aUserRoleDelegate) {
		userRole = (UserRoles)aUserRoleDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return userRole;
	}

	/**
	 * Get the ID
	 */
	public UserRoleId getId() {
		return convert(userRole.getId());
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {		
		userRole.setId(convert((UserRoleId)aId));
	}
	
	/**
	 * Converts the hibernate UserRolesId type to dao UserRoleId type
	 * @param aId
	 * @return
	 */
	private static final UserRoleId convert(
			gen.hibernate.name.paulevans.golf.UserRolesId aId) {
		
		UserRoleId id;
		
		id = new UserRoleId();
		id.setUserId(aId.getUserId());
		id.setRoleName(aId.getRoleName());
		return id;
	}
	
	/**
	 * Converts the dao UserRoleId type to hibernate UserRolesId type
	 * @param aId
	 * @return
	 */
	private static final gen.hibernate.name.paulevans.golf.UserRolesId 
	convert(UserRoleId aId) {
		
		gen.hibernate.name.paulevans.golf.UserRolesId  id;
		
		id = new gen.hibernate.name.paulevans.golf.UserRolesId();
		id.setUserId(aId.getUserId());
		id.setRoleName(aId.getRoleName());
		return id;
	}
}
