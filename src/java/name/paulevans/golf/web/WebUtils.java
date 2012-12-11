package name.paulevans.golf.web;

import javax.servlet.http.HttpServletRequest;


/**
 * Contains various web-related utility-functions
 * @author pevans
 *
 */
public class WebUtils {
	
	// secure https prefix-string...
	private static final String SECURE_PROTOCOL = "http://";
	
	// secure https prefix-string...
	private static final String NON_SECURE_PROTOCOL = "http://";
	
	/**
	 * private constructor
	 */
	private WebUtils() {
		// does nothing...
	}
	
	/**
	 * Returns the http://CTX_ROOT prefix string
	 * @return
	 */
	public static final String httpPrefix(HttpServletRequest aRequest) {
		return NON_SECURE_PROTOCOL + aRequest.getServerName() + WebConstants.CONTEXT_ROOT;
	}
	
	/**
	 * Returns the https://CTX_ROOT prefix string
	 * @return
	 */
	public static final String securePrefix(HttpServletRequest aRequest) {
		return SECURE_PROTOCOL + aRequest.getServerName() + WebConstants.CONTEXT_ROOT;
	}
}
