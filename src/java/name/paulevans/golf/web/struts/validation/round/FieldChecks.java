package name.paulevans.golf.web.struts.validation.round;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/**
 * Contains various custom validation routines.
 * @author pevans
 *
 */
public class FieldChecks extends org.apache.struts.validator.FieldChecks {
	
//	 logger...
	private static final Logger logger = Logger.getLogger(FieldChecks.class);
	
    /**
     * Checks if any nine-hole rounds are checked
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
    public static boolean validateHolesSelected(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        boolean isValid;
        RoundForm formBean;
        int numStartingHoles, numCourses;
        
        isValid = true;
        formBean = (RoundForm)aFormBean;
        numStartingHoles = formBean.getStartingHoles().length;
        numCourses = ((CourseDAO[])aRequest.getSession().getAttribute(
        		AttributeKeyConstants.COURSES)).length;
        
        if (numCourses == 0) {
        	aField.setKey("round.errors.courserequired");
        	aValidatorAction.setMsg("round.errors.courserequired");
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
            isValid = false;
        } else if (numStartingHoles == 0) {
        	aField.setKey("round.errors.holesrequired");
        	aValidatorAction.setMsg("round.errors.holesrequired");
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
            isValid = false;
        } else if (numStartingHoles > 2) {
        	aField.setKey("round.errors.nomorethantwo");
        	aValidatorAction.setMsg("round.errors.nomorethantwo");
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
            isValid = false;
        }
        return isValid;
    }
    
    /**
     * Checks that the score has been inputted
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
    public static boolean validateOverallScore(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {
    	
    	RoundForm form;
    	boolean isValid;
    	
    	isValid = true;
    	form = (RoundForm)aFormBean;
    	if (StringUtils.equals(aRequest.getParameter(WebConstants.MODE), 
    			WebConstants.INPUT_OVERALL_SCORE)) {
    		if (StringUtils.isBlank(form.getOverallScore())) {
    			aErrors.add(aField.getKey(), Resources.getActionMessage(
    					aValidator, aRequest, aValidatorAction, aField));
    			isValid = false;
    		}
    	}
    	return isValid;
    }
}
