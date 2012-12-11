package name.paulevans.golf.web.struts.formbean;

/**
 * Form bean for the contact us form
 * @author pevans
 *
 */
public class MessageBroadcastForm extends BaseForm {
	
	private String subject, message;

	/**
	 * @return the subject
	 */
	public final String getSubject() {
		return subject;
	}

	/**
	 * @param aSubject the subject to set
	 */
	public final void setSubject(String aSubject) {
		this.subject = aSubject;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @param aMessage the message to set
	 */
	public final void setMessage(String aMessage) {
		this.message = aMessage;
	}

	/**
	 * public no-arg constructor
	 */
	public MessageBroadcastForm() {
		clear();
	}
	
	/**
	 * Clears the form
	 *
	 */
	public void clear() {
	}
}