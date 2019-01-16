package com.ubaid.app.model;

public enum SportType
{
	FootBall(1),
	HandBall(7),
	BasketBall(8),
	VolleyBall(20),
	ESports(90),
	IceHokey(6);
	
	private int id;
    SportType(int id) { this.id = id; }
    public int getValue() { return id; }	
}
