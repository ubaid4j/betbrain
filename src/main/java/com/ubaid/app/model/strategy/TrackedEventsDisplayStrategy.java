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

public class TrackedEventsDisplayStrategy implements RequestHandler
{

	public TrackedEventsDisplayStrategy()
	{
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		//here to get all outcomes and show this in the file with out session, (using json)	
		Logic logic = new RegisteredOutcomeLogic();
		LinkedList<Entity> _rOutcomes = logic.getAll();
		
		JSONArray array = new JSONArray();
		JSONObject object = null;
		
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
