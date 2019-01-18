package com.ubaid.app.model.strategy;

import java.sql.Timestamp;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.schedule1_1.BettingType;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;

public class HomeAwayDrawRegisterarStrategy implements RequestHandler {

	public HomeAwayDrawRegisterarStrategy()
	{
	}

	@Override
	public JSONArray get(Map<String, String[]> map_r)
	{
		String _map = map_r.get("data")[0];		
		String _isAdd = map_r.get("checked")[0];
		boolean isAdd = Boolean.parseBoolean(_isAdd);
		JSONObject map = new JSONObject(_map);
		Logic logic = new RegisteredOutcomeLogic();
		Outcome[] outcomes = new Outcome[3];
		
		
		outcomes[0] = new Outcome.Builder()
								.id(map.getLong(Helper.HOMETEAMOUTCOMEID.toString()))
								.homeTeam(map.getString(Helper.HOMETEAM.toString()))
								.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
								.participant(map.getString(Helper.HOMETEAM.toString()))
								.leagueName(map.getString(Helper.LEAGUENAME.toString()))
								.matchName(map.getString(Helper.MATCHNAME.toString()))
								.odds(map.getFloat(Helper.HOMETEAMODDS.toString()))
								.registerTime(new Timestamp(System.currentTimeMillis()))
								.changedTime(new Timestamp(System.currentTimeMillis()))
								.threshold(-1)
								.bettingType(BettingType.HomeDrawAway)
								.build();
		
		outcomes[1] = new Outcome.Builder()
								.id(map.getLong(Helper.AWAYTEAMOUTCOMEID.toString()))
								.homeTeam(map.getString(Helper.HOMETEAM.toString()))
								.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
								.participant(map.getString(Helper.AWAYTEAM.toString()))
								.leagueName(map.getString(Helper.LEAGUENAME.toString()))
								.matchName(map.getString(Helper.MATCHNAME.toString()))
								.odds(map.getFloat(Helper.AWAYTEAMODDS.toString()))
								.registerTime(new Timestamp(System.currentTimeMillis()))
								.changedTime(new Timestamp(System.currentTimeMillis()))
								.threshold(-1)
								.bettingType(BettingType.HomeDrawAway)
								.build();

		
		outcomes[2] = new Outcome.Builder()
								.id(map.getLong(Helper.DRAWOUTCOMEID.toString()))
								.homeTeam(map.getString(Helper.HOMETEAM.toString()))
								.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
								.leagueName(map.getString(Helper.LEAGUENAME.toString()))
								.matchName(map.getString(Helper.MATCHNAME.toString()))
								.odds(map.getFloat(Helper.DRAWODDS.toString()))
								.registerTime(new Timestamp(System.currentTimeMillis()))
								.changedTime(new Timestamp(System.currentTimeMillis()))
								.threshold(-1)
								.bettingType(BettingType.HomeDrawAway)
								.build();

		
		
		
		
		
		if(isAdd)
			return add(logic, outcomes);
		else
			return remove(logic, outcomes);

	}
	
	
	private JSONArray remove(Logic logic, Outcome[] outcomes)
	{
		try
		{
			for(int i = 0; i < 3; i++)
			{
				if(logic.delete(outcomes[i].getId()))
					Scheduler.removeFromTrackedEvents(outcomes[i].getId());			
			}			
		}
		catch(Exception exp)
		{
			return new JSONArray("[{action: 'n'}]");
		}
		
		return new JSONArray("[{action: 'y'}]");

	}
	
	private JSONArray add(Logic logic, Outcome[] outcomes)
	{
		try
		{
			for(int i = 0; i < 3; i++)
			{
				if(logic.add(outcomes[i]));
					Scheduler.putInTrackeEvents(outcomes[i].getId(), outcomes[i]);
			}			
		}
		catch(Exception exp)
		{
			return new JSONArray("[{action: 'n'}]");			
		}
		
		return new JSONArray("[{action: 'y'}]");

	}


}
