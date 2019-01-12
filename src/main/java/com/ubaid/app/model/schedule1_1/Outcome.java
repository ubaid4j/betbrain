package com.ubaid.app.model.schedule1_1;

import com.ubaid.app.model.objects.Entity;

public final class Outcome extends Entity {

	private float threshold;
	private float odds;
	private long id;
	private String leagueName;
	private String participant;
	private String homeTeam;
	private String awayTeam;
	private String matchName;

	public Outcome() {

	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
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

	public static class Builder {
		private float threshold;
		private float odds;
		private long id;
		private String leagueName;
		private String participant;
		private String homeTeam;
		private String awayTeam;
		private String matchName;
		
		public Builder threshold(float threshold) {
			this.threshold = threshold;
			return this;
		}

		public Builder odds(float odds) {
			this.odds = odds;
			return this;
		}

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Builder leagueName(String leagueName) {
			this.leagueName = leagueName;
			return this;
		}

		public Builder participant(String participant) {
			this.participant = participant;
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
		
		public Builder matchName(String matchName)
		{
			this.matchName = matchName;
			return this;
		}

		public Outcome build() {
			return new Outcome(this);
		}
	}

	private Outcome(Builder builder) {
		this.threshold = builder.threshold;
		this.odds = builder.odds;
		this.id = builder.id;
		this.leagueName = builder.leagueName;
		this.participant = builder.participant;
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.matchName = builder.matchName;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
}
