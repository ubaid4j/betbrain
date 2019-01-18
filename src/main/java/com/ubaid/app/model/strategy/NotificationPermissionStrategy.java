package com.ubaid.app.model.strategy;

import java.util.Map;

import org.json.JSONArray;

public class NotificationPermissionStrategy extends AbstractRequestHandler
{

	public NotificationPermissionStrategy()
	{
		
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		if(NotificationStrategy.isLoopRunning())
		{
			return new JSONArray("[{action: false}]");
		}
		else
		{
			return new JSONArray("[{action: true}]");		
		}
	}
	

}
