package com.ubaid.app.model.strategy;

import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.logic.EventsLogic;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Events;

public class EventsStrategy implements RequestHandler
{

	public EventsStrategy()
	{

	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		String attribute = map.get("name")[0];
		Logic logic  = new EventsLogic();
		
		SportType type;
		
		switch (attribute)
		{
			case "FootBall":
				type = SportType.FootBall;
				break;
			case "Handball":
				type = SportType.HandBall;
				break;
			case "Basketball":
				type = SportType.BasketBall;
				break;
			case "Volleyball":
				type = SportType.VolleyBall;
				break;
			case "E-Sports":
				type = SportType.ESports;
				break;
			case "IceHockey":
				type = SportType.IceHokey;
				break;
			default:
				type = SportType.FootBall;
				break;
		}
		
		LinkedList<Entity> events = logic.getAll(type);
		
		LinkedList<Events> list = new LinkedList<>();
		
		for(int i = 0; i < events.size(); i++)
		{
			list.add((Events) events.get(i));
		}
		
		JSONArray array = new JSONArray();
		JSONObject object;
		
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

}
