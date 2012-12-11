package name.paulevans.golf.tools;

import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.CourseSlopeRating;
import gen.hibernate.name.paulevans.golf.CourseSlopeRatingId;
import gen.hibernate.name.paulevans.golf.StateProvince;
import gen.hibernate.name.paulevans.golf.StateProvinceId;
import gen.hibernate.name.paulevans.golf.Tee;
import gen.hibernate.name.paulevans.golf.TeeId;
import gen.hibernate.name.paulevans.golf.TeeName;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.paulevans.golf.Constants;
import name.paulevans.golf.util.NewUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Tool used to import the course slope/rating database extract from the USGA
 * into the local statistics database
 * @author pevans
 *
 */
public class SlopeRatingImportTool extends Tool implements Runnable {
	
	// modes...
	private static final int PRINT_UNIQUE_TEE_COLORS = 0;
	private static final int IMPORT_DATA = 1;
	
	// environment variable property names...
	private static final String EXTRACT_FILE_LOCATION_PROPERTY = 
		"extractlocation";
	private static final String MODE = "mode";
	private static final String STD_TEE_COLORS_PROPERTY = "stdteecolors";
	
	// contains location of extract file...
	private static final String EXTRACT_FILE = System.getProperty(
			EXTRACT_FILE_LOCATION_PROPERTY);
		
	// tee colors map...
	private static final Map<String,String> TEE_COLORS = 
		new HashMap<String,String>();
	
	// collection of state-province IDs...
	private static final Map<String,StateProvince> STATE_PROV_IDS = 
		new HashMap<String,StateProvince>();
	
	// standard tee colors...
	private static final String STD_TEE_COLORS[] = NewUtil.getValues(
			System.getProperty(STD_TEE_COLORS_PROPERTY));
	
	// the collection of TeeName entities persisted...
	private static Map<String,TeeName> TEE_NAMES = 
		new HashMap<String,TeeName>();
	
	// the collection of Course entities persisted...
	private static Map<String,Course> COURSES = new HashMap<String,Course>();
	
	// the collection Tee entities persisted...
	private static Map<String,Tee> TEES = new HashMap<String,Tee>();

	// save counts...
	private static int TEE_NAME_SAVE_COUNT;
	private static int ADDRESS_SAVE_COUNT;
	private static int COURSE_SAVE_COUNT;
	private static int TEE_SAVE_COUNT;
	
	/**
	 * Application entry point
	 * @param aArgs
	 */
	public static void main(String aArgs[]) {
		
		Thread thread;
		
		// create and start the thread...
		thread = new Thread(new SlopeRatingImportTool(aArgs));
		thread.start();
	}
	
	/**
	 * Private constructor
	 * @param aCmdArgs
	 */
	private SlopeRatingImportTool(String aCmdArgs[]) {
		super();
	}
	
	/**
	 * Run method.
	 */
	public void run() {
		
		int mode;
		
		mode = Integer.parseInt(System.getProperty(MODE));
		switch (mode) {
		case PRINT_UNIQUE_TEE_COLORS:
			printUniqueTeeColors();
			break;
		case IMPORT_DATA:
			importData();
			break;
		}
	}
	
	/**
	 * Print out the unique tee colors/names
	 * @throws Exception
	 */
	private void printUniqueTeeColors() {
		processExtract(null, PRINT_UNIQUE_TEE_COLORS);
	}
	
	/**
	 * Import the data
	 * @param aArgs
	 */
	private void importData() {
		
		Session session;
		Transaction transaction;
		
		session = newSession();
		transaction = null;
		try {
			transaction = session.beginTransaction();
			populateTeeNamesMap(session);
			populateCoursesMap(session);
			processExtract(session, IMPORT_DATA);
			System.out.printf("Tee name save count: %d\n", TEE_NAME_SAVE_COUNT);
			System.out.printf("Address save count: %d\n", ADDRESS_SAVE_COUNT);
			System.out.printf("Course save count: %d\n", COURSE_SAVE_COUNT);
			System.out.printf("Tee save count: %d\n", TEE_SAVE_COUNT);
			transaction.commit();
		} catch (Exception any) {
			any.printStackTrace();
			if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	/**
	 * Processes the current record depending on the aMode
	 * @param aFieldValues
	 * @param aLineNumber
	 * @param aSession
	 */
	private final void processRecords(Map<String,String> aFieldValues, 
			int aLineNumber,
			int aMode, Session aSession) {
		if (aMode == PRINT_UNIQUE_TEE_COLORS) {
			printTeeColor(aFieldValues);
		} else if (aMode == IMPORT_DATA) {
			importRecord(aFieldValues, aLineNumber, aSession);
		}
	}
	
	/**
	 * Inserts the records for a given line of data
	 * @param aFieldValues
	 */
	private final void importRecord(Map<String,String> aFieldValues, 
			int aLineNumber, Session aSession) {
		
		StateProvince stateProvince;
		TeeName teeName;
		Course course;
		
		teeName = saveTeeName(aFieldValues, aSession);
		stateProvince = getStateProvinceId(aSession, aFieldValues.get("state"));
		if (stateProvince != null) {
			course = saveCourse(aFieldValues, stateProvince, aSession);
			saveTee(aFieldValues, teeName, course, aSession, aLineNumber);
		} else {
			System.out.printf("Line number: %d produced a null address\n", 
					aLineNumber);
		}
	}

	/**
	 * Saves a course entity
	 * @param aFieldValues
	 * @param aSession
	 */
	private final Course saveCourse(Map<String,String> aFieldValues, 
			StateProvince aStateProvince, Session aSession) {
		
		Course courseEntity;
		String courseName, clubName, courseKey, courseCity, courseState;
		String associationName;
		int clubNameLength, strLenCheck;
		StateProvince stateProvince;
		
		courseName = aFieldValues.get("courseName").trim();
		courseCity = aFieldValues.get("city").trim();
		courseState = aFieldValues.get("state").trim();
		clubName = aFieldValues.get("clubName").trim();
		clubNameLength= clubName.length();
		strLenCheck = clubNameLength / 2;
		associationName = aFieldValues.get("associationName").trim();
		// if the first half of the characters that appear in the club name
		// do not equal the first characters that appear in the course name, 
		// append the club namd and course name togeter...
		if (!StringUtils.equals(StringUtils.left(clubName, strLenCheck), 
				StringUtils.left(courseName, strLenCheck))) {
			courseName = clubName + " - " + courseName;
		}
		courseKey = clubName + "$$$" + courseName + "$$$" + courseState;
		courseEntity = COURSES.get(courseKey);
		if (courseEntity == null) {
			courseEntity = new Course();
			courseEntity.setStateProvince(aStateProvince);
			courseEntity.setCity(aFieldValues.get("city"));
			courseEntity.setDescription(courseName);
			courseEntity.setClubName(clubName);
			courseEntity.setAssociationName(associationName);
			courseEntity.setFrontNineStartingHole(1);
			courseEntity.setBackNineStartingHole(10);
			COURSES.put(courseKey, courseEntity);
			aSession.saveOrUpdate(courseEntity);
			COURSE_SAVE_COUNT++;
			flushSession(COURSE_SAVE_COUNT, aSession);
		}
		return courseEntity;
	}		
	
	/**
	 * Saves a tee entity
	 * @param aFieldValues
	 * @param aTeeName
	 * @param aCourse
	 * @param aSession
	 * @return
	 */
	private final Tee saveTee(Map<String,String> aFieldValues, 
			TeeName aTeeName, Course aCourse, Session aSession, 
			int aLineNumber) {
		
		String teeKey;
		Tee teeEntity;
		TeeId teeId;
		CourseSlopeRating slopeRating;
		CourseSlopeRatingId slopeRatingId;

		teeKey = aCourse.getId() + "$$$" + aTeeName.getId();
		teeEntity = TEES.get(teeKey);
		if (teeEntity == null) {
			try {
				teeEntity = new Tee();
				teeId = new TeeId();
				teeId.setCourseId(aCourse.getId());
				teeId.setTeeNameId(aTeeName.getId());
				teeEntity.setId(teeId);
				teeEntity.setCourse(aCourse);
				teeEntity.setTeeName(aTeeName);
				teeEntity.setOverallRating(NumberUtils.createFloat(
						aFieldValues.get("ratingOverall")));
				teeEntity.setOverallSlope(NumberUtils.createInteger(
						aFieldValues.get("slopeOverall")));
				aSession.save(teeEntity);
				slopeRating = new CourseSlopeRating();
				slopeRatingId = new CourseSlopeRatingId();
				slopeRatingId.setCourseId(aCourse.getId());
				slopeRatingId.setTeeNameId(aTeeName.getId());
				slopeRatingId.setNineType(Constants.FRONT_NINE_TYPE);
				slopeRating.setStartingHole(1);
				slopeRating.setId(slopeRatingId);
				slopeRating.setRating(NumberUtils.createFloat(aFieldValues.get(
				"ratingFront")));
				try {
					slopeRating.setSlope(NumberUtils.createFloat(
							aFieldValues.get("slopeFront")).intValue());
					if (slopeRating.getSlope() > 0 && 
						slopeRating.getRating() > 0) {
						aSession.save(slopeRating);
					} 
				} catch (NumberFormatException e) {
					// do nothing...
				}
				slopeRating = new CourseSlopeRating();
				slopeRatingId = new CourseSlopeRatingId();
				slopeRatingId.setCourseId(aCourse.getId());
				slopeRatingId.setTeeNameId(aTeeName.getId());
				slopeRatingId.setNineType(Constants.BACK_NINE_TYPE);
				slopeRating.setStartingHole(10);
				slopeRating.setId(slopeRatingId);
				slopeRating.setRating(NumberUtils.createFloat(aFieldValues.get(
				"ratingBack")));
				try {
					slopeRating.setSlope(NumberUtils.createFloat(
							aFieldValues.get("slopeBack")).intValue());
					if (slopeRating.getSlope() > 0 && 
						slopeRating.getRating() > 0) {
						aSession.save(slopeRating);
					}
				} catch (NumberFormatException e) {
					// do nothing...
				}
				TEES.put(teeKey, teeEntity);
				TEE_SAVE_COUNT++;
				flushSession(TEE_SAVE_COUNT, aSession);
			} catch (Exception any) {
				throw new RuntimeException("courseid: " + aFieldValues.get("courseid"), any);
			}
		}
		return teeEntity;
	}
	
	/**
	 * Saves a tee name entity
	 * @param aFieldValues
	 * @param aSession
	 */
	private final TeeName saveTeeName(Map<String,String> aFieldValues, 
			Session aSession) {
		
		TeeName teeNameEntity;
		String teeName;
		
		teeName = aFieldValues.get("teesName").trim();
		teeNameEntity = TEE_NAMES.get(teeName);
		if (teeNameEntity == null) {
			teeNameEntity = new TeeName();
			teeNameEntity.setName(teeName);
			if (isStandardColor(teeName)) {
				teeNameEntity.setIsStandardColor(true);
			}
			TEE_NAMES.put(teeName, teeNameEntity);
			aSession.save(teeNameEntity);
			TEE_NAME_SAVE_COUNT++;
			flushSession(TEE_NAME_SAVE_COUNT, aSession);
		}
		return teeNameEntity;
	}
	
	/**
	 * Prints a tee color/name if it is unique
	 * @param aFieldValues
	 */
	private static final void printTeeColor(Map<String,String> aFieldValues) {
		
		String teesName;
		
		teesName = aFieldValues.get("teesName");
		if (TEE_COLORS.get(teesName) == null) {
			TEE_COLORS.put(teesName, teesName);
			System.out.println(teesName);
		}
	}
	
	/**
	 * Returns true if aTeeName is a standard color-name
	 * @param aTeeName
	 * @return
	 */
	private static final boolean isStandardColor(String aTeeName) {
		
		for (String teeColor : STD_TEE_COLORS) {
			if (StringUtils.equalsIgnoreCase(teeColor, aTeeName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Imports the extract data
	 * @param aSession
	 * @throws SQLException
	 */
	private final void processExtract(Session aSession, int aMode) {
	
		
		Map<String,String> fieldValues;
		CSVReader reader;
		String nextLine[];
		int lineNumber;
		String val;
		
		fieldValues = new HashMap<String,String>();
		lineNumber = 2;
		try {
			reader = new CSVReader(new FileReader(EXTRACT_FILE));
			reader.readNext(); // advance cursor to first line of actual data...
			while ((nextLine = reader.readNext()) != null) {
				fieldValues.put("courseid", nextLine[0]);
				val = nextLine[1].toLowerCase();
				val = StringUtils.replace(val, " gc", " GC");
				val = StringUtils.replace(val, " cc", " CC");
				fieldValues.put("clubName", WordUtils.capitalize(val));
				val = nextLine[2].toLowerCase();
				val = StringUtils.replace(val, " gc", " GC");
				val = StringUtils.replace(val, " cc", " CC");
				fieldValues.put("courseName", WordUtils.capitalize(val));
				fieldValues.put("teesName", WordUtils.capitalize(
						nextLine[3].toLowerCase()));
				fieldValues.put("city", WordUtils.capitalize(
						nextLine[4].toLowerCase()));
				fieldValues.put("state", nextLine[5]);
				fieldValues.put("associationName", WordUtils.capitalize(
						nextLine[6].toLowerCase()));
				fieldValues.put("menWomenToggle", nextLine[7]);
				fieldValues.put("ratingFront", nextLine[8]);
				fieldValues.put("ratingBack", nextLine[9]);
				fieldValues.put("ratingOverall", nextLine[10]);
				fieldValues.put("slopeFront", nextLine[11]);
				fieldValues.put("slopeBack", nextLine[12]);
				fieldValues.put("slopeOverall", nextLine[13]);
				fieldValues.put("effectiveDt", nextLine[14]);
				fieldValues.put("bogeyFront", nextLine[15]);
				fieldValues.put("bogeyBack", nextLine[16]);
				fieldValues.put("bogeyOverall", nextLine[17]);
				if (validRecord(fieldValues, lineNumber)) {
					processRecords(fieldValues, lineNumber, aMode, aSession);
				}
				lineNumber++;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Validates the record
	 * @param aFieldValues
	 * @return
	 */
	private static final boolean validRecord(Map<String,String> aFieldValues, 
			int aLineNumber) {
		return true;
	}
	
	/**
	 * Populates the TEE_NAMES map with the existing entities
	 * @param aSession
	 */
	private static final void populateTeeNamesMap(Session aSession) {
		
		List<TeeName> teeNames;
				
		teeNames = aSession.createQuery("from TeeName").list();
		for (TeeName teeNameEntity : teeNames) {
			TEE_NAMES.put(teeNameEntity.getName(), teeNameEntity);
		}
	}

	/**
	 * Populates the COURSES map with the existing entities
	 * @param aSession
	 */
	private final void populateCoursesMap(Session aSession) {
		
		List<Course> courses;
		StateProvince stateProvince;
		String courseKey, clubName, courseName;
		int strLenCheck, clubNameLength;
				
		courses = aSession.createQuery("from Course").list();
		for (Course course : courses) {
			clubName = course.getClubName();
			courseName = course.getDescription();
			clubNameLength= clubName.length();
			strLenCheck = clubNameLength / 2;
			// if the first half of the characters that appear in the club name
			// do not equal the first characters that appear in the course name, 
			// append the club namd and course name togeter...
			if (!StringUtils.equals(StringUtils.left(clubName, strLenCheck), 
					StringUtils.left(courseName, strLenCheck))) {
				courseName = clubName + " - " + courseName;
			}
			stateProvince = course.getStateProvince();
			Hibernate.initialize(stateProvince);
			courseKey = clubName + "$$$" + courseName + "$$$" + 
				stateProvince.getName();
			COURSES.put(courseKey, course);
		}
	}	
	
	/**
	 * Returns the ID of the state-province entity given the inputted state-name
	 * @param aSession
	 * @param aStateName
	 * @return
	 */
	private final StateProvince getStateProvinceId(Session aSession, 
			String aStateName) {
		
		Integer result[];
		StateProvince stateProvince;
		StateProvinceId stateProvinceId;
		
		stateProvince = STATE_PROV_IDS.get(aStateName);
		if (stateProvince == null) {
			result = retreiveStateProvinceId(aSession, aStateName);
			if (result != null) {
				stateProvince = new StateProvince();
				stateProvinceId = new StateProvinceId();
				stateProvinceId.setCountryId(result[0]);
				stateProvinceId.setId(result[1]);
				stateProvince.setId(stateProvinceId);
				STATE_PROV_IDS.put(aStateName, stateProvince);
			}
		}
		return stateProvince;
	}
	
	/**
	 * Returns the ID of the state-province entity given the inputted state-name
	 * @param aSession
	 * @param aStateName
	 * @return
	 */
	private final Integer[] retreiveStateProvinceId(Session aSession, 
			String aStateName) {
		
		SQLQuery query;
		Integer result[] = new Integer[2];
		
		query = aSession.createSQLQuery("select id as stateId from " +
				"state_province where country_id = 0 and name = :stateName");
		query.setString("stateName", aStateName);
		query.addScalar("stateId", Hibernate.INTEGER);
		result[1] = (Integer)query.uniqueResult();
		result[0] = 0;
		
		if (result[1] == null) { // try looking in canada...
			query = aSession.createSQLQuery("select id as stateId from " +
			"state_province where country_id = 1 and name = :stateName");
			query.setString("stateName", aStateName);
			query.addScalar("stateId", Hibernate.INTEGER);
			result[1] = (Integer)query.uniqueResult();
			result[0] = 1;
		} else return result;
		if (result[1] == null) { // try looking in the Netherlands...
			query = aSession.createSQLQuery("select id as stateId from " +
			"state_province where country_id = 2 and name = :stateName");
			query.setString("stateName", aStateName);
			query.addScalar("stateId", Hibernate.INTEGER);
			result[1] = (Integer)query.uniqueResult();
			result[0] = 2;
		} else return result;
		return null;
	}
}
