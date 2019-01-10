package com.ubaid.app.model.objects;

import java.sql.Timestamp;

import com.ubaid.app.model.singleton.HashFunction;

public class Events extends Entity {

	private String eventName;
	private String locationName;
	private Timestamp startTime;
	private Timestamp endTime;
	private long id;

	
	public Events()
	{
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getHash()
	{
		String str = Long.toString(id);	
		return HashFunction.getHash2(str);
	}
	

	public static class Builder {
		private String eventName;
		private String locationName;
		private Timestamp startTime;
		private Timestamp endTime;
		private long id;

		public Builder eventName(String eventName) {
			this.eventName = eventName;
			return this;
		}

		public Builder locationName(String locationName) {
			this.locationName = locationName;
			return this;
		}

		public Builder startTime(Timestamp startTime) {
			this.startTime = startTime;
			return this;
		}

		public Builder endTime(Timestamp endTime) {
			this.endTime = endTime;
			return this;
		}
		
		public Builder id(long id)
		{
			this.id = id;
			return this;
		}

		public Events build() {
			Events events = new Events();
			events.eventName = eventName;
			events.locationName = locationName;
			events.startTime = startTime;
			events.endTime = endTime;
			events.id = id;
			return events;
		}
	}
}
