package name.paulevans.golf.web;

import javax.servlet.http.HttpSession;


/**
 * Class used to add objects to the http session.
 * @author Paul
 *
 */
public class SessionUtils {
	
	/**
	 * Private no-arg constructor
	 *
	 */
	private SessionUtils() {
		// does nothing...
	}
	
	/**
	 * Returns true if aSession contains a non-null attribute with key aKey.
	 * @param aSession
	 * @param aKey
	 * @return
	 */
	private static boolean isInSession(HttpSession aSession, String aKey) {
		return aSession.getAttribute(aKey) != null;
	}
	
	/**
	 * Adds aObject to aSession only if it doesn't already exist.
	 * @param aObject
	 * @param aTypes
	 * @param aKey
	 */
	public static void addToSession(HttpSession aSession, Object aObject,
			String aKey) {
		
		if (!isInSession(aSession, aKey)) {
			aSession.setAttribute(aKey, aObject);
		}
	}

}
