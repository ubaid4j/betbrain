package com.ubaid.app.model;


public enum SportType
{
	Football(1),
	Basketball(8),
	ESports(90),
	IceHockey(6),
	Tennis(3),
	LOL(100),
	CSGO(98),
	DOTA(99),
	Null(-1);
	
	private int id;
    SportType(int id) { this.id = id; }
    public int getValue() { return id; }	

    public static SportType getSport(long id)
    {
    	for(SportType sportType : SportType.values())
    	{
    		if(sportType.getValue() == id)
    			return sportType;
    	}
 
    	return SportType.Null;
    }
}
