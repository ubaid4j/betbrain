package com.ubaid.app.model.schedule1_1;

import java.sql.Timestamp;

import com.ubaid.app.model.objects.Entity;

public final class Outcome extends Entity {

	private float threshold;
	private float oldThreshold;
	private float odds;
	private float oldOdds;
	private long id;
	private String leagueName;
	private String participant;
	private String homeTeam;
	private String awayTeam;
	private String matchName;
	private Timestamp changedTime;
	private Timestamp registerTime;
	private BettingType bettingType;

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public float getOldThreshold() {
		return oldThreshold;
	}

	public void setOldThreshold(float oldThreshold) {
		this.oldThreshold = oldThreshold;
	}

	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}

	public float getOldOdds() {
		return oldOdds;
	}

	public void setOldOdds(float oldOdds) {
		this.oldOdds = oldOdds;
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

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public Timestamp getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Timestamp changedTime) {
		this.changedTime = changedTime;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public BettingType getBettingType() {
		return bettingType;
	}

	public void setBettingType(BettingType bettingType) {
		this.bettingType = bettingType;
	}

	public static class Builder {
		private float threshold;
		private float oldThreshold;
		private float odds;
		private float oldOdds;
		private long id;
		private String leagueName;
		private String participant;
		private String homeTeam;
		private String awayTeam;
		private String matchName;
		private Timestamp changedTime;
		private Timestamp registerTime;
		private BettingType bettingType;

		public Builder threshold(float threshold) {
			this.threshold = threshold;
			return this;
		}

		public Builder oldThreshold(float oldThreshold) {
			this.oldThreshold = oldThreshold;
			return this;
		}

		public Builder odds(float odds) {
			this.odds = odds;
			return this;
		}

		public Builder oldOdds(float oldOdds) {
			this.oldOdds = oldOdds;
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

		public Builder matchName(String matchName) {
			this.matchName = matchName;
			return this;
		}

		public Builder changedTime(Timestamp changedTime) {
			this.changedTime = changedTime;
			return this;
		}

		public Builder registerTime(Timestamp registerTime) {
			this.registerTime = registerTime;
			return this;
		}

		public Builder bettingType(BettingType bettingType) {
			this.bettingType = bettingType;
			return this;
		}

		public Outcome build() {
			return new Outcome(this);
		}
	}

	private Outcome(Builder builder) {
		this.threshold = builder.threshold;
		this.oldThreshold = builder.oldThreshold;
		this.odds = builder.odds;
		this.oldOdds = builder.oldOdds;
		this.id = builder.id;
		this.leagueName = builder.leagueName;
		this.participant = builder.participant;
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.matchName = builder.matchName;
		this.changedTime = builder.changedTime;
		this.registerTime = builder.registerTime;
		this.bettingType = builder.bettingType;
	}

	@Override
	public String toString() {
		return "Outcome [threshold=" + threshold + ", oldThreshold=" + oldThreshold + ", odds=" + odds + ", oldOdds="
				+ oldOdds + ", id=" + id + ", " + (leagueName != null ? "leagueName=" + leagueName + ", " : "")
				+ (participant != null ? "participant=" + participant + ", " : "")
				+ (homeTeam != null ? "homeTeam=" + homeTeam + ", " : "")
				+ (awayTeam != null ? "awayTeam=" + awayTeam + ", " : "")
				+ (matchName != null ? "matchName=" + matchName + ", " : "")
				+ (changedTime != null ? "changedTime=" + changedTime + ", " : "")
				+ (registerTime != null ? "registerTime=" + registerTime + ", " : "")
				+ (bettingType != null ? "bettingType=" + bettingType : "") + "]";
	}
	
	
}