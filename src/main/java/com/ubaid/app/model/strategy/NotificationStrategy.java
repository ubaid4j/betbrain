package com.ubaid.app.model.strategy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.schedule1_1.Notification;
import com.ubaid.app.model.schedule1_1.OutcomeNotificationi;

public class NotificationStrategy extends AbstractRequestHandler
{

	private static boolean isLoopRunning = false;
	
	public NotificationStrategy()
	{

	}

	
	@Override
	public JSONArray get(HttpServletResponse response)
	{
		try
		{
			System.out.println("------------------Notifications------------------");
			final Notification notification = new OutcomeNotificationi();
					
	    	response.setContentType("text/event-stream");
	    	response.setCharacterEncoding("utf-8");
	    	
	    	PrintWriter writer = null;
	    	
	    	while(true)
	    	{
	    		setLoopRunning(true);
				JSONObject object = notification.getNotification();

	    		try
	    		{
	    			writer = response.getWriter();
	    			writer.print("data: "  + object.toString());
	    			writer.print("\n\n");	    			
	    			response.flushBuffer();
	    		}
	    		catch(IOException exp)
	    		{
	    			setLoopRunning(false);
	    			writer.close();
	    			return new JSONArray("[{action: 'Notification Loop Ended'}]");
	    		}
	    	}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return new JSONArray("[{action: 'Some Unexpected Error'}]");
		}
		
	}


	static boolean isLoopRunning() {
		return isLoopRunning;
	}


	static void setLoopRunning(boolean isLoopRunningL) {
		isLoopRunning = isLoopRunningL;
	}

}
