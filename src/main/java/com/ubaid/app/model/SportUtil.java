package com.ubaid.app.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;





public class SportUtil
{

	private Map<Integer, Map<Integer, Integer>> map;
	private Map<Integer, Integer> subEventBettingTypeMap;  //sportName -> bettingType | this map determine the betting type of subevents of a match
	private Map<Integer, Integer> ahoubetting_eventPart;
	private DateFormat inputFormat = null;
	private DateFormat outputFormat = null;
	private DateFormat dateOutput = null;
	private DateFormat dateInput = null;
	
	SportUtil()
	{
		
		inputFormat = new SimpleDateFormat("HH:mm:ss");
		outputFormat = new SimpleDateFormat("KK:mm:ss a");

		dateOutput = new SimpleDateFormat("EEE, dd MMM yyyy");
		dateInput = new SimpleDateFormat("yyyy-MM-dd");
		
		map = new HashMap<>();
		
		//1
		Map<Integer, Integer> football = new HashMap<>();  //Betting type id -> event Part Id
		football.put(47, 3); //overUnder
		football.put(48, 3); //asian handicap
		football.put(69, 3); //home draw away
		//2
		Map<Integer, Integer> iceHockeyy = new HashMap<>(); //betting type id -> event part id
		iceHockeyy.put(48, 40); //assian handicap
		iceHockeyy.put(47, 40); //overUnder
		iceHockeyy.put(69, 41); //home draw away
		//3
		Map<Integer, Integer> basketBall = new HashMap<>(); //betting type id -> event par id
		basketBall.put(70, 60); //home away = 70 -> eventPartId = 60 (whole match)
		basketBall.put(47, 60); //over under = 47 -> eventPartId = 60 (whole match)
		basketBall.put(48, 60); //assian handicap = 48 -> eventPartId = 60 (whole match)
		//4
		Map<Integer, Integer> tennis = new HashMap<>(); //betting type id -> event part id
		tennis.put(70, 20); //homw away of tennis map to event part id of whole match
		tennis.put(47, -1); //TODO for now I am writing no eventPart id for tennis
		tennis.put(48, -1);
		
		//9
		Map<Integer, Integer> handball = new HashMap<>(); //bettingTypeId -> eventPartId
		handball.put(69, 51);
		handball.put(47, 51);
		handball.put(48, 51);
		
		//5
		Map<Integer, Integer> esports = new HashMap<>(); //betting type id -> event part id
		esports.put(112, 600);  //homw away
		esports.put(47, -1);	//no over under handicap
		esports.put(48, -1);	//no  assian handicap
		//6
		Map<Integer, Integer> lol = new HashMap<>();
		lol.put(70, 1890); //home away
		lol.put(47, -1); //no over under
		lol.put(48, -1); //no a/h
		//7
		Map<Integer, Integer>  csgo = new HashMap<>();
		csgo.put(70, 1901); //home away
		csgo.put(47, -1); //no over under
		csgo.put(48, -1); // no ah
		//8
		Map<Integer, Integer> dota = new HashMap<>();
		dota.put(70, 1895); //home away
		dota.put(47, -1); //no over/under
		dota.put(47, -1); //no ah
				
		
		
		
		//sport -> Map[bettingTypeId -> eventPartId]
		map.put(1, football);
		map.put(6, iceHockeyy);
		map.put(90, esports);
		map.put(3, tennis);
		map.put(SportType.Basketball.getValue(), basketBall);
		map.put(SportType.LOL.getValue(), lol);
		map.put(SportType.CSGO.getValue(), csgo);
		map.put(SportType.DOTA.getValue(), dota);
		map.put(SportType.Handball.getValue(), handball);
		
		//home away draw betting type of an sport
		subEventBettingTypeMap = new HashMap<>();
		subEventBettingTypeMap.put(SportType.Football.getValue(), 69);
		subEventBettingTypeMap.put(SportType.IceHockey.getValue(), 69);
		subEventBettingTypeMap.put(SportType.Tennis.getValue(), 70);
		subEventBettingTypeMap.put(SportType.Basketball.getValue(), 70);
		subEventBettingTypeMap.put(SportType.ESports.getValue(), 112);
		subEventBettingTypeMap.put(SportType.LOL.getValue(), 70);
		subEventBettingTypeMap.put(SportType.CSGO.getValue(), 70);
		subEventBettingTypeMap.put(SportType.DOTA.getValue(), 70);
		subEventBettingTypeMap.put(SportType.Handball.getValue(), 69);
		

		//event part id for assian handicap and over/under
		ahoubetting_eventPart = new HashMap<>();
		ahoubetting_eventPart.put(SportType.Football.getValue(), 3);
		ahoubetting_eventPart.put(SportType.IceHockey.getValue(), 40);
		ahoubetting_eventPart.put(SportType.Basketball.getValue(), 60);
		ahoubetting_eventPart.put(SportType.ESports.getValue(), -1);
		ahoubetting_eventPart.put(SportType.LOL.getValue(), -1);
		ahoubetting_eventPart.put(SportType.DOTA.getValue(), -1);
		ahoubetting_eventPart.put(SportType.CSGO.getValue(), -1);
		ahoubetting_eventPart.put(SportType.Handball.getValue(), 51);
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
				type = SportType.Football;
				break;
			case "Tennis":
				type = SportType.Tennis;
				break;
			case "Basketball":
				type = SportType.Basketball;
				break;
			case "ESports":
				type = SportType.ESports;
				break;
			case "IceHockey":
				type = SportType.IceHockey;
				break;
			case "CSGO":
				type = SportType.CSGO;
				break;
			case "DOTA":
				type = SportType.DOTA;
				break;
			case "LOL":
				type = SportType.LOL;
				break;
			case "Handball":
				type = SportType.Handball;
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
	
	public String getCurrentTime()
	{
		LocalTime _paktime = LocalTime.now(ZoneId.of("UTC+05:00"));
		LocalTime _lithwaniaTime = LocalTime.now(ZoneId.of("UTC+02:00"));

		LocalDate _lithwaniaDate = LocalDate.now(ZoneId.of("UTC+02:00"));
		LocalDate _pakDate = LocalDate.now(ZoneId.of("UTC+05:00"));
		
		
		
		String time = null;
		try
		{
			time = String.format("Pakistan : %s %s\nLithuania: %s %s\n",
					dateOutput.format(dateInput.parse(_pakDate.toString())),
					outputFormat.format(inputFormat.parse(_paktime.toString())),
					dateOutput.format(dateInput.parse(_lithwaniaDate.toString())),
					outputFormat.format(inputFormat.parse(_lithwaniaTime.toString())));
			
		}
		catch(ParseException exp)
		{
			exp.printStackTrace();
		}
		
		return time;
	}
	
}
