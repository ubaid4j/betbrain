package com.ubaid.app.model.overunder;

import com.ubaid.app.model.objects.Entity;

public class OverUnderRawData extends Entity {
	//13 Over
	//14 Under
	private long typeId;
	private float odds;
	private float threshold;
	private long outcomeId;
	private long providerId;

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
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

	public long getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(long outcomeId) {
		this.outcomeId = outcomeId;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public static class Builder {
		private long typeId;
		private float odds;
		private float threshold;
		private long outcomeId;
		private long providerId;

		public Builder typeId(long typeId) {
			this.typeId = typeId;
			return this;
		}

		public Builder odds(float odds) {
			this.odds = odds;
			return this;
		}

		public Builder threshold(float threshold) {
			this.threshold = threshold;
			return this;
		}

		public Builder outcomeId(long outcomeId) {
			this.outcomeId = outcomeId;
			return this;
		}

		public Builder providerId(long providerId) {
			this.providerId = providerId;
			return this;
		}

		public OverUnderRawData build() {
			return new OverUnderRawData(this);
		}
	}

	private OverUnderRawData(Builder builder) {
		this.typeId = builder.typeId;
		this.odds = builder.odds;
		this.threshold = builder.threshold;
		this.outcomeId = builder.outcomeId;
		this.providerId = builder.providerId;
	}
}
