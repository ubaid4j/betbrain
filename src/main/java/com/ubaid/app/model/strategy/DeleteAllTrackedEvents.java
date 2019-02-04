package com.ubaid.app.model.strategy;

import java.util.Map;


import org.json.JSONArray;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.logic.TrackedMatchLogic;
import com.ubaid.app.model.schedule1_1.oddsDetection.OddsDetection;
import com.ubaid.app.model.schedule1_1.thresholdDetection.ThresholdDetection;

public class DeleteAllTrackedEvents extends AbstractRequestHandler
{

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		Logic logic = new RegisteredOutcomeLogic();
		Logic logic2 = new TrackedMatchLogic();
		boolean action = logic.deleteAll();
		
		boolean action2 = logic2.deleteAll();

		
		
		if(action && action2)
		{
			OddsDetection.getTrackedOutcomes().clear();
			ThresholdDetection.getTrackedOutcomes().clear();
		}

		

		
		return new JSONArray("[{action: " + (action && action2) + "}]");
	}

	public DeleteAllTrackedEvents()
	{
		
	}
	
}
