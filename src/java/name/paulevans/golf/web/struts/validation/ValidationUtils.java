package name.paulevans.golf.web.struts.validation;

import org.apache.commons.validator.Arg;

public class ValidationUtils {

	/**
	 * Creates and returns an Arg object.
	 * @param aPosition
	 * @param aValue
	 * @return
	 */
	public static final Arg createArg(int aPosition, String aValue) {
		
		Arg arg;
		
		arg = new Arg();
		arg.setPosition(aPosition);
		arg.setResource(false);
		arg.setKey(aValue);
		return arg;
	}
}
