package com.ubaid.app.model.schedule1_1.thresholdDetection;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ubaid.app.model.SportUtil;
import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.asianhandicap.AssianHandicapRawData;
import com.ubaid.app.model.logic.AssianHandicapLogic;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.OverUnderLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.overunder.OverUnderRawData;
import com.ubaid.app.model.schedule1_1.BettingType;
import com.ubaid.app.model.schedule1_1.Outcome;

/**
 * outcomes contain all 47 and 48 betting type outcomes
 * @author ubaid
 *
 */
public class TrackedMatches extends Entity {

	private long matchId;
	private String leagueName;
	private String matchName;
	private String homeTeam;
	private String awayTeam;
	private String sportName;
	private List<Outcome> outcomes;
	
	/**********************Do not delete*******************************/
	public Outcome getOutcome()
	{
		Outcome outcome = null;
		
		if(getOutcomes() != null)
		{
			int sizeOfOutcomes = getOutcomes().size();
			if(sizeOfOutcomes > 0)
				outcome = getOutcomes().get(0);
		}
		
		return outcome;
	}
	
	
	public String getMatchName() {
		if(matchName == null)
			return getHomeTeam() + " VS " + getAwayTeam();
		return matchName;
	}

	/******************************************************************/
	

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}


	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public synchronized List<Outcome> getOutcomes()
	{
		return outcomes;
	}

	public synchronized void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	public static class Builder {
		private long matchId;
		private String leagueName;
		private String matchName;
		private String homeTeam;
		private String awayTeam;
		private String sportName;

		public Builder matchId(long matchId) {
			this.matchId = matchId;
			return this;
		}

		public Builder leagueName(String leagueName) {
			this.leagueName = leagueName;
			return this;
		}

		public Builder matchName(String matchName) {
			this.matchName = matchName;
			return this;
		}

		public Builder homeTeam(String homeTeam) {
			this.homeTeam = homeTeam;
			return this;
		}

		public Builder awayTeam(String awayTeam) {
			this.awayTeam = awayTeam;
			return this;
		}

		public Builder sportName(String sportName) {
			this.sportName = sportName;
			return this;
		}

		public TrackedMatches build() {
			return new TrackedMatches(this);
		}
	}

	private TrackedMatches(Builder builder) {
		this.matchId = builder.matchId;
		this.leagueName = builder.leagueName;
		this.matchName = builder.matchName;
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.sportName = builder.sportName;
	}
	
	/**
	 * this method retrieve the outcomes[47 and 48] from the 
	 * database
	 */
	public void populateOutcomes()
	{
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(new _Populate());
		service.shutdown();
		try
		{
			service.awaitTermination(10, TimeUnit.SECONDS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addOutcome(Outcome outcome)
	{
		getOutcomes().add(outcome);
	}
	
	public void removeOutcome(Outcome outcome)
	{
		getOutcomes().remove(outcome);
	}
	
	private class _Populate implements Runnable
	{

		List<Entity> assianHandicapRawData;
		List<Entity> overUnderRawData;

		@Override
		public void run()
		{
			try
			{
				//getting logic
				Logic ahLogic = new AssianHandicapLogic();
				Logic ouLogic = new OverUnderLogic();
				
				//getting sport util
				SportUtil su = SportUtilFactory.getSportUtil();
				
				ExecutorService service = Executors.newFixedThreadPool(1);
				
				
				service.execute(new Runnable()
				{
					
					@Override
					public void run()
					{
						//getting all assian handicap raw data of this match
						int eventPartId = su.getEventPartId(sportName, 48);
						if(eventPartId != -1)
							assianHandicapRawData = ahLogic.getAll(matchId, eventPartId);						
					}
				});

				service.execute(new Runnable()
				{
					
					@Override
					public void run()
					{
						//getting all overUnder raw data
						int eventPartId = su.getEventPartId(sportName, 47);
						if(eventPartId != -1)
							overUnderRawData = ouLogic.getAll(matchId, eventPartId);
						
					}
				});

				service.shutdown();
				
				try
				{
					service.awaitTermination(1, TimeUnit.MINUTES);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				//creating outcome
				Outcome prototyep = new Outcome.Builder()
									.matchId(matchId)
									.homeTeam(homeTeam)
									.awayTeam(awayTeam)
									.matchName(matchName)
									.leagueName(leagueName)
									.sportName(sportName)
									.build();
				
				List<Outcome> outcomes = new LinkedList<Outcome>();
				
				
				for(Entity entity : assianHandicapRawData)
				{
					AssianHandicapRawData rawData = (AssianHandicapRawData) entity;
					Outcome outcome = (Outcome) prototyep.clone();
					outcome.setOdds(rawData.getOdds());
					outcome.setThreshold(rawData.getThreshold());
					outcome.setId(rawData.getOutcomeId());
					outcome.setParticipant(rawData.getParticipant());
					outcome.setBettingType(BettingType.AssianHandicap);
					outcomes.add(outcome);
				}
				
				for(Entity entity : overUnderRawData)
				{
					OverUnderRawData rawData = (OverUnderRawData) entity;
					Outcome outcome = (Outcome) prototyep.clone();
					outcome.setOdds(rawData.getOdds());
					outcome.setThreshold(rawData.getThreshold());
					outcome.setId(rawData.getOutcomeId());
					outcome.setParticipant(rawData.getTypeId() == 14 ? "Under" : "Over");
					outcome.setBettingType(BettingType.OverUnder);
					outcomes.add(outcome);
				}

				setOutcomes(outcomes);
			}
			catch(NullPointerException exp)
			{
				System.out.println(sportName + " is not suport threshold detection");
			}
			catch(CloneNotSupportedException exp)
			{
				exp.printStackTrace();
			}
			finally {
			}
			
		}		
	}
}
