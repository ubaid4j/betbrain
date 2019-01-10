package com.ubaid.app.model.objects;

import java.sql.Timestamp;

import com.ubaid.app.model.singleton.HashFunction;


public class SubEvents extends Events {

	@Override
	public String getHash()
	{
		String str = Long.toString(super.getId());	
		return HashFunction.getHash(str);
	}

	private String eventPart;
	private String eventType;
	private String roleName;
	private int roleId;


	public SubEvents() {

	}

	public String getEventPart() {
		return eventPart;
	}

	@Override
	public Timestamp getStartTime() {
		return super.getStartTime();
	}

	@Override
	public void setStartTime(Timestamp startTime) {
		super.setStartTime(startTime);
	}

	@Override
	public long getId() {
		return super.getId();
	}

	@Override
	public void setId(long id) {
		super.setId(id);
	}

	public void setEventPart(String eventPart) {
		this.eventPart = eventPart;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public static class Builder {
		private String eventName;
		private String eventType;
		private String roleName;
		private int roleId;
		private long id;
		private Timestamp startTime;

		public Builder eventName(String eventName) {
			this.eventName = eventName;
			return this;
		}

		public Builder eventType(String eventType) {
			this.eventType = eventType;
			return this;
		}

		public Builder roleName(String roleName) {
			this.roleName = roleName;
			return this;
		}

		public Builder roleId(int roleId) {
			this.roleId = roleId;
			return this;
		}

		
		public Builder startTime(Timestamp startTime) {
			this.startTime = startTime;
			return this;
		}

		public Builder id(long id)
		{
			this.id = id;
			return this;
		}


		public SubEvents build() {
			SubEvents subEvents = new SubEvents();
			subEvents.eventType = eventType;
			subEvents.setRoleId(roleId);
			subEvents.roleName = roleName;
			subEvents.setEventName(eventName);
			subEvents.setId(id);
			subEvents.setStartTime(startTime);
			return subEvents;
		}
	}
}
