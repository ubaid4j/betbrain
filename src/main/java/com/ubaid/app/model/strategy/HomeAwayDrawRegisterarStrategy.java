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

/**
 * this class is responsible to register home draw away outcome
 * there is minor difference betweern [assian handicap, over/under] and home/draw/away
 * for home/draw/away, we have to create three outcomes as there are three odds 
 * when we mark a tick on an match, then actually we are marking three odds [outcomes]
 * @author ubaid
 *
 */
public class HomeAwayDrawRegisterarStrategy extends AbstractRequestHandler
{

	public HomeAwayDrawRegisterarStrategy()
	{
	}

	@Override
	public JSONArray get(Map<String, String[]> map_r)
	{
		//getting data from the request
		String _map = map_r.get("data")[0];		
		String _isAdd = map_r.get("checked")[0];
		boolean isAdd = Boolean.parseBoolean(_isAdd);
		JSONObject map = new JSONObject(_map);
		
		//creating logic
		Logic logic = new RegisteredOutcomeLogic();
		
		//creating an array of outcome of size 3
		Outcome[] outcomes = new Outcome[3];
		
		
		
		//creating three outcomes
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

		
		
		
		
		//on detemining delete or add data, adding or removing accordingly
		if(isAdd)
			return add(logic, outcomes);
		else
			return remove(logic, outcomes);

	}
	
	//this method remove the outcomes from the database as well as from program
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

	//this method add outcomes from the database as well as from program
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
