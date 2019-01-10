package com.ubaid.app.model.objects;

import java.sql.Timestamp;


public class WelcomeEvents extends Entity
{
	private long bettingTypeId;
	private String bettingTypeName;
	private float odds;
	private String ParticipantName;
	private long eventID;
	private Timestamp lastChangedTime;
	private Timestamp lastCollectedTime;
	private String outComeTypeName;
	private float threshold;
	private String sportName;
	private Timestamp eventStartTime;
	private int eventPartId;
	private String eventPartName;

	public long getBettingTypeId() {
		return bettingTypeId;
	}

	public void setBettingTypeId(long bettingTypeId) {
		this.bettingTypeId = bettingTypeId;
	}

	public String getBettingTypeName() {
		return bettingTypeName;
	}

	public void setBettingTypeName(String bettingTypeName) {
		this.bettingTypeName = bettingTypeName;
	}

	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}

	public String getParticipantName() {
		return ParticipantName;
	}

	public void setParticipantName(String participantName) {
		ParticipantName = participantName;
	}

	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}

	public Timestamp getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Timestamp lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

	public Timestamp getLastCollectedTime() {
		return lastCollectedTime;
	}

	public void setLastCollectedTime(Timestamp lastCollectedTime) {
		this.lastCollectedTime = lastCollectedTime;
	}

	public String getOutComeTypeName() {
		return outComeTypeName;
	}

	public void setOutComeTypeName(String outComeTypeName) {
		this.outComeTypeName = outComeTypeName;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public Timestamp getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(Timestamp eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	public int getEventPartId() {
		return eventPartId;
	}

	public void setEventPartId(int eventPartId) {
		this.eventPartId = eventPartId;
	}

	public String getEventPartName() {
		return eventPartName;
	}

	public void setEventPartName(String eventPartName) {
		this.eventPartName = eventPartName;
	}

	public static class Builder {
		private long bettingTypeId;
		private String bettingTypeName;
		private float odds;
		private String ParticipantName;
		private long eventID;
		private Timestamp lastChangedTime;
		private Timestamp lastCollectedTime;
		private String outComeTypeName;
		private float threshold;
		private String sportName;
		private Timestamp eventStartTime;
		private int eventPartId;
		private String eventPartName;

		public Builder bettingTypeId(long bettingTypeId) {
			this.bettingTypeId = bettingTypeId;
			return this;
		}

		public Builder bettingTypeName(String bettingTypeName) {
			this.bettingTypeName = bettingTypeName;
			return this;
		}

		public Builder odds(float odds) {
			this.odds = odds;
			return this;
		}

		public Builder ParticipantName(String ParticipantName) {
			this.ParticipantName = ParticipantName;
			return this;
		}

		public Builder eventID(long eventID) {
			this.eventID = eventID;
			return this;
		}

		public Builder lastChangedTime(Timestamp lastChangedTime) {
			this.lastChangedTime = lastChangedTime;
			return this;
		}

		public Builder lastCollectedTime(Timestamp lastCollectedTime) {
			this.lastCollectedTime = lastCollectedTime;
			return this;
		}

		public Builder outComeTypeName(String outComeTypeName) {
			this.outComeTypeName = outComeTypeName;
			return this;
		}

		public Builder threshold(float threshold) {
			this.threshold = threshold;
			return this;
		}

		public Builder sportName(String sportName) {
			this.sportName = sportName;
			return this;
		}

		public Builder eventStartTime(Timestamp eventStartTime) {
			this.eventStartTime = eventStartTime;
			return this;
		}

		public Builder eventPartId(int eventPartId) {
			this.eventPartId = eventPartId;
			return this;
		}

		
		public Builder eventPartName(String eventPartName) {
			this.eventPartName = eventPartName;
			return this;
		}

		public WelcomeEvents build() {
			return new WelcomeEvents(this);
		}
	}

	private WelcomeEvents(Builder builder) {
		this.bettingTypeId = builder.bettingTypeId;
		this.bettingTypeName = builder.bettingTypeName;
		this.odds = builder.odds;
		this.ParticipantName = builder.ParticipantName;
		this.eventID = builder.eventID;
		this.lastChangedTime = builder.lastChangedTime;
		this.lastCollectedTime = builder.lastCollectedTime;
		this.outComeTypeName = builder.outComeTypeName;
		this.threshold = builder.threshold;
		this.sportName = builder.sportName;
		this.eventStartTime = builder.eventStartTime;
		this.eventPartId = builder.eventPartId;
		this.eventPartName = builder.eventPartName;
	}

	@Override
	public String toString() {
		return "WelcomeEvents [bettingTypeId=" + bettingTypeId + ", bettingTypeName=" + bettingTypeName + ", odds="
				+ odds + ", ParticipantName=" + ParticipantName + ", eventID=" + eventID + ", lastChangedTime="
				+ lastChangedTime + ", lastCollectedTime=" + lastCollectedTime + ", outComeTypeName=" + outComeTypeName
				+ ", threshold=" + threshold + ", sportName=" + sportName + ", eventStartTime=" + eventStartTime
				+ ", eventPartId=" + eventPartId + ", eventPartName=" + eventPartName + "]";
	}
}
