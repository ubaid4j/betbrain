package com.ubaid.app.model.strategy;

import java.util.Map;


import org.json.JSONArray;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;

public class DeleteAllTrackedEvents extends AbstractRequestHandler
{

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		//TODO commented allTracked events [due to security]
		@SuppressWarnings("unused")
		Logic logic = new RegisteredOutcomeLogic();
//		boolean action = logic.deleteAll();
//		if(action)

//		Scheduler.getTrackedNotification().clear();

		boolean action = true;
		return new JSONArray("[{action: " + action + "}]");
	}

	public DeleteAllTrackedEvents()
	{
		
	}
	
}
