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
import com.ubaid.app.model.strategy.DeleteRegisteredEventsStrategy;
import com.ubaid.app.model.strategy.EventsStrategy;
import com.ubaid.app.model.strategy.HomeAwayDrawRegisterarStrategy;
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

	@SuppressWarnings("unused")
	@Override
	public void init() throws ServletException
	{
		super.init();

		StartUpUtil startUpUtil = new StartUpUtil();
//		startUpUtil.onStart();
		Controller controller = Controller.getController();
//		controller.startSchedular();
		
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
	}
		
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
				
		String className = request.getParameter("className");
		Map<String, String[]> map = request.getParameterMap();
		RequestHandler handler = classHash.get(className);
		JSONArray array = handler.get(map);
		
		Writer writer = response.getWriter();
		writer.write(array.toString());
		writer.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
