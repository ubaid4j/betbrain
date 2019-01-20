package com.ubaid.app.model;

import java.util.HashMap;
import java.util.Map;


public class SportUtil
{

	private Map<Integer, Map<Integer, Integer>> map;
	private Map<Integer, Integer> subEventBettingTypeMap;  //sportName -> bettingType | this map determine the betting type of subevents of a match
	private Map<Integer, Integer> ahoubetting_eventPart;
	
	
	SportUtil()
	{
		
		map = new HashMap<>();
		
		Map<Integer, Integer> football = new HashMap<>();  //Betting type id -> event Part Id
		football.put(47, 3); //overUnder
		football.put(48, 3); //asian handicap
		football.put(69, 3); //home draw away
		
		Map<Integer, Integer> iceHockeyy = new HashMap<>(); //betting type id -> event part id
		iceHockeyy.put(47, 40); //overUnder
		iceHockeyy.put(48, 41); //assian handicap
		iceHockeyy.put(69, 41); //home draw away
				
		Map<Integer, Integer> esports = new HashMap<>(); //betting type id -> event part id
		esports.put(112, 600);  //homw away
		
		
		
		Map<Integer, Integer> tennis = new HashMap<>(); //betting type id -> event part id
		tennis.put(70, 20); //homw away of tennis map to event part id of whole match
		
		
		
		//for basket ball
		Map<Integer, Integer> basketBall = new HashMap<>(); //betting type id -> event par id
		basketBall.put(70, 60); //home away = 70 -> eventPartId = 60 (whole match)
		basketBall.put(47, 60); //over under = 47 -> eventPartId = 60 (whole match)
		basketBall.put(48, 60); //assian handicap = 48 -> eventPartId = 60 (whole match)
		
		
		
		//sport -> Map[bettingTypeId -> eventPartId]
		map.put(1, football);
		map.put(6, iceHockeyy);
		map.put(90, esports);
		map.put(3, tennis);
		map.put(SportType.BasketBall.getValue(), basketBall);
		
		//home away draw betting type of an sport
		subEventBettingTypeMap = new HashMap<>();
		subEventBettingTypeMap.put(SportType.FootBall.getValue(), 69);
		subEventBettingTypeMap.put(SportType.IceHokey.getValue(), 69);
		subEventBettingTypeMap.put(SportType.ESports.getValue(), 112);
		subEventBettingTypeMap.put(SportType.Tennis.getValue(), 70);
		subEventBettingTypeMap.put(SportType.BasketBall.getValue(), 70);
		
		
		ahoubetting_eventPart.put(SportType.FootBall.getValue(), 3);
		ahoubetting_eventPart.put(SportType.IceHokey.getValue(), 41);
		ahoubetting_eventPart.put(SportType.BasketBall.getValue(), 60);
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
			case "Tennis":
				type = SportType.Tennis;
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
				type = SportType.Null;
				break;
		}

		return type.getValue();
	}
	
	public int getEventPartId(String sportName, Integer bettingTypeId)
	{
		return map.get(getSportId(sportName)).get(bettingTypeId);
	}


	public int getAHOUEventPartId(String sportName)
	{
		return ahoubetting_eventPart.get(getSportId(sportName));
	}

}
