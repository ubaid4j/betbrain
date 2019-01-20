package com.ubaid.app.model.strategy;

import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Outcome;

/**
 * this class is responsible to return the json array of tracked events [registered outcomes table]
 * @author ubaid
 *
 */
public class TrackedEventsDisplayStrategy extends AbstractRequestHandler
{

	public TrackedEventsDisplayStrategy()
	{
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		//getting logic
		Logic logic = new RegisteredOutcomeLogic();

		//getting all registered outcome
		LinkedList<Entity> _rOutcomes = logic.getAll();
		
		JSONArray array = new JSONArray();
		JSONObject object = null;
		
		//creating JSON array of JSON object
		for(Entity entity : _rOutcomes)
		{
			Outcome outcome  = (Outcome) entity;
			object = new JSONObject();
			object.put(Helper.LEAGUENAME.toString(), outcome.getLeagueName());
			object.put(Helper.MATCHNAME.toString(), outcome.getMatchName());
			object.put(Helper.BETTINGTYPE.toString(), outcome.getBettingType());
			object.put(Helper.PARTICIPANT.toString(), outcome.getParticipant());
			object.put(Helper.OUTCOMEID.toString(), outcome.getId());
			array.put(object);
		}
		return array;
	}
}
