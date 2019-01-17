package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.SportsLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Sport;
import com.ubaid.app.model.startup.StartUpUtil;

/**
 * Servlet implementation class _Sport
 */
public class _Sport extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private Logic sportLogic;
	private StartUpUtil startUpUtil = new StartUpUtil();
	
	public _Sport()
    {
        super();
        sportLogic = new SportsLogic();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		if(StartUpUtil.isFirst)
		{
			StartUpUtil.isFirst = false;
			startUpUtil.onStart();
			Controller controller = Controller.getController();
			controller.startSchedular();
		}

		
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
		
		response.getWriter().write(array.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
