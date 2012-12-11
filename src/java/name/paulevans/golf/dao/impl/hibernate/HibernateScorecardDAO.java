package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.EyeWear;
import gen.hibernate.name.paulevans.golf.HeadWear;
import gen.hibernate.name.paulevans.golf.Hole;
import gen.hibernate.name.paulevans.golf.PantWear;
import gen.hibernate.name.paulevans.golf.Player;
import gen.hibernate.name.paulevans.golf.Score;
import gen.hibernate.name.paulevans.golf.Scorecard;
import gen.hibernate.name.paulevans.golf.Tee;
import gen.hibernate.name.paulevans.golf.WeatherCondition;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.WeatherConditionDAO;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate tee dao
 * @author Paul
 *
 */
public class HibernateScorecardDAO extends HibernateDAOAdapter 
implements ScorecardDAO {

	// delegate object - generated Hibernate type...
	private Scorecard scorecard;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateScorecardDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aTee
	 */
	public HibernateScorecardDAO(Scorecard aScorecardDelegate) {
		scorecard = aScorecardDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aScorecardDelegate) {
		scorecard = (Scorecard)aScorecardDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return scorecard;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return scorecard.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		scorecard.setId((Integer)aId);
	}
	
	/**
	 * Returns the starting hole (only useful if this is a nine-hole round)
	 * @return
	 */
	public HoleDAO getStartingHole() {
		
		Set<ScoreDAO> scores;
		HoleDAO hole, startingHole;
		ScoreDAO scoresArray[];
	
		scores = this.getScores();
		scoresArray = scores.toArray(new ScoreDAO[scores.size()]);
		if (scoresArray.length > 0) {
			startingHole = scoresArray[0].getHole();
			for (ScoreDAO score : scoresArray) {
				hole = score.getHole();
				if (hole.getNumber() < startingHole.getNumber()) {
					startingHole = hole;
				}
			}
		} else { // only relevant for non-stats, 9-hole rounds...
			startingHole = BeanFactory.newHoleDAO();
			startingHole.setNumber(scorecard.getStartingHole());
		}
		return startingHole;
	}
	
	/**
	 * Sets the starting hole (only useful if this is a 9-hole round)
	 */
	public void setStartingHole(HoleDAO aHole) {
		scorecard.setStartingHole(aHole.getNumber());
	}
	
	/**
	 * Get the player
	 * @return
	 */
	public PlayerDAO getPlayer() {
		return new HibernatePlayerDAO(scorecard.getPlayer());
	}

	/**
	 * Set the player
	 * @param aPlayer
	 */
	public void setPlayer(PlayerDAO aPlayer) {
		scorecard.setPlayer((Player)aPlayer.getDelegate());
	}

	/**
	 * Get the weather condition
	 * @return
	 */
	public WeatherConditionDAO getWeatherCondition() {
		return new HibernateWeatherConditionDAO(
				scorecard.getWeatherCondition());
	}

	/**
	 * Set the weather condition
	 * @param aWeatherCondition
	 */
	public void setWeatherCondition(WeatherConditionDAO aWeatherCondition) {
		scorecard.setWeatherCondition((WeatherCondition)
				aWeatherCondition.getDelegate());
	}

	/**
	 * Get the tee played
	 * @return
	 */
	public TeeDAO getTee() {
		return new HibernateTeeDAO(scorecard.getTee());
	}

	/**
	 * Set the tee played
	 * @param aTee
	 */
	public void setTee(TeeDAO aTee) {
		scorecard.setTee((Tee)aTee.getDelegate());
	}

	/**
	 * Get the eye-wear used
	 * @return
	 */
	public EyeWearDAO getEyeWear() {
		return new HibernateEyeWearDAO(scorecard.getEyeWear());
	}

	/**
	 * Set the eye-wear used
	 * @param aEyeWear
	 */
	public void setEyeWear(EyeWearDAO aEyeWear) {
		scorecard.setEyeWear((EyeWear)aEyeWear.getDelegate());
	}

	/**
	 * Get the pant-wear used
	 * @return
	 */
	public PantWearDAO getPantWear() {
		return new HibernatePantWearDAO(scorecard.getPantWear());
	}

	/**
	 * Set the pant-wear used
	 * @param aPantWear
	 */
	public void setPantWear(PantWearDAO aPantWear) {
		scorecard.setPantWear((PantWear)aPantWear.getDelegate());
	}

	/**
	 * Get the head-wear used
	 * @return
	 */
	public HeadWearDAO getHeadWear() {
		return new HibernateHeadWearDAO(scorecard.getHeadWear());
	}

	/**
	 * Set the head-wear used
	 * @param aHeadWear
	 */
	public void setHeadWear(HeadWearDAO aHeadWear) {
		scorecard.setHeadWear((HeadWear)aHeadWear.getDelegate());
	}

	/**
	 * Get the date played
	 * @return
	 */
	public Date getDate() {
		return scorecard.getDate();
	}

	/**
	 * Set the date played
	 * @param aDate
	 */
	public void setDate(Date aDate) {
		scorecard.setDate(aDate);
	}

	/**
	 * Get if long-sleeves were worn
	 * @return
	 */
	public boolean getWoreLongSleeves() {
		return scorecard.getWoreLongSleeves();
	}

	/**
	 * Set if long-sleeves were worn
	 * @param aWoreLongSleeves
	 */
	public void setWoreLongSleeves(boolean aWoreLongSleeves) {
		scorecard.setWoreLongSleeves(aWoreLongSleeves);
	}
	
	/**
	 * Returns if a caddie was used for this round
	 * @return
	 */
	public boolean getUsedCaddie() {
		return scorecard.isUsedCaddie();
	}
	
	/**
	 * Sets if a caddie was used for this round
	 * @param aUsedCaddie
	 */
	public void setUsedCaddie(boolean aUsedCaddie) {
		scorecard.setUsedCaddie(aUsedCaddie);
	}
	
	/**
	 * Returns if the player used a cart for this round
	 * @return
	 */
	public boolean getUsedCart() {
		return scorecard.isUsedCart();
	}
	
	/**
	 * Sets if the player used a cart for this round
	 * @param aUsedCart
	 */
	public void setUsedCart(boolean aUsedCart) {
		scorecard.setUsedCart(aUsedCart);
	}

	/**
	 * Get is a vest was worn
	 * @return
	 */
	public boolean getWoreVest() {
		return scorecard.getWoreVest();
	}

	/**
	 * Set if a vest was worn
	 * @param aWoreVest
	 */
	public void setWoreVest(boolean aWoreVest) {
		scorecard.setWoreVest(aWoreVest);
	}

	/**
	 * Get the journal notes
	 * @return
	 */
	public String getJournalNotes() {
		return scorecard.getJournalNotes();
	}

	/**
	 * Set the journal notes
	 * @param aJournalNotes
	 */
	public void setJournalNotes(String aJournalNotes) {
		scorecard.setJournalNotes(aJournalNotes);
	}

	/**
	 * Get the attestor
	 * @return
	 */
	public String getAttestedBy() {
		return scorecard.getAttestedBy();
	}

	/**
	 * Set the attestor; null if there is no attestor
	 * @param aAttestedBy
	 */
	public void setAttestedBy(String aAttestedBy) {
		scorecard.setAttestedBy(aAttestedBy);
	}

	/**
	 * Get the scorer; null if scored by the player
	 * @return
	 */
	public String getScorer() {
		return scorecard.getScorer();
	}

	/**
	 * Set the scorer
	 * @param aScorer
	 */
	public void setScorer(String aScorer) {
		scorecard.setScorer(aScorer);
	}
	
	/**
	 * Returns if this score was made at the player's home course or not
	 * @return
	 */
	public boolean getIsHome() {
		return scorecard.isIsHome();
	}
	
	/**
	 * Sets if this score was made at the player's home course or not
	 * @param aIsHome
	 */
	public void setIsHome(boolean aIsHome) {
		scorecard.setIsHome(aIsHome);
	}
	
	/**
	 * Returns if this score was played during a tournament or not
	 * @return
	 */
	public boolean getIsTournament() {
		return scorecard.isIsTournament();
	}
	
	/**
	 * Sets if this score was played during a tournament or not
	 *
	 */
	public void setIsTournament(boolean aIsTournament) {
		scorecard.setIsTournament(aIsTournament);
	}
	
	/**
	 * Returns if the player collected the 'club used off tee' stat
	 * @return
	 */
    public boolean isCollectClubUsedOffTee() {
    	return scorecard.isCollectClubUsedOffTee();
    }
    
    /**
     * Sets if the player collected the 'club used off tee' stat
     * @param aCollectClubUsedOffTee
     */
    public void setCollectClubUsedOffTee(boolean aCollectClubUsedOffTee) {
    	scorecard.setCollectClubUsedOffTee(aCollectClubUsedOffTee);
    }
    
    /**
     * Returns if the player collected the 'tee shot distance' stat
     * @return
     */
    public boolean isCollectTeeShotDistance() {
    	return scorecard.isCollectTeeShotDistance();
    }
    
    /**
     * Sets if the player collected the 'tee shot distance' stat
     * @param aCollectTeeShotDistance
     */
    public void setCollectTeeShotDistance(boolean aCollectTeeShotDistance) {
    	scorecard.setCollectTeeShotDistance(aCollectTeeShotDistance);
    }
    
    /**
     * Returns if the player collected the 'num putts' stat
     * @return
     */
    public boolean isCollectNumPutts() {
    	return scorecard.isCollectNumPutts();
    }
    
    /**
     * Sets if the player collected the 'num putts' stat
     * @param aCollectNumPutts
     */
    public void setCollectNumPutts(boolean aCollectNumPutts) {
    	scorecard.setCollectNumPutts(aCollectNumPutts);
    }
    
    /**
     * Returns if the player collected the 'fairway hit' stat
     * @return
     */
    public boolean isCollectFairwayHit() {
    	return scorecard.isCollectFairwayHit();
    }
    
    /**
     * Sets if the player collected the 'fairway hit' stat
     * @param aCollectFairwayHit
     */
    public void setCollectFairwayHit(boolean aCollectFairwayHit) {
    	scorecard.setCollectFairwayHit(aCollectFairwayHit);
    }
    
    /**
     * Returns if the player collected the 'GIR' stat
     * @return
     */
    public boolean isCollectGir() {
    	return scorecard.isCollectGir();
    }
    
    /**
     * Sets if the player collected the 'GIR' stat
     * @param aCollectGir
     */
    public void setCollectGir(boolean aCollectGir) {
    	scorecard.setCollectGir(aCollectGir);
    }
    
    /**
     * Returns if the player collected the 'approach shot line' stat
     * @return
     */
    public boolean isCollectApproachShotLine() {
    	return scorecard.isCollectApproachShotLine();
    }
    
    /**
     * Sets if the player collected the 'approach shot line' stat
     * @param aCollectApproachShotLine
     */
    public void setCollectApproachShotLine(boolean aCollectApproachShotLine) {
    	scorecard.setCollectApproachShotLine(aCollectApproachShotLine);
    }
    
    /**
     * Returns if the player collected the 'approach shot distance' 
     * stat
     * @return
     */
    public boolean isCollectApproachShotDistance() {
    	return scorecard.isCollectApproachShotDistance();
    }
    
    /**
     * Sets if the player collected the 'approach shot distance' stat
     * @param aCollectApproachShotDistance
     */
    public void setCollectApproachShotDistance(
    		boolean aCollectApproachShotDistance) {
    	scorecard.setCollectApproachShotDistance(aCollectApproachShotDistance);
    }
    
    /**
     * Returns if the player collected the 'sand save' stat
     * @return
     */
    public boolean isCollectSandSave() {
    	return scorecard.isCollectSandSave();
    }
    
    /**
     * Sets if the player collected the 'sand save' stat
     * @param aCollectSandSave
     */
    public void setCollectSandSave(boolean aCollectSandSave) {
    	scorecard.setCollectSandSave(aCollectSandSave);
    }
    
    /**
     * Returns if the player collected the 'up and down' stat
     * @return
     */
    public boolean isCollectUpDown() {
    	return scorecard.isCollectUpDown();
    }
    
    /**
     * Sets if the player collected the 'up and down' stat
     * @param aCollectUpDown
     */
    public void setCollectUpDown(boolean aCollectUpDown) {
    	scorecard.setCollectUpDown(aCollectUpDown);
    }	
    
    /**
     * Returns the date entered
     * @return
     */
    public Date getDateCreated() {
    	return scorecard.getDateCreated();
    }
    
    /**
     * Sets the date entered
     * @param aDateCreated
     */
    public void setDateCreated(Date aDateCreated) {
    	scorecard.setDateCreated(aDateCreated);
    }
    
    /**
     * Returns the last update date
     * @return
     */
    public Date getLastUpdateDate() {
    	return scorecard.getLastUpdateDate();
    }
    
    /**
     * Sets the last update date
     * @param aLastUpdateDate
     */
    public void setLastUpdateDate(Date aLastUpdateDate) {
    	scorecard.setLastUpdateDate(aLastUpdateDate);
    }

	/**
	 * Get the hole-scores
	 * @return
	 */
	public Set<ScoreDAO> getScores() {
		
		Set<Score> scores;
		Set<ScoreDAO> scoreDAOs;
		ScoreDAO scoreDAO;
		
		scores = scorecard.getScores();
		scoreDAOs = new HashSet<ScoreDAO>();
		for (Score score : scores) {
			scoreDAO = BeanFactory.newScoreDAO();
			scoreDAO.setDelegate(score);
			scoreDAOs.add(scoreDAO);
		}
		return scoreDAOs;
	}

	/**
	 * Set the hole-scores
	 * @param aScores
	 */
	public void setScores(Set<ScoreDAO> aScores) {
		
		Set<Score> scores;
		
		scores = new HashSet<Score>();
		for (ScoreDAO scoreDAO : aScores) {
			scores.add((Score)scoreDAO.getDelegate());
		}
		scorecard.setScores(scores);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		scorecard = (Scorecard)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Scorecard scorecard;
				Course course;
				Tee teesPlayed;
				Set<Hole> holes;
				Set<Tee> tees;
				Set<Score> scores;
				
				scorecard = (Scorecard)aSession.load(Scorecard.class, getId());
				Hibernate.initialize(scorecard);
				teesPlayed = scorecard.getTee();
				Hibernate.initialize(teesPlayed);
				Hibernate.initialize(teesPlayed.getTeeName());
				course = teesPlayed.getCourse();
				Hibernate.initialize(course);
				Hibernate.initialize(course.getStateProvince());
				Hibernate.initialize(
						course.getStateProvince().getCountry());
				Hibernate.initialize(tees = course.getTees());
				for (Tee tee : tees) {
					Hibernate.initialize(tee);
					holes = tee.getHoles();
					for (Hole hole : holes) {
						Hibernate.initialize(hole);
						Hibernate.initialize(hole.getTee());
						Hibernate.initialize(hole.getTee().getTeeName());
					}
				}
				Hibernate.initialize(scores = scorecard.getScores());
				for (Score score : scores) {
					Hibernate.initialize(score);
					Hibernate.initialize(score.getApproachShotDistance());
					Hibernate.initialize(score.getApproachShotLine());
					Hibernate.initialize(score.getGolfClub());
					Hibernate.initialize(score.getGreenInRegulation());
					Hibernate.initialize(score.getTeeShotAccuracy());
					Hibernate.initialize(score.getHole());
				}
				return scorecard;
			}
		});
	}
}
