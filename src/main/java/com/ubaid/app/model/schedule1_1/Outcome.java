package com.ubaid.app.model.schedule1_1;

import java.sql.Timestamp;

import com.ubaid.app.model.objects.Entity;

public final class Outcome extends Entity implements Cloneable {

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
	private long matchId;
	private String sportName;
	private long typeId;
	private String status;

	/*-------------------------------Do not delete-----------------------------------------*/
	public String getParticipant() {
		if (participant == null) {
			return getTypeId() == 14 ? "Under" : "Over";
		}

		return participant;
	}
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/*-------------------------------Do not delete-----------------------------------------*/



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

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
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
		private long matchId;
		private String sportName;
		private long typeId;
		private String status;

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

		public Builder matchId(long matchId) {
			this.matchId = matchId;
			return this;
		}

		public Builder sportName(String sportName) {
			this.sportName = sportName;
			return this;
		}

		public Builder typeId(long typeId) {
			this.typeId = typeId;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
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
		this.matchId = builder.matchId;
		this.sportName = builder.sportName;
		this.typeId = builder.typeId;
		this.status = builder.status;
	}
}