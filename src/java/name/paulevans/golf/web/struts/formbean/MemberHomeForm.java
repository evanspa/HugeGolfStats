package name.paulevans.golf.web.struts.formbean;

import org.apache.log4j.Logger;

public class MemberHomeForm extends BaseForm {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(MemberHomeForm.class);
	
	// player profile info...
	private String courseId;
	
	/**
	 * public no-arg constructor
	 */
	public MemberHomeForm() {
		clear();
	}
	
	/**
	 * Clears the form
	 *
	 */
	public void clear() {
		
		// initializations...
		courseId = null;
	}
	
	/**
	 * Callback to reset checkbox-backed properties
	 *
	 */
	public void reset() {
	}

	/**
	 * @return the courseId
	 */
	public final String getCourseId() {
		return courseId;
	}

	/**
	 * @param aCourseId the courseId to set
	 */
	public final void setCourseId(String aCourseId) {
		courseId = aCourseId;
	}
}
