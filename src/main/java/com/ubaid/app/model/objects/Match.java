package com.ubaid.app.model.objects;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.OddsLogic;
import com.ubaid.app.model.logic.matchLogic.MatchLogic;
import com.ubaid.app.model.singleton.HashFunction;

public class Match {
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
	private Hashtable<Integer, String> hash = new Hashtable<>();
	private Match currentMatch;
	private Timestamp timestamp;
	private long tournamentId;
	private String tournamentName;
	private float homeTeamThreshold1;
	private float awayTeamThreshold1;
	private float homeTeamThreshold2;
	private float awayTeamThreshold2;
	private float overOdds;
	
	
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

	private float underOdds;
	private long outcome1;
	private long outcome2;
	private boolean outcome1Cheked;
	private boolean outcome2Cheked;
	
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
		hash.put(1, homeTeam);
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
		hash.put(2, awayTeam);
	}

	public Match() {

	}
	
	@Override
	public String toString()
	{
		return String.format("%s%d\n%s%f%s%f%s%f",
								"The Match Id is: " ,
								 this.getId(),
								 "Home Team Odds: ",
								 	getHomeTeamOdds(),
								 "Away Team Odds: ",
								 	getAwayTeamOdds(), 
								 "Draw Odds",
								 	getDrawOdds());
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getHash() {
		return HashFunction.getHash2(Long.toString(id));
	}

	//only for home/draw/away <69> betting type
	public void execute() {
		try {
			Logic oddsLogic = new OddsLogic();
			LinkedList<Entity> odds_ = oddsLogic.getAll(id);
			int size = odds_.size();
			LinkedList<Odds> odds = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				odds.add((Odds) odds_.get(i));
			}
			Hashtable<String, Float> localHash = new Hashtable<>();
			Hashtable<String, Long> outcomeHash = new Hashtable<>();
			for (int i = 0; i < 3; i++) {
				String key = odds.get(i).getName();
				key = key == null ? "NULL" : key;
				localHash.put(key, odds.get(i).getOdds());
				outcomeHash.put(key, odds.get(i).getOutcomeId());
			}
			setAwayTeamOdds(localHash.get(hash.get(2)));
			setHomeTeamOdds(localHash.get(hash.get(1)));
			setDrawOdds(localHash.get("NULL"));
			setAwayTeamOutcomeId(outcomeHash.get(hash.get(2)));
			setHomeTeamOutcomeId(outcomeHash.get(hash.get(1)));
			setDrawOutcomeId(outcomeHash.get("NULL"));

		} catch (NullPointerException exp) {

		}
	}

	
	public static class Builder {
		private long id;
		private String homeTeam;
		private String awayTeam;
		private Timestamp startTime;
		private float homeTeamOdds;
		private float awayTeamOdds;
		private float drawOdds;
		public Builder homeTeamOdds(float odds)
		{
			this.homeTeamOdds = odds;
			return this;
		}
		public Builder awayTeamOdds(float odds)
		{
			this.awayTeamOdds = odds;
			return this;
		}
		public Builder drawOdds(float odds)
		{
			this.homeTeamOdds = odds;
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
		public Match build() {
			return new Match(this);
		}
	}
	private Match(Builder builder) {
		this.id = builder.id;
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.startTime = builder.startTime;
		this.awayTeamOdds = builder.awayTeamOdds;
		this.homeTeamOdds = builder.homeTeamOdds;
		this.drawOdds = builder.drawOdds;
	}
	public Match getMatch(long match_id)
	{
		MatchLogic logic = new MatchLogic();
		return logic.getMatch(match_id);
	}
	public boolean compare()
	{
		return isChanged(getMatch(getId()));
	}
	public boolean isChanged(Match newMatch)
	{
		if(
					(Math.abs(newMatch.getAwayTeamOdds() - getAwayTeamOdds()) > 0.000001)
				||
					(Math.abs(newMatch.getDrawOdds() - getDrawOdds()) > 0.000001)
				|| 
					(Math.abs(newMatch.getHomeTeamOdds() - getHomeTeamOdds()) > 0.000001)
		  )
		{
			setCurrentMatch(newMatch);
			return true;
		}
		
		return false;
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

	public float getUnderOdds() {
		return underOdds;
	}

	public void setUnderOdds(float underOdds) {
		this.underOdds = underOdds;
	}

	public float getOverOdds() {
		return overOdds;
	}

	public void setOverOdds(float overOdds) {
		this.overOdds = overOdds;
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
	
	
}
