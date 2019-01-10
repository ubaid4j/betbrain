package com.ubaid.app.model.asianhandicap;

import com.ubaid.app.model.objects.Entity;

public class AssianHandicapRawData extends Entity
{
	private float odds;
	private float threshold;
	private String participant;

	public AssianHandicapRawData() {

	}

	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public static class Builder {
		private float odds;
		private float threshold;
		private String participant;

		public Builder odds(float odds) {
			this.odds = odds;
			return this;
		}

		public Builder threshold(float threshold) {
			this.threshold = threshold;
			return this;
		}

		public Builder participant(String participant) {
			this.participant = participant;
			return this;
		}

		public AssianHandicapRawData build() {
			return new AssianHandicapRawData(this);
		}
	}

	private AssianHandicapRawData(Builder builder) {
		this.odds = builder.odds;
		this.threshold = builder.threshold;
		this.participant = builder.participant;
	}
}
