package com.ubaid.app.model.strategy;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.asianhandicap.AssianHandicapConverter;
import com.ubaid.app.model.logic.AssianHandicapLogic;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;

/**
 * this class called by a request, when a user view AssianHandicap odds 
 * of n match
 * @author ubaid
 *
 */
public class AssianHandicapStrategy extends AbstractRequestHandler
{

	public AssianHandicapStrategy()
	{
	}
	
	
	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		//getting AssianHandicap Logic
		Logic logic = new AssianHandicapLogic();
		
		//getting assian handicap converter
		Converter converter = new AssianHandicapConverter();
		
		//getting the parameters of request
		String _id = map.get("id")[0];
		String homeTeam = map.get("homeTeam")[0];
		String awayTeam = map.get("awayTeam")[0];
		String sportName = map.get("sportName")[0];
					
		//getting id of a match
		long id = Long.parseLong(_id);
		
		//getting all odds of an match
		LinkedList<Entity> _eEntities = logic.getAll(id, SportUtilFactory.getSportUtil().getEventPartId(sportName, 48));
				
		//converting these odds into a match having [homeTeam odds, awayTeam odds]
		LinkedList<Match> events =  converter.convert(id, homeTeam, awayTeam, _eEntities);
		
		//JSON
		JSONArray array = new JSONArray();
		JSONObject object;
		
		//this is hashtable, in which we track the registered match
		Hashtable<Long, Outcome> hash = Scheduler.getTrackedNotification();
		
		//creating an array of JSON objects
		for (Match match : events)
		{
			object = new JSONObject();
			object.put("homeTeamThreshold", match.getHomeTeamThreshold1());
			object.put("awayTeamThreshold", match.getAwayTeamThreshold1());
			object.put("homeTeamOdds", match.getHomeTeamOdds());
			object.put("awayTeamOdds", match.getAwayTeamOdds());
			object.put("outcome1", match.getOutcome1());
			object.put("outcome2", match.getOutcome2());
			object.put("outcome1Checked", hash.get(match.getOutcome1()) == null ? false : true);
			object.put("outcome2Checked", hash.get(match.getOutcome2()) == null ? false : true);
			
			
			array.put(object);
		}

		//returing array
		return array;
	}

}
