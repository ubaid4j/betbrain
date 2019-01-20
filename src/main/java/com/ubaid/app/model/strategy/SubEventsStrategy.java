package com.ubaid.app.model.strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.SportUtil;
import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.SubEventsLogic;
import com.ubaid.app.model.objects.Converter;
import com.ubaid.app.model.objects.ConverterFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.objects.SubEvents;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.oddsDetection.OddsDetection;

/**
 * this class is responsible to show matches of an tournament
 * @author ubaid
 *
 */
public class SubEventsStrategy extends AbstractRequestHandler
{

	public SubEventsStrategy()
	{
		
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		//geting converter which convert subEvents into match
		Converter converter = ConverterFactory.getConverter();
		
		//getting getRequest parameters
		String attribute = map.get("id")[0];
		String sportName = map.get("sportName")[0];
		
		//getting logic
		Logic logic  = new SubEventsLogic();
		
		//parsing id of the tournament
		long id = Long.parseLong(attribute);
		
		//getting all subevents
		LinkedList<Entity> events = logic.getAll(id);
		LinkedList<SubEvents> list = new LinkedList<>();
		for(int i = 0; i < events.size(); i++)
		{
			list.add((SubEvents) events.get(i));
		}

		//converting all subevents into matches on passing subEvents and eventPart id
		//these matches will have [home draw away odds]
		SportUtil su = SportUtilFactory.getSportUtil();
		List<Match> matchs = converter.convert(list, su.getEventPartId(sportName, su.getSubEventBettingType(sportName)), su.getSubEventBettingType(sportName));
					
		JSONArray array = new JSONArray();
		JSONObject object;
		
		//TODO bug removed, observe the iteration of this list
		try
		{
			//creating JSON array of JSON objects
			for(Match match : matchs)
			{
				object = new JSONObject();
				
				object.put(Helper.ISCHECKED.toString(), OddsDetection.getTrackedOutcomes().get(match.getHomeTeamOutcomeId()) == null ? "" : "Checked");
				object.put(Helper.HOMETEAM.toString(), match.getHomeTeam());
				object.put(Helper.AWAYTEAM.toString(), match.getAwayTeam());
				object.put(Helper.LEAGUENAME.toString(), match.getTournamentName());
				object.put(Helper.HOMETEAMODDS.toString(), match.getHomeTeamOdds());
				object.put(Helper.AWAYTEAMODDS.toString(), match.getAwayTeamOdds());
				object.put(Helper.DRAWODDS.toString(), match.getDrawOdds());
				object.put(Helper.HOMETEAMOUTCOMEID.toString(), match.getHomeTeamOutcomeId());
				object.put(Helper.AWAYTEAMOUTCOMEID.toString(), match.getAwayTeamOutcomeId());
				object.put(Helper.DRAWOUTCOMEID.toString(), match.getDrawOutcomeId());
				object.put("startTime", match.getStartTime());
				object.put("id", match.getId());
				object.put("sportName", sportName);
	
				array.put(object);
			}
			
		}
		catch(NullPointerException exp)
		{
			
		}

		return array;
	}

}
