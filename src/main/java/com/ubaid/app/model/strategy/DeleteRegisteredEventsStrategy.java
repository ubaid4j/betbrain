package com.ubaid.app.model.strategy;

import java.util.Map;

import org.json.JSONArray;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.schedule1_1.Scheduler;

public class DeleteRegisteredEventsStrategy extends AbstractRequestHandler
{

	public DeleteRegisteredEventsStrategy()
	{
		
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		try
		{
			Logic logic = new RegisteredOutcomeLogic();
			long id = Long.parseLong(map.get("id")[0]);
			
			if(logic.delete(id))
				Scheduler.removeFromTrackedEvents(id);			
		}
		catch(Exception exp)
		{
			return new JSONArray("[{action: 'error'}]");
			
		}
		
		return new JSONArray("[{action: 'deleted'}]");
	}

}
