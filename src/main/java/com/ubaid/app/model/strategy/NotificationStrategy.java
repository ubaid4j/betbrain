package com.ubaid.app.model.strategy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.schedule1_1.Notification;
import com.ubaid.app.model.schedule1_1.OutcomeNotificationi;

/**
 * this class is responsisble to response SSE events
 * @author ubaid
 *
 */
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

			//getting notification service
			final Notification notification = new OutcomeNotificationi();
					
	    	response.setContentType("text/event-stream");
	    	response.setCharacterEncoding("utf-8");
	    	
	    	PrintWriter writer = null;
	    	
	    	//in loop
	    	while(true)
	    	{
	    		//setting loop to true
	    		setLoopRunning(true);
	    		
	    		//getting new notification from the service
				JSONObject object = notification.getNotification();

	    		try
	    		{
					//writing down
	    			writer = response.getWriter();
	    			writer.print("data: "  + object.toString());
	    			writer.print("\n\n");	    			
	    			response.flushBuffer();
	    		}
	    		catch(IOException exp)
	    		{
	    			//when SSE event removed, then exception arise, setting loop to false and closing the writer
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
