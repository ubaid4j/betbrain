package com.ubaid.app.model.strategy;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.OverUnderLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.overunder.OverUnderConverter;
import com.ubaid.app.model.schedule1_1.Key;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.oddsDetection.OddsDetection;

/**
 * this class is responsible to return an JSON array of OverUnder outcomes of a match
 * @author ubaid
 *
 */
public class OverUnderStrategy extends AbstractRequestHandler
{

	public OverUnderStrategy()
	{
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		//creating logic
		Logic logic = new OverUnderLogic();
		
		//getting OverUnder converter
		Converter converter = new OverUnderConverter();
		
		//getting get request parameters
		String _id = map.get("id")[0];
		String homeTeam = map.get("homeTeam")[0];
		String awayTeam = map.get("awayTeam")[0];
		String sportName = map.get("sportName")[0];
					
		//getting id of match
		long id = Long.parseLong(_id);
		
		//getting odds of the match passing id, and its eventPartId
		LinkedList<Entity> _eEntities = logic.getAll(id,SportUtilFactory.getSportUtil().getEventPartId(sportName, 47));
		
		//getting hash [which track the registered outcomes]
		Hashtable<Key, Outcome> hash = OddsDetection.getTrackedOutcomes();
				
		//converting these all odds to match [having over under odds]
		LinkedList<Match> events =  converter.convert(id, homeTeam, awayTeam, _eEntities);
		
		JSONArray array = new JSONArray();
		JSONObject object;
		
		//creating jSON array of json objects
		for (Match match : events)
		{
			object = new JSONObject();
			object.put("threshold", match.getHomeTeamThreshold1());
			object.put("overTeamOdds", match.getOverOdds());
			object.put("underTeamOdds", match.getUnderOdds());
			object.put("outcome1", match.getOutcome1());
			object.put("outcome2", match.getOutcome2());
			object.put("providerId", match.getProviderId());
			object.put("outcome1Checked", hash.get(new Key(match.getOutcome1(), match.getProviderId())) == null ? false : true);
			object.put("outcome2Checked", hash.get(new Key(match.getOutcome2(), match.getProviderId())) == null ? false : true);
			
			array.put(object);
		}
		

		return array;
	}

}
