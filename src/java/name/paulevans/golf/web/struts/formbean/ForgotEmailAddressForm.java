package name.paulevans.golf.web.struts.formbean;


/**
 * Models the forgot-email address form
 * @author Paul
 *
 */
public class ForgotEmailAddressForm extends BaseForm {
	
	private String firstName;
	private String lastName;
	
	/**
	 * Getter
	 * @return
	 */
	public final String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter
	 * @param firstName
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter
	 * @param lastName
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}