package name.paulevans.golf.web.struts.validation.profile;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.struts.formbean.ProfileForm;
import name.paulevans.golf.web.struts.validation.ValidationUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/**
 * Contains various custom validation routines.
 * @author pevans
 *
 */
public class FieldChecks extends org.apache.struts.validator.FieldChecks {
	
    /**
     * Checks if the user id not a duplicate w/in the system
     *
     * @param aFormBean The bean validation is being performed on.
     * @param aValidatorAction The <code>ValidatorAction</code> that is 
     * currently being performed.
     * @param aField The <code>Field</code> object associated with the current
     * field being validated.
     * @param aErrors The <code>ActionMessages</code> object to add errors to if
     * any validation errors occur.
     * @param aValidator The <code>Validator</code> instance, used to access
     *                  other field values.
     * @param aRequest Current request object.
     * @return true if meets stated requirements, false otherwise.
     */
    public static boolean validateUserId(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        boolean isValid, isLoggedIn;
        ProfileForm formBean;
        String userId;
        DAOUtils daoUtils;
        Field copy;
        
        isLoggedIn = NewUtil.isLoggedIn(aRequest);
        formBean = (ProfileForm)aFormBean;
        userId = formBean.getUserId();
        isValid = true;
        if (!isLoggedIn) {
        	daoUtils = BeanFactory.getDAOUtils();
        	if (daoUtils.doesUserIdExist(userId)) {
        		isValid = false;
        	} 
        }
        if (!isValid) {
        	copy = (Field)aField.clone();
        	copy.addArg(ValidationUtils.createArg(0, userId));
        	aErrors.add(copy.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, copy));
        }
        return isValid;
    }
	
    /**
     * Checks if the email address is valid and not a duplicate w/in the system
     *
     * @param aFormBean The bean validation is being performed on.
     * @param aValidatorAction The <code>ValidatorAction</code> that is 
     * currently being performed.
     * @param aField The <code>Field</code> object associated with the current
     * field being validated.
     * @param aErrors The <code>ActionMessages</code> object to add errors to if
     * any validation errors occur.
     * @param aValidator The <code>Validator</code> instance, used to access
     *                  other field values.
     * @param aRequest Current request object.
     * @return true if meets stated requirements, false otherwise.
     */
    public static boolean validateEmail(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        boolean isValid, isLoggedIn;
        ProfileForm formBean;
        String email;
        DAOUtils daoUtils;
        Field copy;
        
        isLoggedIn = NewUtil.isLoggedIn(aRequest);
        formBean = (ProfileForm)aFormBean;
        email = formBean.getEmailAddress();
        isValid = true;
        if (!isLoggedIn) {
        	daoUtils = BeanFactory.getDAOUtils();
        	if (daoUtils.doesEmailAddressExist(email)) {
        		isValid = false;
        	} 
        }
        if (!isValid) {
        	copy = (Field)aField.clone();
        	copy.addArg(ValidationUtils.createArg(0, email));
        	aErrors.add(copy.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, copy));
        }
        return isValid;
    }
	
    /**
     * Checks if the password / password-confirm values are valid
     *
     * @param aFormBean The bean validation is being performed on.
     * @param aValidatorAction The <code>ValidatorAction</code> that is 
     * currently being performed.
     * @param aField The <code>Field</code> object associated with the current
     * field being validated.
     * @param aErrors The <code>ActionMessages</code> object to add errors to if
     * any validation errors occur.
     * @param aValidator The <code>Validator</code> instance, used to access
     *                  other field values.
     * @param aRequest Current request object.
     * @return true if meets stated requirements, false otherwise.
     */
    public static boolean validatePassword(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        boolean isValid;
        ProfileForm formBean;
        String password, passwordConfirm;
        boolean isLoggedIn;
        
        formBean = (ProfileForm)aFormBean;
        password = formBean.getPassword();
        passwordConfirm = formBean.getPasswordConfirm();
        isLoggedIn = NewUtil.isLoggedIn(aRequest);
        isValid = false;
        if (isLoggedIn) {
        	if (StringUtils.isNotBlank(password) ||
        			StringUtils.isNotBlank(passwordConfirm)) {
        		isValid = StringUtils.equals(password, passwordConfirm);
        	} else {
        		isValid = true;
        	}
        } else {
        	if (StringUtils.isNotBlank(password)) {
        		isValid = StringUtils.equals(password, passwordConfirm);
        	} 
        }
        if (!isValid) {
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
        }
        return isValid;
    }
}
