package com.ubaid.app.model.objects.eventParticipant;

import com.ubaid.app.model.objects.Entity;

public class ParticipantRole extends Entity {
	private int role;

	public ParticipantRole(int role) {
		this.role = role;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public static class Builder {
		private int role;

		public Builder role(int role) {
			this.role = role;
			return this;
		}

		public ParticipantRole build() {
			return new ParticipantRole(this);
		}
	}

	private ParticipantRole(Builder builder) {
		this.role = builder.role;
	}
}
