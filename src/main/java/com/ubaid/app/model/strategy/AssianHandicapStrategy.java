package com.ubaid.app.model.strategy;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.asianhandicap.AssianHandicapConverter;
import com.ubaid.app.model.logic.AssianHandicapLogic;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;

public class AssianHandicapStrategy extends AbstractRequestHandler {

	public AssianHandicapStrategy()
	{
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		Logic logic = new AssianHandicapLogic();
		Converter converter = new AssianHandicapConverter();
		
		String _id = map.get("id")[0];
		String homeTeam = map.get("homeTeam")[0];
		String awayTeam = map.get("awayTeam")[0];
					
		long id = Long.parseLong(_id);
		
		LinkedList<Entity> _eEntities = logic.getAll(id);
				
		LinkedList<Match> events =  converter.convert(id, homeTeam, awayTeam, _eEntities);
		
		JSONArray array = new JSONArray();
		JSONObject object;
		
		Hashtable<Long, Outcome> hash = Scheduler.getTrackedNotification();
		
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

		return array;
	}

}
