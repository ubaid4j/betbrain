package com.ubaid.app.model.objects.eventParticipant;

import com.ubaid.app.model.objects.Entity;

public class EventParticipent extends Entity {
	private String participantName;
	private long eventId;

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public EventParticipent() {

	}

	public static class Builder {
		private String participantName;
		private long eventId;

		public Builder participantName(String participantName) {
			this.participantName = participantName;
			return this;
		}

		public Builder eventId(long eventId) {
			this.eventId = eventId;
			return this;
		}

		public EventParticipent build() {
			return new EventParticipent(this);
		}
	}

	private EventParticipent(Builder builder) {
		this.participantName = builder.participantName;
		this.eventId = builder.eventId;
	}
}
