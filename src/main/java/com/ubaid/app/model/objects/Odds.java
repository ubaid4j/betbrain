package com.ubaid.app.model.objects;

public class Odds extends Entity {
	private String name;
	private float odds;
	private float threshold;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		private String name;
		private float odds;
		private float threshold;

		public Builder name(String name) {
			this.name = name;
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

		public Odds build() {
			return new Odds(this);
		}
	}

	private Odds(Builder builder) {
		this.name = builder.name;
		this.odds = builder.odds;
		this.threshold = builder.threshold;
	}
}
