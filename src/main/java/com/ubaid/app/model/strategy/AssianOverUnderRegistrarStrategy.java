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

public class AssianOverUnderRegistrarStrategy extends AbstractRequestHandler
{

	public AssianOverUnderRegistrarStrategy()
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
		
		Outcome outcome = new Outcome.Builder()
								.id(map.getLong(Helper.OUTCOMEID.toString()))
								.homeTeam(map.getString(Helper.HOMETEAM.toString()))
								.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
								.participant(map.getString(Helper.PARTICIPANT.toString()))
								.leagueName(map.getString(Helper.LEAGUENAME.toString()))
								.matchName(map.getString(Helper.MATCHNAME.toString()))
								.threshold(map.getFloat(Helper.THRESHOLD.toString()))
								.odds(map.getFloat(Helper.ODDS.toString()))
								.registerTime(new Timestamp(System.currentTimeMillis()))
								.changedTime(new Timestamp(System.currentTimeMillis()))
								.bettingType(BettingType.valueOf(map.getString(Helper.BETTINGTYPE.toString())))
								.build();
		if(isAdd)
			return add(logic, outcome);
		else
			return remove(logic, outcome);			
	}

	
	private JSONArray remove(Logic logic, Outcome outcome)
	{
		try
		{
			if(logic.delete(outcome.getId()))
				Scheduler.removeFromTrackedEvents(outcome.getId());			
		}
		catch(Exception exp)
		{
			return new JSONArray("[{action: 'y'}]");			
		}
		
		return new JSONArray("[{action: 'y'}]");
	}
	
	private JSONArray add(Logic logic, Outcome outcome)
	{
		try
		{
			if(logic.add(outcome));
				Scheduler.putInTrackeEvents(outcome.getId(), outcome);
		}
		catch(Exception exp)
		{
			return new JSONArray("[{action: 'n'}]");			
		}

		return new JSONArray("[{action: 'y'}]");
	}

}
