package com.ubaid.app.model.strategy;

import java.util.Map;

import org.json.JSONArray;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.schedule1_1.Scheduler;

/**
 * this class is responsible to delete a registered outcome from the database as well as from the hashtable
 * @author ubaid
 *
 */
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
			//getting logic
			Logic logic = new RegisteredOutcomeLogic();
		
			//getting id of registered outcome which is suppose to delete
			long id = Long.parseLong(map.get("id")[0]);
			
			//if from database, it deleted then it will delete from the hashtable
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
