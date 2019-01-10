package com.ubaid.app.model.objects;

public class Sport extends Entity {

	private String name;
	private long id;

	public Sport() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static class Builder {
		private String name;
		private long id;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Sport build() {
			Sport sport = new Sport();
			sport.name = name;
			sport.id = id;
			return sport;
		}
	}
}
