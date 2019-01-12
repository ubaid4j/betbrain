package com.ubaid.app.model.overunder;

import com.ubaid.app.model.objects.Entity;

public class OverUnderRawData extends Entity {
	private long key;
	private float odds;
	private float threshold;
	private long outcomeId;

	public OverUnderRawData() {
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
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

	public static class Builder {
		private long key;
		private float odds;
		private float threshold;
		private long outcomeId;

		public Builder key(long key) {
			this.key = key;
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

		public Builder outcomeId(long outcomeId)
		{
			this.outcomeId = outcomeId;
			return this;
		}
		
		public OverUnderRawData build() {
			return new OverUnderRawData(this);
		}
		
	
	}

	private OverUnderRawData(Builder builder) {
		this.key = builder.key;
		this.odds = builder.odds;
		this.threshold = builder.threshold;
		this.outcomeId = builder.outcomeId;
	}

	public long getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(long outcomeId) {
		this.outcomeId = outcomeId;
	}
}
