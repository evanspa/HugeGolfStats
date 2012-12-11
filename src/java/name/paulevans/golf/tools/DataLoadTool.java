package name.paulevans.golf.tools;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.auth.RoleConstants;
import name.paulevans.golf.dao.ApproachShotDistanceDAO;
import name.paulevans.golf.dao.ApproachShotLineDAO;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.GolfClubDAO;
import name.paulevans.golf.dao.GreenInRegulationDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScoreId;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;
import name.paulevans.golf.dao.TeeShotAccuracyDAO;
import name.paulevans.golf.dao.UserRoleDAO;
import name.paulevans.golf.dao.UserRoleId;
import name.paulevans.golf.dao.WeatherConditionDAO;
import name.paulevans.golf.util.EncryptionUtils;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.struts.action.round.SaveRoundAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Tool used to populate the database with fictional players with fictional
 * data
 * @author pevans
 *
 */
public class DataLoadTool extends Tool implements Runnable {
	
	// default number of players to create if not supplied on command-line...
	private static final int DEFAULT_NUM_PLAYERS = 5;
	
	// default number of rounds to create for each player...
	private static final int DEFAULT_NUM_ROUNDS = 50;
	
	// default number of holes to create per course...
	private static final int NUM_HOLES_TO_CREATE = 18;
	
	// random first-name strings...
	private static final String FIRST_NAMES[] = { "Paul", "Jordan", "Dave",
		"Caroline", "Sarah", "Bill", "Tracey", "Andy", "Ron", "Chris", "Deb",
		"Cindy", "Martina", "Steve", "Phil", "John", "Eric", "Jennifer", "Tom",
		"Helen", "Schylar", "Meredith", "Nicole", "Julie", "Nate", "Bob" };
	
	// random last-name strings...
	private static final String LAST_NAMES[] = { "Evans", "Brown", "Read",
		"Uffelman", "Feinman", "Adams", "Madden", "Reid", "Frist", "Pelosi",
		"Woods", "Provost", "Pyle", "Darby", "Harris", "Magnusson" };
	
	// par values used to populate the holes of a course...
	private static final int PAR_VALUES[] = {4,4,4,4,3,4,4,4,4,3,4,4,4,4,3,
		5,4,4,4,4,3,3,4,4,4,4,4,4,3,4,4,4,5,5,4,4,4,4,5,5,5,4,4,4,4,4,3,4,4,4,4
	};
	
	// par-4 or higher club IDs...
	private static final int PAR_4_CLUB_IDS[] = {0,1,14};
	
	// par-3 club IDs...
	private static final int PAR_3_CLUB_IDS[] = {15,16,17,18,19,20,21};
	
	// map to store courses with holes defined...
	private static final Map<String,HoleDAO[]> COURSES_WITH_HOLES = 
		new HashMap<String,HoleDAO[]>();
		
	// get handle to encryption utils object...
	private static final EncryptionUtils encryptionUtils = 
		BeanFactory.getEncryptionUtils();		
	
	// instance members...
	private String cmdArgs[];
	private TeeDAO tees[];
	private SessionFactory sessionFactory;
	private PlatformTransactionManager transactionManager;
	
	/**
	 * Private constructor
	 * @param aArgs
	 */
	public DataLoadTool() {
		super();
	}
	
	/**
	 * Setter for the command-line arguments
	 * @param aArgs
	 */
	private void setArgs(String aArgs[]) {
		cmdArgs = aArgs;
	}
	
	/**
	 * Sets the session factory
	 * @param aSessionFactory
	 */
	public void setSessionFactory(SessionFactory aSessionFactory) {
		sessionFactory = aSessionFactory;
	}
	
	/**
	 * Gets the session factory
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Get the transaction manager
	 * @return
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * Set the transaction manager
	 * @param aTransactionManager
	 */
	public void setTransactionManager(
			PlatformTransactionManager aTransactionManager) {
		transactionManager = aTransactionManager;
	}
	
	/**
	 * Run method.
	 */
	public void run() {
		
		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(getTransactionManager());
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {
				
				int numPlayers;
				
				// set the number of players to create...
				numPlayers = DEFAULT_NUM_PLAYERS;
				if (cmdArgs != null && cmdArgs.length > 0) {
					numPlayers = Integer.parseInt(cmdArgs[0]);
				}
		
				// load courses...
				loadTees();
				System.out.println("Tees loaded successfully...");

				// create the players...
				createPlayers(numPlayers, DEFAULT_NUM_ROUNDS);
			}
		});
	}
	
	/**
	 * Loads the courseIds private member-variable
	 *
	 */
	private final void loadTees() {
		
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		tees = (TeeDAO[])ht.execute(
				new HibernateCallback() {				
					public Object doInHibernate(Session aSession) 
					throws HibernateException {
						
						List tees;
						List<TeeDAO> teeDAOs;
						TeeDAO tee;

						teeDAOs = new ArrayList<TeeDAO>();
						tees = aSession.createQuery("from Tee").list();
						for (int i = 0; i < tees.size(); i++) {
							tee = BeanFactory.newTeeDAO();
							tee.setDelegate(tees.get(i));
							teeDAOs.add(tee);
						}
						flush(aSession);
						return teeDAOs.toArray(new TeeDAO[teeDAOs.size()]);
					}
				});
	}
	
	/**
	 * Creates the players
	 * @param aNumPlayers
	 * @return
	 */
	private final void createPlayers(final int aNumPlayers, final int aNumRounds) {
		
		PlayerDAO player;
	
		for (int i = 0; i < aNumPlayers; i++) {
			player = createPlayer(i, aNumRounds);
			System.out.println("Player " + (i+1) + " of " + aNumPlayers + " (" + 
					player.getUserId() + ", password: '" + player.getPasswd() + 
					"') created...");
		}
	}
	
	/**
	 * Creates a player of a random skill-level
	 * @param aSession
	 * @return
	 */
	private final PlayerDAO createPlayer(int aIndex, int aNumRounds) {
		
		PlayerDAO player;
		UserRoleDAO role;
		UserRoleId roleId;
		int randomHandicap;
		
		// create and save the user...
		player = BeanFactory.newPlayerDAO();
		player.setFirstName(FIRST_NAMES[RandomUtils.nextInt(
				FIRST_NAMES.length)]);
		player.setLastName(LAST_NAMES[RandomUtils.nextInt(
				LAST_NAMES.length)]);
		player.setEmailAddress(player.getFirstName() + "." + 
				player.getLastName() + "_" + aIndex + "@hugegolfstats.com");
		player.setBirthdate(getRandomBirthdate());
		player.setPasswd(encryptionUtils.digest("password"));
		player.setPostalCode("12345");
		player.setUserId("user_" + aIndex);
		player.setGHINNumber("123");
		player.setEyeWear(getRandomEyeWearType());
		player.setHeadWear(getRandomHeadWearType());
		player.setPantWear(getRandomPantWearType());
		player.setNumHolesPlayed(18);
		role = BeanFactory.newUserRoleDAO();
		roleId = new UserRoleId();
		roleId.setRoleName(RoleConstants.USER_ROLE);
		roleId.setUserId(player.getUserId());
		role.setId(roleId);
		role.save();
		player.save();
		
		// set a random handicap value for the player...
		randomHandicap = RandomUtils.nextInt(33);
		
		// create the rounds for the player...
		createRounds(player, randomHandicap, aNumRounds);
		
		// flush the session...
		if (aIndex % 10 == 0) {
			flush(getSessionFactory().getCurrentSession());
		}
		return player;
	}
	
	/**
	 * Creates the rounds for the player
	 * @param aPlayer
	 * @param aHandicap
	 */
	private final void createRounds(PlayerDAO aPlayer, int aHandicap, 
			int aNumRounds) {
		
		int loop;
		Set<ScorecardDAO> scorecards;
		Calendar datePlayed;
		
		datePlayed = Calendar.getInstance();
		scorecards = new HashSet<ScorecardDAO>();
		for (loop = 0; loop < aNumRounds; loop++) {
			datePlayed.add(Calendar.DATE, (aNumRounds-loop) * -1);
			scorecards.add(createScorecard(aPlayer, aHandicap, 
					datePlayed.getTime()));
		}
	}
	
	/**
	 * Returns a random tee
	 * @return
	 */
	private final TeeDAO getRandomTee() {
		return tees[RandomUtils.nextInt(tees.length)];
	}
	
	/**
	 * Creates a random scorecard based on the player's handicap
	 * @param aPlayer
	 * @param aHandicap
	 * @return
	 */
	private final ScorecardDAO createScorecard(PlayerDAO aPlayer, 
			int aHandicap, Date aDatePlayed) {
		
		ScorecardDAO scorecard;
		TeeDAO tee;
		HoleDAO holes[];
		
		// create the scorecard...
		scorecard = BeanFactory.newScorecardDAO();
		
		// set the player...
		scorecard.setPlayer(aPlayer);
		
		// set the random tee...
		scorecard.setTee(tee = getRandomTee());
		
		// get the holes...
		holes = getHoles(tee);
		
		// set random date played...
		scorecard.setDate(aDatePlayed);
		
		// set circumstances and save...
		scorecard.setCollectNumPutts(true);
		scorecard.setCollectApproachShotDistance(true);
		scorecard.setCollectApproachShotLine(true);
		scorecard.setCollectClubUsedOffTee(true);
		scorecard.setCollectFairwayHit(true);
		scorecard.setCollectGir(true);
		scorecard.setCollectSandSave(true);
		scorecard.setCollectTeeShotDistance(true);
		scorecard.setCollectUpDown(true);
		scorecard.setWoreLongSleeves(RandomUtils.nextBoolean());
		scorecard.setWoreVest(RandomUtils.nextBoolean());
		scorecard.setIsTournament(RandomUtils.nextBoolean());
		scorecard.setUsedCaddie(RandomUtils.nextBoolean());
		scorecard.setUsedCart(RandomUtils.nextBoolean());
		scorecard.setWeatherCondition(getRandomWeatherCondition());
		scorecard.setHeadWear(getRandomHeadWearType());
		scorecard.setPantWear(getRandomPantWearType());
		scorecard.setEyeWear(getRandomEyeWearType());
		scorecard.save();
		
		// create and save scores...
		saveScores(scorecard, aHandicap, holes);
		
		// save the summary object...
		createSummary(aPlayer, scorecard).save();	
		
		// refresh the summary...
		//aPlayer.refreshSummary(BeanFactory.getDAOUtils(), 
		//		BeanFactory.getUtilObject(), null);
		
		return scorecard;
	}
	
	/**
	 * Creates a scorecard summary object from the inputted parameters
	 * @return
	 */
	private final ScorecardSummaryDAO createSummary(PlayerDAO aPlayer, 
			ScorecardDAO aScorecard) {
		
		RoundForm roundForm;
		DAOUtils daoUtils;
		NewUtil newUtil;
		ScorecardSummaryDAO summary;
		
		daoUtils = BeanFactory.getDAOUtils();	
		roundForm = new RoundForm();
		roundForm.initialize(newUtil = BeanFactory.getUtilObject(), 
				aScorecard, null);
		roundForm.setHolesPlayed("1-9", "true");
		roundForm.setHolesPlayed("10-18", "true");
		summary = SaveRoundAction.getSummary(aPlayer, newUtil, roundForm, 
				daoUtils);
		summary.setScoreType(NewUtil.getScoreType(aScorecard));
		summary.setScorecardId((Integer)aScorecard.getId());
		summary.setScorecard(aScorecard);
		summary.setCourseId((Integer)aScorecard.getTee().getCourse().getId());
		summary.setSlope(aScorecard.getTee().getOverallSlope());
		summary.setRating(aScorecard.getTee().getOverallRating());
		return summary;
	}
	
	/**
	 * Saves the hole-scores based on the inputted criteria
	 * @param aScorecard
	 * @param aHoles
	 * @param aSession
	 * @param aNumEagles
	 * @param aNumBirdies
	 * @param aNumPars
	 * @param aNumBogeys
	 * @param aNumDblBogeys
	 * @param aNumTplBogeys
	 */
	private static final void saveScores(ScorecardDAO aScorecard, HoleDAO aHoles[],
			int aNumEagles, int aNumBirdies, int aNumPars,
			int aNumBogeys, int aNumDblBogeys, int aNumTplBogeys) {
		
		int scoreCount, loop;
		ScoreDAO score;
		Set<ScoreDAO> scores;
		
		scores = new HashSet<ScoreDAO>();
		scoreCount = 0;
		
		// create eagles...
		for (loop = 0; loop < aNumEagles; loop++) {
			score = createEagle(aScorecard, aHoles[scoreCount]);
			scores.add(score);
			score.save();
			scoreCount++;
		}
		// create birdies...
		for (loop = 0; loop < aNumBirdies; loop++) {
			score = createBirdie(aScorecard, aHoles[scoreCount]);
			scores.add(score);
			score.save();
			scoreCount++;
		}
		// create pars...
		for (loop = 0; loop < aNumPars; loop++) {
			score = createPar(aScorecard, aHoles[scoreCount]);
			scores.add(score);
			score.save();
			scoreCount++;
		}
		// create bogeys...
		for (loop = 0; loop < aNumBogeys; loop++) {
			score = createBogey(aScorecard, aHoles[scoreCount]);
			scores.add(score);
			score.save();
			scoreCount++;
		}
		// create double-bogeys...
		for (loop = 0; loop < aNumDblBogeys; loop++) {
			score = createDblBogey(aScorecard, aHoles[scoreCount]);
			scores.add(score);
			score.save();
			scoreCount++;
		}
		// create triple-bogeys...
		for (loop = 0; loop < aNumTplBogeys; loop++) {
			score = createTplBogey(aScorecard, aHoles[scoreCount]);
			scores.add(score);
			score.save();
			scoreCount++;
		}
		aScorecard.setScores(scores);
	}
	
	/**
	 * Creates a triple-bogey score object
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreDAO createTplBogey(ScorecardDAO aScorecard, 
			HoleDAO aHole) {
		
		int score;
		
		score = aHole.getPar() + 3;
		return createScore(aScorecard, aHole, score, 
				RandomUtils.nextInt(5),
				getRandomClubUsedOffTee(aHole), 
				Constants.GIR_NO_GIR,
				RandomUtils.nextBoolean() ? 
					Constants.APPROACH_SHOT_DISTANCE_LONG : 
					Constants.APPROACH_SHOT_DISTANCE_SHORT,
					RandomUtils.nextBoolean() ? 
							Constants.APPROACH_SHOT_LINE_LEFT : 
							Constants.APPROACH_SHOT_LINE_RIGHT,
				aHole.getPar() >=4 ? 200+RandomUtils.nextInt(125) :
						100+RandomUtils.nextInt(115),
				RandomUtils.nextBoolean() ? 
						Constants.RIGHT_ROUGH_ID : Constants.LEFT_ROUGH_ID,
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean());
	}
	
	/**
	 * Returns a random GolfClubDAO based on the hole-par
	 * @param aHole
	 * @return
	 */
	private static final int getRandomClubUsedOffTee(HoleDAO aHole) {
		
		int par;
		
		par = aHole.getPar();
		if (par >= 4) {
			// randomly pick a 2-iron, 3-wd or 1-wd...
			return PAR_4_CLUB_IDS[RandomUtils.nextInt(
					PAR_4_CLUB_IDS.length)];
		} else {
			// randomly pick any iron above pitching-wedge...
			return PAR_3_CLUB_IDS[RandomUtils.nextInt(
					PAR_3_CLUB_IDS.length)];
		}
	}
	
	/**
	 * Creates a double-bogey score object
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreDAO createDblBogey(ScorecardDAO aScorecard, 
			HoleDAO aHole) {
		return createScore(aScorecard, aHole, aHole.getPar() + 2, 
				RandomUtils.nextInt(5),
				getRandomClubUsedOffTee(aHole), 
				Constants.NO_GIR_HIT_ID,
				RandomUtils.nextBoolean() ? 
					Constants.APPROACH_SHOT_DISTANCE_LONG : 
					Constants.APPROACH_SHOT_DISTANCE_SHORT,
					RandomUtils.nextBoolean() ? 
							Constants.APPROACH_SHOT_LINE_LEFT : 
							Constants.APPROACH_SHOT_LINE_RIGHT,
				aHole.getPar() >=4 ? 200+RandomUtils.nextInt(125) :
						100+RandomUtils.nextInt(115),
				RandomUtils.nextBoolean() ? 
						Constants.RIGHT_ROUGH_ID : Constants.LEFT_ROUGH_ID,
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean());
	}

	/**
	 * Creates a bogey score object
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreDAO createBogey(ScorecardDAO aScorecard, 
			HoleDAO aHole) {
		return createScore(aScorecard, aHole, aHole.getPar() + 1, 
				RandomUtils.nextInt(4),
				getRandomClubUsedOffTee(aHole), 
				RandomUtils.nextBoolean() ? Constants.GIR_GREATER_THAN_15_FEET : 
					Constants.NO_GIR_HIT_ID,
				RandomUtils.nextBoolean() ? 
					Constants.APPROACH_SHOT_DISTANCE_LONG : 
					Constants.APPROACH_SHOT_DISTANCE_SHORT,
					RandomUtils.nextBoolean() ? 
							Constants.APPROACH_SHOT_LINE_LEFT : 
							Constants.APPROACH_SHOT_LINE_RIGHT,
				aHole.getPar() >=4 ? 200+RandomUtils.nextInt(125) :
						100+RandomUtils.nextInt(115),
				RandomUtils.nextBoolean() ? 
						Constants.FAIRWAY_HIT_ID : Constants.LEFT_ROUGH_ID,
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean());
	}	
	
	/**
	 * Creates a par score object
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreDAO createPar(ScorecardDAO aScorecard, 
			HoleDAO aHole) {
		return createScore(aScorecard, aHole, aHole.getPar(), 
				RandomUtils.nextInt(3),
				getRandomClubUsedOffTee(aHole), 
				RandomUtils.nextBoolean() ? Constants.GIR_GREATER_THAN_15_FEET : 
					Constants.NO_GIR_HIT_ID,
				RandomUtils.nextBoolean() ? 
					Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH : 
					Constants.APPROACH_SHOT_DISTANCE_SHORT,
					RandomUtils.nextBoolean() ? 
							Constants.APPROACH_SHOT_LINE_LEFT : 
							Constants.APPROACH_SHOT_LINE_GREEN,
				aHole.getPar() >=4 ? 200+RandomUtils.nextInt(125) :
						100+RandomUtils.nextInt(115),
				RandomUtils.nextBoolean() ? 
						Constants.FAIRWAY_HIT_ID : Constants.LEFT_ROUGH_ID,
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean());
	}
	
	/**
	 * Creates a birdie score object
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreDAO createBirdie(ScorecardDAO aScorecard, 
			HoleDAO aHole) {
		return createScore(aScorecard, aHole, aHole.getPar() - 1, 
				RandomUtils.nextInt(2),
				getRandomClubUsedOffTee(aHole), 
				RandomUtils.nextBoolean() ? Constants.GIR_GREATER_THAN_15_FEET : 
					Constants.GIR_LESS_THAN_15_FEET,
				Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH,
				Constants.APPROACH_SHOT_LINE_GREEN,
				aHole.getPar() >=4 ? 200+RandomUtils.nextInt(125) :
						100+RandomUtils.nextInt(115),
				Constants.FAIRWAY_HIT_ID,
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean());
	}
	
	/**
	 * Creates an eagle score object
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreDAO createEagle(ScorecardDAO aScorecard, 
			HoleDAO aHole) {
		return createScore(aScorecard, aHole, aHole.getPar() - 2, 
				RandomUtils.nextInt(2),
				getRandomClubUsedOffTee(aHole), 
				Constants.GIR_LESS_THAN_15_FEET,
				Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH,
				Constants.APPROACH_SHOT_LINE_GREEN,
				aHole.getPar() >=4 ? 200+RandomUtils.nextInt(125) :
						100+RandomUtils.nextInt(115),
				Constants.FAIRWAY_HIT_ID,
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean(),
						RandomUtils.nextBoolean());
	}
	
	/**
	 * Creates a score object using the inputted parameters
	 * @param aScorecard
	 * @param aHole
	 * @param aScore
	 * @param aNumPutts
	 * @return
	 */
	private static final ScoreDAO createScore(ScorecardDAO aScorecard, 
			HoleDAO aHole, int aScore, int aNumPutts, 
			int aClubUsedOffTeeId, int aGIRId,
			int aAPSDistanceId, int aAPSLineId,
			int aDriveDistance, int aTeeShotAccuracyId,
			boolean aUpDownAttempt, boolean aUpDownConvert,
			boolean aSandSaveAttempt, boolean aSandSaveConvert) {
		
		ScoreDAO score;
		GolfClubDAO golfClubDAO;
		GreenInRegulationDAO girDAO;
		ApproachShotDistanceDAO apsDistanceDAO;
		ApproachShotLineDAO apsLineDAO;
		TeeShotAccuracyDAO teeShotAccuracyDAO;
		
		golfClubDAO = BeanFactory.newGolfClubDAO();
		girDAO = BeanFactory.newGreenInRegulationDAO();
		apsDistanceDAO = BeanFactory.newApproachShotDistanceDAO();
		apsLineDAO = BeanFactory.newApproachShotLineDAO();
		teeShotAccuracyDAO = BeanFactory.newTeeShotAccuracyDAO();
		golfClubDAO.setId(aClubUsedOffTeeId);
		girDAO.setId(aGIRId);
		apsDistanceDAO.setId(aAPSDistanceId);
		apsLineDAO.setId(aAPSLineId);
		teeShotAccuracyDAO.setId(aTeeShotAccuracyId);
		score = BeanFactory.newScoreDAO();
		score.setScore(aScore);
		score.setNumPutts(aNumPutts);
		score.setId(createScoreId(aScorecard, aHole));	
		score.setHole(aHole);
		score.setGolfClub(golfClubDAO);
		score.setGreenInRegulation(girDAO);
		score.setApproachShotDistance(apsDistanceDAO);
		score.setApproachShotLine(apsLineDAO);
		score.setDriveDistance(aDriveDistance);
		score.setTeeShotAccuracy(teeShotAccuracyDAO);
		score.setUpAndDownAttempted(aUpDownAttempt);
		score.setIfUpAndDownConversion(aUpDownAttempt ? aUpDownConvert : false);
		score.setSandSaveAttempted(aSandSaveAttempt);
		score.setIfSandSaveConversion(aSandSaveAttempt ? aSandSaveConvert : 
			false);
		return score;
	}
	
	/**
	 * Returns a ScoreId object based on the inputted parameters
	 * @param aScorecard
	 * @param aHole
	 * @return
	 */
	private static final ScoreId createScoreId(ScorecardDAO aScorecard, HoleDAO aHole) {
		
		ScoreId scoreId;
		scoreId = new ScoreId();
		scoreId.setHoleId((Integer)aHole.getId());
		scoreId.setScorecardId((Integer)aScorecard.getId());
		return scoreId;	
	}
	
	/**
	 * Saves the hole-scores
	 * @param aScorecard
	 * @param aHandicap
	 * @param aSession
	 */
	private static final void saveScores(ScorecardDAO aScorecard, int aHandicap,
			HoleDAO aHoles[]) {
		
		int numBirdies, numEagles, numDblBogeys, numTplBogeys, numBogeys, numPars;
		int handicapVariance, handicapToPlayTo;
		int plusMinus, numRemainingHoles;
		
		handicapVariance = RandomUtils.nextInt(11);
		handicapToPlayTo = RandomUtils.nextBoolean() ? 
				(aHandicap + handicapVariance) : (aHandicap - handicapVariance);
				
		numEagles = RandomUtils.nextInt(getMaxNumEagles(handicapToPlayTo) + 1);
		numBirdies = RandomUtils.nextInt(getMaxNumBirdies(
				handicapToPlayTo) + 1);
		numDblBogeys = RandomUtils.nextInt(getMaxNumDblBogeys(
				handicapToPlayTo) + 1);
		numTplBogeys = RandomUtils.nextInt(getMaxNumTplBogeys(
				handicapToPlayTo) + 1);
		plusMinus = (numDblBogeys * 2) + (numTplBogeys * 3) + (numBirdies * -1) 
			+ (numEagles * -2);
		numRemainingHoles = 18 - (numDblBogeys + numTplBogeys + numBirdies + 
				numEagles);
		numBogeys = 0;
		if (plusMinus < 0) {
			numBogeys = plusMinus * -1;
			if (numRemainingHoles + numBogeys >= 18) {
				numBogeys = numRemainingHoles;
				numPars = 0;
			} else {
				numPars = 18 - (numDblBogeys + numTplBogeys + numBirdies + 
						numEagles + numBogeys);
			}
		} else {
			numBogeys = 0;
			numPars = numRemainingHoles;
		}
		saveScores(aScorecard, aHoles, numEagles, numBirdies, numPars,
				numBogeys, numDblBogeys, numTplBogeys);
	}

	/**
	 * Returns the maximum number of triple bogeys a player with the inputted 
	 * handicap will get during a round of 18-holes
	 * @param aHandicap
	 * @return
	 */
	private static final int getMaxNumTplBogeys(int aHandicap) {
		 
		int maxNumEagles;
		
		maxNumEagles = 0;
		if (aHandicap <= 0 || aHandicap == 5) maxNumEagles = 1;
		else if (aHandicap <= 10) maxNumEagles = 2;
		else if (aHandicap <= 15) maxNumEagles = 3;
		else if (aHandicap <= 20) maxNumEagles = 4;
		else if (aHandicap <= 25) maxNumEagles = 5;
		else if (aHandicap <= 30) maxNumEagles = 6;
		else if (aHandicap > 31)  maxNumEagles = 7;
		return maxNumEagles;
	}	
	
	/**
	 * Returns the maximum number of double bogeys a player with the inputted 
	 * handicap will get during a round of 18-holes
	 * @param aHandicap
	 * @return
	 */
	private static final int getMaxNumDblBogeys(int aHandicap) {
		 
		int maxNumEagles;
		
		maxNumEagles = 0;
		if (aHandicap <= 0 || aHandicap == 5) maxNumEagles = 4;
		else if (aHandicap <= 10) maxNumEagles = 5;
		else if (aHandicap <= 15) maxNumEagles = 6;
		else if (aHandicap <= 20) maxNumEagles = 7;
		else if (aHandicap <= 25) maxNumEagles = 8;
		else if (aHandicap <= 30) maxNumEagles = 9;
		else if (aHandicap > 31)  maxNumEagles = 10;
		return maxNumEagles;
	}
	
	/**
	 * Returns the maximum number of eagles a player with the inputted handicap
	 * will get during a round of 18-holes
	 * @param aHandicap
	 * @return
	 */
	private static final int getMaxNumEagles(int aHandicap) {
		 
		int maxNumEagles;
		
		maxNumEagles = 0;
		if (aHandicap <= 0 || aHandicap == 5) maxNumEagles = 2;
		else if (aHandicap <= 10) maxNumEagles = 1;
		else if (aHandicap <= 15) maxNumEagles = 1;
		else if (aHandicap <= 20) maxNumEagles = 0;
		else if (aHandicap <= 25) maxNumEagles = 0;
		else if (aHandicap <= 30) maxNumEagles = 0;
		else if (aHandicap > 31)  maxNumEagles = 0;
		return maxNumEagles;
	}
	
	/**
	 * Returns the maximum number of birdies a player with the inputted handicap
	 * will get during a round of 18-holes
	 * @param aHandicap
	 * @return
	 */
	private static final int getMaxNumBirdies(int aHandicap) {
		 
		int maxNumBirdies;
		
		maxNumBirdies = 0;
		if (aHandicap <= 0 || aHandicap == 5) maxNumBirdies = 5;
		else if (aHandicap <= 10) maxNumBirdies = 4;
		else if (aHandicap <= 15) maxNumBirdies = 3;
		else if (aHandicap <= 20) maxNumBirdies = 2;
		else if (aHandicap <= 25) maxNumBirdies = 1;
		else if (aHandicap <= 30) maxNumBirdies = 0;
		else if (aHandicap > 31)  maxNumBirdies = 0;
		return maxNumBirdies;
	}
	
	/**
	 * Returns a random eye-wear type
	 * @return
	 */
	private static final EyeWearDAO getRandomEyeWearType() {
		
		EyeWearDAO eyeWearType;
		
		eyeWearType = BeanFactory.newEyeWearDAO();
		eyeWearType.setId(RandomUtils.nextInt(
				Constants.SUN_GLASSES_EYE_WEAR_TYPE + 1));
		return eyeWearType;
	}

	/**
	 * Returns a random pant-wear type
	 * @return
	 */
	private static final PantWearDAO getRandomPantWearType() {
		
		PantWearDAO pantWearType;
		
		pantWearType = BeanFactory.newPantWearDAO();
		pantWearType.setId(RandomUtils.nextInt(
				Constants.KNICKERS_PANT_WEAR_TYPE + 1));
		return pantWearType;
	}	
	
	/**
	 * Returns a random head-wear type
	 * @return
	 */
	private static final HeadWearDAO getRandomHeadWearType() {
		
		HeadWearDAO headWearType;
		
		headWearType = BeanFactory.newHeadWearDAO();
		headWearType.setId(RandomUtils.nextInt(
				Constants.CAP_HEAD_WEAR_TYPE + 1));
		return headWearType;
	}
	
	/**
	 * Returns a random weather condition
	 * @return
	 */
	private static final WeatherConditionDAO getRandomWeatherCondition() {
		
		WeatherConditionDAO cond;
		
		cond = BeanFactory.newWeatherConditionDAO();
		cond.setId(RandomUtils.nextInt(Constants.HOT_HUMID_WEATHER + 1));
		return cond;
	}
	
	/**
	 * Returns the set of holes for the given tee.
	 * @param aTee
	 * @return
	 */
	private final HoleDAO[] getHoles(TeeDAO aTee) {
		
		HoleDAO holes[];
		String key;
		
		key = getHolesKey(aTee);
		holes = COURSES_WITH_HOLES.get(key);
		if (holes == null) {
			holes = createHoles(aTee);
		}
		return holes;
	}
	
	/**
	 * Creates the holes for the inputted tee and returns them (also creates the
	 * holes for the other tees defined on the associated course)
	 * @param aTee
	 * @return
	 */
	private final HoleDAO[] createHoles(TeeDAO aTee) {
		
		CourseDAO course;
		Set<TeeDAO> tees;
		HoleDAO holes[], holesToReturn[];
		String key;
		
		key = getHolesKey(aTee);
		course = aTee.getCourse();
		course.load();
		tees = course.getTees();
		holesToReturn = null;
		for (TeeDAO tee : tees) {
			holes = createTeeHoles(tee);
			if (getHolesKey(tee).equals(key)) {
				holesToReturn = holes;
			}
		}
		return holesToReturn;
	}
	
	/**
	 * Creates and persists the course holes.
	 * @param aTeeId
	 * @param aSession
	 * @return
	 */
	private static final HoleDAO[] createTeeHoles(TeeDAO aTee) {
		
		HoleDAO hole;
		List<HoleDAO> holesList;
		HoleDAO holes[];
		int loop;
		
		holesList = new ArrayList<HoleDAO>();
		for (loop = 0; loop < NUM_HOLES_TO_CREATE; loop++) {
			hole = BeanFactory.newHoleDAO();
			hole.setTee(aTee);
			hole.setNumber(loop + 1);
			hole.setPar(PAR_VALUES[RandomUtils.nextInt(PAR_VALUES.length)]);
			holesList.add(hole);
			hole.save();
		}
		holes = holesList.toArray(new HoleDAO[holesList.size()]);
		COURSES_WITH_HOLES.put(getHolesKey(aTee), holes);
		return holes;
	}
	
	/**
	 * Returns a key associated with the inputted tee
	 * @param aTee
	 * @return
	 */
	private static final String getHolesKey(TeeDAO aTee) {
		
		TeeId teeId;
		
		teeId = (TeeId)aTee.getId();
		return teeId.getCourseId() + "_" + teeId.getTeeNameId(); 
	}
	
	/**
	 * Creates a random birthdate
	 * @return
	 */
	private static final Date getRandomBirthdate() {
		
		Calendar calendar;
		int currentYear, numYearsToSubstract;
		
		calendar = Calendar.getInstance();
		
		// subtract between 15 and 70 years from date...
		currentYear = calendar.get(Calendar.YEAR);
		numYearsToSubstract = RandomUtils.nextInt(70);
		if (numYearsToSubstract <= 16) {
			numYearsToSubstract += 16;
		}
		calendar.set(Calendar.YEAR, currentYear - numYearsToSubstract);
		
		// set random month...
		calendar.set(Calendar.MONTH, RandomUtils.nextInt(12));
		
		// set random date of month...
		calendar.set(Calendar.DAY_OF_MONTH, RandomUtils.nextInt(29));
		return calendar.getTime();
	}
	
	/**
	 * Application entry point
	 * @param aArgs
	 */
	public static void main(String aArgs[]) {
		
		Thread thread;
		
		// create and start the thread...
		thread = new Thread(BeanFactory.newDataLoadTool());
		thread.start();
	}
}
