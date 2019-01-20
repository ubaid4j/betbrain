package com.ubaid.app.model;

import java.util.HashMap;
import java.util.Map;

public class SportUtil
{

	private Map<Integer, Map<Integer, Integer>> map;
	private Map<Integer, Integer> subEventBettingTypeMap;  //sportName -> bettingType | this map determine the betting type of subevents of a match
	
	SportUtil()
	{
		
		map = new HashMap<>();
		
		Map<Integer, Integer> football = new HashMap<>();  //Betting type id -> event Part Id
		football.put(47, 3); //overUnder
		football.put(48, 3); //asian handicap
		football.put(69, 3); //home draw away
		
		Map<Integer, Integer> iceHockeyy = new HashMap<>(); //betting type id -> event part id
		iceHockeyy.put(47, 40);
		iceHockeyy.put(48, 41);
		iceHockeyy.put(69, 41);
				
		Map<Integer, Integer> esports = new HashMap<>(); //betting type id -> event part id
		esports.put(112, 600);
		
		map.put(1, football);
		map.put(6, iceHockeyy);
		map.put(90, esports);
		
		subEventBettingTypeMap = new HashMap<>();
		subEventBettingTypeMap.put(SportType.FootBall.getValue(), 69);
		subEventBettingTypeMap.put(SportType.IceHokey.getValue(), 69);
		subEventBettingTypeMap.put(SportType.ESports.getValue(), 112);
	}
	
	
	public int getSubEventBettingType(String sportName)
	{
		return subEventBettingTypeMap.get(getSportId(sportName));
	}
	
	public int getSportId(String sportName)
	{
		SportType type;
		
		switch (sportName)
		{
			case "Football":
				type = SportType.FootBall;
				break;
			case "Handball":
				type = SportType.HandBall;
				break;
			case "Basketball":
				type = SportType.BasketBall;
				break;
			case "Volleyball":
				type = SportType.VolleyBall;
				break;
			case "E-Sports":
				type = SportType.ESports;
				break;
			case "IceHockey":
				type = SportType.IceHokey;
				break;
			default:
				type = SportType.FootBall;
				break;
		}

		return type.getValue();
	}
	
	public int getEventPartId(String sportName, Integer bettingTypeId)
	{
		return map.get(getSportId(sportName)).get(bettingTypeId);
	}

}
