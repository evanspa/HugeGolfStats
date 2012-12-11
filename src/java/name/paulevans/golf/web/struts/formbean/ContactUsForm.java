package name.paulevans.golf.web.struts.formbean;

/**
 * Form bean for the contact us form
 * @author pevans
 *
 */
public class ContactUsForm extends BaseForm {
	
	private String name, fromEmail, message;
	private int messageType;
	
	/**
	 * @return the messageType
	 */
	public final int getMessageType() {
		return messageType;
	}

	/**
	 * @return the fromEmail
	 */
	public final String getFromEmail() {
		return fromEmail;
	}

	/**
	 * @param aFromEmail the fromEmail to set
	 */
	public final void setFromEmail(String aFromEmail) {
		this.fromEmail = aFromEmail;
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
	 * @param messageType the messageType to set
	 */
	public final void setMessageType(int aMessageType) {
		messageType = aMessageType;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param aName the name to set
	 */
	public final void setName(String aName) {
		name = aName;
	}

	/**
	 * public no-arg constructor
	 */
	public ContactUsForm() {
		clear();
	}
	
	/**
	 * Clears the form
	 *
	 */
	public void clear() {
	}
}