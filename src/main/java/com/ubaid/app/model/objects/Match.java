package com.ubaid.app.model.objects;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.LinkedList;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.OddsLogic;
import com.ubaid.app.model.singleton.HashFunction;

public class Match {
	/*******************************Do not delete*************************/
	private Hashtable<Integer, String> hash = new Hashtable<>();

	public String getHash() {
		return HashFunction.getHash2(Long.toString(id));
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
		hash.put(2, awayTeam);
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
		hash.put(1, homeTeam);
	}

	@Override
	public String toString() {
		return String.format("%s%d\n%s%f%s%f%s%f", "The Match Id is: ", this.getId(), "Home Team Odds: ",
				getHomeTeamOdds(), "Away Team Odds: ", getAwayTeamOdds(), "Draw Odds", getDrawOdds());
	}

	//only for home/draw/away <69 and 112> betting type
	/**
	 * 
	 * @param eventPartId
	 * @param bettingTypeId
	 */
	public void execute(int eventPartId, int bettingTypeId)
	{
		try
		{
			//TODO we will create a logic for this execute to create 
			//but now we are dealing with one provider id
			Logic oddsLogic = new OddsLogic();
			LinkedList<Entity> odds_ = oddsLogic.getAll(id, bettingTypeId, eventPartId);
			if (odds_.size() == 0)
				throw new NullPointerException();
			int size = odds_.size();
			LinkedList<Odds> odds = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				odds.add((Odds) odds_.get(i));
			}
			Hashtable<String, Float> localHash = new Hashtable<>();
			Hashtable<String, Long> outcomeHash = new Hashtable<>();

			try {
				for (int i = 0; i < 3; i++) {
					String key = odds.get(i).getName();
					key = key == null ? "NULL" : key;
					localHash.put(key, odds.get(i).getOdds());
					outcomeHash.put(key, odds.get(i).getOutcomeId());
				}

			} catch (IndexOutOfBoundsException exp) {
				localHash.put("NULL", (float) 0);
				outcomeHash.put("NULL", (long) 0);
			}

			setProviderId(odds.get(0).getProviderId());
			setAwayTeamOdds(localHash.get(hash.get(2)));
			setHomeTeamOdds(localHash.get(hash.get(1)));
			setDrawOdds(localHash.get("NULL"));
			setAwayTeamOutcomeId(outcomeHash.get(hash.get(2)));
			setHomeTeamOutcomeId(outcomeHash.get(hash.get(1)));
			setDrawOutcomeId(outcomeHash.get("NULL"));

		} catch (NullPointerException exp) {
			throw new NullPointerException("There are no home draw odds");

		}
	}

	public Match()
	{
		
	}
	
	/**************************************************************************************************/

	private long id;
	private String homeTeam;
	private String awayTeam;
	private Timestamp startTime;
	private float homeTeamOdds;
	private float awayTeamOdds;
	private float drawOdds;
	private long homeTeamOutcomeId;
	private long awayTeamOutcomeId;
	private long drawOutcomeId;
	private Match currentMatch;
	private Timestamp timestamp;
	private long tournamentId;
	private String tournamentName;
	private float homeTeamThreshold1;
	private float awayTeamThreshold1;
	private float homeTeamThreshold2;
	private float awayTeamThreshold2;
	private float overOdds;
	private float underOdds;
	private long outcome1;
	private long outcome2;
	private boolean outcome1Cheked;
	private boolean outcome2Cheked;
	private long providerId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public float getHomeTeamOdds() {
		return homeTeamOdds;
	}

	public void setHomeTeamOdds(float homeTeamOdds) {
		this.homeTeamOdds = homeTeamOdds;
	}

	public float getAwayTeamOdds() {
		return awayTeamOdds;
	}

	public void setAwayTeamOdds(float awayTeamOdds) {
		this.awayTeamOdds = awayTeamOdds;
	}

	public float getDrawOdds() {
		return drawOdds;
	}

	public void setDrawOdds(float drawOdds) {
		this.drawOdds = drawOdds;
	}

	public long getHomeTeamOutcomeId() {
		return homeTeamOutcomeId;
	}

	public void setHomeTeamOutcomeId(long homeTeamOutcomeId) {
		this.homeTeamOutcomeId = homeTeamOutcomeId;
	}

	public long getAwayTeamOutcomeId() {
		return awayTeamOutcomeId;
	}

	public void setAwayTeamOutcomeId(long awayTeamOutcomeId) {
		this.awayTeamOutcomeId = awayTeamOutcomeId;
	}

	public long getDrawOutcomeId() {
		return drawOutcomeId;
	}

	public void setDrawOutcomeId(long drawOutcomeId) {
		this.drawOutcomeId = drawOutcomeId;
	}

	public Match getCurrentMatch() {
		return currentMatch;
	}

	public void setCurrentMatch(Match currentMatch) {
		this.currentMatch = currentMatch;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public float getHomeTeamThreshold1() {
		return homeTeamThreshold1;
	}

	public void setHomeTeamThreshold1(float homeTeamThreshold1) {
		this.homeTeamThreshold1 = homeTeamThreshold1;
	}

	public float getAwayTeamThreshold1() {
		return awayTeamThreshold1;
	}

	public void setAwayTeamThreshold1(float awayTeamThreshold1) {
		this.awayTeamThreshold1 = awayTeamThreshold1;
	}

	public float getHomeTeamThreshold2() {
		return homeTeamThreshold2;
	}

	public void setHomeTeamThreshold2(float homeTeamThreshold2) {
		this.homeTeamThreshold2 = homeTeamThreshold2;
	}

	public float getAwayTeamThreshold2() {
		return awayTeamThreshold2;
	}

	public void setAwayTeamThreshold2(float awayTeamThreshold2) {
		this.awayTeamThreshold2 = awayTeamThreshold2;
	}

	public float getOverOdds() {
		return overOdds;
	}

	public void setOverOdds(float overOdds) {
		this.overOdds = overOdds;
	}

	public float getUnderOdds() {
		return underOdds;
	}

	public void setUnderOdds(float underOdds) {
		this.underOdds = underOdds;
	}

	public long getOutcome1() {
		return outcome1;
	}

	public void setOutcome1(long outcome1) {
		this.outcome1 = outcome1;
	}

	public long getOutcome2() {
		return outcome2;
	}

	public void setOutcome2(long outcome2) {
		this.outcome2 = outcome2;
	}

	public boolean isOutcome1Cheked() {
		return outcome1Cheked;
	}

	public void setOutcome1Cheked(boolean outcome1Cheked) {
		this.outcome1Cheked = outcome1Cheked;
	}

	public boolean isOutcome2Cheked() {
		return outcome2Cheked;
	}

	public void setOutcome2Cheked(boolean outcome2Cheked) {
		this.outcome2Cheked = outcome2Cheked;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setHash(Hashtable<Integer, String> hash) {
		this.hash = hash;
	}

	public static class Builder {
		private Hashtable<Integer, String> hash;
		private long id;
		private String homeTeam;
		private String awayTeam;
		private Timestamp startTime;
		private float homeTeamOdds;
		private float awayTeamOdds;
		private float drawOdds;
		private long homeTeamOutcomeId;
		private long awayTeamOutcomeId;
		private long drawOutcomeId;
		private Match currentMatch;
		private Timestamp timestamp;
		private long tournamentId;
		private String tournamentName;
		private float homeTeamThreshold1;
		private float awayTeamThreshold1;
		private float homeTeamThreshold2;
		private float awayTeamThreshold2;
		private float overOdds;
		private float underOdds;
		private long outcome1;
		private long outcome2;
		private boolean outcome1Cheked;
		private boolean outcome2Cheked;
		private long providerId;

		public Builder hash(Hashtable<Integer, String> hash) {
			this.hash = hash;
			return this;
		}

		public Builder id(long id) {
			this.id = id;
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

		public Builder startTime(Timestamp startTime) {
			this.startTime = startTime;
			return this;
		}

		public Builder homeTeamOdds(float homeTeamOdds) {
			this.homeTeamOdds = homeTeamOdds;
			return this;
		}

		public Builder awayTeamOdds(float awayTeamOdds) {
			this.awayTeamOdds = awayTeamOdds;
			return this;
		}

		public Builder drawOdds(float drawOdds) {
			this.drawOdds = drawOdds;
			return this;
		}

		public Builder homeTeamOutcomeId(long homeTeamOutcomeId) {
			this.homeTeamOutcomeId = homeTeamOutcomeId;
			return this;
		}

		public Builder awayTeamOutcomeId(long awayTeamOutcomeId) {
			this.awayTeamOutcomeId = awayTeamOutcomeId;
			return this;
		}

		public Builder drawOutcomeId(long drawOutcomeId) {
			this.drawOutcomeId = drawOutcomeId;
			return this;
		}

		public Builder currentMatch(Match currentMatch) {
			this.currentMatch = currentMatch;
			return this;
		}

		public Builder timestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder tournamentId(long tournamentId) {
			this.tournamentId = tournamentId;
			return this;
		}

		public Builder tournamentName(String tournamentName) {
			this.tournamentName = tournamentName;
			return this;
		}

		public Builder homeTeamThreshold1(float homeTeamThreshold1) {
			this.homeTeamThreshold1 = homeTeamThreshold1;
			return this;
		}

		public Builder awayTeamThreshold1(float awayTeamThreshold1) {
			this.awayTeamThreshold1 = awayTeamThreshold1;
			return this;
		}

		public Builder homeTeamThreshold2(float homeTeamThreshold2) {
			this.homeTeamThreshold2 = homeTeamThreshold2;
			return this;
		}

		public Builder awayTeamThreshold2(float awayTeamThreshold2) {
			this.awayTeamThreshold2 = awayTeamThreshold2;
			return this;
		}

		public Builder overOdds(float overOdds) {
			this.overOdds = overOdds;
			return this;
		}

		public Builder underOdds(float underOdds) {
			this.underOdds = underOdds;
			return this;
		}

		public Builder outcome1(long outcome1) {
			this.outcome1 = outcome1;
			return this;
		}

		public Builder outcome2(long outcome2) {
			this.outcome2 = outcome2;
			return this;
		}

		public Builder outcome1Cheked(boolean outcome1Cheked) {
			this.outcome1Cheked = outcome1Cheked;
			return this;
		}

		public Builder outcome2Cheked(boolean outcome2Cheked) {
			this.outcome2Cheked = outcome2Cheked;
			return this;
		}

		public Builder providerId(long providerId) {
			this.providerId = providerId;
			return this;
		}

		public Match build() {
			return new Match(this);
		}
	}

	private Match(Builder builder) {
		this.hash = builder.hash;
		this.id = builder.id;
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.startTime = builder.startTime;
		this.homeTeamOdds = builder.homeTeamOdds;
		this.awayTeamOdds = builder.awayTeamOdds;
		this.drawOdds = builder.drawOdds;
		this.homeTeamOutcomeId = builder.homeTeamOutcomeId;
		this.awayTeamOutcomeId = builder.awayTeamOutcomeId;
		this.drawOutcomeId = builder.drawOutcomeId;
		this.currentMatch = builder.currentMatch;
		this.timestamp = builder.timestamp;
		this.tournamentId = builder.tournamentId;
		this.tournamentName = builder.tournamentName;
		this.homeTeamThreshold1 = builder.homeTeamThreshold1;
		this.awayTeamThreshold1 = builder.awayTeamThreshold1;
		this.homeTeamThreshold2 = builder.homeTeamThreshold2;
		this.awayTeamThreshold2 = builder.awayTeamThreshold2;
		this.overOdds = builder.overOdds;
		this.underOdds = builder.underOdds;
		this.outcome1 = builder.outcome1;
		this.outcome2 = builder.outcome2;
		this.outcome1Cheked = builder.outcome1Cheked;
		this.outcome2Cheked = builder.outcome2Cheked;
		this.providerId = builder.providerId;
	}
}
