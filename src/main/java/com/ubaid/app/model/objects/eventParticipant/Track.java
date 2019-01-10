package com.ubaid.app.model.objects.eventParticipant;

import com.ubaid.app.model.objects.Entity;

public class Track extends Entity {
	private long matchId;
	private long parentMatchId;
	private long userId;
	private int bettingType;

	private long produceId()
	{
		return matchId + parentMatchId + bettingType;
	}
	
	
	public long getId() {
		return produceId();
	}


	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public long getParentMatchId() {
		return parentMatchId;
	}

	public void setParentMatchId(long parentMatchId) {
		this.parentMatchId = parentMatchId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getBettingType() {
		return bettingType;
	}

	public void setBettingType(int bettingType) {
		this.bettingType = bettingType;
	}

	public static class Builder {
		private long matchId;
		private long parentMatchId;
		private long userId;
		private int bettingType;

		public Builder matchId(long matchId) {
			this.matchId = matchId;
			return this;
		}

		public Builder parentMatchId(long parentMatchId) {
			this.parentMatchId = parentMatchId;
			return this;
		}

		public Builder userId(long userId) {
			this.userId = userId;
			return this;
		}

		public Builder bettingType(int bettingType) {
			this.bettingType = bettingType;
			return this;
		}

		public Track build() {
			return new Track(this);
		}
	}

	private Track(Builder builder) {
		this.matchId = builder.matchId;
		this.parentMatchId = builder.parentMatchId;
		this.userId = builder.userId;
		this.bettingType = builder.bettingType;
	}
}
