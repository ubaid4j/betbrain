package com.ubaid.app.model.strategy;

import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.logic.EventsLogic;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Events;

/**
 * this class is responsible to return an array of JSON consisting tournaments of a sport
 * @author ubaid
 *
 */
public class EventsStrategy extends AbstractRequestHandler
{

	public EventsStrategy()
	{

	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		try
		{
			//getting name of sport
			String attribute = map.get("name")[0];
			
			//getting logic
			Logic logic  = new EventsLogic();
			
			//getting all the tournaments by passing the name of support
			LinkedList<Entity> events = logic.getAll(SportUtilFactory.getSportUtil().getSportId(attribute));
			
			//creating an tournament list
			LinkedList<Events> list = new LinkedList<>();
			for(int i = 0; i < events.size(); i++)
			{
				list.add((Events) events.get(i));
			}
			
			JSONArray array = new JSONArray();
			JSONObject object;
			
			//creating an json array
			for(Events event : list)
			{
				object = new JSONObject();
				object.put("id", event.getId());
				object.put("name", event.getEventName());
				object.put("hash", event.getHash());
				object.put("location", event.getLocationName());
				array.put(object);
			}

			return array;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return null;
		}
	}

}
