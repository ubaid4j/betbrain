package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.singleton.DataSource;
import com.ubaid.app.model.startup.StartUpUtil;
import com.ubaid.app.model.strategy.AssianHandicapStrategy;
import com.ubaid.app.model.strategy.AssianOverUnderRegistrarStrategy;
import com.ubaid.app.model.strategy.DeleteAllTrackedEvents;
import com.ubaid.app.model.strategy.DeleteRegisteredEventsStrategy;
import com.ubaid.app.model.strategy.EventsStrategy;
import com.ubaid.app.model.strategy.HomeAwayDrawRegisterarStrategy;
import com.ubaid.app.model.strategy.NotificationPermissionStrategy;
import com.ubaid.app.model.strategy.NotificationStrategy;
import com.ubaid.app.model.strategy.OverUnderStrategy;
import com.ubaid.app.model.strategy.RequestHandler;
import com.ubaid.app.model.strategy.SportStrategy;
import com.ubaid.app.model.strategy.SubEventsStrategy;
import com.ubaid.app.model.strategy.TrackedEventsDisplayStrategy;

/**
 * Servlet implementation class _Sport
 */
public class AppRequestHandler extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Hashtable<String, RequestHandler> classHash;
	
	public AppRequestHandler()
    {
        super();
    }

	
	
	//after deployment, when we goto first time in website 
	//then this init() method called
	//this init method populate the trackedNotification from the server
	//and then start the schedular
	//after that, it make a hashClass table 
	//in which instance of each class of RequestHandler will be stored
	@Override
	public void init() throws ServletException
	{
		super.init();

	    if(!System.getProperty("os.name").toLowerCase().contains("window"))
	    {
			StartUpUtil startUpUtil = new StartUpUtil();
			long startTime = System.nanoTime();
			startUpUtil.onStart();
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println("Final Time " + duration/1000000 + " milli seconds");
			Controller controller = Controller.getController();
			controller.startSchedular();

	    }
	    else
	    {
			StartUpUtil startUpUtil = new StartUpUtil();
			startUpUtil.onStart();
			Controller controller = Controller.getController();
			controller.startSchedular();

	    }

		
		
		
		classHash = new Hashtable<>();
		classHash.put("sport", new SportStrategy());		
		classHash.put("events", new EventsStrategy());
		classHash.put("subEvents", new SubEventsStrategy());
		classHash.put("deleteRegisteredEvents", new DeleteRegisteredEventsStrategy());
		classHash.put("trackedEventsDisplay", new TrackedEventsDisplayStrategy());
		classHash.put("assianHandicap", new AssianHandicapStrategy());
		classHash.put("overUnder", new OverUnderStrategy());
		classHash.put("hodRegistrar", new HomeAwayDrawRegisterarStrategy());
		classHash.put("AHOURegistrar", new AssianOverUnderRegistrarStrategy());
		classHash.put("notification", new NotificationStrategy());
		classHash.put("notificationPermission", new NotificationPermissionStrategy());
		classHash.put("deleteAllTrackedEvent", new DeleteAllTrackedEvents());
	}
		
	//when our container decides to eliminate the 
	//the servlet, then this method called 
	//and this method, simply stop the scheduler and close the connection
	@Override
	public void destroy()
	{
		Controller controller = Controller.getController();
		controller.stopSchedular();

		try
		{
			DataSource.getConnection().close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		super.destroy();		
	}


	//on each get request
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
				
		try
		{
			//first we find the className from the parameter of the request
			String className = request.getParameter("className");
			JSONArray array = null;
			
			try
			{
				//getting Map of parameters
				Map<String, String[]> map = request.getParameterMap();
				
				//getting Request handler class according to className
				RequestHandler handler = classHash.get(className);
				
				//getting JSON array from the request handler get method
				array = handler.get(map);			
			}
			catch(NullPointerException exp)
			{
				//if className is null [in the case of notification SSE request] 
				//then find the Notification Handler [SSE]
				if(className == null)
				{
					RequestHandler handler = classHash.get("notification");
					//getting JSON array from the SSE handler
					array = handler.get(response);											
				}
				
			}
			
			//after that, writer writes the JSON array to response
			Writer writer = response.getWriter();
			writer.write(array.toString());
			writer.flush();
			
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
