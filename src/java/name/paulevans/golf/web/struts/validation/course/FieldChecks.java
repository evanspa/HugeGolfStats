package name.paulevans.golf.web.struts.validation.course;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.Constants;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.formbean.CourseForm;
import name.paulevans.golf.web.struts.validation.ValidationUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
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
	
	// logger object...
	private static Logger logger = Logger.getLogger(FieldChecks.class);
	
	/**
     * Validates that the course yardage values are valid
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
    public static boolean validateCourseYardages(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        String value, key;
        Integer yardageValue;
        boolean isValid;
        CourseForm formBean;
        Map<String,String> courseTees, yardageValues;
        String teeIds[];
        int loop, holeNum, numHoles;
        
        isValid = true;       
        formBean = (CourseForm)aFormBean;
        
        // only validate the yardage values if the user inputted them...
        if (StringUtils.equals(formBean.getPromptYardages(), 
        		Constants.TRUE)) {
        	courseTees = formBean.getCourseTees();
        	yardageValues = formBean.getYardageValues();

        	// set the "nomenu" parameter...
        	NewUtil.setNoMenuAttribute(aRequest);

        	// only proceed to validation if handicapValues contains values...
        	if (yardageValues.size() > 0) {

        		// get the array of tee ids in play...
        		teeIds = (String[])courseTees.values().toArray(
        				new String[courseTees.size()]);

        		// get the number of holes...
        		numHoles = formBean.getNumHoles();

        		// loop over the objects and validate them...
        		for (loop = 0; loop < teeIds.length; loop++) {

        			// loop over the holes...
        			for (holeNum = 1; holeNum <= numHoles; holeNum++) {

        				// create the key to the map...
        				key = teeIds[loop] + "-" + holeNum;

        				// validate the handicap values are valid...
        				value = yardageValues.get(key);

        				yardageValue = GenericTypeValidator.formatInt(value);
        				if ((yardageValue == null) || 
        						(yardageValue.intValue() <= 0)) {

        					// we have a value that isn't a positive integer...
        					isValid = false;
        					addError(aField, courseTees, aRequest, aValidator, 
        							aValidatorAction, teeIds[loop], aErrors,
        							aValidatorAction.getMsg(), holeNum);
        				}
        			}
        		}
        	}
        }
        return isValid;
    }

    /**
     * Validates that the course handicap values are valid
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
    public static boolean validateCourseHandicaps(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        String value, key;
        Integer handicapValue;
        boolean isValid, dupErrMsgAdded;
        CourseForm formBean;
        Map<String,String> courseTees, handicapValues;
        String teeIds[];
        int loop, holeNum, numHoles;
        Map<String,Integer> handicapValuesTracker;
        
        // set the "nomenu" parameter...
		NewUtil.setNoMenuAttribute(aRequest);
        
        isValid = true;       
        formBean = (CourseForm)aFormBean;
        
        // only validate the handicap values if the user inputted them...
        if (StringUtils.equals(formBean.getPromptHandicaps(), 
        		Constants.TRUE)) {
        	dupErrMsgAdded = false;
        	courseTees = formBean.getCourseTees();
        	handicapValues = formBean.getHandicapValues();
        	handicapValuesTracker = new HashMap<String,Integer>();

        	// only proceed to validation if handicapValues contains values...
        	if (handicapValues.size() > 0) {

        		// get the array of tee ids in play...
        		teeIds = (String[])courseTees.values().toArray(
        				new String[courseTees.size()]);

        		// get the number of holes...
        		numHoles = formBean.getNumHoles();

        		// loop over the objects and validate them...
        		for (loop = 0; loop < teeIds.length; loop++) {

        			// fill the handicapValuesTracer...
        			for (holeNum = 1; holeNum <= numHoles; holeNum++) {

        				// insert into handicap tracker...		
        				key = teeIds[loop] + "-" + holeNum;
        				handicapValuesTracker.put(key, holeNum);
        			}

        			// loop over the holes...
        			for (holeNum = 1; holeNum <= numHoles; holeNum++) {

        				// create the key to the map...
        				key = teeIds[loop] + "-" + holeNum;

        				// validate the handicap values are valid...
        				value = handicapValues.get(key);

        				handicapValue = GenericTypeValidator.formatInt(value);
        				if ((handicapValue == null) || 
        						(handicapValue.intValue() <= 0)) {

        					// we have a value that isn't a positive integer...
        					isValid = false;
        					addError(aField, courseTees, aRequest, aValidator, 
        							aValidatorAction, teeIds[loop], aErrors,
        							"course.errors.handicappositiveinteger", 
        							holeNum);
        				} else if (handicapValue.intValue() > numHoles) {

        					// we're out of range...
        					isValid = false;
        					addError(aField, courseTees, aRequest, aValidator, 
        							aValidatorAction, teeIds[loop], aErrors,
        							"course.errors.handicapoutofrange", holeNum);
        				} else {            			
        					// remove entry from handicapValuesTracker (for
        					// dup-checking)...
        					handicapValuesTracker.remove(teeIds[loop] + "-" + 
        							handicapValue);
        				}
        			}

        			// if each hole had a unique handicap value, then 
        			// handicapValuesTracker should have a zero-size after the 
        			// inner for-loop completes.  Only do this check if we're 
        			// things are still valid...
        			if (isValid) {
        				if (handicapValuesTracker.size() > 0) {        				
        					isValid = false;
        					checkForDups(handicapValues, aErrors, aRequest, aField, 
        							aValidator, aValidatorAction, courseTees, 
        							teeIds[loop]);
        				}
        			}
        		}
        	}
        }
        return isValid;
    }	
    
    /**
     * Adds error to the aErrors with 2 args (tee color and hole num) using
     * a message-key of aMessageKey
     * @param aField
     * @param aCourseTees
     * @param aRequest
     * @param aValidator
     * @param aValidatorAction
     * @param aTeeId
     * @param aErrors
     * @param aMessageKey
     * @param aHoleNum
     */
    private static final void addError(Field aField, 
    		Map<String,String> aCourseTees, HttpServletRequest aRequest,
    		Validator aValidator, ValidatorAction aValidatorAction, 
    		String aTeeId, ActionMessages aErrors, String aMessageKey, 
    		int aHoleNum) {
	
    	// set the color for arg 0...
    	aField.addArg(ValidationUtils.createArg(0, NewUtil.getKey(aCourseTees, 
    			aTeeId)));
    	aField.addArg(ValidationUtils.createArg(1, Integer.toString(aHoleNum)));
    	aValidatorAction.setMsg(aMessageKey);
    	aField.setKey(aMessageKey);
    	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
			aRequest, aValidatorAction, aField));
    }
    
    /**
     * Checks for duplicate handicap values.
     * @param aHandicapValues
     * @param aErrors
     * @param aRequest
     * @param aField
     * @param aValidator
     * @param aValidatorAction
     * @param aCourseTees
     * @param aTeeId
     */
    private static final void checkForDups(Map<String,String> aHandicapValues, 
    		ActionMessages aErrors, HttpServletRequest aRequest, Field aField, 
    		Validator aValidator, ValidatorAction aValidatorAction, 
    		Map<String,String> aCourseTees, String aTeeId) {
    
    	Map<String,String> checkDups;
    	String handicapValuesArray[];
    	
    	checkDups = new HashMap<String,String>();
    	handicapValuesArray = (String[])aHandicapValues.values().toArray(
    				new String[aHandicapValues.size()]);
    	for (int i = 0; i < handicapValuesArray.length; i++) {        					
    		checkDups.put(handicapValuesArray[i], 
    				handicapValuesArray[i]);
    	}
    	if (checkDups.size() < handicapValuesArray.length) {
    		aField.addArg(ValidationUtils.createArg(0, NewUtil.getKey(
    				aCourseTees, aTeeId)));
    		aValidatorAction.setMsg("course.errors.handicapduplicate");
    		aErrors.add(aField.getKey(), Resources.getActionMessage(
    					aValidator, aRequest, aValidatorAction, aField));
    	}
    }
		
    /**
     * Validates that the course rating values are all valid 
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
    public static boolean validateCourseRatings(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        CourseForm formBean;
        Map<String,String> courseTees, ratingValues;
        String teeIds[];
        int loop, validCount;
        boolean doValidation;
        
        doValidation = aRequest.getParameter(
        		WebConstants.DO_VALIDATE_RATINGS_REQ_PARAM) != null;
        if (doValidation) {
        
        	// set the "nomenu" parameter...
        	NewUtil.setNoMenuAttribute(aRequest);

        	validCount = 0;
        	formBean = (CourseForm)aFormBean;
        	courseTees = formBean.getCourseTees();
        	ratingValues = formBean.getRatingValues();

        	// only proceed to validation if slopeValues contains values...

        	if (ratingValues.size() > 0) {

        		// get the array of tee ids in play...
        		teeIds = (String[])courseTees.values().toArray(
        				new String[courseTees.size()]);

        		// loop over the objects and validate them...if validCount
        		// ends-up being greater than 0, than this function will return 
        		// false...
        		for (loop = 0; loop < teeIds.length; loop++) {
        			validCount += validateRatingValue(ratingValues.get("overall|" + 
        					teeIds[loop]), aField, aErrors, aRequest, courseTees, 
        					aValidator, aValidatorAction, "Overall", teeIds[loop]);
        			//validCount += validateRatingValue(ratingValues.get("front|" + 
        			//		teeIds[loop]), aField, aErrors, aRequest, courseTees, 
        			//		aValidator, aValidatorAction, "Front-9", teeIds[loop]);
        			//validCount += validateRatingValue(ratingValues.get("back|" + 
        			//		teeIds[loop]), aField, aErrors, aRequest, courseTees, 
        			//		aValidator, aValidatorAction, "Back-9", teeIds[loop]);
        		}
        	}
        	return validCount == 0;
        } else {
        	return true;
        }
    }	
    
    /**
     * Validates the rating value, aRatingValue
     * @param aRatingValue
     * @param aField
     * @param aErrors
     * @param aRequest
     * @param aCourseTees
     * @param aValidator
     * @param aValidatorAction
     * @param aTeeType
     * @param aTeeId
     * @return 0 if the slope value is valid; 1 if the slope is invalid
     */
    private static int validateRatingValue(String aRatingValue, Field aField,
    		ActionMessages aErrors, HttpServletRequest aRequest, 
    		Map<String,String> aCourseTees, Validator aValidator, 
    		ValidatorAction aValidatorAction, String aTeeType, String aTeeId) {
    	
    	Float ratingValue;
    	boolean isValid;
    	isValid = true;
    	ratingValue = GenericTypeValidator.formatFloat(aRatingValue);
    	if ((ratingValue == null) || (ratingValue.floatValue() < 0)) {
    		isValid = false;
    		aField.addArg(ValidationUtils.createArg(0, 
    				NewUtil.getKey(aCourseTees, aTeeId)));
    		aField.addArg(ValidationUtils.createArg(1, aTeeType));
    		aErrors.add(aField.getKey(), 
    				Resources.getActionMessage(aValidator, 
    						aRequest, aValidatorAction, aField));
    	}
		return isValid ? 0 : 1;
    }
	
    /**
     * Validates that the course slope values are all valid 
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
    public static boolean validateCourseSlopes(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        CourseForm formBean;
        Map<String,String> courseTees, slopeValues;
        String teeIds[];
        int loop, validCount;
        boolean doValidation;
        
        doValidation = aRequest.getParameter(
        		WebConstants.DO_VALIDATE_SLOPES_REQ_PARAM) != null;
        if (doValidation) {
        
        	// set the "nomenu" parameter...
        	NewUtil.setNoMenuAttribute(aRequest);

        	validCount = 0;
        	formBean = (CourseForm)aFormBean;
        	courseTees = formBean.getCourseTees();
        	slopeValues = formBean.getSlopeValues();

        	// only proceed to validation if slopeValues contains values...
        	if (slopeValues.size() > 0) {

        		// get the array of tee ids in play...
        		teeIds = (String[])courseTees.values().toArray(
        				new String[courseTees.size()]);

        		// loop over the objects and validate them...if validCount
        		// ends-up being greater than 0, than this function will return 
        		// false...
        		for (loop = 0; loop < teeIds.length; loop++) {
        			validCount += validateSlopeValue(slopeValues.get("overall|" + 
        					teeIds[loop]), aField, aErrors, aRequest, courseTees, 
        					aValidator, aValidatorAction, "Overall", teeIds[loop]);
        			//validCount += validateSlopeValue(slopeValues.get("front|" + 
        			//		teeIds[loop]), aField, aErrors, aRequest, courseTees, 
        			//		aValidator, aValidatorAction, "Front-9", teeIds[loop]);
        			//validCount += validateSlopeValue(slopeValues.get("back|" + 
        			//		teeIds[loop]), aField, aErrors, aRequest, courseTees, 
        			//		aValidator, aValidatorAction, "Back-9", teeIds[loop]);
        		}
        	}
        	return validCount == 0;
        } else {
        	return true;
        }
    }
    
    /**
     * Validates the slope value, aSlopeValue
     * @param aSlopeValue
     * @param aField
     * @param aErrors
     * @param aRequest
     * @param aCourseTees
     * @param aValidator
     * @param aValidatorAction
     * @param aTeeType
     * @param aTeeId
     * @return 0 if the slope value is valid; 1 if the slope is invalid
     */
    private static int validateSlopeValue(String aSlopeValue, Field aField,
    		ActionMessages aErrors, HttpServletRequest aRequest, 
    		Map<String,String> aCourseTees, Validator aValidator, 
    		ValidatorAction aValidatorAction, String aTeeType, String aTeeId) {
    	
    	Integer slopeValue;
    	boolean isValid;
    	
    	isValid = true;
    	slopeValue = GenericTypeValidator.formatInt(aSlopeValue);
		if ((slopeValue == null) || (slopeValue.intValue() < 0)) {
			isValid = false;
			aField.addArg(ValidationUtils.createArg(0, 
					NewUtil.getKey(aCourseTees, aTeeId)));
			aField.addArg(ValidationUtils.createArg(1, aTeeType));
			aErrors.add(aField.getKey(), 
					Resources.getActionMessage(aValidator, 
							aRequest, aValidatorAction, aField));
		}
		return isValid ? 0 : 1;
    }

    /**
     * Checks if the tee color field isn't null 
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
    public static boolean validateTeeColorRequired(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        boolean isValid;
        CourseForm formBean;
        Map<String,String> courseTees;
        
        // set the "nomenu" parameter...
		NewUtil.setNoMenuAttribute(aRequest);
        isValid = true;
        formBean = (CourseForm)aFormBean;
        courseTees = formBean.getCourseTees();
        if (courseTees.size() == 0) {
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
            isValid = false;
        } 
        return isValid;
    }
    
    /**
     * Checks if the starting holes are valid or not 
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
    public static boolean validateStartingHoles(Object aFormBean,
                                           ValidatorAction aValidatorAction, 
                                           Field aField,
                                           ActionMessages aErrors,
                                           Validator aValidator,
                                           HttpServletRequest aRequest) {

        boolean isValid;
        CourseForm formBean;
        
        // set the "nomenu" parameter...
		NewUtil.setNoMenuAttribute(aRequest);
        isValid = true;
        formBean = (CourseForm)aFormBean;
        if (formBean.getFront9StartingHole() == formBean.getBack9StartingHole()) {
        	aValidatorAction.setMsg("course.errors.startingholes.unique");
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
            isValid = false;        	
        } else if (formBean.getFront9StartingHole() > formBean.getBack9StartingHole()) {
        	aValidatorAction.setMsg("course.errors.startingholes.ascending");
        	aErrors.add(aField.getKey(), Resources.getActionMessage(aValidator, 
        			aRequest, aValidatorAction, aField));
            isValid = false;         	
        }
        return isValid;
    }
}
