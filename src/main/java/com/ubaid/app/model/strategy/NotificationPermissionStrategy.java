package com.ubaid.app.model.strategy;

import java.util.Map;

import org.json.JSONArray;

/**
 * this class is responsible to permit SSE or not
 * this class check if SSE loop is established, then it send false otherwise no
 * @author ubaid
 *
 */
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
