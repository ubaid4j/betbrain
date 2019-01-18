package com.ubaid.app.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.SportsLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Sport;

public class SportStrategy extends AbstractRequestHandler
{

	public SportStrategy()
	{

	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		Logic sportLogic = new SportsLogic();


		List<Entity> entities = sportLogic.getAll();
		
		List<Sport> sports = new ArrayList<>();
		
		for(int i = 0; i < entities.size(); i++)
		{
			sports.add((Sport) entities.get(i));
		}
				
		
		JSONArray array = new JSONArray();
		JSONObject object;
		
		for(Sport sport : sports)
		{
			object = new JSONObject();
			object.put("name", sport.getName());
			array.put(object);
		}

		
		
		return array;
	}

}
