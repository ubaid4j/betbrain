package com.ubaid.app.model.strategy;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.logic.OutcomeLogic;
import com.ubaid.app.model.logic.RemovedOutComeLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Outcome;

public class RemovedOutcomes extends AbstractRequestHandler
{

	public RemovedOutcomes()
	{
		
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
	
		try
		{
			JSONArray array = new JSONArray();
			OutcomeLogic logic = new RemovedOutComeLogic();
			List<Entity> outcomes = logic.getAll();
	
			for(Entity entity : outcomes)
			{
				Outcome outcome = (Outcome) entity;
				JSONObject object = new JSONObject();
				object.put(Helper.LASTUPDATETIME.toString(), outcome.getChangedTime());
				object.put(Helper.LEAGUENAME.toString(), outcome.getLeagueName());
				object.put(Helper.MATCHNAME.toString(), outcome.getMatchName());
				object.put(Helper.PARTICIPANT.toString(), outcome.getParticipant());
				object.put(Helper.THRESHOLD.toString(), outcome.getThreshold());
				object.put(Helper.ODDS.toString(), outcome.getOdds());
				object.put(Helper.BETTINGTYPE.toString(), outcome.getBettingType());
				object.put("sport", outcome.getSportName());
				array.put(object);
			}
			
			return array;
		}
		catch(NullPointerException exp)
		{
			
			return null;
		}
	}
	
	

}
